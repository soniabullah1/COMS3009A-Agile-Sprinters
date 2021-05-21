package com.example.agilesprintersapp;

import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class SettingsActivityTest {
    @Rule
    public ActivityTestRule<SettingsActivity> settingsActivityActivityTestRule = new ActivityTestRule<SettingsActivity>(SettingsActivity.class);
    private SettingsActivity settingsActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(LandingActivity.class.getName(),null ,false);

    @Before
    public void setUp() throws Exception {
        settingsActivity = settingsActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = settingsActivity.findViewById(R.id.btn_save);
        assertNotNull(view);
    }

    @Test
    public void testLogoutMenu(){

    }

    @After
    public void tearDown() throws Exception {
    }
}