package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import workers.ModelFactory;

public class GeneratorController extends Controller {

    protected static ModelFactory modelFactory = ModelFactory.getInstance();

    public static Result generateTripOffers(Integer number) {
        for (int i = 0; i < number; i++) {
            modelFactory.getRandomTripOffer().save();
        }
        return redirect(routes.TripOfferController.getTripOffer(1));
    }

    public static Result generateTripRequests(Integer number) {
        for (int i = 0; i < number; i++) {
            modelFactory.getRandomTripRequest().save();
        }
        return redirect(routes.TripRequestController.getTripRequest(1));
    }
}
