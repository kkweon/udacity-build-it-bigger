package dev.kkweon.joke;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

public class JokeFactory {

    private static List<String> jokes = null;
    private static Random random = new Random();

    public static FutureTask<String> getJoke() {

        return new FutureTask<>(
                () -> {
                    if (jokes == null) {
                        jokes = readJokesFromTextFile();
                    }
                    int idx = random.nextInt(jokes.size());
                    return jokes.get(idx);
                });
    }

    public static List<String> readJokesFromTextFile() throws URISyntaxException, IOException {
        List<String> jokes =
                Files.readAllLines(Paths.get(JokeFactory.class.getResource("/jokes.txt").toURI()));
        return jokes.stream().filter(j -> !j.isEmpty()).collect(Collectors.toList());
    }
}
