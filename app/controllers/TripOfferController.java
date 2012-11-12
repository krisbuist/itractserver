package controllers;

import java.util.List;

import models.TripOffer;
import models.User;
import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import actions.BasicAuthAction;
import flexjson.JSONSerializer;

public class TripOfferController extends Controller {

    private static User activeUser() {
        Object u = ctx().args.get("user");
        if (u != null && u instanceof User) {
            return (User) u;
        }
        return null;
    }
    
    private static JSONSerializer getSerializer()
    {
	return new JSONSerializer().exclude("matches.tripOffer.matches", "matches.tripRequest.matches").include("*");
    }

    public static Result getTripOffers() {
        List<TripOffer> trips = TripOffer.find.where().le("id", 15).findList();
	        response().setContentType("application/json");
        response().setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
	response().setHeader("Access-Control-Allow-Credentials", "true");
	response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	response().setHeader("Access-Control-Allow-Origin", "*");
	response().setHeader("Access-Control-Request-Headers", "origin, content-type, accept");
	response().setHeader("Access-Control-Max-Age", "60");
        response().setContentType("application/json");
        return ok(getSerializer().serialize(trips));
    }

    public static Result getTripOffer(Integer id) {
        TripOffer trip = TripOffer.find.byId(id);

        if (trip != null) {
                response().setContentType("application/json");
	        response().setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
		response().setHeader("Access-Control-Allow-Credentials", "true");
		response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		response().setHeader("Access-Control-Allow-Origin", "*");
		response().setHeader("Access-Control-Request-Headers", "origin, content-type, accept");
		response().setHeader("Access-Control-Max-Age", "60");
            return ok(getSerializer().serialize(trip));
        } else {
            return notFound();
        }
    }

    @With(BasicAuthAction.class)
    @BodyParser.Of(play.mvc.BodyParser.Json.class)
    public static Result newTripOffer() {
        Form<TripOffer> tripOfferForm = form(TripOffer.class).bindFromRequest();
        if (tripOfferForm.hasErrors()) {
            return badRequest();
        }

        TripOffer newTripOffer = tripOfferForm.get();

        newTripOffer.setId(0);
        newTripOffer.setUser(activeUser());
        newTripOffer.save();

        response().setContentType("application/json");
	        response().setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
		response().setHeader("Access-Control-Allow-Credentials", "true");
		response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		response().setHeader("Access-Control-Allow-Origin", "*");
		response().setHeader("Access-Control-Request-Headers", "origin, content-type, accept");
		response().setHeader("Access-Control-Max-Age", "60");
        return created(getSerializer().serialize(newTripOffer));
    }

    @With(BasicAuthAction.class)
    @BodyParser.Of(play.mvc.BodyParser.Json.class)
    public static Result updateTripOffer(Integer id) {
        TripOffer tripToUpdate = TripOffer.find.byId(id);

        if (tripToUpdate == null) {
            return notFound();
        }

        if (tripToUpdate.getUser().getId() != activeUser().getId()) {
            return unauthorized();
        }

        Form<TripOffer> tripOfferForm = form(TripOffer.class).bindFromRequest();
        if (tripOfferForm.hasErrors()) {
            return badRequest();
        }
	        response().setContentType("application/json");
        response().setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
	response().setHeader("Access-Control-Allow-Credentials", "true");
	response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	response().setHeader("Access-Control-Allow-Origin", "*");
	response().setHeader("Access-Control-Request-Headers", "origin, content-type, accept");
	response().setHeader("Access-Control-Max-Age", "60");
        TripOffer editedTripOffer = tripOfferForm.get();

        editedTripOffer.setId(tripToUpdate.getId());
        editedTripOffer.setUser(activeUser());
        editedTripOffer.update();

        response().setContentType("application/json");
        return ok(getSerializer().serialize(editedTripOffer));
    }

    @With(BasicAuthAction.class)
    public static Result deleteTripOffer(Integer id) {
        TripOffer toDelete = TripOffer.find.byId(id);

        if (toDelete == null) {
            return notFound();
        }

	        response().setContentType("application/json");
        response().setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
	response().setHeader("Access-Control-Allow-Credentials", "true");
	response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	response().setHeader("Access-Control-Allow-Origin", "*");
	response().setHeader("Access-Control-Request-Headers", "origin, content-type, accept");
	response().setHeader("Access-Control-Max-Age", "60");
        if (toDelete.getUser().getId() != activeUser().getId()) {
            return unauthorized();
        }

        toDelete.delete();
        return noContent();
    }
    public static Result respondToOptionsWithId(int id) {
                response().setContentType("application/json");
        response().setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
	response().setHeader("Access-Control-Allow-Credentials", "true");
	response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	response().setHeader("Access-Control-Allow-Origin", "*");
	response().setHeader("Access-Control-Request-Headers", "origin, content-type, accept");
	response().setHeader("Access-Control-Max-Age", "60");
        return ok();
    }
    public static Result respondToOptions() {
                response().setContentType("application/json");
        response().setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
	response().setHeader("Access-Control-Allow-Credentials", "true");
	response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	response().setHeader("Access-Control-Allow-Origin", "*");
	response().setHeader("Access-Control-Request-Headers", "origin, content-type, accept");
	response().setHeader("Access-Control-Max-Age", "60");
        return ok();
    }
}
