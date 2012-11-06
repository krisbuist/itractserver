package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import workers.StatisticsGenerator;


@Entity
public class TripMetaData extends Model{

    /**
     *
     */
    private static final long serialVersionUID = 3091163212158817944L;
    public static Finder<Integer, TripMetaData> find = new Finder<Integer, TripMetaData>(Integer.class, TripMetaData.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Constraints.Required
    private long crowFliesDistance;

    private long calculatedDuration;
    private long directionsDistance;

    public long getApproximateDuration() {
        return (long)(getApproximateDirectionsDistance() / StatisticsGenerator.getDistanceToTravelTimeRatio(crowFliesDistance));
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

    public long getApproximateDirectionsDistance()
    {
        return (long)(crowFliesDistance * StatisticsGenerator.getCrowFlyDistanceOverhead(crowFliesDistance));
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

    public boolean hasResultsFromAPI() {
        return calculatedDuration != 0 && directionsDistance != 0;
    }

}