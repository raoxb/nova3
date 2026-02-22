package c13.nim5.ez8.h5_proto.signaling;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import c13.nim5.ez8.h5_proto.Atom;
import kotlin.jvm.internal.ByteCompanionObject;
import lIllIIIlIl1.IlIllll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SignalingRequest implements IlIllll1 {
    private final Atom atom;
    private final Content content;

    public static abstract class Content implements IlIllll1 {

        public static class Control extends Content {
            private final ControlCommand control;

            public Control(ControlCommand controlCommand) {
                this.control = controlCommand;
            }

            public ControlCommand getControl() {
                return this.control;
            }

            @Override // lIllIIIlIl1.IlIllll1
            public JSONObject toJSONObject() throws JSONException {
                JSONObject jSONObject = new JSONObject();
                llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-15, -106, 37, -99, -46, 12, 51, -60, -26, ByteCompanionObject.MIN_VALUE, 59, -116}, new byte[]{-110, -7, 75, -23, -73, 98, 71, -101}), lllliiiill1.llllIIIIll1(new byte[]{111, -49, 39, -104, 8, -109, 55}, new byte[]{12, -96, 73, -20, 122, -4, 91, -86}));
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{82, -6, 92, -58, -110, 41, 68}, new byte[]{49, -107, 50, -78, -32, 70, 40, -65}), this.control.toJSONObject());
                return jSONObject;
            }
        }

        public static class IceCandidate extends Content {
            private final ICECandidate iceCandidate;

            public IceCandidate(ICECandidate iCECandidate) {
                this.iceCandidate = iCECandidate;
            }

            public ICECandidate getIceCandidate() {
                return this.iceCandidate;
            }

            @Override // lIllIIIlIl1.IlIllll1
            public JSONObject toJSONObject() throws JSONException {
                JSONObject jSONObject = new JSONObject();
                llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{112, -120, 121, 109, -61, 80, -125, 77, 103, -98, 103, 124}, new byte[]{19, -25, 23, 25, -90, 62, -9, 18}), lllliiiill1.llllIIIIll1(new byte[]{37, 39, 93, -60, -28, -83, 61, 26, 37, 32, 89, -17, -30}, new byte[]{76, 68, 56, -101, -121, -52, 83, 126}));
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{103, 52, 78, -61, 16, 123, 83, 76, 103, 51, 74, -24, 22}, new byte[]{14, 87, 43, -100, 115, 26, 61, 40}), this.iceCandidate.toJSONObject());
                return jSONObject;
            }
        }

        public static class PingMessage extends Content {
            private final Ping ping;

            public PingMessage(Ping ping) {
                this.ping = ping;
            }

            public Ping getPing() {
                return this.ping;
            }

            @Override // lIllIIIlIl1.IlIllll1
            public JSONObject toJSONObject() throws JSONException {
                JSONObject jSONObject = new JSONObject();
                llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{16, 22, -25, 117, -85, 104, 1, -122, 7, 0, -7, 100}, new byte[]{115, 121, -119, 1, -50, 6, 117, -39}), lllliiiill1.llllIIIIll1(new byte[]{99, -69, -66, -18}, new byte[]{19, -46, -48, -119, -105, 60, 99, 24}));
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-96, 71, -21, 102}, new byte[]{-48, 46, -123, 1, 34, 47, -48, -55}), this.ping.toJSONObject());
                return jSONObject;
            }
        }

        public static class SdpAnswer extends Content {
            private final SDPAnswer sdpAnswer;

            public SdpAnswer(SDPAnswer sDPAnswer) {
                this.sdpAnswer = sDPAnswer;
            }

            public SDPAnswer getSdpAnswer() {
                return this.sdpAnswer;
            }

            @Override // lIllIIIlIl1.IlIllll1
            public JSONObject toJSONObject() throws JSONException {
                JSONObject jSONObject = new JSONObject();
                llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-36, -126, -59, -29, 67, -3, -116, -87, -53, -108, -37, -14}, new byte[]{-65, -19, -85, -105, 38, -109, -8, -10}), lllliiiill1.llllIIIIll1(new byte[]{-50, ByteCompanionObject.MAX_VALUE, -88, -21, 75, -127, -67, -53, -40, 105}, new byte[]{-67, 27, -40, -76, 42, -17, -50, -68}));
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-117, 86, 40, 116, 24, -67, -27, 74, -99, 64}, new byte[]{-8, 50, 88, 43, 121, -45, -106, 61}), this.sdpAnswer.toJSONObject());
                return jSONObject;
            }
        }

        public static class SdpOffer extends Content {
            private final SDPOffer sdpOffer;

            public SdpOffer(SDPOffer sDPOffer) {
                this.sdpOffer = sDPOffer;
            }

            public SDPOffer getSdpOffer() {
                return this.sdpOffer;
            }

            @Override // lIllIIIlIl1.IlIllll1
            public JSONObject toJSONObject() throws JSONException {
                JSONObject jSONObject = new JSONObject();
                llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-98, -57, 8, 2, 107, -109, 17, -101, -119, -47, 22, 19}, new byte[]{-3, -88, 102, 118, 14, -3, 101, -60}), lllliiiill1.llllIIIIll1(new byte[]{-114, 89, 1, 79, 40, -64, 67, 20, -113}, new byte[]{-3, 61, 113, 16, 71, -90, 37, 113}));
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-31, -39, -27, ByteCompanionObject.MIN_VALUE, -101, 84, 60, 56, -32}, new byte[]{-110, -67, -107, -33, -12, 50, 90, 93}), this.sdpOffer.toJSONObject());
                return jSONObject;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public static Content fromJSONObject(JSONObject jSONObject) throws JSONException {
            char c;
            byte[] bArr = {125, -79, -106, -85, 109, 98, ByteCompanionObject.MIN_VALUE, -18};
            llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
            String optString = jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{30, -34, -8, -33, 8, 12, -12, -79, 9, -56, -26, -50}, bArr), "");
            switch (optString.hashCode()) {
                case -69874084:
                    if (optString.equals(lllliiiill1.llllIIIIll1(new byte[]{121, 56, 79, 75, 51, -23, 109, -42, 120}, new byte[]{10, 92, 63, 20, 92, -113, 11, -77}))) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 3441010:
                    if (optString.equals(lllliiiill1.llllIIIIll1(new byte[]{115, 64, 102, 16}, new byte[]{3, 41, 8, 119, -62, 36, -124, 115}))) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 951543133:
                    if (optString.equals(lllliiiill1.llllIIIIll1(new byte[]{-77, 27, 93, 30, 107, -53, -98}, new byte[]{-48, 116, 51, 106, 25, -92, -14, 51}))) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1362998479:
                    if (optString.equals(lllliiiill1.llllIIIIll1(new byte[]{109, -15, 75, 91, 10, -41, 45, 56, 109, -10, 79, 112, 12}, new byte[]{4, -110, 46, 4, 105, -74, 67, 92}))) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1735855038:
                    if (optString.equals(lllliiiill1.llllIIIIll1(new byte[]{22, 116, -61, 95, -122, 71, 58, -70, 0, 98}, new byte[]{101, 16, -77, 0, -25, 41, 73, -51}))) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                return new SdpOffer(SDPOffer.fromJSONObject(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-29, 110, 3, -38, -57, 83, 31, -77, -30}, new byte[]{-112, 10, 115, -123, -88, 53, 121, -42})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{123, -85, -53, -117, -45, 35, 0, -66, 122}, new byte[]{8, -49, -69, -44, -68, 69, 102, -37})) : new JSONObject()));
            }
            if (c == 1) {
                return new SdpAnswer(SDPAnswer.fromJSONObject(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-42, -111, -95, -100, 16, -48, 93, -44, -64, -121}, new byte[]{-91, -11, -47, -61, 113, -66, 46, -93})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-8, -5, -89, 32, -60, 86, 10, 62, -18, -19}, new byte[]{-117, -97, -41, ByteCompanionObject.MAX_VALUE, -91, 56, 121, 73})) : new JSONObject()));
            }
            if (c == 2) {
                return new IceCandidate(ICECandidate.fromJSONObject(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{65, -111, 42, -62, -52, -89, 91, -54, 65, -106, 46, -23, -54}, new byte[]{40, -14, 79, -99, -81, -58, 53, -82})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-68, 112, -121, -61, 81, 1, 70, -62, -68, 119, -125, -24, 87}, new byte[]{-43, 19, -30, -100, 50, 96, 40, -90})) : new JSONObject()));
            }
            if (c == 3) {
                return new Control(ControlCommand.fromJSONObject(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-6, -53, 111, -24, 81, -78, 2}, new byte[]{-103, -92, 1, -100, 35, -35, 110, -83})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{50, -39, 36, -117, -68, -4, -28}, new byte[]{81, -74, 74, -1, -50, -109, -120, -14})) : new JSONObject()));
            }
            if (c == 4) {
                return new PingMessage(Ping.fromJSONObject(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{33, -62, 62, 57}, new byte[]{81, -85, 80, 94, -77, 85, -125, 14})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-94, -28, -31, 49}, new byte[]{-46, -115, -113, 86, 21, -114, 121, 63})) : new JSONObject()));
            }
            throw new IllegalArgumentException(lllliiiill1.llllIIIIll1(new byte[]{-109, -5, -50, 75, 1, -122, -78, 36, -91, -6, -53, 81, 11, -97, -88, 36, -78, -20, -43, 64, 84, -47}, new byte[]{-58, -107, -91, 37, 110, -15, -36, 4}).concat(optString));
        }
    }

    public SignalingRequest(Content content, Atom atom) {
        this.content = content;
        this.atom = atom;
    }

    public static SignalingRequest fromJSONObject(JSONObject jSONObject) throws JSONException {
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return new SignalingRequest(Content.fromJSONObject(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{113, -119, 10, 44, 97, 49, -126}, new byte[]{18, -26, 100, 88, 4, 95, -10, -52})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{61, -53, -80, 100, 25, -4, -66}, new byte[]{94, -92, -34, 16, 124, -110, -54, -32})) : new JSONObject()), Atom.fromJSONObject(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-50, -125, 58, -126}, new byte[]{-81, -9, 85, -17, 105, 4, 22, 35})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{126, 104, 65, 55}, new byte[]{31, 28, 46, 90, 76, 121, -112, -124})) : new JSONObject()));
    }

    public Atom getAtom() {
        return this.atom;
    }

    public Content getContent() {
        return this.content;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{67, -32, -85, 98, -6, 28, -102}, new byte[]{32, -113, -59, 22, -97, 114, -18, 96}), this.content.toJSONObject());
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{84, 84, -79, 72}, new byte[]{53, 32, -34, 37, 81, 91, -33, 41}), this.atom.toJSONObject());
        return jSONObject;
    }
}
