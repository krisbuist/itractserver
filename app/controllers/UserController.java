package controllers;

import static play.libs.Json.toJson;

import java.util.List;

import models.*;
import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import actions.BasicAuthAction;
import flexjson.JSONSerializer;

public class UserController extends Controller {

    private static User activeUser() {
        Object u = ctx().args.get("user");
        if (u != null && u instanceof User) {
            return (User) u;
        }
        return null;
        }


        public static Result getUsers() {
        List<User> users = User.find.where().le("id", 20).findList();
        return ok(toJson(users));
    }

    @With(BasicAuthAction.class)
        public static Result getUser(Integer id) {
        User user = User.find.byId(id);
        if (user != null) {
            return ok(toJson(user));
        } else {
            return notFound();
        }
    }

    @BodyParser.Of(play.mvc.BodyParser.Json.class)
    public static Result newUser() {
        Form<User> userForm = form(User.class).bindFromRequest();

        if (userForm.hasErrors()) {
            return badRequest();
        }

        User newUser = userForm.get();
        newUser.setId(0);
        newUser.save();
        response().setContentType("application/json");
        return status(201, toJson(newUser));
    }

    @With(BasicAuthAction.class)
    @BodyParser.Of(play.mvc.BodyParser.Json.class)
    public static Result updateUser(Integer id) {
        User userToEdit = User.find.byId(id);

        if (userToEdit == null) {
            return notFound();
        }

        if (userToEdit.getId() != activeUser().getId()) {
            return unauthorized();
	}

	Form<User> userForm = form(User.class).bindFromRequest();

        if (userForm.hasErrors()) {
            return badRequest();
        }

        User editedUser = userForm.get();
        editedUser.setId(userToEdit.getId());
        editedUser.update();

        return status(200, toJson(editedUser));
    }

    @With(BasicAuthAction.class)
    public static Result deleteUser(Integer id) {
        User u = User.find.byId(id);

        if (u == null) {
            return notFound();
        }

        if (u.getId() != activeUser().getId()) {
            return unauthorized();
        }

        u.delete();
        return noContent();
    }

    public static Result getRequestsByUser(Integer id) {
        List<TripRequest> requests = TripRequest.find.join("user").where().eq("user.id", id).findList();

        JSONSerializer serializer = new JSONSerializer().exclude("*.class").include("*");

        response().setContentType("application/json");
        return ok(serializer.serialize(requests));
    }

    public static Result getOffersByUser(Integer id) {

        List<TripOffer> offers = TripOffer.find.join("user").where().eq("user.id", id).findList();

        JSONSerializer serializer = new JSONSerializer().exclude("class").include("*");

        response().setContentType("application/json");
        return ok(serializer.serialize(offers));
    }

    public static Result getMatchesByUser(Integer id) {
        List<TripMatch> matches = TripMatch.find.join("tripRequest").where().eq("tripRequest.user.id", id).findList();

        JSONSerializer serializer = new JSONSerializer().exclude("class").exclude("*.password").include("*");

        response().setContentType("application/json");
        return ok(serializer.serialize(matches));
    }

    @With(BasicAuthAction.class)
    public static Result doLogin() {
        User user = activeUser();
    	return ok(toJson(user));
    }

    @With(BasicAuthAction.class)
    @BodyParser.Of(play.mvc.BodyParser.Json.class)
    public static Result doLoginWithDeviceId()
    {
        User user = activeUser();

        user.setDeviceID(request().body().asJson().get("deviceID").asText());
        user.update();
        return ok(toJson(user));
    }

	//@With(BasicAuthAction.class)
    public static Result getNotifications(){
        //List<Notification> notifications = Notification.find.where().eq("user",activeUser()).findList();
        List<Notification> notifications = Notification.find.findList();

        JSONSerializer serializer = new JSONSerializer().exclude("tripMatch.tripRequest.matches").exclude("*.password").include("*");
        response().setContentType("application/json");
        response().setHeader("Access-Control-Allow-Headers", "Content-Type");
        response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        response().setHeader("Access-Control-Allow-Origin", "*");
        response().setHeader("Access-Control-Request-Headers", "origin, content-type, accept");
        response().setHeader("Access-Control-Max-Age", "60");
        return ok(serializer.serialize(notifications));
    }
}