package dev.kkweon.joke;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class JokeFactory {
    public static FutureTask<String> getJoke() {
        return new FutureTask<>(
                new Callable<String>() {
                    @Override
                    public String call() {
                        return "Knock Knock";
                    }
                });
    }
}
