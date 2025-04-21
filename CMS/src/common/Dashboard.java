package common;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import admin.AdminData;
import admin.AdminProfilePanel;
import common.attendence.AttendencePanel;
import common.connected.AssignSubPanel;
import cource.CourcePanel;
import faculty.FacultyData;
import faculty.FacultyPanel;
import home.homepage;
import student.StudentData;
import student.StudentPanel;
import subject.SubjectPanel;

@SuppressWarnings("unused")
public class Dashboard implements ActionListener {

	private int user_id;
	private int row = 100;
	private JFrame mf;
	private String profile;
	private CardLayout card;
	private JLabel profilelogo;
	private JPanel contentPanel, sidePanel, mainContentPanel, homePanel, studentPanel, facultyPanel, couresPanel,
			subjectPanel, assignPanel, ResultPanel, attandancePanel, usersPanel, searchPanel, profilePanel;
	private JButton homeButton, studentButton, facultyButton, courseButton, subjectButton, assignButton, ResultButton,
			attandanceButton, attandanceReportButton, usersButton, searchButton, profileButton, logOutButton,
			exitButton, currentButton;
	private AdminData ad;
	private FacultyData fd;
	private StudentData sd;

	private final Color buttonfcolor = Color.white;
	private final Font buttonfont = new Font("Tw Cen MT", Font.PLAIN, 20);

	public Dashboard(JFrame mainframe, String p, int uid) {

		this.mf = mainframe;
		this.profile = p;
		this.user_id = uid;

		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		mf.setContentPane(contentPanel);

		profilelogo = new JLabel(profile, new ImageIcon(""), JLabel.CENTER);
		profilelogo.setFont(new Font("times new roman", Font.BOLD, 30));
		profilelogo.setForeground(Color.white);
		profilelogo.setBounds(0, 0, 200, 100);
		JLabel l = new JLabel("");
		l.setBorder(new MatteBorder(3, 0, 0, 0, Color.CYAN));
		l.setBounds(0, 80, 250, 10);

		sidePanel = new JPanel(null);
		sidePanel.setBackground(Color.DARK_GRAY);
		sidePanel.setBorder(new LineBorder(Color.black));
		sidePanel.setBounds(5, 5, 250, mf.getHeight());
		sidePanel.setVisible(true);
		sidePanel.add(l);
		sidePanel.add(profilelogo);

		addButton();
		contentPanel.add(sidePanel);

		card = new CardLayout();
		mainContentPanel = new JPanel(card);
		mainContentPanel.setBounds(255, 5, mf.getWidth(), mf.getHeight());
		System.out.println(mf.getWidth());
		RBAC();
		addPanels();
		contentPanel.add(mainContentPanel);

		
		
		mf.revalidate();
		mf.repaint();
	}

	public void addButton() {
		homeButton = createButton("Home");
		//currentButton = homeButton;
		sidePanel.add(homeButton);

		studentButton = createButton("Student");
		sidePanel.add(studentButton);

		facultyButton = createButton("Faculty");
		sidePanel.add(facultyButton);

		courseButton = createButton("Course");
		sidePanel.add(courseButton);

		subjectButton = createButton("Subject");
		currentButton = subjectButton;
		sidePanel.add(subjectButton);

		assignButton = createButton("Assign Subject");
		sidePanel.add(assignButton);

		ResultButton = createButton("Result");
		sidePanel.add(ResultButton);

		attandanceButton = createButton("Attandance");
		sidePanel.add(attandanceButton);

		usersButton = createButton("Users");
		sidePanel.add(usersButton);

		searchButton = createButton("Search");
		sidePanel.add(searchButton);

		profileButton = createButton("Profile");
		sidePanel.add(profileButton);

		logOutButton = createButton("Log Out");
		sidePanel.add(logOutButton);

		exitButton = createButton("Exit");
		sidePanel.add(exitButton);
	}

	public void addPanels() {
		
		
		homePanel = new Homepanel(profile, user_id);
		mainContentPanel.add(homeButton.getName(), homePanel);

		studentPanel = new StudentPanel();
		mainContentPanel.add(studentButton.getName(), studentPanel);

		facultyPanel = new FacultyPanel();
		mainContentPanel.add(facultyButton.getName(), facultyPanel);

		couresPanel = new CourcePanel();
		mainContentPanel.add(courseButton.getName(), couresPanel);
		
		subjectPanel = new SubjectPanel();
		mainContentPanel.add(subjectButton.getName(), subjectPanel);

		assignPanel = new AssignSubPanel();
		mainContentPanel.add(assignButton.getName(), assignPanel);

		ResultPanel = new ResultPanel();
		mainContentPanel.add(ResultButton.getName(), ResultPanel);

		attandancePanel = new AttendencePanel();
		mainContentPanel.add(attandanceButton.getName(), attandancePanel);

		usersPanel = new UserPanel();
		mainContentPanel.add(usersButton.getName(), usersPanel);

		searchPanel = new SearchPanel();
		mainContentPanel.add(searchButton.getName(), searchPanel);

		profilePanel = new AdminProfilePanel();
		mainContentPanel.add(profileButton.getName(), profilePanel);

	}

	public void RBAC() {
		switch (profile) {
		case "Student":
			sd = new StudentData(user_id);
			sdinilize();
			break;
		case "Faculty":
			fd = new FacultyData(user_id);
			fdinilize();
			break;
		default:
			ad = new AdminData(user_id);
			adinilize();
			break;
		}
	}

	public void adinilize() {

	}

	public void fdinilize() {

	}

	public void sdinilize() {

	}

	public JButton createButton(String text) {

		JButton button = new JButton(text);
		button.setForeground(buttonfcolor);
		button.setFont(buttonfont);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setBorder(new EmptyBorder(0, 0, 0, 0));
		button.setName(text);
		button.setIcon(new ImageIcon("assets/icon/bticon/" + text + ".png"));
		button.addActionListener(this);
		button.setIconTextGap(10);
		button.setLocation(0, row);
		button.setSize(240, 40);
		row += 40;
		return button;
	}

	public void ActiveButton(JButton btn) {
		btn.setForeground(Color.blue);
	}

	public void DeActiveButton(JButton btn) {
		btn.setForeground(Color.white);
	}

	public void DisableButton(JButton btn) {
		btn.setForeground(Color.red);
		btn.setEnabled(false);
	}

	public void EnableButton(JButton btn) {
		btn.setForeground(Color.white);
		btn.setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		DeActiveButton(currentButton);

		if (e.getSource() == homeButton) {
			currentButton = homeButton;
		}
		if (e.getSource() == studentButton) {
			currentButton = studentButton;
		}
		if (e.getSource() == facultyButton) {
			currentButton = facultyButton;
		}
		if (e.getSource() == courseButton) {
			currentButton = courseButton;
		}
		if (e.getSource() == subjectButton) {
			currentButton = subjectButton;
		}
		if (e.getSource() == assignButton) {
			currentButton = assignButton;
		}
		if (e.getSource() == ResultButton) {
			currentButton = ResultButton;
		}
		if (e.getSource() == attandanceButton) {
			currentButton = attandanceButton;
		}
		if (e.getSource() == attandanceReportButton) {
			currentButton = attandanceReportButton;
		}
		if (e.getSource() == usersButton) {
			currentButton = usersButton;
		}
		if (e.getSource() == searchButton) {
			currentButton = searchButton;
		}
		if (e.getSource() == profileButton) {
			currentButton = profileButton;
		}
		if (e.getSource() == logOutButton) {
			currentButton = logOutButton;
		}
		if (e.getSource() == exitButton) {
			currentButton = exitButton;
		}

		ActiveButton(currentButton);
		card.show(mainContentPanel, currentButton.getName());

	}
}
