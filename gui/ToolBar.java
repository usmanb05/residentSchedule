package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ToolBar extends JPanel implements ActionListener{
	private JButton refreshButton;
	private JButton logoutButton;
	
	private ToolbarListener textListener;
	
	public ToolBar() {
		setBorder(BorderFactory.createEtchedBorder());
		
		refreshButton = new JButton("Refresh");
		refreshButton.setIcon(creatIcon("/images/Refresh16.gif"));
		logoutButton = new JButton("Logout");
		
		refreshButton.addActionListener(this);
		logoutButton.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(refreshButton);
		add(logoutButton);
	}
	
	private ImageIcon creatIcon(String path) {
		URL url = getClass().getResource(path);
		
		if(url == null) System.out.println("Unable to load image: " + path);
		
		ImageIcon icon = new ImageIcon(url);
		
		return icon;
	}

	public void setToolbarListener(ToolbarListener listener) {
		this.textListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		
		if(clicked == refreshButton) {
			if(textListener != null) {
				textListener.refreshEventOccured();
			}
		}
		else if(clicked == logoutButton) {
			if(textListener != null) {
				textListener.logoutEventOccured();
			}
		}

	}
}
