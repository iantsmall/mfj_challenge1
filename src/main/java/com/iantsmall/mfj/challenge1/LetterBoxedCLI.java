package com.iantsmall.mfj.challenge1;

public class LetterBoxedCLI {
    static void run(String[] args) {
        if (args == null || args.length != 2) {
            throw new IllegalArgumentException("Expected 2 arguments, the file path and the square string");
        }
        LetterBoxed.play(args[0], args[1])
                .forEach(wordPair -> System.out.println(wordPair));
    }
}
