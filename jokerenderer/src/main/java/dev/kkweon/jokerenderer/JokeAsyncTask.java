package dev.kkweon.jokerenderer;

import android.os.AsyncTask;
import androidx.annotation.Nullable;
import dev.kkweon.joke.JokeFactory;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/** Wraps around CompletableFuture because its API is supported only in API level >= 24. */
class JokeAsyncTask extends AsyncTask<Void, Void, String> {
    @Nullable private OnJokeFetched mCallback;

    interface OnJokeFetched {
        void onJoke(String joke);

        void onError();
    }

    public JokeAsyncTask(OnJokeFetched renderJoke) {
        mCallback = renderJoke;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            FutureTask<String> jokeTask = JokeFactory.getJoke();
            Executors.newCachedThreadPool().submit(jokeTask);
            return jokeTask.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (mCallback != null) {
            mCallback.onError();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String joke) {
        if (mCallback != null) {
            mCallback.onJoke(joke);
        }
    }
}
