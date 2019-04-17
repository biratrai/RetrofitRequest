package com.gooner10.retrofitsample.user;

import android.support.test.rule.ActivityTestRule;

import com.gooner10.retrofitsample.MockWebServerRule;
import com.gooner10.retrofitsample.R;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import okhttp3.mockwebserver.MockResponse;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class UserActivityTest {

    @Rule
    public ActivityTestRule<UserActivity> activityTestRule = new ActivityTestRule<>(UserActivity.class
            , true, false);

    @Rule
    public MockWebServerRule mockWebServerRule = new MockWebServerRule();

    @Rule
    OkHttp3IdlingResource okHttp3IdlingResource = OkHttp3IdlingResource.create("OkHttp", new OkHttpClient());

    @Test
    public void timeOut() {
        mockWebServerRule.server.enqueue(new MockResponse().setBody("")
                .throttleBody(1, 1, TimeUnit.SECONDS));

        activityTestRule.launchActivity(null);


        onView(withId(R.id.error_text))
                .check(matches(withText("No Data available.")));
    }
}