package com.example.agilesprintersapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;
import android.widget.TextView;

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

public class LoginActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity> loginActivityTestRule = new ActivityTestRule<>(LoginActivity.class);
    private LoginActivity loginActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null ,false);


    @Before
    public void setUp() throws Exception {
        loginActivity = loginActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchEditTextEmail(){
        View view = loginActivity.findViewById(R.id.email);
        assertNotNull(view);
    }

    @Test
    public void testLaunchEditTextPassword(){
        View view = loginActivity.findViewById(R.id.password);
        assertNotNull(view);
    }

    @Test
    public void testLaunchTextView3(){
        TextView textViewTest = (TextView) loginActivity.findViewById(R.id.textView3);
        String actual = (String) textViewTest.getText();
        String expected = "Welcome Back to TalkTime, You're almost ready to get chatty!";

        assertEquals(actual,expected);
    }

    @Test
    public void testReturnToHomePageButton(){
        onView(withId(R.id.btn_Return)).perform(click());
        Activity registerActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(registerActivity);

        registerActivity.finish();
    }


    @After
    public void tearDown() throws Exception {
        loginActivity = null;
    }
}