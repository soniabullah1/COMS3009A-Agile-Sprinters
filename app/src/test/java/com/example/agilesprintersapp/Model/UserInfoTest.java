package com.example.agilesprintersapp.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserInfoTest {
    UserInfo userInfo = new UserInfo("01","imageurl","unit","test", "unittest","unittest@gmail", "12345");


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getId() {
        String expected = "01";
        assertEquals(expected,userInfo.getId());
    }

    @Test
    public void setId() {
        userInfo.setId("02");
        String expected = "02";
        assertEquals(expected, userInfo.getId());
    }

    @Test
    public void getImageURL() {
        String expected = "imageurl";
        assertEquals(expected, userInfo.getImageURL());
    }

    @Test
    public void setImageURL() {
        userInfo.setImageURL("image");
        String expected = "image";
        assertEquals(expected, userInfo.getImageURL());
    }

    @Test
    public void getFirstName() {
        String expected = "unit";
        assertEquals(expected, userInfo.getFirstName());
    }

    @Test
    public void setFirstName() {
        userInfo.setFirstName("Junit");
        String expected = "Junit";
        assertEquals(expected, userInfo.getFirstName());
    }

    @Test
    public void getLastName() {
        String expected = "test";
        assertEquals(expected, userInfo.getLastName());
    }

    @Test
    public void setLastName() {
        userInfo.setLastName("TNT");
        String expected = "TNT";
        assertEquals(expected, userInfo.getLastName());
    }

    @Test
    public void getUsername() {
        String expected = "unittest";
        assertEquals(expected, userInfo.getUsername());
    }

    @Test
    public void setUsername() {
        userInfo.setUsername("bored");
        String expected = "bored";
        assertEquals(expected, userInfo.getUsername());
    }

    @Test
    public void getEmail() {
        String expected = "unittest@gmail";
        assertEquals(expected, userInfo.getEmail());
    }

    @Test
    public void setEmail() {
        userInfo.setEmail("gmail");
        String expected = "gmail";
        assertEquals(expected, userInfo.getEmail());
    }

    @Test
    public void getPhoneNumber() {
        String expected = "12345";
        assertEquals(expected, userInfo.getPhoneNumber());
    }

    @Test
    public void setPhoneNumber() {
        userInfo.setPhoneNumber("123456");
        String expected = "123456";
        assertEquals(expected, userInfo.getPhoneNumber());
    }

    @Test
    public void getPassword() {
        String expected = null;
        assertEquals(expected, userInfo.getPassword());
    }

    @Test
    public void setPassword() {
        userInfo.setPassword("123456");
        String expected = "123456";
        assertEquals(expected, userInfo.getPassword());
    }

    @Test
    public void getConfirmPassword() {
        String expected = null;
        assertEquals(expected, userInfo.getConfirmPassword());
    }

    @Test
    public void setConfirmPassword() {
        userInfo.setPassword("123456");
        String expected = null;
        assertEquals(expected, userInfo.getConfirmPassword());
    }
}