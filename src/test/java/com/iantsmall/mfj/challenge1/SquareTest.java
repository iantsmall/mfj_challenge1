package com.iantsmall.mfj.challenge1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Locale;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquareTest {

    private static class SideTestArg{
        final int expectedSide;
        final String squareDef;
        final char letter;

        private SideTestArg(String squareDef, char letter, int expectedSide) {
            this.expectedSide = expectedSide;
            this.squareDef = squareDef;
            this.letter = letter;
        }
    }

    static Stream<SideTestArg> argsProvider() {
        final var squareDef = "abc,def,hij,klm";
        return Stream.of(
                new SideTestArg(squareDef, ' ', -1),
                new SideTestArg(squareDef, '1', -1),
                new SideTestArg(squareDef, '\\', -1),
                new SideTestArg(squareDef, '/', -1),
                new SideTestArg(squareDef, ',', -1),
                new SideTestArg(squareDef, '.', -1),
                new SideTestArg(squareDef, 'a', 0),
                new SideTestArg(squareDef, 'b', 0),
                new SideTestArg(squareDef, 'c', 0),
                new SideTestArg(squareDef, 'd', 1),
                new SideTestArg(squareDef, 'e', 1),
                new SideTestArg(squareDef, 'f', 1),
                new SideTestArg(squareDef, 'h', 2),
                new SideTestArg(squareDef, 'i', 2),
                new SideTestArg(squareDef, 'j', 2),
                new SideTestArg(squareDef, 'k', 3),
                new SideTestArg(squareDef, 'l', 3),
                new SideTestArg(squareDef, 'm', 3),
                new SideTestArg(squareDef, 'A', 0),
                new SideTestArg(squareDef, 'D', 1),
                new SideTestArg(squareDef, 'H', 2),
                new SideTestArg(squareDef, 'K', 3),
                new SideTestArg(squareDef.toUpperCase(), 'a', 0),
                new SideTestArg(squareDef.toUpperCase(), 'd', 1),
                new SideTestArg(squareDef.toUpperCase(), 'h', 2),
                new SideTestArg(squareDef.toUpperCase(), 'k', 3),
                new SideTestArg(squareDef.toUpperCase(), 'A', 0),
                new SideTestArg(squareDef.toUpperCase(), 'D', 1),
                new SideTestArg(squareDef.toUpperCase(), 'H', 2),
                new SideTestArg(squareDef.toUpperCase(), 'K', 3)
                );
    }

    @ParameterizedTest
    @MethodSource("argsProvider")
    void side(SideTestArg p) {
        assertEquals(new Square(p.squareDef).side(p.letter), p.expectedSide);
    }
}