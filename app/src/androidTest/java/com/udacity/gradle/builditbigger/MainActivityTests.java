package com.udacity.gradle.builditbigger;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import dev.kkweon.jokerenderer.JokeRendererActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTests {
    @Rule
    public IntentsTestRule<MainActivity> mActivityTestRule =
            new IntentsTestRule<>(MainActivity.class);

    @Test
    public void testButtonVisible() {
        onView(withId(R.id.button_tell_joke)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void testButtonJumpsToJokeActivity() {
        onView(withId(R.id.button_tell_joke)).perform(click());
        intended(hasComponent(JokeRendererActivity.class.getName()));
    }
}
