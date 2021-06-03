package com.example.agilesprintersapp.Model;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserInfoTest {
    User userInfo = new User("01","unittest","imageurl");


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


}