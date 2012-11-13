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

    private static JSONSerializer getSerializer() {
        return new JSONSerializer().exclude("tripOffer.matches", "tripRequest.matches", "*.password").include("*");
    }

    @With(BasicAuthAction.class)
    public static Result getMatch(Integer id) {
        TripMatch match = TripMatch.find.byId(id);

        if (match == null) {
            return notFound();
        }

        response().setContentType("application/json");
        response().setHeader("Access-Control-Allow-Headers", "Content-Type");
        response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        response().setHeader("Access-Control-Allow-Origin", "*");
        response().setHeader("Access-Control-Request-Headers", "origin, content-type, accept");
        response().setHeader("Access-Control-Max-Age", "60");

        return ok(getSerializer().serialize(match));
    }

    public static Result displayMatches(Integer id) {
        TripRequest trip = TripRequest.find.byId(id);
        if (trip == null) {
            return notFound();
        } else {
            return ok(tripmatches.render(trip, trip.getMatches()));
        }
    }

    @With(BasicAuthAction.class)
    @BodyParser.Of(play.mvc.BodyParser.Json.class)
    public static Result updateMatch(Integer id) {
        TripMatch match = TripMatch.find.byId(id);

        int newState = request().body().asJson().get("state").asInt();

        if (match.getState() != newState) {
            match.setState(newState);
            match.update();

            Notification n = new Notification();
            n.setTripMatch(match);
            n.setState(newState);

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

            n.setUser(user);
            n.setTitle(title);
            n.setMessage(message);
            n.save();

            if (user != null && user.getDeviceID() != null && message != null && title != null) {
                GCMWorker.sendMessage(user.getDeviceID(), String.valueOf(n.getId()), title, message, Integer.toString(match.getId()));
            }
        }

        JSONSerializer serializer = new JSONSerializer().exclude("tripRequest.matches", "tripOffer.matches", "*.password").include("*");

        response().setContentType("application/json");
        response().setHeader("Access-Control-Allow-Headers", "Content-Type");
        response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        response().setHeader("Access-Control-Allow-Origin", "*");
        response().setHeader("Access-Control-Request-Headers", "origin, content-type, accept");
        response().setHeader("Access-Control-Max-Age", "60");
        return ok(serializer.serialize(match));
    }

}
