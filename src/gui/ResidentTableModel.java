package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Resident;

public class ResidentTableModel extends AbstractTableModel{
	
	private List<Resident> db;
	private String[] colNames = {"Name", "Email", "Password Set"};
	
	public String getColumnName(int column) {
		return colNames[column];
	}
	
	public void setData(List<Resident> db) {
		this.db = db;
	}
	
	
	@Override
	public int getColumnCount() {
		return colNames.length;
	}
	@Override
	public int getRowCount() {
		return db.size();
	}
	@Override
	public Object getValueAt(int row, int col) {
		Resident resident = db.get(row);
		
		switch(col) {
		case 0:
			return resident.getUsername();
		case 1:
			return resident.getEmail();
		case 2:
			return resident.getPassword();
		}
		return null;
	}
}