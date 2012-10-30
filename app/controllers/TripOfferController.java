package controllers;

import static play.libs.Json.toJson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import models.TripOffer;
import models.TripRequest;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;

public class TripOfferController extends Controller {

    public static Result getTripOffers() {
	List<TripOffer> trips = TripOffer.find.where().le("id", 50).findList();
	LinkedHashMap<String, Object> details = new LinkedHashMap<String, Object>();
	ArrayList<LinkedHashMap<String, Object>> offers = new ArrayList<LinkedHashMap<String, Object>>();
	for (TripOffer trip : trips) {
	    LinkedHashMap<String, Object> tripDetails = new LinkedHashMap<String, Object>();
	    tripDetails.put("offer_id", trip.getId());
	    tripDetails.put("offer_user", trip.getUser().getId());
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
	    tripDetails.put("offer_number_of_seats", trip.getNumberOfSeats());
	    tripDetails.put("offer_state", "OPEN");
	    offers.add(tripDetails);
	}
	details.put("tripOffers", offers);
	return ok(toJson(details));
    }

    public static Result getTripOffer(Integer id) {
	TripOffer trip = TripOffer.find.byId(id);

	if (trip != null) {
	    LinkedHashMap<String, Object> details = new LinkedHashMap<String, Object>();
	    details.put("offer_id", trip.getId());
	    details.put("offer_user", trip.getUser().getId());
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
	    details.put("offer_number_of_seats", trip.getNumberOfSeats());
	    details.put("offer_state", "OPEN");

	    return ok(toJson(details));
	} else {
	    return notFound();
	}
    }

    public static Result newTripOffer() {

	TripOffer t = new TripOffer();
	Map<String, String[]> values = request().body().asFormUrlEncoded();

	try{
	    t.setOriginLong(Double.parseDouble(values.get("origin_long")[0]));
	    t.setOriginLat(Double.parseDouble(values.get("origin_lat")[0]));
	    t.setOriginAddress("Origin");
	    t.setDestinationLong(Double.parseDouble(values.get("destination_long")[0]));
	    t.setDestinationLat(Double.parseDouble(values.get("destination_lat")[0]));
	    t.setDestinationAddress("Destination");
	    t.setStartTimeMin(Long.parseLong(values.get("start_time_min")[0]));
	    t.setStartTimeMax(Long.parseLong(values.get("start_time_max")[0]));
	    t.setEndTimeMin(Long.parseLong(values.get("end_time_min")[0]));
	    t.setEndTimeMax(Long.parseLong(values.get("end_time_max")[0]));
	    t.setNumberOfSeats(Integer.parseInt(values.get("number_of_seats")[0]));
	    t.setUser(User.find.byId(Integer.parseInt(values.get("user")[0])));
	    t.save();
	}
	catch (NullPointerException e)
	{
	    return status(400, e.getMessage());
	}
	catch (NumberFormatException e)
	{
	    return status(400, e.getMessage());
	}
	
	return getTripOffer(t.getId());
    }

    public static Result deleteTripOffer(Integer id) {
	TripOffer.find.byId(id).delete();
	return redirect(routes.TripOfferController.getTripOffers());
    }

    public static Result updateTripOffer(Integer id) {
	TripOffer trip = TripOffer.find.byId(id);
	trip.save();
	return ok(toJson(trip));
    }
}