package hook;

import android.app.Application;
import android.content.Context;
import java.lang.reflect.Field;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: IlIllll1/lIIIIlllllIlll1
 *
 * Static utility that installs the package name hook by reflecting into the
 * Application's mBase field and replacing the original Context with a
 * HookContextWrapper. After installation, all context.getPackageName() and
 * context.getPackageManager() calls from the Application flow through the hook.
 *
 * Original obfuscated name: IlIllll1.lIIIIlllllIlll1
 */
public class ContextHookInstaller {

    /** Tracks whether the hook has been successfully installed */
    public static boolean isHooked = false;

    /**
     * Installs the context hook by reflecting Application.mBase.
     *
     * Steps:
     *   1. Get the Application instance via AppContext
     *   2. Reflect to find the "mBase" field (searching up the inheritance chain)
     *   3. Read the original Context from mBase
     *   4. Replace it with a new HookContextWrapper wrapping the original
     *   5. Verify the replacement succeeded
     *
     * @throws Throwable if reflection fails or the Application is unavailable
     */
    public static void installHook() throws Throwable {
        // Get the Application instance
        Application application = getApplication();
        if (application == null) {
            throw new HookException("application is null");
        }

        // Reflect to find ContextWrapper.mBase field (walk up inheritance chain)
        Field mBaseField = findFieldRecursive(application.getClass(), "mBase");
        if (mBaseField == null) {
            throw new HookException("mBase field not found");
        }

        // Read the original base context
        Object originalContext = mBaseField.get(application);
        if (!(originalContext instanceof Context)) {
            throw new HookException("mBase is not Context");
        }

        // Replace with hooked context wrapper
        mBaseField.set(application, new HookContextWrapper((Context) originalContext));

        // Verify replacement succeeded
        isHooked = mBaseField.get(application) instanceof HookContextWrapper;
    }

    /**
     * Recursively searches for a field by name, walking up the class hierarchy.
     * Makes the field accessible if found.
     *
     * @param cls       the class to start searching from
     * @param fieldName the field name to find
     * @return the Field, or null if not found in the entire hierarchy
     */
    public static Field findFieldRecursive(Class<?> cls, String fieldName) {
        Field field;
        try {
            field = cls.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            field = null;
        }
        // If not found in this class, recurse into superclass
        if (field == null && cls.getSuperclass() != null) {
            return findFieldRecursive(cls.getSuperclass(), fieldName);
        }
        if (field != null && !field.isAccessible()) {
            field.setAccessible(true);
        }
        return field;
    }

    /**
     * Gets the Application instance. Delegates to AppContext.
     * In the original code this was: IlIlllIIlI1.lIIIIlllllIlll1.getApplication()
     */
    private static Application getApplication() {
        try {
            // Use ActivityThread.currentApplication() via reflection as a fallback
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object currentActivityThread = activityThread.getMethod("currentActivityThread").invoke(null);
            return (Application) activityThread.getMethod("getApplication").invoke(currentActivityThread);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Inner exception type for hook installation failures.
     */
    public static class HookException extends RuntimeException {
        public HookException(String message) {
            super(message);
        }
    }
}
