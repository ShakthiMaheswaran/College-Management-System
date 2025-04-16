package cource;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import common.db.Courcedb;

public class CourcePanel extends JPanel {
	
	private JPanel bgpanel;
	private JButton addcourse,editcourse,refreshbutton;
	private JLabel facultylabel;
	private static DefaultTableModel model;
	private JTable table;
	
	public CourcePanel(){
		
		bgpanel = new JPanel();
		bgpanel.setBackground(Color.cyan);
		bgpanel.setLayout(null);
		bgpanel.setBorder(new LineBorder(Color.black,3,true));
		bgpanel.setBounds(8, 0, 1032, 200);
		
		refreshbutton = new JButton("Refresh");
		refreshbutton.setBorder(new LineBorder(Color.black));
		refreshbutton.setBounds(546, 139, 153, 33);
		bgpanel.add(refreshbutton);
		refreshbutton.setFocusable(false);
		refreshbutton.setForeground(new Color(0, 128, 128));
		refreshbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		refreshbutton.setBackground(new Color(255, 255, 255));
		refreshbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		refreshbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				refresh();
				
			}
		});
		
		addcourse = new JButton("Add Course");
		addcourse.setBorder(new LineBorder(Color.black));
		addcourse.setBounds(709, 139, 153, 33);
		bgpanel.add(addcourse);
		addcourse.setFocusable(false);
		addcourse.setForeground(new Color(0, 128, 128));
		addcourse.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addcourse.setBackground(new Color(255, 255, 255));
		addcourse.setFont(new Font("Segoe UI", Font.BOLD, 15));
		addcourse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AddCourseDialog acd = new AddCourseDialog(null);
				acd.setVisible(true);
				
			}
		});
		
		editcourse = new JButton("Edit Course");
		editcourse.setBorder(new LineBorder(Color.black));
		editcourse.setBounds(872, 139, 153, 33);
		bgpanel.add(editcourse);
		editcourse.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editcourse.setFocusable(false);
		editcourse.setForeground(new Color(0, 128, 128));
		editcourse.setFont(new Font("Segoe UI", Font.BOLD, 15));
		editcourse.setBackground(new Color(255, 255, 255));
		
		facultylabel = new JLabel("All Courcse");
		facultylabel.setIcon(null);
		facultylabel.setBounds(10, 65, 224, 44);
		bgpanel.add(facultylabel);
		facultylabel.setBackground(Color.cyan);
		facultylabel.setHorizontalAlignment(SwingConstants.LEFT);
		facultylabel.setForeground(Color.WHITE);
		facultylabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		facultylabel.setOpaque(true);
		
		
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
		add(bgpanel);
		
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
