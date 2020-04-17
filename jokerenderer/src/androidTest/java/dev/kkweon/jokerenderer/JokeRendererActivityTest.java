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

@RunWith(AndroidJUnit4.class)
public class JokeRendererActivityTest {

    @Rule
    public ActivityTestRule<JokeRendererActivity> mActivityTestRule =
            new ActivityTestRule<>(JokeRendererActivity.class);

    @Test
    public void testWithoutIntent() {
        onView(withText(R.string.no_joke_available))
                .inRoot(
                        withDecorView(
                                not(
                                        is(
                                                mActivityTestRule
                                                        .getActivity()
                                                        .getWindow()
                                                        .getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testWithIntent() {
        Intent intent = new Intent();
        intent.putExtra(JokeRendererActivity.EXTRA_JOKE_RENDERER_JOKE, "Hope Hope");
        mActivityTestRule.launchActivity(intent);

        onView(withText("Hope Hope")).check(matches(isCompletelyDisplayed()));
    }
}
