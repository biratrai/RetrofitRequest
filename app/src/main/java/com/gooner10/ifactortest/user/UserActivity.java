package com.gooner10.ifactortest.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gooner10.ifactortest.R;
import com.gooner10.ifactortest.databinding.ActivityMainBinding;
import com.gooner10.ifactortest.model.Users;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity implements UserContract.View {

    private final List<Users> usersList = new ArrayList<>();
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding layout = DataBindingUtil.setContentView(this, R.layout.activity_main);
        RecyclerView recyclerView = layout.recyclerViewMain;
        adapter = new UserAdapter(usersList, this);

        // Load user Data
        UserContract.UserPresenter userPresenter = new UserPresenter(this);
        userPresenter.loadUserData();

        // Set recycler adapter and layout
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void displayUserData(List<Users> usersList) {
        adapter.addData(usersList);
    }
}
