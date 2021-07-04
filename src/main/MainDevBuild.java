package main;

import main.Backend.BackendContext;

import java.sql.SQLException;

public class MainDevBuild {
    public static void main(String[] args) throws SQLException {

//        System.out.println("Starting application...");
//        System.out.println("Connecting to database...");
//
//
//        var BEContext = new BackendContext("jdbc:sqlserver://localhost:1433;databaseName=JUNITDB;user=sa;password=QuidEst");




        // Testing the ORM Maps API

//        Logger.Instance.Add("Starting server", Logger.LogLevels.INFO);


        var handler = new main.Backend.Maps.OpenRouteService.ORSHandler("5b3ce3597851110001cf624856dfbc93ad4f41428588775eb447549b");
        var villa = handler.Search("red").get(0);
        var huravee = handler.Search("blue").get(0);
        var routes = handler.GetRoutes(villa, huravee);

        for (var r : routes) {
            System.out.println("Distance : " + r.Distance);
        }
    }
}
