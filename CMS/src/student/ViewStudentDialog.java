package student;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import common.db.Courcedb;
import common.db.Departdb;
import common.db.Studentdb;


@SuppressWarnings("serial")
public class ViewStudentDialog extends JDialog{
	
	private JLabel depart,course,year,rollno;
	private JComboBox<String> departText,courseText,yearText;
	private JComboBox<Integer> rollnoText;
	private Departdb deptdb = new Departdb();
	private Courcedb coursedb = new Courcedb();
	private Studentdb stdb = new Studentdb();
	public JButton viewButton;
	
	
	public ViewStudentDialog(JFrame parent) {
		super(parent,"View Student",true);
		setLayout(new BorderLayout());
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(4, 2,20,20));
		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		depart = new JLabel("Dapartment :");		
		course = new JLabel("Course :");		
		year = new JLabel("Year :");
		rollno = new JLabel("Roll NO :");
		
		departText = new JComboBox<String>(deptdb.getdepts());
		departText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				courseText.setModel(new DefaultComboBoxModel<String>(coursedb.getCourseNames(deptdb.getdeptId((String)departText.getSelectedItem()))));
			}
		});
		
		courseText = new JComboBox<>(coursedb.getCourseNames(deptdb.getdeptId((String)departText.getSelectedItem())));
		courseText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(coursedb.getDuris((String)courseText.getSelectedItem())==3) 
					yearText.setModel(new DefaultComboBoxModel<String>(new String[]{"1st","2nd","3rd"}));
				else 
					yearText.setModel(new DefaultComboBoxModel<String>(new String[]{"1st","2nd"}));
				
			}
		});
		
		yearText = new JComboBox<String>(new String[] {});
		yearText.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rollnoText.setModel(new DefaultComboBoxModel<Integer>(stdb.getrollnos((String)yearText.getSelectedItem(),Courcedb.getcid((String)courseText.getSelectedItem()) )));
				
			}
		});
		rollnoText = new JComboBox<Integer>(new Integer[] {});
		
		formPanel.add(depart);
		formPanel.add(departText);
		formPanel.add(course);
		formPanel.add(courseText);
		formPanel.add(year);
		formPanel.add(yearText);
		formPanel.add(rollno);
		formPanel.add(rollnoText);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		viewButton = new JButton("View Student");
		JButton cancelButton = new JButton("Cancel");
		buttonPanel.add(viewButton);
		buttonPanel.add(cancelButton);



		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		add(formPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		setSize(400, 300);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
	}
	
	public int getcId() {
		return Courcedb.getcid((String)courseText.getSelectedItem());
	}
	
	public String getYear() {
		return (String)yearText.getSelectedItem();
	}
	
	public int getRollno() {
		return (Integer)rollnoText.getSelectedItem();
	}
}
