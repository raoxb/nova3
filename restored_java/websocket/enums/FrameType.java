package websocket.enums;

/**
 * MALWARE ANALYSIS — Frame type enum (opcode)
 *
 * Original: lIIlIIIIlIlII1.llllIllIl1
 *
 * WebSocket frame opcodes per RFC 6455 Section 5.2.
 */
public enum FrameType {
    /** Continuation frame (opcode 0x0) */
    CONTINUOUS,
    /** Text frame (opcode 0x1) */
    TEXT,
    /** Binary frame (opcode 0x2) */
    BINARY,
    /** Ping control frame (opcode 0x9) */
    PING,
    /** Pong control frame (opcode 0xA) */
    PONG,
    /** Close control frame (opcode 0x8) */
    CLOSING
}
