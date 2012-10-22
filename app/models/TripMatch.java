package models;

public class TripMatch
{

    private int match_id;
    private TripOffer tripOffer;
    private TripMatchState match_state;

    public TripMatch()
    {

    }

    public int getMatch_id()
    {
        return match_id;
    }

    public void setMatch_id(int match_id)
    {
        this.match_id = match_id;
    }

    public TripOffer getTripOffer()
    {
        return tripOffer;
    }

    public void setTripOffer(TripOffer tripOffer)
    {
        this.tripOffer = tripOffer;
    }

    public TripMatchState getMatch_state()
    {
        return match_state;
    }

    public void setMatch_state(TripMatchState match_state)
    {
        this.match_state = match_state;
    }
}