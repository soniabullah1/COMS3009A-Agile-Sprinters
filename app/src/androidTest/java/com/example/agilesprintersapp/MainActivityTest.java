package com.example.agilesprintersapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(RegisterActivity.class.getName(),null ,false);

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityActivityTestRule.getActivity();

    }

    @Test
    public void testLaunch(){
        View view = mainActivity.findViewById(R.id.tSignIn);
        assertNotNull(view);
    }

    @Test
    public void testButtonClickRegister(){
        onView(withId(R.id.tRegister)).perform(click());
        Activity registerActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(registerActivity);

        registerActivity.finish();
    }


    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}