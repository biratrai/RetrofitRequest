package com.gooner10.retrofitsample.detailpost;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gooner10.retrofitsample.R;
import com.gooner10.retrofitsample.model.Posts;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView Adapter for the DetailActivity RecyclerView
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private List<Posts> postsList = new ArrayList<>();

    private final Caller caller;

    public PostAdapter(List<Posts> postsList, Caller caller) {
        this.postsList = postsList;
        this.caller = caller;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Inflate the custom layout
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.single_row_post, parent, false);
        // Return a new holder instance
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.postTitle.setText(postsList.get(position).getTitle());
        holder.postBody.setText(postsList.get(position).getBody());
        Log.d("LOG_TAG", "addData:" + postsList.get(position).getTitle());
    }

    public void addData(List<Posts> postsList) {
        this.postsList = postsList;
        notifyDataSetChanged();
        Log.d("LOG_TAG", "addData:" + postsList.size());
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView postTitle;
        final TextView postBody;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            postTitle = itemView.findViewById(R.id.postTitle);
            postBody = itemView.findViewById(R.id.postBody);
        }

        @Override
        public void onClick(View v) {
            Log.d("TAG", "onClick " + getAdapterPosition() + " " + postsList.get(getAdapterPosition()));
            caller.editPost(postsList.get(getAdapterPosition()));
        }
    }

    public interface Caller {
        void editPost(Posts posts);
    }
}
