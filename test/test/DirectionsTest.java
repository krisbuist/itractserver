package test;
import googleMapsDirections.Directions;
import models.Location;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: jurrestender
 * Date: 10/23/12
 * Time: 1:47 PM
 */
public class DirectionsTest {
    @Test
    public void testGetDirectionsCenter() {
        Location groningen = new Location(53.217800, 6.566400);
        Location zwolle = new Location(52.5125, 6.0886);
        Location center = new Location(52.86515, 6.3275);
        ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(groningen);
        locations.add(zwolle);
        Directions directions = new Directions(locations);
        
        assert(center.equals(directions.getDirectionsCenter()));
    }
}
