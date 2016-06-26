package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class UserPanel extends JPanel {
	
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel emailLabel;
	
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField emailField;
	
	private UserListener userListener;
	
	private JTextArea alertField;
	private JButton addUserBtn;
	
	public UserPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 280;
		setPreferredSize(dim);
		
		firstNameLabel = new JLabel("First Name: ");
		lastNameLabel = new JLabel("Last Name: ");
		emailLabel = new JLabel("Email Address: ");
		firstNameField = new JTextField(10);
		lastNameField = new JTextField(10);
		emailField = new JTextField(10);
		alertField = new JTextArea();
		addUserBtn = new JButton("Add User");
		
		alertField.setForeground(Color.red);
		
		addUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String name = firstNameField.getText() + " " + lastNameField.getText();
				String email = emailField.getText();
				
				if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || emailField.getText().isEmpty()) {
					System.out.println("empty fields from UserPanel class");
					alertField.setText("Please fill out all fields");
				}
				
				else if(!isValidEmailAddress(emailField.getText())) {
					System.out.println("Invalid email address from UserPanel class");
					alertField.setText("Please enter valid email address");
				}
				
				else {
					UserEvent ev = new UserEvent(this, name, email);
					if (userListener != null) {
						userListener.userEventOccurred(ev);
						resetTextFields();
					}
				}
			}
		});
		
		firstNameField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				alertField.setText("");
			}
		});
		
		lastNameField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				alertField.setText("");
			}
		});
		
		emailField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				alertField.setText("");
			}
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("Add Resident");
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
		gc.insets = new Insets(25, 0, 0, 5);
		add(firstNameLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(25, 0, 0, 0);
		add(firstNameField, gc);
		
		// Next row
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
		
		// Next row
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
		
		// Next row
		gc.gridy++;
		gc.gridx = 1;
		gc.weighty = 0.2;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 10, 0, 30);
		add(addUserBtn, gc);
		
		// Next row
		gc.gridy++;
		gc.weighty = 1.8;
		gc.fill = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(0, 10, 10, 30);
		gc.gridwidth = 2;
		gc.gridx = 0;
		//gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(alertField, gc);
		
	}
	
	public void setUserListener(UserListener listener) {
		this.userListener = listener;
	}
	
	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	
	public void resetTextFields() {
		firstNameField.setText("");
		lastNameField.setText("");
		emailField.setText("");
		//alertField.setText("User submitted");
	}
	
	public void setAlertField(String text) {
		alertField.setText(text);
	}

}
