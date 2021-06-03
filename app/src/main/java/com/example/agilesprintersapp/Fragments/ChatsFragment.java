package com.example.agilesprintersapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesprintersapp.Adapter.UserAdapter;
import com.example.agilesprintersapp.ContactsList;
import com.example.agilesprintersapp.Model.Chatlist;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ChatsFragment extends Fragment {

    SimpleDateFormat date = new SimpleDateFormat("HH:mm dd/mm/yyyy");

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> mUsers;
FloatingActionButton floatingActionButton;
    FirebaseUser fuser;
    DatabaseReference reference;

//    private List<String> userList;
private List<Chatlist> userList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        recyclerView = view.findViewById(R.id.recycler_view2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fuser = FirebaseAuth.getInstance().getCurrentUser();

        userList = new ArrayList<>();


        if(fuser != null) {
            reference = FirebaseDatabase.getInstance().getReference("Chatlist").child(fuser.getUid());

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                    userList.clear();

                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {

                        Chatlist chatlist = snapshot.getValue(Chatlist.class);
                        userList.add(chatlist);

//                    Chat chat = snapshot.getValue(Chat.class);
//                    User user = snapshot.getValue(User.class);
//
//                    if (fuser != null) {
//
//                        if (user.getId() != null && fuser.getUid().equals(fuser.getUid())&& chat.getSender().equals(fuser.getUid())) {
//                            userList.add(chat.getReceiver());
//                        }
//                        if (user.getId() != null && fuser.getUid().equals(fuser.getUid())&& chat.getReceiver().equals(fuser.getUid())) {
//                            userList.add(chat.getSender());
//                        }
//                    }
                    }

                    //readChats();

                    chatList();
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContactsList.class);
            startActivity(intent);



            }
        });

        return view;
    }

    private void chatList() {
        mUsers = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("User");
        //Query query = reference.orderByChild("time");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
//                    for (Chatlist id : userList){
//                        if (user != null && user.getId() != null && user.getId().equals(id)) {
                    for (Chatlist chatlist : userList) {
                        if (user.getId() != null && user.getId().equals(chatlist.getId())) {
                            mUsers.add(user);
                        }
                    }
//                        }
//
//                    }
                }
                userAdapter = new UserAdapter(getContext(), mUsers, true);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    private void readChats(){
//
//        mUsers = new ArrayList<>();
//        reference = FirebaseDatabase.getInstance().getReference("User");
//
//        reference .addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot datasnapshot){
//                mUsers.clear();
//
//                for(DataSnapshot snapshot : datasnapshot.getChildren()){
//                    User user = snapshot.getValue(User.class);
//
//                    for (String id : userList){
//                        if(user!=null && user.getId() != null && user.getId().equals(id)){
//                            if (mUsers.size() != 0) {
//                                for(User user1: new ArrayList<User>(mUsers)){
//                                    //for(User user1 : mUsers){
//                                    //user.getId() != null &&  fuser.getUid().equals(fuser.getUid()) &&
//                                    if(!user.getId().equals(user1.getId())) {
//                                        mUsers.add(user);
//                                    }
//                                }
//                            }else{
//                                mUsers.add(user);
//                            }
//                        }
//                    }
//                }
//
//                userAdapter = new UserAdapter(getContext(), mUsers, false);
//                recyclerView.setAdapter(userAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
}
