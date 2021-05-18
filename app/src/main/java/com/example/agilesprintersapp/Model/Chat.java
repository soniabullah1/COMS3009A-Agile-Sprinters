package com.example.agilesprintersapp.Model;

public class Chat {

    private String sender;
    private String receiver;
    private String message;
    private String type;
    private String time;
    private String caption;

    private boolean isseen;

    public Chat(String sender, String receiver, String message, String type, String time, String caption){
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.type = type;
        this.isseen = isseen;
        this.time = time;
        this.caption = caption;
    }

    public Chat() {

    }

    public String getSender(){
        return sender;
    }

    public void setSender(String sender){
        this.sender = sender;
    }

    public String getReceiver(){
        return receiver;
    }

    public void setReceiver(String receiver){
        this.receiver = receiver;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public boolean isIsseen(){
        return isseen;
    }

    //ADDED IN
    public void setIsseen(boolean isseen){
        this.isseen = isseen;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
