package controllers;

import javax.persistence.PersistenceException;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import workers.ModelFactory;

public class GeneratorController extends Controller {

    protected static ModelFactory modelFactory = ModelFactory.getInstance();

    public static Result generateTripOffers(Integer number) {
	for (int i = 0; i < number; i++) {
	    modelFactory.getRandomTripOffer().save();
	}
	return redirect(routes.TripOfferController.getTripOffers());
    }

    public static Result generateTripRequests(Integer number) {
	for (int i = 0; i < number; i++) {
	    modelFactory.getRandomTripRequest().save();
	}
	return redirect(routes.TripRequestController.getTripRequests());
    }

    public static Result generateUsers(Integer number) {
	for (int i = 0; i < number; i++) {
	    User newUser = modelFactory.generateNewRandomUser();
	    boolean inserted = false;
	    int j = 1;
	    while (!inserted || j > 10) {
		try {
		    newUser.save();
		    inserted = true;
		} catch (PersistenceException pe) {
		    newUser.setEmail(newUser.getFirstName() + "." + newUser.getLastName() + j + "@itract.com");
		    j++;
		}
	    }
	}
	return redirect(routes.UserController.getUsers());
    }
}
