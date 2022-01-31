package com.iantsmall.mfj.challenge1;

import lombok.Data;
import lombok.Value;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

@Value
@Data
class Square {
    List<String> sides;

    /**
     * Create a LetterBox game's Square defined from a string square definition.
     *
     * @param def the case-insensitive string definition of a square, in the format "ABC,EGL,API"
     */
    public Square(String def) {
        final var sides = Arrays.asList(def.toUpperCase().split(","));
        final var numSides = sides.size();
        if (numSides != 4) {
            var msg = String.format("Square string definition must have 4 sides, discovered %o", numSides);
            throw new InvalidParameterException(msg);
        }
        this.sides = sides;
    }

    /**
     * Confirms is a word is a valid word for the Square.
     * @param word the word to check
     * @return true if the word is valid, else false
     */
    public boolean isValid(String word) {
        // a valid word is at least 3 letters long, with a letter on each square side, and no repeating sides.
        // argument length check
        if (word == null || word.length() < 3) {
            return false;
        }
        // now check if it matches the square
        var lastSide = -1;
        // TODO consider a regex as an alternative
        for (var letter : word.toCharArray()) {
            var side = this.side(letter);
            if (side == -1 || side == lastSide) {
                return false;
            }
            lastSide = side;
        }
        // successfully navigated the square to the end of the word
        return true;
    }

    /**
     * @param letter a string containing a single letter
     * @return the index of the side the letter is in, 0-3, -1 if not present
     */
    private int side(char letter) {
        var letterString = Character.toString(letter).toUpperCase();
        for (var i = 0; i <= 3; ++i) {
            if (this.sides.get(i).contains(letterString)) {
                return i;
            }
        }
        return -1;
    }
}
