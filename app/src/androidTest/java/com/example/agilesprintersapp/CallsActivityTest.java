package com.example.agilesprintersapp;

import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.test.annotation.UiThreadTest;
import androidx.test.filters.LargeTest;
import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;
import static org.hamcrest.core.AllOf.allOf;


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
    }
    @Test
    public void testNavBarContacts(){
        View view = callsActivity.findViewById(R.id.contacts);
        assertNotNull(view);
    }
    @Test
    public void testNavBarCamera(){
        View view = callsActivity.findViewById(R.id.camera);
        assertNotNull(view);
    }
    @Test
    public void testNavBarSettings(){
        View view = callsActivity.findViewById(R.id.settings);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        callsActivity = null;
    }
}