# APK 逆向分析报告：testBQ_nv_0915.apk

## 一、基本信息

| 属性 | 值 |
|---|---|
| **文件名** | testBQ_nv_0915.apk |
| **文件大小** | 5,071,571 字节 (4.8 MB) |
| **包名** | `com.android.wallpaper` (伪装成壁纸应用) |
| **主Activity** | `com.nied.MainActivity` |
| **SDK版本** | minSdk=24, targetSdk=34 |
| **版本号** | versionCode=34, versionName="14" |
| **内部SDK名称** | **DllpgdLiteSDK** / **H5Lite** |
| **内部版本** | 2.0.1 (release) |
| **DEX文件** | 单一 classes.dex (9.4MB, 2905个类) |
| **权限** | `INTERNET`, `ACCESS_NETWORK_STATE` |
| **签名** | APK Signing Block |

## 二、性质判定

**广告欺诈 / 点击欺诈 SDK (Ad Fraud / Click Fraud)**

该 APK 伪装为系统壁纸应用，内嵌一个名为 DllpgdLiteSDK 的广告欺诈框架。核心行为是在隐藏的 WebView 中加载广告页面，通过程序化生成高仿真触摸手势（点击、滑动）实施广告点击欺诈，同时支持通过 WebRTC 信令进行实时远程控制。

## 三、架构总览

```
MainActivity.onCreate()
    └── Kucopd.init(context, "n3Hza")       ← SDK入口，"n3Hza"为渠道标识
         └── new Thread().start()            ← 新线程启动，避免阻塞UI
              ├── 1. 进程检查 — 验证当前进程名与包名一致
              ├── 2. 冷却检查 — 6小时内不重复执行
              ├── 3. 设备指纹收集 (Atom)
              ├── 4. 初始化日志/事件上报系统
              ├── 5. 连接C&C服务器获取配置 (DllpgdConfig)
              ├── 6. 创建隐藏WebView (主线程)
              ├── 7. 网络连接检查
              ├── 8. 建立信令通道 (WebSocket/HTTP轮询)
              ├── 9. 检查信令插件启动 (CheckSignalingPluginStart)
              ├── 10. 注入加密JavaScript + 加载目标URL
              ├── 11. 模拟点击/滑动手势
              └── 12. Timer定时器 — 每30分钟重复任务
```

## 四、核心恶意功能详细分析

> 详见下方各节的深入分析

### 4.1 触摸事件伪造

详见 [第五节：触摸事件伪造详细分析](#五触摸事件伪造详细分析)

### 4.2 WebView 自动化

详见 [第六节：WebView 自动化详细分析](#六webview-自动化详细分析)

### 4.3 C&C 通信协议

详见 [第七节：C&C 通信协议详细分析](#七cc-通信协议详细分析)

### 4.4 WebRTC 信令 — 实时远程控制

详见 [第八节：WebRTC 信令详细分析](#八webrtc-信令--实时远程控制详细分析)

## 五、触摸事件伪造详细分析

### 5.1 核心类概览

触摸事件伪造系统由两个核心类组成：

| 类 | 路径 | 职责 |
|---|---|---|
| `MotionHelper` | `com/nied/MotionHelper.java` | 构造并分发伪造的 MotionEvent |
| `RandomHelper` | `com/nied/RandomHelper.java` | 提供高精度随机数生成 |

### 5.2 点击事件伪造 — `clickViewExact(Point)`

**核心方法** (`MotionHelper.java:45-70`)

```java
public String clickViewExact(Point point) {
    long currentTimeMillis = System.currentTimeMillis();
    int duration = RandomHelper.getRandomInt(60, 160);  // 按下持续时间 60-160ms
    long upTime = currentTimeMillis + duration;

    // 坐标微扰 — 模拟人手抖动
    if (RandomHelper.getRandomFloat() < 0.75d) {
        // 75% 概率：小偏移 ±1px
        offsetX = RandomHelper.getRandomInt(-1, 1);
        offsetY = RandomHelper.getRandomInt(-1, 1);
    } else {
        // 25% 概率：大偏移 ±3px
        offsetX = RandomHelper.getRandomInt(-2, 3);
        offsetY = RandomHelper.getRandomInt(-2, 3);
    }

    // DOWN 和 UP 的坐标分别加上亚像素随机浮点
    fArr[0] = {point.x + RandomHelper.getRandomFloat(), point.y + RandomHelper.getRandomFloat()};
    fArr[1] = {point.x + offsetX + RandomHelper.getRandomFloat(), point.y + offsetY + RandomHelper.getRandomFloat()};

    // 构造 MotionEvent
    MotionEvent downEvent = MotionEvent.obtain(downTime, downTime, ACTION_DOWN, ...);
    MotionEvent upEvent = MotionEvent.obtain(downTime, upTime, ACTION_UP, ...);

    // 直接通过 View.dispatchTouchEvent() 分发，绕过 InputManager
    this.mView.dispatchTouchEvent(downEvent);
    this.mView.dispatchTouchEvent(upEvent);
}
```

**关键设计意图：**
- **坐标微扰**：75%/25% 的双层偏移分布，模拟真实人手点击时的微小抖动
- **亚像素精度**：每个坐标额外加 `getRandomFloat()` (0.0~1.0)，产生浮点坐标
- **时间随机化**：按下持续时间 60-160ms，符合真人点击的时间分布
- **绕过 isTrusted**：通过 `View.dispatchTouchEvent()` 而非 `Instrumentation.sendPointerSync()` 分发事件

### 5.3 另一种点击 — `clickViewByPoint(float, float)`

(`MotionHelper.java:72-77`, `329-345`)

此方法生成更复杂的多事件点击序列：

```
60% 概率 → 2个事件 (DOWN + UP)
40% 概率 → 3~4个事件 (DOWN + MOVE... + UP)
```

多事件模式中间插入 `ACTION_MOVE` 事件，模拟手指在屏幕上微移的自然行为。

### 5.4 滚动手势伪造 — 贝塞尔曲线路径

(`MotionHelper.java:347-375`)

滚动轨迹通过**二次贝塞尔曲线**生成，而非简单的直线插值：

```java
// 计算转折点（控制点），引入随机性
turningPoint.x = start.x + ((end.x - start.x) * randomInt(2500, 3500)) / 10000.0f;
turningPoint.y = start.y + ((end.y - start.y) * randomInt(5000, 7500)) / 10000.0f;

// 使用 Android Path API 构造贝塞尔曲线
Path path = new Path();
path.moveTo(start.x, start.y);
path.quadTo(turningPoint.x, turningPoint.y, end.x, end.y);  // 二次贝塞尔

// 通过 PathMeasure 均匀采样曲线上的点
PathMeasure pathMeasure = new PathMeasure(path, false);
float length = pathMeasure.getLength();
for (int i = 0; i < numPoints; i++) {
    pathMeasure.getMatrix((i * length) / (numPoints - 1), matrix, 1);
    // 每个采样点再加 1-10px 随机偏移
    points[i] = {matrix.x + randomInt(1, 10), matrix.y + randomInt(1, 10)};
}
```

**贝塞尔控制点参数：**
- X轴控制点位置：总距离的 25%~35% 处
- Y轴控制点位置：总距离的 50%~75% 处
- 每个采样点额外加 1~10px 随机噪声

### 5.5 滚动行为模式

| 方法 | 行为 | 方向偏好 |
|---|---|---|
| `randomScroll(n, listener)` | 滚动 n 次 | 85% 向上, 15% 向下 |
| `randomScrollWithDur(sec, listener)` | 滚动指定秒数 | 90% 向上, 10% 向下 |
| `scroll2Bottom(listener)` | 滚动到页面底部 | 90% 向上, 10% 回退 |
| `scroll2Y(targetY, listener)` | 滚动到指定Y坐标 | 智能方向判断 |
| `scroll2BottomOrContentChange(listener)` | 滚动到底部或内容变化时停止 | 95% 向上, 5% 回退 |

**关键设计**：所有滚动方法都混入少量反方向滚动（回退），模拟真人浏览时的"来回看"行为。

### 5.6 触摸压力模拟

(`MotionHelper.java:417-429`)

压力值采用**四段分布**模型：

```
┌─────────────────────────────────────────────────┐
│  90% → [0.15, 0.35]   (正常轻触)                │
│  ├─ 剩余10%中:                                  │
│  │  80% → [0.10, 0.15]  (极轻触摸)              │
│  │  或 [0.35, 0.50]     (稍重触摸)              │
│  └─ 20% → [0.50, 1.00]  (重按)                  │
└─────────────────────────────────────────────────┘
```

精度高达 10^14 ~ 10^17 位小数（由 `RandomHelper.getRandomDouble()` 实现），使得压力值几乎不可能重复。

### 5.7 PointerCoords 九轴模拟

(`MotionHelper.java:431-445`)

每个触摸点设置 9 个轴值，完整模拟真实触摸屏传感器数据：

| 轴 (Axis) | 索引 | 含义 | 值来源 |
|---|---|---|---|
| `AXIS_X` | 0 | X坐标 | 目标坐标 + 随机偏移 |
| `AXIS_Y` | 1 | Y坐标 | 目标坐标 + 随机偏移 |
| `AXIS_PRESSURE` | 2 | 压力 | 四段分布随机值 |
| `AXIS_SIZE` | 3 | 触摸面积 | 由压力值派生 |
| `AXIS_TOUCH_MAJOR` | 4 | 触摸椭圆长轴 | 由面积派生 |
| `AXIS_TOUCH_MINOR` | 5 | 触摸椭圆短轴 | 由面积派生 |
| `AXIS_TOOL_MAJOR` | 6 | 工具椭圆长轴 | 等于 TOUCH_MAJOR |
| `AXIS_TOOL_MINOR` | 7 | 工具椭圆短轴 | 等于 TOUCH_MINOR |
| `AXIS_ORIENTATION` | 8 | 方向角 | 固定为 0.0 |

压力、面积、椭圆轴之间的派生关系 (`getPointerCoordsValue`, 行 447-485)：
- `size = pressure / (touchSize ± random)` — 面积与压力成正比
- `touchMajor` 和 `touchMinor` 由面积进一步派生
- 接触尾部最后几个点 (由 `randomInt3` 控制) 压力会微减，模拟手指抬起过程

### 5.8 时间戳模拟

(`MotionHelper.java:487-532`)

**滚动事件时间戳** (含加速/减速):
```
70% 概率 → 间隔 10-40ms (快速段)
30% 概率 → 间隔 50-100ms (慢速段)
```

支持加速度模式 (`boolean z` 参数)：
- 开始时间间隔较短（加速阶段）
- 结束时最后一个事件间隔 5-50ms（减速阶段）

**点击事件时间戳**：
```
2事件: DOWN=t, UP=t+[40,150]ms
3事件: DOWN=t, MOVE=t+[40,150]ms, UP=MOVE+[8,10]ms
4事件: DOWN=t, MOVE1=t+[40,150]ms, MOVE2..UP间隔各[5,50]ms
```

### 5.9 事件分发机制

(`MotionHelper.java:259-292`)

```java
private void sendMotionEvent(View view, MotionEvent[] events) {
    // 根据 View 在窗口中的位置选择 Handler
    int[] location = new int[2];
    view.getLocationInWindow(location);
    Handler handler = (location[0] == 0 || location[1] == 0)
        ? this.mHandler       // 使用主线程 Handler (View 不可见时)
        : view.getHandler();  // 使用 View 自己的 Handler

    for (MotionEvent event : events) {
        // 按事件时间戳差值延迟分发，模拟真实时序
        handler.postDelayed(() -> {
            view.dispatchTouchEvent(event);
        }, event.getEventTime() - event.getDownTime());
    }
}
```

**关键细节：**
- 设备ID固定为 `4`（`getEventDeviceId()` 返回 4）
- 输入源设为 `SOURCE_TOUCHSCREEN` (0x1002)
- `toolType` 设为 `1` (TOOL_TYPE_FINGER)
- 当 View 不在窗口可见区域时（位置为 0,0），使用主线程 Handler 而非 View Handler
- 事件通过 `dispatchTouchEvent()` 直接分发到 WebView，不经过系统 InputManager

### 5.10 反检测总结

| 维度 | 技术 | 效果 |
|---|---|---|
| **坐标** | 双层偏移分布 + 亚像素浮点 | 每次点击坐标不完全相同 |
| **轨迹** | 贝塞尔曲线 + 路径采样噪声 | 滚动路径非线性，类似人手 |
| **时间** | 多段随机分布 + 加速/减速模型 | 时间间隔符合人类操作节奏 |
| **压力** | 四段概率分布 + 10^17精度 | 压力值几乎不可能重复 |
| **传感器** | 9轴完整模拟 | 通过触摸屏传感器完整性检查 |
| **行为** | 混入反方向滚动 | 模拟真人"来回浏览"习惯 |
| **分发** | `dispatchTouchEvent()` | 绕过系统级事件注入检测 |

## 六、WebView 自动化详细分析

### 6.1 系统架构

WebView 自动化系统位于混淆包 `lIllIlIll1` 中，由以下组件构成：

| 文件 | 功能角色 |
|------|---------|
| `llllIIIIll1.java` | 抽象基类 — WebView 引擎核心，包含 WebSettings、JS Bridge、WebViewClient/WebChromeClient、触摸模拟、反取证清理 |
| `IlIllIlllIllI1.java` | JS Bridge 接口定义 (`@JavascriptInterface` 方法声明) |
| `IlIlllIIlI1.java` | 任务编排器 — 从 C2 获取任务配置、创建 WebView 实例、分发信令/非信令任务 |
| `IllIIlIIII1.java` | 信令模式任务实现 (extends `llllIIIIll1`)，支持 `UpdateSignalingStatus` 上报 |
| `llllIllIl1.java` | 非信令模式任务实现 (extends `llllIIIIll1`)，带屏幕截图和回调完成机制 |
| `lIIIIlllllIlll1.java` | 滑动/滚动触摸模拟器 — 通过贝塞尔曲线路径 + `AccelerateDecelerateInterpolator` 模拟人类滑动 |

### 6.2 WebView 配置 — 全权限开放

**文件**: `lIllIlIll1/llllIIIIll1.java:498-513`

```java
settings.setMixedContentMode(0);                    // MIXED_CONTENT_ALWAYS_ALLOW
settings.setJavaScriptEnabled(true);                 // 启用 JavaScript
settings.setCacheMode(-1);                           // LOAD_DEFAULT
settings.setMediaPlaybackRequiresUserGesture(false); // 无需用户手势即可自动播放
settings.setDomStorageEnabled(true);                 // 启用 DOM 存储
settings.setUseWideViewPort(true);                   // 宽视口
settings.setLoadWithOverviewMode(true);              // 概览模式
settings.setAllowFileAccess(true);                   // 允许文件访问
settings.setAllowContentAccess(true);                // 允许内容访问
settings.setAllowFileAccessFromFileURLs(true);       // 允许 file:// 访问本地文件
settings.setAllowUniversalAccessFromFileURLs(true);  // 允许 file:// 跨域访问
settings.setMediaPlaybackRequiresUserGesture(false); // 重复设置确保自动播放
```

**安全隐患**：
- `MIXED_CONTENT_ALWAYS_ALLOW`：完全放开混合内容限制
- `AllowFileAccessFromFileURLs` + `AllowUniversalAccessFromFileURLs`：在 Android 4.1+ 默认为 `false`，此处显式开启，赋予 file:// scheme 完整跨域能力
- `setLayerType(2, null)` (行 522)：启用硬件加速渲染

### 6.3 JavaScript 注入机制

#### 6.3.1 JS Bridge 注册与注销

**注册** (`llllIIIIll1.java:521`)：
```java
this.f508llllIIIIll1.addJavascriptInterface(this, this.f502IllIIlIIII1);
```

Bridge 对象名通过 XOR 加密字符串运行时解密。JavaScript 代码通过 `window.<bridge_name>` 访问原生方法。

**注销** (清理阶段, 行 122-127)：
```java
lllliiiill1.f508llllIIIIll1.removeJavascriptInterface(lllliiiill1.f502IllIIlIIII1);
```

#### 6.3.2 动态 JS 注入

**内部类 `IlIllIlllIllI1`** (行 136-150) 在页面加载完成后通过 `evaluateJavascript` 注入控制脚本：

```java
webView.evaluateJavascript(
    XOR_DECRYPT(前缀)           // JS 函数包装开头
    + this.f509llllIllIl1       // C2 下发的动态 JS 脚本
    + XOR_DECRYPT(后缀),        // JS 闭包结束
    null);
```

注入模式为：**前缀(函数包装)** + **C2 下发的动态脚本** + **后缀(闭包结束)**。

#### 6.3.3 预编译大型 JS 脚本

字段 `f493lllIlIIIlI1` (行 52-60) 是一个数千字节的加密字节数组，通过静态初始化块解密，包含完整的页面元素定位、广告交互等自动化控制逻辑。

### 6.4 JS Bridge 接口方法 (@JavascriptInterface)

**接口**: `lIllIlIll1/IlIllIlllIllI1.java`
**实现**: `lIllIlIll1/llllIIIIll1.java`

| 方法 | 功能 | 行号 |
|------|------|------|
| `touch(float, float)` | **模拟点击** — 在指定坐标位置模拟触摸事件 | 417-424 |
| `scroll(f,f,f,f,long)` | **模拟滑动** — 贝塞尔曲线滑动，带持续时间 | 389-396 |
| `screenshot()` | **截图** — 返回 Base64 编码的 PNG 图像 | 364-387 |
| `done(String)` | **任务完成** — 通知原生端任务完成，触发清理 | 315-330 |
| `getConfig()` | 返回 C2 下发的任务配置 JSON | 332-336 |
| `setConfig(String, String)` | 更新任务配置 | 398-409 |
| `getGAID()` | 获取 Google Advertising ID | 338-342 |
| `getTime()` / `setTime(long)` | 获取/设置累计执行时间 | 344-348 / 411-415 |
| `isSignaling()` | 查询是否处于信令模式 | 350-354 |
| `updateSignalStatus(int)` | 更新信令状态 (IN_LANDING 等) | 426-430 |
| `upload_event(String)` | **事件上报** — JS 端事件加入上传队列 | 432-440 |
| `upload_log(String)` | **日志上报** — JS 端日志加入上传队列 | 442-450 |
| `back()` | 调用 `WebView.goBack()` 后退 | 294-300 |
| `debugLog(String)` | 将 JS 端日志传递到原生日志系统 | 302-307 |

**截图机制** (行 366-387)：以 PNG 格式压缩 (quality=100, 无损)，Base64 编码 (NO_WRAP) 返回给 JS 端。

### 6.5 请求拦截 / 代理行为

**内部类**: `IlIlllIIlI1 extends WebViewClient` (行 594-763)

#### 路径 A：本地资源服务 (行 561-592)

当 URL 包含特定标识时，从应用 `filesDir` 目录加载本地文件：
- `.js` → `application/javascript`
- `.css` → `text/css`
- 其他 → `application/octet-stream`
- 不存在时返回空 404 响应

#### 路径 B：代理请求 (行 602-655)

当代理模式标志位 `f504lIllIIIlIl1` 为 `true` 时激活：

1. 通过正则匹配请求路径
2. 非主框架的子资源请求被拦截
3. 通过原生 `HttpsURLConnection` 发起代理请求（超时 8000ms）
4. **响应注入**：所有符合条件的响应体末尾追加 C2 控制的 JavaScript 代码

```java
String str5 = responseBody + separator + this.f509llllIllIl1;  // 追加C2下发JS
return new WebResourceResponse(mimeType, "utf-8", new ByteArrayInputStream(str5.getBytes()));
```

### 6.6 WebChromeClient 行为

**内部类 `IllIIlIIII1 extends WebChromeClient`** (行 152-170)：

- `onConsoleMessage()` 始终返回 `true`，**静默吞没所有 console.log/error 输出**，阻止其写入 logcat
- `onProgressChanged()` 仅记录 DEBUG 级别日志

### 6.7 任务执行模型

#### 初始化链 (`IlIlllIIlI1.llllIllIl1.java:327-390`)

```
1. 设置 WebView DataDirectorySuffix (Android 9+)
2. 初始化应用上下文
3. 检查运行环境 (isMainProcess)
4. 初始化信令连接
5. 发送 CheckSignalingPluginStart 请求
6. 清理 WebView 缓存/Cookie
7. UI线程创建 WebView 实例
8. sleep(2000ms) 等待初始化
9. 创建任务编排器执行首次任务
10. Timer 每 30 分钟重复执行
```

#### 任务编排 (`lIllIlIll1/IlIlllIIlI1.java:87-109`)

```
1. 从 C2 获取认证令牌
2. 判断信令/非信令模式：
   - 信令模式 → 获取信令 JS + 信令配置 → new IllIIlIIII1(...)
   - 非信令模式 → 获取普通 JS + 普通配置 → new llllIllIl1(...)
3. UI 线程执行 lIllIIIlIl1() 初始化
```

#### 页面加载与 JS 执行时序

```
loadUrl(目标URL)
   → onPageStarted: 记录加载开始时间
   → onPageFinished: 累计加载时间 → 触发 evaluateJavascript(C2下发JS)
      → JS 通过 Bridge 调用 touch()/scroll()/screenshot()
      → JS 通过 Bridge 上报 upload_event()/upload_log()
      → JS 任务完成 → done() → 清理WebView → 链式启动下一任务
```

#### 链式执行机制

非信令模式完成回调调用 `llllIllIl1()` (行 232-245)，创建全新 WebView 实例并启动新任务，实现**任务完成后自动启动下一轮的循环机制**。

### 6.8 定期数据上报

**内部类 `llllIllIl1 extends TimerTask`** (行 214-242)：

```java
this.f506llIIIIlIlllIII1.schedule(new llllIllIl1(), 1000L, 4000L);
// 首次延迟1秒，之后每4秒执行
```

每次执行：
1. 事件队列不为空时批量上传到 C2
2. 每 8 个周期 (~32秒)，日志队列不为空时批量上传

### 6.9 反取证清理 — 全面数据销毁

**方法**: `llllIllIl1()` (行 534-551)

```java
this.f508llllIIIIll1.clearCache(true);                    // 清除所有缓存(含磁盘)
this.f508llllIIIIll1.clearHistory();                      // 清除浏览历史
this.f508llllIIIIll1.clearFormData();                     // 清除表单数据
this.f508llllIIIIll1.clearSslPreferences();               // 清除SSL偏好
CookieManager.getInstance().removeSessionCookies(null);   // 删除会话Cookie
CookieManager.getInstance().removeAllCookies(null);       // 删除所有Cookie
WebViewDatabase.getInstance(...).clearHttpAuthUsernamePassword(); // 清除HTTP认证
WebStorage.getInstance().deleteAllData();                  // 清除所有Web存储
```

**触发时机**：每次新任务开始前以 **92% 的概率**执行 (行 526-528)。覆盖了 WebView 所有持久化存储，使取证分析几乎无法恢复任何访问痕迹。

### 6.10 click_test.html — 调试验证页

**文件**: `assets/click_test.html`

开发/测试用页面，用于验证触摸模拟效果：

```javascript
document.addEventListener('click', function (event) {
    console.log("Click position:");
    console.log("  clientX/clientY:", event.clientX, event.clientY);
    console.log({ isTrusted: event.isTrusted, ... });
    if (window.AndroidInterface) {
        window.AndroidInterface.onClick(event.clientX, event.clientY);
    }
});
```

特别关注 `event.isTrusted` 属性，表明开发者重点验证模拟事件能否通过 `isTrusted` 检测。

## 七、C&C 通信协议详细分析

*（待补充详细分析）*

## 八、WebRTC 信令 — 实时远程控制详细分析

*（待补充详细分析）*

## 九、反检测手段

| 手段 | 实现 |
|---|---|
| **字符串全加密** | 所有字符串使用 XOR 密码加密（8字节密钥），运行时解密 |
| **AES-256-CBC** | 网络通信使用 AES 加密 + GZIP 压缩 |
| **包名伪装** | 使用 `com.android.wallpaper` 伪装成系统壁纸应用 |
| **类名混淆** | 使用 `IlIIlllllI1`/`llllIIIIll1` 等 l/I 混淆命名 |
| **PackageManager Hook** | 配置中包含 `hookPackageManagerStackTraces`，主动 hook 包管理器 |
| **手势随机化** | 坐标偏移、贝塞尔曲线、压力/时间随机化 |
| **反取证清理** | 每次任务后清除 cookies、缓存、历史、表单数据、SSL 偏好 |
| **进程检查** | 检查当前进程名是否匹配包名 |
| **时间窗口检查** | 21600000ms (6小时) 冷却时间，防止频繁执行 |
| **定时器** | 每30分钟 (1800000ms) 循环执行任务 |

## 十、关键文件路径

| 组件 | 反编译路径 |
|---|---|
| 入口点 | `com/nied/MainActivity.java` → `com/nied/lduvv/Kucopd.java` |
| 触摸伪造 | `com/nied/MotionHelper.java` |
| 随机数工具 | `com/nied/RandomHelper.java` |
| 字符串加密 | `IlIlllIIlI1/IllIIlIIII1.java` |
| SDK配置常量 | `IlIlllIIlI1/llllIIIIll1.java` |
| 主控制逻辑 | `IlIlllIIlI1/llllIllIl1.java` |
| HTTP通信 | `c13/nim5/ez8/h5_proto/HttpGatewayClient.java` |
| SDK核心 | `c13/nim5/ez8/h5_proto/DllpgdLiteSDK.java` |
| 设备指纹 | `c13/nim5/ez8/h5_proto/Atom.java` |
| 远程配置 | `c13/nim5/ez8/h5_proto/DllpgdConfig.java` |
| WebRTC信令 | `c13/nim5/ez8/h5_proto/signaling/` |
| WebView控制 | `lIllIlIll1/` (混淆包) |
| 点击测试页 | `assets/click_test.html` |
