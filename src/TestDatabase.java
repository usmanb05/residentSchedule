import java.sql.SQLException;

import model.Database;

public class TestDatabase {

	public static void main(String[] args) {
		System.out.println("Running test database");
		
		Database db = new Database();
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
		db.disconnect();
	}

}
