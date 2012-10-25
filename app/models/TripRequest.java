package models;

import googleMapsDirections.Directions;
import play.Logger;

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

	List<TripOffer> matchesInTimeWindow = TripOffer.find.where().le("start_time_min", getStartTimeMax()).ge("start_time_max", getStartTimeMin())
		.ge("end_time_max", getEndTimeMin()).le("end_time_min", getEndTimeMax()).ge("number_of_seats", getNumberOfSeats()).findList();

	Directions originalDirections, directionsIncludingRequest;

	for (TripOffer matchingOffer : matchesInTimeWindow) {

	    Waypoint offerOrigin = new Waypoint(matchingOffer.getOriginLong(), matchingOffer.getOriginLat(), matchingOffer.getStartTimeMin(),
		    matchingOffer.getStartTimeMax());
	    Waypoint offerDestination = new Waypoint(matchingOffer.getDestinationLong(), matchingOffer.getDestinationLat(),
		    matchingOffer.getEndTimeMin(), matchingOffer.getEndTimeMax());
	    Waypoint requestOrigin = new Waypoint(getOriginLong(), getOriginLat(), getStartTimeMin(), getStartTimeMax());
	    Waypoint requestDestination = new Waypoint(getDestinationLong(), getDestinationLat(), getEndTimeMin(), getEndTimeMax());

	    originalDirections = new Directions();
	    originalDirections.addWaypoint(offerOrigin);
	    originalDirections.addWaypoint(offerDestination);

	    directionsIncludingRequest = new Directions();
	    directionsIncludingRequest.addWaypoint(offerOrigin);
	    directionsIncludingRequest.addWaypoint(requestOrigin);
	    directionsIncludingRequest.addWaypoint(requestDestination);
	    directionsIncludingRequest.addWaypoint(offerDestination);

	    if (this.isBetweenBounds(originalDirections.getNorthWestBounds(), originalDirections.getSouthEastBounds())
		    && isPossibleMatchOnTravelTime(directionsIncludingRequest)
		    && isPossibleMatchOnTravelDistance(matchingOffer, originalDirections, directionsIncludingRequest)) {
		matches.add(matchingOffer);
	    }
	}
	return matches;
    }

    private boolean isPossibleMatchOnTravelTime(Directions directions) {
	return directions.isValidForWaypointTimeConstraints();
    }

    private boolean isPossibleMatchOnTravelDistance(TripOffer tripOffer, Directions originalDirections, Directions newDirections) {

	if (originalDirections.getApproximateRouteDistance() * tripOverhead >= newDirections.getApproximateRouteDistance()) {

	    newDirections.retrieveGoogleAPICalculations();

	    long offerIncludingRequestDistance = newDirections.getTotalDirectionDistance();

	    if (!tripOffer.getMetaData().hasResultsFromAPI()) {
		originalDirections.retrieveGoogleAPICalculations();

		tripOffer.getMetaData().setCalculatedDuration(originalDirections.getCalculatedTravelTimeInSeconds());
		tripOffer.getMetaData().setDirectionsDistance(originalDirections.getTotalDirectionDistance());
		tripOffer.getMetaData().save();
		// Sleep is for not sending too many requests to the Google API
		// per second
		try {
		    Thread.sleep(150);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }

	    // Sleep is for not sending too many requests to the Google API
	    // per second
	    try {
		Thread.sleep(150);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	    long originalDistance = tripOffer.getMetaData().getDirectionsDistance();

	    if (originalDistance == 0 || offerIncludingRequestDistance == 0) {
		Logger.error("Request rejected by Google API");
	    } else {
		if ((originalDistance * tripOverhead) >= offerIncludingRequestDistance) {
		    return true;
		}
	    }
	}
	return false;
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