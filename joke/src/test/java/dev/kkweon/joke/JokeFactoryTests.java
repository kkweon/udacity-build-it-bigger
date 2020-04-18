package dev.kkweon.joke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JokeFactoryTests {

    @Test
    public void testGetJoke() throws ExecutionException, InterruptedException {
        FutureTask<String> joke = JokeFactory.getJoke();
        Executors.newCachedThreadPool().submit(joke);
        Assertions.assertNotNull(joke.get());
    }

    @Test
    public void testReadJokesFromTextFile() throws IOException {
        List<String> jokes = JokeFactory.readJokesFromTextFile();
        assertEquals(
                "\"Automatic\" simply means that you cannot repair it yourself.", jokes.get(0));
    }
}
