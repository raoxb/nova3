package c13.nim5.ez8.h5_proto.signaling;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import c13.nim5.ez8.h5_proto.Atom;
import kotlin.jvm.internal.ByteCompanionObject;
import lIllIIIlIl1.IlIllll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UpdateSignalingStatusRequest implements IlIllll1 {
    private final Atom atom;
    private final String jobId;
    private final Status status;
    private final String url;

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'UNKNOWN' uses external variables
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
    public static final class Status {
        private static final /* synthetic */ Status[] $VALUES;
        public static final Status DONE;
        public static final Status IN_LANDING;
        public static final Status START;
        public static final Status UNKNOWN;
        private final int value;

        private static /* synthetic */ Status[] $values() {
            return new Status[]{UNKNOWN, START, IN_LANDING, DONE};
        }

        static {
            llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
            UNKNOWN = new Status(lllliiiill1.llllIIIIll1(new byte[]{-15, 94, -99, -3, 55, 0, 68}, new byte[]{-92, 16, -42, -77, 120, 87, 10, 60}), 0, 0);
            START = new Status(lllliiiill1.llllIIIIll1(new byte[]{-101, 93, 24, -113, 37}, new byte[]{-56, 9, 89, -35, 113, 74, -51, 85}), 1, 1);
            IN_LANDING = new Status(lllliiiill1.llllIIIIll1(new byte[]{121, -69, -90, 34, -56, -70, 4, 42, 126, -78}, new byte[]{48, -11, -7, 110, -119, -12, 64, 99}), 2, 2);
            DONE = new Status(lllliiiill1.llllIIIIll1(new byte[]{-105, -127, -81, -99}, new byte[]{-45, -50, -31, -40, -115, -87, -25, 19}), 3, 3);
            $VALUES = $values();
        }

        private Status(String str, int i, int i2) {
            this.value = i2;
        }

        public static Status fromValue(int i) {
            for (Status status : values()) {
                if (status.value == i) {
                    return status;
                }
            }
            return UNKNOWN;
        }

        public static Status valueOf(String str) {
            return (Status) Enum.valueOf(Status.class, str);
        }

        public static Status[] values() {
            return (Status[]) $VALUES.clone();
        }

        public int getValue() {
            return this.value;
        }
    }

    public UpdateSignalingStatusRequest(Atom atom, String str, Status status, String str2) {
        this.atom = atom;
        this.jobId = str;
        this.status = status;
        this.url = str2;
    }

    public static UpdateSignalingStatusRequest fromJSONObject(JSONObject jSONObject) throws JSONException {
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return new UpdateSignalingStatusRequest(Atom.fromJSONObject(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-66, 48, 52, 84}, new byte[]{-33, 68, 91, 57, -41, -23, -21, 126})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{65, 37, -22, -2}, new byte[]{32, 81, -123, -109, -47, 117, 58, -123})) : new JSONObject()), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-57, -90, -90, -116, ByteCompanionObject.MAX_VALUE}, new byte[]{-83, -55, -60, -59, 27, -103, -56, -117}), ""), Status.fromValue(jSONObject.optInt(lllliiiill1.llllIIIIll1(new byte[]{ByteCompanionObject.MAX_VALUE, -34, -1, -115, -47, 103}, new byte[]{12, -86, -98, -7, -92, 20, -55, -60}), 0)), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-47, -33, -1}, new byte[]{-92, -83, -109, -64, -20, -104, 86, -78}), ""));
    }

    public Atom getAtom() {
        return this.atom;
    }

    public String getJobId() {
        return this.jobId;
    }

    public Status getStatus() {
        return this.status;
    }

    public String getUrl() {
        return this.url;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-7, 114, 103, -46}, new byte[]{-104, 6, 8, -65, 20, 42, -105, 64}), this.atom.toJSONObject());
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{126, -107, 111, 10, 31}, new byte[]{20, -6, 13, 67, 123, 34, -25, 42}), this.jobId);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-43, 122, -52, -72, 18, 82}, new byte[]{-90, 14, -83, -52, 103, 33, 4, 3}), this.status.getValue());
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-44, 27, 33}, new byte[]{-95, 105, 77, 4, 103, 58, 38, -97}), this.url);
        return jSONObject;
    }
}
