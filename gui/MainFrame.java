package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.security.SecureRandom;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Controller;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 456729041278894510L;
	private UserPanel userPanel;
	private Controller controller;
	private ResidentTablePanel residentTablePanel;
	
	
	public MainFrame() {
		super("Resident Ranking Form");
		
		getContentPane().setLayout(new BorderLayout());
		
		controller = new Controller();
		
		userPanel = new UserPanel();
		residentTablePanel = new ResidentTablePanel();
		
		userPanel.setUserListener(new UserListener() {
			public void userEventOccurred(UserEvent e) {
				String userEmail = e.getEmail();
				
				try {
					if (controller.checkEmail(userEmail)) {
						JOptionPane.showMessageDialog(MainFrame.this, "User Email already in database", "Database Message", JOptionPane.ERROR_MESSAGE);
					}
					else {
						controller.addPerson(e);
						residentTablePanel.refresh();
						userPanel.setAlertField("User Added");
					}
				} catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		
		residentTablePanel.setData(controller.getPeople());
		
		residentTablePanel.setResidentTableListener(new ResidentTableListener() {
			public void rowDeleted(int row) {
				controller.removePerson(row);
			}
		});
		
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
