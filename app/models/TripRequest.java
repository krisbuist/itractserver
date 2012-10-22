package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class TripRequest extends Trip {

	public TripRequest() {
		super();
	}
	
	public List<TripOffer> getMatchingOffers()
	{
		return new ArrayList<TripOffer>();
	}
}