package models;


import javax.persistence.*;



import play.db.ebean.Model;

@Entity
public class Notification extends Model {
    /**
     * 
     */
    private static final long serialVersionUID = 5525198001992977678L;

    public static Finder<Integer, Notification> find = new Finder<Integer, Notification>(Integer.class, Notification.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private User user;
    @OneToOne
    private TripMatch tripMatch;

    private String title;
    private String message;
    private int state;


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}