package main.Backend.Maps.MapStructs;

import java.util.ArrayList;
import java.util.List;

public class Route {
    public float Distance;
    public float Duration;
    public List<Waypoint> Waypoints;
    public Waypoint StartWaypoint;

    public Route() {
        this.Waypoints = new ArrayList<Waypoint>();
    }
}
