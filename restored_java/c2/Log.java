package c2;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: c13.nim5.ez8.h5_proto.Log
 *
 * Represents a single log entry sent to the C&C server via /api/v1/dllpgd/updateLog.
 * Contains a severity level (DEBUG/INFO/WARN/ERROR), timestamp, tag, and message.
 *
 * NOTE: The LogLevel enum was broken by JADX decompilation (due to XOR-encrypted
 * constructor arguments). It has been manually reconstructed below.
 */
public class Log {
    private Long timestamp;
    private LogLevel level;
    private String tag;
    private String message;

    /**
     * Log severity level enum.
     * Supports lookup by both string name and integer value.
     *
     * JADX failed to decompile this enum because the constructor arguments
     * used XOR-encrypted strings. Manually reconstructed from bytecode analysis.
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
         * Resolves a LogLevel from either a String name or integer value.
         * Falls back to DEBUG if no match is found or input is null.
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

        public int getIntValue() { return this.intValue; }
        public String getStringValue() { return this.stringValue; }
        public String toJsonValue() { return this.stringValue; }
    }

    public Log() {
        this.timestamp = 0L;
        this.level = LogLevel.DEBUG;
        this.tag = "";
        this.message = "";
    }

    public static Log fromJSONObject(JSONObject json) throws JSONException {
        Log log = new Log();
        if (json.has("timestamp") && !json.isNull("timestamp")) {
            log.timestamp = Long.valueOf(json.getLong("timestamp"));
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

    public LogLevel getLevel() { return this.level; }
    public String getMessage() { return this.message; }
    public String getTag() { return this.tag; }
    public Long getTimestamp() { return this.timestamp; }

    public void setLevel(LogLevel logLevel) {
        this.level = (logLevel != null) ? logLevel : LogLevel.DEBUG;
    }

    public void setLevelFromValue(Object obj) {
        this.level = LogLevel.fromValue(obj);
    }

    public void setMessage(String str) {
        this.message = (str != null) ? str : "";
    }

    public void setTag(String str) {
        this.tag = (str != null) ? str : "";
    }

    public void setTimestamp(Long l) {
        this.timestamp = Long.valueOf(l != null ? l.longValue() : 0L);
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("timestamp", this.timestamp);
        json.put("level", this.level.toJsonValue());
        json.put("tag", this.tag);
        json.put("message", this.message);
        return json;
    }

    @Override
    public String toString() {
        return "Log{timestamp=" + this.timestamp
                + ", level=" + this.level
                + ", tag=" + this.tag
                + ", message=" + this.message + "}";
    }
}
