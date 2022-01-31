package com.iantsmall.mfj.challenge1;

import lombok.Data;

/**
 * Representation of a valid pair of words in a square.
 * Does NOT validate data in any way.
 */
@Data
class WordPair {
    public final String prefix;
    public final String suffix;
    public String toString() {
        return String.format("%s,%s", this.prefix, this.suffix);
    }
}
