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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SurveyPanel extends JPanel {
	private JTextArea infoArea;
	private JButton submitBtn;
	private final int totalForms = 22;
	private SurveyListener surveyListener;
	
	private JLabel[] labels = new JLabel[totalForms];
	private JTextField[] fields = new JTextField[totalForms];
	
	private String[] names = {"Allergy", "Pulmonary", "Cardiology", "Psychiatry", "Dermatology", "Endocrine", "ENT", "Genetics", "GI", "Gynecology", "Hematology", "ID", "Neurology", "Ophthalmology", "Orthopedics", "Palliative Care", "Renal", "Rheumatology", "Sports Medicine", "Toxicology", "7W", "9W"};
	
	
	private JLabel allergyLabel;
	private JLabel pulmonaryLabel;
	private JLabel cardiologyLabel;
	private JLabel psychiatryLabel;
	private JLabel dermatologyLabel;
	private JLabel endocrineLabel;
	private JLabel entLabel;
	private JLabel geneticsLabel;
	private JLabel giLabel;
	private JLabel gynecologyLabel;
	private JLabel hematologyLabel;
	private JLabel idiseaseLabel;
	private JLabel neurologyLabel;
	private JLabel ophthalmologyLabel;
	private JLabel orthopedicsLabel;
	private JLabel palliativeLabel;
	private JLabel renalLabel;
	private JLabel rheumatologyLabel;
	private JLabel sportsLabel;
	private JLabel toxicologyLabel;
	private JLabel sevenWLabel;
	private JLabel nineWLabel;
	
	private JTextField allergyField;
	private JTextField pulmonaryField;
	private JTextField cardiologyField;
	private JTextField psychiatryField;
	private JTextField dermatologyField;
	private JTextField endocrineField;
	private JTextField entField;
	private JTextField geneticsField;
	private JTextField giField;
	private JTextField gynecologyField;
	private JTextField hematologyField;
	private JTextField idiseaseField;
	private JTextField neurologyField;
	private JTextField ophthalmologyField;
	private JTextField orthopedicsField;
	private JTextField palliativeField;
	private JTextField renalField;
	private JTextField rheumatologyField;
	private JTextField sportsField;
	private JTextField toxicologyField;
	private JTextField sevenWField;
	private JTextField nineWField;
	
	
	public SurveyPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);
		
		infoArea = new JTextArea();
		submitBtn = new JButton("Submit");
		
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] formArray = new String[totalForms];
				
				for (int i = 0; i < totalForms; i++) {
					formArray[i] = fields[i].getText();
					System.out.print(formArray[i] + " ");
				}
				
				if (surveyListener != null) {
					surveyListener.surveyEventOccurred(formArray);
				}
			}
		});
		
		/*
		allergyLabel = new JLabel("Allergy: ");
		allergyField = new JTextField(5);
		
		pulmonaryLabel = new JLabel("Pulmonary: ");
		pulmonaryField = new JTextField(5);
		
		cardiologyLabel = new JLabel("Cardiology: ");
		cardiologyField = new JTextField(5);
		
		psychiatryLabel = new JLabel("Psychiatry: ");
		psychiatryField = new JTextField(5);
		
		dermatologyLabel = new JLabel("Dermatology: ");
		dermatologyField = new JTextField(5);
		
		endocrineLabel = new JLabel("Endocrine: ");
		endocrineField = new JTextField(5);
		
		geneticsLabel = new JLabel("Genetics: ");
		geneticsField = new JTextField(5);
		
		giLabel = new JLabel("GI: ");
		giField = new JTextField(5);
		
		gynecologyLabel = new JLabel("Gynecology: ");
		gynecologyField = new JTextField(5);
		
		*/
		
		
		
		
		Border innerBorder = BorderFactory.createTitledBorder("Fill Out Form");
		Border outBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outBorder, innerBorder));
		layoutComponents();
		
		
	}
	
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.gridy = 0;
		gc.gridx = 0;
		
		for (int i = 0; i < names.length; i++) {
			
			labels[i] = new JLabel(names[i] + ": ");
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
		
		gc.gridy++;
		gc.weighty = 3;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(10, 0, 0, 5);
		add(submitBtn, gc);
		
		
		/*
		// First Row
		gc.gridy = 0;
		gc.gridx = 0;
		infoArea.setText("Please rank, in order, as many preferences as you can. \n (1) Most preferred, and (22) Least Preferred");
		gc.insets = new Insets(0, 0, 0, 5);
		add(infoArea, gc);
		
		
		// Next Row
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(allergyLabel, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 5);
		add(allergyField, gc);
		
		// Next Row
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(pulmonaryLabel, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 5);
		add(pulmonaryField, gc);
		
		// Next Row
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(cardiologyLabel, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 5);
		add(cardiologyField, gc);
		
		// Next Row
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(psychiatryLabel, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 5);
		add(psychiatryField, gc);
		*/
		
	}
	
	public void setSurveyListener(SurveyListener listener) {
		this.surveyListener = listener;
	}
}
