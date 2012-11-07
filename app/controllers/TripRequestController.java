package controllers;

/**
 * Created with IntelliJ IDEA.
 * UserController: erwin
 * Date: 22-10-12
 * Time: 14:08
 * To change this template use File | Settings | File Templates.
 */

import java.util.List;

import models.TripRequest;
import play.mvc.Controller;
import play.mvc.Result;
import flexjson.JSONSerializer;

public class TripRequestController extends Controller {

    public static Result getTripRequests() {
	List<TripRequest> trips = TripRequest.find.where().le("id", 15).findList();

	JSONSerializer serializer = new JSONSerializer().include("*").exclude("*");

	response().setContentType("application/json");
	return ok(serializer.serialize(trips));
    }

    public static Result newTripRequest() {

	return TODO;
    }

    public static Result getTripRequest(Integer id) {
	TripRequest trip = TripRequest.find.byId(id);

	JSONSerializer serializer = new JSONSerializer().include("*").exclude("*");

	if (trip != null) {
	    response().setContentType("application/json");
	    return ok(serializer.serialize(trip));
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
