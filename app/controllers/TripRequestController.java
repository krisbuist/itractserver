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

import models.TripRequest;
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
	    tripDetails.put("request_user", trip.getUser().getUserId());
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
	    tripDetails.put("request_numberOfSeats", trip.getNumberOfSeats());
	    tripDetails.put("request_state", "OPEN");
	    offers.add(tripDetails);
	}
	details.put("tripRequests", offers);
	return ok(toJson(details));
    }

    public static Result newTripRequest() {
        return TODO;
    }

    public static Result getTripRequest(Integer id) {
	TripRequest trip = TripRequest.find.byId(id);

	LinkedHashMap<String, Object> details = new LinkedHashMap<String, Object>();
	details.put("request_id", trip.getId());
	details.put("request_user", trip.getUser().getUserId());
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
	details.put("request_numberOfSeats", trip.getNumberOfSeats());
	details.put("request_state", "OPEN");

	return ok(toJson(details));
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
