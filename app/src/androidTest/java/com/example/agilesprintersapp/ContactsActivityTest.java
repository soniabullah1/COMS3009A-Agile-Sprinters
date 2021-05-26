package com.example.agilesprintersapp;

import android.content.Intent;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

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
    public void testNavBarItems(){
        View view = contactsActivity.findViewById(R.id.camera);
        View view1 = contactsActivity.findViewById(R.id.settings);
        View view2 = contactsActivity.findViewById(R.id.contacts);
        View view3 = contactsActivity.findViewById(R.id.chats);

        assertNotNull(view);
        assertNotNull(view1);
        assertNotNull(view2);
        assertNotNull(view3);

        contactsActivity.finish();
    }
    @Test
    public void Z_testNavBarCLicks(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.chats)).perform(click());
        onView(withId(R.id.contacts)).perform(click());
        onView(withId(R.id.camera)).perform(click());
        onView(withId(R.id.settings)).perform(click());
        contactsActivity.finish();
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