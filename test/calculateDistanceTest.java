import static org.junit.Assert.*;
import googleMapsDirections.Directions;
import googleMapsDirections.Location;

import org.junit.Test;


public class calculateDistanceTest {

    //Used for approximate equals
    private int delta = 10;
    
    @Test
    public void testFromGroningenToZwolle() {
	Location groningen = new Location(53.219520, 6.566810);
	Location zwolle = new Location(52.516750, 6.083060000000001);
	
	Directions directions = new Directions();
	directions.addRoutePoint(groningen);
	directions.addRoutePoint(zwolle);
	
	assertEquals(105414, directions.getTotalDistance(), delta);
    }
    
    @Test
    public void testFromGroningenToZwolleViaAmsterdam()
    {
	Location groningen = new Location(53.219520, 6.566810);
	Location amsterdam = new Location(52.3700, 4.8900);
	Location zwolle = new Location(52.516750, 6.083060000000001);
	
	Directions directions = new Directions();
	directions.addRoutePoint(groningen);
	directions.addRoutePoint(amsterdam);
	directions.addRoutePoint(zwolle);
	
	assertEquals(304207, directions.getTotalDistance(), delta);
    }
    
    @Test
    public void testFromGroningenToZwolleViaAmsterdamAndAssen()
    {
	Location groningen = new Location(53.219520, 6.566810);
	Location amsterdam = new Location(52.3700, 4.8900);
	Location assen = new Location(53.0000, 6.5500);
	Location zwolle = new Location(52.516750, 6.083060000000001);
	
	Directions directions = new Directions();
	directions.addRoutePoint(groningen);
	directions.addRoutePoint(amsterdam);
	directions.addRoutePoint(assen);
	directions.addRoutePoint(zwolle);
	
	assertEquals((186127 + 190279 + 77662), directions.getTotalDistance(), delta);
    }

}
