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
	public static Finder<Integer, TripMatch> find = new Finder<Integer, TripMatch>(
			Integer.class, TripMatch.class);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	private TripOffer tripOffer;
	@ManyToOne
	private TripRequest tripRequest;
	@Required
	@Enumerated(EnumType.STRING)
	private TripMatchState state;

	public TripMatch() {

	}

	public int getId() {
		return id;
	}

	public TripOffer getTripOffer() {
		return tripOffer;
	}

	public void setTripOffer(TripOffer tripOffer) {
		this.tripOffer = tripOffer;
	}

	public int getState() {
		return state.ordinal();
	}

	public void setState(int state) {
		this.state = TripMatchState.values()[state];
	}

	public void setTripRequest(TripRequest tripRequest) {
		this.tripRequest = tripRequest;
	}

	public TripRequest getTripRequest() {
		return tripRequest;
	}

	public TripMatchState getEnumState() {
		return TripMatchState.values()[getState()];
	}
}