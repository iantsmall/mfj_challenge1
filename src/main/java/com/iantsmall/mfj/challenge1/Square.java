package com.iantsmall.mfj.challenge1;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

class Square {
    private final List<String> sides;

    /**
     * Create a LetterBox game's Square from a string square definition
     *
     * @param def the case insensitive string defintion of a square, in the format "ABC,EGL,API"
     */
    public Square(String def) {
        var sides = Arrays.asList(def.toUpperCase().split(","));
        var numSides = sides.size();
        if (numSides != 4) {
            var msg = String.format("Square string definition must have 4 sides");
            throw new InvalidParameterException(msg);
        }
        sides.forEach(String::toUpperCase);
        this.sides = sides;
    }

    /**
     * @param letter a string containing a single letter
     * @return the index of the side the letter is in, 0-3, -1 if not present
     */
    public int side(char letter) {
        var letterString = Character.toString(letter).toUpperCase();
        for (var i = 0; i < 3; ++i) {
            if (this.sides.get(i).contains(letterString)) {
                return i;
            }
        }
        return -1;
    }
}
