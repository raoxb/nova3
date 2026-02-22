package signaling;

/**
 * Enumerates the possible connection states for the signaling channel.
 * Used to track and report the current health of the WebRTC or
 * signaling-layer connection to the remote peer.
 */
public enum ConnectionStatus {

    /** The connection is fully established and operational. */
    CONNECTED(0),

    /** The connection was lost and a reconnection attempt is in progress. */
    RECONNECTING(1),

    /** The connection has been terminated and is no longer active. */
    DISCONNECTED(2);

    private final int value;

    ConnectionStatus(int value) {
        this.value = value;
    }

    /**
     * Returns the integer value associated with this status.
     *
     * @return the numeric status code
     */
    public int getValue() {
        return value;
    }

    /**
     * Resolves a ConnectionStatus from its integer value.
     *
     * @param value the numeric status code
     * @return the matching ConnectionStatus, or {@link #DISCONNECTED} if unknown
     */
    public static ConnectionStatus fromValue(int value) {
        for (ConnectionStatus status : values()) {
            if (status.value == value) {
                return status;
            }
        }
        return DISCONNECTED;
    }
}
