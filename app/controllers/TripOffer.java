package controllers;

import static play.libs.Json.toJson;
import play.mvc.*;

import java.util.HashMap;
import java.util.Map;

public class TripOffer extends Controller {


    public static Result getTripOffers()
    {
        Map<String, String> result = new HashMap<>();
        result.put("test", "test");

        return ok(toJson(result));
    }

    public static Result getTripOffer(int id)
    {
        return ok("dd");
    }

    public static Result setTripOffer()
    {
        return TODO;
    }

    public static Result updateTripOffer(int id)
    {
        return TODO;
    }
}
