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
    public void testNavBarItems(){
        View view = callsActivity.findViewById(R.id.chats);
        View view1 = callsActivity.findViewById(R.id.contacts);
        View view2 = callsActivity.findViewById(R.id.camera);
        View view3 = callsActivity.findViewById(R.id.settings);

        assertNotNull(view);
        assertNotNull(view1);
        assertNotNull(view2);
        assertNotNull(view3);

        callsActivity.finish();
    }

    @Test
    public void Z_testNavBarCLicks(){
        onView(withId(R.id.chats)).perform(click());
        onView(withId(R.id.contacts)).perform(click());
        onView(withId(R.id.camera)).perform(click());
        onView(withId(R.id.settings)).perform(click());
        callsActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        callsActivity = null;
    }
}