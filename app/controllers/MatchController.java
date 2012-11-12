package controllers;

import actions.BasicAuthAction;
import models.Notification;
import models.TripMatch;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import flexjson.JSONSerializer;

public class MatchController extends Controller {

    @With(BasicAuthAction.class)
    public static Result getMatch(Integer id) {
	TripMatch match = TripMatch.find.byId(id);

        if (match == null) {
            return notFound();
        }

        JSONSerializer serializer = new JSONSerializer().exclude("tripRequest.matches").include("*");

        response().setContentType("application/json");
        response().setHeader("Access-Control-Allow-Headers", "Content-Type");
        response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        response().setHeader("Access-Control-Allow-Origin", "*");
        response().setHeader("Access-Control-Request-Headers", "origin, content-type, accept");
        response().setHeader("Access-Control-Max-Age", "60");
        return ok(serializer.serialize(match));
    }

    @With(BasicAuthAction.class)
    @BodyParser.Of(play.mvc.BodyParser.Json.class)
    public static Result updateMatch(Integer id) {
        TripMatch match = TripMatch.find.byId(id);

        int newState = request().body().asJson().get("state").asInt();

        Notification notification = new Notification();
        notification.setTripMatch(match);
        notification.setState(newState);

        if(newState == 1 || newState == 4 || newState == 5)
            notification.setUser(match.getTripOffer().getUser());
        else
            notification.setUser(match.getTripRequest().getUser());

        match.setState(newState);

        notification.save();
        match.update();


        JSONSerializer serializer = new JSONSerializer().exclude("tripRequest.matches", "tripOffer.matches").include("*");

        response().setContentType("application/json");
        response().setHeader("Access-Control-Allow-Headers", "Content-Type");
        response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        response().setHeader("Access-Control-Allow-Origin", "*");
        response().setHeader("Access-Control-Request-Headers", "origin, content-type, accept");
        response().setHeader("Access-Control-Max-Age", "60");
        return ok(serializer.serialize(match));
    }

}
