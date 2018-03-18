package com.gooner10.retrofitsample.user;

import android.util.Log;

import com.gooner10.retrofitsample.model.Users;
import com.gooner10.retrofitsample.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Listens to user actions from the UI ({@link UserActivity}), retrieves the data and updates the
 * UI as required.
 */
public class UserPresenter implements UserContract.UserPresenter {
    private static final String LOG_TAG = UserPresenter.class.getSimpleName();
    private List<Users> usersList;
    private final UserContract.View userView;

    public UserPresenter(UserContract.View mUserView) {
        this.userView = mUserView;
    }

    @Override
    public void loadUserData(ApiService service) {
        callRetrofitService(service);
    }

    private void callRetrofitService(ApiService service) {
        Call<List<Users>> call = service.getUserData();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (response.isSuccessful()) {
                    usersList = response.body();
                    userView.displayUserData(usersList);
                    Log.d(LOG_TAG, "Retrofit onResponse: " + usersList);
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Log.d(LOG_TAG, "Retrofit onFailure: " + t.getMessage());
                userView.displayErrorData();
            }
        });
    }
}
