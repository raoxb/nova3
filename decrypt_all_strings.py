#!/usr/bin/env python3
"""Batch XOR decryption for all obfuscated strings in the signaling/WebRTC files."""

def xor_decrypt(cipher_bytes, key_bytes):
    result = bytearray(len(cipher_bytes))
    key_len = len(key_bytes)
    for i in range(len(cipher_bytes)):
        result[i] = (cipher_bytes[i] & 0xFF) ^ (key_bytes[i % key_len] & 0xFF)
    return result.decode('utf-8', errors='replace')

def to_signed(b):
    return b if b < 128 else b - 256

def parse_byte_array(s):
    """Parse Java byte array literal like {-23, 123, -35}"""
    s = s.strip().strip('{}')
    parts = []
    for p in s.split(','):
        p = p.strip()
        if not p:
            continue
        p = p.replace('ByteCompanionObject.MIN_VALUE', '-128')
        p = p.replace('ByteCompanionObject.MAX_VALUE', '127')
        try:
            parts.append(int(p))
        except ValueError:
            continue
    return parts

import re, sys, os

def extract_and_decrypt_from_file(filepath):
    """Extract all XOR-encrypted string calls from a file and decrypt them."""
    with open(filepath, 'r') as f:
        content = f.read()

    # Pattern: llllIIIIll1(new byte[]{...}, new byte[]{...})
    # Also: lIIIIlllllIlll1(new byte[]{...}, new byte[]{...})
    pattern = r'(?:llllIIIIll1|lIIIIlllllIlll1)\s*\(\s*new\s+byte\s*\[\s*\]\s*\{([^}]+)\}\s*,\s*new\s+byte\s*\[\s*\]\s*\{([^}]+)\}\s*\)'

    results = []
    for match in re.finditer(pattern, content):
        cipher_str = match.group(1)
        key_str = match.group(2)
        try:
            cipher = parse_byte_array(cipher_str)
            key = parse_byte_array(key_str)
            decrypted = xor_decrypt(cipher, key)
            # Filter out non-printable strings
            if all(c.isprintable() or c in '\n\r\t' for c in decrypted):
                results.append((match.start(), decrypted, match.group(0)[:80]))
        except Exception as e:
            results.append((match.start(), f"<ERROR: {e}>", match.group(0)[:80]))

    return results

# Process signaling files
signaling_dir = '/root/code/nova2/jadx_output/sources/c13/nim5/ez8/h5_proto/signaling/'
webrtc_dir = '/root/code/nova2/jadx_output/sources/llIIIIlIlllIII1/'

print("=" * 80)
print("SIGNALING PROTOCOL STRING DECRYPTION")
print("=" * 80)

for fname in sorted(os.listdir(signaling_dir)):
    if not fname.endswith('.java'):
        continue
    filepath = os.path.join(signaling_dir, fname)
    results = extract_and_decrypt_from_file(filepath)
    if results:
        print(f"\n--- {fname} ---")
        for pos, decrypted, raw in results:
            print(f"  [{pos:6d}] → \"{decrypted}\"")

print("\n" + "=" * 80)
print("WEBRTC CONTROLLER STRING DECRYPTION")
print("=" * 80)

for fname in sorted(os.listdir(webrtc_dir)):
    if not fname.endswith('.java'):
        continue
    filepath = os.path.join(webrtc_dir, fname)
    results = extract_and_decrypt_from_file(filepath)
    if results:
        print(f"\n--- {fname} ---")
        for pos, decrypted, raw in results:
            print(f"  [{pos:6d}] → \"{decrypted}\"")

# Also process the WebSocket base class
ws_file = '/root/code/nova2/jadx_output/sources/llIIllIl1/llllIIIIll1.java'
if os.path.exists(ws_file):
    print(f"\n--- WebSocket base: llIIllIl1/llllIIIIll1.java ---")
    results = extract_and_decrypt_from_file(ws_file)
    for pos, decrypted, raw in results:
        print(f"  [{pos:6d}] → \"{decrypted}\"")
