package com.iantsmall.mfj.challenge1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquareTest {

    @SuppressWarnings("SpellCheckingInspection")
    static Stream<TestArg_isValid> isValid_argsProvider() {
        final var squareDef = "abc,def,hij,klm";
        // TODO setup checking each unicode character for checking edge cases
        return Stream.of(
                new TestArg_isValid(squareDef, "", false),
                new TestArg_isValid(squareDef, ".", false),
                new TestArg_isValid(squareDef, ",", false),
                new TestArg_isValid(squareDef, "\\", false),
                new TestArg_isValid(squareDef, "/", false),
                new TestArg_isValid(squareDef, "/", false),
                new TestArg_isValid(squareDef, ",", false),
                new TestArg_isValid(squareDef, "a", false),
                new TestArg_isValid(squareDef, "b", false),
                new TestArg_isValid(squareDef, "c", false),
                new TestArg_isValid(squareDef, "d", false),
                new TestArg_isValid(squareDef, "e", false),
                new TestArg_isValid(squareDef, "f", false),
                new TestArg_isValid(squareDef, "h", false),
                new TestArg_isValid(squareDef, "i", false),
                new TestArg_isValid(squareDef, "j", false),
                new TestArg_isValid(squareDef, "k", false),
                new TestArg_isValid(squareDef, "l", false),
                new TestArg_isValid(squareDef, "m", false),
                new TestArg_isValid(squareDef, "nop", false),
                new TestArg_isValid(squareDef, "aaa", false),
                new TestArg_isValid(squareDef, "abc", false),
                new TestArg_isValid(squareDef, "ada", true),
                new TestArg_isValid(squareDef, "adhk", true),
                new TestArg_isValid(squareDef, "mdaha", true)
        );
    }

    @ParameterizedTest
    @MethodSource("isValid_argsProvider")
    void isValid(TestArg_isValid p) {
        assertEquals(new Square(p.squareDef).isValid(p.word), p.expectedResponse, p.toString());
    }

    private static class SideTestArg {
        final int expectedSide;
        final String squareDef;
        final char letter;

        private SideTestArg(String squareDef, char letter, int expectedSide) {
            this.expectedSide = expectedSide;
            this.squareDef = squareDef;
            this.letter = letter;
        }
    }

    private static class TestArg_isValid {
        final String squareDef;
        final String word;
        final boolean expectedResponse;

        @SuppressWarnings("SameParameterValue")
        private TestArg_isValid(String squareDef, String word, boolean expectedResponse) {
            this.squareDef = squareDef;
            this.word = word;
            this.expectedResponse = expectedResponse;
        }

        @Override
        public String toString() {
            return "TestArg_isValid{" +
                    "squareDef='" + squareDef + '\'' +
                    ", word='" + word + '\'' +
                    ", expectedResponse=" + expectedResponse +
                    '}';
        }
    }
}