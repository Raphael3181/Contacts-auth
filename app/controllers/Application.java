package controllers;

import play.mvc.*;
import play.data.Form;

import models.Contact;;

public class Application extends Controller {
	
	public static Result index() {
		return redirect(routes.Application.contacts());
	}

	public static Result contacts() {
		return ok(views.html.index.render(Contact.all(), Form.form(Contact.class)));
	}
}