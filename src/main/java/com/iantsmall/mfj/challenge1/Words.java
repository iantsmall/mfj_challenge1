package com.iantsmall.mfj.challenge1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Words {
    final String fileName;

    public Words(final String fileName) {
        if(fileName == null){
            throw new IllegalArgumentException("Words constructor given unexpected nonnull filename");
        }
        else if(fileName.trim().length() == 0){
            throw new IllegalArgumentException("Words constructor given unexpected nonblank filename");
        }
        this.fileName = fileName;
    }

    /**
     * Performs an action for each element of this stream.
     *
     * <p>This is a <a href="package-summary.html#StreamOps">terminal
     * operation</a>.
     *
     * <p>The behavior of this operation is explicitly nondeterministic.
     * For parallel stream pipelines, this operation does <em>not</em>
     * guarantee to respect the encounter order of the stream, as doing so
     * would sacrifice the benefit of parallelism.  For any given element, the
     * action may be performed at whatever time and in whatever thread the
     * library chooses.  If the action accesses shared state, it is
     * responsible for providing the required synchronization.
     *
     * @param consumer a consumer which accepts the stream of words
     */
    void consumeWords(Consumer<Stream<String>> consumer) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(this.fileName))) {
            consumer.accept(stream);
        }
    }

}
