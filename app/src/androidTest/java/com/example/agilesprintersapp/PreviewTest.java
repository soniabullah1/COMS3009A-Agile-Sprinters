package com.example.agilesprintersapp;

import android.content.Intent;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.agilesprintersapp.Model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

public class PreviewTest {
    @Rule
    public ActivityTestRule<Preview> PreviewTestRule = new ActivityTestRule<Preview>(Preview.class);
    private Preview preview = null;

    @Before
    public void setUp() throws Exception {
        preview = PreviewTestRule.getActivity();
        preview.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }

    @Test
    public void testLaunch(){
        View view = preview.findViewById(R.id.textView);
        assertNotNull(view);
        preview.finish();
    }

    @Test
    public void testSendAMessage(){
        List<User> user = new ArrayList<User>();
        String time = String.valueOf(System.currentTimeMillis());
        preview.sendMessage( "1HYeIejMyvhSemoNl2UbYXC9SvB3", user, "Unit testing is not fun", "text", time, "UGHHHHHHH");
        preview.finish();
    }

     @Test
     public void testReadMessage(){
         String pic_url = "unittest";
         preview.readMessages("1HYeIejMyvhSemoNl2UbYXC9SvB3","4WP1IvaihjYaB4fHmaMl413bsN62",pic_url);
         preview.finish();
     }

    @Test
    public void testSeenMessage(){
        preview.seenMessage("1HYeIejMyvhSemoNl2UbYXC9SvB3");
        preview.finish();
    }

    @Test
    public void testMessageSendButton(){
        onView(withId(R.id.button3)).perform(click());
        preview.finish();
    }

    @After
    public void tearDown() throws Exception {
        preview = null;
    }
}
