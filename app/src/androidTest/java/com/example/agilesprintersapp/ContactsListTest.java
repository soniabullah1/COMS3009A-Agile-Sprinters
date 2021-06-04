package com.example.agilesprintersapp;

import android.content.Intent;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactsListTest {
    @Rule
    public ActivityTestRule<ContactsList> contactsListTestRule = new ActivityTestRule<ContactsList>(ContactsList.class);
    private ContactsList contactsList = null;

    @Before
    public void setUp() throws Exception {
        contactsList = contactsListTestRule.getActivity();
        contactsList.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }

    @Test
    public void testLaunch(){
        View view = contactsList.findViewById(R.id.search_users);
        assertNotNull(view);
        View view1 = contactsList.findViewById(R.id.recycler_view12);
        assertNotNull(view1);
        contactsList.finish();

        contactsList.readUsers();
        contactsList.searchUsers("tris");
    }

    @After
    public void tearDown() throws Exception {
        contactsList = null;
    }
}