package main.java.Sunchip.models;

public class DriverModel {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String confirmPassword;
    private String manufacturer;
    private String licensePlateNo;
    private String colour;



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

    public String getManufacturer() { return manufacturer; }

    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }


    public String getLicensePlateNo() { return licensePlateNo; }

    public void setLicensePlateNo(String licensePlateNo) { this.licensePlateNo = licensePlateNo; }

    public String getColour() { return colour; }

    public void setColour(String colour) { this.colour = colour; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }

    public String getConfirmPassword() {
        return confirmPassword;
    }

}

