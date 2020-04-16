package dev.kkweon.jokerenderer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.kkweon.jokerenderer.databinding.ActivityJokeRendererBinding;

public class JokeRendererActivity extends AppCompatActivity {

    ActivityJokeRendererBinding mActivityJokeRendererBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String joke = getJokeFromIntent(getIntent());

        mActivityJokeRendererBinding = ActivityJokeRendererBinding.inflate(getLayoutInflater());
        setContentView(mActivityJokeRendererBinding.getRoot());
    }

    private String getJokeFromIntent(Intent intent) {
        return null;
    }
}
