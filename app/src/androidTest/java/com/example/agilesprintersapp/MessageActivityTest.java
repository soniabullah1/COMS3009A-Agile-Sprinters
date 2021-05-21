package com.example.agilesprintersapp;

import android.view.View;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MessageActivityTest {

    @Rule
    public ActivityTestRule<MessageActivity> messageActivityTestRule = new ActivityTestRule<>(MessageActivity.class);
    private MessageActivity messageActivity = null;

    @Before
    public void setUp() throws Exception {
        messageActivity = messageActivityTestRule.getActivity();
        //messageActivity.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

    }

    @Test
    public void testLaunch(){
        View view = messageActivity.findViewById(R.id.recycler_view12);
        assertNotNull(view);
    }

    @Test
    public void testLaunchEditText_Send(){
        View view = messageActivity.findViewById(R.id.text_send);
        assertNotNull(view);

    }


    @Test
    public void testMessageSendButton(){
        onView(withId(R.id.btn_send)).perform(click());
        boolean toastMade = messageActivity.toastMade;
        assertEquals(true, toastMade);
    }

    @UiThreadTest
    @Test
    public void testSendAMessage(){
        String time = String.valueOf(System.currentTimeMillis());
        messageActivity.sendMessage( "1HYeIejMyvhSemoNl2UbYXC9SvB3", "4WP1IvaihjYaB4fHmaMl413bsN62", "Unit testing is not fun", "text", time);
    }

    @After
    public void tearDown() throws Exception {
        messageActivity = null;
    }
}