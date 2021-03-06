package actions;

import models.User;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

public class BasicAuthAction extends Action.Simple {

    private static final String AUTHORIZATION = "Authorization";
    private static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    private static final String REALM = "Basic realm=\"Enter your credentials\"";

    @Override
    public Result call(Context context) throws Throwable {

	String authHeader = context.request().getHeader(AUTHORIZATION);
	if (authHeader == null) {
	    context.response().setHeader(WWW_AUTHENTICATE, REALM);
	    return unauthorized();
	}

	String auth = authHeader.substring(6);
	byte[] decodedAuth = new sun.misc.BASE64Decoder().decodeBuffer(auth);
	String[] credString = new String(decodedAuth, "UTF-8").split(":");

	if (credString == null || credString.length != 2) {
	    return unauthorized();
	}

	String username = credString[0];
	String password = credString[1];
	User authUser = User.authenticate(username, password);
	context.args.put("user", authUser);
	
	return (authUser == null) ? unauthorized() : delegate.call(context);
    }

}
