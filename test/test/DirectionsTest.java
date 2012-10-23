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
    public void testGetNorthWestBounds() {
        Location groningen = new Location(53.217800, 6.566400);
        Location zwolle = new Location(52.5125, 6.0886);
        Location northWestBounds = new Location(53.29128768290621, 5.903958274442637);
        Location southEastBounds = new Location(52.43901231709379, 6.7510417255573625);

        ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(groningen);
        locations.add(zwolle);
        Directions directions = new Directions(locations);

        assert(northWestBounds.equals(directions.getNorthWestBounds()));
        assert(southEastBounds.equals(directions.getSouthEastBounds()));
    }
}
