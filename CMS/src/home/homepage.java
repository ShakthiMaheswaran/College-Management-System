package home;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import common.Dashboard;
import common.Img;

public class homepage {

	// declaration section
	JFrame mainFrame;

	private JPanel contentPanel;
	private LoginPanel adminpanel, studentpanel, facultypanel;
	private JLabel bgimg, underlinelabel;
	private Timer timer;
	private int adminpanelx = -2280, adminpanely = 250;
	private int facultypanelx = -900, facultypanely = 250;
	private int studentpanelx = 420, studentpanely = 250;
	private boolean adminchanging = false, facultychanging = false, studentchanging = false;
	private int underlinelabelx = 340, underlinelabelwidth = 150;

	homepage() {

		// initialization

		// timer for the login panel
		timer = new Timer(20, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (adminchanging) {
					if (adminpanelx == 420) {
						adminchanging = false;
						underlinelabelx = 8;
						timer.stop();
					} else {
						adminpanelx += 60;
						facultypanelx += 60;
						studentpanelx += 60;
						underlinelabelx -= 7;
					}
				} else if (facultychanging) {
					if (facultypanelx == 420) {
						facultychanging = false;
						underlinelabelx = 174;
						timer.stop();
					} else {
						if (facultypanelx < 420) {
							adminpanelx += 60;
							facultypanelx += 60;
							studentpanelx += 60;
							underlinelabelx -= 7;
						} else {
							adminpanelx -= 60;
							facultypanelx -= 60;
							studentpanelx -= 60;
							underlinelabelx += 7;
						}
					}
				} else if (studentchanging) {
					if (studentpanelx == 420) {
						studentchanging = false;
						underlinelabelx = 340;
						timer.stop();
					} else {
						adminpanelx -= 60;
						facultypanelx -= 60;
						studentpanelx -= 60;
						underlinelabelx += 7;
					}
				}

				adminpanel.setLocation(adminpanelx, adminpanely);
				facultypanel.setLocation(facultypanelx, facultypanely);
				studentpanel.setLocation(studentpanelx, studentpanely);
				underlinelabel.setLocation(underlinelabelx, 40);
				mainFrame.repaint();

			}
		});

		// Properties of main frame
		mainFrame = new JFrame("College Management System");
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setSize(1380, 733);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// setting the appLogo
		ImageIcon img = Img.applogo;
		mainFrame.setIconImage(img.getImage());

		// contentPanel for the mainframe
		contentPanel = new JPanel();
		mainFrame.setContentPane(contentPanel);
		contentPanel.setLayout(null);

		// heading (college name with logo )
		JPanel namePanel = new JPanel();
		namePanel.setBackground(new Color(255, 255, 255, 150));
		namePanel.setBounds(30, 30, 1240, 105);
		namePanel.setBorder(new LineBorder(Color.black, 2));
		namePanel.setLayout(null);
		// name of the college
		JLabel cnlabel = new JLabel("Adaikala Matha College");
		cnlabel.setFont(new Font("times new roman", Font.BOLD, 80));
		cnlabel.setBounds(250, 5, 1100, 85);
		namePanel.add(cnlabel);
		// college logo

		JLabel clogo = new JLabel(Img.clogoing);
		clogo.setBounds(8, 8, 90, 90);
		namePanel.add(clogo);
		contentPanel.add(namePanel);

		// login bar
		@SuppressWarnings("serial")
		JPanel lbar = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		lbar.setOpaque(false);
		lbar.setBackground(new Color(0, 0, 0, 120));
		lbar.setBounds((mainFrame.getWidth() / 2) - 270, 190, 500, 50);
		lbar.setLayout(null);

		// switch Butttons
		JButton adminbutton = new JButton("Admin");
		adminbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.requestFocusInWindow();

				if (adminpanelx != 420) {
					adminchanging = true;
					if (!timer.isRunning()) {
						timer.start();
					}
				}

			}
		});
		buttonstyle(adminbutton);
		adminbutton.setBounds(0, 0, 166, 45);
		lbar.add(adminbutton);

		JButton facultybutton = new JButton("Faculty");
		facultybutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (facultypanelx != 420) {
					facultychanging = true;
					if (!timer.isRunning()) {
						timer.start();
					}
				}
			}
		});
		buttonstyle(facultybutton);
		facultybutton.setBounds(166, 0, 166, 45);
		lbar.add(facultybutton);

		JButton studentbutton = new JButton("Student");
		studentbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked student");
				if (studentpanelx != 420) {
					studentchanging = true;
					if (!timer.isRunning()) {
						timer.start();
					}
				}

			}
		});
		buttonstyle(studentbutton);
		studentbutton.setBounds(332, 0, 166, 45);
		lbar.add(studentbutton);

		underlinelabel = new JLabel("");
		underlinelabel.setBorder(new MatteBorder(3, 0, 0, 0, Color.CYAN));
		underlinelabel.setBounds(underlinelabelx, 40, underlinelabelwidth, 6);
		lbar.add(underlinelabel);

		contentPanel.add(lbar);

		// login panels for admin student faculty
		adminpanel = new LoginPanel("Admin", Img.adminloginlogo, mainFrame);
		adminpanel.setVisible(true);
		adminpanel.setLocation(adminpanelx, adminpanely);
		contentPanel.add(adminpanel);

		studentpanel = new LoginPanel("Student", Img.studentloginlogo, mainFrame);
		studentpanel.setVisible(true);
		studentpanel.setLocation(studentpanelx, studentpanely);
		contentPanel.add(studentpanel);

		facultypanel = new LoginPanel("Faculty", Img.facultyloginlogo, mainFrame);
		facultypanel.setVisible(true);
		facultypanel.setLocation(facultypanelx, facultypanely);
		contentPanel.add(facultypanel);
		
		adminpanel.Lsucces(101); // remove this after developing

		// background image
		bgimg = new JLabel();
		ImageIcon bgicon = new ImageIcon("assets/img/2.jpg");
		bgimg.setIcon(bgicon);
		bgimg.setBounds(10, 5, mainFrame.getWidth(), mainFrame.getHeight());
		contentPanel.add(bgimg);

		mainFrame.setVisible(true);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				@SuppressWarnings("unused")
				homepage h = new homepage();
				
				
			}
		});
	}

	public void buttonstyle(JButton button) {

		button.setFocusable(true);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Segoe UI", Font.BOLD, 15));
		button.setBorder(new EmptyBorder(0, 0, 0, 0));
		button.setBackground(Color.black);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setOpaque(false);
	}
}
