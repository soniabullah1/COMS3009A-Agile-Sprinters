package com.example.agilesprintersapp;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

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

//    @Test
////    public void testCheckDialogDisplayed() {
////        // Click on the button that shows the dialog
////        onView(withId(R.id.btn_attach_pic)).perform(click());
////
////        // Check the dialog title text is displayed
////        onView(withText(R.string.)).check(matches(isDisplayed()));
////    }

    @Test
    public void testMessageSendButton(){
        onView(withId(R.id.btn_send)).perform(click());
        boolean toastMade = messageActivity.toastMade;
        assertEquals(true, toastMade);
    }

    @After
    public void tearDown() throws Exception {
        messageActivity = null;
    }
}