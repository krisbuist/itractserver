package controllers;

import static play.libs.Json.toJson;

import java.util.List;

import models.TripOffer;
import models.TripRequest;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.tripmatches;

public class MatchController extends Controller {

    public static Result getMatch(Integer id) {
	List<TripOffer> trips = TripRequest.find.byId(id).getMatchingOffers();
	return ok(tripmatches.render(TripRequest.find.byId(id), trips));
	//return ok(toJson(trips));
    }

    public static Result updateMatch(Integer id) {
        return TODO;
    }

}
