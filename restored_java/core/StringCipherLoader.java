package core;

import java.lang.reflect.InvocationTargetException;

/**
 * MALWARE ANALYSIS — Dynamic string cipher loader
 *
 * Original: lIIIIlllllIlll1.IllIIlIIII1
 *
 * Loads a StringCipherInterface implementation by class name via reflection.
 * This allows the malware to swap cipher implementations without recompilation.
 *
 * The cipher is loaded once in the constructor and cached for all future calls.
 */
public final class StringCipherLoader implements StringCipherInterface {

    /** Loaded cipher instance. Original: f333llllIIIIll1 */
    public final StringCipherInterface delegate;

    /**
     * Load a cipher implementation by fully qualified class name.
     * Original: IllIIlIIII1(String)
     */
    public StringCipherLoader(String className) {
        try {
            this.delegate = (StringCipherInterface) Class.forName(className)
                    .getDeclaredConstructor()
                    .newInstance();
        } catch (ClassNotFoundException unused) {
            throw new IllegalArgumentException("Cipher class not found: " + className);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalArgumentException("Cannot instantiate cipher: " + e.getMessage());
        } catch (InstantiationException e) {
            throw new IllegalArgumentException("Cannot instantiate cipher: " + e.getMessage());
        }
    }

    @Override
    public String decrypt(byte[] encrypted, byte[] key) {
        return delegate != null ? delegate.decrypt(encrypted, key) : new String(encrypted);
    }

    @Override
    public byte[] encrypt(String plaintext, byte[] key) {
        return delegate != null ? delegate.encrypt(plaintext, key) : plaintext.getBytes();
    }

    @Override
    public boolean isActive(String name) {
        return delegate != null && delegate.isActive(name);
    }
}
