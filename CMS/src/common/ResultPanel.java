package common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import common.db.Courcedb;
import common.db.Studentdb;

@SuppressWarnings("serial")
public class ResultPanel extends GeneralPanel{

	private JPanel selectcourcepanel;
	private static DefaultTableModel model;
	private JTable table;
	private JLabel selectcourselable, selectyearlabel, selectsublable;
	private JComboBox<String> coursetext, yeartext, subtext;

	public ResultPanel() {

		resize(120);
		b3.setText("Internal Marks");
		b3.setVisible(true);
		b2.setText("External Marks");
		b2.setVisible(true);
		b1.setText("Enter Marks");
		b1.setVisible(true);
		heading.setText("Result");
		
		selectcourcepanel = new JPanel();
		selectcourcepanel.setBounds(8, 125, 1073, 250);
		selectcourcepanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		selectcourcepanel.setBackground(Color.WHITE);
		selectcourcepanel.setLayout(null);

		coursetext = new JComboBox<String>(Courcedb.getCourseNames());
		coursetext.setFocusable(false);
		coursetext.setBackground(Color.WHITE);
		coursetext.addActionListener(null);
		coursetext.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		coursetext.setBounds(236, 30, 750, 40);
		selectcourcepanel.add(coursetext);

		yeartext = new JComboBox<String>(new String[] { "1st year", "2nd year", "3rd year" });
		yeartext.setFocusable(false);
		yeartext.setBackground(Color.WHITE);
		yeartext.setBounds(236, 103, 750, 40);
		yeartext.addActionListener(null);
		yeartext.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		selectcourcepanel.add(yeartext);

		subtext = new JComboBox<String>(new String[] { "c", "java", "python" });
		subtext.setFocusable(false);
		subtext.setBackground(Color.WHITE);
		subtext.setBounds(236, 173, 750, 40);
		subtext.addActionListener(null);
		subtext.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		selectcourcepanel.add(subtext);

		selectcourselable = new JLabel("Select Cource   :");
		selectcourselable.setHorizontalAlignment(SwingConstants.RIGHT);
		selectcourselable.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		selectcourselable.setBounds(10, 30, 200, 40);
		selectcourcepanel.add(selectcourselable);

		selectyearlabel = new JLabel("Select Sem/Year  :");
		selectyearlabel.setHorizontalAlignment(SwingConstants.RIGHT);
		selectyearlabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		selectyearlabel.setBounds(10, 102, 200, 40);
		selectcourcepanel.add(selectyearlabel);

		selectsublable = new JLabel("Select Subject   :");
		selectsublable.setHorizontalAlignment(SwingConstants.RIGHT);
		selectsublable.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		selectsublable.setBounds(10, 172, 200, 40);
		selectcourcepanel.add(selectsublable);

		try {
			model = new DefaultTableModel();
			table = new JTable(model);
			loadDataIntoTable(Studentdb.getstudents());

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
			sp.setLocation(10, 376);
			sp.setBackground(Color.white);
			sp.setSize(1025, 165);
			add(sp);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
			e.printStackTrace();
		}

		setLayout(null);
		add(selectcourcepanel);
		

	}

	private static void loadDataIntoTable(ResultSet rs) throws SQLException {

		
		model.setRowCount(0);
		model.setColumnCount(0); 

		
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();

		
		for (int i = 1; i <= columnCount; i++) {
			model.addColumn(metaData.getColumnLabel(i));
		}

		
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
			loadDataIntoTable(Studentdb.getstudents());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
