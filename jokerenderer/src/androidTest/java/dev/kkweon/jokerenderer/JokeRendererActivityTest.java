package dev.kkweon.jokerenderer;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import android.content.Intent;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import dev.kkweon.joke.JokeFactory;

@RunWith(AndroidJUnit4.class)
public class JokeRendererActivityTest {

  @Rule
  public ActivityTestRule<JokeRendererActivity> mActivityTestRule =
      new ActivityTestRule<>(JokeRendererActivity.class);

  @Test
  public void testJokeRenders() {
    onView(withText(JokeFactory.getJoke())).check(matches(isCompletelyDisplayed()));
  }
}
