#!/usr/bin/env python3
"""
XOR String Decryptor for JADX decompiled Java sources.
Decrypts patterns like: llllIIIIll1(new byte[]{...}, new byte[]{...})
and also: lIIIIlllllIlll1(new byte[]{...}, new byte[]{...}) (returns raw bytes)
Using 8-byte repeating-key XOR: data[i] ^ key[i % len(key)]
"""

import re
import sys
import os

# Pattern to match XOR-encrypted calls in both forms:
# .llllIIIIll1(new byte[]{...}, new byte[]{...})   -> returns String
# .lIIIIlllllIlll1(new byte[]{...}, new byte[]{...}) -> returns byte[]
PATTERN = re.compile(
    r'(?:llllIIIIll1|lIIIIlllllIlll1)\s*\(\s*new\s+byte\s*\[\s*\]\s*\{([^}]*)\}\s*,\s*new\s+byte\s*\[\s*\]\s*\{([^}]*)\}\s*\)'
)

# ByteCompanionObject constants
BYTE_CONSTANTS = {
    'ByteCompanionObject.MIN_VALUE': -128,
    'ByteCompanionObject.MAX_VALUE': 127,
}

def parse_byte_array(s):
    """Parse a Java byte array initializer string into a list of ints (signed bytes)."""
    s = s.strip()
    if not s:
        return []
    tokens = [t.strip() for t in s.split(',')]
    result = []
    for t in tokens:
        t = t.strip()
        if not t:
            continue
        # Replace known constants
        for const_name, const_val in BYTE_CONSTANTS.items():
            if const_name in t:
                t = t.replace(const_name, str(const_val))
                break
        try:
            val = int(t)
            result.append(val)
        except ValueError:
            # Try to evaluate simple expressions
            try:
                val = int(eval(t))
                result.append(val)
            except:
                print(f"  WARNING: Could not parse byte value: '{t}'", file=sys.stderr)
                result.append(0)
    return result

def xor_decrypt(data_bytes, key_bytes):
    """XOR decrypt data with repeating key."""
    if not key_bytes:
        return b''
    result = bytearray()
    for i, d in enumerate(data_bytes):
        # Convert signed Java bytes to unsigned
        d_unsigned = d & 0xFF
        k_unsigned = key_bytes[i % len(key_bytes)] & 0xFF
        result.append(d_unsigned ^ k_unsigned)
    return bytes(result)

def decrypt_file(filepath):
    """Find and decrypt all XOR-encrypted strings in a Java file."""
    with open(filepath, 'r') as f:
        content = f.read()
    
    lines = content.split('\n')
    results = []
    
    # Search line by line for context, but use full content for multi-line matches
    for match in PATTERN.finditer(content):
        data_str = match.group(1)
        key_str = match.group(2)
        
        # Calculate line number
        line_num = content[:match.start()].count('\n') + 1
        
        # Get the matching text for context
        match_text = match.group(0)
        
        data_bytes = parse_byte_array(data_str)
        key_bytes = parse_byte_array(key_str)
        
        decrypted_raw = xor_decrypt(data_bytes, key_bytes)
        try:
            decrypted_str = decrypted_raw.decode('utf-8')
        except UnicodeDecodeError:
            decrypted_str = decrypted_raw.decode('utf-8', errors='replace')
        
        results.append({
            'line': line_num,
            'decrypted': decrypted_str,
            'data_len': len(data_bytes),
            'key_len': len(key_bytes),
        })
    
    return results

def main():
    if len(sys.argv) < 2:
        print(f"Usage: {sys.argv[0]} <java_file> [<java_file> ...]")
        sys.exit(1)
    
    for filepath in sys.argv[1:]:
        if not os.path.exists(filepath):
            print(f"ERROR: File not found: {filepath}")
            continue
        
        basename = os.path.basename(filepath)
        dirname = os.path.basename(os.path.dirname(filepath))
        print(f"\n{'='*70}")
        print(f"File: {dirname}/{basename}")
        print(f"Path: {filepath}")
        print(f"{'='*70}")
        
        results = decrypt_file(filepath)
        
        if not results:
            print("  No encrypted strings found.")
        else:
            print(f"  Found {len(results)} encrypted string(s):\n")
            for i, r in enumerate(results, 1):
                print(f"  [{i}] Line {r['line']}: \"{r['decrypted']}\"")
            print()

if __name__ == '__main__':
    main()
