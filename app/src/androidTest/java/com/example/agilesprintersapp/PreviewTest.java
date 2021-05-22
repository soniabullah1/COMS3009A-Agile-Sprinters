package com.example.agilesprintersapp;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PreviewTest {
    @Rule
    public ActivityTestRule<Preview> PreviewTestRule = new ActivityTestRule<Preview>(Preview.class);
    private Preview preview = null;

    @Before
    public void setUp() throws Exception {
        preview = PreviewTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = preview.findViewById(R.id.textView);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
    }
}