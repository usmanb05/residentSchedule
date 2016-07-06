package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

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
	private JSplitPane splitPane;
	private ToolBar toolBar;
	private JFileChooser fileChooser;
	
	
	public MainFrame() {
		super("Resident Ranking Form");
		
		setLayout(new BorderLayout());
		
		controller = new Controller();
		toolBar = new ToolBar();
		loginDialog = new LoginDialog(this);
		userPanel = new UserPanel();
		surveyPanel = new SurveyPanel();
		residentTablePanel = new ResidentTablePanel();
		rankingTablePanel = new RankingTablePanel();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, userPanel, residentTablePanel);
		splitPane.setOneTouchExpandable(true);
		
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
		rankingTablePanel.setSurveyData(controller.getSurveys());
		
		residentTablePanel.setResidentTableListener(new ResidentTableListener() {
			public void rowDeleted(int row) {
				controller.removePerson(row);
			}
		});
		
		toolBar.setToolbarListener(new ToolbarListener() {
			public void logoutEventOccured() {
				controller.disconnect();
				System.exit(1);
			}

			public void refreshEventOccured() {
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
				rankingTablePanel.refresh();
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
		setJMenuBar(createMenuBar());
		
		//add(userPanel, BorderLayout.WEST);
		add(splitPane, BorderLayout.CENTER);
		add(toolBar, BorderLayout.NORTH);
	
		
		rankingTablePanel.setPreferredSize(new Dimension (1600, 400));
		add(rankingTablePanel, BorderLayout.SOUTH);
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
		rankingTablePanel.refresh();
		//revalidate();
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar(); 
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem exportDataItem = new JMenuItem("Export Data to PDF...");
		JMenuItem exitItem = new JMenuItem("Logout");
		
		//fileMenu.add(exportDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		exitItem.setMnemonic(KeyEvent.VK_X);
		
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile());
				};
			}
			
		});
		
		menuBar.add(fileMenu);
		return menuBar;
	}
}
