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

import com.example.agilesprintersapp.Adapter.MessageAdapter;
import com.example.agilesprintersapp.Model.Chat;
import com.example.agilesprintersapp.Model.User;
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
    private String sender, receiver;

    DatabaseReference reference;

    List<Chat> mChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);


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
        }
        imageview.setImageURI(fileUri);

        String msg = caption.getText().toString();

        userid = intent.getStringExtra("userid");
        sender = intent.getStringExtra("sender");
        receiver = intent.getStringExtra("receiver");
        message = intent.getStringExtra("message");
        checker = intent.getStringExtra("checker");
        msg = intent.getStringExtra("caption");

        Exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = caption.getText().toString();
                    sendMessage(sender, receiver, message, checker, time, msg);

                finish();
            }
        });
    }



    public static void sendMessage(String sender, String receiver, String message, String type, String time, String msg) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("message", message);
        hashMap.put("type", type);
        hashMap.put("time", time);
        hashMap.put("isseen", false);
        hashMap.put("caption", msg);

        reference.child("Chat").push().setValue(hashMap);

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

}


