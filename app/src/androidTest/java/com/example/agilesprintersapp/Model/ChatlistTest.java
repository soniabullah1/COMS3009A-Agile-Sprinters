package com.example.agilesprintersapp.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChatlistTest {

    Chatlist chatList = new Chatlist("07");

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetId(){
        String expected = "07";
        assertEquals(expected,chatList.getId());
    }

    @Test
    public void testSetId() {
        chatList.setId("02");
        String expected = "02";
        assertEquals(expected, chatList.getId());
    }

    @After
    public void tearDown() throws Exception {
    }
}