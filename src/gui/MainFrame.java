package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

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
				controller.addPerson(e);
				residentTablePanel.refresh();
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
	}
	

}
