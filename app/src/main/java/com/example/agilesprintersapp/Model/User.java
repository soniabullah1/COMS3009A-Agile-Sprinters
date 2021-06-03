package com.example.agilesprintersapp.Model;

public class User {

    private String id;
    private String username;
    private String imageURL;
    private  String email;
    private  String contactNumber;

    public User(String id, String username, String imageURL){
        this.id = id;
        this.username = username;
        this.imageURL = imageURL;
        this.email = email;
        this.contactNumber = contactNumber;

    }

    public User(String id){
        this.id = id;
    }

    public User(){

    }

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() { return contactNumber; }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}

