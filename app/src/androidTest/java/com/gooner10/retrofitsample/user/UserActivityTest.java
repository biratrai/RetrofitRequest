package com.gooner10.retrofitsample.user;

import com.gooner10.retrofitsample.MockWebServerRule;
import com.gooner10.retrofitsample.R;
import com.gooner10.retrofitsample.URL;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import androidx.test.rule.ActivityTestRule;
import okhttp3.mockwebserver.MockResponse;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class UserActivityTest {

    @Rule
    public ActivityTestRule<UserActivity> activityTestRule = new ActivityTestRule<>(UserActivity.class
            , true, false);

    @Rule
    public MockWebServerRule mockWebServerRule = new MockWebServerRule();

    @Before
    public void setUp() {
        URL.API_BASE_URL = mockWebServerRule.server.url("/").toString();
    }

    @Test
    public void showsText() {
        mockWebServerRule.server.enqueue(new MockResponse().setBody(getData()));

        activityTestRule.launchActivity(null);

        onView(withId(R.id.recycler_view_main))
                .check(matches(hasDescendant(withText("Leanne Graham"))));
    }

    @Test
    public void timeOut() {
        mockWebServerRule.server.enqueue(new MockResponse().setBody("")
                .throttleBody(1, 1, TimeUnit.SECONDS));

        activityTestRule.launchActivity(null);

        onView(withId(R.id.error_text))
                .check(matches(withText("EOFException")));
    }

    @Test
    public void status404() {
        mockWebServerRule.server.enqueue(new MockResponse().setResponseCode(404));

        activityTestRule.launchActivity(null);

        onView(withId(R.id.error_text))
                .check(matches(withText("404")));
    }

    @Test
    public void malformedJson() {
        mockWebServerRule.server.enqueue(new MockResponse().setBody("Jason"));

        activityTestRule.launchActivity(null);

        onView(withId(R.id.error_text))
                .check(matches(withText("MalformedJsonException")));
    }


    private String getData(){
        return "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Leanne Graham\",\n" +
                "    \"username\": \"Bret\",\n" +
                "    \"email\": \"Sincere@april.biz\",\n" +
                "    \"address\": {\n" +
                "      \"street\": \"Kulas Light\",\n" +
                "      \"suite\": \"Apt. 556\",\n" +
                "      \"city\": \"Gwenborough\",\n" +
                "      \"zipcode\": \"92998-3874\",\n" +
                "      \"geo\": {\n" +
                "        \"lat\": \"-37.3159\",\n" +
                "        \"lng\": \"81.1496\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"phone\": \"1-770-736-8031 x56442\",\n" +
                "    \"website\": \"hildegard.org\",\n" +
                "    \"company\": {\n" +
                "      \"name\": \"Romaguera-Crona\",\n" +
                "      \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
                "      \"bs\": \"harness real-time e-markets\"\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"Ervin Howell\",\n" +
                "    \"username\": \"Antonette\",\n" +
                "    \"email\": \"Shanna@melissa.tv\",\n" +
                "    \"address\": {\n" +
                "      \"street\": \"Victor Plains\",\n" +
                "      \"suite\": \"Suite 879\",\n" +
                "      \"city\": \"Wisokyburgh\",\n" +
                "      \"zipcode\": \"90566-7771\",\n" +
                "      \"geo\": {\n" +
                "        \"lat\": \"-43.9509\",\n" +
                "        \"lng\": \"-34.4618\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"phone\": \"010-692-6593 x09125\",\n" +
                "    \"website\": \"anastasia.net\",\n" +
                "    \"company\": {\n" +
                "      \"name\": \"Deckow-Crist\",\n" +
                "      \"catchPhrase\": \"Proactive didactic contingency\",\n" +
                "      \"bs\": \"synergize scalable supply-chains\"\n" +
                "    }\n" +
                "  }\n" +
                "]";
    }
}