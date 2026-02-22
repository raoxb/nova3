package c13.nim5.ez8.h5_proto.signaling;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import kotlin.jvm.internal.ByteCompanionObject;
import lIllIIIlIl1.IlIllll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SignalingResponse implements IlIllll1 {
    private final Content content;
    private final Error error;

    public static abstract class Content implements IlIllll1 {

        public static class DoneMessage extends Content {
            private final Done done;

            public DoneMessage(Done done) {
                this.done = done;
            }

            public Done getDone() {
                return this.done;
            }

            @Override // lIllIIIlIl1.IlIllll1
            public JSONObject toJSONObject() throws JSONException {
                JSONObject jSONObject = new JSONObject();
                llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-57, 99, 89, 10, 95, -111, 68, -85, -48, 117, 71, 27}, new byte[]{-92, 12, 55, 126, 58, -1, 48, -12}), lllliiiill1.llllIIIIll1(new byte[]{-79, 85, -35, -42}, new byte[]{-43, 58, -77, -77, -91, -70, 87, 13}));
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{0, -79, -69, -60}, new byte[]{100, -34, -43, -95, -45, -38, -68, 122}), this.done.toJSONObject());
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
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-7, 92, -16, -25, 56, -115, 29, -50, -18, 74, -18, -10}, new byte[]{-102, 51, -98, -109, 93, -29, 105, -111}), lllliiiill1.llllIIIIll1(new byte[]{94, -27, -1, -103, 32, 94, -77, 33, 94, -30, -5, -78, 38}, new byte[]{55, -122, -102, -58, 67, 63, -35, 69}));
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-104, -117, 53, -109, -94, -70, 16, 30, -104, -116, 49, -72, -92}, new byte[]{-15, -24, 80, -52, -63, -37, 126, 122}), this.iceCandidate.toJSONObject());
                return jSONObject;
            }
        }

        public static class PongMessage extends Content {
            private final Pong pong;

            public PongMessage(Pong pong) {
                this.pong = pong;
            }

            public Pong getPong() {
                return this.pong;
            }

            @Override // lIllIIIlIl1.IlIllll1
            public JSONObject toJSONObject() throws JSONException {
                JSONObject jSONObject = new JSONObject();
                llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{5, 46, 58, 48, -82, -54, 58, -9, 18, 56, 36, 33}, new byte[]{102, 65, 84, 68, -53, -92, 78, -88}), lllliiiill1.llllIIIIll1(new byte[]{-17, -28, 11, 3}, new byte[]{-97, -117, 101, 100, 108, 54, -81, -60}));
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{36, -44, -25, -4}, new byte[]{84, -69, -119, -101, 0, 123, 109, 98}), this.pong.toJSONObject());
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
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-1, 5, -62, -54, 124, -17, -122, -23, -24, 19, -36, -37}, new byte[]{-100, 106, -84, -66, 25, -127, -14, -74}), lllliiiill1.llllIIIIll1(new byte[]{35, 105, -44, -14, 8, -78, -102, 125, 53, ByteCompanionObject.MAX_VALUE}, new byte[]{80, 13, -92, -83, 105, -36, -23, 10}));
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-14, 101, ByteCompanionObject.MIN_VALUE, -33, 67, 40, -93, -26, -28, 115}, new byte[]{-127, 1, -16, ByteCompanionObject.MIN_VALUE, 34, 70, -48, -111}), this.sdpAnswer.toJSONObject());
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
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{123, -16, -40, -101, -91, -48, 75, -44, 108, -26, -58, -118}, new byte[]{24, -97, -74, -17, -64, -66, 63, -117}), lllliiiill1.llllIIIIll1(new byte[]{38, 58, 13, 115, 103, 47, 52, -88, 39}, new byte[]{85, 94, 125, 44, 8, 73, 82, -51}));
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{0, -83, -9, -87, -3, -16, -41, -22, 1}, new byte[]{115, -55, -121, -10, -110, -106, -79, -113}), this.sdpOffer.toJSONObject());
                return jSONObject;
            }
        }

        public static class Status extends Content {
            private final ConnectionStatus status;

            public Status(ConnectionStatus connectionStatus) {
                this.status = connectionStatus;
            }

            public ConnectionStatus getStatus() {
                return this.status;
            }

            @Override // lIllIIIlIl1.IlIllll1
            public JSONObject toJSONObject() throws JSONException {
                JSONObject jSONObject = new JSONObject();
                llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-100, 55, 82, 92, -53, 125, 67, 68, -117, 33, 76, 77}, new byte[]{-1, 88, 60, 40, -82, 19, 55, 27}), lllliiiill1.llllIIIIll1(new byte[]{-13, -77, -73, -118, ByteCompanionObject.MAX_VALUE, 114}, new byte[]{ByteCompanionObject.MIN_VALUE, -57, -42, -2, 10, 1, 97, 65}));
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-29, -116, 103, 67, 113, -9}, new byte[]{-112, -8, 6, 55, 4, -124, 90, -82}), this.status.getValue());
                return jSONObject;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public static Content fromJSONObject(JSONObject jSONObject) throws JSONException {
            char c;
            llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
            String optString = jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{41, 40, 10, -98, 15, -71, 35, -91, 62, 62, 20, -113}, new byte[]{74, 71, 100, -22, 106, -41, 87, -6}), "");
            switch (optString.hashCode()) {
                case -892481550:
                    if (optString.equals(lllliiiill1.llllIIIIll1(new byte[]{83, 16, 113, -7, 43, -8}, new byte[]{32, 100, 16, -115, 94, -117, 114, 79}))) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -69874084:
                    if (optString.equals(lllliiiill1.llllIIIIll1(new byte[]{-14, -75, 94, 43, 32, 71, -65, 110, -13}, new byte[]{-127, -47, 46, 116, 79, 33, -39, 11}))) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 3089282:
                    if (optString.equals(lllliiiill1.llllIIIIll1(new byte[]{25, 89, 13, 3}, new byte[]{125, 54, 99, 102, -6, -55, 86, -110}))) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 3446776:
                    if (optString.equals(lllliiiill1.llllIIIIll1(new byte[]{78, -111, 45, 72}, new byte[]{62, -2, 67, 47, 8, 120, -92, 123}))) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 1362998479:
                    if (optString.equals(lllliiiill1.llllIIIIll1(new byte[]{-59, 79, 83, -24, 107, -3, -98, 37, -59, 72, 87, -61, 109}, new byte[]{-84, 44, 54, -73, 8, -100, -16, 65}))) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1735855038:
                    if (optString.equals(lllliiiill1.llllIIIIll1(new byte[]{48, -45, 37, -72, 93, 95, 24, -66, 38, -59}, new byte[]{67, -73, 85, -25, 60, 49, 107, -55}))) {
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
                return new SdpOffer(SDPOffer.fromJSONObject(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-118, -77, -75, -78, 65, -116, 126, 85, -117}, new byte[]{-7, -41, -59, -19, 46, -22, 24, 48})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{49, 94, -3, -124, 81, 61, -84, -48, 48}, new byte[]{66, 58, -115, -37, 62, 91, -54, -75})) : new JSONObject()));
            }
            if (c == 1) {
                return new SdpAnswer(SDPAnswer.fromJSONObject(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{50, 101, 110, 57, -71, 75, 104, 36, 36, 115}, new byte[]{65, 1, 30, 102, -40, 37, 27, 83})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{122, 11, 12, -5, 32, -6, 12, -109, 108, 29}, new byte[]{9, 111, 124, -92, 65, -108, ByteCompanionObject.MAX_VALUE, -28})) : new JSONObject()));
            }
            if (c == 2) {
                return new IceCandidate(ICECandidate.fromJSONObject(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{21, -8, -103, -17, 23, -86, 96, 56, 21, -1, -99, -60, 17}, new byte[]{124, -101, -4, -80, 116, -53, 14, 92})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-69, 86, -19, -102, 112, -68, 33, -86, -69, 81, -23, -79, 118}, new byte[]{-46, 53, -120, -59, 19, -35, 79, -50})) : new JSONObject()));
            }
            if (c == 3) {
                return new Status(ConnectionStatus.fromValue(jSONObject.optInt(lllliiiill1.llllIIIIll1(new byte[]{83, -109, -27, 71, -47, -5}, new byte[]{32, -25, -124, 51, -92, -120, -99, -56}), 0)));
            }
            if (c == 4) {
                return new PongMessage(Pong.fromJSONObject(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{47, 117, 60, -104}, new byte[]{95, 26, 82, -1, -49, 101, 90, -92})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{83, 89, -20, 102}, new byte[]{35, 54, -126, 1, -69, 51, 115, -13})) : new JSONObject()));
            }
            if (c == 5) {
                return new DoneMessage(Done.fromJSONObject(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{38, 63, 122, -38}, new byte[]{66, 80, 20, -65, 88, 115, 0, 86})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-31, 22, -18, -104}, new byte[]{-123, 121, ByteCompanionObject.MIN_VALUE, -3, -110, -28, -67, -49})) : new JSONObject()));
            }
            throw new IllegalArgumentException(lllliiiill1.llllIIIIll1(new byte[]{-80, -56, 94, -60, 33, 77, -20, 116, -122, -55, 91, -34, 43, 84, -10, 116, -111, -33, 69, -49, 116, 26}, new byte[]{-27, -90, 53, -86, 78, 58, -126, 84}).concat(optString));
        }
    }

    public SignalingResponse(Content content, Error error) {
        this.content = content;
        this.error = error;
    }

    public static SignalingResponse fromJSONObject(JSONObject jSONObject) throws JSONException {
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        Error error = null;
        Content fromJSONObject = (!jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{75, 106, -95, -102, 63, 92, -70}, new byte[]{40, 5, -49, -18, 90, 50, -50, -56})) || jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{15, -87, 74, 64, -90, -126, -10}, new byte[]{108, -58, 36, 52, -61, -20, -126, 4}))) ? null : Content.fromJSONObject(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{101, -29, 112, -54, -126, 12, -14}, new byte[]{6, -116, 30, -66, -25, 98, -122, 18})));
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{13, 26, 102, 62, 37}, new byte[]{104, 104, 20, 81, 87, 51, -107, 79})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{100, 109, -77, -39, -48}, new byte[]{1, 31, -63, -74, -94, 22, 44, -43}))) {
            error = Error.fromJSONObject(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{7, -91, 28, -47, 13}, new byte[]{98, -41, 110, -66, ByteCompanionObject.MAX_VALUE, 38, 86, -35})));
        }
        return new SignalingResponse(fromJSONObject, error);
    }

    public Content getContent() {
        return this.content;
    }

    public Error getError() {
        return this.error;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.content != null) {
            jSONObject.put(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-58, -119, 9, 64, 28, 45, 86}, new byte[]{-91, -26, 103, 52, 121, 67, 34, -42}), this.content.toJSONObject());
        }
        if (this.error != null) {
            jSONObject.put(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-84, 3, -37, 107, -41}, new byte[]{-55, 113, -87, 4, -91, 4, -70, 7}), this.error.toJSONObject());
        }
        return jSONObject;
    }
}
