package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.db.ebean.Model;

@Entity
public class User extends Model {

    private static final long serialVersionUID = -4929261798604562211L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Constraints.Required
    @MinLength(5)
    @MaxLength(20)
    private String userName;

    public User() {

    }

    public int getUserId() {
	return userId;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }
}
