package com.iantsmall.mfj.challenge1;

import lombok.NonNull;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Suite of static functions to execute the LetterBoxed game.
 */
class LetterBoxed {

    /**
     * Creates the PrefixSuffixSets valid for the square from Words.
     *
     * @param words the words to use as a dictionary to populate the PrefixSuffixSets
     * @param squareString the string representation of a square, passed to the Square constructor
     * @return A list of all possible PrefixSuffixSets for words and the squareString
     * @throws IllegalArgumentException
     */
    public static List<PrefixSuffixSet> toPrefixSuffixSets(@NonNull Words words, @NonNull String squareString) throws IllegalArgumentException {
        final var square = new Square(squareString);
        final var prefixSuffixSets = Stream.generate(PrefixSuffixSet::new).limit(26).collect(Collectors.toList());
        try {
            words.consumeWords((steam) -> steam.filter(square::isValid).forEach(s -> {
                final var word = s.trim().toUpperCase();
                final var startsWith = word.charAt(0) - 'A';
                final var endsWith = word.charAt(word.length() - 1) - 'A';
                prefixSuffixSets.get(endsWith).addPrefix(word);
                prefixSuffixSets.get(startsWith).addSuffix(word);
            }));
        } catch (IOException e) {
            throw new IllegalArgumentException("An IOException occurred while processing the words", e);
        }
        return prefixSuffixSets;
    }

    /**
     * Plays the game and makes all possible 2 word pairs for the given squareString
     * using words as a dictionary of possible words.
     *
     * @param words the words to use as a dictionary to populate the WordPairs Stream
     * @param squareString the string representation of a square, passed to the Square constructor
     * @return A stream of all possible WordPairs for words and the squareString
     * @throws IllegalArgumentException
     */
    public static Stream<WordPair> play(@NonNull Words words, @NonNull String squareString) throws IllegalArgumentException {
        return toPrefixSuffixSets(words, squareString).parallelStream().flatMap(PrefixSuffixSet::toWordPairs);
    }

}
