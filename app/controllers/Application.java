package controllers;

import models.TripMatch;
import models.TripMatchState;
import models.TripOffer;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    public static Result index() {
	TripOffer tripOffer = new TripOffer();
	User user = new User();
	user.setUserName("test");
	user.save();
	tripOffer.setUser(user);
	tripOffer.setNumberOfSeats(4);

	tripOffer.save();

	TripMatch tripMatch = new TripMatch();
	tripMatch.setMatchState(TripMatchState.MATCHED);
	tripMatch.setTripOffer(tripOffer);
	tripMatch.save();
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