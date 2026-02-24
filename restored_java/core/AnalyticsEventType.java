package core;

/**
 * MALWARE ANALYSIS -- Analytics event type enumeration
 *
 * Original: lllllIllIl1.llllIIIIll1
 *
 * Defines the set of telemetry event types used by the malware to track
 * actions, milestones, and errors. Each enum constant has a string value
 * that is XOR-encrypted at runtime and decrypted when needed.
 *
 * The 12 event types likely correspond to lifecycle and operation events
 * such as SDK initialization, connection, task execution, errors, etc.
 * The exact string values are encrypted with XOR keys at compile time
 * and decrypted via {@link XorStringCipher} at runtime.
 *
 * Usage: Each constant's {@link #getValue()} returns the decrypted string
 * identifier that is sent to the C&C server in event reports.
 *
 * Mapping of original field names to enum constants:
 *   f722lIIIIlllllIlll1  -> EVENT_TYPE_1   (e.g., "sdk_init")
 *   f725llllIllIl1        -> EVENT_TYPE_2   (e.g., "connect")
 *   f721IllIIlIIII1       -> EVENT_TYPE_3   (e.g., "disconnect")
 *   f719IlIlllIIlI1       -> EVENT_TYPE_4   (e.g., "error")
 *   f718IlIllIlllIllI1    -> EVENT_TYPE_5   (e.g., "task_started")
 *   f727llllllIlIIIlll1   -> EVENT_TYPE_6   (e.g., "task_completed")
 *   f717IlIlIIlIII1       -> EVENT_TYPE_7   (e.g., "screenshot")
 *   f723lIllIIIlIl1       -> EVENT_TYPE_8   (e.g., "touch_event")
 *   f715IIlIllIIll1       -> EVENT_TYPE_9   (e.g., "scroll_event")
 *   f720IlIllll1           -> EVENT_TYPE_10  (e.g., "webrtc_connected")
 *   f726lllllIllIl1        -> EVENT_TYPE_11  (e.g., "webrtc_error")
 *   f716IlIIlllllI1       -> EVENT_TYPE_12  (e.g., "heartbeat")
 */
public enum AnalyticsEventType {

    // =========================================================================
    // Enum Constants
    // =========================================================================

    /**
     * Original: f722lIIIIlllllIlll1
     * XOR key: {13, 91, -52, -114, 97, -90, 31, 74}
     */
    SDK_INIT("sdk_init"),

    /**
     * Original: f725llllIllIl1
     * XOR key: {118, -101, 101, -47, 57, -126, 63, -33}
     */
    CONNECT("connect"),

    /**
     * Original: f721IllIIlIIII1
     * XOR key: {45, -112, 62, -28, -65, -32, 90, 47}
     */
    DISCONNECT("disconnect"),

    /**
     * Original: f719IlIlllIIlI1
     * XOR key: {101, -31, 48, 13, 17, -77, 21, 17}
     */
    ERROR("error"),

    /**
     * Original: f718IlIllIlllIllI1
     * XOR key: {-51, -17, 57, 45, -53, -110, 71, 112}
     */
    TASK_STARTED("task_started"),

    /**
     * Original: f727llllllIlIIIlll1
     * XOR key: {44, 92, 47, 11, 68, 8, -66, -40}
     */
    TASK_COMPLETED("task_completed"),

    /**
     * Original: f717IlIlIIlIII1
     * XOR key: {113, 48, -84, 112, 49, 58, 82, 107}
     */
    SCREENSHOT("screenshot"),

    /**
     * Original: f723lIllIIIlIl1
     * XOR key: {-113, 120, 56, -57, 104, -91, -98, 16}
     */
    TOUCH_EVENT("touch_event"),

    /**
     * Original: f715IIlIllIIll1
     * XOR key: {111, 21, 118, 53, 94, 101, 23, -118}
     */
    SCROLL_EVENT("scroll_event"),

    /**
     * Original: f720IlIllll1
     * XOR key: {33, -86, -85, 66, -110, -87, 70, -66}
     */
    WEBRTC_CONNECTED("webrtc_connected"),

    /**
     * Original: f726lllllIllIl1
     * XOR key: {7, -81, 74, 122, -95, 45, 116, 116}
     */
    WEBRTC_ERROR("webrtc_error"),

    /**
     * Original: f716IlIIlllllI1
     * XOR key: {94, 77, -22, 56, 59, -12, -17, 110}
     */
    HEARTBEAT("heartbeat");

    // =========================================================================
    // Fields
    // =========================================================================

    /**
     * The string identifier for this event type (decrypted from XOR at runtime).
     * Original: f728llllIIIIll1
     */
    public final String value;

    // =========================================================================
    // Constructor
    // =========================================================================

    /**
     * @param value the decrypted string identifier for this event type
     */
    AnalyticsEventType(String value) {
        this.value = value;
    }

    // =========================================================================
    // Accessor
    // =========================================================================

    /**
     * Returns the string identifier for this event type.
     *
     * Original: lIIIIlllllIlll1() -> String
     *
     * @return the event type string
     */
    public String getValue() {
        return this.value;
    }
}
