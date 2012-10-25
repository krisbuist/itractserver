package test;

import static org.junit.Assert.assertEquals;
import googleMapsDirections.Directions;
import models.Waypoint;

import org.junit.Test;

public class calculateDistanceTest {

    // Used for approximate equals
    private int delta = 10;

    @Test
    public void testFromGroningenToZwolle() {
	Waypoint groningen = new Waypoint(53.219520, 6.566810, 0, 0);
	Waypoint zwolle = new Waypoint(52.516750, 6.083060000000001, 0, 0);

	Directions directions = new Directions();
	directions.addWaypoint(groningen);
	directions.addWaypoint(zwolle);
	directions.retrieveGoogleAPICalculations();

	long totalRouteDistance = directions.getTotalDirectionDistance();
	float lineairDistance = directions.getTotalLinearDistance();
	long tripDuration = directions.getCalculatedTravelTimeInSeconds();

	assertEquals(105414, totalRouteDistance, delta);
	assert (lineairDistance < totalRouteDistance);
	System.out.println(String.format("Route: %d, Lineair: %f, Overhead: %f, Duration: %d, LinearDistance/Duration: %f", totalRouteDistance,
		lineairDistance, (totalRouteDistance / lineairDistance), tripDuration, (lineairDistance / tripDuration)));
    }

    @Test
    public void testFromGroningenToZwolleViaAmsterdam() {
	Waypoint groningen = new Waypoint(53.219520, 6.566810, 0, 0);
	Waypoint amsterdam = new Waypoint(52.3700, 4.8900, 0, 0);
	Waypoint zwolle = new Waypoint(52.516750, 6.083060000000001, 0, 0);

	Directions directions = new Directions();
	directions.addWaypoint(groningen);
	directions.addWaypoint(amsterdam);
	directions.addWaypoint(zwolle);
	directions.retrieveGoogleAPICalculations();

	long totalRouteDistance = directions.getTotalDirectionDistance();
	float lineairDistance = directions.getTotalLinearDistance();
	long tripDuration = directions.getCalculatedTravelTimeInSeconds();

	assertEquals(304207, totalRouteDistance, delta);
	assert (lineairDistance < totalRouteDistance);
	System.out.println(String.format("Route: %d, Lineair: %f, Overhead: %f, Duration: %d, LinearDistance/Duration: %f", totalRouteDistance,
		lineairDistance, (totalRouteDistance / lineairDistance), tripDuration, (lineairDistance / tripDuration)));
    }

    @Test
    public void testFromGroningenToZwolleViaAmsterdamAndAssen() {
	Waypoint groningen = new Waypoint(53.219520, 6.566810, 0, 0);
	Waypoint amsterdam = new Waypoint(52.3700, 4.8900, 0, 0);
	Waypoint assen = new Waypoint(53.0000, 6.5500, 0, 0);
	Waypoint zwolle = new Waypoint(52.516750, 6.083060000000001, 0, 0);

	Directions directions = new Directions();
	directions.addWaypoint(groningen);
	directions.addWaypoint(amsterdam);
	directions.addWaypoint(assen);
	directions.addWaypoint(zwolle);
	directions.retrieveGoogleAPICalculations();

	long totalRouteDistance = directions.getTotalDirectionDistance();
	float lineairDistance = directions.getTotalLinearDistance();
	long tripDuration = directions.getCalculatedTravelTimeInSeconds();

	assertEquals((186127 + 190279 + 77662), directions.getTotalDirectionDistance(), delta);
	assert (lineairDistance < totalRouteDistance);
	System.out.println(String.format("Route: %d, Lineair: %f, Overhead: %f, Duration: %d, LinearDistance/Duration: %f", totalRouteDistance,
		lineairDistance, (totalRouteDistance / lineairDistance), tripDuration, (lineairDistance / tripDuration)));
    }

}
