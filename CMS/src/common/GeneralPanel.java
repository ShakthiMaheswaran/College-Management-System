package common;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class GeneralPanel extends JPanel{
	
	protected JPanel backgroundPanel;
	protected int bgheight = 300;
	protected JLabel heading;
	protected JButton  b1;
	protected JButton  b2;
	protected JButton  b3;
	protected JButton  b4;
	
	public GeneralPanel() {
		
		setLayout(null);
		
		backgroundPanel = new JPanel();
		backgroundPanel.setBackground(Color.cyan);
		backgroundPanel.setLayout(null);
		backgroundPanel.setBorder(new LineBorder(Color.black, 3, true));
		backgroundPanel.setBounds(8, 0, 1032, bgheight);
		
		heading = new JLabel();
		heading.setForeground(Color.white);
		heading.setHorizontalAlignment(JLabel.LEFT);
		heading.setFont(new Font("times new roman", Font.BOLD, 50));
		heading.setBounds(10, (bgheight/2)- 30 , 1025, 60);
		backgroundPanel.add(heading);
		
		b1 = new JButton();
		b1.setFont(new Font("Segoe UI", Font.BOLD, 15));
		b1.setForeground(new Color(0, 128, 128));
		b1.setBackground(new Color(255, 255, 255));
		b1.setBorder(new LineBorder(Color.black));
		b1.setBounds(870, bgheight - 45, 150, 35);
		b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b1.setFocusable(false);
		b1.setVisible(false);
		
		b2 = new JButton();
		b2.setFont(new Font("Segoe UI", Font.BOLD, 15));
		b2.setForeground(new Color(0, 128, 128));
		b2.setBackground(new Color(255, 255, 255));
		b2.setBorder(new LineBorder(Color.black));
		b2.setBounds(710, bgheight - 45, 150, 35);
		b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b2.setFocusable(false);
		b2.setVisible(false);

		b3 = new JButton();
		b3.setFont(new Font("Segoe UI", Font.BOLD, 15));
		b3.setForeground(new Color(0, 128, 128));
		b3.setBackground(new Color(255, 255, 255));
		b3.setBorder(new LineBorder(Color.black));
		b3.setBounds(550, bgheight - 45, 150, 35);
		b3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b3.setFocusable(false);
		b3.setVisible(false);

		b4 = new JButton();
		b4.setFont(new Font("Segoe UI", Font.BOLD, 15));
		b4.setForeground(new Color(0, 128, 128));
		b4.setBackground(new Color(255, 255, 255));
		b4.setBorder(new LineBorder(Color.black));
		b4.setBounds(390, bgheight - 45, 150, 35);
		b4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b4.setFocusable(false);
		b4.setVisible(false);

		
		backgroundPanel.add(b1);
		backgroundPanel.add(b2);
		backgroundPanel.add(b3);
		backgroundPanel.add(b4);

		
		add(backgroundPanel);
	}
	public void setheight(int height) {
		bgheight = height;
		backgroundPanel.setSize(1032, bgheight);
	}
	
}
