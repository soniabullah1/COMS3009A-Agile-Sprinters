package com.example.agilesprintersapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class LoginActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity> loginActivityTestRule = new ActivityTestRule<>(LoginActivity.class);
    private LoginActivity loginActivity = null;


    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(LandingActivity.class.getName(),null ,false);
    Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(HomeActivity.class.getName(),null ,false);

    //String a = loginActivity.email.getText().toString();
    public static final String STRING_TO_BE_TYPED_EMAIL = "rushilpatel0703@gmail.com";
    public static final String STRING_TO_BE_TYPED_PASSWORD = "cakeface42";

    @Before
    public void setUp() throws Exception {
        loginActivity = loginActivityTestRule.getActivity();
        loginActivity.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }

    @Test
    public void testLaunchEditTextEmail(){
        View view = loginActivity.findViewById(R.id.email);
        assertNotNull(view);
        loginActivity.finish();
    }

    @Test
    public void testLaunchEditTextPassword(){
        View view = loginActivity.findViewById(R.id.password);
        assertNotNull(view);
        loginActivity.finish();
    }

    @Test
    public void testLaunchTextView3(){
        TextView textViewTest = (TextView) loginActivity.findViewById(R.id.textView3);
        String actual = (String) textViewTest.getText();
        String expected = "Welcome Back to TalkTime, You're almost ready to get chatty!";

        assertEquals(actual,expected);
        loginActivity.finish();
    }

    @Test
    public void testReturnToHomePageButton(){
        onView(withId(R.id.btn_Return)).perform(click());
        Activity registerActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(registerActivity);

        registerActivity.finish();
        loginActivity.finish();
    }

    @Test
    public void testLoginButton(){
        onView(withId(R.id.email)).perform(typeText(STRING_TO_BE_TYPED_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.btn_Login)).perform(click());

        Activity loginActivity = getInstrumentation().waitForMonitorWithTimeout(monitor2,5000);
        assertNotNull(loginActivity);

        loginActivity.finish();


    }


    @After
    public void tearDown() throws Exception {
        loginActivity = null;
    }
}
