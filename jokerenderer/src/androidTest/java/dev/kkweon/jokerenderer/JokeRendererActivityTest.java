package dev.kkweon.jokerenderer;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import java.util.concurrent.ExecutionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class JokeRendererActivityTest {

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        ActivityScenario.launch(JokeRendererActivity.class)
                .onActivity(
                        activity -> {
                            mIdlingResource = activity.getIdlingResource();
                            IdlingRegistry.getInstance().register(mIdlingResource);
                        });
    }

    @Test
    public void testJokeRenders() throws ExecutionException, InterruptedException {
        onView(withId(R.id.text_view_joke))
                .check(matches(not(withText(R.string.joke_placeholder))));
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}
