package models;

import java.util.*;
import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Contact extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@Required
	public String name;
	public String homephone;
	public String mobilephone;

	public static Finder<Long, Contact> find = new Finder<Long, Contact>(Long.class, Contact.class);

	public static List<Contact> all() {
		return find.all();
	}
	
	public static Contact byId(Long Id) {
		return find.byId(Id);
	}
	
	public static void delete(Long id) {
		find.ref(id).delete();
	}
}