package main.java.com.enterprise.sunchip.models;

import Database.Entities.User;

public class SignUpModel {
    private User.UserType userType;

    private String firstName;
    private String lastName;

    public String Manufacturer;
    public String LicensePlateNo;
    public String Color;

    private String username;
    private String password;


    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getLicensePlateNo() {
        return LicensePlateNo;
    }

    public void setLicensePlateNo(String licensePlateNo) {
        LicensePlateNo = licensePlateNo;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public User.UserType getUserType() {
        return userType;
    }

    public void setUserType(User.UserType userType) {
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
