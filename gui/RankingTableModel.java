package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Ranking;

public class RankingTableModel extends AbstractTableModel {
	
	private List<Ranking> db;
	private String[] rowNames = {"Allergy", "Pulmonary", "Cardiology", "Psychiatry", "Dermatology", "Endocrine", "ENT", "Genetics", "GI", "Gynecology", "Hematology", "ID", "Neurology", "Opthalmology", "Orthopedics", "Palliative Care", "Renal", "Rheumatology", "Sports Medicine", "Toxicology", "7W", "9W"};

	public String getRowNames(int row) {
		return rowNames[row];
	}
	
	@Override
	public int getColumnCount() {
		return 1;
	}
	
	public void setData(List<Ranking> db) {
		this.db = db;
	}

	@Override
	public int getRowCount() {
		return rowNames.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Ranking ranking = db.get(row);
		
		switch(row) {
		case 0:
			return ranking.getAllergy();
		case 1:
			return ranking.getPulmonary();
		case 2:
			return ranking.getCardiology();
		case 3:
			return ranking.getPsychiatry();
		case 4:
			return ranking.getDermatology();
		case 5:
			return ranking.getEndocrine();
		case 6:
			return ranking.getEnt();
		case 7:
			return ranking.getGenetics();
		case 8:
			return ranking.getGi();
		case 9:
			return ranking.getGynecology();
		case 10:
			return ranking.getHematology();
		case 11:
			return ranking.getId();
		case 12:
			return ranking.getNeurology();
		case 13:
			return ranking.getOpthalmology();
		case 14:
			return ranking.getOrthopedics();
		case 15:
			return ranking.getPalliative();
		case 16:
			return ranking.getRenal();
		case 17:
			return ranking.getRheumatology();
		case 18:
			return ranking.getSports();
		case 19:
			return ranking.getToxicology();
		case 20:
			return ranking.getSevenW();
		case 21:
			return ranking.getNineW();
		}
		return null;
	}
	
	

}
