package controllers;

import static play.libs.Json.toJson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import models.TripOffer;
import models.TripRequest;
import play.mvc.Controller;
import play.mvc.Result;

public class MatchController extends Controller {

    public static Result getMatch(Integer id) {
	try {
	    TripRequest tripRequest = TripRequest.find.byId(id);
	    List<TripOffer> matches = tripRequest.getMatchingOffers();

	    LinkedHashMap<String, Object> details = new LinkedHashMap<String, Object>();

	    LinkedHashMap<String, Object> triprequestDetails = new LinkedHashMap<String, Object>();
	    triprequestDetails.put("request_id", tripRequest.getId());
	    triprequestDetails.put("request_user", tripRequest.getUser().getId());
	    triprequestDetails.put("request_origin_long", tripRequest.getOriginLong());
	    triprequestDetails.put("request_origin_lat", tripRequest.getOriginLat());
	    triprequestDetails.put("request_origin_window", 0);
	    triprequestDetails.put("request_destination_long", tripRequest.getDestinationLong());
	    triprequestDetails.put("request_destination_lat", tripRequest.getDestinationLat());
	    triprequestDetails.put("request_destination_window", 0);
	    triprequestDetails.put("request_start_time_min", tripRequest.getStartTimeMin());
	    triprequestDetails.put("request_start_time_max", tripRequest.getStartTimeMax());
	    triprequestDetails.put("request_end_time_min", tripRequest.getEndTimeMin());
	    triprequestDetails.put("request_end_time_max", tripRequest.getEndTimeMax());
	    triprequestDetails.put("request_number_of_seats", tripRequest.getNumberOfSeats());
	    triprequestDetails.put("request_state", "OPEN");

	    details.put("tripRequest", triprequestDetails);

	    ArrayList<LinkedHashMap<String, Object>> matchesDetails = new ArrayList<LinkedHashMap<String, Object>>();

	    for (TripOffer trip : matches) {
		LinkedHashMap<String, Object> matchDetails = new LinkedHashMap<String, Object>();
		matchDetails.put("offer_id", trip.getId());
		matchDetails.put("offer_user", trip.getUser().getId());
		matchDetails.put("offer_origin_long", trip.getOriginLong());
		matchDetails.put("offer_origin_lat", trip.getOriginLat());
		matchDetails.put("offer_origin_window", 0);
		matchDetails.put("offer_destination_long", trip.getDestinationLong());
		matchDetails.put("offer_destination_lat", trip.getDestinationLat());
		matchDetails.put("offer_destination_window", 0);
		matchDetails.put("offer_start_time_min", trip.getStartTimeMin());
		matchDetails.put("offer_start_time_max", trip.getStartTimeMax());
		matchDetails.put("offer_end_time_min", trip.getEndTimeMin());
		matchDetails.put("offer_end_time_max", trip.getEndTimeMax());
		matchDetails.put("offer_number_of_seats", trip.getNumberOfSeats());
		matchDetails.put("offer_state", "OPEN");
		matchDetails.put("match_id", 0);
		matchDetails.put("match_state", "OPEN");
		matchesDetails.add(matchDetails);
	    }

	    details.put("tripMatches", matchesDetails);
	    return ok(toJson(details));
	} catch (Exception e) {
	    return badRequest();
	}
    }

    public static Result updateMatch(Integer id) {
	return TODO;
    }

}
