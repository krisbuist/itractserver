package models;

import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class TripRequest extends Trip {

    /**
     * 
     */
    private static final long serialVersionUID = -7829362116641187593L;
    public static Finder<Integer, TripRequest> find = new Finder<Integer, TripRequest>(Integer.class, TripRequest.class);

    public TripRequest() {
	super();
    }

    public ArrayList<TripOffer> getMatchingOffers() {
	return new ArrayList<TripOffer>();
    }

}