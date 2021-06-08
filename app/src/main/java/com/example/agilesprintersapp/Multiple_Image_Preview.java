package com.example.agilesprintersapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class Multiple_Image_Preview extends AppCompatActivity {
    public String userid;
    Button Exit;
    Button btn_send;
    EditText caption;
    ImageSwitcher imageSwitcher;
    Button NextBtn, PreviousBtn;
    private String time, message, msg;
    private String checker = "";
    private String sender, receiver;
    TextView image_position;

    //private String checker = "";
    ValueEventListener seenListener;
    FirebaseUser fuser;
    MessageAdapter messageAdapter;

    DatabaseReference reference;

    List<Chat> mChat;

    private ArrayList<Uri> imageUris;
    private ArrayList<String> stringUris;

    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple__image__preview);
        NextBtn = findViewById(R.id.Nextbtn);
        PreviousBtn = findViewById(R.id.PreviousBtn);
        imageSwitcher = findViewById(R.id.imageSwitcher);
        Exit = findViewById(R.id.close);
        btn_send = findViewById(R.id.button3);
        caption = findViewById(R.id.Caption);
        time = String.valueOf(System.currentTimeMillis());
        image_position = findViewById(R.id.image_position);

        Intent intent = getIntent();

        Bundle args = intent.getBundleExtra("BUNDLE");
        if(imageUris != null) {
            imageUris = (ArrayList<Uri>) args.getSerializable("IMAGES");
            stringUris = (ArrayList<String>) args.getSerializable("STRING_IMAGES");
            imageUris.size();
            stringUris.size();
        }

        userid = intent.getStringExtra("userid");
        sender = intent.getStringExtra("sender");
        receiver = intent.getStringExtra("receiver");
        message = intent.getStringExtra("message");
        checker = intent.getStringExtra("checker");
        msg = intent.getStringExtra("caption");

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {

                ImageView imageView = new ImageView(getApplicationContext());

                if(stringUris != null) {
                    Uri fileUri = Uri.parse(stringUris.get(0));
                    //imageView.setImageURI(fileUri);

                    if(stringUris.size() == 1){
                        imageView.setImageURI(fileUri);
                        NextBtn.setVisibility(View.INVISIBLE);
                        PreviousBtn.setVisibility(View.INVISIBLE);
                    }
                    else{
                        imageView.setImageURI(fileUri);
                        image_position.setText("Image 1 of " + imageUris.size());
                    }
                }
                return imageView;
            }
        });

        PreviousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int positions = position;

                if(position > 0){
                    position--;
                    image_position.setText("Image " + positions + " of " + imageUris.size());
                    imageSwitcher.setImageURI(imageUris.get(position));
                }
                else{
                    Toast.makeText(Multiple_Image_Preview.this, "No Previous Images ...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUris != null) {

                    int positions = position + 2;

                    if (position < imageUris.size() -1) {
                        position++;
                        image_position.setText("Image " + positions + " of " + imageUris.size());
                        imageSwitcher.setImageURI(imageUris.get(position));
                    }
                    else{
                        Toast.makeText(Multiple_Image_Preview.this, "No More Images ...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringUris.clear();
                imageUris.clear();
                finish();
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int a = imageUris.size();

                String msg = caption.getText().toString().trim();

                for(int i = 0; i < a; i++){
                    sendMessage(sender, receiver, imageUris.get(i).toString(), checker, time, msg);
                    //readMessages(sender, userid, );
                }

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
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    if (fuser != null) {
                        String myid = fuser.getUid();
                    }
                    String imageurl = "";
                    if (myid.equals(chat.getReceiver()) && userid.equals(chat.getSender()) ||
                            userid.equals(chat.getReceiver()) && myid.equals(chat.getSender())) {
                        mChat.add(chat);
                    }
                    messageAdapter = new MessageAdapter(Multiple_Image_Preview.this, mChat, imageurl);
                    if (findViewById(R.id.recycler_view12) != null){
                        RecyclerView recyclerView = findViewById(R.id.recycler_view12);
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
