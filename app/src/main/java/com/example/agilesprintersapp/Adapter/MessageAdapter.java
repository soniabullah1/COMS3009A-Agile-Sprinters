package com.example.agilesprintersapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agilesprintersapp.Model.Chat;
import com.example.agilesprintersapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        public TextView time_tv;
        public TextView caption;

        public  TextView txt_seen;

        public ViewHolder(View itemView){
            super(itemView);
            show_message = itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.profile_image);
            messagePicture = itemView.findViewById(R.id.message_image_view);
            time_tv = itemView.findViewById(R.id.time_tv);
            caption = itemView.findViewById(R.id.caption);

            txt_seen = itemView.findViewById(R.id.txt_seen);

        }

        public String convertTime(String time){
            DateFormat formatter = new SimpleDateFormat("d MMM, HH:mm a");
            String date = formatter.format(Calendar.getInstance().getTime());String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            String dateString = formatter.format(new Date(Long.parseLong(time)));
            return dateString;
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

        if(chat.getTime()!=null && !chat.getTime().trim().equals("")) {
            holder.time_tv.setText(holder.convertTime(chat.getTime()));
        }

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
        }
        holder.show_message.setVisibility(View.GONE);
        holder.messagePicture.setVisibility(View.GONE);
        holder.caption.setVisibility(View.GONE);

        if(chat.getType().equals("text")){
            holder.show_message.setVisibility(View.VISIBLE);
            holder.show_message.setText(chat.getMessage());

        }

        else if (chat.getType().equals("image")){

                holder.messagePicture.setVisibility(View.VISIBLE);
                holder.caption.setVisibility(View.VISIBLE);

                Glide.with(mContext).load(chat.getMessage()).into(holder.messagePicture);
                holder.caption.setText(chat.getCaption());

        }

        holder.messagePicture.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final Dialog nagDialog = new Dialog(mContext,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                nagDialog.setCancelable(false);
                nagDialog.setContentView(R.layout.preview_image);
                ImageButton btnClose = (ImageButton) nagDialog.findViewById(R.id.btnIvClose);
                ImageView ivPreview = (ImageView)nagDialog.findViewById(R.id.iv_preview_image);
                Glide.with(mContext).load(chat.getMessage()).into(ivPreview);

                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {

                        nagDialog.dismiss();
                    }
                });
                nagDialog.show();

                return true;
            }
        });

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