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
private List<Survey> surveys;
private Connection con;
	
	public Database () {
		people = new LinkedList<Resident>();
		rankings = new LinkedList<Ranking>();
		surveys = new LinkedList<Survey>();
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
		surveys.clear();
		
		String sql = "select username, allergy, pulmonary, cardiology, psychiatry, dermatology, endocrine, ent, genetics, gi, gynecology, hematology, idisease, neurology, ophthalmology, orthopedics, palliative, renal, rheumatology, sports, toxicology, sevenW, nineW from users where submission=?";
		PreparedStatement selectStatement = con.prepareStatement(sql);
		final String[] fieldNames = {"allergy", "pulmonary", "cardiology", "psychiatry", "dermatology", "endocrine", "ent", "genetics", "gi", "gynecology", "hematology", "idisease", "neurology", "ophthalmology", "orthopedics", "palliative", "renal", "rheumatology", "sports", "toxicology", "sevenW", "nineW"};
		int[] data = new int[22];
		
		selectStatement.setInt(1, 1);
		
		ResultSet results = selectStatement.executeQuery();
		
		while(results.next()) {
			for (int i = 0; i < 22; i++) {
				data[i] = results.getInt(fieldNames[i]);
			}
			
			Ranking ranks = new Ranking(data);
			rankings.add(ranks);
			
			String name = results.getString("username");
			String one = "";
			String two = "";
			String three = "";
			String four = "";
			String five = "";
			String six = "";
			
			for (int i = 0; i < data.length; i++) {
				int value = 0;
				if (data[i] > 0) {
					value = data[i];
					
					switch(value) {
					case 1:
						one = fieldNames[i];
						break;
					case 2:
						two = fieldNames[i];
						break;
					case 3:
						three = fieldNames[i];
						break;
					case 4:
						four = fieldNames[i];
						break;
					case 5:
						five  = fieldNames[i];
						break;
					case 6:
						six = fieldNames[i];
						break;
					}
				}
			}
			Survey user = new Survey(name, one, two, three, four, five, six);
			surveys.add(user);
		}
		
		// for survey class
		
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
	
	public List<Survey> getSurveys() {
		return Collections.unmodifiableList(surveys);
	}
	
	public boolean checkUserEmail(String userEmail) throws SQLException {
		String checkEmailSql = "select email from users where email=?";
		PreparedStatement checkStatement = con.prepareStatement(checkEmailSql);
		
		checkStatement.setString(1, userEmail);
		
		ResultSet result = checkStatement.executeQuery();
		if (result.next()) return true;
		
		else return false;
	}
	
	public void submitFields(Ranking fields, String email) throws SQLException{

		String updataeSql = "update users set allergy=?, pulmonary=?, cardiology=?, psychiatry=?, dermatology=?, endocrine=?, ent=?, genetics=?, gi=?, gynecology=?, hematology=?, idisease=?, neurology=?, ophthalmology=?, orthopedics=?, palliative=?, renal=?, rheumatology=?, sports=?, toxicology=?, sevenW=?, nineW=?, submission=? where email=?";
		PreparedStatement updateStatement = con.prepareStatement(updataeSql);
		
		int col = 1;
		
			updateStatement.setInt(col++, fields.getAllergy());
			updateStatement.setInt(col++, fields.getPulmonary());
			updateStatement.setInt(col++, fields.getCardiology());
			updateStatement.setInt(col++, fields.getPsychiatry());
			updateStatement.setInt(col++, fields.getDermatology());
			updateStatement.setInt(col++, fields.getEndocrine());
			updateStatement.setInt(col++, fields.getEnt());
			updateStatement.setInt(col++, fields.getGenetics());
			updateStatement.setInt(col++, fields.getGi());
			updateStatement.setInt(col++, fields.getGynecology());
			updateStatement.setInt(col++, fields.getHematology());
			updateStatement.setInt(col++, fields.getId());
			updateStatement.setInt(col++, fields.getNeurology());
			updateStatement.setInt(col++, fields.getOphthalmology());
			updateStatement.setInt(col++, fields.getOrthopedics());
			updateStatement.setInt(col++, fields.getPalliative());
			updateStatement.setInt(col++, fields.getRenal());
			updateStatement.setInt(col++, fields.getRheumatology());
			updateStatement.setInt(col++, fields.getSports());
			updateStatement.setInt(col++, fields.getToxicology());
			updateStatement.setInt(col++, fields.getSevenW());
			updateStatement.setInt(col++, fields.getNineW());
			updateStatement.setInt(col++, 1);
			updateStatement.setString(col++, email);
			
			updateStatement.executeUpdate();
			updateStatement.close();
			
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
