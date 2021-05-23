package com.example.agilesprintersapp;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactsActivityTest {
    @Rule
    public ActivityTestRule<ContactsActivity> contactsActivityTestRule = new ActivityTestRule<ContactsActivity>(ContactsActivity.class);
    private ContactsActivity contactsActivity = null;

    @Before
    public void setUp() throws Exception {
        contactsActivity = contactsActivityTestRule.getActivity();
    }

    @Test
    public void testNavBarChat(){
        View view = contactsActivity.findViewById(R.id.chats);
        assertNotNull(view);
    }
    @Test
    public void testNavBarContacts(){
        View view = contactsActivity.findViewById(R.id.contacts);
        assertNotNull(view);
    }
    @Test
    public void testNavBarCamera(){
        View view = contactsActivity.findViewById(R.id.camera);
        assertNotNull(view);
    }
    @Test
    public void testNavBarSettings(){
        View view = contactsActivity.findViewById(R.id.settings);
        assertNotNull(view);
    }

    @Test
    public void testRequestPermission(){
        contactsActivity.requestPermission();
    }

    @Test
    public void testGetAllContacts(){
        contactsActivity.getAllContacts();
    }

    @After
    public void tearDown() throws Exception {
        contactsActivity = null;
    }
}