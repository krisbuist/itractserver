package models;


import javax.persistence.*;



import play.db.ebean.Model;

@Entity
public class Notification extends Model {


    public static Finder<Integer, Notification> find = new Finder<Integer, Notification>(Integer.class, Notification.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private User user;
    @OneToOne
    private TripMatch tripMatch;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TripMatch getTripMatch() {
        return tripMatch;
    }

    public void setTripMatch(TripMatch match) {
        this.tripMatch = match;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}