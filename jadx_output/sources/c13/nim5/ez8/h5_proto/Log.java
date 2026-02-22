package c13.nim5.ez8.h5_proto;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Log {
    private LogLevel level;
    private String message;
    private String tag;
    private Long timestamp;

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'DEBUG' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:293)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:266)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class LogLevel {
        private static final /* synthetic */ LogLevel[] $VALUES;
        public static final LogLevel DEBUG;
        public static final LogLevel ERROR;
        public static final LogLevel INFO;
        public static final LogLevel WARN;
        private final int intValue;
        private final String stringValue;

        private static /* synthetic */ LogLevel[] $values() {
            return new LogLevel[]{DEBUG, INFO, WARN, ERROR};
        }

        static {
            llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
            DEBUG = new LogLevel(lllliiiill1.llllIIIIll1(new byte[]{-17, 60, 110, -56, -46}, new byte[]{-85, 121, 44, -99, -107, 99, -61, 39}), 0, lllliiiill1.llllIIIIll1(new byte[]{21, -86, 14, 36, 91}, new byte[]{81, -17, 76, 113, 28, 107, -116, -92}), 0);
            INFO = new LogLevel(lllliiiill1.llllIIIIll1(new byte[]{79, -78, -97, 28}, new byte[]{6, -4, -39, 83, -8, 82, -96, 22}), 1, lllliiiill1.llllIIIIll1(new byte[]{100, 29, -53, 69}, new byte[]{45, 83, -115, 10, -62, -90, -4, -15}), 1);
            WARN = new LogLevel(lllliiiill1.llllIIIIll1(new byte[]{37, ByteCompanionObject.MAX_VALUE, 10, -50}, new byte[]{114, 62, 88, ByteCompanionObject.MIN_VALUE, -86, -49, -28, -9}), 2, lllliiiill1.llllIIIIll1(new byte[]{126, -16, -70, 53}, new byte[]{41, -79, -24, 123, -52, -6, -1, 116}), 2);
            ERROR = new LogLevel(lllliiiill1.llllIIIIll1(new byte[]{-55, 5, 79, 99, 19}, new byte[]{-116, 87, 29, 44, 65, 11, 91, 79}), 3, lllliiiill1.llllIIIIll1(new byte[]{-10, 77, 109, -21, 54}, new byte[]{-77, 31, 63, -92, 100, 117, -54, 73}), 3);
            $VALUES = $values();
        }

        private LogLevel(String str, int i, String str2, int i2) {
            this.stringValue = str2;
            this.intValue = i2;
        }

        public static LogLevel fromValue(Object obj) {
            if (obj == null) {
                return DEBUG;
            }
            int i = 0;
            if (obj instanceof String) {
                String upperCase = ((String) obj).toUpperCase();
                LogLevel[] values = values();
                int length = values.length;
                while (i < length) {
                    LogLevel logLevel = values[i];
                    if (logLevel.stringValue.equals(upperCase)) {
                        return logLevel;
                    }
                    i++;
                }
            } else if ((obj instanceof Integer) || (obj instanceof Long)) {
                int intValue = ((Number) obj).intValue();
                LogLevel[] values2 = values();
                int length2 = values2.length;
                while (i < length2) {
                    LogLevel logLevel2 = values2[i];
                    if (logLevel2.intValue == intValue) {
                        return logLevel2;
                    }
                    i++;
                }
            }
            return DEBUG;
        }

        public static LogLevel valueOf(String str) {
            return (LogLevel) Enum.valueOf(LogLevel.class, str);
        }

        public static LogLevel[] values() {
            return (LogLevel[]) $VALUES.clone();
        }

        public int getIntValue() {
            return this.intValue;
        }

        public String getStringValue() {
            return this.stringValue;
        }

        public String toJsonValue() {
            return this.stringValue;
        }
    }

    public Log() {
        this.timestamp = 0L;
        LogLevel logLevel = LogLevel.DEBUG;
        this.level = logLevel;
        this.tag = "";
        this.message = "";
        this.timestamp = 0L;
        this.level = logLevel;
        this.tag = "";
        this.message = "";
    }

    public static Log fromJSONObject(JSONObject jSONObject) throws JSONException {
        Log log = new Log();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{-104, 117, 13, 12, -53, 105, 32, -113, -100}, new byte[]{-20, 28, 96, 105, -72, 29, 65, -30})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{-68, -39, -102, -39, -29, -18, -75, 17, -72}, new byte[]{-56, -80, -9, -68, -112, -102, -44, 124}))) {
            log.timestamp = Long.valueOf(jSONObject.getLong(lllliiiill1.llllIIIIll1(new byte[]{-34, 24, 38, 117, -70, -34, 40, -104, -38}, new byte[]{-86, 113, 75, 16, -55, -86, 73, -11})));
        }
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{-41, -58, 30, 8, -121}, new byte[]{-69, -93, 104, 109, -21, 111, 19, -69})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{65, 78, 37, -93, -96}, new byte[]{45, 43, 83, -58, -52, -87, -122, -38}))) {
            log.level = LogLevel.fromValue(jSONObject.get(lllliiiill1.llllIIIIll1(new byte[]{-54, -78, -68, -82, 49}, new byte[]{-90, -41, -54, -53, 93, 84, -40, 118})));
        }
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{-69, 24, 75}, new byte[]{-49, 121, 44, 84, -43, 37, -117, -37})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{-43, 50, 33}, new byte[]{-95, 83, 70, -28, -33, 36, 63, 65}))) {
            log.tag = jSONObject.getString(lllliiiill1.llllIIIIll1(new byte[]{-127, -21, -52}, new byte[]{-11, -118, -85, -81, -96, -7, 113, 26}));
        }
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{7, 26, 24, ByteCompanionObject.MIN_VALUE, -68, 31, -53}, new byte[]{106, ByteCompanionObject.MAX_VALUE, 107, -13, -35, 120, -82, 104})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{100, 5, -6, 79, 54, 122, -71}, new byte[]{9, 96, -119, 60, 87, 29, -36, 86}))) {
            log.message = jSONObject.getString(lllliiiill1.llllIIIIll1(new byte[]{88, 50, 69, 125, -61, -40, 63}, new byte[]{53, 87, 54, 14, -94, -65, 90, 119}));
        }
        return log;
    }

    public LogLevel getLevel() {
        return this.level;
    }

    public String getMessage() {
        return this.message;
    }

    public String getTag() {
        return this.tag;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setLevel(LogLevel logLevel) {
        if (logLevel == null) {
            logLevel = LogLevel.DEBUG;
        }
        this.level = logLevel;
    }

    public void setLevelFromValue(Object obj) {
        this.level = LogLevel.fromValue(obj);
    }

    public void setMessage(String str) {
        if (str == null) {
            str = "";
        }
        this.message = str;
    }

    public void setTag(String str) {
        if (str == null) {
            str = "";
        }
        this.tag = str;
    }

    public void setTimestamp(Long l) {
        this.timestamp = Long.valueOf(l != null ? l.longValue() : 0L);
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-48, 23, 109, -96, -77, -25, 53, 90, -44}, new byte[]{-92, 126, 0, -59, -64, -109, 84, 55}), this.timestamp);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{53, 35, 36, 89, 75}, new byte[]{89, 70, 82, 60, 39, -2, 51, -7}), this.level.toJsonValue());
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{50, -115, -54}, new byte[]{70, -20, -83, -71, -79, -19, -101, -38}), this.tag);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{94, 123, 7, 19, 28, 118, 40}, new byte[]{51, 30, 116, 96, 125, 17, 77, -95}), this.message);
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return sb.append(lllliiiill1.llllIIIIll1(new byte[]{-28, 125, 125, -84, -88, ByteCompanionObject.MIN_VALUE, -73, 45, -37, 102, 123, -70, -84, -44}, new byte[]{-88, 18, 26, -41, -36, -23, -38, 72})).append(this.timestamp).append(lllliiiill1.llllIIIIll1(new byte[]{-52, 42, -24, 21, -88, 33, -44, 115}, new byte[]{-32, 10, -124, 112, -34, 68, -72, 78})).append(this.level).append(lllliiiill1.llllIIIIll1(new byte[]{12, -113, -118, 81, -74, -73}, new byte[]{32, -81, -2, 48, -47, -118, -8, 33})).append(this.tag).append(lllliiiill1.llllIIIIll1(new byte[]{-16, 35, 29, 64, 40, 12, -115, 46, -71, 62}, new byte[]{-36, 3, 112, 37, 91, ByteCompanionObject.MAX_VALUE, -20, 73})).append(this.message).append(lllliiiill1.llllIIIIll1(new byte[]{80}, new byte[]{45, 37, 26, 15, 77, 29, 105, -3})).toString();
    }
}
