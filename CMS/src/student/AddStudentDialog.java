package student;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import common.db.Courcedb;
import common.db.Studentdb;
import cource.CourseData;

@SuppressWarnings("serial")
public class AddStudentDialog extends JDialog {

	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField emailField;
	private JTextField dobField;
	private JComboBox<String> genderComboBox;
	private JTextField fathersNameField;
	private JTextField mothersNameField;
	private JTextField phoneField;
	private JTextField batchNoField;
	private JComboBox<String> courseNameField;

	public AddStudentDialog(JFrame parent) {
		super(parent, "Add Student", true);
		setLayout(new BorderLayout());

		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(10, 2, 10, 10));
		formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		firstNameField = new JTextField();
		lastNameField = new JTextField();
		emailField = new JTextField();
		dobField = new JTextField();
		genderComboBox = new JComboBox<>(new String[] { "Male", "Female", "Other" });
		fathersNameField = new JTextField();
		mothersNameField = new JTextField();
		phoneField = new JTextField();
		batchNoField = new JTextField();
		courseNameField = new JComboBox<>(Courcedb.getCourseNames());

		formPanel.add(new JLabel("First Name:"));
		formPanel.add(firstNameField);
		formPanel.add(new JLabel("Last Name:"));
		formPanel.add(lastNameField);
		formPanel.add(new JLabel("Email ID:"));
		formPanel.add(emailField);
		formPanel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
		formPanel.add(dobField);
		formPanel.add(new JLabel("Gender:"));
		formPanel.add(genderComboBox);
		formPanel.add(new JLabel("Father's Name:"));
		formPanel.add(fathersNameField);
		formPanel.add(new JLabel("Mother's Name:"));
		formPanel.add(mothersNameField);
		formPanel.add(new JLabel("Phone No:"));
		formPanel.add(phoneField);
		formPanel.add(new JLabel("Batch No:"));
		formPanel.add(batchNoField);
		formPanel.add(new JLabel("Course Name:"));
		formPanel.add(courseNameField);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JButton addButton = new JButton("Add Student");
		JButton cancelButton = new JButton("Cancel");
		buttonPanel.add(addButton);
		buttonPanel.add(cancelButton);

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				StudentData sd = new StudentData();
				sd.setFirstName(firstNameField.getText());
				sd.setLastName(lastNameField.getText());
				sd.setEmailId(emailField.getText());
				sd.setD_o_b(dobField.getText());
				sd.setGender((String) genderComboBox.getSelectedItem());
				sd.setFathersName(fathersNameField.getText());
				sd.setMothersName(mothersNameField.getText());
				sd.setAdmisionDate(new java.util.Date().toString());
				sd.setActiveStatus(true);
				sd.setPhNo(phoneField.getText());
				sd.setRollNo(0);
				sd.setBatchNo(batchNoField.getText());
				CourseData cd = new CourseData(Courcedb.getcid((String) courseNameField.getSelectedItem()));
				sd.setCourseId(cd.getId());
				sd.setCurrentsen(1);
				sd.setClas((String) courseNameField.getSelectedItem());
				sd.setYear("1st year");

				Studentdb sdb = new Studentdb();
				sdb.addStudent(sd);
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

		setSize(400, 500);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	}

}
