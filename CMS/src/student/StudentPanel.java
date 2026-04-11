package student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import common.GeneralPanel;
import common.db.Studentdb;
import common.Img;

@SuppressWarnings("serial")
public class StudentPanel extends GeneralPanel {

	private static DefaultTableModel model;
	private JTable table;
	private JScrollPane sp, sp2;
	private JPanel studentviewpanel,comanPanel,datapanel,acdmDataPanel;
	private ViewStudentDialog dialog;
	private Img img;
	private Studentdb stdb;
	private StudentData st = new StudentData(5101);

	public StudentPanel() {

		resize(200);

		b3.setText("Refresh");
		b3.setVisible(true);
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				refresh();

			}
		});
		
		
		dialog = new ViewStudentDialog(null);
		b2.setText("View Student");
		b2.setVisible(true);
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
				
			}
		});
		dialog.viewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//StudentData stdata = new StudentData(Integer.parseInt(dialog.getRollno()));
				
				stdb = new Studentdb();
				st = new StudentData(stdb.getId(dialog.getcId(), dialog.getYear(), dialog.getRollno()));
				studentView();
				dialog.dispose();
				
			}
		});

		b1.setText("Add Student");
		b1.setVisible(true);
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				AddStudentDialog dialog = new AddStudentDialog(null);
				dialog.setVisible(true);
			}
		});

		heading.setText("All Student");
		add(backgroundPanel);

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

			sp = new JScrollPane(table);
			sp.setBounds(8, 210, 1032, 522);
			//sp.setLocation(8, 251);
			//sp.setSize(1032, 400);
			add(sp);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
			e.printStackTrace();
		}

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
	public void studentView() {
		remove(sp);  
		
		studentviewpanel = new JPanel();
		studentviewpanel.setLayout(null);
		studentviewpanel.setPreferredSize(new Dimension(1012, 700));
		
		sp2 =  new JScrollPane(studentviewpanel);
		sp2.setBounds(8, 210, 1032, 522);
		sp2.setBorder(BorderFactory.createLineBorder(Color.black));
		
		comanPanel = new JPanel();
		comanPanel.setLayout(new BorderLayout());
		comanPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		comanPanel.setBounds(0, 100, 800, 600);
		
		//Components 
		JButton closemark = new JButton("X");
		closemark.setBounds(960,2,50,30);
		closemark.setFocusable(false);
		closemark.setBackground(Color.red);
		closemark.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(sp2);
				add(sp);
				repaint();
			}
		});
		studentviewpanel.add(closemark);
		
		JButton personaldetails = new JButton("Personal Details");
		personaldetails.setBounds(10, 10, 130,30);
		personaldetails.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				comanPanel.removeAll();
				comanPanel.add(datapanel,BorderLayout.WEST);
				comanPanel.repaint();
			}
		});
		studentviewpanel.add(personaldetails);
		
		JButton Acadamicdetails = new JButton("Acadamic Details");
		Acadamicdetails.setBounds(150, 10, 130,30);
		Acadamicdetails.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showStudentAcadmicData();
				
			}
		});;
		studentviewpanel.add(Acadamicdetails);
		
		img = new Img();//local image class
		//JLabel studentPhoto = new JLabel(img.getroundimage(img.resize("E:\\eclips1\\CMS\\assets\\StudentImages\\passportSize.jpg")));
		JLabel studentPhoto = new JLabel(img.getroundimage(img.resize("E:\\CMSrepo\\CMS\\assets\\student img\\passportSize.jpg")));
		studentPhoto.setBounds(830, 30, 150, 200);
		studentviewpanel.add(studentPhoto);
		
		showStudentData();		
		
		studentviewpanel.add(comanPanel);
		sp2.revalidate();
		sp2.repaint();
		add(sp2);
		revalidate();
		repaint();
	}
	
	public JLabel createLabel(String name){
		
		
		
		JLabel l = new JLabel(name);
		l.setFont(new Font("times new Roman", Font.BOLD, 20));
		l.setVerticalAlignment(JLabel.CENTER);
		l.setHorizontalAlignment(JLabel.LEADING);
		
		
		return l;
	}
	
	public void showStudentData() {
		
		comanPanel.removeAll();
		
		JLabel nameLabel 			= createLabel("Name");
		JLabel dobLabel 			= createLabel("DoB");
		JLabel studentIdLabel 		= createLabel("Student Id");
		JLabel registerNoLabel 		= createLabel("Regester No");
		JLabel degreeLabel 			= createLabel("Degree");
		JLabel academicYearLabel 	= createLabel("Academic Year");
		JLabel mobileNoLabel 		= createLabel("Mobile No");
		JLabel emailIdLabel 		= createLabel("Email Id");
		JLabel fatherLabel 			= createLabel("Father\'s Name");
		JLabel motherLabel 			= createLabel("Mother\'s Nam\te\t:");
		JLabel addressLabel 		= createLabel("Adders ");
		
		JLabel name = createLabel(st.getFirstName());
		JLabel dob = createLabel(st.getD_o_b());
		JLabel StudentId = createLabel(Integer.toString(st.getStudentId()));
		JLabel regNo = createLabel("reg000000000");
		JLabel degree = createLabel("not available");
		JLabel year = createLabel(st.getYear());
		JLabel mobileNo = createLabel(st.getPhNo());
		JLabel mailId = createLabel(st.getEmailId());
		JLabel f_Name= createLabel(st.getFathersName());
		JLabel m_Name = createLabel(st.getMothersName());
		JLabel address = createLabel("to check the length 123456789123");
		
		
		datapanel = new JPanel(new GridLayout(11, 2, 20, 20)); 
		//datapanel.setBounds(150, 100, 500, 400);
		//datapanel.setPreferredSize(new Dimension(300,300));
		datapanel.setBorder(new LineBorder(Color.BLACK));
		
		datapanel.add(nameLabel);
		datapanel.add(name);
		datapanel.add(dobLabel);
		datapanel.add(dob);
		datapanel.add(studentIdLabel);
		datapanel.add(StudentId);
		datapanel.add(registerNoLabel);
		datapanel.add(regNo);
		datapanel.add(degreeLabel);
		datapanel.add(degree);
		datapanel.add(academicYearLabel);
		datapanel.add(year);
		datapanel.add(mobileNoLabel);
		datapanel.add(mobileNo);
		datapanel.add(emailIdLabel);
		datapanel.add(mailId);
		datapanel.add(fatherLabel);
		datapanel.add(f_Name);
		datapanel.add(motherLabel);
		datapanel.add(m_Name);
		datapanel.add(addressLabel);
		datapanel.add(address);
		
		
		comanPanel.add(datapanel,BorderLayout.WEST);

		
	}
	
	public void showStudentAcadmicData() {
		
		acdmDataPanel = new JPanel(new GridLayout(10, 2, 20, 20));
		
		
		comanPanel.removeAll();
		comanPanel.repaint();
	}

}
