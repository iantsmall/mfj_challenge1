package com.iantsmall.mfj.challenge1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WordsTest {
    final static String wordsPath = "src/test/resources/words.txt";
    final static String nonexistentPath = wordsPath + ".nonexistentExtension";

    @ParameterizedTest
    @NullAndEmptySource
    void constructor_givenIllegalArguments(String path) {
        assertThrows(IllegalArgumentException.class, () -> new Words(path));
    }

    @ParameterizedTest
    @ValueSource(strings = {wordsPath, nonexistentPath})
    void constructor_givenLegalArguments(String path) {
        assertDoesNotThrow(() -> new Words(path));
    }

    @Test
    void consumeWords_givenNonexistentFilepath() {
        assertThrows(NoSuchFileException.class, () -> new Words(nonexistentPath).consumeWords((stream) -> {
        }));
    }

    @Test
    void consumeWords_givenLegalArguments_doesNotThrow() {
        assertDoesNotThrow(() -> new Words(wordsPath).consumeWords((stream) -> {
        }));
    }
}