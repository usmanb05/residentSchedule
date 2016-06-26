package controller;

import java.sql.SQLException;
import java.util.List;

import gui.UserEvent;
import model.Database;
import model.GeneratePassword;
import model.Resident;
import model.SendEmail;

public class Controller {
	Database db = new Database();
	GeneratePassword password = new GeneratePassword(); 
	SendEmail emailPass = new SendEmail();
	
	
	public List<Resident> getPeople() {
		return db.getPeople();
	}
	
	public void connect() throws Exception {
		db.connect();
	}
	
	public void save() throws SQLException {
		db.save();
	}
	
	public void disconnect() {
		db.disconnect();
	}
	
	public void load() throws SQLException {
		db.load();
	}
	
	public boolean checkEmail(String userEmail) throws SQLException {
		return db.checkUserEmail(userEmail);
	}
	
	
	public void removePerson(int index) {
		db.removePerson(index);
	}

	public void addPerson(UserEvent e) {
		String name = e.getUsername();
		String email = e.getEmail();
		String newPassword = password.randomString(10);
		
		Resident resident = new Resident(name, email, newPassword);
		try {
			db.addPerson(resident);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		/*
		for(Resident resident1 : db.getPeople()){
		    System.out.println(resident1.getUsername() + " " + resident1.getEmail() + " " +resident1.getPassword() + " from Controller Class");
		}
		*/
		
		//System.out.println(resident.getUsername() + " " + resident.getEmail() + " " + password + " from Controller Class");
	}
	
	public void emailPassword(String email, String password) {
		emailPass.send(email, password);
	}
	
	
	

}
