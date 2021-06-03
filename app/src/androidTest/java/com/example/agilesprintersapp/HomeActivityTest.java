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

//    @UiThreadTest
//    @Test
//    public void testAddItemsWithoutMenuInflation() {
//        BottomNavigationView navigation = new BottomNavigationView(homeActivityTestRule.getActivity());
//        homeActivityTestRule.getActivity().setContentView(navigation);
//        navigation.getMenu().add("Item1");
//        navigation.getMenu().add("Item2");
//        assertEquals(2, navigation.getMenu().size());
//        navigation.getMenu().removeItem(0);
//        navigation.getMenu().removeItem(0);
//        assertEquals(0, navigation.getMenu().size());
//        homeActivity.finish();
//    }

//    @Test
//    public void testFloatingButton(){
//        View view = homeActivity.findViewById(R.id.Chat_Icon);
//        assertNotNull(view);
//
//        onView(withId(R.id.Chat_Icon)).perform(click());
//        Activity contactListActivity = getInstrumentation().waitForMonitorWithTimeout(monitor1,5000);
//        assertNotNull(contactListActivity);
//    }



    @After
    public void tearDown() throws Exception {
        homeActivity = null;
    }

//    private void checkAndVerifyExclusiveItem(final Menu menu, final int id) throws Throwable {
//        menu.findItem(id).setChecked(true);
//        for (int i = 0; i < menu.size(); i++) {
//            final MenuItem item = menu.getItem(i);
//            if (item.getItemId() == id) {
//                assertTrue(item.isChecked());
//            } else {
//                assertFalse(item.isChecked());
//            }
//        }
//    }

}