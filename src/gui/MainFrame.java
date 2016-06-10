package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 456729041278894510L;
	private UserPanel userPanel;
	
	public MainFrame() {
		super("Resident Ranking Form");
		
		getContentPane().setLayout(new BorderLayout());
		
		userPanel = new UserPanel();
		
		add(userPanel, BorderLayout.WEST);
		setMinimumSize(new Dimension(500, 400));
		setSize(1200, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	

}
