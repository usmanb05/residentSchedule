package model;

public class Resident {
	private static int count = 1;
	private int id;
	private String username;
	private String email;
	private String password;
	
	public Resident (String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
		
		this.id = count;
		count++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
