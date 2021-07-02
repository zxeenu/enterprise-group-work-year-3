package main.java.com.enterprise.sunchip.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    public Location getLocationFromWebservice(String name) throws JsonProcessingException {
        RestTemplate rt = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        //change to weservice uri
        String LocationString = rt.getForObject("http://localhost:8081/v1/location-services-sunchip?location="+name, String.class);
        List<Location> locationList = objectMapper.readValue(LocationString, new TypeReference<List<Location>>(){});

        return locationList.get(0);

    }

    public String locationNameFromWebservice() throws JsonProcessingException {
        Location thisLocation = getLocationFromWebservice(location);
        return thisLocation.getName();
    }
    public float[] getLocationCoordinatesFromWebservice() throws JsonProcessingException {
        return convertStringCoordinates(getLocationFromWebservice(location).getCoordinates());
    }
    public String DestinationNameFromWebservice() throws JsonProcessingException {
        Location thisLocation = getLocationFromWebservice(destination);
        return thisLocation.getName();
    }
    public float[] getDestinationCoordinatesFromWebservice() throws JsonProcessingException {
        return convertStringCoordinates(getLocationFromWebservice(destination).getCoordinates());
    }

    public float[] convertStringCoordinates(String coordinates){
        coordinates = coordinates.substring(1, coordinates.length() - 1);
        String[] longitudeAndLatitude = coordinates.split("[,]", 0);
        float[] seperatedCoordinates = {Float.parseFloat(longitudeAndLatitude[0]),
                                        Float.parseFloat(longitudeAndLatitude[1])};
        return seperatedCoordinates;


    }




}
