package cource;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import common.GeneralPanel;
import common.db.Courcedb;

public class CourcePanel extends GeneralPanel {
	
	private static DefaultTableModel model;
	private JTable table;
	
	public CourcePanel(){
		
		resize(200);
		b3.setText("Refresh");
		b3.setVisible(true);
		b3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				refresh();
				
			}
		});
		b2.setText("Add Course");
		b2.setVisible(true);
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AddCourseDialog acd = new AddCourseDialog(null);
				acd.setVisible(true);
				
			}
		});
		b1.setText("Edit Course");
		b1.setVisible(true);
		heading.setText("All Courcse");
		
		
		try {
			model = new DefaultTableModel();
            table = new JTable(model);
			loadDataIntoTable(Courcedb.getcourcse());
			table.getTableHeader().setBackground(new Color(32, 178, 170));
			table.setBorder(new LineBorder(new Color(192, 192, 192)));
			table.setForeground(Color.DARK_GRAY);
			table.setRowHeight(40);
			table.getTableHeader().setForeground(Color.white);
			table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
			table.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			table.getTableHeader().setPreferredSize(new Dimension(50, 40));
			table.setDragEnabled(false);

			table.setGridColor(Color.LIGHT_GRAY);
			
			JScrollPane sp = new JScrollPane(table);
			sp.setLocation(8, 251);
			sp.setSize(1032, 400);
			add(sp);
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		setLayout(null);
		
	}
	
	private static void loadDataIntoTable(ResultSet rs) throws SQLException {
        

        // Clear existing data
        model.setRowCount(0);
        model.setColumnCount(0); // Clear columns  as well

        // Get metadata to determine column count and names
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Add column names to the model
        for (int i = 1; i <= columnCount; i++) {
            model.addColumn(metaData.getColumnLabel(i));
        }

        // Add rows to the model
        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            model.addRow(row);
        }

	}

	private void refresh() {
		try {
			loadDataIntoTable(Courcedb.getcourcse());
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
}
