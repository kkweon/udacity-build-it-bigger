package dev.kkweon.joke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class JokeFactoryTests {

    @Test
    public void testGetJoke() {
        assertEquals("Knock Knock", JokeFactory.getJoke());
    }
}
