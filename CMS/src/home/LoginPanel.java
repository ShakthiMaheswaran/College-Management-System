package home;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import admin.AdminData;
import common.*;
import common.db.Admindb;
import common.db.Facultydb;
import common.db.Studentdb;


@SuppressWarnings("serial")
public class LoginPanel extends JPanel implements ActionListener {

	String panalName;
	JLabel usernameLabel, passwordLabel, logolable;
	JTextField usertextField;
	JPasswordField passwordField;
	JButton loginButton, showButton;
	ImageIcon logoIcon, userIcon, passwordIcon;
	JFrame mf;

	public LoginPanel(String name, ImageIcon img, JFrame mframe) {
		this.panalName = name;
		this.mf = mframe;
		this.logoIcon = img;
		setBorder(new LineBorder(new Color(192, 192, 192), 3));
		setBackground(new Color(0, 0, 0,80));
		setBounds(490, 206, 500, 434);
		setLayout(null);
		
		inilizeComp();
		addComponent();
	}

	private void inilizeComp() {
		logolable = new JLabel(logoIcon);
		logolable.setBounds(200, 50, 100, 110);
		logolable.setText(panalName +" Login");
		logolable.setFont(new Font("times new roman", Font.BOLD, 15));
		logolable.setForeground(Color.white);
		logolable.setVerticalTextPosition(JLabel.BOTTOM);
		logolable.setHorizontalTextPosition(JLabel.CENTER);
		add(logolable);
		
		usernameLabel = new JLabel(Img.usericon);
		usernameLabel.setOpaque(true);
		usernameLabel.setBackground(new Color(32, 178, 170));
		usernameLabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		usernameLabel.setBounds(60, 196, 60, 44);
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		usertextField = new JTextField();
		usertextField.setBounds(120, 196, 323, 44);
		usertextField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		usertextField.setToolTipText("Username");
		usertextField.setText("Username");
		usertextField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(usertextField.getText().isEmpty()) {
					usertextField.setText("Username");
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(usertextField.getText().equals("Username")) {
					usertextField.setText("");
				}
				
			}
		});
		
		passwordLabel = new JLabel();
		passwordLabel.setOpaque(true);
		passwordLabel.setBackground(new Color(32, 178, 170));
		passwordLabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		passwordLabel.setIcon(Img.passicon);
		passwordLabel.setBounds(60, 272, 60, 44);
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(120, 272, 261, 44);
		passwordField.setFont(new Font("Segor UI", Font.PLAIN, 18));
		passwordField.setToolTipText("Password");
		passwordField.setText("Password");
		passwordField.setEchoChar('\u0000');
		passwordField.addFocusListener(new FocusListener() {
					
			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {
				if(passwordField.getText().isEmpty()) {
					passwordField.setText("Password");
				}
				
			}
			
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if(passwordField.getText().equals("Password")) {
					passwordField.setText("");
					passwordField.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
				}
				
			}
		});
		
		loginButton = new JButton("Log In");
		loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(32, 178, 170));
		loginButton.setBounds((getWidth()/2)-190, 355, 383, 44);
		loginButton.setFocusable(false);
		loginButton.setBorderPainted(false);
		loginButton.addActionListener(this);
		
		showButton = new JButton("show");
		showButton.setForeground(new Color(255, 255, 255));
		showButton.setBounds(381, 272, 62, 44);
		showButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		showButton.setFocusable(false);
		showButton.setFocusPainted(false);
		showButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		showButton.setBackground(new Color(32, 178, 170));
		showButton.setBorderPainted(false);
		showButton.addActionListener(this);
	}	    
	private void addComponent() {	

		this.add(usernameLabel);
		this.add(passwordLabel);
		this.add(usertextField);
		this.add(passwordField);
		this.add(showButton);
		this.add(loginButton);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==showButton) {
			if(showButton.getText().equals("show")){
	    		passwordField.setEchoChar('\u0000');
	    		showButton.setText("hide");
	    	}
	    	else{
	    		passwordField.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
	    		showButton.setText("show");
	    	}
		}
		if(e.getSource()==loginButton) {	
			String usertext = usertextField.getText();
			String passtext = passwordField.getText();
			
			if(usertext.isBlank()||passtext.isBlank()||usertext.equals("Username")||passtext.equals("Password")) {
				JOptionPane.showMessageDialog(null, "please fill in the fields", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else {
				if(panalName == "Student") {
					if(new Studentdb().login(Integer.parseInt(usertext), passtext)) {
						Lsucces(Integer.parseInt(usertext));
					}
				}
				else if(panalName == "Faculty"){
					if(new Facultydb().login(usertext, passtext)) {
						Lsucces(Facultydb.getId(usertext));
					}
				}
				else {
					
					if(new Admindb().login(usertext, passtext)) {
						Lsucces(Admindb.getId(usertext));
					}
				}
			}
		}
	}
	public void Lsucces(int uid) {
		mf.repaint();
		mf.getContentPane().removeAll();
		new Dashboard(mf, panalName,uid);
	}
}
