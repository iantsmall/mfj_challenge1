package com.iantsmall.mfj.challenge1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AppTest {

    final static String wordsPath = "src/test/resources/words.txt";

    static Stream<Arguments> invalidArgsProvider() {
        return Stream.of(
                Arguments.of((Object) new String[]{"", "abc,def,hij,klm"})
                , Arguments.of((Object) new String[]{"invalid file path", "abc,def,hij,klm"})
                , Arguments.of((Object) new String[]{wordsPath, "def,hij,klm"})
        );
    }

    @ParameterizedTest
    @MethodSource("invalidArgsProvider")
    @NullAndEmptySource
    void main_givenIllegalArguments(String[] args) {
        assertThrows(IllegalArgumentException.class, () -> App.main(args));
    }

    @Test
    void main_givenLegalArguments_doesNotThrow() {
        assertDoesNotThrow(() -> App.main(new String[]{wordsPath, "def,hij,klm,lap"}));
    }
}