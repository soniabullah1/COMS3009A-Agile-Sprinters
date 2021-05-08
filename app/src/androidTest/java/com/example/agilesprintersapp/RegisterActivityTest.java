package com.example.agilesprintersapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.test.espresso.Root;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

import static org.hamcrest.Matchers.allOf;

public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<RegisterActivity> registerActivityTestRule = new ActivityTestRule<>(RegisterActivity.class);
    private RegisterActivity registerActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null ,false);

    public static final String STRING_TO_BE_TYPED_FNAME = "rushil";
    public static final String STRING_TO_BE_TYPED_LNAME = "patel";
    public static final String STRING_TO_BE_TYPED_USERNAME = "rushil";
    public static final String STRING_TO_BE_TYPED_MAIL = "rushilpatel0703@gmail.com";
    public static final String STRING_TO_BE_TYPED_PHONE = "0610230497";
    public static final String STRING_TO_BE_TYPED_PASSWORD1 = "cakeface42";
    public static final String STRING_TO_BE_TYPED_PASSWORD2 = "cakeface42";


    @Before
    public void setUp() throws Exception {
        registerActivity = registerActivityTestRule.getActivity();
        registerActivity.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

    }


    @Test
    public void testLaunchTextView7(){
        TextView textViewTest = (TextView) registerActivity.findViewById(R.id.textView7);
        String actual = (String) textViewTest.getText();
        String expected = "Please fill out your details below:";

        assertEquals(actual,expected);
    }

    @Test
    public void testLaunchTextView11(){
        TextView textViewTest = (TextView) registerActivity.findViewById(R.id.textView11);
        String actual = (String) textViewTest.getText();
        String expected = "First Name:";

        assertEquals(actual,expected);
    }

    @Test
    public void testLaunchTextView16(){
        TextView textViewTest = (TextView) registerActivity.findViewById(R.id.textView16);
        String actual = (String) textViewTest.getText();
        String expected = "Last Name:";

        assertEquals(actual,expected);
    }

    @Test
    public void testLaunchTextView9(){
        TextView textViewTest = (TextView) registerActivity.findViewById(R.id.textView9);
        String actual = (String) textViewTest.getText();
        String expected = "Username:";

        assertEquals(actual,expected);
    }

    @Test
    public void testLaunchTextView8(){
        TextView textViewTest = (TextView) registerActivity.findViewById(R.id.textView8);
        String actual = (String) textViewTest.getText();
        String expected = "Email:";

        assertEquals(actual,expected);
    }

    @Test
    public void testLaunchTextView10(){
        TextView textViewTest = (TextView) registerActivity.findViewById(R.id.textView10);
        String actual = (String) textViewTest.getText();
        String expected = "Phone Number:";

        assertEquals(actual,expected);
    }

    @Test
    public void testLaunchTextView12(){
        TextView textViewTest = (TextView) registerActivity.findViewById(R.id.textView12);
        String actual = (String) textViewTest.getText();
        String expected = "Password:";

        assertEquals(actual,expected);
    }

    @Test
    public void testLaunchTextView13(){
        TextView textViewTest = (TextView) registerActivity.findViewById(R.id.textView13);
        String actual = (String) textViewTest.getText();
        String expected = "Confirm Password:";

        assertEquals(actual,expected);
    }

    @Test
    public void testLaunchTextView6(){
        TextView textViewTest = (TextView) registerActivity.findViewById(R.id.textView6);
        String actual = (String) textViewTest.getText();
        String expected = "Let's get you connected!";

        assertEquals(actual,expected);
    }

    @Test
    public void testLaunchEditTextFirstName(){
        View view = registerActivity.findViewById(R.id.FName);
        assertNotNull(view);
    }

    @Test
    public void testLaunchEditTextLastName(){
        View view = registerActivity.findViewById(R.id.LName);
        assertNotNull(view);
    }

    @Test
    public void testLaunchEditTextUsername(){
        View view = registerActivity.findViewById(R.id.editTextUsername);
        assertNotNull(view);
    }

    @Test
    public void testLaunchEditTextEmailAddress(){
        View view = registerActivity.findViewById(R.id.editTextEmailAddress);
        assertNotNull(view);
    }

    @Test
    public void testLaunchEditTextPhone(){
        View view = registerActivity.findViewById(R.id.editTextPhone);
        assertNotNull(view);
    }

    @Test
    public void testLaunchEditTextTextPassword2(){
        View view = registerActivity.findViewById(R.id.editTextTextPassword2);
        assertNotNull(view);
    }

    @Test
    public void testLaunchEditTextTextPassword3(){
        View view = registerActivity.findViewById(R.id.editTextTextPassword3);
        assertNotNull(view);
    }

    @Test
    public void testLaunchCheckBoxPwd(){
        View view = registerActivity.findViewById(R.id.checkBoxPwd);
        assertNotNull(view);
    }

    @Test
    public void testLaunchButton2(){
        View view = registerActivity.findViewById(R.id.button2);
        assertNotNull(view);
    }

    @Test
    public void testLaunchButton4(){
        View view = registerActivity.findViewById(R.id.button4);
        assertNotNull(view);
    }

    @Test
    public void testReturnToHomePageButton(){
        onView(withId(R.id.button4)).perform(click());
        Activity registerActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(registerActivity);

        registerActivity.finish();
    }

    @Test
    public void testCheckBoxPwd(){
        onView(withId(R.id.checkBoxPwd)).perform(click()).check(matches(ViewMatchers.isChecked()));
    }

    @Test
    public void testRegisterButton(){
        onView(withId(R.id.FName)).perform(typeText(STRING_TO_BE_TYPED_FNAME), closeSoftKeyboard());
        onView(withId(R.id.LName)).perform(typeText(STRING_TO_BE_TYPED_LNAME), closeSoftKeyboard());
        onView(withId(R.id.editTextUsername)).perform(typeText(STRING_TO_BE_TYPED_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.editTextEmailAddress)).perform(typeText(STRING_TO_BE_TYPED_MAIL), closeSoftKeyboard());
        onView(withId(R.id.editTextPhone)).perform(typeText(STRING_TO_BE_TYPED_PHONE), closeSoftKeyboard());
        onView(withId(R.id.editTextTextPassword2)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD1), closeSoftKeyboard());
        onView(withId(R.id.editTextTextPassword3)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD2), closeSoftKeyboard());

        onView(withId(R.id.button2)).perform(click());
        String check = registerActivity.unitTest;
        assertEquals(check,"True");

    }


    @After
    public void tearDown() throws Exception {
        registerActivity = null;
    }
}
