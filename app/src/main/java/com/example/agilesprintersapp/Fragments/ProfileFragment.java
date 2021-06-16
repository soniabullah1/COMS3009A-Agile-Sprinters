package com.example.agilesprintersapp.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.agilesprintersapp.ChangePasswordActivity;
import com.example.agilesprintersapp.Model.User;
import com.example.agilesprintersapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
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

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {


    private static final int RESULT_OK = -1;
    CircleImageView image_profile;
    TextView edit_profile_image;
    TextView edit_username;
    TextView edit_password;
    Button save_btn;
    EditText email;
    EditText contactNumber;
    EditText username;
    DatabaseReference reference;
    FirebaseUser fuser ;
    String j;

    StorageReference storageReference;
    private static final int IMAGE_REQUEST = 1;
    private Uri imageUri;
    private StorageTask uploadTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container,false);

        image_profile = view.findViewById(R.id.profile_image);
        username = view.findViewById(R.id.edit_username);
        edit_profile_image = view.findViewById(R.id.edit_profile_image);
        edit_username = view.findViewById(R.id.edit_username);
        edit_password = view.findViewById(R.id.edit_password);
        email = view.findViewById(R.id.edit_email);
        contactNumber = view.findViewById(R.id.edit_phone_number);
        save_btn = view.findViewById(R.id.btn_saver);
        storageReference = FirebaseStorage.getInstance().getReference("uploads");


        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (fuser != null) {
            reference = FirebaseDatabase.getInstance().getReference("User").child(fuser.getUid());

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User user = snapshot.getValue(User.class);
                    username.setText(user.getUsername());
                    contactNumber.setText(user.getContactNumber());
                    email.setText(user.getEmail());

                    if (user != null && user.getId() != null) {
                        if (user.getImageURL().equals("default")) {
                            image_profile.setImageResource(R.mipmap.ic_launcher);
                        } else {
                            if(getActivity() == null){
                                return;
                            }
                            Glide.with(getContext()).load(user.getImageURL()).into(image_profile);
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });}

        edit_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });

        edit_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().isEmpty() || email.getText().toString().isEmpty() || contactNumber.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"One or many fields required!",Toast.LENGTH_SHORT).show();
                    return;
                }

                String profileemail = email.getText().toString();
                fuser.updateEmail(profileemail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

//                        reference = FirebaseDatabase.getInstance().getReference("User").child(fuser.getUid());
//                        HashMap<String, Object> map = new HashMap<>();
//                        map.put("email", profileemail);
//                        map.put("contactNumber", contactNumber.getText().toString());
//                        map.put("username", username.getText().toString());
//                        reference.updateChildren(map);
//                        Toast.makeText(getActivity(),"Your profile has been updated!",Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

        image_profile.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//
//                reference = FirebaseDatabase.getInstance().getReference("User").child(fuser.getUid());
//
//                reference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        User user = snapshot.getValue(User.class);
//                        if (user != null && user.getId() != null) {
//                            if (user.getImageURL().equals("default")) {
//                                image_profile.setImageResource(R.mipmap.ic_launcher);
//                            } else {
//                                final Dialog nagDialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
//                                nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                                nagDialog.setCancelable(false);
//                                nagDialog.setContentView(R.layout.preview_image);
//                                ImageButton btnClose = (ImageButton) nagDialog.findViewById(R.id.btnIvClose);
//                                ImageView ivPreview = (ImageView)nagDialog.findViewById(R.id.iv_preview_image);
//                                Glide.with(getContext()).load(user.getImageURL()).into(ivPreview);
//
//                                btnClose.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View arg0) {
//
//                                        nagDialog.dismiss();
//                                    }
//                                });
//                                nagDialog.show();
//                                //Glide.with(getContext()).load(user.getImageURL()).into(image_profile);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
                return true;
            }
        });

        return view;
    }

    public void openImage() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, IMAGE_REQUEST);
    }

//    private String getFileExtension(Uri uri){
//        ContentResolver contentResolver = getContext().getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
//    }

    public void uploadImage(){
//        final ProgressDialog pd = new ProgressDialog(getContext());
//        pd.setMessage("Uploading");
//        pd.show();
//
//        if(imageUri != null){
//            final StorageReference fileReference = storageReference.child(System.currentTimeMillis()
//                    +"."+getFileExtension(imageUri));
//
//            uploadTask = fileReference.putFile(imageUri);
//            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
//                @Override
//                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//                    if(!task.isSuccessful()){
//                        throw task.getException();
//                    }
//                    return fileReference.getDownloadUrl();
//                }
//            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                @Override
//                public void onComplete(@NonNull Task<Uri> task) {
//                    if (task.isSuccessful()){
//                        Uri downloadUri = task.getResult();
//                        String mUri = downloadUri.toString();
//
//                        reference = FirebaseDatabase.getInstance().getReference("User").child(fuser.getUid());
//                        HashMap<String, Object> map = new HashMap<>();
//                        map.put("imageURL", mUri);
//                        reference.updateChildren(map);
//
//                        pd.dismiss();
//                    }else{
//                        Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
//                        pd.dismiss();
//                    }
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                    pd.dismiss();
//                }
//            });
//        }else{
//            Toast.makeText(getContext(),"No image selected",Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
//            imageUri = data.getData();
//
//            if(uploadTask != null && uploadTask.isInProgress()){
//                Toast.makeText(getContext(),"Upload in progress", Toast.LENGTH_SHORT).show();
//            }else{
//                uploadImage();
//            }
//        }
    }
}
