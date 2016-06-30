package gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Ranking;

public class RankingTablePanel extends JPanel{
	private JTable table;
	private RankingTableModel rankingTableModel;
	
	public RankingTablePanel() {
		rankingTableModel = new RankingTableModel();
		table = new JTable(rankingTableModel);
		
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
		
	}
	
	public void setData(List<Ranking> db) {
		rankingTableModel.setData(db);
	}
	
	public void refresh() {
		rankingTableModel.fireTableDataChanged();
	}
	
	
}
