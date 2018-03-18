package com.gooner10.retrofitsample.network;

import com.gooner10.retrofitsample.detailpost.DetailPostContract;
import com.gooner10.retrofitsample.detailpost.DetailPostPresenter;
import com.gooner10.retrofitsample.model.Posts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test for class {@link ApiService}
 */
public class ApiServiceTest {
    private MockWebServer mockWebServer;
    private ApiService service;

    @Mock
    DetailPostContract.View postView;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        mockWebServer = new MockWebServer();
        service = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);
    }

    @After
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void loadPostsData_displaysPostData_whenSuccessful() throws InterruptedException, IOException {
        enqueueResponse("user-detail.json");
//        List<Posts> userposts = service.getUserPosts("1");
        verify(postView, times(1)).displayPostData(ArgumentMatchers.<Posts>anyList());
    }

    private void enqueueResponse(String fileName) throws IOException {
        enqueueResponse(fileName, Collections.<String, String>emptyMap());
    }

    private void enqueueResponse(String fileName, Map<String, String> headers) throws IOException {
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("api-response/" + fileName);
        BufferedSource source = Okio.buffer(Okio.source(inputStream));
        MockResponse mockResponse = new MockResponse();
        mockWebServer.enqueue(mockResponse
                .setBody(source.readString(StandardCharsets.UTF_8)));
    }
}