package models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import validators.ItractDataException;
import validators.Validator;

import com.avaje.ebean.validation.Range;

@MappedSuperclass
public abstract class Trip extends Model {
    private static final long serialVersionUID = 4984810188329781545L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @ManyToOne
    protected User user;
    @Required
    protected double originLong;
    @Required
    protected double originLat;
    @Required
    protected double destinationLong;
    @Required
    protected double destinationLat;
    @Required
    protected long startTimeMin;
    @Required
    protected long startTimeMax;
    @Required
    protected long endTimeMin;
    @Required
    protected long endTimeMax;
    @Required
    @Range(min = 1, max = 7)
    // inclusive
    private int numberOfSeats;

    public Trip() {
    }

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

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public void save() {
	try {
	    Validator.validateLongitude(originLong);
	    Validator.validateLatitude(originLat);
	    Validator.validateLongitude(destinationLong);
	    Validator.validateLatitude(destinationLat);
	    Validator.validateNumberofSeats(numberOfSeats);
	} catch (ItractDataException e) {
	    return;
	}
	super.save();
    }
}