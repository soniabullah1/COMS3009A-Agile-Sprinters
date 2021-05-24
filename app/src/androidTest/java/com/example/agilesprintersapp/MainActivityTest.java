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
    @Test
    public void getId() {
        String expected = "01";
        assertEquals(expected,userInfo.getId());
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
    public void getImageURL() {
        String expected = "imageurl";
        assertEquals(expected, userInfo.getImageURL());
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
    public void getFirstName() {
        String expected = "unit";
        assertEquals(expected, userInfo.getFirstName());
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
    public void getLastName() {
        String expected = "test";
        assertEquals(expected, userInfo.getLastName());
        mainActivity.finish();
    }

    @Test
    public void setLastName() {
        userInfo.setLastName("TNT");
        String expected = "TNT";
        assertEquals(expected, userInfo.getLastName());
        mainActivity.finish();
    }

    @Test
    public void getUsername() {
        String expected = "unittest";
        assertEquals(expected, userInfo.getUsername());
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
    public void getEmail() {
        String expected = "unittest@gmail";
        assertEquals(expected, userInfo.getEmail());
        mainActivity.finish();
    }

    @Test
    public void setEmail() {
        userInfo.setEmail("gmail");
        String expected = "gmail";
        assertEquals(expected, userInfo.getEmail());
        mainActivity.finish();
    }

    @Test
    public void getPhoneNumber() {
        String expected = "12345";
        assertEquals(expected, userInfo.getPhoneNumber());
        mainActivity.finish();
    }

    @Test
    public void setPhoneNumber() {
        userInfo.setPhoneNumber("123456");
        String expected = "123456";
        assertEquals(expected, userInfo.getPhoneNumber());
        mainActivity.finish();
    }

    @Test
    public void getPassword() {
        String expected = null;
        assertEquals(expected, userInfo.getPassword());
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
    public void getConfirmPassword() {
        String expected = null;
        assertEquals(expected, userInfo.getConfirmPassword());
        mainActivity.finish();
    }

    @Test
    public void setConfirmPassword() {
        userInfo.setPassword("123456");
        String expected = null;
        assertEquals(expected, userInfo.getConfirmPassword());
        mainActivity.finish();
    }


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

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}
