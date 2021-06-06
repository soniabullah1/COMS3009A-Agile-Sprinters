package com.example.agilesprintersapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.annotation.UiThread;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class LandingActivityTest {

    @Rule
    public ActivityTestRule<LandingActivity> landingActivityActivityTestRule = new ActivityTestRule<LandingActivity>(LandingActivity.class);
    private LandingActivity landingActivity = null;

    Instrumentation.ActivityMonitor monitor1 = getInstrumentation().addMonitor(RegisterActivity.class.getName(),null ,false);
    Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(LoginActivity.class.getName(),null ,false);

    @Before
    public void setUp() throws Exception {
        landingActivity = landingActivityActivityTestRule.getActivity();
        //landingActivity.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

    }

    @Test
    public void A_testLaunch(){
        View view = landingActivity.findViewById(R.id.tSignIn);
        assertNotNull(view);
        //landingActivity.finish();
    }

    @UiThread
    @Test
    public void testButtonClickRegister(){
        onView(withId(R.id.tRegister)).perform(click());
        Activity registerActivity = getInstrumentation().waitForMonitorWithTimeout(monitor1,5000);
        assertNotNull(registerActivity);

        //registerActivity.finish();

        //landingActivity.finish();
    }

    @UiThread
    @Test
    public void testButtonClickSignIn(){
        onView(withId((R.id.tSignIn))).perform(click());
        Activity signInActivity = getInstrumentation().waitForMonitorWithTimeout(monitor2,5000);

        assertNotNull(signInActivity);
        //signInActivity.finish();
        //landingActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        landingActivity = null;
    }
}