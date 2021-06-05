package com.example.agilesprintersapp.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChatTest {

    Chat chat = new Chat("sender","reciever","message","type","time","caption", false);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetters(){
        String expected1 = "sender";
        String expected2 = "reciever";
        String expected3 = "message";
        String expected4 = "type";
        String expected5 = "time";
        String expected6 = "caption";

        assertEquals(expected1,chat.getSender());
        assertEquals(expected2,chat.getReceiver());
        assertEquals(expected3,chat.getMessage());
        assertEquals(expected4,chat.getType());
        assertEquals(expected5,chat.getTime());
        assertEquals(expected6,chat.getCaption());
    }

    @Test
    public void setSender() {
        chat.setSender("newSender");
        String expected = "newSender";
        assertEquals(expected, chat.getSender());
    }

    @Test
    public void setReceiver() {
        chat.setReceiver("newReceiver");
        String expected = "newReceiver";
        assertEquals(expected, chat.getReceiver());
    }

    @Test
    public void setMessage() {
        chat.setMessage("newMessage");
        String expected = "newMessage";
        assertEquals(expected, chat.getMessage());
    }

    @Test
    public void setType() {
        chat.setType("newType");
        String expected = "newType";
        assertEquals(expected, chat.getType());
    }

    @Test
    public void setTime() {
        chat.setTime("newTime");
        String expected = "newTime";
        assertEquals(expected, chat.getTime());
    }

    @Test
    public void setCaption() {
        chat.setCaption("newCaption");
        String expected = "newCaption";
        assertEquals(expected, chat.getCaption());
    }


    @After
    public void tearDown() throws Exception {
    }
}