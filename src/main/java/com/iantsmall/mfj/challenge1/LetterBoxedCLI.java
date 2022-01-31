package com.iantsmall.mfj.challenge1;

/**
 * Utility class to run the Letter Boxed game's command line interface.
 * A very simple class which simply supplies a "run" method which accepts command
 * arguments and a java.io.PrintStream to print output.
 */
public class LetterBoxedCLI {

    /**
     * Parses the CLI arguments and uses them to execute LetterBoxed.play.
     * args[0] is passed as the filename for the Words constructor, and arg[1] is the
     * the squareDef passed to the Square constructor.
     *
     * @param args Size 2 array of string arguments, arg[0] being a file name and arg[1] being a square definition
     * @param out a PrintStream to use as an output for application messages
     */
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
