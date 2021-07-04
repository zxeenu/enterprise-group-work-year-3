package main.Backend.Maps.OpenRouteService;


import main.Common.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Networking {

    /**
     * This function will accept a JSON Object and send it to a POST endpoint.
     * @param EndpointURL Endpoint to send it to
     * @param JSONObject JSON Object to send
     * @param Token Token to use
     * @return Response from the server
     */
    public static String SendJsonPost(String EndpointURL, JSONObject JSONObject, String Token) {
        try {
            Logger.Instance.Add("Sending JSON to Endpoint " + EndpointURL, Logger.LogLevels.DEBUG);
            HttpClient client = HttpClient.newBuilder()
                    .build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(EndpointURL))
                    .setHeader("Content-Type", "application/json; charset=utf-8")
                    .setHeader("Authorization", Token)
                    .POST(HttpRequest.BodyPublishers.ofString(JSONObject.toJSONString()))
                    .build();


            System.out.println(JSONObject.toJSONString());

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                Logger.Instance.Add("POST request status code not 200 : Actual : " + response.statusCode(), Logger.LogLevels.WARNING);
            } else {
                Logger.Instance.Add("POST Request successful", Logger.LogLevels.INFO);
            }
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String SendGetRequest(String EndPointURL, JSONArray Parameters, String Token) {
        try {
            Logger.Instance.Add("Sending GET Reuest to Endpoint " + EndPointURL, Logger.LogLevels.INFO);
            Parameters.add(new JSONArray() {{
                add("api_key");
                add(Token);
            }}); // Add the access token to URL

            var urlextension = "?";
            for (var p : Parameters.toArray()) {
                var pc = (JSONArray) p;
                urlextension += pc.get(0) + "=" + URLEncoder.encode((String) pc.get(1), java.nio.charset.StandardCharsets.UTF_8.toString())  + "&";
            }
            urlextension = urlextension.substring(0, urlextension.length() - 1);
            var finalUrl = EndPointURL + urlextension;


            System.out.println(urlextension);

            // Send the actual request
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(finalUrl))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                Logger.Instance.Add("GET request status code not 200 : Actual : " + response.statusCode(), Logger.LogLevels.WARNING);
            } else {
                Logger.Instance.Add("GET request successful", Logger.LogLevels.INFO);
            }

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
