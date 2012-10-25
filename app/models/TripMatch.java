package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class TripMatch extends Model {

    private static final long serialVersionUID = -2382823100066234473L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int matchId;
    @ManyToOne
    private TripOffer tripOffer;
    @Required
    @Enumerated(EnumType.STRING)
    private TripMatchState matchState;

    public TripMatch() {

    }

    public int getMatchId() {
        return matchId;
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