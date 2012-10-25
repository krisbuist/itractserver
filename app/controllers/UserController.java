package controllers;

import static play.libs.Json.toJson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;

public class UserController extends Controller {

    public static Result getUser(Integer id) {
	User user = User.find.byId(id);
	
	LinkedHashMap<String, Object> details = new LinkedHashMap<String, Object>();
	details.put("user_id", user.getUserId());
	details.put("user_name", user.getUserName());
	
	return ok(toJson(details));
    }

    public static Result getUsers() {
        List<User> users = User.find.all();
        
        LinkedHashMap<String, Object> details = new LinkedHashMap<String, Object>();
        List<LinkedHashMap<String, Object>> allUserDetails = new ArrayList<LinkedHashMap<String,Object>>();
        for (User user : users)
        {
            LinkedHashMap<String, Object> oneUserDetails = new LinkedHashMap<String, Object>();
            oneUserDetails.put("user_id", user.getUserId());
            oneUserDetails.put("user_name", user.getUserName());
            allUserDetails.add(oneUserDetails);
        }
        details.put("users", allUserDetails);
        return ok(toJson(details));
    }

    public static Result newUser() {
        return TODO;
    }

    public static Result updateUser(Integer id) {
        return TODO;
    }
    
    public static Result deleteUser(Integer id) {
	return TODO;
    }
}