package com.example.agilesprintersapp;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StoryActivityTest {

    @Rule
    public ActivityTestRule<StoryActivity> storyActivityTestRule = new ActivityTestRule<>(StoryActivity.class);
    private StoryActivity storyActivity = null;

    @Before
    public void setUp() throws Exception {
        storyActivity = storyActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view1 = storyActivity.findViewById(R.id.username);
        View view2 = storyActivity.findViewById(R.id.storyTimeStamp);
        View view3 = storyActivity.findViewById(R.id.close);
        View view4 = storyActivity.findViewById(R.id.story);
        View view5 = storyActivity.findViewById(R.id.storyCap);

        assertNotNull(view1);
        assertNotNull(view2);
        assertNotNull(view3);
        assertNotNull(view4);
        assertNotNull(view5);
    }

    @Test
    public void testTimeFunction(){
        String expected = "12 Jun, 10:20 AM";
        String time = storyActivity.convertTime("1623486043728");
        assertEquals(expected, time);
    }

    @Test
    public void testGetProfileFunction(){
        storyActivity.getCurrentUserPicture("1HYeIejMyvhSemoNl2UbYXC9SvB3");
    }

    @After
    public void tearDown() throws Exception {
        storyActivity = null;
    }
}