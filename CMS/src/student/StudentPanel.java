package student;

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

import common.db.Studentdb;

@SuppressWarnings("serial")
public class StudentPanel extends JPanel {
	private JPanel bgpanel;
	private JButton viewstudent, addstudent, refreshbutton;
	private JLabel studentslabel;
	private static DefaultTableModel model;
	private JTable table;

	public StudentPanel() {

		bgpanel = new JPanel();
		bgpanel.setBackground(Color.cyan);
		bgpanel.setLayout(null);
		bgpanel.setBorder(new LineBorder(Color.black, 3, true));
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

		viewstudent = new JButton("View Student");
		viewstudent.setBorder(new LineBorder(Color.black));
		viewstudent.setBounds(709, 139, 153, 33);
		bgpanel.add(viewstudent);
		viewstudent.setFocusable(false);
		viewstudent.setForeground(new Color(0, 128, 128));
		viewstudent.setCursor(new Cursor(Cursor.HAND_CURSOR));
		viewstudent.setBackground(new Color(255, 255, 255));
		viewstudent.setFont(new Font("Segoe UI", Font.BOLD, 15));
		;

		addstudent = new JButton("Add Student");
		addstudent.setBorder(new LineBorder(Color.black));
		addstudent.setBounds(872, 139, 153, 33);
		bgpanel.add(addstudent);
		addstudent.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addstudent.setFocusable(false);
		addstudent.setForeground(new Color(0, 128, 128));
		addstudent.setFont(new Font("Segoe UI", Font.BOLD, 15));
		addstudent.setBackground(new Color(255, 255, 255));
		addstudent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				AddStudentDialog dialog = new AddStudentDialog(null);
				dialog.setVisible(true);
			}
		});

		studentslabel = new JLabel("All Students");
		studentslabel.setIcon(null);
		studentslabel.setBounds(10, 65, 224, 44);
		bgpanel.add(studentslabel);
		studentslabel.setBackground(Color.cyan);
		studentslabel.setHorizontalAlignment(SwingConstants.LEFT);
		studentslabel.setForeground(Color.WHITE);
		studentslabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		studentslabel.setOpaque(true);

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
			sp.setLocation(8, 251);
			sp.setSize(1032, 400);
			add(sp);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
			e.printStackTrace();
		}

		setLayout(null);
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
