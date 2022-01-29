package com.iantsmall.mfj.challenge1;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class LetterBoxed {

    public static List<PrefixSuffixSet> toPrefixSuffixSets(Words words, String squareString) throws IllegalArgumentException {
        var square = new Square(squareString);
        var prefixSuffixSets = Stream.generate(() -> new PrefixSuffixSet()).limit(26).collect(Collectors.toList());
        try {
            words.consumeWords((steam) -> steam.filter(s -> square.isValid(s)).forEach(s -> {
                var word = s.trim().toUpperCase();
                var startsWith = word.charAt(0) - 'A';
                var endsWith = word.charAt(word.length() - 1) - 'A';
                prefixSuffixSets.get(endsWith).addPrefix(word);
                prefixSuffixSets.get(startsWith).addSuffix(word);
            }));
        } catch (IOException e) {
            throw new IllegalArgumentException("An IOException occurred while processing the words", e);
        }
        return prefixSuffixSets;
    }

    public static Stream<WordPair> play(Words words, String squareString) throws IllegalArgumentException {
        return toPrefixSuffixSets(words, squareString).stream().flatMap(PrefixSuffixSet::toWordPairs);
    }

}
