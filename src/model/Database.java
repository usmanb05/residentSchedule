package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {

private List<Resident> people;
private Connection con;
	
	public Database () {
		people = new LinkedList<Resident>();
	}
	
	public void connect() throws Exception {
		if (con != null) return;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Drive not found");
		}
		//Creating a variable for the connection called "con"
		String url = "jdbc:mysql://localhost:3306/rotationusers?autoReconnect=true&useSSL=false";
		con = DriverManager.getConnection(url, "root", "uzzie");
		
		
		// Prints out users in name database;
		
		PreparedStatement statement = con.prepareStatement("select username from users");
		
		ResultSet result = statement.executeQuery();
		
		while(result.next())
		{
			System.out.println(result.getString(1) + " ");
		}
		
		statement.close();
		
	}
	
	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Can't close connection");
			}
		}
	}
	
	public void save() throws SQLException {
		String checkSqlEmail = "select email from users where username=?";
		PreparedStatement statement = con.prepareStatement(checkSqlEmail);
		
		String username = "connor";
		statement.setString(1, username);
		
		ResultSet checkResult = statement.executeQuery();
		
		if(!checkResult.isBeforeFirst()) {
			System.out.println("no email");
		}
		
		while(checkResult.next())
		{
			System.out.println("Email: " + checkResult.getString(1));
			
		}
		
		statement.close();
		
	}
	
	public void removePerson(int index) {
		people.remove(index);
	}
	
	public void addPerson(Resident person) {
		people.add(person);
	}
	
	public List<Resident> getPeople() {
		return Collections.unmodifiableList(people);
	}
}
