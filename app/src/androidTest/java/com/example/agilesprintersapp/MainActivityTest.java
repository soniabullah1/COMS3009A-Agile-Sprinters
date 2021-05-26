package com.example.agilesprintersapp;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.agilesprintersapp.Model.UserInfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mainActivity = null;

    UserInfo userInfo = new UserInfo("01","imageurl","unit","test", "unittest","unittest@gmail", "12345");

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

    @Test
    public void testGetters() {
        String expected = "01";
        assertEquals(expected,userInfo.getId());

        String expected1 = "imageurl";
        assertEquals(expected1, userInfo.getImageURL());

        String expected2 = "unit";
        assertEquals(expected2, userInfo.getFirstName());

        String expected3 = "test";
        assertEquals(expected3, userInfo.getLastName());

        String expected4 = "unittest";
        assertEquals(expected4, userInfo.getUsername());

        String expected5 = "unittest@gmail";
        assertEquals(expected5, userInfo.getEmail());

        String expected6 = "12345";
        assertEquals(expected6, userInfo.getPhoneNumber());

        String expected7 = null;
        assertEquals(expected7, userInfo.getPassword());

        String expected8 = null;
        assertEquals(expected8, userInfo.getConfirmPassword());

        mainActivity.finish();
    }

    @Test
    public void setId() {
        userInfo.setId("02");
        String expected = "02";
        assertEquals(expected, userInfo.getId());
        mainActivity.finish();
    }

    @Test
    public void setImageURL() {
        userInfo.setImageURL("image");
        String expected = "image";
        assertEquals(expected, userInfo.getImageURL());
        mainActivity.finish();
    }

    @Test
    public void setFirstName() {
        userInfo.setFirstName("Junit");
        String expected = "Junit";
        assertEquals(expected, userInfo.getFirstName());
        mainActivity.finish();
    }

    @Test
    public void setLastName() {
        userInfo.setLastName("TNT");
        String expected = "TNT";
        assertEquals(expected, userInfo.getLastName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mainActivity.finish();
    }

    @Test
    public void setUsername() {
        userInfo.setUsername("bored");
        String expected = "bored";
        assertEquals(expected, userInfo.getUsername());
        mainActivity.finish();
    }

    @Test
    public void setEmail() {
        userInfo.setEmail("gmail");
        String expected = "gmail";
        assertEquals(expected, userInfo.getEmail());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mainActivity.finish();
    }

    @Test
    public void setPhoneNumber() {
        try {
            Thread.sleep(4500);
            userInfo.setPhoneNumber("123456");
            String expected = "123456";
            assertEquals(expected, userInfo.getPhoneNumber());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mainActivity.finish();
    }

    @Test
    public void setPassword() {
        userInfo.setPassword("123456");
        String expected = "123456";
        assertEquals(expected, userInfo.getPassword());
        mainActivity.finish();
    }

    @Test
    public void setConfirmPassword() {
        userInfo.setPassword("123456");
        String expected = null;
        assertEquals(expected, userInfo.getConfirmPassword());
        mainActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}
