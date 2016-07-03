import java.sql.SQLException;

import javax.swing.JOptionPane;

import gui.MainFrame;
import model.Database;
import model.SendEmail;

public class TestDatabase {

	public static void main(String[] args) {
		System.out.println("Running test database");
		
		Database db = new Database();
		//SendEmail email = new SendEmail();
		
		
		try {
			db.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(db.checkLogin("admin", "admin")) {
				try {
					System.out.println("yes");
					db.load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else {
				System.out.println("wrong login");
			}
		} catch (SQLException e2) {
			System.out.println("Could not check login");
		}
		
		try {
			db.loadRanks();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		try {
			db.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			db.save();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			db.load();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			boolean check = db.checkUserEmail("amusto711@gmail.com");
			System.out.println(check);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		db.disconnect();
		*/
	}

}
