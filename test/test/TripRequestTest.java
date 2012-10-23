package test;

import java.util.List;

import models.TripOffer;
import models.TripRequest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import play.test.FakeApplication;
import play.test.Helpers;

public class TripRequestTest {
    public static FakeApplication app;

    @BeforeClass
    public static void startApp() {
	app = Helpers.fakeApplication(Helpers.inMemoryDatabase());
	Helpers.start(app);
    }

    @AfterClass
    public static void stopApp() {
	Helpers.stop(app);
    }

    @Test
    public void testMatchOnLocation() {
	TripOffer offerToMatch = new TripOffer();
	offerToMatch.setOriginLat(50.0);
	offerToMatch.setOriginLong(10.0);
	offerToMatch.setDestinationLat(55.0);
	offerToMatch.setDestinationLong(15.0);
	offerToMatch.setNumberOfSeats(1);
	offerToMatch.save();

	TripRequest request = new TripRequest();
	request.setOriginLat(50.0);
	request.setOriginLong(10.0);
	request.setDestinationLat(55.0);
	request.setDestinationLong(15.0);
	request.setNumberOfSeats(1);
	request.save();

	List<TripOffer> matchingOffers = request.getMatchingOffers();
	assert (matchingOffers.contains(offerToMatch));
    }

    @Test
    public void testMismatchOnLocation() {
	TripOffer offerToMatch = new TripOffer();
	offerToMatch.setOriginLat(50.0);
	offerToMatch.setOriginLong(10.0);
	offerToMatch.setDestinationLat(55.0);
	offerToMatch.setDestinationLong(15.0);
	offerToMatch.setNumberOfSeats(1);
	offerToMatch.save();

	TripRequest request = new TripRequest();
	request.setOriginLat(55.0);
	request.setOriginLong(15.0);
	request.setDestinationLat(60.0);
	request.setDestinationLong(20.0);
	request.setNumberOfSeats(1);
	request.save();

	List<TripOffer> matchingOffers = request.getMatchingOffers();
	assert (!matchingOffers.contains(offerToMatch));
    }

    @Test
    public void testMatchOnTimeWindows() {
	TripOffer offerToMatch = new TripOffer();
	offerToMatch.setOriginLat(50.0);
	offerToMatch.setOriginLong(10.0);
	offerToMatch.setDestinationLat(60.0);
	offerToMatch.setDestinationLong(15.0);
	offerToMatch.setStartTimeMin(10);
	offerToMatch.setStartTimeMax(20);
	offerToMatch.setEndTimeMin(60);
	offerToMatch.setEndTimeMax(70);
	offerToMatch.setNumberOfSeats(1);
	offerToMatch.save();

	TripRequest request = new TripRequest();
	request.setOriginLat(50.0);
	request.setOriginLong(10.0);
	request.setDestinationLat(60.0);
	request.setDestinationLong(15.0);
	request.setStartTimeMin(30);
	request.setStartTimeMax(35);
	request.setEndTimeMin(40);
	request.setEndTimeMax(45);
	request.setNumberOfSeats(1);
	request.save();

	List<TripOffer> matchingOffers = request.getMatchingOffers();
	assert (matchingOffers.contains(offerToMatch));
    }

    @Test
    public void testMismatchOnTimeWindows() {
	TripOffer offerToMatch = new TripOffer();
	offerToMatch.setOriginLat(50.0);
	offerToMatch.setOriginLong(10.0);
	offerToMatch.setDestinationLat(60.0);
	offerToMatch.setDestinationLong(15.0);
	offerToMatch.setStartTimeMin(10);
	offerToMatch.setStartTimeMax(20);
	offerToMatch.setEndTimeMin(60);
	offerToMatch.setEndTimeMax(70);
	offerToMatch.setNumberOfSeats(1);
	offerToMatch.save();

	TripRequest request = new TripRequest();
	request.setOriginLat(50.0);
	request.setOriginLong(10.0);
	request.setDestinationLat(60.0);
	request.setDestinationLong(15.0);
	request.setStartTimeMin(0);
	request.setStartTimeMax(5);
	request.setEndTimeMin(40);
	request.setEndTimeMax(45);
	request.setNumberOfSeats(1);
	request.save();

	List<TripOffer> matchingOffers = request.getMatchingOffers();
	assert (!matchingOffers.contains(offerToMatch));
    }

}
