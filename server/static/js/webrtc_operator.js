// WebRTC Operator Client
// Connects to the signaling WebSocket and establishes a WebRTC peer connection
// to receive a device's video stream and send control commands via DataChannel.

let ws = null;
let pc = null;
let dc = null; // DataChannel
let currentRoomId = null;

const signalingHost = window.location.hostname || 'localhost';
const signalingPort = '8082';

function connectRoom(roomId) {
    if (!roomId) {
        alert('Please select a device/room');
        return;
    }

    disconnect(); // clean up any existing connection

    currentRoomId = roomId;
    document.getElementById('webrtcPanel').style.display = 'block';
    document.getElementById('connStatus').textContent = 'Connecting...';
    document.getElementById('connStatus').className = 'badge badge-yellow';

    // Connect to signaling server as operator
    const wsUrl = `ws://${signalingHost}:${signalingPort}/ws?role=operator&room_id=${encodeURIComponent(roomId)}`;
    ws = new WebSocket(wsUrl);

    ws.onopen = function() {
        console.log('[WS] Connected to signaling server');
        document.getElementById('connStatus').textContent = 'WS Connected';
        // Send a ping to verify
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
}

function createPeerConnection() {
    const config = {
        iceServers: [{ urls: 'stun:stun.l.google.com:19302' }]
    };

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

    pc.ontrack = function(event) {
        console.log('[WebRTC] Track received:', event.track.kind);
        const video = document.getElementById('remoteVideo');
        if (event.streams && event.streams[0]) {
            video.srcObject = event.streams[0];
        }
        document.getElementById('connStatus').textContent = 'Streaming';
        document.getElementById('connStatus').className = 'badge badge-green';
    };

    pc.ondatachannel = function(event) {
        dc = event.channel;
        dc.onmessage = function(e) {
            console.log('[DC] Received:', e.data);
        };
        console.log('[WebRTC] DataChannel received:', dc.label);
    };

    pc.onconnectionstatechange = function() {
        console.log('[WebRTC] Connection state:', pc.connectionState);
        const status = document.getElementById('connStatus');
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

    // Create DataChannel for control commands
    dc = pc.createDataChannel('control');
    dc.onopen = function() {
        console.log('[DC] Control channel open');
    };

    // Create and send offer
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

    const content = msg.content || {};
    // If we receive an SDP offer from device, this means the device initiated
    const sdp = content.sdp || (msg.atom && JSON.parse(msg.atom).sdp);

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
    const content = msg.content || {};
    pc.setRemoteDescription(new RTCSessionDescription({ type: 'answer', sdp: content.sdp }))
        .catch(function(err) {
            console.error('[WebRTC] Set remote description error:', err);
        });
}

function handleICECandidate(msg) {
    if (!pc) return;
    const content = msg.content || {};
    const candidate = new RTCIceCandidate({
        candidate: content.candidate,
        sdpMid: content.sdpMid,
        sdpMLineIndex: content.sdpMLineIndex
    });
    pc.addIceCandidate(candidate)
        .catch(function(err) {
            console.error('[WebRTC] Add ICE candidate error:', err);
        });
}

function sendControl(command) {
    if (dc && dc.readyState === 'open') {
        dc.send(JSON.stringify({ command: command, timestamp: Date.now() }));
        console.log('[DC] Sent control:', command);
    } else if (ws && ws.readyState === WebSocket.OPEN) {
        // Fallback: send via signaling
        ws.send(JSON.stringify({
            atom: currentRoomId,
            content: {
                content_type: 'control',
                command: command
            }
        }));
    } else {
        console.warn('No connection available to send control command');
    }
}

function disconnect() {
    if (dc) { dc.close(); dc = null; }
    if (pc) { pc.close(); pc = null; }
    if (ws) { ws.close(); ws = null; }

    const video = document.getElementById('remoteVideo');
    if (video) video.srcObject = null;

    const status = document.getElementById('connStatus');
    if (status) {
        status.textContent = 'Disconnected';
        status.className = 'badge badge-red';
    }

    currentRoomId = null;
}
