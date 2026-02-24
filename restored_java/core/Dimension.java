package core;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: IlIIlllllI1/llllIIIIll1
 *
 * Simple immutable width/height holder used for screen and view dimensions.
 * Distinct from core.Size in that this represents logical dimensions
 * (potentially non-pixel units) whereas Size is strictly pixel-based.
 *
 * Original obfuscated name: IlIIlllllI1.llllIIIIll1
 */
public class Dimension {

    public final int width;
    public final int height;

    public Dimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Dimension(" + this.width + ", " + this.height + ")";
    }
}
