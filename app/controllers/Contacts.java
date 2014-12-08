package controllers;

import models.Contact;
import play.data.Form;
import play.mvc.*;
import views.html.*;

@Security.Authenticated(Secured.class)
public class Contacts extends Controller {
	static Form<Contact> ContactForm = Form.form(Contact.class);
	
	public static Result newContact() {
		Form<Contact> filledForm = ContactForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			System.out.println("errors");
			return badRequest(index.render(Contact.all(), filledForm));
		} else {
			System.out.println("saving");
			filledForm.get().save();
			return redirect(routes.Application.contacts());
		}
	}

	public static Result editContact() {
		Form<Contact> filledForm = ContactForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(edit.render(filledForm));
		} else {
			filledForm.get().update();
			return redirect(routes.Application.contacts());
		}
	}

	public static Result openContact(Long id) {
		return ok(edit.render(ContactForm.fill(Contact.byId(id))));
	}

	public static Result deleteContact(Long id) {
		Contact.delete(id);
		return redirect(routes.Application.contacts());
	}
}
