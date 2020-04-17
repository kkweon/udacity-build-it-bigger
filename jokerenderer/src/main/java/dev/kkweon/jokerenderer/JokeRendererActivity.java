package dev.kkweon.jokerenderer;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.IdlingResource;
import dev.kkweon.jokerenderer.databinding.ActivityJokeRendererBinding;
import dev.kkweon.jokerenderer.testutils.SimpleIdlingResource;

public class JokeRendererActivity extends AppCompatActivity implements JokeAsyncTask.OnJokeFetched {

    ActivityJokeRendererBinding mActivityJokeRendererBinding;

    @Nullable private SimpleIdlingResource mIdlingResource;

    @Override
    public void onJoke(String joke) {
        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(true);
        }

        if (joke == null || joke.isEmpty()) {
            Toast.makeText(this, getString(R.string.no_joke_available), Toast.LENGTH_SHORT).show();
        }

        mActivityJokeRendererBinding.textViewJoke.setText(joke);
    }

    @Override
    public void onError() {
        mActivityJokeRendererBinding.textViewJoke.setText(R.string.no_joke_available);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityJokeRendererBinding = ActivityJokeRendererBinding.inflate(getLayoutInflater());
        setContentView(mActivityJokeRendererBinding.getRoot());

        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(false);
        }

        new JokeAsyncTask(this).execute();
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }
}
