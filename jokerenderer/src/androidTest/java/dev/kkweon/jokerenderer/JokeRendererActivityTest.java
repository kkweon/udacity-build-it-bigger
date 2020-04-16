package dev.kkweon.jokerenderer;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class JokeRendererActivityTest {

    @Rule
    ActivityTestRule mActivityTestRule = new ActivityTestRule<>(JokeRendererActivity.class);


    @Test
    public void testWithIntent() {
        onView(withId(R.id.text_view_joke)).check(matchers(isCompletelyDisplayed()));
    }
}