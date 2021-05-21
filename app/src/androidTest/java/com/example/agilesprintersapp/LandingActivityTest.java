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

public class LandingActivityTest {

    @Rule
    public ActivityTestRule<LandingActivity> landingActivityActivityTestRule = new ActivityTestRule<LandingActivity>(LandingActivity.class);
    private LandingActivity landingActivity = null;

    Instrumentation.ActivityMonitor monitor1 = getInstrumentation().addMonitor(RegisterActivity.class.getName(),null ,false);
    Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(LoginActivity.class.getName(),null ,false);

    @Before
    public void setUp() throws Exception {
        landingActivity = landingActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = landingActivity.findViewById(R.id.tSignIn);
        assertNotNull(view);
    }

    @Test
    public void testButtonClickRegister(){
        onView(withId(R.id.tRegister)).perform(click());
        Activity registerActivity = getInstrumentation().waitForMonitorWithTimeout(monitor1,50000);
        assertNotNull(registerActivity);

        registerActivity.finish();
    }

    @Test
    public void testButtonClickSignIn(){
        onView(withId((R.id.tSignIn))).perform(click());
        Activity signInActivity = getInstrumentation().waitForMonitorWithTimeout(monitor2,50000);
        assertNotNull(signInActivity);

        signInActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        landingActivity = null;
    }
}