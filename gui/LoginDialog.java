package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class LoginDialog extends JDialog{
	private JButton okButton;
	private JButton cancelButton;
	private JTextField userField;
	private JPasswordField passField;
	private LoginListener loginListener;
	private JTextArea alertField;
	
	
	public LoginDialog(JFrame parent) {
		super(parent, "Login", false);
		
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		
		alertField = new JTextArea();
		userField = new JTextField(10);
		passField = new JPasswordField(10);
		
		passField.setEchoChar('*');
		
		layoutControls();
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = userField.getText();
				char[] password = passField.getPassword();
				
				if(loginListener != null) {
					loginListener.loginSet(user, new String(password));
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				System.exit(0);
			}
		});
		
		passField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER && !(userField.getText().length() == 0) && !(passField.getPassword().length == 0)) {
					String user = userField.getText();
					char[] password = passField.getPassword();
					
					if(loginListener != null) {
						loginListener.loginSet(user, new String(password));
					}
				}
			}
		});
		
		setSize(300, 200);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	private void layoutControls() {
		
		JPanel controlsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		int space = 15;
		Border outerBorder = BorderFactory.createEmptyBorder(space, space, space, space);
		Border titleBorder = BorderFactory.createTitledBorder("");
		
		controlsPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, titleBorder));
		
		controlsPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.gridx = 0;
		gc.gridy = 0;
		
		Insets rightPadding = new Insets(0, 0, 0, 15);
		Insets noPadding = new Insets(0, 0 , 0, 0);
		
		// first row
		gc.anchor = GridBagConstraints.SOUTHEAST;
		gc.insets = rightPadding;
		controlsPanel.add(new JLabel("Username: "), gc);
		gc.gridx++;
		gc.anchor = GridBagConstraints.SOUTHWEST;
		gc.insets = noPadding;
		controlsPanel.add(userField, gc);
		
		// next row
		gc.gridx--;
		gc.gridy++;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 15);
		gc.insets = rightPadding;
		controlsPanel.add(new JLabel("Password: "), gc);
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		controlsPanel.add(passField, gc);
		
		/// Buttons Panel
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		gc.gridx = 0;
		gc.gridy++;
		
		buttonsPanel.add(alertField);
		
		gc.gridx++;
		buttonsPanel.add(okButton);
		
		gc.gridx++;
		buttonsPanel.add(cancelButton);
		
		Dimension btnSize = cancelButton.getPreferredSize();
		okButton.setPreferredSize(btnSize);
		
		// add sub panels to dialog
		setLayout(new BorderLayout());
		add(controlsPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}

	public void setAlert(String text) {
		alertField.setText(text);
	}
	public void setLoginListener(LoginListener listener) {
			this.loginListener = listener;
	}
}
