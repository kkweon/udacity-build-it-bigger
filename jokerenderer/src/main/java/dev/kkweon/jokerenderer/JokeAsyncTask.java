package dev.kkweon.jokerenderer;

import android.os.AsyncTask;
import androidx.annotation.Nullable;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import java.io.IOException;

/** Wraps around CompletableFuture because its API is supported only in API level >= 24. */
class JokeAsyncTask extends AsyncTask<Void, Void, String> {
    @Nullable private OnJokeFetched mCallback;
    private static MyApi mService =
            new MyApi.Builder(new NetHttpTransport.Builder().build(), new GsonFactory(), null)
                    .build();

    interface OnJokeFetched {
        void onJoke(String joke);

        void onError();
    }

    public JokeAsyncTask(@Nullable OnJokeFetched callback) {
        mCallback = callback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            return mService.jokes().execute().getJoke();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        if (mCallback != null) {
            mCallback.onJoke(joke);
        }
    }
}
