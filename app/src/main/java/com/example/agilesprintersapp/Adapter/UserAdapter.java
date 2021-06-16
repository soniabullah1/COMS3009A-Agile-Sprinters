package com.example.agilesprintersapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import com.example.agilesprintersapp.MessageActivity;
import com.example.agilesprintersapp.Model.Chat;
import com.example.agilesprintersapp.Model.User;
import com.example.agilesprintersapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private final Context mContext;
    private final List<User> mUsers;
    private boolean ischat;

    String lastMessage;

    public UserAdapter (Context mContext, List<User> mUsers, boolean ischat){
        this.mUsers = mUsers;
        this.mContext = mContext;
        this.ischat = ischat;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username;
        public ImageView profile_image;
        public TextView last_msg;

        private ImageView imgOn;
        private ImageView imgOff;

        public ViewHolder(View itemView){
            super(itemView);
            username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.profile_image);
            last_msg = itemView.findViewById(R.id.last_msg);

            imgOn = itemView.findViewById(R.id.img_on);
            imgOff = itemView.findViewById(R.id.img_off);


        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        User user = mUsers.get(position);
        holder.username.setText(user.getUsername());
        if(user.getImageURL().equals("default")){
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }else{
            Glide.with(mContext).load(user.getImageURL()).into(holder.profile_image);
        }

        if (ischat){
            lastMessage(user.getId(), holder.last_msg);
        } else {
            holder.last_msg.setVisibility(View.GONE);
        }

        if (ischat){
            if(user.getStatus() !=null){
                 if (user.getStatus().equals("online")){
                     holder.imgOn.setVisibility(View.VISIBLE);
                     holder.imgOff.setVisibility(View.GONE);
                 } else {
                     holder.imgOn.setVisibility(View.GONE);
                     holder.imgOff.setVisibility(View.VISIBLE);
            }
            }
        } else {
            holder.imgOn.setVisibility(View.GONE);
            holder.imgOff.setVisibility(View.GONE);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("userid", user.getId());
                mContext.startActivity(intent);
            }
        });

        holder.profile_image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final Dialog nagDialog = new Dialog(mContext,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                nagDialog.setCancelable(false);
                nagDialog.setContentView(R.layout.preview_image);
                ImageButton btnClose = (ImageButton) nagDialog.findViewById(R.id.btnIvClose);
                ImageView ivPreview = (ImageView)nagDialog.findViewById(R.id.iv_preview_image);
                Glide.with(mContext).load(user.getImageURL()).into(ivPreview);

                if(user.getImageURL().equals("default")){
                    holder.profile_image.setImageResource(R.mipmap.ic_launcher);
                }else{
                    Glide.with(mContext).load(user.getImageURL()).into(holder.profile_image);
                }

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
        return mUsers.size();
    }

    //check for last message
    private void lastMessage(final String userid, final TextView last_msg){
        lastMessage = "default";
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chat");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Chat chat = snapshot.getValue(Chat.class);
                    if (firebaseUser != null && chat != null) {
                        if(firebaseUser.getUid().equals(chat.getReceiver()) && userid.equals(chat.getSender()) || userid.equals(chat.getReceiver()) && firebaseUser.getUid().equals(chat.getSender())){
                            lastMessage = chat.getMessage();
                        }
                    }
                }

                switch (lastMessage){
                    case  "default":
                        last_msg.setText("No Message");
                        break;

                    default:
                        last_msg.setText(lastMessage);
                        break;
                }

                lastMessage = "default";
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
