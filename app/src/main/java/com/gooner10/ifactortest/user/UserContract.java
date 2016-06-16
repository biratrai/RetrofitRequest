package com.gooner10.ifactortest.user;

import com.gooner10.ifactortest.model.Users;

import java.util.List;

/**
 * This specifies the contract between the UserActivity view and the User presenter.
 */
public interface UserContract {
    interface View {
        void displayUserData(List<Users> usersList);
    }

    interface UserPresenter {
        void loadUserData();
    }
}