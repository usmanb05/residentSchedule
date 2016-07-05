package gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Ranking;
import model.Survey;
import model.SurveyTableModel;

public class RankingTablePanel extends JPanel{
	private JTable table;
	private RankingTableModel rankingTableModel;
	private SurveyTableModel surveyTableModel;
	
	public RankingTablePanel() {
		rankingTableModel = new RankingTableModel();
		surveyTableModel = new SurveyTableModel();
		table = new JTable(surveyTableModel);
		
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
		
	}
	
	public void setData(List<Ranking> db) {
		rankingTableModel.setData(db);
	}
	
	public void setSurveyData(List<Survey> db) {
		surveyTableModel.setData(db);
	}
	
	public void refresh() {
		rankingTableModel.fireTableDataChanged();
		surveyTableModel.fireTableDataChanged();
	}
	
	
}
