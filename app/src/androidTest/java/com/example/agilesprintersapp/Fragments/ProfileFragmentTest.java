package com.example.agilesprintersapp.Fragments;

import androidx.annotation.UiThread;

import com.android21buttons.fragmenttestrule.FragmentTestRule;
import com.example.agilesprintersapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@RunWith(RobolectricTestRunner.class)
public class ProfileFragmentTest {

    @Rule
    public FragmentTestRule<?, ProfileFragment> profilefragmentTestRule = FragmentTestRule.create(ProfileFragment.class);
    ProfileFragment profileFragment = profilefragmentTestRule.getFragment();

    @Before
    public void setUp() throws Exception {

    }

    @Test()
    public void testLaunch() throws Exception {
        onView(withId(R.id.yourprofile)).check(matches((isDisplayed())));
        onView(withId(R.id.profile_image)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_profile_image)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_password)).check(matches(isDisplayed()));
        onView(withId(R.id.editdetails)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_email)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_phone_number)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_username)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()));
    }

    @UiThread
    @Test
    public void testEditProfileImage() {
        onView(withId(R.id.edit_profile_image)).perform(click());
    }

    @UiThread
    @Test
    public void testEditPassword() {
        onView(withId(R.id.edit_password)).perform(click());

    }

    @UiThread
    @Test
    public void testSaveButton() {
        onView(withId(R.id.btn_save)).perform(click());

    }

//    @Test
//    public void testNotNull() {
//        ProfileFragment fragment = new ProfileFragment();
//        startFragment(fragment);
//        assertNotNull(fragment);
//    }

    @After
    public void tearDown() throws Exception {
    }

//    public static void startFragment(ProfileFragment fragment) {
//        FragmentActivity activity = Robolectric.buildActivity(FragmentActivity.class)
//                .create()
//                .start()
//                .resume()
//                .get();
//
//        FragmentManager fragmentManager = activity.getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(fragment, null);
//        fragmentTransaction.commit();
//
//    }
}