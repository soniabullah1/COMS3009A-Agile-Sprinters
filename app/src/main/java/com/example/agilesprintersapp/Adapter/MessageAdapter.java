package com.example.agilesprintersapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agilesprintersapp.MessageActivity;
import com.example.agilesprintersapp.Model.Chat;
import com.example.agilesprintersapp.Model.User;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agilesprintersapp.Model.User;
import com.example.agilesprintersapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private final Context mContext;
    private final List<Chat> mChat;
    private final String imageurl;

    FirebaseUser fuser;

    public MessageAdapter (Context mContext, List<Chat> mChat, String imageurl){
        this.mChat = mChat;
        this.mContext = mContext;
        this.imageurl = imageurl;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView show_message;
        public ImageView profile_image;
        public ImageView messagePicture;

        public  TextView txt_seen;

        public ViewHolder(View itemView){
            super(itemView);
            show_message = itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.profile_image);
            messagePicture = itemView.findViewById(R.id.message_image_view);

            txt_seen = itemView.findViewById(R.id.txt_seen);
        }
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == MSG_TYPE_RIGHT) {
            view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
        }else{

            view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);

        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Chat chat = mChat.get(position);
        holder.show_message.setText(chat.getMessage());

        if (imageurl.equals("default")) {

            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }else{

            Glide.with(mContext).load(imageurl).into(holder.profile_image);
        }

        if(position == mChat.size()-1){
            if(chat.isIsseen()){
                holder.txt_seen.setText("Seen");
            }else{
                holder.txt_seen.setText("Delivered");
            }
        }else{
            holder.txt_seen.setVisibility(View.GONE);
        }

        holder.show_message.setVisibility(View.GONE);
        holder.messagePicture.setVisibility(View.GONE);

        if(chat.getType().equals("text")){
           holder.show_message.setVisibility(View.VISIBLE);
            holder.show_message.setText(chat.getMessage());

        }

        else if (chat.getType().equals("image")){
            holder.messagePicture.setVisibility(View.VISIBLE);

            Glide.with(mContext).load(chat.getMessage()).into(holder.messagePicture);

        }


    }



    @Override
    public int getItemCount() {
        return mChat.size();
    }

    @Override
    public int getItemViewType(int position){
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if(mChat.get(position).getSender().equals(fuser.getUid())){
            return MSG_TYPE_RIGHT;
        }else{
            return MSG_TYPE_LEFT;
        }
    }


}