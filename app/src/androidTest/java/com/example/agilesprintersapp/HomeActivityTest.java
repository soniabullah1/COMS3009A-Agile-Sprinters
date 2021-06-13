package com.example.agilesprintersapp;

import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;


public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> homeActivityTestRule = new ActivityTestRule<>(HomeActivity.class);
    private HomeActivity homeActivity = null;
    Instrumentation.ActivityMonitor monitor1 = getInstrumentation().addMonitor(ContactsList.class.getName(),null ,false);

    @Before
    public void setUp() throws Exception {
        homeActivity = homeActivityTestRule.getActivity();
        homeActivity.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

    }

//    @Test
//    public void testFloatingButton(){
//        View view = homeActivity.findViewById(R.id.Chat_Icon);
//        assertNotNull(view);
//
//        onView(withId(R.id.Chat_Icon)).perform(click());
//        Activity contactListActivity = getInstrumentation().waitForMonitorWithTimeout(monitor1,5000);
//        assertNotNull(contactListActivity);
//    }

//    @UiThread
//    @Test
//    public void testFunction(){
//        homeActivity.onLogoutClick();
//    }



    @After
    public void tearDown() throws Exception {
        homeActivity = null;
    }

}