package com.iantsmall.mfj.challenge1;

public class LetterBoxedCLI {
    static void run(String[] args, java.io.PrintStream out) {
        if (args == null || args.length != 2) {
            throw new IllegalArgumentException("Expected 2 arguments, the file path and the square string");
        }
        final var words = new Words(args[0]);
        // Note: With something like Spring's dependency injection unit testing would be much simpler
        LetterBoxed.play(words, args[1])
                .map(WordPair::toString)
                .forEach(out::println);
    }
}
