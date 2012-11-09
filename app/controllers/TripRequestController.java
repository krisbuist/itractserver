package controllers;

import java.util.List;

import models.TripRequest;
import models.User;
import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import actions.BasicAuthAction;
import flexjson.JSONSerializer;

public class TripRequestController extends Controller {

    private static User activeUser() {
	Object u = ctx().args.get("user");
	if (u != null && u instanceof User) {
	    return (User) u;
	}
	return null;
    }

    public static Result getTripRequests() {
	List<TripRequest> trips = TripRequest.find.where().le("id", 10000).findList();

	JSONSerializer serializer = new JSONSerializer().include("*").exclude("*");

	response().setContentType("application/json");
	return ok(serializer.serialize(trips));
    }

    @With(BasicAuthAction.class)
    @BodyParser.Of(play.mvc.BodyParser.Json.class)
    public static Result newTripRequest() {
	Form<TripRequest> tripRequestForm = form(TripRequest.class).bindFromRequest();
	if (tripRequestForm.hasErrors()) {
	    return badRequest();
	}

	TripRequest newTripRequest = tripRequestForm.get();

	newTripRequest.setId(0);
	newTripRequest.setUser(activeUser());

	newTripRequest.save();

	if (newTripRequest.getStartTimeMin() == 0 && newTripRequest.getStartTimeMax() == 0) {
	    newTripRequest.setStartTimeMin(newTripRequest.getEndTimeMin() - newTripRequest.getMetaData().getApproximateDuration());
	    newTripRequest.setStartTimeMax(newTripRequest.getStartTimeMin() + 60 * 60 * 2);
	} else {
	    newTripRequest.setEndTimeMin(newTripRequest.getStartTimeMin() + newTripRequest.getMetaData().getApproximateDuration());
	    newTripRequest.setEndTimeMax(newTripRequest.getEndTimeMin() + 60 * 60 * 2);    
	}
	newTripRequest.update();

	JSONSerializer serializer = new JSONSerializer().include("*").exclude("*");

	response().setContentType("application/json");
	response().setHeader("Access-Control-Allow-Headers", "Content-Type");
	response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	response().setHeader("Access-Control-Allow-Origin", "*");
	response().setHeader("Access-Control-Request-Headers", "origin, content-type, accept");
	response().setHeader("Access-Control-Max-Age", "60");
	return created(serializer.serialize(newTripRequest));
    }

    public static Result getTripRequest(Integer id) {
	TripRequest trip = TripRequest.find.byId(id);

	JSONSerializer serializer = new JSONSerializer().include("*").exclude("*");

	if (trip != null) {
	    response().setContentType("application/json");
	    response().setHeader("Access-Control-Allow-Headers", "Content-Type");
	    response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	    response().setHeader("Access-Control-Allow-Origin", "*");
	    response().setHeader("Access-Control-Request-Headers", "origin, content-type, accept");
	    response().setHeader("Access-Control-Max-Age", "60");
	    return ok(serializer.serialize(trip));
	} else {
	    return notFound();
	}
    }

    @With(BasicAuthAction.class)
    @BodyParser.Of(play.mvc.BodyParser.Json.class)
    public static Result updateTripRequest(Integer id) {
	TripRequest tripToUpdate = TripRequest.find.byId(id);

	if (tripToUpdate == null) {
	    return notFound();
	}

	if (tripToUpdate.getUser().getId() != activeUser().getId()) {
	    return unauthorized();
	}

	Form<TripRequest> tripRequestForm = form(TripRequest.class).bindFromRequest();
	if (tripRequestForm.hasErrors()) {
	    return badRequest();
	}

	TripRequest editedTripRequest = tripRequestForm.get();

	editedTripRequest.setId(tripToUpdate.getId());
	editedTripRequest.setUser(activeUser());
	editedTripRequest.update();

	JSONSerializer serializer = new JSONSerializer().include("*").exclude("*");
	response().setContentType("application/json");
	return ok(serializer.serialize(editedTripRequest));
    }

    @With(BasicAuthAction.class)
    public static Result deleteTripRequest(Integer id) {
	TripRequest tripToDelete = TripRequest.find.byId(id);

	if (tripToDelete == null) {
	    return notFound();
	}

	if (tripToDelete.getUser().getId() != activeUser().getId()) {
	    return unauthorized();
	}

	tripToDelete.delete();
	return noContent();

    }

    public static Result respondToOptions() {
	response().setHeader("Access-Control-Allow-Headers", "Content-Type");
	response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	response().setHeader("Access-Control-Allow-Origin", "*");
	response().setHeader("Access-Control-Request-Headers", "origin, content-type, accept");
	response().setHeader("Access-Control-Max-Age", "60");

	return ok();
    }

}
