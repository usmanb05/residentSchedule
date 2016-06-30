package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Controller;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 456729041278894510L;
	private UserPanel userPanel;
	private Controller controller;
	private ResidentTablePanel residentTablePanel;
	private RankingTablePanel rankingTablePanel;
	private LoginDialog loginDialog;
	
	
	public MainFrame() {
		super("Resident Ranking Form");
		
		//getContentPane().setLayout(new BorderLayout());
		
		JFrame frame = new JFrame();
		controller = new Controller();
		loginDialog = new LoginDialog(this);
		userPanel = new UserPanel();
		residentTablePanel = new ResidentTablePanel();
		rankingTablePanel = new RankingTablePanel();
		
		try {
			controller.connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainFrame.this, "Cannot connect to database", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
		}
		
		loginDialog.setModal(true);
		loginDialog.setVisible(true);
		loginDialog.setLocationRelativeTo(null);
		loginDialog.setLoginListener(new LoginListener() {
			public void loginSet(String email, String password) {
				try {
					if(controller.checkLogin(email, password)) {
						try {
							loginDialog.setVisible(false);
							setMinimumSize(new Dimension(900, 400));
							setSize(1200, 500);
							setVisible(true);
							frame.setLayout(new BorderLayout());
							add(userPanel, BorderLayout.WEST);
							add(residentTablePanel, BorderLayout.CENTER);
							controller.load();
							residentTablePanel.refresh();
							revalidate();
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(MainFrame.this, "Cannot load from database", "No data from Database", JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						loginDialog.setAlert("Incorrect Login, Try again");
					}
				} catch (SQLException e2) {
					System.out.println("Could not check login");
				}
			}
		});
		
		residentTablePanel.setData(controller.getPeople());
		residentTablePanel.setResidentTableListener(new ResidentTableListener() {
			public void rowDeleted(int row) {
				controller.removePerson(row);
			}
		});
		
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
		
		setMinimumSize(new Dimension(800, 600));
		setSize(1200, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
