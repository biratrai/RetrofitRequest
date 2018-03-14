package com.gooner10.ifactortest.user;

import com.gooner10.ifactortest.model.Users;
import com.gooner10.ifactortest.network.ApiService;

import java.util.List;

/**
 * This specifies the contract between the UserActivity view and the User presenter.
 */
public interface UserContract {
    interface View {
        void displayUserData(List<Users> usersList);

        void displayErrorData();
    }

    interface UserPresenter {
        void loadUserData(ApiService service);
    }
}