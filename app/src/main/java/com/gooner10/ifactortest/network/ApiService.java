package com.gooner10.ifactortest.network;


import com.gooner10.ifactortest.model.NewPost;
import com.gooner10.ifactortest.model.Posts;
import com.gooner10.ifactortest.model.Users;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ApiService {

    // Get Call for list of users
    @GET("users")
    Call<List<Users>> getUserData();

    // Get Call for list of user's post by userId
    @GET("posts?")
    Call<List<Posts>> getUserPosts(@Query("posts") String param);

    // Post Call for new Post
    @POST("posts")
    Call<NewPost> putNewUserPost(@Body NewPost newPost);

    // Put Call for edited Post
    @PUT("posts/{postId}")
    Call<Posts> editUserPost(@Path("postId") String postId, @Body Posts posts);

}