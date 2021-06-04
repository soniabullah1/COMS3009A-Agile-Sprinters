package com.example.agilesprintersapp;

import android.content.Intent;
import android.view.View;

import androidx.annotation.UiThread;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageActivityTest{

    @Rule
    public ActivityTestRule<MessageActivity> messageActivityTestRule = new ActivityTestRule<>(MessageActivity.class);
    private MessageActivity messageActivity = null;

    @Before
    public void setUp() throws Exception {
        messageActivity = messageActivityTestRule.getActivity();
        messageActivity.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

    }

    @Test
    public void testLaunch(){
        View view = messageActivity.findViewById(R.id.recycler_view12);
        View view2 = messageActivity.findViewById(R.id.text_send);

        assertNotNull(view);
        assertNotNull(view2);

        messageActivity.finish();
    }

    @UiThread
    @Test
    public void testMessageSendButton(){
        onView(withId(R.id.btn_send)).perform(click());
        boolean toastMade = messageActivity.toastMade;
        assertEquals(true, toastMade);
        messageActivity.finish();
    }

    @Test
    public void testSendAMessage(){
        String time = String.valueOf(System.currentTimeMillis());
        messageActivity.sendMessage( "1HYeIejMyvhSemoNl2UbYXC9SvB3", "4WP1IvaihjYaB4fHmaMl413bsN62", "Unit testing is not fun", "text", time);
        messageActivity.finish();
    }

    @Test
    public void testReadMessage(){
        String pic_url = "unittest";
        messageActivity.readMessages("1HYeIejMyvhSemoNl2UbYXC9SvB3","4WP1IvaihjYaB4fHmaMl413bsN62",pic_url);
        messageActivity.finish();
    }

    @UiThread
    @Test
    public void Z_testAttachImage(){
        onView(withId(R.id.btn_attach_pic)).perform(click());
        messageActivity.finish();
        //messageActivity.pickImagesIntent();
    }

    @Test
    public void testOnActivityResult() {
        Intent data = new Intent();
        messageActivity.onActivityResult(0,-1,data);

        messageActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        messageActivity = null;
    }
}
