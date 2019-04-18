package com.gooner10.retrofitsample;

import com.gooner10.retrofitsample.network.ServiceGenerator;
import com.jakewharton.espresso.OkHttp3IdlingResource;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;

public class OkHttpIdlingResourceRule implements TestRule {
    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                IdlingResource idlingResource = OkHttp3IdlingResource.create(
                        "okhttp", ServiceGenerator.getLoggingCapableHttpClient());
                IdlingRegistry.getInstance().register(idlingResource);

                base.evaluate();

                IdlingRegistry.getInstance().unregister(idlingResource);
            }
        };
    }
}
