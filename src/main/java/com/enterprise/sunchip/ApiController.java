package main.java.com.enterprise.sunchip;

import Backend.Maps.OpenRouteService.ORSHandler;
import Common.Shared;
import Tests.Const;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    public String TestingToken = Const.ORSToken;

    // /v1/location-services-sunchip?location=darkness
    @GetMapping("/v1/location-services-sunchip")
    public String getLocations(@RequestParam("location") String location) {

        var handler = new Backend.Maps.OpenRouteService.ORSHandler(TestingToken, false);
        var locations = handler.Search(location);

        JSONArray locatinList = new JSONArray();
        for (var loc : locations) {
//            System.out.println(loc.Name + "  " + loc.Coordinates.GetAsJSONArray());
            JSONObject _location = new JSONObject();
            _location.put("name",loc.Name);
            _location.put("coordinates",loc.Coordinates.GetAsJSONArray().toJSONString());
            locatinList.add(_location);
        }

//        JSONObject preparedList = new JSONObject();
//        preparedList.put("location_list", locatinList.toJSONString());

        return locatinList.toJSONString();
    }

    @GetMapping("/v1/location-services-sunchip/***")
    public String anythihingElse() {
        return "Please use '/v1/location-services-sunchip?location'";
    }

    @GetMapping("/")
    public String anythihingHome() {
        return "Please use '/v1/location-services-sunchip?location'";
    }

}
