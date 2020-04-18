package dev.kkweon.joke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.FutureTask;

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

    public static List<String> readJokesFromTextFile() throws IOException {
        InputStream inputStream = JokeFactory.class.getResourceAsStream("/jokes.txt");
        //creating an InputStreamReader object
        InputStreamReader isReader = new InputStreamReader(inputStream);
        //Creating a BufferedReader object
        BufferedReader reader = new BufferedReader(isReader);

        List<String> jokes = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            jokes.add(line.trim());
        }
        return jokes;
    }
}
