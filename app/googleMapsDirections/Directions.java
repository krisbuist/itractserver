package googleMapsDirections;

import java.util.ArrayList;

import org.codehaus.jackson.JsonNode;

public class Directions {

    ArrayList<Location> route;
    final String apiServer = "http://maps.googleapis.com/maps/api/directions/json?";

    public Directions() {
	route = new ArrayList<Location>();
    }

    public void addRoutePoint(Location loc) {
	route.add(loc);
    }

    public long calculateDistance()
    {
	
	String requestURL = String.format("%sorigin=%s&destination=%s&sensor=false&units=metric&waypoints=%s", apiServer, getOriginLocation(), getDestinationLocation(), getWaypointsLocations());

	HttpRequest req = new HttpRequest(requestURL);
	JsonNode result = req.getResult();
	
	long distance = Long.parseLong(result.get("routes").findValue("legs").findValue("distance").findValue("value").toString());
	
	return distance;
    }

    private String getWaypointsLocations() {
	StringBuilder res = new StringBuilder();
	for (Location loc : route.subList(1, route.size()-1)) {
	    res.append(loc.toString());
	}
	return res.toString();
    }

    private String getDestinationLocation() {
	return route.get(route.size()-1).toString();
    }

    private String getOriginLocation() {
	return route.get(0).toString();
    }

    private class Location {

    }

}
