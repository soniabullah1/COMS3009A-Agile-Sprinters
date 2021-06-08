package com.example.agilesprintersapp.Fragments;

import com.android21buttons.fragmenttestrule.FragmentTestRule;
import com.example.agilesprintersapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class StoryFragmentTest {
    @Rule
    public FragmentTestRule<?, StoryFragment> profilefragmentTestRule = FragmentTestRule.create(StoryFragment.class);
    StoryFragment profileFragment = profilefragmentTestRule.getFragment();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testLaunch(){
        onView(withId(R.id.circular_status_view)).check(matches((isDisplayed())));
        onView(withId(R.id.myStory)).check(matches(isDisplayed()));
        onView(withId(R.id.deleteButton)).check(matches(isDisplayed()));
        onView(withId(R.id.stories)).check(matches(isDisplayed()));
        onView(withId(R.id.floatingActionButton)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
    }
}