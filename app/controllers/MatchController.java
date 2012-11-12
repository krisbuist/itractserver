package controllers;

import models.Notification;
import models.TripMatch;
import models.TripRequest;
import models.User;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import views.html.tripmatches;
import workers.GCMWorker;
import actions.BasicAuthAction;
import flexjson.JSONSerializer;

public class MatchController extends Controller {

    public static Result getMatch(Integer id) {
	TripMatch match = TripMatch.find.byId(id);

	if (match == null) {
	    return notFound();
	}

	response().setContentType("application/json");
	return ok(getSerializer().serialize(match));
    }

    public static Result displayMatches(Integer id) {
	TripRequest trip = TripRequest.find.byId(id);
	return ok(tripmatches.render(trip, trip.getMatches()));
    }

    private static JSONSerializer getSerializer() {
	return new JSONSerializer().exclude("tripOffer.matches", "tripRequest.matches").include("*");
    }

    @With(BasicAuthAction.class)
    @BodyParser.Of(play.mvc.BodyParser.Json.class)
    public static Result updateMatch(Integer id) {
	TripMatch match = TripMatch.find.byId(id);

	int newState = request().body().asJson().get("state").asInt();

	// TODO: Check if state change is allowed (e.g. change from 1 to 6, 6 to
	// 5, 2 to 6 etc. is not allowed)

	if (match.getState() != newState) {
	    match.setState(newState);
	    match.update();

	    Notification n = new Notification();
	    n.setTripMatch(match);
	    n.setUser(match.getTripOffer().getUser());
	    n.save();

	  

		String message = null;
		String title = null;

		String offerName = match.getTripOffer().getUser().getFirstName() + " " + match.getTripOffer().getUser().getLastName();
		String requestName = match.getTripRequest().getUser().getFirstName() + " " + match.getTripRequest().getUser().getLastName();

		User user = null;
		
		switch (match.getEnumState()) {
		case POTENTIAL:
			user = match.getTripOffer().getUser();
		    title = "New trip request";
		    message = "You have received an request from " + requestName;
		    break;
		case CONFIRMED_POTENTIAL:
			user = match.getTripRequest().getUser();
		    title = "Trip confirmed";
		    message = offerName + " accepted your request.";
		    break;
		case DECLINED_POTENTIAL:
			user = match.getTripRequest().getUser();
		    title = "Trip declined";
		    message = offerName + " declined your request.";
		    break;
		case CONFIRMED_MATCH:
			user = match.getTripOffer().getUser();
		    title = "Match confirmed";
		    message = requestName + " confirmed the match";
		    break;
		case DECLINED_MATCH:
			user = match.getTripOffer().getUser();
		    title = "Match declined";
		    message = requestName + " declined the match";
		default:
		    // keep message null
		    break;
		}

		
		if (user != null && user.getDeviceID() != null && message != null && title != null) {
			n.setUser(user);
		    GCMWorker.sendMessage(user.getDeviceID(), title, message, Integer.toString(match.getId()));
	    }
	}

	response().setContentType("application/json");
	return ok(getSerializer().serialize(match));
    }
}
