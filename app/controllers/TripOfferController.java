package controllers;

import static play.libs.Json.toJson;

import java.util.List;

import models.TripOffer;
import play.mvc.Controller;
import play.mvc.Result;

public class TripOfferController extends Controller {

    //TODO: Valid specification JSON implementation (http://www.playframework.org/documentation/2.0/JavaJsonRequests)

    public static Result getTripOffers() {
        List<TripOffer> trips = TripOffer.find.all();

        return ok(toJson(trips));
    }

    public static Result getTripOffer(Integer id) {
        TripOffer trip = TripOffer.find.byId(id);

        return ok(toJson(trip));
    }

    public static Result setTripOffer() {
        List<TripOffer> trips = TripOffer.find.all();

        return ok(toJson(trips));
    }

    public static Result updateTripOffer(Integer id) {
        TripOffer trip = TripOffer.find.byId(id);
        trip.save();
        return ok(toJson(trip));
    }
}
