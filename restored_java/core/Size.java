package core;

/**
 * Size — 屏幕/视图尺寸
 *
 * Original: IlIIlllllI1.llllIIIIll1
 *
 * Simple 2D size class used to store screen dimensions.
 * Fields:
 *   - f134llllIIIIll1 → width
 *   - f133lIIIIlllllIlll1 → height
 */
public class Size {

    public final int width;   /* f134llllIIIIll1 */
    public final int height;  /* f133lIIIIlllllIlll1 */

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Returns true if either dimension is zero or negative (invalid size).
     * Original: llllIIIIll1()
     */
    public boolean isInvalid() {
        return this.width <= 0 || this.height <= 0;
    }

    @Override
    public String toString() {
        return "Vector2(" + this.width + ", " + this.height + ")";
    }
}
