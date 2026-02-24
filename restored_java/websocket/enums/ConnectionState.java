package websocket.enums;

/**
 * MALWARE ANALYSIS — Connection state enum
 *
 * Original: lIIlIIIIlIlII1.IllIIlIIII1
 *
 * WebSocket connection lifecycle states.
 */
public enum ConnectionState {
    /** Before handshake */
    NOT_YET_CONNECTED,
    /** After successful handshake */
    OPEN,
    /** Close frame sent or received */
    CLOSING,
    /** Connection fully closed */
    CLOSED
}
