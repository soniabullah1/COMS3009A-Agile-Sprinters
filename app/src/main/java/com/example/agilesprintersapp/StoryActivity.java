package com.example.agilesprintersapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.agilesprintersapp.Model.Story;
import com.example.agilesprintersapp.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StoryActivity extends AppCompatActivity {

    DatabaseReference reference;
    FirebaseUser fuser;
    StorageReference storageReference;

    private ImageView story;
    private ImageView profile_image;
    private Button exit;
    private TextView caption, time, username;
    private String ImageURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        story = findViewById(R.id.story);
        exit = findViewById(R.id.close);
        caption = findViewById(R.id.storyCap);
        time = findViewById(R.id.storyTimeStamp);
        username = findViewById(R.id.username);
        profile_image = findViewById(R.id.profile_image);


        Intent intent = getIntent();
        String sender = intent.getStringExtra("sender");
        String username1 = intent.getStringExtra("username");
        String story1 = intent.getStringExtra("story");
        String time1 = intent.getStringExtra("time");
        String caption1 = intent.getStringExtra("caption");

        Glide.with(StoryActivity.this).load(story1).into(story);
        caption.setText(caption1);
        time.setText(convertTime(time1));
        username.setText(username1);

        //display profile image in story activity
        ImageURL = getCurrentUserPicture(sender);
        //Glide.with(StoryActivity.this).load(ImageURL).into(profile_image);
        if(ImageURL != null) {
            if (ImageURL.equals("default")) {
                profile_image.setImageResource(R.mipmap.ic_launcher);
            } else {
                Glide.with(getApplicationContext()).load(ImageURL).into(profile_image);
            }
        }





        exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();
            }
        });

        storageReference = FirebaseStorage.getInstance().getReference("Image Files");


        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (fuser != null) {
            reference = FirebaseDatabase.getInstance().getReference("stories").child(fuser.getUid());

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    Story myStory = snapshot.getValue(Story.class);

//                    if(myStory.getCaption() != null) {
//                        caption.setText(myStory.getCaption());
//                    }

                    if (myStory != null && myStory.getSender() != null) {
//                        if (myStory.getStory().equals("default")) {
//                            story.setImageResource(R.mipmap.ic_launcher);
//                        }

                        //else {
                            //Glide.with(getApplicationContext()).load(myStory.getStory()).into(story);
                        //}

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public String convertTime(String time){
        DateFormat formatter = new SimpleDateFormat("d MMM, HH:mm a");
        String date = formatter.format(Calendar.getInstance().getTime());String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String dateString = formatter.format(new Date(Long.parseLong(time)));
        return dateString;
    }

    public String getCurrentUserPicture(String id){


        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");

        reference .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    if (firebaseUser != null) {

                        if (user != null && user.getId() != null && user.getId().equals(id)){
                            ImageURL = user.getImageURL();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return ImageURL;
    }
}