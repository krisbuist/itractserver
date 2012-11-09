package controllers;

import actions.BasicAuthAction;
import models.TripMatch;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
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

    @With(BasicAuthAction.class)
    @BodyParser.Of(play.mvc.BodyParser.Json.class)
    public static Result updateMatch(Integer id) {
	TripMatch match = TripMatch.find.byId(id);

	int newState = request().body().asJson().get("state").asInt();

	match.setState(newState);

	
	JSONSerializer serializer = new JSONSerializer().exclude("tripRequest.matches", "tripOffer.matches").include("*");

	response().setContentType("application/json");
	return ok(serializer.serialize(match));
    }

}
