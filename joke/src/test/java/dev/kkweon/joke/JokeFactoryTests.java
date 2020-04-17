package dev.kkweon.joke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

public class JokeFactoryTests {

    @Test
    public void testGetJoke() throws ExecutionException, InterruptedException {
        assertEquals("Knock Knock", JokeFactory.getJoke().get());
    }
}
