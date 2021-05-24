package com.example.agilesprintersapp.Fragments;

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
public class ProfileFragmentTest {
    @Rule
    public FragmentTestRule<?,ProfileFragment> profilefragmentTestRule = FragmentTestRule.create(ProfileFragment.class);
    ProfileFragment profileFragment = profilefragmentTestRule.getFragment();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testEditProfileImage() {
        onView(withId(R.id.edit_profile_image)).perform(click());
    }

    @Test()
    public void A_testLaunch() throws Exception {
        onView(withId(R.id.profile_image)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_profile_image)).check(matches(isDisplayed()));
        onView(withId(R.id.username)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_username)).check(matches(isDisplayed()));
    }



    @After
    public void tearDown() throws Exception {
    }
}