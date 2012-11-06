package controllers;

import models.TripMatch;
import play.mvc.Controller;
import play.mvc.Result;
import flexjson.JSONSerializer;

public class MatchController extends Controller {

    public static Result getMatch(Integer id) {
	TripMatch match = TripMatch.find.byId(id);
	
	if (match == null) {
	    return notFound();
	}

	JSONSerializer serializer = new JSONSerializer().exclude("tripRequest.matches").include("*");
	
	response().setContentType("application/json");
	return ok(serializer.serialize(match));
    }

    public static Result updateMatch(Integer id) {
	return TODO;
    }

}
