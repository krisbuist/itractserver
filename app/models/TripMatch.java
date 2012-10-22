package models;

public class TripMatch {

    private int matchId;
    private TripOffer tripOffer;
    private TripMatchState matchState;

    public TripMatch() {

    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public TripOffer getTripOffer() {
        return tripOffer;
    }

    public void setTripOffer(TripOffer tripOffer) {
        this.tripOffer = tripOffer;
    }

    public TripMatchState getMatchState() {
        return matchState;
    }

    public void setMatchState(TripMatchState matchState) {
        this.matchState = matchState;
    }
}