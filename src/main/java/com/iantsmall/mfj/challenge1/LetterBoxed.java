package com.iantsmall.mfj.challenge1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class LetterBoxed {

    private static boolean isValid(Square square, String word) {
        // a valid word is at least 3 letters long, with a letter on each square side, and no repeating sides.
        // argument length check
        if (word == null || word.length() < 3) {
            return false;
        }
        // now check if it matches the square
        var lastSide = -1;
        // TODO consider a regex as an alternative
        for (var letter : word.toCharArray()) {
            var side = square.side(letter);
            if (side == -1 || side == lastSide) {
                return false;
            }
        }
        // successfully navigated the square to the end of the word
        return true;
    }

    public static List<PrefixSuffixSet> toPrefixSuffixSets(String fileName, String squareString) {
        var square = new Square(squareString);
        var prefixSuffixSets = Stream.generate(() -> new PrefixSuffixSet())
                .limit(26)
                .collect(Collectors.toList());
        // for each( word in file.lines ): // use a stream
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.filter(s -> LetterBoxed.isValid(square, s))
                    .forEach(s -> {
                        var word = s.trim().toUpperCase();
                        var startsWith = word.charAt(0) - 'A';
                        var endsWith = word.charAt(word.length() - 1) - 'A';
                        prefixSuffixSets.get(endsWith).addPrefix(word);
                        prefixSuffixSets.get(startsWith).addSuffix(word);
                    });
        } catch (Exception e) {
            throw new IllegalArgumentException("An exception occurred while processing the words file", e);
        }
        return prefixSuffixSets;
    }

    public static Stream<WordPair> play(String fileName, String squareString) {
        return toPrefixSuffixSets(fileName, squareString).stream().flatMap(PrefixSuffixSet::toWordPairs);
    }

}
