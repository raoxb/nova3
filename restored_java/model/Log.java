package model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Log — 日志条目模型
 *
 * Original: c13.nim5.ez8.h5_proto.Log
 *
 * Data model for log entries transmitted to the C&C server.
 * Each log entry contains a timestamp, severity level, tag, and message.
 *
 * Used by LogHelper to serialize log entries for remote upload.
 * When LogHelper.log() is called at INFO level, a Log instance is created
 * and enqueued for upload via EventReporter.
 *
 * JSON format (sent to C&C):
 * {
 *   "timestamp": 1700000000000,
 *   "level": "INFO",
 *   "tag": "TaskOrchestrator",
 *   "message": "start task"
 * }
 *
 * ┌──────────────────────────────────────────────────────────────┐
 * │ LogLevel Enum                                                │
 * ├──────────────────────────────────────────────────────────────┤
 * │ DEBUG  = 0   (default if null/unknown)                      │
 * │ INFO   = 1   (triggers C&C log upload)                      │
 * │ WARN   = 2                                                  │
 * │ ERROR  = 3                                                  │
 * └──────────────────────────────────────────────────────────────┘
 *
 * NOTE: JADX failed to restore the enum due to "external variable init".
 *       The enum values were reconstructed from the static initializer
 *       and XOR-decrypted string names.
 */
public class Log {

    private Long timestamp;
    private LogLevel level;
    private String tag;
    private String message;

    // =========================================================================
    // LogLevel Enum
    // =========================================================================

    /**
     * Log severity level enum.
     *
     * Original: c13.nim5.ez8.h5_proto.Log.LogLevel
     *
     * JADX warning: "Enum visitor error — Init of enum field 'DEBUG' uses external variables"
     * The enum was reconstructed from the static initializer block.
     *
     * Each value has:
     *   - stringValue: uppercase name (used for JSON serialization and fromValue lookup)
     *   - intValue: numeric ordinal (used for comparisons and fromValue lookup)
     *
     * The LogHelper checks intValue == 1 (INFO) to decide whether to upload logs to C&C.
     */
    public enum LogLevel {
        DEBUG("DEBUG", 0),
        INFO("INFO", 1),
        WARN("WARN", 2),
        ERROR("ERROR", 3);

        private final String stringValue;
        private final int intValue;

        LogLevel(String stringValue, int intValue) {
            this.stringValue = stringValue;
            this.intValue = intValue;
        }

        /**
         * Returns the integer value of this level.
         * LogHelper uses this to check: logLevel.getIntValue() == 1 → upload to C&C
         */
        public int getIntValue() {
            return this.intValue;
        }

        /** Returns the string representation (e.g., "DEBUG", "INFO"). */
        public String getStringValue() {
            return this.stringValue;
        }

        /** Returns the JSON serialization value (same as stringValue). */
        public String toJsonValue() {
            return this.stringValue;
        }

        /**
         * Resolves a LogLevel from a String or Integer value.
         * Returns DEBUG if the input is null or doesn't match any level.
         *
         * Original: fromValue(Object obj)
         *
         * @param obj a String (case-insensitive) or Integer/Long value
         * @return the matching LogLevel, or DEBUG as fallback
         */
        public static LogLevel fromValue(Object obj) {
            if (obj == null) {
                return DEBUG;
            }
            if (obj instanceof String) {
                String upperCase = ((String) obj).toUpperCase();
                for (LogLevel level : values()) {
                    if (level.stringValue.equals(upperCase)) {
                        return level;
                    }
                }
            } else if (obj instanceof Integer || obj instanceof Long) {
                int intValue = ((Number) obj).intValue();
                for (LogLevel level : values()) {
                    if (level.intValue == intValue) {
                        return level;
                    }
                }
            }
            return DEBUG;
        }
    }

    // =========================================================================
    // Constructors
    // =========================================================================

    /**
     * Default constructor. Initializes all fields to defaults.
     *
     * Original has redundant double-initialization (decompiler artifact).
     */
    public Log() {
        this.timestamp = 0L;
        this.level = LogLevel.DEBUG;
        this.tag = "";
        this.message = "";
    }

    // =========================================================================
    // JSON Serialization
    // =========================================================================

    /**
     * Serializes this Log entry to a JSONObject for C&C upload.
     *
     * JSON keys (XOR-decrypted):
     *   - "timestamp" → this.timestamp
     *   - "level"     → this.level.toJsonValue()
     *   - "tag"       → this.tag
     *   - "message"   → this.message
     */
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("timestamp", this.timestamp);
        json.put("level", this.level.toJsonValue());
        json.put("tag", this.tag);
        json.put("message", this.message);
        return json;
    }

    /**
     * Deserializes a Log entry from a JSONObject.
     *
     * Original: fromJSONObject(JSONObject)
     */
    public static Log fromJSONObject(JSONObject json) throws JSONException {
        Log log = new Log();
        if (json.has("timestamp") && !json.isNull("timestamp")) {
            log.timestamp = json.getLong("timestamp");
        }
        if (json.has("level") && !json.isNull("level")) {
            log.level = LogLevel.fromValue(json.get("level"));
        }
        if (json.has("tag") && !json.isNull("tag")) {
            log.tag = json.getString("tag");
        }
        if (json.has("message") && !json.isNull("message")) {
            log.message = json.getString("message");
        }
        return log;
    }

    // =========================================================================
    // Getters & Setters
    // =========================================================================

    public Long getTimestamp() { return this.timestamp; }
    public LogLevel getLevel() { return this.level; }
    public String getTag() { return this.tag; }
    public String getMessage() { return this.message; }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp != null ? timestamp : 0L;
    }

    public void setLevel(LogLevel level) {
        this.level = (level != null) ? level : LogLevel.DEBUG;
    }

    public void setLevelFromValue(Object obj) {
        this.level = LogLevel.fromValue(obj);
    }

    public void setTag(String tag) {
        this.tag = (tag != null) ? tag : "";
    }

    public void setMessage(String message) {
        this.message = (message != null) ? message : "";
    }

    // =========================================================================
    // toString
    // =========================================================================

    @Override
    public String toString() {
        return "Log{timestamp=" + this.timestamp
                + ", level=" + this.level
                + ", tag=" + this.tag
                + ", message=" + this.message + "}";
    }
}
