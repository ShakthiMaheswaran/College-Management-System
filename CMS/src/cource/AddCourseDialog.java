package cource;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import common.db.Courcedb;

@SuppressWarnings("serial")
public class AddCourseDialog extends JDialog {

	private JTextField nameField;
	private JTextField durationField;
	private JTextField deptIdField;
	private JTextField courseCodeField;

	public AddCourseDialog(JFrame parent) {
		super(parent, "Add Course", true);
		setLayout(new BorderLayout());

		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(4, 2, 10, 10));
		formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		nameField = new JTextField();
		durationField = new JTextField();
		deptIdField = new JTextField();
		courseCodeField = new JTextField();

		formPanel.add(new JLabel("Course Name:"));
		formPanel.add(nameField);
		formPanel.add(new JLabel("Duration:"));
		formPanel.add(durationField);
		formPanel.add(new JLabel("Department ID:"));
		formPanel.add(deptIdField);
		formPanel.add(new JLabel("Course Code:"));
		formPanel.add(courseCodeField);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JButton addButton = new JButton("Add Course");
		JButton cancelButton = new JButton("Cancel");
		buttonPanel.add(addButton);
		buttonPanel.add(cancelButton);

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CourseData cd = new CourseData();
				cd.setName(nameField.getText());
				cd.setDuration(Integer.parseInt(durationField.getText()));
				cd.setDeptid(Integer.parseInt(deptIdField.getText()));
				cd.setCode(courseCodeField.getText());

				Courcedb cdb = new Courcedb();
				cdb.adddata(cd);
				dispose();
			}
		});

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
}