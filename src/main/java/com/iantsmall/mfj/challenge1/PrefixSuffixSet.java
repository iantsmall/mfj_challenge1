package com.iantsmall.mfj.challenge1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Stream;

class PrefixSuffixSet {

    private final Collection<String> prefixes = new ArrayList<>();
    private final Collection<String> suffixes = new ArrayList<>();
    /**
     * creates a Stream of WordPairs for the given prefix paired with the suffixes
      */
    private final Function<String, Stream<? extends WordPair>> toPrefixSuffixesStream =
            p -> suffixes.parallelStream().map(s -> new WordPair(p, s));

    PrefixSuffixSet() {
    }

    public void addPrefix(String word) {
        prefixes.add(word);
    }

    public void addSuffix(String word) {
        suffixes.add(word);
    }

    /**
     * Unions the prefixes and suffixes into a stream of WordPairs
     *
     * @return a stream of WordPairs from the union of prefixes and suffixes
     */
    public Stream<WordPair> toWordPairs() {
        return prefixes.parallelStream().flatMap(toPrefixSuffixesStream);
    }

}
