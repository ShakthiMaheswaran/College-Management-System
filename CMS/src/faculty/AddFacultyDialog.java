package faculty;

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

import common.db.Facultydb;

@SuppressWarnings("serial")
public class AddFacultyDialog extends JDialog {

	private JTextField fidField;
	private JTextField fnameField;
	private JTextField addressField;
	private JTextField emailField;
	private JTextField contactNoField;
	private JTextField qualificationField;
	private JTextField experienceField;
	private JComboBox<String> genderComboBox;
	private JTextField dobField;
	private JTextField joinDateField;

	public AddFacultyDialog(JFrame parent) {
		super(parent, "Add Faculty", true);
		setLayout(new BorderLayout());

		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(10, 2, 10, 10));
		formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		fidField = new JTextField();
		fnameField = new JTextField();
		addressField = new JTextField();
		emailField = new JTextField();
		contactNoField = new JTextField();
		qualificationField = new JTextField();
		experienceField = new JTextField();
		genderComboBox = new JComboBox<>(new String[] { "Male", "Female", "Other" });
		dobField = new JTextField();
		joinDateField = new JTextField();

		formPanel.add(new JLabel("Faculty ID:"));
		formPanel.add(fidField);
		formPanel.add(new JLabel("First Name:"));
		formPanel.add(fnameField);
		formPanel.add(new JLabel("Address:"));
		formPanel.add(addressField);
		formPanel.add(new JLabel("Email ID:"));
		formPanel.add(emailField);
		formPanel.add(new JLabel("Contact No:"));
		formPanel.add(contactNoField);
		formPanel.add(new JLabel("Qualification:"));
		formPanel.add(qualificationField);
		formPanel.add(new JLabel("Experience (Years):"));
		formPanel.add(experienceField);
		formPanel.add(new JLabel("Gender:"));
		formPanel.add(genderComboBox);
		formPanel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
		formPanel.add(dobField);
		formPanel.add(new JLabel("Join Date (YYYY-MM-DD):"));
		formPanel.add(joinDateField);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JButton addButton = new JButton("Add Faculty");
		JButton cancelButton = new JButton("Cancel");
		buttonPanel.add(addButton);
		buttonPanel.add(cancelButton);

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FacultyData fd = new FacultyData();
				fd.setfId(Integer.parseInt(fidField.getText()));
				fd.setFname(fnameField.getText());
				fd.setAddres(addressField.getText());
				fd.setEmailID(emailField.getText());
				fd.setContactNo(contactNoField.getText());
				fd.setQualification(qualificationField.getText());
				fd.setExperience(experienceField.getText());
				fd.setGender((String) genderComboBox.getSelectedItem());
				fd.setDob(dobField.getText());
				fd.setJoindate(joinDateField.getText());

				Facultydb fdb = new Facultydb();
				fdb.addFaculty(fd);
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