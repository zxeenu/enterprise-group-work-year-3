package main.Backend.Maps.MapStructs;

import org.json.simple.JSONArray;

public class Point {
    public double Lattitude;
    public double Longtitude;

    public Point(double Lat, double Long) {
        Lattitude = Lat;
        Longtitude = Long;
    }

    public Point(JSONArray j) {
        this.Lattitude = (double)j.get(0);
        this.Longtitude = (double)j.get(1);
    }

    /**
     * Get the point as a JSON Object Array. Used for contructing requests
     * @return
     */
    public JSONArray GetAsJSONArray() {
        return new JSONArray() {{ add(Lattitude); add(Longtitude); }};
    }
}
