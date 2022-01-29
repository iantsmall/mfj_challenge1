package com.iantsmall.mfj.challenge1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class LetterBoxedCLITest {

    @Captor ArgumentCaptor<Words> wordsArgumentCaptor;

    @Captor ArgumentCaptor<String> squareDefArgumentCaptor;

    @Mock java.io.PrintStream outMock;

    @Test
    void run() {
        final var args = new String[]{"src/test/resources/words.txt", "dzt,hqg,eau,yro"};
        final var wordPairStream = Stream.of(
                new WordPair("test1","pair1"),
                new WordPair("test2","pair2")
        );
        try (MockedStatic<LetterBoxed> playMockedStatic = mockStatic(LetterBoxed.class)) {
            playMockedStatic.when(() -> LetterBoxed.play(any(), any())).thenReturn(wordPairStream);
            LetterBoxedCLI.run(args, outMock);
            playMockedStatic.verify(() -> LetterBoxed.play(wordsArgumentCaptor.capture(), squareDefArgumentCaptor.capture()));
            final var playedWords = wordsArgumentCaptor.getValue();
            assertNotNull(playedWords);
            assertEquals(playedWords.fileName, args[0]);
            final var playedSquareDef = squareDefArgumentCaptor.getValue();
            assertNotNull(playedSquareDef);
            assertEquals(playedSquareDef, args[1]);
        }
        Stream.of(
                "test1,pair1",
                "test2,pair2"
        ).forEach((wordPair)-> then(outMock).should().println(wordPair.toString()));
        then(outMock).shouldHaveNoMoreInteractions();
    }
}
