package controllers;

/**
 * Created with IntelliJ IDEA.
 * UserController: erwin
 * Date: 22-10-12
 * Time: 14:08
 * To change this template use File | Settings | File Templates.
 */

import static play.libs.Json.toJson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import models.TripRequest;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class TripRequestController extends Controller {

    public static Result getTripRequests() {
	List<TripRequest> trips = TripRequest.find.where().le("id", 50).findList();
	LinkedHashMap<String, Object> details = new LinkedHashMap<String, Object>();
	ArrayList<LinkedHashMap<String, Object>> offers = new ArrayList<LinkedHashMap<String, Object>>();
	for (TripRequest trip : trips) {
	    LinkedHashMap<String, Object> tripDetails = new LinkedHashMap<String, Object>();
	    tripDetails.put("request_id", trip.getId());
	    tripDetails.put("request_user", trip.getUser().getId());
	    tripDetails.put("request_origin_long", trip.getOriginLong());
	    tripDetails.put("request_origin_lat", trip.getOriginLat());
	    tripDetails.put("request_origin_window", 0);
	    tripDetails.put("request_destination_long", trip.getDestinationLong());
	    tripDetails.put("request_destination_lat", trip.getDestinationLat());
	    tripDetails.put("request_destination_window", 0);
	    tripDetails.put("request_start_time_min", trip.getStartTimeMin());
	    tripDetails.put("request_start_time_max", trip.getStartTimeMax());
	    tripDetails.put("request_end_time_min", trip.getEndTimeMin());
	    tripDetails.put("request_end_time_max", trip.getEndTimeMax());
	    tripDetails.put("request_number_of_seats", trip.getNumberOfSeats());
	    tripDetails.put("request_state", "OPEN");
	    offers.add(tripDetails);
	}
	details.put("tripRequests", offers);
	return ok(toJson(details));
    }

    public static Result newTripRequest() {

	TripRequest t = new TripRequest();
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
	return redirect(routes.MatchController.getMatch(t.getId()));
    }

    public static Result getTripRequest(Integer id) {
	TripRequest trip = TripRequest.find.byId(id);

	if (trip != null) {
	    LinkedHashMap<String, Object> details = new LinkedHashMap<String, Object>();
	    details.put("request_id", trip.getId());
	    details.put("request_user", trip.getUser().getId());
	    details.put("request_origin_long", trip.getOriginLong());
	    details.put("request_origin_lat", trip.getOriginLat());
	    details.put("request_origin_window", 0);
	    details.put("request_destination_long", trip.getDestinationLong());
	    details.put("request_destination_lat", trip.getDestinationLat());
	    details.put("request_destination_window", 0);
	    details.put("request_start_time_min", trip.getStartTimeMin());
	    details.put("request_start_time_max", trip.getStartTimeMax());
	    details.put("request_end_time_min", trip.getEndTimeMin());
	    details.put("request_end_time_max", trip.getEndTimeMax());
	    details.put("request_number_of_seats", trip.getNumberOfSeats());
	    details.put("request_state", "OPEN");

	    return ok(toJson(details));
	} else {
	    return notFound();
	}
    }

    public static Result updateTripRequest(Integer id) {
	TripRequest trip = TripRequest.find.byId(id);
	return redirect(routes.TripRequestController.getTripRequest(trip.getId()));
    }

    public static Result deleteTripRequest(Integer id) {
	TripRequest.find.byId(id).delete();
	return redirect(routes.TripRequestController.getTripRequests());
    }

}
