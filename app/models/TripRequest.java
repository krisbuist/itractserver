package models;

import googleMapsDirections.Directions;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class TripRequest extends Trip {

    private static final double tripOverhead = 1.1;

    /**
     *
     */
    private static final long serialVersionUID = -7829362116641187593L;
    public static Finder<Integer, TripRequest> find = new Finder<Integer, TripRequest>(Integer.class, TripRequest.class);

    public TripRequest() {
        super();
    }

    public List<TripOffer> getMatchingOffers() {

        List<TripOffer> matches = new ArrayList<TripOffer>();

        // Reduce to matching offers in time window
        List<TripOffer> matchesInTimeWindow = TripOffer.find.where()
                .le("start_time_min", getStartTimeMax())
                .ge("start_time_max", getStartTimeMin())
                .ge("end_time_max", getEndTimeMin())
                .le("end_time_min", getEndTimeMax())
                .ge("number_of_seats", getNumberOfSeats())
                .findList();

        // Reduce matching offer to matching offers in boundary box and travelDistance
        Directions d;
        for (TripOffer t : matchesInTimeWindow) {
            d = new Directions();
            d.addRoutePoint(new Location(t.getOriginLong(), t.getOriginLat()));
            d.addRoutePoint(new Location(t.getDestinationLong(), t.getDestinationLat()));

            if (isBetweenBounds(d.getNorthWestBounds(), d.getSouthEastBounds()) && isPossibleMatchOnTravelDistance(t)) {
                matches.add(t);
            }
        }
        return matches;
    }

    private boolean isPossibleMatchOnTravelDistance(TripOffer t) {
        Location offerOrigin = new Location(t.getOriginLong(), t.getOriginLat());
        Location offerDestination = new Location(t.getDestinationLong(), t.getDestinationLat());
        Location requestOrigin = new Location(getOriginLong(), getOriginLat());
        Location requestDestination = new Location(getDestinationLong(), getDestinationLat());

        Directions originalOffer = new Directions();
        originalOffer.addRoutePoint(offerOrigin);
        originalOffer.addRoutePoint(offerDestination);

        Directions offerIncludingRequest = new Directions();
        offerIncludingRequest.addRoutePoint(offerOrigin);
        offerIncludingRequest.addRoutePoint(requestOrigin);
        offerIncludingRequest.addRoutePoint(requestDestination);
        offerIncludingRequest.addRoutePoint(offerDestination);

        if (originalOffer.getApproximateRouteDistance() * tripOverhead >= offerIncludingRequest.getApproximateRouteDistance()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isBetweenBounds(Location northWestBounds, Location southEastBounds) {
        boolean inBoundaries;

        inBoundaries = (northWestBounds.getLongitude() >= getOriginLong());
        inBoundaries = inBoundaries && (northWestBounds.getLongitude() >= getDestinationLong());
        inBoundaries = inBoundaries && (northWestBounds.getLatitude() <= getOriginLat());
        inBoundaries = inBoundaries && (northWestBounds.getLatitude() <= getDestinationLat());
        inBoundaries = inBoundaries && (southEastBounds.getLongitude() <= getOriginLong());
        inBoundaries = inBoundaries && (southEastBounds.getLongitude() <= getDestinationLong());
        inBoundaries = inBoundaries && (southEastBounds.getLatitude() >= getOriginLat());
        inBoundaries = inBoundaries && (southEastBounds.getLatitude() >= getDestinationLat());

        return inBoundaries;
    }

}