package com.example.agilesprintersapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.view.View;

import androidx.annotation.UiThread;
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
import static org.junit.Assert.assertNotNull;

public class ChangePasswordActivityTest {

    @Rule
    public ActivityTestRule<ChangePasswordActivity> changePasswordActivityTestRule = new ActivityTestRule<>(ChangePasswordActivity.class);
    private ChangePasswordActivity changePasswordActivity = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(HomeActivity.class.getName(),null ,false);

    public static final String STRING_TO_BE_TYPED_PASSWORD1 = "unittest";
    public static final String STRING_TO_BE_TYPED_PASSWORD2 = "unittest";

    @Before
    public void setUp() throws Exception {
        changePasswordActivity = changePasswordActivityTestRule.getActivity();
        changePasswordActivity.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }

    @Test
    public void testLaunchItems(){
        View view1 = changePasswordActivity.findViewById(R.id.textView2);
        View view2 = changePasswordActivity.findViewById(R.id.NewPasswordText);
        View view3 = changePasswordActivity.findViewById(R.id.ConfirmPasswordText);
        View view4 = changePasswordActivity.findViewById(R.id.Btn_Change);
        View view5 = changePasswordActivity.findViewById(R.id.Btn_ReturnHome);

        assertNotNull(view1);
        assertNotNull(view2);
        assertNotNull(view3);
        assertNotNull(view4);
        assertNotNull(view5);

    }

    @Test
    public void testReturnToHomePageButton(){
        onView(withId(R.id.Btn_ReturnHome)).perform(click());
        Activity homeActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(homeActivity);
    }

    @UiThread
    @Test
    public void testChangePassword(){
        onView(withId(R.id.NewPasswordText)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD1), closeSoftKeyboard());
        onView(withId(R.id.ConfirmPasswordText)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD2), closeSoftKeyboard());

        //onView(withId(R.id.Btn_Change)).perform(click());
        changePasswordActivity.ChangePassword(STRING_TO_BE_TYPED_PASSWORD1, STRING_TO_BE_TYPED_PASSWORD2);
    }



    @After
    public void tearDown() throws Exception {
        changePasswordActivity = null;
    }
}

