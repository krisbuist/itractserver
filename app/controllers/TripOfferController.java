package controllers;

import static play.libs.Json.toJson;

import models.TripOffer;
import play.db.jpa.JPA;
import play.mvc.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripOfferController extends Controller {


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
