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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity> loginActivityTestRule = new ActivityTestRule<>(LoginActivity.class);
    @Rule
    public ActivityTestRule<ContactsActivity> contactsActivityTestRule = new ActivityTestRule<ContactsActivity>(ContactsActivity.class);

    private LoginActivity loginActivity = null;
    private ContactsActivity contactsActivity= null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(LandingActivity.class.getName(),null ,false);
    Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(HomeActivity.class.getName(),null ,false);
    Instrumentation.ActivityMonitor monitor3 = getInstrumentation().addMonitor(ForgotPasswordActivity.class.getName(),null ,false);

    public static final String STRING_TO_BE_TYPED_EMAIL = "tristenhav@gmail.com";
    public static final String STRING_TO_BE_TYPED_PASSWORD = "Exciting";

    @Before
    public void setUp() throws Exception {
        loginActivity = loginActivityTestRule.getActivity();
        contactsActivity = contactsActivityTestRule.getActivity();
        loginActivity.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }

    @Test
    public void testGetAllContacts(){
        contactsActivity.getAllContacts();
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
        assertNotNull(loginActivity);

        //onData(anything()).inAdapterView(withId(R.id.recycler_view2)).atPosition(0).perform(click());

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
