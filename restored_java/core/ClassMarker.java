package core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MALWARE ANALYSIS — Type-level annotation
 *
 * Original: llllIllIl1.llllIIIIll1
 *
 * Retention CLASS annotation targeting TYPE (classes/interfaces).
 * Purpose unclear from obfuscated code - likely used by the obfuscator
 * or build tooling for class marking.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface ClassMarker {
}
