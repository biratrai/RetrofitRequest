package com.gooner10.ifactortest.user;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gooner10.ifactortest.detailpost.DetailActivity;
import com.gooner10.ifactortest.R;
import com.gooner10.ifactortest.util.Util;
import com.gooner10.ifactortest.model.Users;

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.single_row_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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
            userNameText = (TextView) itemView.findViewById(R.id.userName);
            addressText = (TextView) itemView.findViewById(R.id.address);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("userId", usersList.get(getAdapterPosition()).getId());
            context.startActivity(intent);
        }
    }
}
