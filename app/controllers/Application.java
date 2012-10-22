package controllers;

import models.TripOffer;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    public static Result index() {
	TripOffer tripOffer = new TripOffer();
	tripOffer.setProfileId(1);
	tripOffer.setNumberOfSeats(4);

	tripOffer.save();
	return ok(index.render("Your new application is ready."));
    }

    public static Result tripOffers() {
	return TODO;
    }

    public static Result newTripOffer() {
	return TODO;
    }

    public static Result deleteTripOffer(Long id) {
	return TODO;
    }

}