// WebRTC Operator Client
// Connects to the signaling WebSocket and establishes a WebRTC peer connection
// to receive a device's video stream and send control commands via DataChannel.
//
// ICE_SERVERS is injected by the HTML template from server config (TURN/STUN).

let ws = null;
let pc = null;
let dc = null; // DataChannel
let currentRoomId = null;

const signalingHost = window.location.hostname || 'localhost';
const signalingPort = '8082';

// --- Interaction state ---
let isDragging = false;
let dragPending = false;
let dragStartPos = null;
let mouseDownPos = null;
let lastDragTime = 0;
const DRAG_THRESHOLD = 5; // px
const DRAG_THROTTLE = 50; // ms
const CMD_LOG_MAX = 20;

function getICEConfig() {
    const servers = (typeof ICE_SERVERS !== 'undefined' && ICE_SERVERS) ? ICE_SERVERS : [];
    if (servers.length === 0) {
        return { iceServers: [{ urls: 'stun:stun.l.google.com:19302' }] };
    }
    return { iceServers: servers };
}

// --- SDK-compatible action sender ---
function sendAction(action, params) {
    const msg = Object.assign({ action: action }, params || {});
    const json = JSON.stringify(msg);

    if (dc && dc.readyState === 'open') {
        dc.send(json);
        console.log('[DC] Sent:', json);
    } else if (ws && ws.readyState === WebSocket.OPEN) {
        ws.send(JSON.stringify({
            atom: currentRoomId,
            content: Object.assign({ content_type: 'control' }, msg)
        }));
        console.log('[WS] Sent control:', json);
    } else {
        console.warn('No connection available to send action');
        return;
    }
    logCommand(msg);
    updateLastAction(action);
}

// Backward-compatible wrapper
function sendControl(command) {
    switch (command) {
        case 'home':
            sendAction('keyInput', { key: 'Home', code: 3 });
            break;
        case 'back':
            sendAction('goBack');
            break;
        case 'recents':
            sendAction('keyInput', { key: 'Recents', code: 187 });
            break;
        case 'screenshot':
            sendAction('screenshot');
            break;
        default:
            sendAction(command);
    }
}

// --- Coordinate helpers ---
function videoCoords(event) {
    const video = document.getElementById('remoteVideo');
    if (!video) return null;
    const rect = video.getBoundingClientRect();
    const x = (event.clientX - rect.left) / rect.width;
    const y = (event.clientY - rect.top) / rect.height;
    return {
        x: Math.max(0, Math.min(1, parseFloat(x.toFixed(4)))),
        y: Math.max(0, Math.min(1, parseFloat(y.toFixed(4))))
    };
}

function touchCoords(touch) {
    const video = document.getElementById('remoteVideo');
    if (!video) return null;
    const rect = video.getBoundingClientRect();
    const x = (touch.clientX - rect.left) / rect.width;
    const y = (touch.clientY - rect.top) / rect.height;
    return {
        x: Math.max(0, Math.min(1, parseFloat(x.toFixed(4)))),
        y: Math.max(0, Math.min(1, parseFloat(y.toFixed(4))))
    };
}

function pixelDistance(a, b) {
    const video = document.getElementById('remoteVideo');
    if (!video) return 0;
    const dx = (a.x - b.x) * video.clientWidth;
    const dy = (a.y - b.y) * video.clientHeight;
    return Math.sqrt(dx * dx + dy * dy);
}

// --- Mouse event handlers ---
function initVideoInteraction() {
    const video = document.getElementById('remoteVideo');
    const overlay = document.getElementById('videoOverlay');
    if (!video || !overlay) return;

    // Mouse events
    overlay.addEventListener('mousedown', onMouseDown);
    overlay.addEventListener('mousemove', onMouseMove);
    overlay.addEventListener('mouseup', onMouseUp);
    overlay.addEventListener('mouseleave', onMouseUp);
    overlay.addEventListener('wheel', onWheel, { passive: false });

    // Touch events
    overlay.addEventListener('touchstart', onTouchStart, { passive: false });
    overlay.addEventListener('touchmove', onTouchMove, { passive: false });
    overlay.addEventListener('touchend', onTouchEnd);
    overlay.addEventListener('touchcancel', onTouchEnd);

    // Prevent context menu on overlay
    overlay.addEventListener('contextmenu', function(e) { e.preventDefault(); });
}

function onMouseDown(e) {
    e.preventDefault();
    const coords = videoCoords(e);
    if (!coords) return;
    mouseDownPos = { x: e.clientX, y: e.clientY };
    dragStartPos = coords;
    dragPending = true;
    isDragging = false;
}

function onMouseMove(e) {
    if (!dragPending && !isDragging) return;
    e.preventDefault();

    const coords = videoCoords(e);
    if (!coords) return;

    if (dragPending && !isDragging) {
        const dx = e.clientX - mouseDownPos.x;
        const dy = e.clientY - mouseDownPos.y;
        if (Math.sqrt(dx * dx + dy * dy) >= DRAG_THRESHOLD) {
            isDragging = true;
            dragPending = false;
            sendAction('dragStart', dragStartPos);
            drawDragStart(dragStartPos);
        }
        return;
    }

    if (isDragging) {
        const now = Date.now();
        if (now - lastDragTime >= DRAG_THROTTLE) {
            lastDragTime = now;
            sendAction('drag', coords);
            drawDragMove(coords);
        }
    }
}

function onMouseUp(e) {
    if (!dragPending && !isDragging) return;

    const coords = videoCoords(e);
    if (!coords) return;

    if (isDragging) {
        sendAction('dragEnd', coords);
        drawDragEnd(coords);
    } else {
        sendAction('click', dragStartPos);
        drawClick(dragStartPos);
    }

    dragPending = false;
    isDragging = false;
    dragStartPos = null;
    mouseDownPos = null;
}

function onWheel(e) {
    e.preventDefault();
    const coords = videoCoords(e);
    if (!coords) return;
    sendAction('scroll', {
        x: coords.x,
        y: coords.y,
        deltaX: Math.round(e.deltaX),
        deltaY: Math.round(e.deltaY)
    });
    drawScroll(coords, e.deltaX, e.deltaY);
}

// --- Touch event handlers ---
function onTouchStart(e) {
    e.preventDefault();
    if (e.touches.length !== 1) return;
    const coords = touchCoords(e.touches[0]);
    if (!coords) return;
    const touch = e.touches[0];
    mouseDownPos = { x: touch.clientX, y: touch.clientY };
    dragStartPos = coords;
    dragPending = true;
    isDragging = false;
}

function onTouchMove(e) {
    e.preventDefault();
    if (e.touches.length !== 1) return;
    if (!dragPending && !isDragging) return;

    const touch = e.touches[0];
    const coords = touchCoords(touch);
    if (!coords) return;

    if (dragPending && !isDragging) {
        const dx = touch.clientX - mouseDownPos.x;
        const dy = touch.clientY - mouseDownPos.y;
        if (Math.sqrt(dx * dx + dy * dy) >= DRAG_THRESHOLD) {
            isDragging = true;
            dragPending = false;
            sendAction('dragStart', dragStartPos);
            drawDragStart(dragStartPos);
        }
        return;
    }

    if (isDragging) {
        const now = Date.now();
        if (now - lastDragTime >= DRAG_THROTTLE) {
            lastDragTime = now;
            sendAction('drag', coords);
            drawDragMove(coords);
        }
    }
}

function onTouchEnd(e) {
    if (!dragPending && !isDragging) return;

    if (isDragging) {
        // Use last known drag position
        sendAction('dragEnd', dragStartPos);
        drawDragEnd(dragStartPos);
    } else {
        sendAction('click', dragStartPos);
        drawClick(dragStartPos);
    }

    dragPending = false;
    isDragging = false;
    dragStartPos = null;
    mouseDownPos = null;
}

// --- Canvas visual feedback ---
let feedbackCanvas = null;
let feedbackCtx = null;
let dragPath = [];

function getCanvas() {
    if (!feedbackCanvas) {
        feedbackCanvas = document.getElementById('videoOverlay');
        if (feedbackCanvas) feedbackCtx = feedbackCanvas.getContext('2d');
    }
    return feedbackCtx;
}

function resizeCanvas() {
    const canvas = document.getElementById('videoOverlay');
    const video = document.getElementById('remoteVideo');
    if (!canvas || !video) return;
    canvas.width = video.clientWidth;
    canvas.height = video.clientHeight;
}

function clearCanvas() {
    const ctx = getCanvas();
    if (!ctx) return;
    ctx.clearRect(0, 0, feedbackCanvas.width, feedbackCanvas.height);
}

function drawClick(coords) {
    resizeCanvas();
    const ctx = getCanvas();
    if (!ctx) return;
    clearCanvas();

    const x = coords.x * feedbackCanvas.width;
    const y = coords.y * feedbackCanvas.height;

    ctx.beginPath();
    ctx.arc(x, y, 16, 0, Math.PI * 2);
    ctx.strokeStyle = '#e94560';
    ctx.lineWidth = 3;
    ctx.stroke();

    ctx.beginPath();
    ctx.arc(x, y, 4, 0, Math.PI * 2);
    ctx.fillStyle = '#e94560';
    ctx.fill();

    ctx.font = '11px monospace';
    ctx.fillStyle = '#ff6b6b';
    ctx.fillText('(' + coords.x.toFixed(2) + ', ' + coords.y.toFixed(2) + ')', x + 20, y - 8);

    setTimeout(clearCanvas, 500);
}

function drawDragStart(coords) {
    resizeCanvas();
    clearCanvas();
    dragPath = [coords];

    const ctx = getCanvas();
    if (!ctx) return;
    const x = coords.x * feedbackCanvas.width;
    const y = coords.y * feedbackCanvas.height;

    ctx.beginPath();
    ctx.arc(x, y, 8, 0, Math.PI * 2);
    ctx.fillStyle = 'rgba(78, 205, 196, 0.7)';
    ctx.fill();
}

function drawDragMove(coords) {
    dragPath.push(coords);
    const ctx = getCanvas();
    if (!ctx) return;

    clearCanvas();

    // Draw path
    ctx.beginPath();
    ctx.strokeStyle = 'rgba(78, 205, 196, 0.8)';
    ctx.lineWidth = 2;
    for (let i = 0; i < dragPath.length; i++) {
        const px = dragPath[i].x * feedbackCanvas.width;
        const py = dragPath[i].y * feedbackCanvas.height;
        if (i === 0) ctx.moveTo(px, py);
        else ctx.lineTo(px, py);
    }
    ctx.stroke();

    // Start dot
    const s = dragPath[0];
    ctx.beginPath();
    ctx.arc(s.x * feedbackCanvas.width, s.y * feedbackCanvas.height, 6, 0, Math.PI * 2);
    ctx.fillStyle = 'rgba(78, 205, 196, 0.7)';
    ctx.fill();

    // Current dot
    const c = coords;
    ctx.beginPath();
    ctx.arc(c.x * feedbackCanvas.width, c.y * feedbackCanvas.height, 6, 0, Math.PI * 2);
    ctx.fillStyle = 'rgba(233, 69, 96, 0.8)';
    ctx.fill();
}

function drawDragEnd(coords) {
    dragPath.push(coords);
    const ctx = getCanvas();
    if (!ctx) return;

    // Draw final path with arrow
    clearCanvas();
    ctx.beginPath();
    ctx.strokeStyle = 'rgba(78, 205, 196, 0.6)';
    ctx.lineWidth = 2;
    for (let i = 0; i < dragPath.length; i++) {
        const px = dragPath[i].x * feedbackCanvas.width;
        const py = dragPath[i].y * feedbackCanvas.height;
        if (i === 0) ctx.moveTo(px, py);
        else ctx.lineTo(px, py);
    }
    ctx.stroke();

    // End marker
    const ex = coords.x * feedbackCanvas.width;
    const ey = coords.y * feedbackCanvas.height;
    ctx.beginPath();
    ctx.arc(ex, ey, 8, 0, Math.PI * 2);
    ctx.fillStyle = 'rgba(233, 69, 96, 0.8)';
    ctx.fill();

    dragPath = [];
    setTimeout(clearCanvas, 800);
}

function drawScroll(coords, deltaX, deltaY) {
    resizeCanvas();
    const ctx = getCanvas();
    if (!ctx) return;
    clearCanvas();

    const x = coords.x * feedbackCanvas.width;
    const y = coords.y * feedbackCanvas.height;
    const arrowLen = 30;

    ctx.strokeStyle = '#f0c040';
    ctx.lineWidth = 3;
    ctx.lineCap = 'round';

    if (Math.abs(deltaY) > 0) {
        const dir = deltaY > 0 ? 1 : -1;
        ctx.beginPath();
        ctx.moveTo(x, y - arrowLen * dir);
        ctx.lineTo(x, y + arrowLen * dir);
        ctx.stroke();
        // Arrowhead
        ctx.beginPath();
        ctx.moveTo(x - 8, y + (arrowLen - 10) * dir);
        ctx.lineTo(x, y + arrowLen * dir);
        ctx.lineTo(x + 8, y + (arrowLen - 10) * dir);
        ctx.stroke();
    }

    if (Math.abs(deltaX) > 0) {
        const dir = deltaX > 0 ? 1 : -1;
        ctx.beginPath();
        ctx.moveTo(x - arrowLen * dir, y);
        ctx.lineTo(x + arrowLen * dir, y);
        ctx.stroke();
        ctx.beginPath();
        ctx.moveTo(x + (arrowLen - 10) * dir, y - 8);
        ctx.lineTo(x + arrowLen * dir, y);
        ctx.lineTo(x + (arrowLen - 10) * dir, y + 8);
        ctx.stroke();
    }

    ctx.font = '11px monospace';
    ctx.fillStyle = '#f0c040';
    ctx.fillText('scroll dY=' + deltaY, x + 20, y - 8);

    setTimeout(clearCanvas, 500);
}

// --- Command log ---
function logCommand(msg) {
    const logBody = document.getElementById('cmdLogBody');
    if (!logBody) return;

    const row = document.createElement('tr');
    const time = new Date().toLocaleTimeString();
    const action = msg.action || '?';
    const paramsCopy = Object.assign({}, msg);
    delete paramsCopy.action;
    const paramsStr = Object.keys(paramsCopy).length > 0 ? JSON.stringify(paramsCopy) : '';

    row.innerHTML = '<td>' + time + '</td><td><strong>' + action + '</strong></td><td class="mono">' + escapeHtml(paramsStr) + '</td>';
    logBody.insertBefore(row, logBody.firstChild);

    // Keep max entries
    while (logBody.children.length > CMD_LOG_MAX) {
        logBody.removeChild(logBody.lastChild);
    }
}

function escapeHtml(s) {
    const div = document.createElement('div');
    div.textContent = s;
    return div.innerHTML;
}

function updateLastAction(action) {
    const el = document.getElementById('lastAction');
    if (el) el.textContent = action;
}

function updateDCStatus() {
    const el = document.getElementById('dcStatus');
    if (!el) return;
    if (dc && dc.readyState === 'open') {
        el.textContent = 'open';
        el.className = 'badge badge-green';
    } else {
        el.textContent = dc ? dc.readyState : 'closed';
        el.className = 'badge badge-red';
    }
}

// --- Keyboard / text input ---
function initKeyboardInput() {
    const keyInput = document.getElementById('keyInputField');
    if (!keyInput) return;

    keyInput.addEventListener('keydown', function(e) {
        if (e.key === 'Enter') {
            e.preventDefault();
            sendAction('keyInput', { key: 'Enter', code: 13 });
        } else if (e.key === 'Backspace') {
            e.preventDefault();
            sendAction('keyInput', { key: 'Backspace', code: 8 });
        } else if (e.key === 'Escape') {
            e.preventDefault();
            sendAction('keyInput', { key: 'Escape', code: 27 });
        } else if (e.key === 'Tab') {
            e.preventDefault();
            sendAction('keyInput', { key: 'Tab', code: 9 });
        }
    });
}

function sendPasteText() {
    const input = document.getElementById('pasteTextField');
    if (!input || !input.value) return;
    sendAction('paste', { text: input.value });
    input.value = '';
}

function sendManualClick() {
    const xInput = document.getElementById('manualX');
    const yInput = document.getElementById('manualY');
    if (!xInput || !yInput) return;
    const x = parseFloat(xInput.value);
    const y = parseFloat(yInput.value);
    if (isNaN(x) || isNaN(y) || x < 0 || x > 1 || y < 0 || y > 1) {
        alert('X and Y must be between 0 and 1');
        return;
    }
    sendAction('click', { x: x, y: y });
}

function sendPing() {
    sendAction('ping', { timestamp: Date.now() });
}

// --- Connection ---
function connectRoom(roomId) {
    if (!roomId) {
        alert('Please select a device/room');
        return;
    }

    disconnect();

    currentRoomId = roomId;
    document.getElementById('webrtcPanel').style.display = 'block';
    document.getElementById('connStatus').textContent = 'Connecting...';
    document.getElementById('connStatus').className = 'badge badge-yellow';

    const wsUrl = 'ws://' + signalingHost + ':' + signalingPort + '/ws?role=operator&room_id=' + encodeURIComponent(roomId);
    ws = new WebSocket(wsUrl);

    ws.onopen = function() {
        console.log('[WS] Connected to signaling server');
        document.getElementById('connStatus').textContent = 'WS Connected';
        ws.send(JSON.stringify({
            atom: roomId,
            content: { content_type: 'ping' }
        }));
    };

    ws.onmessage = function(event) {
        const msg = JSON.parse(event.data);
        console.log('[WS] Received:', msg);

        if (msg.error) {
            console.error('[WS] Error:', msg.error);
            return;
        }

        if (!msg.content) return;

        switch (msg.content.content_type) {
            case 'pong':
                console.log('[WS] Pong received');
                createPeerConnection();
                break;
            case 'sdp_offer':
                handleSDPOffer(msg);
                break;
            case 'sdp_answer':
                handleSDPAnswer(msg);
                break;
            case 'ice_candidate':
                handleICECandidate(msg);
                break;
            case 'done':
                disconnect();
                break;
        }
    };

    ws.onerror = function(err) {
        console.error('[WS] Error:', err);
        document.getElementById('connStatus').textContent = 'WS Error';
        document.getElementById('connStatus').className = 'badge badge-red';
    };

    ws.onclose = function() {
        console.log('[WS] Disconnected');
    };

    // Initialize interaction after panel is visible
    setTimeout(function() {
        initVideoInteraction();
        initKeyboardInput();
    }, 100);
}

function createPeerConnection() {
    const config = getICEConfig();
    console.log('[WebRTC] ICE config:', JSON.stringify(config));

    config.bundlePolicy = 'max-bundle';
    config.rtcpMuxPolicy = 'require';
    config.sdpSemantics = 'unified-plan';
    config.iceCandidatePoolSize = 0;

    pc = new RTCPeerConnection(config);

    pc.onicecandidate = function(event) {
        if (event.candidate && ws && ws.readyState === WebSocket.OPEN) {
            ws.send(JSON.stringify({
                atom: currentRoomId,
                content: {
                    content_type: 'ice_candidate',
                    candidate: event.candidate.candidate,
                    sdpMid: event.candidate.sdpMid,
                    sdpMLineIndex: event.candidate.sdpMLineIndex
                }
            }));
        }
    };

    pc.oniceconnectionstatechange = function() {
        console.log('[WebRTC] ICE state:', pc.iceConnectionState);
        var status = document.getElementById('connStatus');
        switch (pc.iceConnectionState) {
            case 'checking':
                status.textContent = 'ICE Checking...';
                status.className = 'badge badge-yellow';
                break;
            case 'connected':
            case 'completed':
                status.textContent = 'ICE Connected';
                status.className = 'badge badge-green';
                break;
            case 'failed':
                status.textContent = 'ICE Failed';
                status.className = 'badge badge-red';
                break;
            case 'disconnected':
                status.textContent = 'ICE Disconnected';
                status.className = 'badge badge-red';
                break;
        }
    };

    pc.ontrack = function(event) {
        console.log('[WebRTC] Track received:', event.track.kind);
        var video = document.getElementById('remoteVideo');
        if (event.streams && event.streams[0]) {
            video.srcObject = event.streams[0];
        }
        document.getElementById('connStatus').textContent = 'Streaming';
        document.getElementById('connStatus').className = 'badge badge-green';
        // Resize canvas to match video once playing
        video.addEventListener('loadedmetadata', resizeCanvas);
        video.addEventListener('resize', resizeCanvas);
        setTimeout(resizeCanvas, 200);
    };

    pc.ondatachannel = function(event) {
        dc = event.channel;
        dc.onmessage = function(e) {
            console.log('[DC] Received:', e.data);
        };
        dc.onopen = function() {
            console.log('[DC] Received channel open');
            updateDCStatus();
        };
        dc.onclose = function() {
            updateDCStatus();
        };
        console.log('[WebRTC] DataChannel received:', dc.label);
    };

    pc.onconnectionstatechange = function() {
        console.log('[WebRTC] Connection state:', pc.connectionState);
        var status = document.getElementById('connStatus');
        switch (pc.connectionState) {
            case 'connected':
                status.textContent = 'Connected';
                status.className = 'badge badge-green';
                break;
            case 'disconnected':
            case 'failed':
                status.textContent = pc.connectionState;
                status.className = 'badge badge-red';
                break;
        }
    };

    dc = pc.createDataChannel('control');
    dc.onopen = function() {
        console.log('[DC] Control channel open');
        updateDCStatus();
    };
    dc.onclose = function() {
        updateDCStatus();
    };

    pc.createOffer({ offerToReceiveVideo: true, offerToReceiveAudio: false })
        .then(function(offer) {
            return pc.setLocalDescription(offer);
        })
        .then(function() {
            ws.send(JSON.stringify({
                atom: currentRoomId,
                content: {
                    content_type: 'sdp_offer',
                    sdp: pc.localDescription.sdp
                }
            }));
            console.log('[WebRTC] SDP offer sent');
        })
        .catch(function(err) {
            console.error('[WebRTC] Create offer error:', err);
        });
}

function handleSDPOffer(msg) {
    if (!pc) createPeerConnection();

    var content = msg.content || {};
    var sdp = content.sdp;

    pc.setRemoteDescription(new RTCSessionDescription({ type: 'offer', sdp: sdp }))
        .then(function() { return pc.createAnswer(); })
        .then(function(answer) { return pc.setLocalDescription(answer); })
        .then(function() {
            ws.send(JSON.stringify({
                atom: currentRoomId,
                content: {
                    content_type: 'sdp_answer',
                    sdp: pc.localDescription.sdp
                }
            }));
        })
        .catch(function(err) {
            console.error('[WebRTC] Handle SDP offer error:', err);
        });
}

function handleSDPAnswer(msg) {
    if (!pc) return;
    var content = msg.content || {};
    pc.setRemoteDescription(new RTCSessionDescription({ type: 'answer', sdp: content.sdp }))
        .catch(function(err) {
            console.error('[WebRTC] Set remote description error:', err);
        });
}

function handleICECandidate(msg) {
    if (!pc) return;
    var content = msg.content || {};
    var candidate = new RTCIceCandidate({
        candidate: content.candidate,
        sdpMid: content.sdpMid,
        sdpMLineIndex: content.sdpMLineIndex
    });
    pc.addIceCandidate(candidate)
        .catch(function(err) {
            console.error('[WebRTC] Add ICE candidate error:', err);
        });
}

function disconnect() {
    if (dc) { dc.close(); dc = null; }
    if (pc) { pc.close(); pc = null; }
    if (ws) { ws.close(); ws = null; }

    var video = document.getElementById('remoteVideo');
    if (video) video.srcObject = null;

    var status = document.getElementById('connStatus');
    if (status) {
        status.textContent = 'Disconnected';
        status.className = 'badge badge-red';
    }

    updateDCStatus();
    currentRoomId = null;
    isDragging = false;
    dragPending = false;
    dragStartPos = null;
    mouseDownPos = null;
    clearCanvas();
}

// Handle window resize
window.addEventListener('resize', resizeCanvas);
