package com.gooner10.retrofitsample.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.gooner10.retrofitsample.R;
import com.gooner10.retrofitsample.databinding.ActivityMainBinding;
import com.gooner10.retrofitsample.model.Users;
import com.gooner10.retrofitsample.network.ApiService;
import com.gooner10.retrofitsample.network.ServiceGenerator;

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
        userPresenter.loadUserData(ServiceGenerator.createService(ApiService.class));

        // Set recycler adapter and layout
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void displayUserData(List<Users> usersList) {
        adapter.addData(usersList);
    }

    @Override
    public void displayErrorData() {
        Toast.makeText(this, "No Data available", Toast.LENGTH_SHORT).show();
    }
}
