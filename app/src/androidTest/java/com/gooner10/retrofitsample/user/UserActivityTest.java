package com.gooner10.retrofitsample.user;

import android.support.test.rule.ActivityTestRule;

import com.gooner10.retrofitsample.MockWebServerRule;
import com.jakewharton.espresso.OkHttp3IdlingResource;

import org.junit.Rule;

import okhttp3.OkHttpClient;

public class UserActivityTest {

    @Rule
    ActivityTestRule<UserActivity> activityTestRule = new ActivityTestRule<>(UserActivity.class
            , true, false);

    @Rule
    MockWebServerRule mockWebServerRule = new MockWebServerRule();

    @Rule
    OkHttp3IdlingResource okHttp3IdlingResource = OkHttp3IdlingResource.create("OkHttp", new OkHttpClient());


}