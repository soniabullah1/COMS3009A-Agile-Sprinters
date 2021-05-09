package com.example.agilesprintersapp;

import android.content.Intent;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageActivityTest {

    @Rule
    public ActivityTestRule<MessageActivity> messageActivityTestRule = new ActivityTestRule<>(MessageActivity.class);
    private MessageActivity messageActivity = null;



    @Before
    public void setUp() throws Exception {
        messageActivity = messageActivityTestRule.getActivity();
        messageActivity.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

    }

    @Test
    public void testNavBarCalls(){
        View view = messageActivity.findViewById(R.id.recycler_view);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        messageActivity = null;
    }
}