package com.example.agilesprintersapp;

import android.content.Intent;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public class CallsActivityTest {

    @Rule
    public ActivityTestRule<CallsActivity> callsActivityTestRule = new ActivityTestRule<>(CallsActivity.class);
    private CallsActivity callsActivity = null;

    @Before
    public void setUp() throws Exception {
        callsActivity = callsActivityTestRule.getActivity();
        callsActivity.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }
    @Test
    public void testNavBarChat(){
        View view = callsActivity.findViewById(R.id.chats);
        assertNotNull(view);
        callsActivity.finish();
    }
    @Test
    public void testNavBarContacts(){
        View view = callsActivity.findViewById(R.id.contacts);
        assertNotNull(view);
        callsActivity.finish();
    }
    @Test
    public void testNavBarCamera(){
        View view = callsActivity.findViewById(R.id.camera);
        assertNotNull(view);
        callsActivity.finish();
    }
    @Test
    public void testNavBarSettings(){
        View view = callsActivity.findViewById(R.id.settings);
        assertNotNull(view);
        callsActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        callsActivity = null;
    }
}