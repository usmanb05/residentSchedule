package gui;

import java.util.EventObject;

import model.GeneratePassword;

public class UserEvent extends EventObject {
	
	private String username;
	private String email;
	private String password;
	GeneratePassword genPassword = new GeneratePassword(); 
	
	public UserEvent(Object source) {
		super(source);
	}
	
	
	public UserEvent(Object source, String username, String email, int password) {
		super(source);
		
		this.username = username;
		this.email = email;
		this.password = genPassword.randomString(password);
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
	
	public String getPassword() {
		return password;
	}

}
