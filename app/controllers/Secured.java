package controllers;

import models.User;
import controllers.routes;
import play.mvc.*;
import play.mvc.Http.Context;

public class Secured extends Security.Authenticator {
	public String getUsername(Context ctx) {
		if(ctx.session().get("email") != null) return User.authcookies();
		else return null;
	}
	public Result onUnauthorized(Context ctx) { return redirect(routes.Auth.login()); }
}