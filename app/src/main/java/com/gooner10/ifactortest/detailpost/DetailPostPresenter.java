package com.gooner10.ifactortest.detailpost;

import android.util.Log;

import com.gooner10.ifactortest.model.NewPost;
import com.gooner10.ifactortest.model.Posts;
import com.gooner10.ifactortest.network.ApiService;
import com.gooner10.ifactortest.network.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Listens to user actions from the UI ({@link DetailActivity}), retrieves the data and updates the
 * UI as required.
 */
public class DetailPostPresenter implements DetailPostContract.DetailPostPresenter {
    private static final String TAG = DetailPostPresenter.class.getSimpleName();
    private final DetailPostContract.View postView;
    private List<Posts> postsList;
    private ApiService service;

    public DetailPostPresenter(DetailPostContract.View postView) {
        this.postView = postView;
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
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                Log.d(TAG, "Response code: " + response.code());
                Log.d(TAG, "Response isSuccess:" + response.isSuccessful());
                if (response.isSuccessful()) {
                    postsList = response.body();
                    postView.displayPostData(postsList);
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Log.d(TAG, "Retrofit onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void editPostData(Posts editPost) {
        Call<Posts> editPostCall = service.editUserPost(editPost.getId(), editPost);

        editPostCall.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                Log.d(TAG, "onResponse editPostData: " + response.isSuccessful());
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                Log.d(TAG, "onFailure editPostData: " + t.getMessage());
            }
        });
        loadPostsData(editPost.getUserId());
    }

    @Override
    public void sendPostData(NewPost newPost) {
        Call<NewPost> newPostCall = service.putNewUserPost(newPost);

        newPostCall.enqueue(new Callback<NewPost>() {
            @Override
            public void onResponse(Call<NewPost> call, Response<NewPost> response) {
                Log.d(TAG, "onResponse sendPostData: " + response.isSuccessful());
            }

            @Override
            public void onFailure(Call<NewPost> call, Throwable t) {
                Log.e(TAG, "onFailure sendPostData: " + t.getMessage());
            }
        });
        loadPostsData(newPost.getUserId());
    }
}
