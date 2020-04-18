package dev.kkweon.joke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class JokeFactory {

    private static List<String> jokes = null;
    private static Random random = new Random();

    public static FutureTask<String> getJoke() {

        return new FutureTask<>(
                new Callable<String>() {
                    @Override
                    public String call() {
                        if (jokes == null) {
                            try {
                                jokes = readJokesFromTextFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                        int idx = random.nextInt(jokes.size());
                        return jokes.get(idx);
                    }
                });
    }

    public static List<String> readJokesFromTextFile() throws IOException {
        File file = new File(JokeFactory.class.getClassLoader().getResource("jokes.txt").getFile());
        BufferedReader bufferedReader = null;
        bufferedReader = new BufferedReader(new FileReader(file));

        List<String> jokes = new ArrayList<>();

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                jokes.add(line);
            }
        }

        return jokes;
    }
}
