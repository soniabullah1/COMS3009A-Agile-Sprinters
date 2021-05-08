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
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> homeActivityTestRule = new ActivityTestRule<>(HomeActivity.class);
    private HomeActivity homeActivity = null;

    @Before
    public void setUp() throws Exception {
        homeActivity = homeActivityTestRule.getActivity();
        homeActivity.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }

    @Test
    public void testNavBarChat(){
        View view = homeActivity.findViewById(R.id.chats);
        assertNotNull(view);
    }

    @Test
    public void testNavBarContacts(){
        View view = homeActivity.findViewById(R.id.contacts);
        assertNotNull(view);
    }

    @Test
    public void testNavBarSettings(){
        View view = homeActivity.findViewById(R.id.settings);
        assertNotNull(view);
    }

    @Test
    public void testNavBarCalls(){
        View view = homeActivity.findViewById(R.id.calls);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        homeActivity = null;
    }
}