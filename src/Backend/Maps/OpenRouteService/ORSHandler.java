package Backend.Maps.OpenRouteService;


import Backend.BackendContext;
import Backend.Maps.MapStructs.Location;
import Backend.Maps.MapStructs.Point;
import Backend.Maps.MapStructs.Route;
import Backend.Maps.MapStructs.Waypoint;
import Common.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ORSHandler {
    public String Token;
    private HttpClient Client;
    private static final String DirectionPostEndPoint = "https://api.openrouteservice.org/v2/directions/driving-car/geojson";
    private static final String GeocodeSearchEndPoint = "https://api.openrouteservice.org/geocode/search";

    /**
     * Constructor
     * @param token Token from Open Route Service
     */
    public ORSHandler(String token) {
        this(token, true);
    }
    public enum DistanceUnits {
        METERS("m"),
        KILOMETERS("km"),
        MILES("mi");

        public final String APIUnit;

        DistanceUnits(String label) {
            this.APIUnit = label;
        }
    }
    public DistanceUnits DistanceUnit = DistanceUnits.KILOMETERS;

    /**
     * This function will get a list of routes between 2 points.
     * @param Start Start point
     * @param End Bunch of points. Will include the end point and any mid points
     * @return List of routes found
     */
    public List<Route> GetRoutes(Point Start, Point...End) {
        return GetRoutes(Start, Arrays.asList(End));
    }

    /**
     * This function will get a list of rotues between multiple points
     * @param Start Start point
     * @param End The end point and any other points there might be
     * @return Route
     */
    public List<Route> GetRoutes(Point Start, List<Point> End) {
        try {
            // Create the stuff needed to create a new request
            var jstruct = new JSONObject();
            var coordinates = new JSONArray();
            coordinates.add(Start.GetAsJSONArray());
            for (var p : End) {
                coordinates.add(p.GetAsJSONArray());
            }
            jstruct.put("coordinates", coordinates);
            var reponse = (JSONObject)new JSONParser().parse(Networking.SendJsonPost(DirectionPostEndPoint, jstruct, Token));

            // Parse through all the routes
            var routes = new ArrayList<Route>();
            var routeiterator = ((JSONArray)reponse.get("features")).iterator();
            while (routeiterator.hasNext()) {
                var currentread = (JSONObject)routeiterator.next();
                var currentobj = new Route();
                var summery = (JSONObject)((JSONObject)currentread.get("properties")).get("summary");
                currentobj.Distance = (float)((double)summery.get("distance"));
                currentobj.Duration = (float)((double)summery.get("duration"));

                // Parse through all the coordinates on the route
                var coorinates = (JSONArray)((JSONObject)currentread.get("geometry")).get("coordinates");
                var coorditerator = coorinates.iterator();
                Waypoint previousWaypoint = null;
                while (coorditerator.hasNext()) {
                    var currentcoord = (JSONArray)coorditerator.next();
                    var currentpoint = new Point((double)currentcoord.get(0), (double)currentcoord.get(1));
                    var currentwaypoint = new Waypoint(currentpoint);

                    // Add the coordinate to the route waypoints
                    currentobj.Waypoints.add(currentwaypoint);

                    // Set the current waypoint as the next way point of the current way point
                    if (previousWaypoint != null) {
                        previousWaypoint.Next = currentwaypoint;
                    } else {
                        // if there is no previous waypoint, that means this is the first waypoint,
                        // so we will set it as the first waypoint of the route
                        currentobj.StartWaypoint = currentwaypoint;
                    }
                    previousWaypoint = currentwaypoint;
                }

                // Add the route to the list of routes
                routes.add(currentobj);
            }
            return routes;

        } catch (Exception e) {
            Logger.Instance.Add("Failed to fetch and process routes", Logger.LogLevels.CRITICAL);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This function get a list of routes between 2 locations
     * @param Start Start location
     * @param End Bunch of locations. Will include the end point and any midpoints
     * @return List of routes found
     */
    public List<Route> GetRoutes(Location Start, Location...End) {
        List<Point> points = new ArrayList<>();
        for (var l : End) {
            points.add(l.Coordinates);
        }
        return GetRoutes(Start.Coordinates, points);
    }

    /**
     * This function will send a request to the server to get the search results for a query
     * @param query Query to look up
     * @return List of locations
     */
    public List<Location> Search(String query) {
        try {
            List<Location> returnObj = new ArrayList<>();
            var end = new JSONArray();
            end.add(new JSONArray(){{ add("text"); add(query); }});
            var SearchResult = (JSONObject)new JSONParser().parse(Networking.SendGetRequest(GeocodeSearchEndPoint, end, Token));

            var featureList = ((JSONArray)SearchResult.get("features"));
            var featureListIterator = featureList.iterator();

            while (featureListIterator.hasNext()) {
                var currentFeature = (JSONObject)featureListIterator.next();
                var coordinatesJson = (JSONArray)((JSONObject)currentFeature.get("geometry")).get("coordinates");
                var propertiesJson = ((JSONObject)currentFeature.get("properties"));
                var coordinates = new Point(coordinatesJson);
                var location = new Location();
                location.Coordinates = coordinates;
                location.Name = (String)propertiesJson.get("name");
                returnObj.add(location);
            }

            return returnObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    };

    /**
     * Constructor with validator token
     * @param token Token from Open Route Service
     * @param ValidateToken If true will validate the token
     */
    public ORSHandler(String token, boolean ValidateToken) {
        if (ValidateToken) {
            if (!VerifyToken(token)) {
                Logger.Instance.Add("Warning : Open Route Service Token invalid : Create a new token at https://openrouteservice.org/dev", Logger.LogLevels.WARNING);
            } else {
                Logger.Instance.Add("Open Reoute Service Token Validated", Logger.LogLevels.INFO);
            }
        }
        Token = token;
        Client = HttpClient.newHttpClient();
    }

    /**
     * This function will verify the token by sending a test package to the ORS service
     * @param token Token to test
     * @return true if the token is valid and false if it is not
     */
    public static boolean VerifyToken(String token) {
        var testReq = new JSONObject();
        var coords = new JSONArray();
        coords.add(new JSONArray() {{
            add(8.681495);
            add(49.41461);
        }});

        coords.add(new JSONArray() {{
            add(8.686507);
            add(49.41943);
        }});

        coords.add(new JSONArray() {{
            add(8.687872);
            add(49.420318);
        }});

        testReq.put("coordinates", coords);
        var stream = Networking.SendJsonPost(DirectionPostEndPoint, testReq, token);
        return !stream.contains("Daily quota reached or API key unauthorized");
    }
}
