package com.example.agilesprintersapp.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devlomi.circularstatusview.CircularStatusView;
import com.example.agilesprintersapp.Adapter.StoryAdapter;
import com.example.agilesprintersapp.Model.Story;
import com.example.agilesprintersapp.Model.User;
import com.example.agilesprintersapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;


public class StoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView storyBox;
    private StoryAdapter storyAdapter;
    private List<Story> mUsers;
    private List<Story> mStory;
    

    private TextView myStory;
    private TextView username;
    private FloatingActionButton floatingButton;
    private CircleImageView profile;
    public CircularStatusView circularStatusView;
    private ImageButton deleteBtn;

    private String myUrl = "";
    private StorageTask uploadTask;
    private Uri fileUri;
    public String userid;
    private String messageSenderID;
    private String messageReceiverID;
    private String time;
    private String username2;
    private String username3;

    //store image uris in array list
    public ArrayList<Uri> imageUris;
    public ArrayList<String> stringUris;

    private static final int PICK_IMAGES_CODE = 0;

    DatabaseReference reference;
    FirebaseUser fuser;
    StorageReference storageReference;
    RelativeLayout myStatus;


    Intent intent;
    Timer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_story, container, false);

        recyclerView = view.findViewById(R.id.stories);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mUsers = new ArrayList<>();
        mStory = new ArrayList<>();

        displayContactsStatus();
        circularStatusView = view.findViewById(R.id.circular_status_view);

        fuser = FirebaseAuth.getInstance().getCurrentUser();

        floatingButton = view.findViewById(R.id.floatingActionButton);
        profile = view.findViewById(R.id.profile_image);
        username = view.findViewById(R.id.username);
        myStatus = view.findViewById(R.id.my_status_box);
        deleteBtn = view.findViewById(R.id.deleteButton);


        intent = getActivity().getIntent();
        userid = intent.getStringExtra("userid");

        if(fuser!=null) {
            username2 = getCurrentUserName(fuser.getUid());
        }
        username3 = getCurrentUserName(userid);

        imageUris = new ArrayList<>();
        stringUris = new ArrayList<>();

        storageReference = FirebaseStorage.getInstance().getReference("uploads");

        circularStatusView.setPortionsCount(1);

        if (fuser != null) {
            reference = FirebaseDatabase.getInstance().getReference("User").child(fuser.getUid());

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    User user = snapshot.getValue(User.class);
                    //username.setText(user.getUsername());

                    if (user != null && user.getId() != null) {
//                        if (user.getImageURL().equals("default")) {
//                            profile.setImageResource(R.mipmap.ic_launcher);
//                        } else {
//
//                            //ADD IN
//                            if (getActivity() == null) {
//                                return;
//                            }
//
//                            //END
//                            Glide.with(getContext()).load(user.getImageURL()).into(profile);
//                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

//        myStatus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                    displayMyStatus();
//            }
//        });



        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select image"), PICK_IMAGES_CODE);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query deleteQuery = ref.child("stories").orderByChild("username").equalTo(username2);
                Query deleteQuery2 = ref.child("stories").orderByChild("username").equalTo(username3);

                deleteQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        deleteQuery2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot deleteSnapshot : dataSnapshot.getChildren()) {
                                    deleteSnapshot.getRef().removeValue();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "onCancelled", databaseError.toException());
                            }
                        });

                        if(dataSnapshot.exists()) {
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

//                                    switch (which) {
//                                        case DialogInterface.BUTTON_POSITIVE:
//
//                                            for (DataSnapshot deleteSnapshot : dataSnapshot.getChildren()) {
//                                                deleteSnapshot.getRef().removeValue();
//                                            }
//
//                                            Toast.makeText(getActivity(), "Status deleted", Toast.LENGTH_SHORT).show();
//                                            break;
//
//                                        case DialogInterface.BUTTON_NEGATIVE:
//                                            break;
//                                    }

                                }
                            };

//                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                            builder.setMessage("Are you sure you want to delete your status ?")
//                                    .setNegativeButton("No", dialogClickListener)
//                                    .setPositiveButton("Yes", dialogClickListener)
//                                    .show();
                        }
                        else{
                            Toast.makeText(getActivity(), "No Status to be deleted", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });

            }
        });

        if (fuser != null) {
            messageSenderID = fuser.getUid();
        }
        //messageReceiverID = userid;

        return view;
    }

//    private void pickImagesIntent() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        // intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        getActivity().startActivityForResult(Intent.createChooser(intent, "Select image"), PICK_IMAGES_CODE);
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGES_CODE) {
//
//            if (resultCode == Activity.RESULT_OK) {
//
//                if (data.getClipData() != null) {
//
//                }
//
//                // This is only for 1 image selected
//                else {
//
//                    fileUri = data.getData();
//                    imageUris.add(fileUri);
//                    stringUris.add(fileUri.toString());
//
//                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Image Files");
//                    DatabaseReference userMessageKeyRef = reference.child("stories")
//                            .child(messageSenderID).push();
//                    String messagePushID = userMessageKeyRef.getKey();
//                    StorageReference filePath = storageReference.child(messagePushID + "." + "jpg");
//                    uploadTask = filePath.putFile(fileUri);
//
//                    uploadTask.continueWithTask(new Continuation() {
//                        @Override
//                        public Object then(@NonNull Task task) throws Exception {
//                            if (!task.isSuccessful()) {
//                                throw task.getException();
//                            }
//                            return filePath.getDownloadUrl();
//                        }
//                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Uri> task) {
//                            if (task.isSuccessful()) {
//                                time = String.valueOf(System.currentTimeMillis());
//                                Uri downloadUrl = task.getResult();
//                                myUrl = downloadUrl.toString();
//                                Uri a = fileUri;
//
//                                Intent i = new Intent(getActivity(), Preview.class);
//
//                                //Bundle args = new Bundle();
//
//                                //args.putSerializable("IMAGES",(Serializable)imageUris);
//                                //args.putSerializable("STRING_IMAGES",(Serializable)stringUris);
//
//
//                                i.putExtra("sender", fuser.getUid());
//                                //i.putExtra("receiver", userid);
//                                i.putExtra("message", myUrl);
//                                i.putExtra("checker", "image");
//                                i.putExtra("time", time);
//
//                                i.putExtra("images", imageUris);
//                                i.putExtra("images_strings", stringUris);
//                                i.putExtra("imagePath", a.toString());
//                                //i.putExtra("BUNDLE", args);
//
//                                startActivity(i);
//
//                            }
//                            imageUris.clear();
//                        }
//                    });
//                }
//            }
//        }
    }



    public void displayContactsStatus() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("stories");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                mStory.clear();

                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    Story story = snapshot.getValue(Story.class);
                    //String id = user.getId();

                    if (firebaseUser != null) {

                            if (story != null && story.getSender() != null) {
                                mStory.add(story);

                            }

                        //                    assert user != null;
                        //                    assert firebaseUser != null;
                        //                    if (!user.getId().equals(firebaseUser.getUid())){
                        //                        mUsers.add(user);
                        //                    }
                    }
                }
                storyAdapter = new StoryAdapter(getContext(), mStory, false);
                recyclerView.setAdapter(storyAdapter);
                Collections.reverse(mStory);

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
                            username2 = user.getUsername();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return username2;
    }

}


