package com.example.agilesprintersapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agilesprintersapp.Adapter.MessageAdapter;
import com.example.agilesprintersapp.Adapter.UserAdapter;
import com.example.agilesprintersapp.Model.Chat;
import com.example.agilesprintersapp.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Preview extends AppCompatActivity {
    private ImageButton btn_attach_pic;
    private String checker = "";
    private final String myUrl = "";
    ValueEventListener seenListener;
    private StorageTask uploadTask;
    private Uri fileUri;
    public String userid;
    private String messageSenderID;
    private String messageReceiverID;
    Button Exit;
    Button btn_send;
    TextView caption;
    FirebaseUser fuser;
    MessageAdapter messageAdapter;
    ImageView imageview;
    DataSnapshot snapshot;
    private String time, message;
    private String sender, receiver, msg, username;

    private UserAdapter userAdapter;
    private List<User> mUsers;

    DatabaseReference reference;

    List<Chat> mChat;
//    User user = snapshot.getValue(User.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);




        mUsers = new ArrayList<>();
        readUsers();

        Exit = findViewById(R.id.close);
        btn_send = findViewById(R.id.button3);
        caption = findViewById(R.id.Caption);
        time = String.valueOf(System.currentTimeMillis());
        imageview = findViewById(R.id.imageView56);

        Intent intent = getIntent();
        String image_path = intent.getStringExtra("imagePath");

        //I have to put this condition or else i cannot test Preview at all - Rushil
        if(image_path != null) {
            Uri fileUri = Uri.parse(image_path);
            imageview.setImageURI(fileUri);
        }

        userid = intent.getStringExtra("userid");
        sender = intent.getStringExtra("sender");
        message = intent.getStringExtra("message");
        time = intent.getStringExtra("time");
        checker = intent.getStringExtra("checker");
        username = getCurrentUserName(sender);
        Exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String msg = caption.getText().toString().trim();


                //sendMessage(sender, mUsers, message, "image", time, msg);

                    //System.out.println(fuser.getDisplayName());
                    sendStory(sender, message, time, msg, username);

                finish();
            }
        });
    }

    public static void sendMessage(String sender, List<User> contacts, String message, String type, String time, String msg) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", contacts);
        hashMap.put("message", message);
        hashMap.put("type", type);
        hashMap.put("time", time);
        //hashMap.put("isseen", false);
        hashMap.put("caption", msg);

        reference.child("stories").push().setValue(hashMap);

    }

    public static void sendStory(String sender, String story, String time, String caption, String username){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);

        hashMap.put("story", story);

        hashMap.put("time", time);
        //hashMap.put("isseen", false);
        hashMap.put("caption", caption);
        hashMap.put("username", username);

        reference.child("stories").push().setValue(hashMap);
    }

    public void readMessages(String myid, String userid, String imageurl) {
        mChat = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Chat");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mChat.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    Chat chat = snapshot.getValue(Chat.class);
                    // Have to do this or cannot test Preview at all and causes build to fail -Rushil
                    if(fuser != null) {
                        String myid = fuser.getUid();
                    }
                    String imageurl = "";
                    if(myid.equals(chat.getReceiver()) && userid.equals(chat.getSender())||
                            userid.equals(chat.getReceiver()) && myid.equals(chat.getSender()))
                    {
                        mChat.add(chat);
                    }
                    messageAdapter = new MessageAdapter(Preview.this, mChat, imageurl);
                    RecyclerView recyclerView = findViewById(R.id.recycler_view12);
                    // Have to do this or cannot test Preview at all and causes build to fail -Rushil
                    if(recyclerView != null) {
                        recyclerView.setAdapter(messageAdapter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void seenMessage(String userid) {
        reference = FirebaseDatabase.getInstance().getReference("Chat");
        seenListener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    User user = snapshot.getValue(User.class);

                    if (user.getId()!=null && user.getId().equals(fuser.getUid()) && chat.getReceiver().equals(fuser.getUid()) && chat.getSender().equals(userid)) {
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("isseen", true);
                        snapshot.getRef().updateChildren(hashMap);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void readUsers(){

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");

        reference .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    if (firebaseUser != null) {

                        if (user != null && user.getId() != null && !user.getId().equals(firebaseUser.getUid())){
                            mUsers.add(user);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public String getCurrentUserName(String id){


            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");

            reference .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                    for(DataSnapshot snapshot : datasnapshot.getChildren()){
                        User user = snapshot.getValue(User.class);
                        if (firebaseUser != null) {

                            if (user != null && user.getId() != null && user.getId().equals(id)){
                                username = user.getUsername();
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        return username;
    }

}


