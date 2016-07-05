package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import model.Ranking;

public class SurveyPanel extends JPanel {
	private JTextArea infoArea;
	private JButton submitBtn;
	private final int totalForms = 22;
	private SurveyListener surveyListener;
	private SpinnerNumberModel spinnerModel;
	
	private JLabel[] labels = new JLabel[totalForms];
	private JTextField[] fields = new JTextField[totalForms];
	
	
	private String[] labelNames = {"Allergy", "Pulmonary", "Cardiology", "Psychiatry", "Dermatology", "Endocrine", "ENT", "Genetics", "GI", "Gynecology", "Hematology", "ID", "Neurology", "Ophthalmology", "Orthopedics", "Palliative Care", "Renal", "Rheumatology", "Sports Medicine", "Toxicology", "7W", "9W"};

	public SurveyPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 400;
		//dim.height = 300;
		setPreferredSize(dim);
		
		infoArea = new JTextArea();
		submitBtn = new JButton("Submit");
		
		listeners();
		layoutComponents();
		
		Border innerBorder = BorderFactory.createTitledBorder("Fill Out Form");
		Border outBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outBorder, innerBorder));
	}
	
	public boolean validateCharacters(String text) {
		for (int i = 0; i < text.length(); i++) {
				char[] chars = text.toCharArray();
				for (char c : chars) {
					if (Character.isLetter(c)) {
						return false;
					}
					else return true;
				}
		}
		return false;
	}
	
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.gridy = 0;
		gc.gridx = 0;
		
		for (int i = 0; i < labelNames.length; i++) {
			
			labels[i] = new JLabel(labelNames[i] + ": ");
			fields[i] = new JTextField(5);

			
			gc.gridy++;
			gc.gridx = 0;
			gc.anchor = GridBagConstraints.LINE_END;
			gc.insets = new Insets(0, 0, 0, 5);
			add(labels[i], gc);
			
			gc.gridx++;
			gc.anchor = GridBagConstraints.LINE_START;
			gc.insets = new Insets(0, 0, 0, 5);
			add(fields[i], gc);
		}
		
		// alert field and button row
		gc.weighty = 3;
		gc.gridx = 0;
		gc.gridy++;
		infoArea.setEditable(false);
		infoArea.setSize(getPreferredSize());
		infoArea.setBackground(getBackground());
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(16, 20, 0, 5);
		add(infoArea, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(10, 0, 0, 5);
		add(submitBtn, gc);
		
	}
	
	public void setSurveyListener(SurveyListener listener) {
		this.surveyListener = listener;
	}
	
	public void listeners() {
		
		
		
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				infoArea.setText("");
				int[] formIntArray = new int[totalForms];
				boolean ready = false;
				
				for (int i = 0; i < totalForms; i++) {
					if (!validateCharacters(fields[i].getText())) {
						infoArea.setText("Please enter only numerical values.");
					}
					else {
						if (fields[i].getText().isEmpty()) {
							formIntArray[i] = 0;
						}
						else {
							formIntArray[i] = Integer.parseInt(fields[i].getText());
							ready = true;
						}
					}
				}
				if (ready) {
					if (surveyListener != null) {
						Ranking ranks = new Ranking(formIntArray);
						surveyListener.surveyEventOccurred(ranks);
					}
				}
				
			}
		});
	}
	
	public void setInfoArea(String text) {
		infoArea.setText(text);
	}
}
