package c13.nim5.ez8.h5_proto.signaling;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'CONNECTED' uses external variables
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
/* loaded from: classes.dex */
public final class ConnectionStatus {
    private static final /* synthetic */ ConnectionStatus[] $VALUES;
    public static final ConnectionStatus CONNECTED;
    public static final ConnectionStatus DISCONNECTED;
    public static final ConnectionStatus RECONNECTING;
    private final int value;

    private static /* synthetic */ ConnectionStatus[] $values() {
        return new ConnectionStatus[]{CONNECTED, RECONNECTING, DISCONNECTED};
    }

    static {
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        CONNECTED = new ConnectionStatus(lllliiiill1.llllIIIIll1(new byte[]{119, 90, -53, 98, 71, -63, -3, -88, 112}, new byte[]{52, 21, -123, 44, 2, -126, -87, -19}), 0, 0);
        RECONNECTING = new ConnectionStatus(lllliiiill1.llllIIIIll1(new byte[]{29, 73, -1, -70, 100, -19, -83, -67, 27, 69, -14, -78}, new byte[]{79, 12, -68, -11, 42, -93, -24, -2}), 1, 1);
        DISCONNECTED = new ConnectionStatus(lllliiiill1.llllIIIIll1(new byte[]{52, -102, -32, -102, 74, -13, -96, 22, 51, -121, -10, -99}, new byte[]{112, -45, -77, -39, 5, -67, -18, 83}), 2, 2);
        $VALUES = $values();
    }

    private ConnectionStatus(String str, int i, int i2) {
        this.value = i2;
    }

    public static ConnectionStatus fromValue(int i) {
        for (ConnectionStatus connectionStatus : values()) {
            if (connectionStatus.value == i) {
                return connectionStatus;
            }
        }
        return CONNECTED;
    }

    public static ConnectionStatus valueOf(String str) {
        return (ConnectionStatus) Enum.valueOf(ConnectionStatus.class, str);
    }

    public static ConnectionStatus[] values() {
        return (ConnectionStatus[]) $VALUES.clone();
    }

    public int getValue() {
        return this.value;
    }
}
