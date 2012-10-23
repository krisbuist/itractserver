package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.db.ebean.Model;


@Entity
public class TripMetaData extends Model{

    /**
     * 
     */
    private static final long serialVersionUID = 3091163212158817944L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    
    @Constraints.Required
    protected long approximateDuration;
    @Constraints.Required
    protected long crowFliesDistance;

    protected long calculatedDuration;
    protected long directionsDistance;

    public long getApproximateDuration() {
	return approximateDuration;
    }

    public void setApproximateDuration(long approximateDuration) {
	this.approximateDuration = approximateDuration;
    }

    public long getCalculatedDuration() {
	return calculatedDuration;
    }

    public void setCalculatedDuration(long calculatedDuration) {
	this.calculatedDuration = calculatedDuration;
    }

    public long getCrowFliesDistance() {
	return crowFliesDistance;
    }

    public void setCrowFliesDistance(long crowFliesDistance) {
	this.crowFliesDistance = crowFliesDistance;
    }

    public long getDirectionsDistance() {
	return directionsDistance;
    }

    public void setDirectionsDistance(long directionsDistance) {
	this.directionsDistance = directionsDistance;
    }

    public long getId() {
	return id;
    }
    
}
