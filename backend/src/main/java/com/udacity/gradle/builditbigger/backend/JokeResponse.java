package com.udacity.gradle.builditbigger.backend;

public class JokeResponse {
    private String joke;

    public JokeResponse() {}

    public JokeResponse(String joke) {
        this.joke = joke;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
