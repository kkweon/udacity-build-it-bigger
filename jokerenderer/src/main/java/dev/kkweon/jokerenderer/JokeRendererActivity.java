package dev.kkweon.jokerenderer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import dev.kkweon.jokerenderer.databinding.ActivityJokeRendererBinding;

public class JokeRendererActivity extends AppCompatActivity {

    ActivityJokeRendererBinding mActivityJokeRendererBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityJokeRendererBinding = ActivityJokeRendererBinding.inflate(getLayoutInflater());
        setContentView(mActivityJokeRendererBinding.getRoot());

        String joke = getJokeFromIntent(getIntent());
        renderJoke(joke);
    }

    private void renderJoke(String joke) {
        if (joke == null) {
            Toast.makeText(this, getString(R.string.no_joke_available), Toast.LENGTH_SHORT).show();
        }
    }

    private String getJokeFromIntent(Intent intent) {
        return null;
    }
}
