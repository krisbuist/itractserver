package controllers;

/**
 * Created with IntelliJ IDEA.
 * UserController: erwin
 * Date: 22-10-12
 * Time: 14:08
 * To change this template use File | Settings | File Templates.
 */

import static play.libs.Json.toJson;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import models.TripRequest;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;

public class TripRequestController extends Controller {

    public static Result getTripRequests() {
	List<TripRequest> trips = TripRequest.find.where().le("id", 50).findList();

	return ok(toJson(trips));
    }

    public static Result newTripRequest() {

	TripRequest t = new TripRequest();
	Map<String, String[]> values = request().body().asFormUrlEncoded();

	try {
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
	} catch (NullPointerException e) {
	    return status(400, e.getMessage());
	} catch (NumberFormatException e) {
	    return status(400, e.getMessage());
	}
	return redirect(routes.MatchController.getMatch(t.getId()));
    }

    public static Result getTripRequest(Integer id) {
	TripRequest trip = TripRequest.find.byId(id);

	if (trip != null) {
	    return ok(toJson(trip));
	} else {
	    return notFound();
	}
    }

    public static Result updateTripRequest(Integer id) {
	TripRequest tripToUpdate = TripRequest.find.byId(id);
	return redirect(routes.TripRequestController.getTripRequest(tripToUpdate.getId()));
    }

    public static Result deleteTripRequest(Integer id) {
	TripRequest tripToDelete = TripRequest.find.byId(id);
	if (tripToDelete == null) {
	    return notFound();
	}
	tripToDelete.delete();
	return noContent();
    }

}
