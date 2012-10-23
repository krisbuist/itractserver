package models;

import java.util.List;

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

    public List<TripOffer> getMatchingOffers() {
	return TripOffer.find.where()
		.eq("origin_long", getOriginLong())
		.eq("origin_lat", getOriginLat())
		.eq("destination_lat", getDestinationLat())
		.eq("destination_long", getDestinationLong())
		.findList();
    }

}