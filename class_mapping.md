# Nova2 类名还原映射表

逆向还原文件与原始混淆类名的对应关系，共 178 个 Java 文件，11 个包。

## 调用链概览

```
┌─────────────────────────────────────────────────────────────────────┐
│ entry/MainActivity                                                  │
│   └→ entry/Kucopd.init(context, "n3Hza")                           │
│        └→ new Thread(InitRunnable)                                  │
│             └→ [WebViewBootstrap].start(context, config, null)      │
│                  ↓ (未单独还原，原始类 llllIllIl1)                     │
│                  ├→ core/AppContext 初始化                            │
│                  └→ touch/TaskOrchestrator.startTask(webView)       │
│                       ├→ api/ApiClient.getToken()                   │
│                       ├→ 判断 signaling / non-signaling 模式         │
│                       ├─[signaling]→ touch/SignalingModeTask        │
│                       │    └→ signaling/SignalingConnection         │
│                       │         └→ webrtc/WebRTCController          │
│                       └─[non-signaling]→ touch/NonSignalingModeTask │
│                            └→ touch/WebViewAutomationBase           │
│                                 └→ JS 注入 + 触摸模拟 + 截图上报     │
└─────────────────────────────────────────────────────────────────────┘
```

## 混淆包名 → 还原包名

| 混淆包名 | 还原包名 | 说明 |
|----------|---------|------|
| `c13.nim5.ez8.h5_proto` | `c2/` | C&C 协议层（dllpgd 通道） |
| `com.idlmlpugdw.h5_v1_refactor` | `c2/` (H5V1Refactor) | H5 协议重构工具 |
| `com.nied.lduvv` | `entry/` | APK 入口 |
| `com.nied` | `entry/` (MainActivity) | 启动 Activity |
| `IlIllIlllIllI1` | `api/` | HTTP API 客户端 |
| `IlIlllIIlI1` | `core/` + `websocket/` | 核心配置 / 初始化 |
| `IlIlIIIlIlIlll1` | `core/` | 偏好设置 / 屏幕工具 |
| `IlIIIlIlIlIII1` | `core/` | 编解码工具 |
| `IlIIlllllI1` | `core/` | 尺寸 / 维度类 |
| `lIIIIlllllIlll1` | `core/` | 加密接口 / Base64 编解码 |
| `IllIIlIIII1` | `core/` | XOR 字符串加密 |
| `llllIIIIll1` | `core/` | 字符串密文辅助 |
| `llllllIlIIIlll1` | `core/` | 设备指纹管理 |
| `lllllIllIl1` | `core/` | 日志 / 事件 / 分析 |
| `IIlllllIlll1` | `core/` | WebSocket 协议接口 |
| `IlIllll1` | `hook/` | Context / PackageManager Hook |
| `lIllIIIlIl1` | `model/` | API 请求/响应模型 |
| `IIIlIllIlI1` | `screenshot/` | 截图 / WebView 反射 |
| `IlIlIIlIII1` | `signaling/` | 信令 API / 连接管理 |
| `llIIIIlIlllIII1` | `signaling/` (WebRTCController) | WebRTC 控制器 |
| `lIllIlIll1` | `touch/` | 触摸自动化 / 任务编排 |
| `llIIllIl1` | `websocket/` | WebSocket 核心实现 |
| `lllIlIIIlI1` | `websocket/` | WebSocket 客户端基类 |
| `lIlllIIIII1` | `websocket/drafts/` | WebSocket Draft 协议 |
| `lIIlIIIIlIlII1` | `websocket/enums/` | WebSocket 枚举 |
| `lIIlllIIIlllII1` | `websocket/extensions/` | WebSocket 扩展 |
| `IllllIllllll1` | `websocket/extensions/` | permessage-deflate |
| `IlIIIIllllIlI1` | `websocket/frames/` | WebSocket 帧 |
| `lllIlIlllI1` | `websocket/handshake/` | WebSocket 握手 |
| `IIlIIllll1` | `websocket/server/` | WebSocket 服务端 |
| `IllIlIllll1` | `core/exceptions/` | WebSocket 异常 |
| `llIlIIlll1` | `websocket/` (SSLSocketFactory) | SSL 工厂 |

---

## api/ — C&C API 客户端（4 文件）

| 还原文件 | 还原类名 | 混淆类名 | 说明 |
|---------|---------|---------|------|
| `ApiClient.java` | ApiClient | `IlIllIlllIllI1.llllIIIIll1` | C&C API 静态方法集（getToken, getTask, uploadLogs 等） |
| `HttpClient.java` | HttpClient | `IlIllIlllIllI1.lIIIIlllllIlll1` | HTTP 传输层，XOR+Base64 加密，15s 超时 |
| `ApiException.java` | ApiException | `IIlIllIIll1.llllIIIIll1` | API 响应异常（code + message） |
| `NetworkException.java` | NetworkException | `IlIllIlllIllI1.lIIIIlllllIlll1.llllIIIIll1` | 网络 I/O 异常包装 |

## c2/ — C&C 协议层 dllpgd 通道（18 文件）

| 还原文件 | 还原类名 | 混淆类名 | 说明 |
|---------|---------|---------|------|
| `Atom.java` | Atom | `c13.nim5.ez8.h5_proto.Atom` | 设备指纹载荷，每个 C&C 请求必带 |
| `DeviceInfo.java` | DeviceInfo | `c13.nim5.ez8.h5_proto.DeviceInfo` | 设备信息（时区、语言、型号、OS 版本） |
| `CommonRequest.java` | CommonRequest | `c13.nim5.ez8.h5_proto.CommonRequest` | 通用请求包装（含 Atom） |
| `CommonResponse.java` | CommonResponse | `c13.nim5.ez8.h5_proto.CommonResponse` | 通用响应（code + message） |
| `DllpgdConfig.java` | DllpgdConfig | `c13.nim5.ez8.h5_proto.DllpgdConfig` | /getConfig 响应配置体（插件列表、会话、Hook 检测） |
| `GetConfigResponse.java` | GetConfigResponse | `c13.nim5.ez8.h5_proto.GetConfigResponse` | /getConfig 完整响应 |
| `Event.java` | Event | `c13.nim5.ez8.h5_proto.Event` | 遥测事件（时间戳 + 名称 + 描述） |
| `UpdateEventRequest.java` | UpdateEventRequest | `c13.nim5.ez8.h5_proto.UpdateEventRequest` | /updateEvent 请求（Atom + 事件列表） |
| `Log.java` | Log | `c13.nim5.ez8.h5_proto.Log` | 日志条目（级别 + 时间戳 + tag + 消息） |
| `UpdateLogRequest.java` | UpdateLogRequest | `c13.nim5.ez8.h5_proto.UpdateLogRequest` | /updateLog 请求（Atom + 日志列表） |
| `PluginInfo.java` | PluginInfo | `c13.nim5.ez8.h5_proto.PluginInfo` | 远程插件描述（URL、AES 密码、MD5、生命周期） |
| `LocalPluginInfo.java` | LocalPluginInfo | `c13.nim5.ez8.h5_proto.LocalPluginInfo` | 本地缓存插件元数据 |
| `DllpgdLiteSDK.java` | DllpgdLiteSDK | `c13.nim5.ez8.h5_proto.DllpgdLiteSDK` | dllpgd C&C 通信 SDK 入口（单例） |
| `H5Lite.java` | H5Lite | `c13.nim5.ez8.h5_proto.H5Lite` | 轻量级事件/日志上报 facade（单例） |
| `H5V1Refactor.java` | H5V1Refactor | `com/idlmlpugdw/h5_v1_refactor/H5V1Refactor` | H5 协议 v1 工具，从 JSON 解析 Atom |
| `HttpGatewayClient.java` | HttpGatewayClient | `c13.nim5.ez8.h5_proto.HttpGatewayClient` | 核心 HTTP 客户端，5 层加密管道 |
| `JsonObjectUtils.java` | JsonObjectUtils | `c13.nim5.ez8.h5_proto.JsonObjectUtils` | 反射式 JSON 序列化/反序列化 |
| `Vector2.java` | Vector2 | `c13.nim5.ez8.h5_proto.Vector2` | 2D 向量（x/y 坐标） |

## entry/ — APK 入口（2 文件）

| 还原文件 | 还原类名 | 混淆类名 | 说明 |
|---------|---------|---------|------|
| `Kucopd.java` | Kucopd | `com.nied.lduvv.Kucopd` | SDK 入口，后台线程启动恶意软件初始化 |
| `MainActivity.java` | MainActivity | `com.nied.MainActivity` | 启动 Activity，调用 `Kucopd.init(context, "n3Hza")` |

## hook/ — 包名伪装 Hook（4 文件）

| 还原文件 | 还原类名 | 混淆类名 | 说明 |
|---------|---------|---------|------|
| `HookConfig.java` | HookConfig | `IlIllll1.llllIIIIll1` | Hook 配置管理（目标包名等） |
| `ContextHookInstaller.java` | ContextHookInstaller | `IlIllll1.lIIIIlllllIlll1` | 通过反射安装 Context hook |
| `HookContextWrapper.java` | HookContextWrapper | `IlIllll1.IllIIlIIII1` | 拦截 getPackageName() / getPackageManager() |
| `PackageManagerProxy.java` | PackageManagerProxy | `IlIllll1.llllIllIl1` | PackageManager 代理，伪造包名查询 |

## model/ — API 请求/响应模型（10 文件）

| 还原文件 | 还原类名 | 混淆类名 | 说明 |
|---------|---------|---------|------|
| `DeviceAuthRequest.java` | DeviceAuthRequest | `lIllIIIlIl1.lIIIIlllllIlll1` | 设备认证请求体（15 字段指纹） |
| `DeviceFingerprint.java` | DeviceFingerprint | `lIllIIIlIl1.llllIIIIll1` | 设备指纹（phantom 通道用） |
| `TokenResponse.java` | TokenResponse | `lIllIIIlIl1.IlIlllIIlI1` | Token 认证响应 |
| `ConfigResponse.java` | ConfigResponse | `lIllIIIlIl1.lIllIlIll1` | 任务配置响应 |
| `FileVersionResponse.java` | FileVersionResponse | `lIllIIIlIl1.IllIIlIIII1` | 文件版本查询响应 |
| `FileInfoResponse.java` | FileInfoResponse | `lIllIIIlIl1.IllIIlIIII1` | 文件信息查询响应（与 FileVersionResponse 同名*） |
| `FileContentResponse.java` | FileContentResponse | `lIllIIIlIl1.lIllIlIll1` | 文件内容下载响应（与 ConfigResponse 同名*） |
| `Offer.java` | Offer | `lIllIIIlIl1.llIIIIlIlllIII1` | 广告任务配置 / C&C 任务分发 |
| `Jsonable.java` | Jsonable | `lIllIIIlIl1.IlIllll1` | JSON 序列化接口 |
| `Log.java` | Log | `c13.nim5.ez8.h5_proto.Log` | 日志条目模型 |

> \* 混淆器对不同泛型特化/重载类生成了相同短名，还原时按语义拆分为独立文件。

## core/ — 核心基础设施（39 文件）

### 主要工具类（29 文件）

| 还原文件 | 还原类名 | 混淆类名 | 说明 |
|---------|---------|---------|------|
| `AppContext.java` | AppContext | `IlIlllIIlI1.lIIIIlllllIlll1` | 全局上下文（appId, deviceId, authToken） |
| `TaskConfig.java` | TaskConfig | `IlIlllIIlI1.llllIllIl1` | SDK 运行时配置 |
| `PreferencesHelper.java` | PreferencesHelper | `IlIlIIIlIlIlll1.IIlIllIIll1` | SharedPreferences 工具 + 加密 |
| `ScreenHelper.java` | ScreenHelper | `IlIlIIIlIlIlll1.lIllIIIlIl1` | 屏幕尺寸获取 |
| `Size.java` | Size | `IlIIlllllI1.llllIIIIll1` | 宽高尺寸类 |
| `Dimension.java` | Dimension | `IlIIlllllI1.llllIIIIll1` | 不可变宽高持有者 |
| `DeviceFingerprintManager.java` | DeviceFingerprintManager | `llllllIlIIIlll1.llllIIIIll1` | 设备指纹单例管理 |
| `LogHelper.java` | LogHelper | `lllllIllIl1.IllIIlIIII1` | 日志工具类 |
| `LogUploader.java` | LogUploader | `lllllIllIl1.llllIllIl1` | C&C 日志上传器（单例） |
| `EventReporter.java` | EventReporter | `lllllIllIl1.lIIIIlllllIlll1` | C&C 事件上报器（单例） |
| `AnalyticsEventType.java` | AnalyticsEventType | `lllllIllIl1.llllIIIIll1` | 分析事件类型枚举 |
| `Base64Codec.java` | Base64Codec | `IlIIIlIlIlIII1.llllIIIIll1` | Base64 编解码 |
| `MalwareBase64Codec.java` | MalwareBase64Codec | `lIIIIlllllIlll1.llllIIIIll1` | Base64/XOR 编解码（含 Encoder/Decoder 内部类） |
| `XorStringCipher.java` | XorStringCipher | `IllIIlIIII1.llllIIIIll1` | XOR 字符串加密实现 |
| `StringCipherInterface.java` | StringCipherInterface | `lIIIIlllllIlll1.llllIllIl1` | 字符串加密接口 |
| `StringCipherLoader.java` | StringCipherLoader | `lIIIIlllllIlll1.IllIIlIIII1` | 反射加载字符串加密器 |
| `EncryptionInterface.java` | EncryptionInterface | `lIIIIlllllIlll1.lIIIIlllllIlll1` | 加密接口（单参数） |
| `CipherStringHelper.java` | CipherStringHelper | `llllIIIIll1.llllIIIIll1` | 合成字符串密文辅助 |
| `ExceptionStringHelper.java` | ExceptionStringHelper | `llllIIIIll1.lIIIIlllllIlll1` | 异常字符串构建辅助 |
| `StringCodec.java` | StringCodec | `IlIIIlIlIlIII1.llllIllIl1` | UTF-8/ASCII 编码工具 |
| `ByteBufferCopier.java` | ByteBufferCopier | `IlIIIlIlIlIII1.lIIIIlllllIlll1` | ByteBuffer 拷贝工具 |
| `ThreadPoolFactory.java` | ThreadPoolFactory | `IlIIIlIlIlIII1.IllIIlIIII1` | 守护线程工厂 |
| `ClassMarker.java` | ClassMarker | `llllIllIl1.llllIIIIll1` | 类型标注注解 |
| `RequestFilter.java` | RequestFilter | `IIIlIllIlI1.lIllIIIlIl1` | WebView 请求拦截 / URL 过滤 |
| `ScreenshotCallback.java` | ScreenshotCallback | `IIIlIllIlI1.IlIlllIIlI1` | 截图回调接口 |
| `WebViewLifecycleCallback.java` | WebViewLifecycleCallback | `IIIlIllIlI1.llllllIlIIIlll1` | WebView 生命周期回调 |
| `Protocol.java` | Protocol | `IIlllllIlll1.llllIIIIll1` | WebSocket 协议/子协议接口 |
| `ProtocolImpl.java` | ProtocolImpl | `IIlllllIlll1.lIIIIlllllIlll1` | WebSocket 协议实现 |
| `WebSocketStatusError.java` | WebSocketStatusError | `IIlIllIIll1.llllIIIIll1` | WebSocket 状态异常 |

### 异常类 core/exceptions/（10 文件）

| 还原文件 | 还原类名 | 混淆类名 | 说明 |
|---------|---------|---------|------|
| `WebSocketException.java` | WebSocketException | `IllIlIllll1.llllIllIl1` | WebSocket 基础异常 |
| `WebSocketCodeException.java` | WebSocketCodeException | `IllIlIllll1.llllIIIIll1` | WebSocket 状态码异常 |
| `WebSocketCloseException.java` | WebSocketCloseException | `IllIlIllll1.llllllIlIIIlll1` | WebSocket 关闭异常 |
| `WebSocketHandshakeException.java` | WebSocketHandshakeException | `IllIlIllll1.IlIlllIIlI1` | WebSocket 握手失败 |
| `WebSocketProtocolException.java` | WebSocketProtocolException | `IllIlIllll1.IlIllIlllIllI1` | WebSocket 协议违规 |
| `WebSocketRuntimeException.java` | WebSocketRuntimeException | `IllIlIllll1.IlIlIIlIII1` | WebSocket 运行时异常 |
| `WebSocketMinimalException.java` | WebSocketMinimalException | `IllIlIllll1.lIllIIIlIl1` | WebSocket 轻量信号异常 |
| `WebSocketStatusException.java` | WebSocketStatusException | `IllIlIllll1.lIIIIlllllIlll1` | HTTP 状态码异常 |
| `ChannelIOException.java` | ChannelIOException | `IllIlIllll1.IIlIllIIll1` | Channel I/O 异常 |
| `EncodingException.java` | EncodingException | `IllIlIllll1.IllIIlIIII1` | 编码异常 |

## screenshot/ — 截图系统（9 文件）

| 还原文件 | 还原类名 | 混淆类名 | 说明 |
|---------|---------|---------|------|
| `Screenshotter.java` | Screenshotter | `IIIlIllIlI1.IllIIlIIII1` | VirtualDisplay + ImageReader 截图 |
| `ScreenshotListener.java` | ScreenshotListener | `IIIlIllIlI1.IlIlllIIlI1` | 截图 Bitmap 回调 |
| `ScreenshotHandler.java` | ScreenshotHandler | `IIIlIllIlI1.lIIIIlllllIlll1` | 截图线程/Handler |
| `WebViewReflectionHelper.java` | WebViewReflectionHelper | `IIIlIllIlI1.IlIlIIlIII1` | 反射获取 Chromium WebContents |
| `ReflectionUtils.java` | ReflectionUtils | `IIIlIllIlI1.llllIIIIll1` | 反射工具类 |
| `RequestInterceptor.java` | RequestInterceptor | `IIIlIllIlI1.lIllIIIlIl1` | Chromium 内部请求拦截 |
| `RequestInterceptorCallback.java` | RequestInterceptorCallback | `IIIlIllIlI1.llllllIlIIIlll1` | 拦截请求回调 |
| `ChromiumInterface.java` | ChromiumInterface | `IIIlIllIlI1.IlIllIlllIllI1` | Chromium 标记接口 |
| `InvalidArgumentException.java` | InvalidArgumentException | `IIIlIllIlI1.llllIllIl1` | 自定义参数异常 |

## signaling/ — 信令协议（24 文件）

### 客户端实现（4 文件，有混淆名）

| 还原文件 | 还原类名 | 混淆类名 | 说明 |
|---------|---------|---------|------|
| `SignalingHttpClient.java` | SignalingHttpClient | `IlIlIIlIII1.llllIIIIll1` | 信令 REST API HTTP 客户端 |
| `SignalingApiClient.java` | SignalingApiClient | `IlIlIIlIII1.llllIllIl1` | 高层信令 API 客户端 |
| `SignalingConnection.java` | SignalingConnection | `IlIlIIlIII1.lIIIIlllllIlll1` | WebRTC 信令连接管理 |
| `SignalingWebSocketManager.java` | SignalingWebSocketManager | `IlIlIIlIII1.IllIIlIIII1` | 信令 WebSocket 连接管理器 |

### WebRTC 控制器（1 文件）

| 还原文件 | 还原类名 | 混淆类名 | 说明 |
|---------|---------|---------|------|
| `WebRTCController.java` | WebRTCController | `llIIIIlIlllIII1.IllIIlIIII1` | WebRTC PeerConnection 生命周期 + DataChannel 远控 |

### 协议消息类（19 文件，无混淆名 — 清晰设计的 DTO）

| 还原文件 | 类名 | 说明 |
|---------|------|------|
| `SDPOffer.java` | SDPOffer | SDP Offer |
| `SDPAnswer.java` | SDPAnswer | SDP Answer |
| `ICECandidate.java` | ICECandidate | ICE 候选 |
| `ControlCommand.java` | ControlCommand | 远控命令（screenshot/home/back/recents） |
| `ClickEvent.java` | ClickEvent | 点击事件 |
| `ScrollEvent.java` | ScrollEvent | 滚动事件 |
| `TextInput.java` | TextInput | 文本输入事件 |
| `Ping.java` / `Pong.java` | Ping / Pong | 心跳 |
| `Done.java` | Done | 会话结束 |
| `Error.java` | Error | 错误消息 |
| `SignalingRequest.java` | SignalingRequest | 出站信令请求 |
| `SignalingResponse.java` | SignalingResponse | 入站信令响应 |
| `ConnectionStatus.java` | ConnectionStatus | 连接状态枚举 |
| `JsonSerializable.java` | JsonSerializable | JSON 序列化接口 |
| `CheckSignalingPluginStartRequest.java` | CheckSignalingPluginStartRequest | 检查插件启动请求 |
| `CheckSignalingPluginStartResponse.java` | CheckSignalingPluginStartResponse | 检查插件启动响应 |
| `UpdateSignalingStatusRequest.java` | UpdateSignalingStatusRequest | 更新信令状态请求 |
| `UpdateSignalingStatusResponse.java` | UpdateSignalingStatusResponse | 更新信令状态响应 |

## touch/ — 触摸自动化 / 任务编排（9 文件）

| 还原文件 | 还原类名 | 混淆类名 | 说明 |
|---------|---------|---------|------|
| `TaskOrchestrator.java` | TaskOrchestrator | `lIllIlIll1.IlIlllIIlI1` | 中央任务编排器（认证→模式判断→JS 下载→WebView 创建→任务分发） |
| `WebViewAutomationBase.java` | WebViewAutomationBase | `lIllIlIll1.llllIIIIll1` | WebView 自动化基类（JS 桥注入、请求拦截、触摸模拟、遥测上传） |
| `WebViewBridge.java` | WebViewBridge | `lIllIlIll1.IlIllIlllIllI1` | JS 桥接口（暴露 native 方法给注入的 JS） |
| `SignalingModeTask.java` | SignalingModeTask | `lIllIlIll1.IllIIlIIII1` | 信令模式任务（WebRTC 远控） |
| `NonSignalingModeTask.java` | NonSignalingModeTask | `lIllIlIll1.llllIllIl1` | 非信令模式任务（自主广告欺诈循环） |
| `SwipeSimulator.java` | SwipeSimulator | `lIllIlIll1.lIIIIlllllIlll1` | 贝塞尔曲线滑动模拟 |
| `SDKInitializer.java` | SDKInitializer | `com.nied.lduvv.Kucopd` | SDK 初始化入口（touch 包副本） |
| `MotionHelper.java` | MotionHelper | *(JADX 路径保留)* | 合成 MotionEvent 注入 WebView |
| `RandomHelper.java` | RandomHelper | *(无)* | 随机值生成工具 |

> **未还原的类**：`llllIllIl1`（WebViewBootstrap）— SDKInitializer 中 `new llllIllIl1().llllIIIIll1(context, config, null)` 的目标类。功能为初始化 AppContext 后委托给 TaskOrchestrator，是薄包装层，未单独建文件。`entry/Kucopd.java` 中有编译 stub。

## webrtc/ — WebRTC 实现（4 文件）

| 还原文件 | 还原类名 | 混淆类名 | 说明 |
|---------|---------|---------|------|
| `WebRTCController.java` | WebRTCController | *(无)* | PeerConnection + 视频流 + 远控主控制器 |
| `VirtualDisplayCapturer.java` | VirtualDisplayCapturer | *(无)* | VirtualDisplay + ImageReader 帧捕获 |
| `BitmapFrameCapturer.java` | BitmapFrameCapturer | *(无)* | WebView.draw() 回退帧捕获 |
| `SafeVideoDecoderFactory.java` | SafeVideoDecoderFactory | *(无)* | 硬件解码器兼容性黑名单包装 |

## websocket/ — WebSocket 库（55 文件）

### 核心（20 文件）

| 还原文件 | 还原类名 | 混淆类名 | 说明 |
|---------|---------|---------|------|
| `WebSocket.java` | WebSocket | `llIIllIl1.IlIllIlllIllI1` | WebSocket 主接口 |
| `WebSocketImpl.java` | WebSocketImpl | `llIIllIl1.lIllIIIlIl1` | 核心实现（~700 行） |
| `WebSocketAdapter.java` | WebSocketAdapter | `llIIllIl1.llllllIlIIIlll1` | Listener 默认空实现基类 |
| `WebSocketListener.java` | WebSocketListener | `llIIllIl1.IIlIllIIll1` | 事件监听接口 |
| `WebSocketFactory.java` | WebSocketFactory | `llIIllIl1.IlIlIIlIII1` | 连接工厂接口 |
| `ServerChannelFactory.java` | ServerChannelFactory | `llIIllIl1.IlIllll1` | SocketChannel → ByteChannel 包装 |
| `AbstractWebSocket.java` | AbstractWebSocket | `llIIllIl1.llllIIIIll1` | 连接管理抽象基类 |
| `WebSocketClientBase.java` | WebSocketClientBase | `lllIlIIIlI1.lIIIIlllllIlll1` | 客户端基类（代理/SSL/重连，~695 行） |
| `WebSocketClientConfig.java` | WebSocketClientConfig | `IlIlllIIlI1.llllIllIl1` | 恶意软件主入口类（初始化所有子系统） |
| `WebSocketClientConnector.java` | WebSocketClientConnector | `IlIlllIIlI1.lIIIIlllllIlll1` | 初始连接 + 设备注册 |
| `WebSocketClient.java` | WebSocketClient | `IlIlllIIlI1.IllIIlIIII1` | 字符串解密（XOR 单例） |
| `WebSocketDraft.java` | WebSocketDraft | `IlIlllIIlI1.llllIIIIll1` | Draft 实现（核心配置包） |
| `DnsResolver.java` | DnsResolver | `lllIlIIIlI1.llllIIIIll1` | DNS 解析接口 |
| `ByteBufferUtils.java` | ByteBufferUtils | `llIIllIl1.IlIlllIIlI1` | ByteBuffer 工具 |
| `SSLSocketChannel.java` | SSLSocketChannel | `llIIllIl1.llllIllIl1` | 第一代 SSLEngine 包装 |
| `SSLSocketChannel2.java` | SSLSocketChannel2 | `llIIllIl1.IllIIlIIII1` | 改进版 SSLEngine 包装 |
| `SSLSocketFactory.java` | SSLSocketFactory | `llIlIIlll1.llllIIIIll1` | SSLEngine 创建接口 |
| `DeprecatedSSLChannel.java` | DeprecatedSSLChannel | `llIIllIl1.lIIIIlllllIlll1` | 废弃的阻塞式 SSL 包装 |
| `WrappedByteChannel.java` | WrappedByteChannel | `llIIllIl1.lllllIllIl1` | SSL 感知 ByteChannel 接口 |

### drafts/（2 文件）

| 还原文件 | 还原类名 | 混淆类名 | 说明 |
|---------|---------|---------|------|
| `Draft.java` | Draft | `lIlllIIIII1.llllIIIIll1` | WebSocket 协议 Draft 抽象基类 |
| `DraftLegacy.java` | DraftLegacy | `lIlllIIIII1.lIIIIlllllIlll1` | RFC 6455 Draft 实现 |

### enums/（5 文件）

| 还原文件 | 还原类名 | 混淆类名 | 说明 |
|---------|---------|---------|------|
| `ConnectionRole.java` | ConnectionRole | `lIIlIIIIlIlII1.IlIlllIIlI1` | CLIENT / SERVER |
| `ConnectionState.java` | ConnectionState | `lIIlIIIIlIlII1.IllIIlIIII1` | 连接生命周期状态 |
| `FrameType.java` | FrameType | `lIIlIIIIlIlII1.llllIllIl1` | RFC 6455 帧 opcode |
| `MessageDirection.java` | MessageDirection | `lIIlIIIIlIlII1.llllIIIIll1` | 握手消息方向 |
| `ValidationState.java` | ValidationState | `lIIlIIIIlIlII1.lIIIIlllllIlll1` | Draft/握手验证结果 |

### extensions/（5 文件）

| 还原文件 | 还原类名 | 混淆类名 | 说明 |
|---------|---------|---------|------|
| `WebSocketExtension.java` | WebSocketExtension | `lIIlllIIIlllII1.IllIIlIIII1` | 扩展接口（RFC 6455 §9） |
| `BaseExtension.java` | BaseExtension | `lIIlllIIIlllII1.lIIIIlllllIlll1` | 默认空扩展实现 |
| `DraftExtension.java` | DraftExtension | `lIIlllIIIlllII1.llllIIIIll1` | RSV 位验证扩展 |
| `MediaType.java` | MediaType | `lIIlllIIIlllII1.llllIllIl1` | 扩展参数解析 |
| `PerMessageDeflateExtension.java` | PerMessageDeflateExtension | `IllllIllllll1.llllIIIIll1` | permessage-deflate（RFC 7692） |

### frames/（10 文件）

| 还原文件 | 还原类名 | 混淆类名 | 说明 |
|---------|---------|---------|------|
| `WebSocketFrame.java` | WebSocketFrame | `IlIIIIllllIlI1.IlIllIlllIllI1` | 帧接口 |
| `BaseFrame.java` | BaseFrame | `IlIIIIllllIlI1.llllllIlIIIlll1` | 帧抽象基类 |
| `DataFrame.java` | DataFrame | `IlIIIIllllIlI1.IlIlllIIlI1` | 数据帧基类 |
| `ControlFrame.java` | ControlFrame | `IlIIIIllllIlI1.IllIIlIIII1` | 控制帧基类 |
| `TextFrame.java` | TextFrame | `IlIIIIllllIlI1.IIlIllIIll1` | TEXT 帧 (0x1) |
| `BinaryFrame.java` | BinaryFrame | `IlIIIIllllIlI1.llllIIIIll1` | BINARY 帧 (0x2) |
| `ContinuousFrame.java` | ContinuousFrame | `IlIIIIllllIlI1.llllIllIl1` | CONTINUATION 帧 (0x0) |
| `CloseFrame.java` | CloseFrame | `IlIIIIllllIlI1.lIIIIlllllIlll1` | CLOSE 帧 (0x8) |
| `PingFrame.java` | PingFrame | `IlIIIIllllIlI1.IlIlIIlIII1` | PING 帧 (0x9) |
| `PongFrame.java` | PongFrame | `IlIIIIllllIlI1.lIllIIIlIl1` | PONG 帧 (0xA) |

### handshake/（9 文件）

| 还原文件 | 还原类名 | 混淆类名 | 说明 |
|---------|---------|---------|------|
| `Handshake.java` | Handshake | `lllIlIlllI1.IlIllIlllIllI1` | 握手基础接口 |
| `HandshakeBuilder.java` | HandshakeBuilder | `lllIlIlllI1.llllIllIl1` | 可变握手构建器 |
| `HandshakeImpl.java` | HandshakeImpl | `lllIlIlllI1.llllllIlIIIlll1` | 握手基础实现（TreeMap） |
| `ClientHandshake.java` | ClientHandshake | `lllIlIlllI1.llllIIIIll1` | 客户端握手（含 URI） |
| `ClientHandshakeBuilder.java` | ClientHandshakeBuilder | `lllIlIlllI1.lIIIIlllllIlll1` | 客户端握手构建器 |
| `ClientHandshakeImpl.java` | ClientHandshakeImpl | `lllIlIlllI1.IllIIlIIII1` | 客户端握手实现 |
| `ServerHandshake.java` | ServerHandshake | `lllIlIlllI1.IlIlIIlIII1` | 服务端握手（含状态码） |
| `ServerHandshakeBuilder.java` | ServerHandshakeBuilder | `lllIlIlllI1.lIllIIIlIl1` | 服务端握手构建器 |
| `ServerHandshakeImpl.java` | ServerHandshakeImpl | `lllIlIlllI1.IlIlllIIlI1` | 服务端握手实现 |

### server/（5 文件）

| 还原文件 | 还原类名 | 混淆类名 | 说明 |
|---------|---------|---------|------|
| `WebSocketServer.java` | WebSocketServer | `IIlIIllll1.IlIlllIIlI1` | 抽象 WebSocket 服务端 |
| `PlainChannelFactory.java` | PlainChannelFactory | `IIlIIllll1.llllIllIl1` | 明文 Channel 工厂 |
| `SSLChannelFactory.java` | SSLChannelFactory | `IIlIIllll1.lIIIIlllllIlll1` | SSL Channel 工厂 |
| `SSLChannelFactoryWithCrypto.java` | SSLChannelFactoryWithCrypto | `IIlIIllll1.llllIIIIll1` | SSL 工厂 + 密码套件 |
| `SSLChannelFactoryWithParams.java` | SSLChannelFactoryWithParams | `IIlIIllll1.IllIIlIIII1` | SSL 工厂 + SSLParameters |

---

## 统计

| 包 | 文件数 | 有混淆名 | 无混淆名 |
|----|--------|---------|---------|
| api | 4 | 4 | 0 |
| c2 | 18 | 18 | 0 |
| core | 29 | 29 | 0 |
| core/exceptions | 10 | 10 | 0 |
| entry | 2 | 2 | 0 |
| hook | 4 | 4 | 0 |
| model | 10 | 10 | 0 |
| screenshot | 9 | 9 | 0 |
| signaling | 24 | 5 | 19 |
| touch | 9 | 7 | 2 |
| webrtc | 4 | 0 | 4 |
| websocket | 55 | 55 | 0 |
| **合计** | **178** | **153** | **25** |

> 25 个无混淆名的文件主要是：signaling 协议消息 DTO（19 个，清晰设计无需混淆还原）、webrtc 实现（4 个）、touch 工具类（2 个）。
