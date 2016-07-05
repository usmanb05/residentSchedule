package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class SurveyTableModel extends AbstractTableModel{
	private List<Survey> db;
	private String[] columnNames = {"Name", "One", "Two", "Three", "Four", "Five", "Six"};

	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	
	public void setData(List<Survey> db) {
		this.db = db;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Survey surveys = db.get(row);
		
		switch(col) {
		case 0:
			return surveys.getName();
		case 1:
			return surveys.getOne();
		case 2:
			return surveys.getTwo();
		case 3:
			return surveys.getThree();
		case 4:
			return surveys.getFour();
		case 5:
			return surveys.getFive();
		case 6:
			return surveys.getSix();
		case 7:
		}
		return null;
	}

}
