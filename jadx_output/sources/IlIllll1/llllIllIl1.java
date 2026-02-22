package IlIllll1;

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

/* loaded from: classes.dex */
public class llllIllIl1 extends PackageManager {

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public static final String f294IllIIlIIII1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-4, 84, -13, -107, -28, 47, -27, 116, -41, 80, -3, -103, -28, 6, -44, 123, -43, 92, -7, -116}, new byte[]{-76, 59, -100, -2, -127, 75, -75, 21});

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public PackageManager f296llllIIIIll1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final String f295lIIIIlllllIlll1 = IlIllll1.llllIIIIll1.llllIIIIll1();

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final String f297llllIllIl1 = IlIllll1.llllIIIIll1.f289IllIIlIIII1;

    public static class llllIIIIll1 extends RuntimeException {
        public llllIIIIll1(String str) {
            super(str);
        }
    }

    public llllIllIl1(PackageManager packageManager) throws llllIIIIll1 {
        this.f296llllIIIIll1 = packageManager;
        llllIIIIll1();
    }

    @Override // android.content.pm.PackageManager
    public void addPackageToPreferred(String str) {
        this.f296llllIIIIll1.addPackageToPreferred(str);
    }

    @Override // android.content.pm.PackageManager
    public boolean addPermission(PermissionInfo permissionInfo) {
        return this.f296llllIIIIll1.addPermission(permissionInfo);
    }

    @Override // android.content.pm.PackageManager
    public boolean addPermissionAsync(PermissionInfo permissionInfo) {
        return this.f296llllIIIIll1.addPermissionAsync(permissionInfo);
    }

    @Override // android.content.pm.PackageManager
    public void addPreferredActivity(IntentFilter intentFilter, int i, ComponentName[] componentNameArr, ComponentName componentName) {
        this.f296llllIIIIll1.addPreferredActivity(intentFilter, i, componentNameArr, componentName);
    }

    @Override // android.content.pm.PackageManager
    public boolean canRequestPackageInstalls() {
        return this.f296llllIIIIll1.canRequestPackageInstalls();
    }

    @Override // android.content.pm.PackageManager
    public String[] canonicalToCurrentPackageNames(String[] strArr) {
        return this.f296llllIIIIll1.canonicalToCurrentPackageNames(strArr);
    }

    @Override // android.content.pm.PackageManager
    public int checkPermission(String str, String str2) {
        return this.f296llllIIIIll1.checkPermission(str, str2);
    }

    @Override // android.content.pm.PackageManager
    public int checkSignatures(String str, String str2) {
        return this.f296llllIIIIll1.checkSignatures(str, str2);
    }

    @Override // android.content.pm.PackageManager
    public void clearInstantAppCookie() {
        this.f296llllIIIIll1.clearInstantAppCookie();
    }

    @Override // android.content.pm.PackageManager
    public void clearPackagePreferredActivities(String str) {
        this.f296llllIIIIll1.clearPackagePreferredActivities(str);
    }

    @Override // android.content.pm.PackageManager
    public String[] currentToCanonicalPackageNames(String[] strArr) {
        return this.f296llllIIIIll1.currentToCanonicalPackageNames(strArr);
    }

    @Override // android.content.pm.PackageManager
    public void extendVerificationTimeout(int i, int i2, long j) {
        this.f296llllIIIIll1.extendVerificationTimeout(i, i2, j);
    }

    @Override // android.content.pm.PackageManager
    public Drawable getActivityBanner(ComponentName componentName) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getActivityBanner(componentName);
    }

    @Override // android.content.pm.PackageManager
    public Drawable getActivityIcon(ComponentName componentName) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getActivityIcon(componentName);
    }

    @Override // android.content.pm.PackageManager
    public ActivityInfo getActivityInfo(ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getActivityInfo(componentName, i);
    }

    @Override // android.content.pm.PackageManager
    public Drawable getActivityLogo(ComponentName componentName) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getActivityLogo(componentName);
    }

    @Override // android.content.pm.PackageManager
    public List<PermissionGroupInfo> getAllPermissionGroups(int i) {
        return this.f296llllIIIIll1.getAllPermissionGroups(i);
    }

    @Override // android.content.pm.PackageManager
    public Drawable getApplicationBanner(ApplicationInfo applicationInfo) {
        return this.f296llllIIIIll1.getApplicationBanner(applicationInfo);
    }

    @Override // android.content.pm.PackageManager
    public int getApplicationEnabledSetting(String str) {
        return this.f296llllIIIIll1.getApplicationEnabledSetting(str);
    }

    @Override // android.content.pm.PackageManager
    public Drawable getApplicationIcon(ApplicationInfo applicationInfo) {
        return this.f296llllIIIIll1.getApplicationIcon(applicationInfo);
    }

    @Override // android.content.pm.PackageManager
    public ApplicationInfo getApplicationInfo(String str, int i) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getApplicationInfo(str, i);
    }

    @Override // android.content.pm.PackageManager
    public CharSequence getApplicationLabel(ApplicationInfo applicationInfo) {
        return this.f296llllIIIIll1.getApplicationLabel(applicationInfo);
    }

    @Override // android.content.pm.PackageManager
    public Drawable getApplicationLogo(ApplicationInfo applicationInfo) {
        return this.f296llllIIIIll1.getApplicationLogo(applicationInfo);
    }

    @Override // android.content.pm.PackageManager
    public ChangedPackages getChangedPackages(int i) {
        return this.f296llllIIIIll1.getChangedPackages(i);
    }

    @Override // android.content.pm.PackageManager
    public int getComponentEnabledSetting(ComponentName componentName) {
        return this.f296llllIIIIll1.getComponentEnabledSetting(componentName);
    }

    @Override // android.content.pm.PackageManager
    public Drawable getDefaultActivityIcon() {
        return this.f296llllIIIIll1.getDefaultActivityIcon();
    }

    @Override // android.content.pm.PackageManager
    public Drawable getDrawable(String str, int i, ApplicationInfo applicationInfo) {
        return this.f296llllIIIIll1.getDrawable(str, i, applicationInfo);
    }

    @Override // android.content.pm.PackageManager
    public List<ApplicationInfo> getInstalledApplications(int i) {
        return this.f296llllIIIIll1.getInstalledApplications(i);
    }

    @Override // android.content.pm.PackageManager
    public List<PackageInfo> getInstalledPackages(int i) {
        return this.f296llllIIIIll1.getInstalledPackages(i);
    }

    @Override // android.content.pm.PackageManager
    public String getInstallerPackageName(String str) {
        if (str != null && str.equals(this.f295lIIIIlllllIlll1)) {
            str = this.f297llllIllIl1;
        }
        return this.f296llllIIIIll1.getInstallerPackageName(str);
    }

    @Override // android.content.pm.PackageManager
    public byte[] getInstantAppCookie() {
        return this.f296llllIIIIll1.getInstantAppCookie();
    }

    @Override // android.content.pm.PackageManager
    public int getInstantAppCookieMaxBytes() {
        return this.f296llllIIIIll1.getInstantAppCookieMaxBytes();
    }

    @Override // android.content.pm.PackageManager
    public InstrumentationInfo getInstrumentationInfo(ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getInstrumentationInfo(componentName, i);
    }

    @Override // android.content.pm.PackageManager
    public Intent getLaunchIntentForPackage(String str) {
        return this.f296llllIIIIll1.getLaunchIntentForPackage(str);
    }

    @Override // android.content.pm.PackageManager
    public Intent getLeanbackLaunchIntentForPackage(String str) {
        return this.f296llllIIIIll1.getLeanbackLaunchIntentForPackage(str);
    }

    @Override // android.content.pm.PackageManager
    public String getNameForUid(int i) {
        return this.f296llllIIIIll1.getNameForUid(i);
    }

    @Override // android.content.pm.PackageManager
    public int[] getPackageGids(String str) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getPackageGids(str);
    }

    @Override // android.content.pm.PackageManager
    public PackageInfo getPackageInfo(String str, int i) throws PackageManager.NameNotFoundException {
        if (str != null && str.equals(this.f295lIIIIlllllIlll1)) {
            str = this.f297llllIllIl1;
        }
        return this.f296llllIIIIll1.getPackageInfo(str, i);
    }

    @Override // android.content.pm.PackageManager
    public PackageInstaller getPackageInstaller() {
        return this.f296llllIIIIll1.getPackageInstaller();
    }

    @Override // android.content.pm.PackageManager
    public int getPackageUid(String str, int i) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getPackageUid(str, i);
    }

    @Override // android.content.pm.PackageManager
    public String[] getPackagesForUid(int i) {
        return this.f296llllIIIIll1.getPackagesForUid(i);
    }

    @Override // android.content.pm.PackageManager
    public List<PackageInfo> getPackagesHoldingPermissions(String[] strArr, int i) {
        return this.f296llllIIIIll1.getPackagesHoldingPermissions(strArr, i);
    }

    @Override // android.content.pm.PackageManager
    public PermissionGroupInfo getPermissionGroupInfo(String str, int i) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getPermissionGroupInfo(str, i);
    }

    @Override // android.content.pm.PackageManager
    public PermissionInfo getPermissionInfo(String str, int i) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getPermissionInfo(str, i);
    }

    @Override // android.content.pm.PackageManager
    public int getPreferredActivities(List<IntentFilter> list, List<ComponentName> list2, String str) {
        return this.f296llllIIIIll1.getPreferredActivities(list, list2, str);
    }

    @Override // android.content.pm.PackageManager
    public List<PackageInfo> getPreferredPackages(int i) {
        return this.f296llllIIIIll1.getPreferredPackages(i);
    }

    @Override // android.content.pm.PackageManager
    public ProviderInfo getProviderInfo(ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getProviderInfo(componentName, i);
    }

    @Override // android.content.pm.PackageManager
    public ActivityInfo getReceiverInfo(ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getReceiverInfo(componentName, i);
    }

    @Override // android.content.pm.PackageManager
    public Resources getResourcesForActivity(ComponentName componentName) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getResourcesForActivity(componentName);
    }

    @Override // android.content.pm.PackageManager
    public Resources getResourcesForApplication(ApplicationInfo applicationInfo) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getResourcesForApplication(applicationInfo);
    }

    @Override // android.content.pm.PackageManager
    public ServiceInfo getServiceInfo(ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getServiceInfo(componentName, i);
    }

    @Override // android.content.pm.PackageManager
    public List<SharedLibraryInfo> getSharedLibraries(int i) {
        return this.f296llllIIIIll1.getSharedLibraries(i);
    }

    @Override // android.content.pm.PackageManager
    public FeatureInfo[] getSystemAvailableFeatures() {
        return this.f296llllIIIIll1.getSystemAvailableFeatures();
    }

    @Override // android.content.pm.PackageManager
    public String[] getSystemSharedLibraryNames() {
        return this.f296llllIIIIll1.getSystemSharedLibraryNames();
    }

    @Override // android.content.pm.PackageManager
    public CharSequence getText(String str, int i, ApplicationInfo applicationInfo) {
        return this.f296llllIIIIll1.getText(str, i, applicationInfo);
    }

    @Override // android.content.pm.PackageManager
    public Drawable getUserBadgedDrawableForDensity(Drawable drawable, UserHandle userHandle, Rect rect, int i) {
        return this.f296llllIIIIll1.getUserBadgedDrawableForDensity(drawable, userHandle, rect, i);
    }

    @Override // android.content.pm.PackageManager
    public Drawable getUserBadgedIcon(Drawable drawable, UserHandle userHandle) {
        return this.f296llllIIIIll1.getUserBadgedIcon(drawable, userHandle);
    }

    @Override // android.content.pm.PackageManager
    public CharSequence getUserBadgedLabel(CharSequence charSequence, UserHandle userHandle) {
        return this.f296llllIIIIll1.getUserBadgedLabel(charSequence, userHandle);
    }

    @Override // android.content.pm.PackageManager
    public XmlResourceParser getXml(String str, int i, ApplicationInfo applicationInfo) {
        return this.f296llllIIIIll1.getXml(str, i, applicationInfo);
    }

    @Override // android.content.pm.PackageManager
    public boolean hasSystemFeature(String str) {
        return this.f296llllIIIIll1.hasSystemFeature(str);
    }

    @Override // android.content.pm.PackageManager
    public boolean isInstantApp() {
        return this.f296llllIIIIll1.isInstantApp();
    }

    @Override // android.content.pm.PackageManager
    public boolean isPermissionRevokedByPolicy(String str, String str2) {
        return this.f296llllIIIIll1.isPermissionRevokedByPolicy(str, str2);
    }

    @Override // android.content.pm.PackageManager
    public boolean isSafeMode() {
        return this.f296llllIIIIll1.isSafeMode();
    }

    public final void llllIIIIll1() throws llllIIIIll1 {
        if (this.f296llllIIIIll1 != null) {
            return;
        }
        throw new llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{40, -82, -91, -88, 109, -107, 38, -116, 33, -82, -79, -88, 109, -120, 38, -127, 43, -88, -77, -65, 109, -116, 52, -49, 36, -70, -70, -95, 99}, new byte[]{74, -49, -42, -51, 77, -27, 71, -17}));
    }

    @Override // android.content.pm.PackageManager
    public List<ResolveInfo> queryBroadcastReceivers(Intent intent, int i) {
        return this.f296llllIIIIll1.queryBroadcastReceivers(intent, i);
    }

    @Override // android.content.pm.PackageManager
    public List<ProviderInfo> queryContentProviders(String str, int i, int i2) {
        return this.f296llllIIIIll1.queryContentProviders(str, i, i2);
    }

    @Override // android.content.pm.PackageManager
    public List<InstrumentationInfo> queryInstrumentation(String str, int i) {
        return this.f296llllIIIIll1.queryInstrumentation(str, i);
    }

    @Override // android.content.pm.PackageManager
    public List<ResolveInfo> queryIntentActivities(Intent intent, int i) {
        return this.f296llllIIIIll1.queryIntentActivities(intent, i);
    }

    @Override // android.content.pm.PackageManager
    public List<ResolveInfo> queryIntentActivityOptions(ComponentName componentName, Intent[] intentArr, Intent intent, int i) {
        return this.f296llllIIIIll1.queryIntentActivityOptions(componentName, intentArr, intent, i);
    }

    @Override // android.content.pm.PackageManager
    public List<ResolveInfo> queryIntentContentProviders(Intent intent, int i) {
        return this.f296llllIIIIll1.queryIntentContentProviders(intent, i);
    }

    @Override // android.content.pm.PackageManager
    public List<ResolveInfo> queryIntentServices(Intent intent, int i) {
        return this.f296llllIIIIll1.queryIntentServices(intent, i);
    }

    @Override // android.content.pm.PackageManager
    public List<PermissionInfo> queryPermissionsByGroup(String str, int i) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.queryPermissionsByGroup(str, i);
    }

    @Override // android.content.pm.PackageManager
    public void removePackageFromPreferred(String str) {
        this.f296llllIIIIll1.removePackageFromPreferred(str);
    }

    @Override // android.content.pm.PackageManager
    public void removePermission(String str) {
        this.f296llllIIIIll1.removePermission(str);
    }

    @Override // android.content.pm.PackageManager
    public ResolveInfo resolveActivity(Intent intent, int i) {
        return this.f296llllIIIIll1.resolveActivity(intent, i);
    }

    @Override // android.content.pm.PackageManager
    public ProviderInfo resolveContentProvider(String str, int i) {
        return this.f296llllIIIIll1.resolveContentProvider(str, i);
    }

    @Override // android.content.pm.PackageManager
    public ResolveInfo resolveService(Intent intent, int i) {
        return this.f296llllIIIIll1.resolveService(intent, i);
    }

    @Override // android.content.pm.PackageManager
    public void setApplicationCategoryHint(String str, int i) {
        this.f296llllIIIIll1.setApplicationCategoryHint(str, i);
    }

    @Override // android.content.pm.PackageManager
    public void setApplicationEnabledSetting(String str, int i, int i2) {
        this.f296llllIIIIll1.setApplicationEnabledSetting(str, i, i2);
    }

    @Override // android.content.pm.PackageManager
    public void setComponentEnabledSetting(ComponentName componentName, int i, int i2) {
        this.f296llllIIIIll1.setComponentEnabledSetting(componentName, i, i2);
    }

    @Override // android.content.pm.PackageManager
    public void setInstallerPackageName(String str, String str2) {
        this.f296llllIIIIll1.setInstallerPackageName(str, str2);
    }

    @Override // android.content.pm.PackageManager
    public void updateInstantAppCookie(byte[] bArr) {
        this.f296llllIIIIll1.updateInstantAppCookie(bArr);
    }

    @Override // android.content.pm.PackageManager
    public void verifyPendingInstall(int i, int i2) {
        this.f296llllIIIIll1.verifyPendingInstall(i, i2);
    }

    @Override // android.content.pm.PackageManager
    public int checkSignatures(int i, int i2) {
        return this.f296llllIIIIll1.checkSignatures(i, i2);
    }

    @Override // android.content.pm.PackageManager
    public Drawable getActivityBanner(Intent intent) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getActivityBanner(intent);
    }

    @Override // android.content.pm.PackageManager
    public Drawable getActivityIcon(Intent intent) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getActivityIcon(intent);
    }

    @Override // android.content.pm.PackageManager
    public Drawable getActivityLogo(Intent intent) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getActivityLogo(intent);
    }

    @Override // android.content.pm.PackageManager
    public Drawable getApplicationBanner(String str) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getApplicationBanner(str);
    }

    @Override // android.content.pm.PackageManager
    public Drawable getApplicationIcon(String str) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getApplicationIcon(str);
    }

    @Override // android.content.pm.PackageManager
    public Drawable getApplicationLogo(String str) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getApplicationLogo(str);
    }

    @Override // android.content.pm.PackageManager
    public int[] getPackageGids(String str, int i) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getPackageGids(str, i);
    }

    @Override // android.content.pm.PackageManager
    public Resources getResourcesForApplication(String str) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getResourcesForApplication(str);
    }

    @Override // android.content.pm.PackageManager
    public boolean hasSystemFeature(String str, int i) {
        return this.f296llllIIIIll1.hasSystemFeature(str, i);
    }

    @Override // android.content.pm.PackageManager
    public boolean isInstantApp(String str) {
        return this.f296llllIIIIll1.isInstantApp(str);
    }

    @Override // android.content.pm.PackageManager
    public PackageInfo getPackageInfo(VersionedPackage versionedPackage, int i) throws PackageManager.NameNotFoundException {
        return this.f296llllIIIIll1.getPackageInfo(versionedPackage, i);
    }
}
