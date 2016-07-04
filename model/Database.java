package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {

private List<Resident> people;
private List<Ranking> rankings;
private Connection con;
	
	public Database () {
		people = new LinkedList<Resident>();
		rankings = new LinkedList<Ranking>();
	}
	
	public void connect() throws Exception {
		if (con != null) return;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Drive not found");
		}
		String url = "jdbc:mysql://localhost:3306/rotationusers?autoReconnect=true&useSSL=false";
		con = DriverManager.getConnection(url, "root", "uzzie");
		
		/* test for getting users from database;
		// Prints out users in name database;
		
		PreparedStatement statement = con.prepareStatement("select username from users");
		
		ResultSet result = statement.executeQuery();
		
		
		while(result.next())
		{
			System.out.println(result.getString(1) + " from Database class connect()");
		}
		
		statement.close();
		*/
		
	}
	

	public void load() throws SQLException {
		people.clear();
		
		String sql = "select username, email, password from users order by username";
		Statement selectStatement = con.createStatement();
		
		ResultSet results = selectStatement.executeQuery(sql);
		
		while(results.next()) {
			String name = results.getString("username");
			String email = results.getString("email");
			String password = results.getString("password");
			
			Resident user = new Resident(name, email, password);
			
			people.add(user);
		}
		//System.out.println(people + " - from Database load()");
		results.close();
		selectStatement.close();
	}
	
	public boolean isAdmin(String email) throws SQLException {
		String sql = "select email from users where email=?";
		PreparedStatement selectStatement = con.prepareStatement(sql);
		
		selectStatement.setString(1, email);
		
		ResultSet results = selectStatement.executeQuery();
		
		while(results.next()) {
			if(results.getString("email").equals("admin")) {
				return true;
			}
			else return false;
		}
			return false;	
		}
	
	public void loadRanks() throws SQLException {
		rankings.clear();
		
		String sql = "select allergy, pulmonary, cardiology, psychiatry, dermatology, endocrine, ent, genetics, gi, gynecology, hematology, idisease, neurology, ophthalmology, orthopedics, palliative, renal, rheumatology, sports, toxicology, sevenW, nineW from users where email=?";
		PreparedStatement selectStatement = con.prepareStatement(sql);
		
		selectStatement.setString(1, "admin");
		
		ResultSet results = selectStatement.executeQuery();
		
		while(results.next()) {
			int allergy = results.getInt("allergy");
			int pulmonary = results.getInt("pulmonary");
			int cardiology = results.getInt("cardiology");
			int psychiatry = results.getInt("psychiatry");
			int dermatology = results.getInt("dermatology");
			int endocrine = results.getInt("endocrine");
			int ent = results.getInt("ent");
			int genetics = results.getInt("genetics");
			int gi = results.getInt("gi");
			int gynecology = results.getInt("gynecology");
			int hematology = results.getInt("hematology");
			int idisease = results.getInt("idisease");
			int neurology = results.getInt("neurology");
			int ophthalmology = results.getInt("ophthalmology");
			int orthopedics = results.getInt("orthopedics");
			int palliative = results.getInt("palliative");
			int renal = results.getInt("renal");
			int rheumatology = results.getInt("rheumatology");
			int sports = results.getInt("sports");
			int toxicology = results.getInt("toxicology");
			int sevenW = results.getInt("sevenW");
			int nineW = results.getInt("nineW");
			
			Ranking ranks = new Ranking(allergy, pulmonary, cardiology, psychiatry, dermatology, endocrine, ent, genetics, gi, gynecology, hematology, idisease, neurology, ophthalmology, orthopedics, palliative, renal, rheumatology, sports, toxicology, sevenW, nineW);
			rankings.add(ranks);
		}
		//System.out.println(rankings + " - from Database loadRanks()");
		results.close();
		selectStatement.close();
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
		
		String username = "Amanda Musto";
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
		System.out.println(index);
		people.remove(index);
	}
	
	public void addPerson(Resident person) throws SQLException {
		String username = person.getUsername();
		String email = person.getEmail();
		String password = person.getPassword();
		
		String insertSql = "insert into users (username, email, password) values (?, ?, ?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);
		
		insertStatement.setString(1, username);
		insertStatement.setString(2, email);
		insertStatement.setString(3, password);
		
		insertStatement.executeUpdate();
		
		insertStatement.close();
		load();
	}
	
	public List<Resident> getPeople() {
		return Collections.unmodifiableList(people);
	}
	
	public List<Ranking> getRanking() {
		return Collections.unmodifiableList(rankings);
	}
	
	public boolean checkUserEmail(String userEmail) throws SQLException {
		String checkEmailSql = "select email from users where email=?";
		PreparedStatement checkStatement = con.prepareStatement(checkEmailSql);
		
		checkStatement.setString(1, userEmail);
		
		ResultSet result = checkStatement.executeQuery();
		if (result.next()) return true;
		
		else return false;
	}
	
	public boolean checkLogin(String email, String password) throws SQLException {
		String checkLoginSql= "select password from users where email=?";
		PreparedStatement checkStatement = con.prepareStatement(checkLoginSql);
		
		checkStatement.setString(1, email);
		
		ResultSet results = checkStatement.executeQuery();
		while (results.next()) {
			String dbPassword = results.getString("password");
			if (dbPassword.equals(password)) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
}
