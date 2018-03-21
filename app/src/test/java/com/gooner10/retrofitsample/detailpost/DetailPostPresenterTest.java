package com.gooner10.retrofitsample.detailpost;

import com.gooner10.retrofitsample.model.Posts;
import com.gooner10.retrofitsample.network.ApiService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Test clas for {@link DetailPostPresenter}
 */
@RunWith(JUnit4.class)
public class DetailPostPresenterTest {
    private DetailPostPresenter postPresenter;
    private List<Posts> posts;

    @Mock
    DetailPostContract.View postView;

    @Mock
    ApiService service;

    @Mock
    Call<List<Posts>> mockLoadPostCall;

    @Mock
    Call<Posts> mockEditPostCall;

    @Mock
    ResponseBody responseBody;

    //    @Mock
    Posts editPost;

    @Captor
    ArgumentCaptor<Callback<List<Posts>>> loadPostArgumentCaptor;

    @Captor
    ArgumentCaptor<Callback<Posts>> editPostArgumentCaptor;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        postPresenter = new DetailPostPresenter(postView, service);
        posts = Collections.singletonList(new Posts("1",
                "ola",
                "body",
                "12"));
        editPost = new Posts("1",
                "title",
                "body",
                "id");
    }

    @Test
    public void loadPostsData_displaysPostData_whenResponseIsSuccessful() throws InterruptedException, IOException {
        when(service.getUserPosts("1")).thenReturn(mockLoadPostCall);
        Response<List<Posts>> response = Response.success(posts);

        postPresenter.loadPostsData("1");

        verify(mockLoadPostCall).enqueue(loadPostArgumentCaptor.capture());
        loadPostArgumentCaptor.getValue().onResponse(mockLoadPostCall, response);

        verify(postView, times(1)).displayPostData(ArgumentMatchers.<Posts>anyList());
    }

    @Test
    public void loadPostsData_displaysErrorData_whenResponseIsFailure() throws Exception {
        when(service.getUserPosts("1")).thenReturn(mockLoadPostCall);
        Throwable throwable = new Throwable(new RuntimeException());

        postPresenter.loadPostsData("1");

        verify(mockLoadPostCall).enqueue(loadPostArgumentCaptor.capture());
        loadPostArgumentCaptor.getValue().onFailure(null, throwable);

        verify(postView).displayErrorData();
    }

    @Test
    public void loadPostData_shouldDoNothing_whenBadRequest() throws Exception {
        when(service.getUserPosts("1")).thenReturn(mockLoadPostCall);
        Response<List<Posts>> response = Response.error(500, responseBody);

        postPresenter.loadPostsData("1");

        verify(mockLoadPostCall).enqueue(loadPostArgumentCaptor.capture());
        loadPostArgumentCaptor.getValue().onResponse(null, response);

        verifyZeroInteractions(postView);
    }

    @Test
    public void editPostData_displaysEditedPostData_whenResponseIsSuccessful() throws Exception {
        when(service.editUserPost(editPost.getId(), editPost)).thenReturn(mockEditPostCall);
        Response<Posts> response = Response.success(editPost);

        postPresenter.editPostData(editPost);

        verify(mockEditPostCall).enqueue(editPostArgumentCaptor.capture());
        editPostArgumentCaptor.getValue().onResponse(null, response);

        verify(postView).displayEditPostData(editPost);
    }
}