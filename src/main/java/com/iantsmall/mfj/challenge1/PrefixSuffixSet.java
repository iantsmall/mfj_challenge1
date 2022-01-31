package com.iantsmall.mfj.challenge1;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Describes a set of prefixes and suffixes which are valid 2 word pairs
 * for a given Square.
 */
@Data
class PrefixSuffixSet {

    private final Collection<String> prefixes = new ArrayList<>();
    private final Collection<String> suffixes = new ArrayList<>();

    /**
     * Creates a Stream of WordPairs for the given prefix paired with the suffixes
     */
    private final Function<String, Stream<? extends WordPair>> toPrefixSuffixesStream =
            p -> suffixes.parallelStream().map(s -> new WordPair(p, s));

    /**
     * Add a valid word to append to the prefixes.
     *
     * Should end with in the same letter as each other prefixes.
     * Prefixes end with the same letter as the first letter of the suffixes.
     *
     * Values are NOT checked or validated in any way.
     *
     * @param word A valid prefix word for the PrefixSuffixSet
     */
    public void addPrefix(String word) {
        prefixes.add(word);
    }

    /**
     * Add a valid word to append to the suffixes.
     *
     * Should start with in the same letter as each other suffixes.
     * Suffixes start with the same letter as the last letter of the prefixes.
     *
     * Values are NOT checked or validated in any way.
     *
     * @param word A valid suffix word for the PrefixSuffixSet
     */
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
