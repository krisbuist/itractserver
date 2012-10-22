package controllers;

import java.util.ArrayList;

import models.TripOffer;
import models.TripRequest;
import play.mvc.Controller;
import play.mvc.Result;

public class MatchController extends Controller {

    public static Result getMatch(Integer id) {
	ArrayList<TripOffer> trips = TripRequest.find.byId(id).getMatchingOffers();
	return ok(trips.toString());
    }

    public static Result updateMatch(Integer id) {
        return TODO;
    }

}
