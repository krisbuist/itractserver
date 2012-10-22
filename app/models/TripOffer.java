package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class TripOffer extends Trip {

    private static final long serialVersionUID = -386674185196292407L;

    public TripOffer() {
	super();
    }

    public static List<TripOffer> all() {
	return new ArrayList<TripOffer>();
    }

    public static void create(TripOffer task) {
    }

    public static void delete(Long id) {
    }

}