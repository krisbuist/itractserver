package googleMapsDirections;

import java.util.ArrayList;
import java.util.Iterator;

import models.Location;

import org.codehaus.jackson.JsonNode;

public class Directions {

    ArrayList<Location> route;
    final String apiServer = "http://maps.googleapis.com/maps/api/directions/json?";
    long duration = 0;
    long distance = 0;

    public Directions() {
        route = new ArrayList<Location>();
        parseGoogleAPI();
    }

    public void addRoutePoint(Location loc) {
        route.add(loc);
    }

    public long getTotalDirectionDistance() {
        return distance;
    }

    private void parseGoogleAPI() {
        String requestURL = String.format("%sorigin=%s&destination=%s&sensor=false&units=metric&waypoints=%s", apiServer, getOriginLocation(),
                getDestinationLocation(), getWaypointsLocations());

        HttpRequest req = new HttpRequest(requestURL);
        JsonNode result = req.getResult();

        if (result.findValue("routes") != null && result.findValue("routes").findValue("legs") != null) {
            Iterator<JsonNode> it = result.findValue("routes").findValue("legs").getElements();
            while (it.hasNext()) {
                JsonNode node = it.next();

                if (node.findValue("distance") != null && node.findValue("distance").findValue("value") != null) {
                    JsonNode distanceValue = node.findValue("distance").findValue("value");
                    distance += distanceValue.asLong();
                }

                if (node.findValue("duration") != null && node.findValue("duration").findValue("value") != null) {
                    JsonNode durationValue = node.findValue("duration").findValue("value");
                    duration += durationValue.asLong();
                }
            }
        }
    }

    public long getCalculatedTravelTimeInSeconds() {
        return duration == 0 ? getApproximateTravelTimeInSeconds() : duration;
    }

    public long getApproximateTravelTimeInSeconds() {
        return (long) getTotalLinearDistance() * 20;
    }

    public float getTotalLinearDistance() {
        float distance = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            distance += distFrom(route.get(i), route.get(i + 1));
        }
        return distance;
    }

    private float distFrom(Location origin, Location destination) {
        return distFrom(origin.getLongitude(), origin.getLatitude(), destination.getLongitude(), destination.getLatitude());
    }

    private float distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2)
                * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        int meterConversion = 1609;

        return (float) (dist * meterConversion);
    }

    private String getWaypointsLocations() {
        StringBuilder res = new StringBuilder();
        for (Location loc : route.subList(1, route.size() - 1)) {
            res.append(loc.getLongLatString());
            res.append("|");
        }
        return res.toString();
    }

    private String getDestinationLocation() {
        return route.get(route.size() - 1).getLongLatString();
    }

    private String getOriginLocation() {
        return route.get(0).getLongLatString();
    }

}
