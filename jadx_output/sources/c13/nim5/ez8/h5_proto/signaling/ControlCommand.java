package c13.nim5.ez8.h5_proto.signaling;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import kotlin.jvm.internal.ByteCompanionObject;
import lIllIIIlIl1.IlIllll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class ControlCommand implements IlIllll1 {

    public static class Click extends ControlCommand {
        private final ClickEvent click;

        public Click(ClickEvent clickEvent) {
            this.click = clickEvent;
        }

        public ClickEvent getClick() {
            return this.click;
        }

        @Override // lIllIIIlIl1.IlIllll1
        public JSONObject toJSONObject() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
            jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-68, 1, -68, 92, -18, 18, 80, -90, -85, 23, -95, 84}, new byte[]{-33, 110, -47, 49, -113, 124, 52, -7}), lllliiiill1.llllIIIIll1(new byte[]{49, 0, 38, -80, -44}, new byte[]{82, 108, 79, -45, -65, 68, 41, -120}));
            jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{42, 31, -103, 74, 106}, new byte[]{73, 115, -16, 41, 1, -4, -17, -12}), this.click.toJSONObject());
            return jSONObject;
        }
    }

    public static class Input extends ControlCommand {
        private final TextInput input;

        public Input(TextInput textInput) {
            this.input = textInput;
        }

        public TextInput getInput() {
            return this.input;
        }

        @Override // lIllIIIlIl1.IlIllll1
        public JSONObject toJSONObject() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
            jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{35, -3, -95, 103, -124, -76, -71, -69, 52, -21, -68, 111}, new byte[]{64, -110, -52, 10, -27, -38, -35, -28}), lllliiiill1.llllIIIIll1(new byte[]{2, -85, 31, -96, -56}, new byte[]{107, -59, 111, -43, -68, -58, -79, 120}));
            jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-122, -100, -4, -110, -96}, new byte[]{-17, -14, -116, -25, -44, -125, -25, 83}), this.input.toJSONObject());
            return jSONObject;
        }
    }

    public static class Scroll extends ControlCommand {
        private final ScrollEvent scroll;

        public Scroll(ScrollEvent scrollEvent) {
            this.scroll = scrollEvent;
        }

        public ScrollEvent getScroll() {
            return this.scroll;
        }

        @Override // lIllIIIlIl1.IlIllll1
        public JSONObject toJSONObject() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
            jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{103, -89, 17, -122, 19, -6, 14, -110, 112, -79, 12, -114}, new byte[]{4, -56, 124, -21, 114, -108, 106, -51}), lllliiiill1.llllIIIIll1(new byte[]{-28, -67, -87, -17, -105, -111}, new byte[]{-105, -34, -37, ByteCompanionObject.MIN_VALUE, -5, -3, 87, 7}));
            jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{57, 2, 87, 56, -67, -59}, new byte[]{74, 97, 37, 87, -47, -87, 99, 44}), this.scroll.toJSONObject());
            return jSONObject;
        }
    }

    public static ControlCommand fromJSONObject(JSONObject jSONObject) throws JSONException {
        char c;
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        String optString = jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{83, 1, -86, -48, 98, 92, -50, 25, 68, 23, -73, -40}, new byte[]{48, 110, -57, -67, 3, 50, -86, 70}), "");
        int hashCode = optString.hashCode();
        if (hashCode == -907680051) {
            if (optString.equals(lllliiiill1.llllIIIIll1(new byte[]{110, 52, 83, 87, 47, -66}, new byte[]{29, 87, 33, 56, 67, -46, -62, -8}))) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode != 94750088) {
            if (hashCode == 100358090 && optString.equals(lllliiiill1.llllIIIIll1(new byte[]{-25, -69, 71, 45, -115}, new byte[]{-114, -43, 55, 88, -7, 53, -21, -127}))) {
                c = 2;
            }
            c = 65535;
        } else {
            if (optString.equals(lllliiiill1.llllIIIIll1(new byte[]{-83, -9, -100, -15, 53}, new byte[]{-50, -101, -11, -110, 94, 44, -79, 62}))) {
                c = 0;
            }
            c = 65535;
        }
        if (c == 0) {
            return new Click(ClickEvent.fromJSONObject(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-62, -115, -121, -40, 108}, new byte[]{-95, -31, -18, -69, 7, -102, -18, 116})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-107, 38, 82, 2, -31}, new byte[]{-10, 74, 59, 97, -118, 27, 76, -63})) : new JSONObject()));
        }
        if (c == 1) {
            return new Scroll(ScrollEvent.fromJSONObject(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-94, -54, -79, 30, 38, -12}, new byte[]{-47, -87, -61, 113, 74, -104, 62, 42})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-124, -46, -115, -50, 52, 16}, new byte[]{-9, -79, -1, -95, 88, 124, 96, -73})) : new JSONObject()));
        }
        if (c == 2) {
            return new Input(TextInput.fromJSONObject(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-10, 42, 47, 55, -39}, new byte[]{-97, 68, 95, 66, -83, 120, -85, 1})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-84, 108, -95, -99, 88}, new byte[]{-59, 2, -47, -24, 44, -31, 111, -96})) : new JSONObject()));
        }
        throw new IllegalArgumentException(lllliiiill1.llllIIIIll1(new byte[]{-70, -75, 15, -33, 28, 25, 38, 37, -116, -76, 9, -36, 18, 0, 44, 37, -101, -94, 20, -44, 73, 78}, new byte[]{-17, -37, 100, -79, 115, 110, 72, 5}).concat(optString));
    }
}
