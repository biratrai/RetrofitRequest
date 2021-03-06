package com.gooner10.retrofitsample.detailpost;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.gooner10.retrofitsample.R;
import com.gooner10.retrofitsample.databinding.ActivityDetailBinding;
import com.gooner10.retrofitsample.model.NewPost;
import com.gooner10.retrofitsample.model.Posts;
import com.gooner10.retrofitsample.network.ApiService;
import com.gooner10.retrofitsample.network.ServiceGenerator;
import com.gooner10.retrofitsample.util.Util;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DetailActivity extends AppCompatActivity implements Button.OnClickListener, PostAdapter.Caller,
        DetailPostContract.View {
    public static final String TAG = DetailActivity.class.getSimpleName();
    private List<Posts> postsList = new ArrayList<>();
    private PostAdapter adapter;
    private String userId;
    private DetailPostContract.DetailPostPresenter detailPostPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        // Receive intent from UserActivity
        userId = getIntent().getStringExtra("userId");

        RecyclerView recyclerView = detailBinding.recyclerViewDetail;
        adapter = new PostAdapter(postsList, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load User post data
        detailPostPresenter = new DetailPostPresenter(this, ServiceGenerator.createService(ApiService.class));
        detailPostPresenter.loadPostsData(userId);

        Button newPostBtn = detailBinding.btnNewPost;
        newPostBtn.setOnClickListener(this);

        Button ascendingBtn = detailBinding.btnAscending;
        ascendingBtn.setOnClickListener(this);

        Button descendingBtn = detailBinding.btnDescending;
        descendingBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnNewPost) {
            getUserPostDialog(null, true, userId);
        } else if (v.getId() == R.id.btnAscending) {
            Util.sortAscending(postsList);
            adapter.addData(postsList);
        } else if (v.getId() == R.id.btnDescending) {
            Util.sortDescending(postsList);
            adapter.addData(postsList);
        }
    }

    // Displays AlertDialog and presents editText for user input
    private void getUserPostDialog(final Posts posts, final boolean isNewPost, final String userId) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailActivity.this);
        @SuppressLint("InflateParams")
        View dialogView = getLayoutInflater().inflate(R.layout.input_dialog_box, null);
        alertDialogBuilder.setView(dialogView);

        final EditText newBodyText = dialogView.findViewById(R.id.newPostBody);
        final EditText newTitleText = dialogView.findViewById(R.id.newPostTitle);

        /*
         * This is the Update Post
         */
        String title, buttonTitle;

        if (!isNewPost) {
            title = "Update Post";
            buttonTitle = "Update";

            newBodyText.setText(posts.getBody());
            newTitleText.setText(posts.getTitle());
        } else {
            title = "Add a New Post";
            buttonTitle = "Add";
        }

        alertDialogBuilder.setTitle(title);

        // Set up the buttons
        alertDialogBuilder.setPositiveButton(buttonTitle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String mBodyText = newBodyText.getText().toString();
                String mTitleText = newTitleText.getText().toString();

                if (isNewPost) {
                    NewPost newPost = new NewPost(DetailActivity.this.userId, mTitleText, mBodyText);
                    detailPostPresenter.sendPostData(newPost);
                } else {
                    posts.setTitle(mTitleText);
                    posts.setBody(mBodyText);
                    Posts editPost = new Posts(DetailActivity.this.userId, mTitleText, mBodyText, posts.getId());
                    detailPostPresenter.editPostData(editPost);
                }
            }
        });
        // Get the AlertDialog from create() and show
        AlertDialog dialog = alertDialogBuilder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.show();
    }

    @Override
    public void editPost(Posts posts) {
        getUserPostDialog(posts, false, userId);
    }

    @Override
    public void displayPostData(List<Posts> postsList) {
        this.postsList = postsList;
        adapter.addData(postsList);
    }

    @Override
    public void displayErrorData() {
        Log.e(TAG, "displayErrorData: ");
    }

    @Override
    public void displayEditPostData(Posts posts) {
        Log.d(TAG, "displayEditPostData: ");
    }
}
