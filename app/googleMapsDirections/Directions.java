package googleMapsDirections;

import java.util.ArrayList;

import org.codehaus.jackson.JsonNode;

public class Directions {

    ArrayList<Location> route;
    
    public Directions()
    {
	route = new ArrayList<Location>();
    }
    
    public void addRoutePoint(Location loc)
    {
	route.add(loc);
    }
    
    public long calculateDistance()
    {
	HttpRequest req = new HttpRequest("");
	JsonNode result = req.getBody("http://maps.googleapis.com/maps/api/directions/json?origin=Toronto&destination=Montreal&sensor=false");
	long distance = Long.parseLong(result.get("routes").findValue("legs").findValue("distance").findValue("value").toString());
	return distance;
    }
    
    private class Location {
	
    }
    
}
