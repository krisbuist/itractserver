package test;

import googleMapsDirections.Directions;
import models.Location;

import org.junit.Test;


/**
 * Created with IntelliJ IDEA. User: jurrestender Date: 10/23/12 Time: 1:47 PM
 */
public class DirectionsTest {
    @Test
    public void testGetDirectionsCenter() {
	Location groningen = new Location(53.217800, 6.566400);
	Location zwolle = new Location(52.5125, 6.0886);
	Location center = new Location(52.86515, 6.3275);
	Directions directions = new Directions();
	directions.addRoutePoint(groningen);
	directions.addRoutePoint(zwolle);

	assert (center.equals(directions.getDirectionsCenter()));
    }

    @Test
    public void testVerticalAlignedStartAndEndpoint() {
        Location north = new Location(53.217800, 6.566400);
        Location south = new Location(52.5125, 6.566400);
        Location northWestBounds = new Location(53.2659723719725, 6.178829919970966);
        Location southEastBounds = new Location(52.46432762658863, 6.953970078637736);

        Directions directions = new Directions();
        directions.addRoutePoint(north);
        directions.addRoutePoint(south);

//        System.out.println(directions.getNorthWestBounds().getLongLatString());
//        System.out.println(directions.getSouthEastBounds().getLongLatString());

        assert(northWestBounds.equals(directions.getNorthWestBounds()));
        assert(southEastBounds.equals(directions.getSouthEastBounds()));
    }

    @Test
    public void testHorizontallAlignedStartAndEndpoint() {
        Location north = new Location(53.217800, 6.566400);
        Location south = new Location(53.217800, 6.266400);
        Location northWestBounds = new Location(53.38405212178819, 6.2504581386771605);
        Location southEastBounds = new Location(53.051547877615, 6.582341860727141);

        Directions directions = new Directions();
        directions.addRoutePoint(north);
        directions.addRoutePoint(south);

//        System.out.println(directions.getNorthWestBounds().getLongLatString());
//        System.out.println(directions.getSouthEastBounds().getLongLatString());

        assert(northWestBounds.equals(directions.getNorthWestBounds()));
        assert(southEastBounds.equals(directions.getSouthEastBounds()));
    }
}