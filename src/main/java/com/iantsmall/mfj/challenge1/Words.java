package com.iantsmall.mfj.challenge1;

import lombok.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Words is composed of a file name which is opened within consumeWords.
 * Filename validity is not fully checked until consumeWords is invoked.
 */
@Value
public class Words {
    String fileName;

    /**
     * @param fileName A string containing the filename path to open as a Stream of word Strings
     */
    public Words(final String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("Words constructor given unexpected nonnull filename");
        } else if (fileName.trim().length() == 0) {
            throw new IllegalArgumentException("Words constructor given unexpected non-blank filename");
        }
        this.fileName = fileName;
    }

    /**
     * Opens a Stream of individual word Strings which are then fed to the consumer with consumer.accept(stream).
     *
     * @param consumer a consumer which accepts the Stream of word Strings
     */
    void consumeWords(Consumer<Stream<String>> consumer) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(this.fileName))) {
            consumer.accept(stream);
        }
    }

}
