package com.gooner10.retrofitsample.user;

import com.gooner10.retrofitsample.MockWebServerRule;
import com.gooner10.retrofitsample.OkHttpIdlingResourceRule;
import com.gooner10.retrofitsample.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import okhttp3.mockwebserver.MockResponse;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserActivityTest {

    @Rule
    public ActivityTestRule<UserActivity> activityTestRule = new ActivityTestRule<>(UserActivity.class
            , true, false);

    @Rule
    public MockWebServerRule mockWebServerRule = new MockWebServerRule();

    @Rule
    public OkHttpIdlingResourceRule okHttpIdlingResourceRule = new OkHttpIdlingResourceRule();


    @Test
    public void showsText() {
        activityTestRule.launchActivity(null);
        onData(anything())
                .inAdapterView(withId(R.id.recycler_view_main))
                .atPosition(0)
                .check(matches(withText("Leanne Graham")));
    }

    @Test
    public void timeOut() {
        mockWebServerRule.server.enqueue(new MockResponse().setBody("")
                .throttleBody(1, 1, TimeUnit.SECONDS));

        activityTestRule.launchActivity(null);

        onView(withId(R.id.error_text))
                .check(matches(withText("No Data available.")));
    }

    @Test
    public void status404() {
        mockWebServerRule.server.enqueue(new MockResponse().setResponseCode(404));

        activityTestRule.launchActivity(null);

        onView(withId(R.id.error_text))
                .check(matches(withText("No Data available.")));
    }
}