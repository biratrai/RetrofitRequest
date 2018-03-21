package com.gooner10.retrofitsample.detailpost;

import com.gooner10.retrofitsample.model.NewPost;
import com.gooner10.retrofitsample.model.Posts;

import java.util.List;

/**
 * This specifies the contract between the DetailActivity view and the Detail presenter.
 */
public interface DetailPostContract {
    interface View {
        void displayPostData(List<Posts> postsList);

        void displayErrorData();

        void displayEditPostData(Posts posts);
    }

    interface DetailPostPresenter {
        void loadPostsData(String userId);

        void editPostData(Posts editPost);

        void sendPostData(NewPost newPost);
    }
}
