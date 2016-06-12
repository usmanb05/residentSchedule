package gui;

import java.util.EventObject;

public class UserEvent extends EventObject {
	
	private String username;
	private String email;
	private String password;
	
	public UserEvent(Object source) {
		super(source);
	}
	
	public UserEvent(Object source, String username, String email) {
		super(source);
		
		this.username = username;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
