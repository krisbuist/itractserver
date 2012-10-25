package controllers;

import static play.libs.Json.toJson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class UserController extends Controller {

    public static Result getUsers() {
	List<User> users = User.find.all();

	LinkedHashMap<String, Object> details = new LinkedHashMap<String, Object>();
	List<LinkedHashMap<String, Object>> allUserDetails = new ArrayList<LinkedHashMap<String, Object>>();
	for (User user : users) {
	    LinkedHashMap<String, Object> oneUserDetails = new LinkedHashMap<String, Object>();
	    oneUserDetails.put("user_id", user.getId());
	    oneUserDetails.put("user_name", user.getName());
	    oneUserDetails.put("user_email", user.getEmail());
	    allUserDetails.add(oneUserDetails);
	}
	details.put("users", allUserDetails);
	return ok(toJson(details));
    }

    public static Result getUser(Integer id) {
	User user = User.find.byId(id);

	LinkedHashMap<String, Object> details = new LinkedHashMap<String, Object>();
	if (user != null) {
	    details.put("user_id", user.getId());
	    details.put("user_name", user.getName());
	    details.put("user_email", user.getEmail());
	    return ok(toJson(details));
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
	    details.put("user_name", user.getName());
	    details.put("user_email", user.getEmail());
	    return status(201, toJson(details));
	}
    }

    public static Result updateUser(Integer id) {
	User u = User.find.byId(id);
	if (u != null) {
	    Map<String, String[]> values = request().body().asFormUrlEncoded();

	    if (values.containsKey("name")) {
		u.setName(values.get("name")[0]);
	    }
	    if (values.containsKey("email")) {
		u.setEmail(values.get("email")[0]);
	    }

	    Form<User> form = form(User.class).fill(u);
	    
	    if (form.hasErrors()) {
		return badRequest();
	    } else {
		User updated = form.get();
		updated.save();
		return getUser(updated.getId());
	    }
	} else {
	    return notFound();
	}
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