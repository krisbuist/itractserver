package models;

/**
 * Created with IntelliJ IDEA.
 * User: jurrestender
 * Date: 10/25/12
 * Time: 12:05 PM
 */
public class Waypoint {
    private Location location;
    private long minimumArrivalTime;
    private long maximumDepartureTime;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public long getMinimumArrivalTime() {
        return minimumArrivalTime;
    }

    public void setMinimumArrivalTime(long minimumArrivalTime) {
        this.minimumArrivalTime = minimumArrivalTime;
    }

    public long getMaximumDepartureTime() {
        return maximumDepartureTime;
    }

    public void setMaximumDepartureTime(long maximumDepartureTime) {
        this.maximumDepartureTime = maximumDepartureTime;
    }
}
