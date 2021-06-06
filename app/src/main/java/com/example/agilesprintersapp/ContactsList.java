package com.example.agilesprintersapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesprintersapp.Adapter.UserAdapter;
import com.example.agilesprintersapp.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ContactsList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> mUsers;

    EditText search_users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        recyclerView = findViewById(R.id.recycler_view12);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mUsers = new ArrayList<>();

        readUsers();

        search_users = findViewById(R.id.search_users);
        search_users.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchUsers(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    public void searchUsers(String toString) {
//
//        FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
//        Query query = FirebaseDatabase.getInstance().getReference("User").orderByChild("username")
//                .startAt(toString)
//                .endAt(toString+"\uf8ff");
//
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
//                if(mUsers != null) {
//                    mUsers.clear();
//                }
//                for(DataSnapshot snapshot: datasnapshot.getChildren()){
//                    User user = snapshot.getValue(User.class);
//
//                    assert user!= null;
//                    assert fuser!= null;
//                    if(!user.getId().equals(fuser.getUid()) && mUsers != null){
//                        mUsers.add(user);
//                    }
//                }
//
//                userAdapter = new UserAdapter(getApplicationContext(), mUsers, false);
//                recyclerView.setAdapter(userAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }
    public void readUsers(){

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");

        reference .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                mUsers.clear();

                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    //String id = user.getId();

                    if (firebaseUser != null) {

                        if (user != null && user.getId() != null && !user.getId().equals(firebaseUser.getUid())){
                            mUsers.add(user);
                        }
                    }
                }

                userAdapter = new UserAdapter(getApplicationContext(), mUsers, false);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
