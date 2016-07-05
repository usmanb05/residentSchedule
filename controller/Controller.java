package controller;

import java.sql.SQLException;
import java.util.List;

import gui.UserEvent;
import model.Database;
import model.GeneratePassword;
import model.Ranking;
import model.Resident;
import model.SendEmail;

public class Controller {
	Database db = new Database();
	SendEmail emailPass = new SendEmail();
	
	
	public List<Resident> getPeople() {
		return db.getPeople();
	}
	
	public List<Ranking> getRanking() {
		return db.getRanking();
	}
	
	public void connect() throws Exception {
		db.connect();
	}
	
	public void save() throws SQLException {
		db.save();
	}
	
	public boolean checkLogin(String email, String password) throws SQLException {
		return db.checkLogin(email, password);
	}
	
	public void disconnect() {
		db.disconnect();
	}
	
	public void load() throws SQLException {
		db.load();
	}
	
	public void loadRanks() throws SQLException {
		db.loadRanks();
	}
	
	public boolean checkEmail(String userEmail) throws SQLException {
		return db.checkUserEmail(userEmail);
	}
	
	public boolean isAdmin(String email) throws SQLException {
		return db.isAdmin(email);
	}
	
	public void removePerson(int index) {
		db.removePerson(index);
	}
	
	public void submitFields(Ranking fields, String email) throws SQLException {
		db.submitFields(fields, email);
	}

	public void addPerson(UserEvent e) {
		String name = e.getUsername();
		String email = e.getEmail();
		String password = e.getPassword();
		
		Resident resident = new Resident(name, email, password);
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
	
	public void emailPassword(String name, String email, String password) {
		emailPass.send(name, email, password);
	}

		
	

}
