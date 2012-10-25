package controllers;

import static play.libs.Json.toJson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import models.TripOffer;
import play.mvc.Controller;
import play.mvc.Result;

public class TripOfferController extends Controller {

    // TODO: Valid specification JSON implementation
    // (http://www.playframework.org/documentation/2.0/JavaJsonRequests)

    public static Result getTripOffers() {
	List<TripOffer> trips = TripOffer.find.where().le("id", 50).findList();
	LinkedHashMap<String, Object> details = new LinkedHashMap<String, Object>();
	ArrayList<LinkedHashMap<String, Object>> offers = new ArrayList<LinkedHashMap<String, Object>>();
	for (TripOffer trip : trips) {
	    LinkedHashMap<String, Object> tripDetails = new LinkedHashMap<String, Object>();
	    tripDetails.put("offer_id", trip.getId());
	    tripDetails.put("offer_user", trip.getUser().getUserId());
	    tripDetails.put("offer_origin_long", trip.getOriginLong());
	    tripDetails.put("offer_origin_lat", trip.getOriginLat());
	    tripDetails.put("offer_origin_window", 0);
	    tripDetails.put("offer_destination_long", trip.getDestinationLong());
	    tripDetails.put("offer_destination_lat", trip.getDestinationLat());
	    tripDetails.put("offer_destination_window", 0);
	    tripDetails.put("offer_start_time_min", trip.getStartTimeMin());
	    tripDetails.put("offer_start_time_max", trip.getStartTimeMax());
	    tripDetails.put("offer_end_time_min", trip.getEndTimeMin());
	    tripDetails.put("offer_end_time_max", trip.getEndTimeMax());
	    tripDetails.put("offer_numberOfSeats", trip.getNumberOfSeats());
	    tripDetails.put("offer_state", "OPEN");
	    offers.add(tripDetails);
	}
	details.put("tripOffers", offers);
	return ok(toJson(details));
    }

    public static Result getTripOffer(Integer id) {
	TripOffer trip = TripOffer.find.byId(id);

	LinkedHashMap<String, Object> details = new LinkedHashMap<String, Object>();
	details.put("offer_id", trip.getId());
	details.put("offer_user", trip.getUser().getUserId());
	details.put("offer_origin_long", trip.getOriginLong());
	details.put("offer_origin_lat", trip.getOriginLat());
	details.put("offer_origin_window", 0);
	details.put("offer_destination_long", trip.getDestinationLong());
	details.put("offer_destination_lat", trip.getDestinationLat());
	details.put("offer_destination_window", 0);
	details.put("offer_start_time_min", trip.getStartTimeMin());
	details.put("offer_start_time_max", trip.getStartTimeMax());
	details.put("offer_end_time_min", trip.getEndTimeMin());
	details.put("offer_end_time_max", trip.getEndTimeMax());
	details.put("offer_numberOfSeats", trip.getNumberOfSeats());
	details.put("offer_state", "OPEN");

	return ok(toJson(details));
    }

    public static Result setTripOffer() {
	List<TripOffer> trips = TripOffer.find.all();

	return ok(toJson(trips));
    }

    public static Result deleteTripOffer(Integer id) {
	return TODO;
    }

    public static Result updateTripOffer(Integer id) {
	TripOffer trip = TripOffer.find.byId(id);
	trip.save();
	return ok(toJson(trip));
    }
}
