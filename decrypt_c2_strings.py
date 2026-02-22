#!/usr/bin/env python3
import re, os

def xor_decrypt(cipher_bytes, key_bytes):
    result = bytearray(len(cipher_bytes))
    key_len = len(key_bytes)
    for i in range(len(cipher_bytes)):
        result[i] = (cipher_bytes[i] & 0xFF) ^ (key_bytes[i % key_len] & 0xFF)
    return result.decode('utf-8', errors='replace')

def parse_byte_array(s):
    s = s.strip().strip('{}')
    parts = []
    for p in s.split(','):
        p = p.strip()
        if not p: continue
        p = p.replace('ByteCompanionObject.MIN_VALUE', '-128')
        p = p.replace('ByteCompanionObject.MAX_VALUE', '127')
        try: parts.append(int(p))
        except ValueError: continue
    return parts

def extract_and_decrypt_from_file(filepath):
    with open(filepath, 'r') as f:
        content = f.read()
    pattern = r'(?:llllIIIIll1|lIIIIlllllIlll1)\s*\(\s*new\s+byte\s*\[\s*\]\s*\{([^}]+)\}\s*,\s*new\s+byte\s*\[\s*\]\s*\{([^}]+)\}\s*\)'
    results = []
    for match in re.finditer(pattern, content):
        cipher_str = match.group(1)
        key_str = match.group(2)
        try:
            cipher = parse_byte_array(cipher_str)
            key = parse_byte_array(key_str)
            decrypted = xor_decrypt(cipher, key)
            if all(c.isprintable() or c in '\n\r\t' for c in decrypted):
                line_num = content[:match.start()].count('\n') + 1
                results.append((line_num, decrypted))
        except Exception as e:
            line_num = content[:match.start()].count('\n') + 1
            results.append((line_num, f"<ERROR: {e}>"))
    return results

base = '/root/code/nova2/jadx_output/sources/c13/nim5/ez8/h5_proto/'
for fname in sorted(os.listdir(base)):
    if not fname.endswith('.java'): continue
    filepath = os.path.join(base, fname)
    if os.path.isdir(filepath): continue
    results = extract_and_decrypt_from_file(filepath)
    with open(filepath, 'r') as f:
        lines = len(f.readlines())
    print(f"\n{'='*70}")
    print(f"{fname} ({lines} lines, {len(results)} XOR strings)")
    print(f"{'='*70}")
    for line_num, decrypted in results:
        print(f"  L{line_num:4d} -> \"{decrypted}\"")
    if not results:
        print("  (no XOR encrypted strings)")
