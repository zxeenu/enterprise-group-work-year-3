package main.java.com.enterprise.sunchip.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestModel {
    private String location;
    private String destination;
    private String date;
    private String time;
    private Date dateAndTime;

    public Date getDateAndTime() throws ParseException {
        String datePlusTime = date + " " + time;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateAndTime = formatter.parse(datePlusTime);
        return dateAndTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


}
