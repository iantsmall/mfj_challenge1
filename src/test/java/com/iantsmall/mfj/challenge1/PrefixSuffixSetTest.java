package com.iantsmall.mfj.challenge1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PrefixSuffixSetTest {

    PrefixSuffixSet prefixSuffixSet;

    @BeforeEach
    void beforeEach() {
        prefixSuffixSet = new PrefixSuffixSet();
    }

    @Test
    void addPrefix() {
        assertDoesNotThrow(() -> prefixSuffixSet.addPrefix("prefix1"));
    }

    @Test
    void addSuffix() {
        assertDoesNotThrow(() -> prefixSuffixSet.addSuffix("suffix1"));
    }

    @Test
    void toWordPairs() {
        prefixSuffixSet.addPrefix("prefix1");
        prefixSuffixSet.addPrefix("prefix2");
        prefixSuffixSet.addSuffix("suffix1");
        prefixSuffixSet.addSuffix("suffix2");
        final var actualCount = prefixSuffixSet.toWordPairs().count();
        assertEquals(4, actualCount);
        final var actualWordPairs = prefixSuffixSet.toWordPairs().map(WordPair::toString).sorted();
        final var expectedWordPairs = Stream.of(
                "prefix1,suffix1",
                "prefix1,suffix2",
                "prefix2,suffix1",
                "prefix2,suffix2"
        );
        assertLinesMatch(expectedWordPairs, actualWordPairs);
    }
}