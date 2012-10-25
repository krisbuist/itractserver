package controllers;


import static play.libs.Json.toJson;

import java.util.List;

import models.TripOffer;
import models.TripRequest;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;

public class UserController extends Controller {

    public static Result getUser(Integer id) {
    	User user = User.find.byId(id);

        return ok(toJson(user));
    }

    public static Result getUsers() {
    	List<User> users = User.find.all();

        return ok(toJson(users));
    }

    public static Result setUser() {
    	List<User> users = User.find.all();

        return ok(toJson(users));
    }

    public static Result updateUser(Integer id) {
    	User user = User.find.byId(id);
        user.save();
        return ok(toJson(user));
    }
}
