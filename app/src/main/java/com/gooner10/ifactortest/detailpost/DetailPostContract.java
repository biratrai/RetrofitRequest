package com.gooner10.ifactortest.detailpost;

import com.gooner10.ifactortest.model.NewPost;
import com.gooner10.ifactortest.model.Posts;

import java.util.List;

/**
 * This specifies the contract between the DetailActivity view and the Detail presenter.
 */
public interface DetailPostContract {
    interface View {
        void displayPostData(List<Posts> postsList);
    }

    interface DetailPostPresenter {
        void loadPostsData(String userId);

        void editPostData(Posts editPost);

        void sendPostData(NewPost newPost);
    }
}
