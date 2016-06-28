package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Controller;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 456729041278894510L;
	private UserPanel userPanel;
	private Controller controller;
	private ResidentTablePanel residentTablePanel;
	private LoginDialog loginDialog;
	private Preferences prefs;
	
	
	public MainFrame() {
		super("Resident Ranking Form");
		
		getContentPane().setLayout(new BorderLayout());
		
		controller = new Controller();
		loginDialog = new LoginDialog(this);
		userPanel = new UserPanel();
		residentTablePanel = new ResidentTablePanel();
		
		prefs = Preferences.userRoot().node("db");
		loginDialog.setLocationRelativeTo(null);
		
		userPanel.setUserListener(new UserListener() {
			public void userEventOccurred(UserEvent e) {
				String name = e.getUsername();
				String userEmail = e.getEmail();
				String password = e.getPassword();
				
				try {
					if (controller.checkEmail(userEmail)) {
						JOptionPane.showMessageDialog(MainFrame.this, "User Email already in database", "Database Message", JOptionPane.ERROR_MESSAGE);
					}
					else {
						userPanel.setAlertField("User Added");
						controller.addPerson(e);
						residentTablePanel.refresh();
						controller.emailPassword(name, userEmail, password);
					}
				} catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		loginDialog.setLoginListener(new LoginListener() {

			public void loginSet(String user, String password) {
				prefs.put("user", user);
				prefs.put("password", password);
			}
		});
		
		String user = prefs.get("user", "");
		String password = prefs.get("password", "");
		
		loginDialog.setDefaults(user, password);
		
		residentTablePanel.setData(controller.getPeople());
		
		residentTablePanel.setResidentTableListener(new ResidentTableListener() {
			public void rowDeleted(int row) {
				controller.removePerson(row);
			}
		});
		
		loginDialog.setModal(true);
		loginDialog.setVisible(true);
		add(userPanel, BorderLayout.WEST);
		add(residentTablePanel, BorderLayout.CENTER);
		setMinimumSize(new Dimension(500, 400));
		setSize(1200, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		try {
			controller.connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainFrame.this, "Cannot connect to database", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
		}
		
		try {
			controller.load();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(MainFrame.this, "Cannot load from database", "No data from Database", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
	}
	
}
