package models;

import java.security.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import models.Login;
import play.data.validation.Constraints.Email;
import play.db.ebean.Model;
import play.mvc.Controller;

@Entity
public class User extends Model {
	
	@Id
	@Email
	public String email;
	@NotNull
	private String hash;
	
	public User(Login l) { email = l.email; hash = SHA256(l.password); }
	
	private static Finder<String, User> find = new Finder<String, User>(String.class, User.class);
	
	// Аутентификация
	public static String authenticate(String email, String password) {
		User user = find.byId(email);
		if(user != null && SHA256(password).equals(user.hash)) {
			Controller.session("email", email);
			Controller.session("hash", user.hash);
			return null;
		} return "failed";
	}
	
	// Аутентификация через куки
	public static String authcookies() {
		User user = find.byId(Controller.session("email"));
		if(user != null && Controller.session("hash").equals(user.hash)) return user.email;
		else return null;
	}
	
	public static User byId(String email) { return find.byId(email); }
	
	public static boolean exists(Login l) { return find.byId(l.email) != null; }
	
	private static String SHA256(String str) {
		try { MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(str.getBytes());
			return new java.math.BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) { return str; }
	}
}
