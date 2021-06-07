package com.example.agilesprintersapp.Model;

public class Story {

    private String sender;
    private String story;
    private String time;
    private String caption;
    private String username;

    public Story(String sender, String story, String time, String caption, String username) {
        this.sender = sender;
        this.story = story;
        this.time = time;
        this.caption = caption;
        this.username = username;
    }

    public Story(){

    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
