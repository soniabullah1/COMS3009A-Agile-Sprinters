package com.example.agilesprintersapp.Model;

public class Story {

    private String sender;
    private String story;
    private String time;
    private String caption;

    public Story(String sender, String story, String caption) {
        this.sender = sender;
        this.story = story;
        this.time = time;
        this.caption = caption;
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

}
