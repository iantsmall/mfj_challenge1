package com.iantsmall.mfj.challenge1;

/**
 * App is the origin point for the application, and has the sole responsibility
 * of sending application arguments to LetterBoxedCLI.run, and setting the CLI out to
 * System.out.
 */
public class App {
    public static void main(String[] args) {
        LetterBoxedCLI.run(args, System.out);
    }
}
