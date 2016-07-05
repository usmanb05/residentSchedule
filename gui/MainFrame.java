package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Controller;
import model.Ranking;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 456729041278894510L;
	private UserPanel userPanel;
	private Controller controller;
	private ResidentTablePanel residentTablePanel;
	private RankingTablePanel rankingTablePanel;
	private LoginDialog loginDialog;
	private SurveyPanel surveyPanel;
	private String userEmail;
	
	
	public MainFrame() {
		super("Resident Ranking Form");
		
		//getContentPane().setLayout(new BorderLayout());
		setLayout(new BorderLayout());
		
		controller = new Controller();
		loginDialog = new LoginDialog(this);
		userPanel = new UserPanel();
		surveyPanel = new SurveyPanel();
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
							userEmail = email;
							loginDialog.setVisible(false);
							boolean isAdmin = controller.isAdmin(email);
							if (isAdmin) {
								setAdminView();
								loadData();
							}
							else {
								setUserView();
							}
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
		
		surveyPanel.setSurveyListener(new SurveyListener() {
			public void surveyEventOccurred(Ranking ranks) {
				try {
					controller.submitFields(ranks, userEmail);
					surveyPanel.setInfoArea("Scheduling Preferences Submitted");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		});
				
		
		residentTablePanel.setData(controller.getPeople());
		rankingTablePanel.setData(controller.getRanking());
		
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
		
		setMinimumSize(new Dimension(1600, 1000));
		setSize(1600, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setUserView() {
		setMinimumSize(new Dimension(400, 600));
		setSize(400, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		add(surveyPanel, BorderLayout.WEST);
	}
	
	public void setAdminView() {
		setMinimumSize(new Dimension(1600, 800));
		setSize(1600, 800);
		setVisible(true);
		
		add(userPanel, BorderLayout.WEST);
		add(residentTablePanel, BorderLayout.CENTER);
		
		rankingTablePanel.setPreferredSize(new Dimension (1600, 100));
		add(rankingTablePanel, BorderLayout.SOUTH);
		add(surveyPanel, BorderLayout.EAST);
	}
	
	public void loadData() {
		try {
			controller.load();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			controller.loadRanks();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		residentTablePanel.refresh();
		//revalidate();
	}
}
