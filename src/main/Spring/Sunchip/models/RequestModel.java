package main.Spring.Sunchip.models;

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

    public LocationModel getLocationFromWebservice(String name) throws JsonProcessingException {
        RestTemplate rt = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        //change to weservice uri
        String LocationString = rt.getForObject("http://localhost:8081/v1/location-services-sunchip?location="+name, String.class);
        List<LocationModel> locationModelList = objectMapper.readValue(LocationString, new TypeReference<List<LocationModel>>(){});

        return locationModelList.get(0);

    }

    public String getStartLocationNameFromWebservice() throws JsonProcessingException {
        LocationModel thisLocationModel = getLocationFromWebservice(location);
        return thisLocationModel.getName();
    }
    public Double[] getStartLocationCoordinatesFromWebservice() throws JsonProcessingException {
        return convertStringCoordinates(getLocationFromWebservice(location).getCoordinates());
    }
    public String getDestinationNameFromWebservice() throws JsonProcessingException {
        LocationModel thisLocationModel = getLocationFromWebservice(destination);
        return thisLocationModel.getName();
    }
    public Double[] getDestinationCoordinatesFromWebservice() throws JsonProcessingException {
        return convertStringCoordinates(getLocationFromWebservice(destination).getCoordinates());
    }

    public Double[] convertStringCoordinates(String coordinates){
        coordinates = coordinates.substring(1, coordinates.length() - 1);
        String[] longitudeAndLatitude = coordinates.split("[,]", 0);
        Double[] seperatedCoordinates = {Double.parseDouble(longitudeAndLatitude[0]),
                                         Double.parseDouble(longitudeAndLatitude[1])};
        return seperatedCoordinates;


    }




}
