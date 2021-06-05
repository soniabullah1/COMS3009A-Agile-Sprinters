package com.example.agilesprintersapp;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mainActivity = null;

    //User userInfo = new User("01","unittest","imageurl");

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mainActivity.findViewById(R.id.imageView56);
        assertNotNull(view);
        mainActivity.finish();
    }

//    @Test
//    public void testGetters() {
//        String expected = "01";
//        assertEquals(expected,userInfo.getId());
//
//        String expected1 = "imageurl";
//        assertEquals(expected1, userInfo.getImageURL());
//
//        String expected4 = "unittest";
//        assertEquals(expected4, userInfo.getUsername());
//
//        String expected5 = null;
//        assertEquals(expected5, userInfo.getEmail());
//
//        mainActivity.finish();
//    }
//
//    @Test
//    public void setId() {
//        userInfo.setId("02");
//        String expected = "02";
//        assertEquals(expected, userInfo.getId());
//        mainActivity.finish();
//    }
//
//    @Test
//    public void setImageURL() {
//        userInfo.setImageURL("image");
//        String expected = "image";
//        assertEquals(expected, userInfo.getImageURL());
//        mainActivity.finish();
//    }
//
//    @Test
//    public void setUsername() {
//        userInfo.setUsername("bored");
//        String expected = "bored";
//        assertEquals(expected, userInfo.getUsername());
//        mainActivity.finish();
//    }
//
//    @Test
//    public void setEmail() {
//        userInfo.setEmail("gmail");
//        String expected = "gmail";
//        assertEquals(expected, userInfo.getEmail());
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        mainActivity.finish();
//    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}
