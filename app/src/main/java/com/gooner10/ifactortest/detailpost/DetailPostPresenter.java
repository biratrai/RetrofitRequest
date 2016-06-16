package com.gooner10.ifactortest.detailpost;

import android.util.Log;

import com.gooner10.ifactortest.model.NewPost;
import com.gooner10.ifactortest.model.Posts;
import com.gooner10.ifactortest.network.ApiService;
import com.gooner10.ifactortest.network.ServiceGenerator;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Listens to user actions from the UI ({@link DetailActivity}), retrieves the data and updates the
 * UI as required.
 */
public class DetailPostPresenter implements DetailPostContract.DetailPostPresenter {
    private static final String LOG_TAG = DetailPostPresenter.class.getSimpleName();
    private final DetailPostContract.View mPostView;
    private List<Posts> postsList;
    private ApiService service;

    public DetailPostPresenter(DetailPostContract.View postView) {
        this.mPostView = postView;
    }

    @Override
    public void loadPostsData(String userId) {
        callRetrofitService(userId);
    }

    private void callRetrofitService(String userId) {
        // prepare call in Retrofit 2.0
        service = ServiceGenerator.createService(ApiService.class);

        Call<List<Posts>> call = service.getUserPosts(userId);
        //asynchronous call
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Response<List<Posts>> response, Retrofit retrofit) {
                Log.d(LOG_TAG, "Response code: " + response.code());
                Log.d(LOG_TAG, "Response isSuccess:" + response.isSuccess());
                if (response.isSuccess()) {
                    postsList = response.body();
                    mPostView.displayPostData(postsList);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(LOG_TAG, "Retrofit onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void editPostData(Posts editPost) {
        Call<Posts> editPostCall = service.editUserPost(editPost.getId(), editPost);

        editPostCall.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Response<Posts> response, Retrofit retrofit) {
                Log.d(LOG_TAG, "onResponse editPostData: " + response.isSuccess());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(LOG_TAG, "onFailure editPostData: " + t.getMessage());
            }
        });
        loadPostsData(editPost.getUserId());
    }

    @Override
    public void sendPostData(NewPost newPost) {
        Call<NewPost> newPostCall = service.putNewUserPost(newPost);

        newPostCall.enqueue(new Callback<NewPost>() {
            @Override
            public void onResponse(Response<NewPost> response, Retrofit retrofit) {
                Log.d(LOG_TAG, "onResponse sendPostData: " + response.isSuccess());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(LOG_TAG, "onFailure sendPostData: " + t.getMessage());
            }
        });
        loadPostsData(newPost.getUserId());
    }
}
