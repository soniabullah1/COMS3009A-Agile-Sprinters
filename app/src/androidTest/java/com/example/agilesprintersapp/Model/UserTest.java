package com.example.agilesprintersapp.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    User user = new User("01","unittest","imageurl", "status");


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetters() {
        String expected = "01";
        assertEquals(expected,user.getId());

        String expected1 = "imageurl";
        assertEquals(expected1, user.getImageURL());

        String expected4 = "unittest";
        assertEquals(expected4, user.getUsername());

        String expected6 = "status";
        assertEquals(expected6, user.getStatus());

        String expected5 = null;
        assertEquals(expected5, user.getEmail());
    }

    @Test
    public void setId() {
        user.setId("02");
        String expected = "02";
        assertEquals(expected, user.getId());
    }

    @Test
    public void setImageURL() {
        user.setImageURL("image");
        String expected = "image";
        assertEquals(expected, user.getImageURL());
    }

    @Test
    public void setUsername() {
        user.setUsername("bored");
        String expected = "bored";
        assertEquals(expected, user.getUsername());
    }

    @Test
    public void setEmail() {
        user.setEmail("gmail");
        String expected = "gmail";
        assertEquals(expected, user.getEmail());
    }


}