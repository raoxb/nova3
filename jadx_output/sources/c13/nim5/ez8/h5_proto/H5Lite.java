package c13.nim5.ez8.h5_proto;

import IlIlllIIlI1.IllIIlIIII1;

/* loaded from: classes.dex */
public class H5Lite {
    public static final H5Lite INSTANCE = new H5Lite(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-55, 54, -56, -81, -114, -50, 93, 91, -63, 51, -57, -76}, new byte[]{-83, 90, -92, -33, -23, -86, 115, 56}));
    private final HttpGatewayClient httpClient;

    public H5Lite(String str) {
        this.httpClient = new HttpGatewayClient(str);
    }

    public CommonResponse updateEvent(UpdateEventRequest updateEventRequest) throws Exception {
        return this.httpClient.updateEvent(updateEventRequest);
    }

    public CommonResponse updateLog(UpdateLogRequest updateLogRequest) throws Exception {
        return this.httpClient.updateLog(updateLogRequest);
    }

    public H5Lite(String str, int i, boolean z) {
        this.httpClient = new HttpGatewayClient(str, i, z);
    }
}
