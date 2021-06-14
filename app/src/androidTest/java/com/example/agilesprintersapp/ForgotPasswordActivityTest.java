package com.example.agilesprintersapp;

import android.app.Instrumentation;
import android.content.Intent;

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
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class ForgotPasswordActivityTest {

    @Rule
    public ActivityTestRule<ForgotPasswordActivity> fPActivityTestRule = new ActivityTestRule<>(ForgotPasswordActivity.class);
    private ForgotPasswordActivity fPActivity = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(LoginActivity.class.getName(),null ,false);
    public static final String STRING_TO_BE_TYPED_EMAIL = "12345@gmail.com";

    @Before
    public void setUp() throws Exception {
        fPActivity = fPActivityTestRule.getActivity();
        fPActivity.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }

    @Test
    public void testLaunch(){
        onView(withId(R.id.textView4)).check(matches((isDisplayed())));
        onView(withId(R.id.EmailText)).check(matches(isDisplayed()));
        onView(withId(R.id.Btn_Reset)).check(matches(isDisplayed()));
        onView(withId(R.id.Btn_Return)).check(matches(isDisplayed()));
    }

//    @UiThread
//    @Test
//    public void testReturnButton(){
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        onView(withId(R.id.Btn_Return)).perform(click());
//        Activity loginActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
//        assertNotNull(loginActivity);
//
//        fPActivity.finish();
//
//    }

    @Test
    public void testResetButton(){
        onView(withId(R.id.EmailText)).perform(typeText(STRING_TO_BE_TYPED_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.Btn_Reset)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        fPActivity = null;
    }
}