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

import common.GeneralPanel;
import common.db.Studentdb;

@SuppressWarnings("serial")
public class StudentPanel extends GeneralPanel{
	private JPanel bgpanel;
	
	private JLabel studentslabel;
	private static DefaultTableModel model;
	private JTable table;

	public StudentPanel() {
		
		b4.setText("Refresh");
		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				refresh();

			}
		});

		b3.setText("View Student");
		
		b2.setText("Add Student");
		b2.addActionListener(new ActionListener() {

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
