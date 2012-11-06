package models;

import java.util.Calendar;

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
    private String firstName;

    @Constraints.Required
    private String lastName;

    @Constraints.Required
    private String email;

    @Constraints.Required
    private String password;

    private String address;

    private String postalCode;

    private String city;

    private String countryCode;

    private Calendar dateOfBirth;

    private String gender;

    public User() {

    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getPostalCode() {
	return postalCode;
    }

    public void setPostalCode(String postalCode) {
	this.postalCode = postalCode;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getCountryCode() {
	return countryCode;
    }

    public void setCountryCode(String countryCode) {
	this.countryCode = countryCode;
    }

    public Calendar getDateOfBirth() {
	return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
	return gender;
    }

    public void setGender(String gender) {
	this.gender = gender;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getProfilePicture() {
	return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
	this.profilePicture = profilePicture;
    }

    private String profilePicture;

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public int getId() {
	return id;
    }
}
