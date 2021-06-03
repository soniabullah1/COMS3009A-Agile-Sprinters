package com.example.agilesprintersapp;

import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuItem;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> homeActivityTestRule = new ActivityTestRule<>(HomeActivity.class);
    private HomeActivity homeActivity = null;

    private BottomNavigationView mBottomNavigation;
    private static final int[] MENU_CONTENT_ITEM_IDS = {R.id.chats, R.id.contacts, R.id.settings, R.id.calls, R.id.camera};
    private Map<Integer, String> mMenuStringContent;



    @Before
    public void setUp() throws Exception {
        homeActivity = homeActivityTestRule.getActivity();
        homeActivity.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

        mBottomNavigation = (BottomNavigationView) homeActivity.findViewById(R.id.bottomNav);

        final Resources res = homeActivity.getResources();

    }

    @UiThreadTest
    @Test
    public void testAddItemsWithoutMenuInflation() {
        BottomNavigationView navigation = new BottomNavigationView(homeActivityTestRule.getActivity());
        homeActivityTestRule.getActivity().setContentView(navigation);
        navigation.getMenu().add("Item1");
        navigation.getMenu().add("Item2");
        assertEquals(2, navigation.getMenu().size());
        navigation.getMenu().removeItem(0);
        navigation.getMenu().removeItem(0);
        assertEquals(0, navigation.getMenu().size());
        homeActivity.finish();
    }

//    @Test
//    public void testNavBarBasics() {
//        // Check the contents of the Menu object
//        final Menu menu = mBottomNavigation.getMenu();
//        assertNotNull("Menu should not be null", menu);
//        assertEquals("Should have matching number of items", MENU_CONTENT_ITEM_IDS.length, menu.size());
//        homeActivity.finish();
//    }
//
//    @UiThreadTest
//    @Test
//    public void testNavBarItemChecking() throws Throwable {
//        final Menu menu = mBottomNavigation.getMenu();
//        checkAndVerifyExclusiveItem(menu, R.id.chats);
//        checkAndVerifyExclusiveItem(menu, R.id.contacts);
//        checkAndVerifyExclusiveItem(menu, R.id.settings);
//        checkAndVerifyExclusiveItem(menu, R.id.calls);
//        homeActivity.finish();
//    }
//
//    @Test
//    public void testNavBarItems(){
//        View view = homeActivity.findViewById(R.id.camera);
//        View view1 = homeActivity.findViewById(R.id.settings);
//        View view2 = homeActivity.findViewById(R.id.contacts);
//        View view3 = homeActivity.findViewById(R.id.chats);
//
//        assertNotNull(view);
//        assertNotNull(view1);
//        assertNotNull(view2);
//        assertNotNull(view3);
//
//        homeActivity.finish();
//    }
//
//    @Test
//    public void Z_testNavBarCLicks(){
//        onView(withId(R.id.chats)).perform(click());
//        onView(withId(R.id.contacts)).perform(click());
//        onView(withId(R.id.camera)).perform(click());
//        onView(withId(R.id.settings)).perform(click());
//        homeActivity.finish();
//    }


    @After
    public void tearDown() throws Exception {
        homeActivity = null;
    }

    private void checkAndVerifyExclusiveItem(final Menu menu, final int id) throws Throwable {
        menu.findItem(id).setChecked(true);
        for (int i = 0; i < menu.size(); i++) {
            final MenuItem item = menu.getItem(i);
            if (item.getItemId() == id) {
                assertTrue(item.isChecked());
            } else {
                assertFalse(item.isChecked());
            }
        }
    }

}