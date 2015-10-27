/**
 *
 */
package info.jabara.sandbox.util;

import java.io.Serializable;

/**
 * @author jabaraster
 */
public final class MemoryUtil {

    private MemoryUtil() {
        // nop
    }

    /**
     * @return
     */
    public static Serializable[] createLargeObject() {
        final int arrayLength = 100000;
        final Serializable[] ret = new Serializable[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            ret[i] = new String(String.valueOf(i));
        }
        return ret;
    }
}
