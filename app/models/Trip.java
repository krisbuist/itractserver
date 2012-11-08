package models;

import googleMapsDirections.Directions;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

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
    @Range(min=1, max=7)
    protected int numberOfSeats;
    @OneToOne
    protected TripMetaData metaData;

    protected String originAddress;
    protected String destinationAddress;

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

    public void setId(int id) {
	this.id = id;
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

    public void setOriginAddress(String originAddress) {
	this.originAddress = originAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
	this.destinationAddress = destinationAddress;
    }

    public TripMetaData getMetaData() {
	return metaData;
    }

    public void setMetaData(TripMetaData metaData) {
	this.metaData = metaData;
    }

    public String getOriginAddress() {
	return originAddress;
    }

    public String getDestinationAddress() {
	return destinationAddress;
    }

    public String getStartTimeWindow() {
	SimpleDateFormat format = new SimpleDateFormat("HH:mm");
	Date begin = new Date(getStartTimeMin() * 1000);
	Date end = new Date(getStartTimeMax() * 1000);
	return format.format(begin) + " - " + format.format(end);
    }

    public String getEndTimeWindow() {
	SimpleDateFormat format = new SimpleDateFormat("HH:mm");
	Date begin = new Date(getEndTimeMin() * 1000);
	Date end = new Date(getEndTimeMax() * 1000);
	return format.format(begin) + " - " + format.format(end);
    }

    @Override
    public void save() {
	try {
	    validate();
	} catch (ItractDataException e) {
	    return;
	}
	super.save();
    }

    private void validate() throws ItractDataException {
	Validator.validateLongitude(originLong);
	Validator.validateLatitude(originLat);
	Validator.validateLongitude(destinationLong);
	Validator.validateLatitude(destinationLat);
	Validator.validateNumberofSeats(numberOfSeats);
	this.calculateMetaData();
    }
    
    @Override
    public void update() {
	try {
	    validate();
	} catch (ItractDataException e) {
	    return;
	}
	super.update();
    }
    

    private void calculateMetaData() {
	metaData = new TripMetaData();
	Directions dir = new Directions();
	dir.addWaypoint(new Waypoint(getOriginLong(), getOriginLat(), getStartTimeMin(), getStartTimeMax()));
	dir.addWaypoint(new Waypoint(getDestinationLong(), getDestinationLat(), getEndTimeMin(), getEndTimeMax()));
	metaData.setCrowFliesDistance(dir.getTotalLinearDistance());
	metaData.setCalculatedDuration(dir.getCalculatedTravelTimeInSeconds());
	metaData.setDirectionsDistance(dir.getTotalDirectionDistance());
	metaData.save();
    }
}