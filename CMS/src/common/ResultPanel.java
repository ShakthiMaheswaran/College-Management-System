package common;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
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
public class ResultPanel extends JPanel {

	private JPanel bgpanel;
	private JPanel selectcourcepanel;
	private JButton intermark, extrnmark;
	private JLabel resultlabel;
	private static DefaultTableModel model;
	private JTable table;
	private JLabel selectcourselable, selectyearlabel, selectsublable;
	private JComboBox<String> coursetext, yeartext, subtext;

	public ResultPanel() {

		bgpanel = new JPanel();
		bgpanel.setBackground(Color.cyan);
		bgpanel.setLayout(null);
		bgpanel.setBorder(new LineBorder(Color.black, 3, true));
		bgpanel.setBounds(8, 0, 1032, 120);

		intermark = new JButton("Internal Marks");
		intermark.setBorder(new LineBorder(Color.black));
		intermark.setBounds(709, 75, 153, 33);
		bgpanel.add(intermark);
		intermark.setFocusable(false);
		intermark.setForeground(new Color(0, 128, 128));
		intermark.setCursor(new Cursor(Cursor.HAND_CURSOR));
		intermark.setBackground(new Color(255, 255, 255));
		intermark.setFont(new Font("Segoe UI", Font.BOLD, 15));
		;

		extrnmark = new JButton("External Marks");
		extrnmark.setBorder(new LineBorder(Color.black));
		extrnmark.setBounds(872, 75, 153, 33);
		bgpanel.add(extrnmark);
		extrnmark.setCursor(new Cursor(Cursor.HAND_CURSOR));
		extrnmark.setFocusable(false);
		extrnmark.setForeground(new Color(0, 128, 128));
		extrnmark.setFont(new Font("Segoe UI", Font.BOLD, 15));
		extrnmark.setBackground(new Color(255, 255, 255));

		resultlabel = new JLabel("Enter Marks");
		resultlabel.setIcon(null);
		resultlabel.setBounds(10, 65, 224, 44);
		bgpanel.add(resultlabel);
		resultlabel.setBackground(Color.cyan);
		resultlabel.setHorizontalAlignment(SwingConstants.LEFT);
		resultlabel.setForeground(Color.WHITE);
		resultlabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		resultlabel.setOpaque(true);

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
		add(bgpanel);

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
