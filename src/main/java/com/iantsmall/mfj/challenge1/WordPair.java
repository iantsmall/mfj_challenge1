package com.iantsmall.mfj.challenge1;

// TODO consider Lombok for cleaner code
class WordPair {
    public String prefix;
    public String suffix;

    WordPair(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String toString() {
        return String.format("%s,%s", this.prefix, this.suffix);
    }
}
