package com.example.agilesprintersapp.Model;

public class UserInfo {

    private String id;
    private String imageURL;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private String confirmPassword;

    //Empty Constructor
    public UserInfo(){

    }

    public UserInfo(String id, String imageURL, String fName, String lName, String username, String email, String phone) {

        this.id = id;
        this.imageURL = imageURL;
        this.firstName = fName;
        this.lastName = lName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phone;
    }

    //getter and setter methods

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lName) {
        this.lastName = lName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String uName) {
        this.username = uName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emailAddress) {
        this.email = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone) {
        this.phoneNumber = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pwd) {
        this.password = pwd;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String pwd2) {
        this.confirmPassword = pwd2;
    }
}
