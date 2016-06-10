package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class UserPanel extends JPanel {
	
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel emailLabel;
	
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField emailField;
	
	private JButton addUserBtn;
	
	public UserPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		firstNameLabel = new JLabel("First Name: ");
		lastNameLabel = new JLabel("Last Name: ");
		emailLabel = new JLabel("Email Address: ");
		firstNameField = new JTextField(10);
		lastNameField = new JTextField(10);
		emailField = new JTextField(10);
		addUserBtn = new JButton("Add User");
		
		
		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outBorder, innerBorder));
		layoutComponents();
		
		
	}
	
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		
		
		// First row
		gc.gridy = 0;
		gc.gridx = 0;
		gc.weighty = 0.2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(firstNameLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(firstNameField, gc);
		
		// Second row
		gc.gridy++;
		gc.gridx = 0;
		gc.weighty = 0.2;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lastNameLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lastNameField, gc);
		
		// Third row
		gc.gridy++;
		gc.gridx = 0;
		gc.weighty = 0.2;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(emailLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(emailField, gc);
		
		// Fourth row
		gc.gridy++;
		gc.gridx = 1;
		gc.weighty = 1.8;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(addUserBtn, gc);
		
	}
}
