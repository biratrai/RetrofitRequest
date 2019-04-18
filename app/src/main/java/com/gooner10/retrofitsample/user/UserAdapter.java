package com.gooner10.retrofitsample.user;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gooner10.retrofitsample.R;
import com.gooner10.retrofitsample.detailpost.DetailActivity;
import com.gooner10.retrofitsample.model.Users;
import com.gooner10.retrofitsample.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView Adapter for the UserActivity RecyclerView
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<Users> usersList = new ArrayList<>();
    private final Context context;

    public UserAdapter(List<Users> usersList, Context mainActivity) {
        this.usersList = usersList;
        this.context = mainActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.single_row_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.userNameText.setText(usersList.get(position).getName());
        holder.addressText.setText(Util.getAddress(usersList.get(position).getAddress()));
    }

    public void addData(List<Users> usersList) {
        this.usersList = usersList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView userNameText;
        final TextView addressText;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            userNameText = itemView.findViewById(R.id.userName);
            addressText = itemView.findViewById(R.id.address);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("userId", usersList.get(getAdapterPosition()).getId());
            context.startActivity(intent);
        }
    }
}
