package com.iantsmall.mfj.challenge1;

public class LetterBoxedCLI {
    static void run(String[] args) {
        if (args == null || args.length != 2) {
            throw new IllegalArgumentException("Expected 2 arguments, the file path and the square string");
        }
        final var words = new Words(args[0]);
        LetterBoxed.play(words, args[1])
                .forEach(wordPair -> System.out.println(wordPair));
    }
}
