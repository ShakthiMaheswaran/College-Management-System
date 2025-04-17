package common;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import admin.AdminData;
import common.db.Facultydb;
import common.db.Studentdb;
import faculty.FacultyData;
import student.StudentData;

@SuppressWarnings("serial")
public class Homepanel extends GeneralPanel {

	private JPanel studentcountPanel;
	private JPanel facultycountPanel;
	private JPanel courcescountPanel;
	private JPanel subjectcountPanel;
	private JLabel count;
	private JLabel welcomeLabel;
	private AdminData ad;
	private FacultyData fd;
	private StudentData sd;
	private String profile;
	private int u_id;
	private int col = 50;

	Homepanel(String p, int u) {

		this.profile = p;
		this.u_id = u;
		RBAC();
		
		welcomeLabel = new JLabel("Welcome Administrator" );//+ ad.getUsername());
		welcomeLabel.setForeground(Color.white);
		welcomeLabel.setHorizontalAlignment(JLabel.RIGHT);
		welcomeLabel.setFont(new Font("times new roman", Font.BOLD, 50));
		welcomeLabel.setBounds(0, 20, 1025, 50);

		heading.setText("Home Page");
		
		studentcountPanel = createPanel("Students", Integer.toString(new Studentdb().studentCount()));

		courcescountPanel = createPanel("Cources", "0");

		facultycountPanel = createPanel("Faculities", Integer.toString(new Facultydb().facultyCount()));

		subjectcountPanel = createPanel("Subjects", "0");

		
		backgroundPanel.add(welcomeLabel);

		
		add(studentcountPanel);
		add(courcescountPanel);
		add(facultycountPanel);
		add(subjectcountPanel);
		add(backgroundPanel);
		
	}

	public void RBAC() {
		switch (profile) {
		case "Student":
			sd = new StudentData(u_id);

			break;
		case "Faculty":
			fd = new FacultyData(u_id);

			break;
		default:
			ad = new AdminData(u_id);

			break;
		}
	}

	public JPanel createPanel(String text, String count) {
		JPanel jp = new JPanel();
		jp.setSize(200, 200);
		jp.setLayout(null);
		jp.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		jp.setBackground(Color.white);
		jp.setLocation(col, 270);
		col += 250;

		JLabel l = new JLabel(text, new ImageIcon("assets/icon/hpanel/" + text + ".png"), JLabel.CENTER);
		l.setHorizontalTextPosition(JLabel.CENTER);
		l.setVerticalTextPosition(JLabel.BOTTOM);
		l.setFont(new Font("times new roman", Font.BOLD, 20));
		l.setBounds(5, 5, 190, 170);

		JLabel l2 = new JLabel(count);
		l2.setBounds(5, 150, 190, 30);
		l2.setFont(new Font("times new roman", Font.BOLD, 20));
		l2.setHorizontalAlignment(JLabel.CENTER);
		l2.setVerticalAlignment(JLabel.TOP);
		jp.add(l);
		jp.add(l2);
		return jp;
	}
}
