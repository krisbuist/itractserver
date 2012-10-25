package controllers;

/**
 * Created with IntelliJ IDEA.
 * UserController: erwin
 * Date: 22-10-12
 * Time: 14:08
 * To change this template use File | Settings | File Templates.
 */

import static play.libs.Json.toJson;

import java.util.List;

import models.TripRequest;
import play.mvc.*;

public class TripRequestController extends Controller {

    public static Result getTripRequests() {
    	List<TripRequest> trips = TripRequest.find.all();

        return ok(toJson(trips));
    }

    public static Result setTripRequest() {
    	List<TripRequest> trips = TripRequest.find.all();

        return ok(toJson(trips));
    }

    public static Result getTripRequest(Integer id) {
    	TripRequest trip = TripRequest.find.byId(id);

        return ok(toJson(trip));
    }

    public static Result updateTripRequest(Integer id) {
    	TripRequest trip = TripRequest.find.byId(id);
        trip.save();
        return ok(toJson(trip));
    }

}
