package models;

public abstract class Trip {
    private int id;
    private int profileId;
    private double originLong;
    private double originLat;
    private int originWindow;
    private double destinationLong;
    private double destinationLat;
    private int destinationWindow;
    private long startTimeMin;
    private long startTimeMax;
    private long endTimeMin;
    private long endTimeMax;

    public long getStartTimeMin() {
        return startTimeMin;
    }

    public void setStartTimeMin(long startTimeMin) {
        this.startTimeMin = startTimeMin;
    }

    public long getStartTimeMax() {
        return startTimeMax;
    }

    public void setStartTimeMax(long startTimeMax) {
        this.startTimeMax = startTimeMax;
    }

    public long getEndTimeMin() {
        return endTimeMin;
    }

    public void setEndTimeMin(long endTimeMin) {
        this.endTimeMin = endTimeMin;
    }

    public long getEndTimeMax() {
        return endTimeMax;
    }

    public void setEndTimeMax(long endTimeMax) {
        this.endTimeMax = endTimeMax;
    }

    private int numberOfSeats;

    public Trip() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public double getOriginLong() {
        return originLong;
    }

    public void setOriginLong(double originLong) {
        this.originLong = originLong;
    }

    public double getOriginLat() {
        return originLat;
    }

    public void setOriginLat(double originLat) {
        this.originLat = originLat;
    }

    public double getDestinationLong() {
        return destinationLong;
    }

    public void setDestinationLong(double destinationLong) {
        this.destinationLong = destinationLong;
    }

    public double getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(double destinationLat) {
        this.destinationLat = destinationLat;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}