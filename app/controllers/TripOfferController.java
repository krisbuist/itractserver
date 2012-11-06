package controllers;

import static play.libs.Json.toJson;

import java.util.List;
import java.util.Map;

import models.TripOffer;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;

public class TripOfferController extends Controller {

    public static Result getTripOffers() {
	List<TripOffer> trips = TripOffer.find.where().le("id", 50).findList();
	return ok(toJson(trips));
    }

    public static Result getTripOffer(Integer id) {
	TripOffer trip = TripOffer.find.byId(id);
	if (trip != null) {
	    return ok(toJson(trip));
	} else {
	    return notFound();
	}
    }

    public static Result newTripOffer() {

	TripOffer t = new TripOffer();
	Map<String, String[]> values = request().body().asFormUrlEncoded();

	try{
	    t.setOriginLong(Double.parseDouble(values.get("origin_long")[0]));
	    t.setOriginLat(Double.parseDouble(values.get("origin_lat")[0]));
	    t.setOriginAddress("Origin");
	    t.setDestinationLong(Double.parseDouble(values.get("destination_long")[0]));
	    t.setDestinationLat(Double.parseDouble(values.get("destination_lat")[0]));
	    t.setDestinationAddress("Destination");
	    t.setStartTimeMin(Long.parseLong(values.get("start_time_min")[0]));
	    t.setStartTimeMax(Long.parseLong(values.get("start_time_max")[0]));
	    t.setEndTimeMin(Long.parseLong(values.get("end_time_min")[0]));
	    t.setEndTimeMax(Long.parseLong(values.get("end_time_max")[0]));
	    t.setNumberOfSeats(Integer.parseInt(values.get("number_of_seats")[0]));
	    t.setUser(User.find.byId(Integer.parseInt(values.get("user")[0])));
	    t.save();
	}
	catch (NullPointerException e)
	{
	    return badRequest(e.getMessage());
	}
	catch (NumberFormatException e)
	{
	    return badRequest(e.getMessage());
	}
	
	return getTripOffer(t.getId());
    }

    public static Result deleteTripOffer(Integer id) {
	TripOffer toDelete = TripOffer.find.byId(id);
	if(toDelete == null)
	{
	    return notFound();
	}
	toDelete.delete();
	return noContent();
    }

    public static Result updateTripOffer(Integer id) {
	TripOffer trip = TripOffer.find.byId(id);
	trip.save();
	return ok(toJson(trip));
    }
}