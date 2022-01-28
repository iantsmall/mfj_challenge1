package com.iantsmall.mfj.challenge1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordPairTest {

    @Test
    void testToString() {
        var prefix = "abcde";
        var suffix = "fghij";
        var wp = new WordPair(prefix, suffix);
        assertEquals(wp.toString(), "abcde,fghij");
    }
}