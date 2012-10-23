package test;

import static org.junit.Assert.*;

import googleMapsDirections.Directions;

import models.Location;

import org.junit.Test;

import java.util.ArrayList;

public class calculateDistanceTest {

    // Used for approximate equals
    private int delta = 10;

    @Test
    public void testFromGroningenToZwolle() {
        Location groningen = new Location(53.219520, 6.566810);
        Location zwolle = new Location(52.516750, 6.083060000000001);

        ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(groningen);
        locations.add(zwolle);
        Directions directions = new Directions(locations);


        long totalRouteDistance = directions.getTotalDirectionDistance();
        float lineairDistance = directions.getTotalLinearDistance();
        long tripDuration = directions.getCalculatedTravelTimeInSeconds();

        assertEquals(105414, totalRouteDistance, delta);
        assert (lineairDistance < totalRouteDistance);
        System.out.println(String.format("Route: %d, Lineair: %f, Overhead: %f, Duration: %d, LinearDistance/Duration: %f", totalRouteDistance, lineairDistance,
                (totalRouteDistance / lineairDistance), tripDuration, (lineairDistance / tripDuration)));
    }

    @Test
    public void testFromGroningenToZwolleViaAmsterdam() {
        Location groningen = new Location(53.219520, 6.566810);
        Location amsterdam = new Location(52.3700, 4.8900);
        Location zwolle = new Location(52.516750, 6.083060000000001);

        ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(groningen);
        locations.add(amsterdam);
        locations.add(zwolle);

        Directions directions = new Directions(locations);

        long totalRouteDistance = directions.getTotalDirectionDistance();
        float lineairDistance = directions.getTotalLinearDistance();
        long tripDuration = directions.getCalculatedTravelTimeInSeconds();

        assertEquals(304207, totalRouteDistance, delta);
        assert (lineairDistance < totalRouteDistance);
        System.out.println(String.format("Route: %d, Lineair: %f, Overhead: %f, Duration: %d, LinearDistance/Duration: %f", totalRouteDistance, lineairDistance,
                (totalRouteDistance / lineairDistance), tripDuration, (lineairDistance / tripDuration)));
    }

    @Test
    public void testFromGroningenToZwolleViaAmsterdamAndAssen() {
        Location groningen = new Location(53.219520, 6.566810);
        Location amsterdam = new Location(52.3700, 4.8900);
        Location assen = new Location(53.0000, 6.5500);
        Location zwolle = new Location(52.516750, 6.083060000000001);

        ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(groningen);
        locations.add(amsterdam);
        locations.add(assen);
        locations.add(zwolle);

        Directions directions = new Directions(locations);


        long totalRouteDistance = directions.getTotalDirectionDistance();
        float lineairDistance = directions.getTotalLinearDistance();
        long tripDuration = directions.getCalculatedTravelTimeInSeconds();

        assertEquals((186127 + 190279 + 77662), directions.getTotalDirectionDistance(), delta);
        assert (lineairDistance < totalRouteDistance);
        System.out.println(String.format("Route: %d, Lineair: %f, Overhead: %f, Duration: %d, LinearDistance/Duration: %f", totalRouteDistance, lineairDistance,
                (totalRouteDistance / lineairDistance), tripDuration, (lineairDistance / tripDuration)));
    }

}
