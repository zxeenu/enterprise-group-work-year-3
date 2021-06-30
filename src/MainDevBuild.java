import Backend.BackendContext;
import Backend.Maps.MapStructs.Point;
import Backend.Maps.OpenRouteService.ORSHandler;
import Common.Logger;
import Database.Entities.User;

import java.nio.channels.Pipe;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Handler;
import java.util.logging.Level;

public class MainDevBuild {
    public static void main(String[] args) throws SQLException {

        System.out.println("Starting application...");
        System.out.println("Connecting to database...");


        var BEContext = new BackendContext("jdbc:sqlserver://localhost:1433;databaseName=JUNITDB;user=sa;password=QuidEst");




        // Testing the ORM Maps API

//        Logger.Instance.Add("Starting server", Logger.LogLevels.INFO);
//        var handler = new Backend.Maps.OpenRouteService.ORSHandler("5b3ce3597851110001cf624856dfbc93ad4f41428588775eb447549b");
//        var villa = handler.Search("Villa College").get(0);
//        var huravee = handler.Search("Huravee Building").get(0);
//        var routes = handler.GetRoutes(villa, huravee);
    }
}
