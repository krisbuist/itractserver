package test;

import googleMapsDirections.Directions;
import models.Waypoint;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA. User: jurrestender Date: 10/23/12 Time: 1:47 PM
 */
public class DirectionsTest {
    @Test
    public void testGetDirectionsCenter() {
	Waypoint groningen = new Waypoint(53.217800, 6.566400, 0, 0);
	Waypoint zwolle = new Waypoint(52.5125, 6.0886, 0, 0);
	Waypoint center = new Waypoint(52.86515, 6.3275, 0, 0);
	Directions directions = new Directions();
	directions.addWaypoint(groningen);
	directions.addWaypoint(zwolle);

	assert (center.equals(directions.getDirectionsCenter()));
    }

    @Test
    public void testVerticalAlignedStartAndEndpoint() {
	Waypoint north = new Waypoint(53.217800, 6.566400, 0, 0);
	Waypoint south = new Waypoint(52.5125, 6.566400, 0, 0);
	Waypoint northWestBounds = new Waypoint(53.2659723719725, 6.178829919970966, 0, 0);
	Waypoint southEastBounds = new Waypoint(52.46432762658863, 6.953970078637736, 0, 0);

	Directions directions = new Directions();
	directions.addWaypoint(north);
	directions.addWaypoint(south);

	System.out.println(directions.getNorthWestBounds().getLatLongString());
	System.out.println(directions.getSouthEastBounds().getLatLongString());

	assert (northWestBounds.equals(directions.getNorthWestBounds()));
	assert (southEastBounds.equals(directions.getSouthEastBounds()));
    }

    @Test
    public void testHorizontallAlignedStartAndEndpoint() {
	Waypoint east = new Waypoint(53.217800, 6.566400, 0, 0);
	Waypoint west = new Waypoint(53.217800, 6.266400, 0, 0);
	Waypoint northWestBounds = new Waypoint(53.38405212178819, 6.2504581386771605, 0, 0);
	Waypoint southEastBounds = new Waypoint(53.051547877615, 6.582341860727141, 0, 0);

	Directions directions = new Directions();
	directions.addWaypoint(east);
	directions.addWaypoint(west);

	System.out.println(directions.getNorthWestBounds().getLatLongString());
	System.out.println(directions.getSouthEastBounds().getLatLongString());

	assert (northWestBounds.equals(directions.getNorthWestBounds()));
	assert (southEastBounds.equals(directions.getSouthEastBounds()));
    }
}