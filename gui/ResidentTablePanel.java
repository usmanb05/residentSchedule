package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import model.Resident;

public class ResidentTablePanel extends JPanel {

	private JTable table;
	private ResidentTableModel residentTableModel;
	private JPopupMenu popup;
	private ResidentTableListener residentTableListener;
	
	public ResidentTablePanel() {
		residentTableModel = new ResidentTableModel();
		table = new JTable(residentTableModel);
		popup = new JPopupMenu();
		TableColumnModel tcm = table.getColumnModel();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		
		table.setCellSelectionEnabled(true);
		table.getTableHeader().setReorderingAllowed(false);
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tcm.getColumn(0).setPreferredWidth(5);
		tcm.getColumn(0).setCellRenderer(centerRenderer);
		tcm.getColumn(1).setPreferredWidth(400);
		tcm.getColumn(2).setPreferredWidth(400);
		tcm.getColumn(3).setPreferredWidth(400);

		JMenuItem removeItem = new JMenuItem("Delete row");
		popup.add(removeItem);
		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				
				table.getSelectionModel().setSelectionInterval(row, row);
				
				if (e.getButton() == MouseEvent.BUTTON3) {
					popup.show(table, e.getX(), e.getY());
				}
			}
		});
		
		removeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if (residentTableListener != null) {
					residentTableListener.rowDeleted(row);
					residentTableModel.fireTableRowsDeleted(row, row);
				}
			}
		});
		
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public void setData(List<Resident> db) {
		residentTableModel.setData(db);
	}
	
	public void refresh() {
		residentTableModel.fireTableDataChanged();
	}

	public void setResidentTableListener(ResidentTableListener listener) {
		this.residentTableListener = listener;
	}
}
