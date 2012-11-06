package controllers;

import static play.libs.Json.toJson;

import java.util.LinkedHashMap;
import java.util.List;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class UserController extends Controller {

    public static Result getUsers() {
	List<User> users = User.find.all();
	return ok(toJson(users));
    }

    public static Result getUser(Integer id) {
	User user = User.find.byId(id);
	if (user != null) {
	    return ok(toJson(user));
	} else {
	    return notFound();
	}

    }

    public static Result newUser() {
	Form<User> form = form(User.class).bindFromRequest();
	if (form.hasErrors()) {
	    return badRequest();
	} else {
	    User user = form.get();
	    user.save();
	    LinkedHashMap<String, Object> details = new LinkedHashMap<String, Object>();
	    details.put("user_id", user.getId());
	    details.put("user_name", user.getFirstName());
	    details.put("user_email", user.getEmail());
	    return status(201, toJson(details));
	}
    }

    public static Result updateUser(Integer id) {
	return TODO;
    }

    public static Result deleteUser(Integer id) {
	User u = User.find.byId(id);
	if (u != null) {
	    u.delete();
	    return ok();
	} else {
	    return notFound();
	}
    }
}