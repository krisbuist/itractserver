package validators;


public class Validator {
    public static void validateLongitude(double longitude) throws ItractDataException {
	if (longitude < -180 || longitude > 180)
	    throw new ItractDataException("Invalid longitude " + longitude);
    }

    public static void validateLatitude(double latitude) throws ItractDataException {
	if (latitude < -90 || latitude > 90)
	    throw new ItractDataException("Invalid latitude " + latitude);
    }

    public static void validateNumberofSeats(int numberOfSeats)
	    throws ItractDataException {
	if (numberOfSeats < 0 || numberOfSeats > 7)
	    throw new ItractDataException("Invalid number of seats "
		    + numberOfSeats);
    }
}
