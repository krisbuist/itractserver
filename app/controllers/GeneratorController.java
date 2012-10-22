package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import factories.ModelFactory;

public class GeneratorController extends Controller {

    protected static ModelFactory modelFactory = ModelFactory.getInstance();

    public static Result index() {
	return TODO;
    }
    
    public static Result generateTripOffers() {
	for (int i = 0; i < 10000; i++) {
	    modelFactory.getRandomTripOffer().save();
	}
	return redirect(routes.TripOfferController.getTripOffers());
    }

    public static Result generateTripRequests() {
	for (int i = 0; i < 10000; i++) {
	    modelFactory.getRandomTripRequest().save();
	}
	return redirect(routes.TripRequestController.getTripRequests());
    }

}
