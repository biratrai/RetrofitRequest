package com.gooner10.ifactortest.user;

import android.util.Log;

import com.gooner10.ifactortest.model.Users;
import com.gooner10.ifactortest.network.ApiService;
import com.gooner10.ifactortest.network.ServiceGenerator;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Listens to user actions from the UI ({@link UserActivity}), retrieves the data and updates the
 * UI as required.
 */
public class UserPresenter implements UserContract.UserPresenter {
    private static final String LOG_TAG = UserPresenter.class.getSimpleName();
    List<Users> usersList;
    private final UserContract.View mUserView;

    public UserPresenter(UserContract.View mUserView) {
        this.mUserView = mUserView;
    }

    @Override
    public void loadUserData() {
        callRetrofitService();
    }

    private void callRetrofitService() {
        ApiService service = ServiceGenerator.createService(ApiService.class);
        Call<List<Users>> call = service.getUserData();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Response<List<Users>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    usersList = response.body();
                    mUserView.displayUserData(usersList);
                    Log.d(LOG_TAG, "Retrofit onResponse: " + usersList);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(LOG_TAG, "Retrofit onFailure: " + t.getMessage());
            }
        });
    }
}
