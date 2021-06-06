package com.example.agilesprintersapp;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ContactsActivityTest {
    @Rule
    public ActivityTestRule<ContactsActivity> contactsActivityTestRule = new ActivityTestRule<ContactsActivity>(ContactsActivity.class);
    private ContactsActivity contactsActivity = null;

    @Before
    public void setUp() throws Exception {
        contactsActivity = contactsActivityTestRule.getActivity();
        contactsActivity.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }

    @Test
    public void testRequestPermission(){
        contactsActivity.requestPermission();
        contactsActivity.finish();
    }

    @Test
    public void testGetAllContacts(){
        contactsActivity.getAllContacts();
        contactsActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        contactsActivity = null;
    }
}