package controller;

import java.security.SecureRandom;
import java.util.List;

import gui.UserEvent;
import model.Database;
import model.Resident;

public class Controller {
	Database db = new Database();
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();
	
	public List<Resident> getPeople() {
		return db.getPeople();
	}
	
	public void removePerson(int index) {
		db.removePerson(index);
	}

	public void addPerson(UserEvent e) {
		String name = e.getUsername();
		String email = e.getEmail();
		String password = randomString(10);
		
		Resident resident = new Resident(name, email, password);
		db.addPerson(resident);
		
		
		for(Resident resident1 : db.getPeople()){
		    System.out.println(resident1.getUsername() + " " + resident1.getEmail() + " " +resident1.getPassword() + " from Controller Class");
		}
		
		
		//System.out.println(resident.getUsername() + " " + resident.getEmail() + " " + password + " from Controller Class");
	}
	
	public String randomString( int len ){
		   StringBuilder sb = new StringBuilder( len );
		   for( int i = 0; i < len; i++ ) 
		      sb.append(AB.charAt(rnd.nextInt(AB.length()) ) );
		   return sb.toString();
		}

}
