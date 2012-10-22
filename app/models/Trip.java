package models;

public abstract class Trip
{
    private int id;
    private int profileId;
    private double origin_long;
    private double origin_lat;
    private int origin_window;
    private double destination_long;
    private double destination_lat;
    private int destination_window;
    private long start_time_min;
    private long start_time_max;
    private long end_time_min;
    private long end_time_max;

    public int getOrigin_window()
    {
        return origin_window;
    }

    public void setOrigin_window(int origin_window)
    {
        this.origin_window = origin_window;
    }

    public int getDestination_window()
    {
        return destination_window;
    }

    public void setDestination_window(int destination_window)
    {
        this.destination_window = destination_window;
    }

    public long getStart_time_min()
    {
        return start_time_min;
    }

    public void setStart_time_min(long start_time_min)
    {
        this.start_time_min = start_time_min;
    }

    public long getStart_time_max()
    {
        return start_time_max;
    }

    public void setStart_time_max(long start_time_max)
    {
        this.start_time_max = start_time_max;
    }

    public long getEnd_time_min()
    {
        return end_time_min;
    }

    public void setEnd_time_min(long end_time_min)
    {
        this.end_time_min = end_time_min;
    }

    public long getEnd_time_max()
    {
        return end_time_max;
    }

    public void setEnd_time_max(long end_time_max)
    {
        this.end_time_max = end_time_max;
    }

    private int numberOfSeats;

    public Trip()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getProfileId()
    {
        return profileId;
    }

    public void setProfileId(int profileId)
    {
        this.profileId = profileId;
    }

    public double getOrigin_long()
    {
        return origin_long;
    }

    public void setOrigin_long(double origin_long)
    {
        this.origin_long = origin_long;
    }

    public double getOrigin_lat()
    {
        return origin_lat;
    }

    public void setOrigin_lat(double origin_lat)
    {
        this.origin_lat = origin_lat;
    }

    public double getDestination_long()
    {
        return destination_long;
    }

    public void setDestination_long(double destination_long)
    {
        this.destination_long = destination_long;
    }

    public double getDestination_lat()
    {
        return destination_lat;
    }

    public void setDestination_lat(double destination_lat)
    {
        this.destination_lat = destination_lat;
    }

    public int getNumberOfSeats()
    {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats)
    {
        this.numberOfSeats = numberOfSeats;
    }
}