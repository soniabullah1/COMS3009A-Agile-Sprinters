package com.example.agilesprintersapp.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agilesprintersapp.MessageActivity;
import com.example.agilesprintersapp.Model.User;
import com.example.agilesprintersapp.Multiple_Image_Preview;
import com.example.agilesprintersapp.Preview;
import com.example.agilesprintersapp.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class StoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView myStory;
    private TextView username;
    private FloatingActionButton floatingButton;
    private CircleImageView profile;

    private String myUrl = "";
    private StorageTask uploadTask;
    private Uri fileUri;
    public String userid;
    private String messageSenderID;
    private String messageReceiverID;
    private String time;

    //store image uris in array list
    public ArrayList<Uri> imageUris;
    public ArrayList<String> stringUris;
    //public  ArrayList<StoryModel> storyList;

    private static final int PICK_IMAGES_CODE = 0;

    DatabaseReference reference;
    FirebaseUser fuser ;
    StorageReference storageReference;

    //StoryView storyView;

    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_story, container, false);

        recyclerView = view.findViewById(R.id.stories);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        floatingButton = view.findViewById(R.id.floatingActionButton);
        profile = view.findViewById(R.id.profile);
        username = view.findViewById(R.id.username);

        //storyView = view.findViewById(R.id.story_view);
        //storyView.resetStoryVisits(); // used to reset story if user already watched the story

        intent = getActivity().getIntent();
        userid = intent.getStringExtra("userid");

        imageUris = new ArrayList<>();
        stringUris = new ArrayList<>();
        //storyList = new ArrayList<>();

        storageReference = FirebaseStorage.getInstance().getReference("uploads");


        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (fuser != null) {
            reference = FirebaseDatabase.getInstance().getReference("User").child(fuser.getUid());

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    User user = snapshot.getValue(User.class);
                    username.setText(user.getUsername());

                    if (user != null && user.getId() != null) {
                        if (user.getImageURL().equals("default")) {
                            profile.setImageResource(R.mipmap.ic_launcher);
                        } else {
                            Glide.with(getContext()).load(user.getImageURL()).into(profile);
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });}


        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), MyStoryActivity.class);
//                startActivity(intent);

                //pickImagesIntent();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select image"), PICK_IMAGES_CODE);
            }
        });

        if(fuser != null) {
            messageSenderID = fuser.getUid();
        }
        //messageReceiverID = userid;

        return view;
    }

    private void pickImagesIntent(){
        Intent intent = new Intent();
        intent.setType("image/*");
        // intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        getActivity().startActivityForResult(Intent.createChooser(intent, "Select image"), PICK_IMAGES_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGES_CODE){

            if (resultCode == Activity.RESULT_OK){

                if(data.getClipData() != null){

                }

                // This is only for 1 image selected
                else{

                    fileUri = data.getData();
                    imageUris.add(fileUri);
                    stringUris.add(fileUri.toString());

                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Image Files");
                    DatabaseReference userMessageKeyRef = reference.child("stories")
                            .child(messageSenderID).push();
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
                                Uri a = fileUri;

                                Intent i = new Intent(getActivity(), Preview.class);

                                //Bundle args = new Bundle();

                                //args.putSerializable("IMAGES",(Serializable)imageUris);
                                //args.putSerializable("STRING_IMAGES",(Serializable)stringUris);


                                i.putExtra("sender", fuser.getUid());
                                //i.putExtra("receiver", userid);
                                i.putExtra("message", myUrl);
                                i.putExtra("checker", "image");
                                i.putExtra("time", time);

                                i.putExtra("images", imageUris);
                                i.putExtra("images_strings", stringUris);
                                i.putExtra("imagePath", a.toString());
                                //i.putExtra("BUNDLE", args);

                                startActivity(i);

                            }
                            imageUris.clear();
                        }
                    });
                }
            }
        }
    }
}
