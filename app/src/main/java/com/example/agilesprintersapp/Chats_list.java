package com.example.agilesprintersapp;

public class Chats_list {
    private String contactName;
    private String mesgRecieved;
    private String timeStamp;

    public Chats_list(String contactName, String mesgRecieved, String timeStamp) {
        this.contactName = contactName;
        this.mesgRecieved = mesgRecieved;
        this.timeStamp = timeStamp;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getMesgRecieved() {
        return mesgRecieved;
    }

    public void setMesgRecieved(String mesgRecieved) {
        this.mesgRecieved = mesgRecieved;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
