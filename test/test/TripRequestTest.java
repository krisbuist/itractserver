package test;

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

        assert (request.getMatchingOffers().contains(offerToMatch));
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

        assert (!request.getMatchingOffers().contains(offerToMatch));
    }

    @Test
    public void testMatchingOnTimeWindows() {
        TripOffer offerToMatch = new TripOffer();
        offerToMatch.setOriginLat(50.0);
        offerToMatch.setOriginLong(10.0);
        offerToMatch.setDestinationLat(60.0);
        offerToMatch.setDestinationLong(15.0);
        offerToMatch.setStartTimeMin(100);
        offerToMatch.setStartTimeMax(200);
        offerToMatch.setEndTimeMin(300);
        offerToMatch.setEndTimeMax(400);
        offerToMatch.setNumberOfSeats(1);
        offerToMatch.save();

        TripRequest request = new TripRequest();
        request.setOriginLat(50.0);
        request.setOriginLong(10.0);
        request.setDestinationLat(60.0);
        request.setDestinationLong(15.0);
        request.setStartTimeMin(220);
        request.setStartTimeMax(230);
        request.setEndTimeMin(240);
        request.setEndTimeMax(250);
        request.setNumberOfSeats(1);
        request.save();

        assert (request.getMatchingOffers().contains(offerToMatch));

        request.setStartTimeMin(150);
        request.setStartTimeMax(160);
        request.setEndTimeMin(350);
        request.setEndTimeMax(360);
        request.save();

        assert (request.getMatchingOffers().contains(offerToMatch));

        request.setStartTimeMin(150);
        request.setStartTimeMax(250);
        request.setEndTimeMin(350);
        request.setEndTimeMax(450);
        request.save();

        assert (request.getMatchingOffers().contains(offerToMatch));

        request.setStartTimeMin(10);
        request.setStartTimeMax(20);
        request.setEndTimeMin(30);
        request.setEndTimeMax(40);
        request.save();

        assert (!request.getMatchingOffers().contains(offerToMatch));

        request.setStartTimeMin(10);
        request.setStartTimeMax(20);
        request.setEndTimeMin(330);
        request.setEndTimeMax(340);
        request.save();

        assert (!request.getMatchingOffers().contains(offerToMatch));

        request.setStartTimeMin(420);
        request.setStartTimeMax(430);
        request.setEndTimeMin(440);
        request.setEndTimeMax(450);
        request.save();

        assert (!request.getMatchingOffers().contains(offerToMatch));
    }

    @Test
    public void testMatchingOnSeats() {
        TripOffer offerToMatch = new TripOffer();
        offerToMatch.setOriginLat(50.0);
        offerToMatch.setOriginLong(10.0);
        offerToMatch.setDestinationLat(55.0);
        offerToMatch.setDestinationLong(15.0);
        offerToMatch.setNumberOfSeats(2);
        offerToMatch.save();

        TripRequest request = new TripRequest();
        request.setOriginLat(50.0);
        request.setOriginLong(10.0);
        request.setDestinationLat(55.0);
        request.setDestinationLong(15.0);
        request.setNumberOfSeats(2);
        request.save();

        assert (request.getMatchingOffers().contains(offerToMatch));

        request.setNumberOfSeats(1);
        request.save();
        assert (!request.getMatchingOffers().contains(offerToMatch));

        request.setNumberOfSeats(3);
        request.save();
        assert (request.getMatchingOffers().contains(offerToMatch));

    }

}
