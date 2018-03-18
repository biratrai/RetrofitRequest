package com.gooner10.retrofitsample.network;


import com.gooner10.retrofitsample.model.NewPost;
import com.gooner10.retrofitsample.model.Posts;
import com.gooner10.retrofitsample.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

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