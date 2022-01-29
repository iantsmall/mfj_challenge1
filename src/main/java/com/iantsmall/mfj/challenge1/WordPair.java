package com.iantsmall.mfj.challenge1;

// TODO consider Lombok for cleaner code
class WordPair {
    public final String prefix;
    public final String suffix;

    WordPair(final String prefix, final String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String toString() {
        return String.format("%s,%s", this.prefix, this.suffix);
    }
}
