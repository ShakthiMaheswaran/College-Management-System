package test;

import java.awt.GridLayout;

import javax.swing.JFrame;

import student.StudentPanel;

public class Test01 {

	public static void main(String arg[]) {
		
		JFrame f1 = new JFrame("College Management System");
		f1.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f1.setSize(1380, 733);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f1.getContentPane().setLayout(new GridLayout());
		StudentPanel st = new StudentPanel();
		st.studentView();
		f1.getContentPane().add(st);
		
		
		f1.setVisible(true);
	}
}
