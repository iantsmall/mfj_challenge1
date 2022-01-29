package com.iantsmall.mfj.challenge1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOError;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WordsTest {
    final static String wordsPath = "src/test/resources/words.txt";

    static Stream<Arguments> invalidArgsProvider() {
        return Stream.of(
                Arguments.of((Object) new String[]{"", "abc,def,hij,klm"})
                , Arguments.of((Object) new String[]{"invalid file path", "abc,def,hij,klm"})
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"src/test/resources/non-existentFile.txt"})
    @NullAndEmptySource
    void consumeWords_givenIllegalArguments(String path) {
        assertThrows(NoSuchFileException.class, () -> new Words(path).consumeWords((stream) -> {}));
    }

    @Test
    void consumeWords_givenLegalArguments_doesNotThrow() {
        assertDoesNotThrow(() -> new Words(wordsPath).consumeWords((stream) -> {}));
        // TODO check retrieved words list
    }
}