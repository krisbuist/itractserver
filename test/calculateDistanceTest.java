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

}
