package com.iantsmall.mfj.challenge1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class LetterBoxed {

    public static List<PrefixSuffixSet> toPrefixSuffixSets(String fileName, String squareString)
            throws IllegalArgumentException{
        var square = new Square(squareString);
        var prefixSuffixSets = Stream.generate(() -> new PrefixSuffixSet())
                .limit(26)
                .collect(Collectors.toList());
        // for each( word in file.lines ): // use a stream
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.filter(s -> square.isValid(s))
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

    public static Stream<WordPair> play(String fileName, String squareString) throws IllegalArgumentException {
        return toPrefixSuffixSets(fileName, squareString).stream().flatMap(PrefixSuffixSet::toWordPairs);
    }

}
