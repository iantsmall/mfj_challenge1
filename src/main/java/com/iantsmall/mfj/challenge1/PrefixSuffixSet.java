package com.iantsmall.mfj.challenge1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

class PrefixSuffixSet {

    private final Collection<String> prefixes = new ArrayList<String>();
    private final Collection<String> suffixes = new ArrayList<String>();

    PrefixSuffixSet() {
    }

    public void addPrefix(String word) {
        this.prefixes.add(word);
    }

    public void addSuffix(String word) {
        this.prefixes.add(word);
    }

    /**
     * Unions the prefixes and suffixes into a stream of WordPairs
     *
     * @return
     */
    public Stream<WordPair> toWordPairs() {
        return this.prefixes.stream().flatMap(
                p -> this.suffixes.stream().map(
                        s -> new WordPair(p, s)));
    }

}
