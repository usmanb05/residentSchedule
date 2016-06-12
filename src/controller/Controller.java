package controller;

import java.util.List;

import gui.UserEvent;
import model.Database;
import model.Resident;

public class Controller {
	Database db = new Database();
	
	public List<Resident> getPeople() {
		return db.getPeople();
	}
	
	public void removePerson(int index) {
		db.removePerson(index);
	}

	public void addPerson(UserEvent e) {
		String name = e.getUsername();
		String email = e.getEmail();
		
		Resident resident = new Resident(name, email);
		db.addPerson(resident);
		
		/*
		for(Resident resident1 : db.getPeople()){
		    System.out.println(resident1.getUsername() + " " + resident1.getEmail() + " from Controller Class");
		}
		*/
		
		System.out.println(resident.getUsername() + " " + resident.getEmail() + " from Controller Class");
	}
}
