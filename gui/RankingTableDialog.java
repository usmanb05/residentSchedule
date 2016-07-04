package gui;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFrame;

import controller.Controller;
import model.Database;

public class RankingTableDialog extends JDialog{
	private RankingTablePanel rankingTablePanel;
	private Controller controller;
	private Database db;
	
	public RankingTableDialog(JFrame parent) {
		super(parent, "User Data", true);
		
		rankingTablePanel = new RankingTablePanel();
		db = new Database();
		
		
		getContentPane().setLayout(new BorderLayout());
		add(rankingTablePanel, BorderLayout.CENTER);
		
		try {
			db.connect();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			db.loadRanks();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rankingTablePanel.setData(db.getRanking());
		rankingTablePanel.refresh();
		
		setSize(1200, 200);
	}
	
	public void setData() {
		
	}
	
}
