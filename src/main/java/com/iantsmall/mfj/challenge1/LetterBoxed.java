package com.iantsmall.mfj.challenge1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

class LetterBoxed {

    private static boolean isValid(Square square, String word){
        // a valid word is at least 3 letters long, with a letter on each square side, and no repeating sides.
        // argument length check
        if(word == null || word.length() < 3){
            return false;
        }
        // now check if it matches the square
        var lastSide = -1;
        // TODO consider a regex as an alternative
        for (var letter: word.toCharArray()) {
            // TODO find the side
            var side = square.side(letter);
            if( side == -1 || side == lastSide){
                return false;
            }
        }
        // successfully navigated the square to the end of the word
        return true;
    }

    public static List<PrefixSuffixSet> toPrefixSuffixSets(String fileName, String squareString){
        var square = new Square(squareString);
        var prefixSuffixSets = Arrays.asList(new PrefixSuffixSet());
        for(var i=0; i < 25; ++i){
            prefixSuffixSets.add(new PrefixSuffixSet());
        }
        // for each( word in file.lines ): // use a stream
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.filter(s -> LetterBoxed.isValid(square, s))
                .forEach(s -> {
                    var word = s.trim().toUpperCase();
                    var startsWith = 'A' - word.charAt(0);
                    var endsWith = 'A' - word.charAt(word.length()-1);
                    prefixSuffixSets.get(endsWith).addPrefix(word);
                    prefixSuffixSets.get(startsWith).addSuffix(word);
                });
        }
        catch (Exception e){
            throw new IllegalArgumentException("An exception occurred while processing the words file", e);
        }
        return prefixSuffixSets;
    }

    public static Stream<WordPair> play(String fileName, String squareString) {
        //TODO convert to a stream of WordPairs
        return toPrefixSuffixSets(fileName, squareString).stream().flatMap(PrefixSuffixSet::toWordPairs);
    }

}
