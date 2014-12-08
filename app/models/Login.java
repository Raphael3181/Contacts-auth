package models;

import javax.persistence.Id;

import play.data.validation.Constraints.*;
import play.db.ebean.Model;

public class Login extends Model {
	
	@Id
	public Long id;
	@Email
	@Required
	public String email;
	@Required
	public String password;
	
	// Валидация
	public String validateUser() { return User.authenticate(email, password); }
}
