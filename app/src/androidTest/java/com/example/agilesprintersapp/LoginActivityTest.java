package com.example.agilesprintersapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity> loginActivityTestRule = new ActivityTestRule<>(LoginActivity.class);
    private LoginActivity loginActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(LandingActivity.class.getName(),null ,false);
    Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(HomeActivity.class.getName(),null ,false);
    Instrumentation.ActivityMonitor monitor3 = getInstrumentation().addMonitor(ForgotPasswordActivity.class.getName(),null ,false);

    public static final String STRING_TO_BE_TYPED_EMAIL = "tristenhav@gmail.com";
    public static final String STRING_TO_BE_TYPED_EMAIL1 = "tristenhav@gmail.com";
    public static final String STRING_TO_BE_TYPED_PHONE = "123456789";
    public static final String STRING_TO_BE_TYPED_USERNAME = "unittester";
    public static final String STRING_TO_BE_TYPED_PASSWORD = "Exciting";

    @Before
    public void setUp() throws Exception {
        loginActivity = loginActivityTestRule.getActivity();
        loginActivity.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }

    @Test
    public void testLaunchItems(){
        View view = loginActivity.findViewById(R.id.email);
        View view1 = loginActivity.findViewById(R.id.password);
        assertNotNull(view);
        assertNotNull(view1);

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

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.recycler_view2)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.text_send)).perform(typeText("unit-testing sucks"), closeSoftKeyboard());
        onView(withId(R.id.btn_send)).perform(click());
        onView(withId(R.id.calls)).perform(click());
        assertNotNull(loginActivity);
        //loginActivity.finish();

    }

    @Test
    public void testStories(){
        onView(withId(R.id.email)).perform(typeText(STRING_TO_BE_TYPED_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.btn_Login)).perform(click());

        //Activity loginActivity = getInstrumentation().waitForMonitorWithTimeout(monitor2,5000);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.view_pager)).perform(swipeLeft());
        onView(withId(R.id.stories)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    }

    @Test
    public void testProfile(){
        onView(withId(R.id.email)).perform(typeText(STRING_TO_BE_TYPED_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.btn_Login)).perform(click());

        //Activity loginActivity = getInstrumentation().waitForMonitorWithTimeout(monitor2,5000);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.view_pager)).perform(swipeLeft());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.stories)).perform(swipeLeft());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.edit_password)).perform(click());
        onView(withId(R.id.NewPasswordText)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.ConfirmPasswordText)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.Btn_Change)).perform(click());
    }

    @Test
    public void testProfile2(){
        onView(withId(R.id.email)).perform(typeText(STRING_TO_BE_TYPED_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.btn_Login)).perform(click());

        //Activity loginActivity = getInstrumentation().waitForMonitorWithTimeout(monitor2,5000);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.view_pager)).perform(swipeLeft());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.stories)).perform(swipeLeft());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.edit_email)).perform(typeText(STRING_TO_BE_TYPED_EMAIL1), closeSoftKeyboard());
        onView(withId(R.id.edit_phone_number)).perform(typeText(STRING_TO_BE_TYPED_PHONE), closeSoftKeyboard());
        onView(withId(R.id.edit_username)).perform(typeText(STRING_TO_BE_TYPED_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.btn_save)).perform(click());

    }

    @Test
    public void testFloatingButton(){
        onView(withId(R.id.email)).perform(typeText(STRING_TO_BE_TYPED_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.btn_Login)).perform(click());

        Activity loginActivity = getInstrumentation().waitForMonitorWithTimeout(monitor2,5000);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.floatingActionButton1)).perform(click());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.floatingActionButton)).perform(click());

        assertNotNull(loginActivity);
        //loginActivity.finish();

    }

    @Test
    public void testForgotPassword(){
        onView(withId(R.id.ForgotPasswordView)).perform(click());
        Activity forgotpasswordActivity = getInstrumentation().waitForMonitorWithTimeout(monitor3,5000);
        assertNotNull(forgotpasswordActivity);
    }

    @After
    public void tearDown() throws Exception {
        loginActivity = null;
    }
}
