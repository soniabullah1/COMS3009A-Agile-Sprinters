package com.example.agilesprintersapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<RegisterActivity> registerActivityTestRule = new ActivityTestRule<>(RegisterActivity.class);
    public ActivityTestRule<LandingActivity> landingActivityTestRule = new ActivityTestRule<>(LandingActivity.class);

    private RegisterActivity registerActivity = null;
    private LandingActivity landingActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null ,false);

    public static final String STRING_TO_BE_TYPED_FNAME = "unit";
    public static final String STRING_TO_BE_TYPED_LNAME = "tester";
    public static final String STRING_TO_BE_TYPED_USERNAME = "unittester";
    public static final String STRING_TO_BE_TYPED_MAIL = "seedr106@gmail.com";
    public static final String STRING_TO_BE_TYPED_PHONE = "0610230497";
    public static final String STRING_TO_BE_TYPED_PASSWORD1 = "unittester";
    public static final String STRING_TO_BE_TYPED_PASSWORD2 = "unittester";
    public static final String STRING_TO_BE_TYPED_PASSWORD_SHORT = "short";

    @Before
    public void setUp() throws Exception {
        registerActivity = registerActivityTestRule.getActivity();
        registerActivity.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
        landingActivity = landingActivityTestRule.getActivity();
        //landingActivity.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }


    @Test
    public void testLaunchTextView7(){
        TextView textViewTest = (TextView) registerActivity.findViewById(R.id.textView7);
        String actual = (String) textViewTest.getText();
        String expected = "Please fill out your details below:";

        assertEquals(actual,expected);
        registerActivity.finish();
    }

    @Test
    public void testLaunchTextView11(){
        TextView textViewTest = (TextView) registerActivity.findViewById(R.id.textView11);
        String actual = (String) textViewTest.getText();
        String expected = "First Name:";

        assertEquals(actual,expected);
        registerActivity.finish();
    }

    @Test
    public void testLaunchTextView16(){
        TextView textViewTest = (TextView) registerActivity.findViewById(R.id.textView16);
        String actual = (String) textViewTest.getText();
        String expected = "Last Name:";

        assertEquals(actual,expected);
        registerActivity.finish();
    }

    @Test
    public void testLaunchTextView9(){
        TextView textViewTest = (TextView) registerActivity.findViewById(R.id.textView9);
        String actual = (String) textViewTest.getText();
        String expected = "Username:";

        assertEquals(actual,expected);
        registerActivity.finish();
    }

    @Test
    public void testLaunchTextView8(){
        TextView textViewTest = (TextView) registerActivity.findViewById(R.id.textView8);
        String actual = (String) textViewTest.getText();
        String expected = "Email:";

        assertEquals(actual,expected);
        registerActivity.finish();
    }

    @Test
    public void testLaunchTextView10(){
        TextView textViewTest = (TextView) registerActivity.findViewById(R.id.textView10);
        String actual = (String) textViewTest.getText();
        String expected = "Phone Number:";

        assertEquals(actual,expected);
        registerActivity.finish();
    }

    @Test
    public void testLaunchTextView12(){
        TextView textViewTest = (TextView) registerActivity.findViewById(R.id.textView12);
        String actual = (String) textViewTest.getText();
        String expected = "Password:";

        assertEquals(actual,expected);
        registerActivity.finish();
    }

    @Test
    public void testLaunchTextView13(){
        TextView textViewTest = (TextView) registerActivity.findViewById(R.id.textView13);
        String actual = (String) textViewTest.getText();
        String expected = "Confirm Password:";

        assertEquals(actual,expected);
        registerActivity.finish();
    }

    @Test
    public void testLaunchTextView6(){
        TextView textViewTest = (TextView) registerActivity.findViewById(R.id.textView6);
        String actual = (String) textViewTest.getText();
        String expected = "Let's get you connected!";

        assertEquals(actual,expected);
        registerActivity.finish();
    }

    @Test
    public void testLaunchItems(){
        View view = registerActivity.findViewById(R.id.FName);
        View view1 = registerActivity.findViewById(R.id.LName);
        View view2 = registerActivity.findViewById(R.id.editTextUsername);
        View view3 = registerActivity.findViewById(R.id.editTextEmailAddress);
        View view4 = registerActivity.findViewById(R.id.editTextPhone);
        View view5 = registerActivity.findViewById(R.id.editTextTextPassword2);
        View view6 = registerActivity.findViewById(R.id.editTextTextPassword3);
        View view7 = registerActivity.findViewById(R.id.checkBoxPwd);
        View view8 = registerActivity.findViewById(R.id.button2);
        View view9 = registerActivity.findViewById(R.id.button4);

        assertNotNull(view);
        assertNotNull(view1);
        assertNotNull(view2);
        assertNotNull(view3);
        assertNotNull(view4);
        assertNotNull(view5);
        assertNotNull(view6);
        assertNotNull(view7);
        assertNotNull(view8);
        assertNotNull(view9);

        registerActivity.finish();
    }

    @Test
    public void testReturnToHomePageButton(){
        onView(withId(R.id.button4)).perform(click());
        Activity landingActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNull(landingActivity);

        registerActivity.finish();
    }

    @Test
    public void testCheckBoxPwd(){
        onView(withId(R.id.checkBoxPwd)).perform(click()).check(matches(ViewMatchers.isChecked()));
        registerActivity.finish();
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
        registerActivity.finish();
    }

    @Test
    public void testNegative1RegisterButton(){
        onView(withId(R.id.button2)).perform(click());
        String check = registerActivity.unitTest;
        assertEquals(check,"True");
        registerActivity.finish();
    }

    @Test
    public void testNegative2RegisterButton(){
        onView(withId(R.id.FName)).perform(typeText(STRING_TO_BE_TYPED_FNAME), closeSoftKeyboard());
        onView(withId(R.id.button2)).perform(click());
        String check = registerActivity.unitTest;
        assertEquals(check,"True");
        registerActivity.finish();
    }

    @Test
    public void testNegative3RegisterButton(){
        onView(withId(R.id.LName)).perform(typeText(STRING_TO_BE_TYPED_LNAME), closeSoftKeyboard());
        onView(withId(R.id.button2)).perform(click());
        String check = registerActivity.unitTest;
        assertEquals(check,"True");
        registerActivity.finish();
    }

    @Test
    public void testNegative4RegisterButton(){
        onView(withId(R.id.editTextUsername)).perform(typeText(STRING_TO_BE_TYPED_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.button2)).perform(click());
        String check = registerActivity.unitTest;
        assertEquals(check,"True");
        registerActivity.finish();
    }

    @Test
    public void testNegative5RegisterButton(){
        onView(withId(R.id.editTextEmailAddress)).perform(typeText(STRING_TO_BE_TYPED_MAIL), closeSoftKeyboard());
        onView(withId(R.id.button2)).perform(click());
        String check = registerActivity.unitTest;
        assertEquals(check,"True");
        registerActivity.finish();
    }

    @Test
    public void testNegative6RegisterButton(){
        onView(withId(R.id.editTextPhone)).perform(typeText(STRING_TO_BE_TYPED_PHONE), closeSoftKeyboard());
        onView(withId(R.id.button2)).perform(click());
        String check = registerActivity.unitTest;
        assertEquals(check,"True");
        registerActivity.finish();
    }

    @Test
    public void testNegative7RegisterButton(){
        onView(withId(R.id.editTextTextPassword2)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD1), closeSoftKeyboard());
        onView(withId(R.id.button2)).perform(click());
        String check = registerActivity.unitTest;
        assertEquals(check,"True");
        registerActivity.finish();
    }

    @Test
    public void testNegative8RegisterButton(){
        onView(withId(R.id.editTextTextPassword3)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD2), closeSoftKeyboard());
        onView(withId(R.id.button2)).perform(click());
        String check = registerActivity.unitTest;
        assertEquals(check,"True");
        registerActivity.finish();
    }


    @Test
    public void testNegative10RegisterButton(){
        onView(withId(R.id.editTextTextPassword2)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD_SHORT), closeSoftKeyboard());
        onView(withId(R.id.editTextTextPassword3)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD2), closeSoftKeyboard());
        onView(withId(R.id.button2)).perform(click());
        String check = registerActivity.unitTest;
        assertEquals(check,"True");
        registerActivity.finish();
    }


    @Test
    public void testRegister(){
        registerActivity.Register(STRING_TO_BE_TYPED_FNAME,STRING_TO_BE_TYPED_LNAME,STRING_TO_BE_TYPED_USERNAME,STRING_TO_BE_TYPED_MAIL,
                STRING_TO_BE_TYPED_PHONE,STRING_TO_BE_TYPED_PASSWORD1);

        registerActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        registerActivity = null;
        landingActivity = null;
    }
}
