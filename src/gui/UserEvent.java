package gui;

import java.math.BigInteger;
import java.util.EventObject;

import model.GeneratePassword;

public class UserEvent extends EventObject {
	
	private String username;
	private String email;
	
	public UserEvent(Object source) {
		super(source);
	}
	
	
	public UserEvent(Object source, String username, String email) {
		super(source);
		
		this.username = username;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
