package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

// this class can eventually be removed, but we're keeping it now because it can be convenient for trying out things
// TODO: remove class
public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }


}