package core;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;

/**
 * MALWARE ANALYSIS — UTF-8/ASCII string encoding utilities
 *
 * Original: IlIIIlIlIlIII1.llllIllIl1
 *
 * Utility class for string encoding/decoding with strict UTF-8 validation.
 * Used by the WebSocket library for text frame validation.
 *
 * Methods:
 *   - stringToUtf8Bytes(String):       encode string to UTF-8 bytes
 *   - stringToAsciiBytes(String):      encode string to ASCII bytes
 *   - utf8BytesToString(byte[]):       decode UTF-8 bytes (throws on invalid)
 *   - utf8BytesToString(ByteBuffer):   decode UTF-8 ByteBuffer (throws on invalid)
 *   - asciiBytesToString(byte[]):      decode ASCII bytes
 *   - asciiBytesToString(byte[], int, int): decode ASCII bytes with offset
 *   - isValidUtf8(ByteBuffer):         validate UTF-8 encoding
 *   - isValidUtf8(ByteBuffer, int):    validate from offset
 *
 * The UTF-8 validation uses a DFA-based state machine (f131lIIIIlllllIlll1)
 * for fast validation without full decoding.
 */
public class StringCodec {

    /** CodingErrorAction for strict UTF-8 decoding. */
    public static final CodingErrorAction STRICT = CodingErrorAction.REPORT;

    /** UTF-8 validation state machine table. Original: f131lIIIIlllllIlll1 */
    public static final int[] UTF8_DFA = {
        0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
        1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,
        7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,
        8,8,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
        10,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,11,6,6,6,5,8,8,8,8,8,8,8,8,8,8,8,
        // Transition table follows...
        0,1,2,3,5,8,7,1,1,1,4,6,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
        1,0,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,2,1,1,1,1,1,1,
        1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,
        1,1,1,1,1,1,1,3,1,3,1,1,1,1,1,1,1,3,1,1,1,1,1,3,1,3,1,1,1,1,1,1,
        1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1
    };

    /** Encode string to UTF-8 bytes. Original: lIIIIlllllIlll1(String) */
    public static byte[] stringToUtf8Bytes(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }

    /** Encode string to ASCII bytes. Original: llllIIIIll1(String) */
    public static byte[] stringToAsciiBytes(String str) {
        return str.getBytes(StandardCharsets.US_ASCII);
    }

    /** Decode UTF-8 bytes to string (throws on invalid). Original: lIIIIlllllIlll1(byte[]) */
    public static String utf8BytesToString(byte[] data) throws Exception {
        return utf8BytesToString(ByteBuffer.wrap(data));
    }

    /** Decode UTF-8 ByteBuffer to string. Original: lIIIIlllllIlll1(ByteBuffer) */
    public static String utf8BytesToString(ByteBuffer buffer) throws Exception {
        CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
        decoder.onMalformedInput(STRICT);
        decoder.onUnmappableCharacter(STRICT);
        try {
            buffer.mark();
            String result = decoder.decode(buffer).toString();
            buffer.reset();
            return result;
        } catch (CharacterCodingException e) {
            throw new Exception("Invalid UTF-8", e);
        }
    }

    /** Decode ASCII bytes to string. Original: llllIIIIll1(byte[]) */
    public static String asciiBytesToString(byte[] data) {
        return new String(data, 0, data.length, StandardCharsets.US_ASCII);
    }

    /** Decode ASCII bytes to string with offset. Original: llllIIIIll1(byte[], int, int) */
    public static String asciiBytesToString(byte[] data, int offset, int length) {
        return new String(data, offset, length, StandardCharsets.US_ASCII);
    }

    /**
     * Validate UTF-8 encoding using DFA.
     * Original: llllIIIIll1(ByteBuffer) -> boolean
     */
    public static boolean isValidUtf8(ByteBuffer buffer) {
        return isValidUtf8(buffer, 0);
    }

    /**
     * Validate UTF-8 encoding from offset using DFA.
     * Original: llllIIIIll1(ByteBuffer, int) -> boolean
     */
    public static boolean isValidUtf8(ByteBuffer buffer, int start) {
        int remaining = buffer.remaining();
        if (remaining < start) return false;
        int state = 0;
        for (int i = start; i < remaining; i++) {
            state = UTF8_DFA[(state << 4) + 256 + UTF8_DFA[buffer.get(i) & 0xFF]];
            if (state == 1) return false;
        }
        return true;
    }
}
