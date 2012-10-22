package googleMapsDirections;

/**
 * Created with IntelliJ IDEA.
 * User: jurrestender
 * Date: 10/22/12
 * Time: 3:36 PM
 */
public class Location {
    private long longitude;
    private long latitude;
    private String address;

    public Location(long longitude, long latitude, String address) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }

    public Location(long longitude, long latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
