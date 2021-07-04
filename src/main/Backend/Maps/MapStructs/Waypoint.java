package main.Backend.Maps.MapStructs;

public class Waypoint {
    public Waypoint Next;
    public Point Point;

    public Waypoint() {}

    public Waypoint(Point p) {
        this.Point = p;
    }
}
