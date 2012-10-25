package models;

/**
 * Created with IntelliJ IDEA.
 * User: jurrestender
 * Date: 10/25/12
 * Time: 12:05 PM
 */

//A waypoint represents a single node in a trip
public class Waypoint extends Location {
    private long minimumArrivalTime;
    private long maximumDepartureTime;

    public Waypoint(double longitude, double latitude, String address, long minimumArrivalTime, long maximumDepartureTime) {
        super(longitude, latitude, address);
        this.minimumArrivalTime = minimumArrivalTime;
        this.maximumDepartureTime = maximumDepartureTime;

    }

    public Waypoint(double longitude, double latitude, long minimumArrivalTime, long maximumDepartureTime) {
        super(longitude, latitude);
        this.minimumArrivalTime = minimumArrivalTime;
        this.maximumDepartureTime = maximumDepartureTime;
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
