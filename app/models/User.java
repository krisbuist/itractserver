package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class User extends Model {

    private static final long serialVersionUID = -4929261798604562211L;
    public static Finder<Integer, User> find = new Finder<Integer, User>(Integer.class, User.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Constraints.Required
    @Constraints.MinLength(3)
    @Constraints.MaxLength(20)
    private String name;
    
    @Constraints.Required
    @Constraints.MinLength(3)
    @Constraints.MaxLength(20)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {

    }

    public int getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }
}
