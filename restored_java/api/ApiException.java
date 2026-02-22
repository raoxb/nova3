package api;

/**
 * ApiException — API 响应异常
 *
 * Original: IIlIllIIll1.llllIIIIll1
 *
 * Thrown when an API call to the C&C server returns an error response.
 * Contains the error code, error name, and error message from the server.
 *
 * The exception message format is:
 *   "API响应失败[{name}]: code={code}, message={message}"
 *   (Chinese: "API response failed[{name}]: code={code}, message={message}")
 *
 * Fields:
 *   - f65lIIIIlllllIlll1 → errorCode (int)
 *   - f66llllIIIIll1 → errorName (String)
 *   - f67llllIllIl1 → errorMessage (String)
 *
 * NOTE: The original JADX decompilation failed on the constructors (bytecode dump only).
 *       The constructors have been manually reconstructed from the smali bytecode.
 */
public class ApiException extends Exception {

    /** The error code from the C&C server response */
    private final int errorCode;         /* f65lIIIIlllllIlll1 */

    /** The error name/category identifier */
    private final String errorName;      /* f66llllIIIIll1 */

    /** The detailed error message */
    private final String errorMessage;   /* f67llllIllIl1 */

    /**
     * Creates an ApiException with the given name, code, and message.
     *
     * The super(message) call formats the exception message as:
     *   "API响应失败[{name}]: code={code}, message={message}"
     *
     * Original: llllIIIIll1(String r6, int r7, String r8)
     * Reconstructed from JADX bytecode dump.
     *
     * @param name    the error name/category (e.g., "getToken", "getTask")
     * @param code    the error code from the server
     * @param message the error message from the server
     */
    public ApiException(String name, int code, String message) {
        super("API响应失败[" + name + "]: code=" + code + ", message=" + message);
        this.errorCode = code;
        this.errorMessage = message;
        this.errorName = name;
    }

    /**
     * Creates an ApiException with the given name, code, message, and cause.
     *
     * Original: llllIIIIll1(String r6, int r7, String r8, Throwable r9)
     * Reconstructed from JADX bytecode dump.
     *
     * @param name    the error name/category
     * @param code    the error code from the server
     * @param message the error message from the server
     * @param cause   the original exception that caused this API error
     */
    public ApiException(String name, int code, String message, Throwable cause) {
        super("API响应失败[" + name + "]: code=" + code + ", message=" + message, cause);
        this.errorCode = code;
        this.errorMessage = message;
        this.errorName = name;
    }

    /** Returns the error code from the C&C server. Original: llllIIIIll1() */
    public int getErrorCode() {
        return this.errorCode;
    }

    /** Returns the error name/category. Original: llllIllIl1() */
    public String getErrorName() {
        return this.errorName;
    }

    /** Returns the error message. Original: lIIIIlllllIlll1() */
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
