package factories;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import models.Location;
import models.Trip;
import models.TripOffer;
import models.TripRequest;
import models.User;

public class ModelFactory {

    private User user;
    private static ModelFactory instance;
    private ArrayList<Location> cities;

    private ModelFactory() {
	loadCityDataFromCsv();
	user = getRandomUser();
	user.save();
    }

    public static ModelFactory getInstance() {
	if (instance == null) {
	    instance = new ModelFactory();
	}
	return instance;
    }

    public TripOffer getRandomTripOffer() {
	TripOffer offer = new TripOffer();
	fillTripWithRandomData(offer);
	return offer;
    }

    private void fillTripWithRandomData(Trip trip) {
	Location origin = getRandomCity();
	Location destination = getRandomCity();
	trip.setOriginLat(origin.getLatitude());
	trip.setOriginLong(origin.getLongitude());
	trip.setDestinationLat(destination.getLatitude());
	trip.setDestinationLong(destination.getLongitude());
	trip.setStartTimeMax(0);
	trip.setStartTimeMax(0);
	trip.setEndTimeMin(0);
	trip.setEndTimeMax(0);
	trip.setNumberOfSeats((int) (Math.random() * 8));
	trip.setUser(user);
    }

    private Location getRandomCity() {
	return cities.get((int) (Math.random() * cities.size()));
    }

    public TripRequest getRandomTripRequest() {
	TripRequest request = new TripRequest();
	fillTripWithRandomData(request);
	return request;
    }

    public User getRandomUser() {
	User u = new User();
	u.setUserName(Long.toHexString(Double.doubleToLongBits(Math.random())));
	return u;
    }

    public void loadCityDataFromCsv() {
	BufferedReader reader;
	try {
	    reader = new BufferedReader(new FileReader("cities.csv"));

	    String[] headerRow = reader.readLine().split(",");
	    if (headerRow.length != 5) {
		reader.close();
		return;
	    }

	    if (!headerRow[0].equals("Woonplaats") || !headerRow[1].equals("Gemeente") || !headerRow[2].equals("Provincie")
		    || !headerRow[3].equals("Latitude") || !headerRow[4].equals("Longitude")) {
		reader.close();
		return;
	    }

	    String line;

	    while ((line = reader.readLine()) != null) {
		String[] cityValues = line.split(",");
		if (cityValues.length == 5) {
		    String address = String.format("%s, %s, %s", cityValues[0], cityValues[1], cityValues[2]);
		    double latitude = Double.parseDouble(cityValues[3]);
		    double longitude = Double.parseDouble(cityValues[4]);
		    Location loc = new Location(longitude, latitude, address);
		    cities.add(loc);
		}
	    }

	    reader.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
