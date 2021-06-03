package com.example.agilesprintersapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agilesprintersapp.MessageActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agilesprintersapp.Model.Chat;
import com.example.agilesprintersapp.Model.User;
import com.example.agilesprintersapp.R;
import com.example.agilesprintersapp.StoryActivity;


import java.util.List;

import xute.storyview.StoryView;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder>{

    private final Context mContext;
    private final List<User> mUsers;


    public StoryAdapter(Context mContext, List<User> mUsers, boolean b){
        this.mUsers = mUsers;
        this.mContext = mContext;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username;
        public TextView timeStamp;
        public ImageView profile_image;


        public ViewHolder(View itemView){
            super(itemView);
            username = itemView.findViewById(R.id.username);
            timeStamp = itemView.findViewById(R.id.storyTimeStamp);
            profile_image = itemView.findViewById(R.id.profile_image);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_itemstory, parent, false);
        return new StoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //storyView = view.findViewById(R.id.story_view);
        User user = mUsers.get(position);
        holder.username.setText(user.getUsername());
        //holder.timeStamp.setText(user.);
//        if(user.getImageURL().equals("default")){
//            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
//        }else{
//            Glide.with(mContext).load(user.getImageURL()).into(holder.profile_image);
//        }

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(mContext, StoryActivity.class);
                intent.putExtra("userid", user.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }



}
