package com.example.agilesprintersapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agilesprintersapp.Adapter.MessageAdapter;
import com.example.agilesprintersapp.Model.Chat;
import com.example.agilesprintersapp.Model.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {

    CircleImageView profile_image;
    TextView username;
    FirebaseUser fuser;
    DatabaseReference reference;
    ImageButton btn_send;
    EditText text_send;
    MessageAdapter messageAdapter;
    List<Chat> mChat;
    ImageButton imageButton;
    RecyclerView recyclerView;
    Intent intent;
    ImageView Image;
    ValueEventListener seenListener;

    private ImageButton btn_attach_pic;
    private String checker = "image";
    private String myUrl = "";
    private StorageTask uploadTask;
    private Uri fileUri;
    public String userid;
    private String messageSenderID;
    private String messageReceiverID;
    private String time;
    private ProgressDialog progressDialog;

    //store image uris in array list
    public ArrayList<Uri> imageUris;
    public ArrayList<String> stringUris;
    private ArrayList<String> captions;

    StorageReference filePath;


    boolean toastMade = true;

    //request code to pick images
    private static final int PICK_IMAGES_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

        recyclerView = findViewById(R.id.recycler_view12);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        imageButton = findViewById(R.id.HomeButton1);
        profile_image = findViewById(R.id.profile_image);
        username = findViewById(R.id.username);
        btn_send = findViewById(R.id.btn_send);
        text_send = findViewById(R.id.text_send);
        btn_attach_pic = findViewById(R.id.btn_attach_pic);
        intent = getIntent();
        userid = intent.getStringExtra("userid");

        imageUris = new ArrayList<>();
        stringUris = new ArrayList<>();


        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MessageActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        btn_attach_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence[] options = new CharSequence[]
                        {
                                "Images",
                                "PDF Files",
                                "Ms Word Files",
                        };
                AlertDialog.Builder builder = new AlertDialog.Builder(MessageActivity.this);
                builder.setTitle("Select the File");

                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i == 0){
                            checker = "image";
                            pickImagesIntent();
                        }
                    }
                });
                builder.show();
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String msg = text_send.getText().toString();
                time = String.valueOf(System.currentTimeMillis());
                if(!msg.equals("")){
                    sendMessage(fuser.getUid(), userid, msg, "text", time);
                }else{
                    Toast.makeText(MessageActivity.this, "Error: Empty message", Toast.LENGTH_SHORT).show();
                    toastMade = true;
                }
                text_send.setText("");
            }
        });

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (userid != null) {
            reference = FirebaseDatabase.getInstance().getReference("User").child(userid);

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User user = snapshot.getValue(User.class);
                    username.setText(user.getUsername());
                    if(user.getImageURL().equals("default")){
                        profile_image.setImageResource(R.mipmap.ic_launcher);

                    }else{
                        Glide.with(getApplicationContext()).load(user.getImageURL()).into(profile_image);
                    }

                    readMessages(fuser.getUid(), userid, user.getImageURL());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        seenMessage(userid);
        if(fuser != null) {
            messageSenderID = fuser.getUid();
        }
        messageReceiverID = userid;
    }

    public void pickImagesIntent(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select image(s)"), PICK_IMAGES_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGES_CODE){

            if (resultCode == Activity.RESULT_OK){

                //if Multiple Images Selected...
                if (data.getClipData() != null){

                    if (!checker.equals("image")){

                    }

                    else if (checker.equals("image")){

                        int count = data.getClipData().getItemCount();

                        for (int i = 0; i < count; i++){
                            fileUri = data.getClipData().getItemAt(i).getUri();
                            imageUris.add(fileUri);
                            if(stringUris != null) {
                                stringUris.add(fileUri.toString());
                            }
                        }

                        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Image Files");
                        DatabaseReference userMessageKeyRef = reference.child("messages")
                                .child(messageSenderID).child(messageReceiverID).push();
                        String messagePushID  = userMessageKeyRef.getKey();
                        StorageReference filePath = storageReference.child(messagePushID + "." + "jpg");
                        uploadTask = filePath.putFile(fileUri);

                        uploadTask.continueWithTask(new Continuation() {
                            @Override
                            public Object then(@NonNull Task task) throws Exception {
                                if (!task.isSuccessful()){
                                    throw task.getException();
                                }
                                return filePath.getDownloadUrl();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if(task.isSuccessful()){
                                    Uri downloadUrl = task.getResult();
                                    myUrl =  downloadUrl.toString();

                                    //Uri a = fileUri;
                                    Intent i = new Intent(MessageActivity.this, Multiple_Image_Preview.class);

                                    Bundle args = new Bundle();
                                    args.putSerializable("IMAGES",(Serializable)imageUris);
                                    args.putSerializable("STRING_IMAGES",(Serializable)stringUris);

                                    i.putExtra("sender", fuser.getUid());
                                    i.putExtra("receiver", userid);
                                    i.putExtra("message", myUrl);
                                    i.putExtra("checker", checker);
                                    i.putExtra("time", time);
                                    i.putExtra("images", imageUris);
                                    i.putExtra("images_strings", stringUris);
                                    i.putExtra("BUNDLE", args);
                                    //i.putExtra("imagePath", StringUris);
                                    startActivity(i);

                                }
                                //sendMessage(fuser.getUid(), userid, myUrl, checker, time);

                                imageUris.clear();
                                stringUris.clear();
                            }
                        });
                    }
                }
                // Only 1 image selected
                else {

                    if (!checker.equals("image")){

                    }
                    else if (checker.equals("image")){

                        fileUri = data.getData();
                        imageUris.add(fileUri);
                        if(stringUris != null && fileUri != null) {
                            stringUris.add(fileUri.toString());
                        }

                        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Image Files");
                        if(messageSenderID != null && messageReceiverID != null) {
                            DatabaseReference userMessageKeyRef = reference.child("messages")
                                    .child(messageSenderID).child(messageReceiverID).push();

                            String messagePushID = userMessageKeyRef.getKey();
                            filePath = storageReference.child(messagePushID + "." + "jpg");
                            uploadTask = filePath.putFile(fileUri);
                        }
                        if (uploadTask != null) {
                            uploadTask.continueWithTask(new Continuation() {
                                @Override
                                public Object then(@NonNull Task task) throws Exception {
                                    if (!task.isSuccessful()) {
                                        throw task.getException();
                                    }
                                    return filePath.getDownloadUrl();
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        Uri downloadUrl = task.getResult();
                                        myUrl = downloadUrl.toString();

                                        Intent i = new Intent(MessageActivity.this, Multiple_Image_Preview.class);

                                        Bundle args = new Bundle();

                                        args.putSerializable("IMAGES", (Serializable) imageUris);
                                        args.putSerializable("STRING_IMAGES", (Serializable) stringUris);

                                        i.putExtra("sender", fuser.getUid());
                                        i.putExtra("receiver", userid);
                                        i.putExtra("message", myUrl);
                                        i.putExtra("checker", checker);
                                        i.putExtra("time", time);
                                        i.putExtra("images", imageUris);
                                        i.putExtra("images_strings", stringUris);
                                        //i.putExtra("imagePath", a);
                                        i.putExtra("BUNDLE", args);

                                        startActivity(i);

                                    }
                                    imageUris.clear();
                                    stringUris.clear();
                                }
                            });
                        }
                    }
                }

            }
        }
    }

    private void seenMessage(String userid) {
        reference = FirebaseDatabase.getInstance().getReference("Chat");
        seenListener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    User user = snapshot.getValue(User.class);

                    if (fuser.getUid().equals(chat.getReceiver()) && userid.equals(chat.getSender())){
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

    public void sendMessage(String sender, String receiver, String message, String type, String time){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String,Object>hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("message", message);
        hashMap.put("type", type);
        hashMap.put("time", time);
        hashMap.put("isseen", false);

        reference.child("Chat").push().setValue(hashMap);

    }

    public void readMessages(String myid, String userid, String imageurl){
        mChat = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Chat");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mChat.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    Chat chat = snapshot.getValue(Chat.class);
                    if(myid.equals(chat.getReceiver()) && userid.equals(chat.getSender())||
                            userid.equals(chat.getReceiver()) && myid.equals(chat.getSender()))
                    {
                        mChat.add(chat);
                    }
                    messageAdapter = new MessageAdapter(MessageActivity.this, mChat, imageurl);
                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        seenMessage(userid);
    }

    private void status (String status){
        if (fuser != null) {

            reference = FirebaseDatabase.getInstance().getReference("User").child(fuser.getUid());

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("status", status);

            reference.updateChildren(hashMap);
        }
    }


    @Override
    protected void onResume () {
        super.onResume();
        status("online");
    }

    @Override
    protected void onPause () {
        super.onPause();
        reference.removeEventListener(seenListener);
        status("offline");
    }


    public void Image () {
        super.onPause();
        reference.removeEventListener(seenListener);
        status("offline");
    }

}
