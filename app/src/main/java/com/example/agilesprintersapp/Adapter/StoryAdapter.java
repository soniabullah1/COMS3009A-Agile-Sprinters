package com.example.agilesprintersapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.devlomi.circularstatusview.CircularStatusView;
import com.example.agilesprintersapp.Model.Story;
import com.example.agilesprintersapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder>{

    private final Context mContext;
    private final List<Story> mUsers;
    //private final List<Story> mStory;

    Timer timer;


    public StoryAdapter(Context mContext, List<Story> mUsers, boolean b){
        this.mUsers = mUsers;
        this.mContext = mContext;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username;
        public TextView timeStamp;
        public ImageView profile_image;
        public CircularStatusView circularStatusView;


        public ViewHolder(View itemView){
            super(itemView);
            username = itemView.findViewById(R.id.username);
            timeStamp = itemView.findViewById(R.id.storyTimeStamp);
            profile_image = itemView.findViewById(R.id.profile_image);
            circularStatusView = itemView.findViewById(R.id.circular_status_view);

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
        Story user = mUsers.get(position);
        holder.username.setText(user.getUsername());
        holder.timeStamp.setText(convertTime(user.getTime()));
        Glide.with(mContext).load(user.getStory()).into(holder.profile_image);
        holder.circularStatusView.setPortionsCount(1);

        //Story story = mStory.get(position);

        //holder.timeStamp.setText(user.);
//        if(user.getImageURL().equals("default")){
//            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
//        }else{
//            Glide.with(mContext).load(user.getImageURL()).into(holder.profile_image);
//        }

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

//                        Intent intent = new Intent();
//                        intent.setClass(mContext, StoryActivity.class);
//                        intent.putExtra("sender", user.getSender());
//                        intent.putExtra("username", user.getUsername());
//                        intent.putExtra("story", user.getStory());
//                        intent.putExtra("time", user.getTime());
//                        intent.putExtra("caption", user.getCaption());
//                        mContext.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public String convertTime(String time){
        DateFormat formatter = new SimpleDateFormat("d MMM, HH:mm a");
        String date = formatter.format(Calendar.getInstance().getTime());String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String dateString = formatter.format(new Date(Long.parseLong(time)));
        return dateString;
    }
}
