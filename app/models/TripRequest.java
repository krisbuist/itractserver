package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class TripRequest extends Trip {

    /**
     * 
     */
    private static final long serialVersionUID = -7829362116641187593L;

    public TripRequest() {
	super();
    }

    public List<TripOffer> getMatchingOffers() {
	return new ArrayList<TripOffer>();
    }

}