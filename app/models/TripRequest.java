package models;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class TripRequest extends Trip {

    private static final double searchSensitvity = 0.05;

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
                .between("origin_long", getOriginLong() - searchSensitvity, getOriginLong() + searchSensitvity)
                .between("origin_lat", getOriginLat() - searchSensitvity, getOriginLat() + searchSensitvity)
                .between("destination_lat", getDestinationLat() - searchSensitvity, getDestinationLat() + searchSensitvity)
                .between("destination_long", getDestinationLong() - searchSensitvity, getDestinationLong() + searchSensitvity)
                .le("start_time_min", getStartTimeMax())
                .ge("start_time_max", getEndTimeMin())
                .ge("number_of_seats", getNumberOfSeats())
                .findList();
    }

}