package controllers;

import models.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Auth extends Controller {

	// Страница формы для аутентификации
    public static Result login() {
    	if(session("email") != null) return redirect(routes.Application.index());
    	else return ok(login.render(Form.form(Login.class), false));
    }
    
    // Обработка формы аутентификации
    public static Result auth() {
    	Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
    	if(loginForm.hasErrors()) return badRequest(login.render(loginForm, false));
    	else {
    		if(loginForm.get().validateUser() == null) return redirect(routes.Application.index());
    		else return badRequest(login.render(loginForm, true));
    	}
    }
    
    // Выход и очистка сессии
    public static Result logout() {
    	session().clear();
    	return redirect(routes.Application.index());
    }
    
    // Страница регистрации
    public static Result signup() {
    	if(session("email") != null) return redirect(routes.Application.index());
    	return ok(register.render(Form.form(Login.class).fill(new Login()), false));
    }
    
    // Регистрация
    public static Result register() {
    	Form<Login> regForm = Form.form(Login.class).bindFromRequest();
    	if(regForm.hasErrors()) return badRequest(register.render(regForm, false));
    	else if(!User.exists(regForm.get())) {
    		(new User(regForm.get())).save();
    		User.authenticate(regForm.get().email, regForm.get().password);
    		return redirect(routes.Application.index());
    	} else return badRequest(register.render(regForm, true));
    }
}
