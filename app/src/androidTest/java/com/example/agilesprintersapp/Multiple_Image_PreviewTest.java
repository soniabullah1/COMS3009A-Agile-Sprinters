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

public class Multiple_Image_PreviewTest {
    @Rule
    public ActivityTestRule<Multiple_Image_Preview> mipActivityTestRule = new ActivityTestRule<>(Multiple_Image_Preview.class);
    private Multiple_Image_Preview mipActivity = null;

    @Before
    public void setUp() throws Exception {
        mipActivity = mipActivityTestRule.getActivity();
        mipActivity.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }

    @Test
    public void testLaunch(){
        View view1 = mipActivity.findViewById(R.id.Caption);
        View view2 = mipActivity.findViewById(R.id.PreviousBtn);
        View view3 = mipActivity.findViewById(R.id.Nextbtn);
        View view4 = mipActivity.findViewById(R.id.textView);
        View view5 = mipActivity.findViewById(R.id.imageSwitcher);
        View view6 = mipActivity.findViewById(R.id.close);
        View view7 = mipActivity.findViewById(R.id.button3);

        assertNotNull(view1);
        assertNotNull(view2);
        assertNotNull(view3);
        assertNotNull(view4);
        assertNotNull(view5);
        assertNotNull(view6);
        mipActivity.finish();
    }

    @Test
    public void testPreviousButton(){
        onView(withId(R.id.PreviousBtn)).perform(click());
        //onView(withId(R.id.Nextbtn)).perform(click());
        mipActivity.finish();
    }

//    @Test
//    public void testNextButton(){
//        onView(withId(R.id.Nextbtn)).perform(click());
//        mipActivity.finish();
//    }

    @Test
    public void testSendButton(){
        onView(withId(R.id.button3)).perform(click());
        mipActivity.finish();
    }

    @Test
    public void testSendAMessage(){
        String time = String.valueOf(System.currentTimeMillis());
        mipActivity.sendMessage( "1HYeIejMyvhSemoNl2UbYXC9SvB3", "4WP1IvaihjYaB4fHmaMl413bsN62", "Unit testing is not fun", "text", time, "UGHHHHHHH");
        mipActivity.finish();
    }

    @Test
     public void testReadMessage(){
         String pic_url = "unittest";
         mipActivity.readMessages("1HYeIejMyvhSemoNl2UbYXC9SvB3","4WP1IvaihjYaB4fHmaMl413bsN62",pic_url);
         mipActivity.finish();
    }

    @Test
    public void testSeenMessage(){
        mipActivity.seenMessage("1HYeIejMyvhSemoNl2UbYXC9SvB3");
        mipActivity.finish();
    }


    @After
    public void tearDown() throws Exception {
        mipActivity = null;
    }
}