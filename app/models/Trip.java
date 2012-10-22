package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

public abstract class Trip extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7138432153074898081L;

	@Id
    protected int id;
	
	@Required
    protected int profileId;
	
	@Required
    protected double originLong;
    protected double originLat;
    protected double destinationLong;
    protected double destinationLat;
    protected long startTimeMin;
    protected long startTimeMax;
    protected long endTimeMin;
    protected long endTimeMax;
    protected int numberOfSeats;

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
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Finder<Long, Trip> find = new Finder(Long.class, Trip.class);

	public static List<Trip> all() {
		return find.all();
	}

	public static void create(TripOffer offer) {
		offer.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}
}