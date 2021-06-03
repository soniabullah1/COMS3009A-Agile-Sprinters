package com.example.agilesprintersapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class StoryActivity extends AppCompatActivity {

    DatabaseReference reference;
    FirebaseUser fuser;
    StorageReference storageReference;

    private ImageView story;
    private Button exit;
    private TextView caption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        story = findViewById(R.id.story);
        exit = findViewById(R.id.close);
        caption = findViewById(R.id.storyCap);

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
                            Glide.with(getApplicationContext()).load(myStory.getStory()).into(story);
                        //}

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}