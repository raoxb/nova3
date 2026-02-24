package core.exceptions;

import java.io.IOException;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: IllIlIllll1/IIlIllIIll1
 *
 * Wraps an IOException that occurred on a specific WebSocket channel,
 * preserving a reference to the channel for error handling/cleanup.
 * The channel reference is transient to avoid serialization issues.
 *
 * Original obfuscated name: IllIlIllll1.IIlIllIIll1
 */
public class ChannelIOException extends Exception {

    /** The underlying I/O exception */
    private final IOException ioException;

    /** The WebSocket channel on which the I/O error occurred (transient to avoid serialization) */
    private transient Object /* WebSocketChannel */ channel;

    public ChannelIOException(IOException ioException, Object /* WebSocketChannel */ channel) {
        super(ioException);
        this.ioException = ioException;
        this.channel = channel;
    }

    /** Returns the underlying IOException. */
    public IOException getIOException() {
        return this.ioException;
    }

    /** Returns the WebSocket channel on which the error occurred. */
    public Object /* WebSocketChannel */ getChannel() {
        return this.channel;
    }
}
