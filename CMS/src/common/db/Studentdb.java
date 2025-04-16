package common.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.crypto.Data;

import student.StudentData;

public class Studentdb {

	private DataBase db = new DataBase();

	public boolean login(int studnetId, String password) {

		try {
			Connection con = db.getconnection();

			PreparedStatement ps = con.prepareStatement("select d_o_b from student where studentId = ?;");
			ps.setInt(1, studnetId);

			ResultSet rs = ps.executeQuery();

			if (!!rs.isBeforeFirst()) {
				rs.next();

				if (password.equals(rs.getDate(1).toString())) {
					con.close();
					return true;
				}

				con.close();

			}
			return false;
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}

	public void setData(StudentData st) {

		try {
			Connection con = db.getconnection();

			PreparedStatement ps = con.prepareStatement("select * from student where studentId = ?;");
			ps.setInt(1, st.getStudentId());

			ResultSet rs = ps.executeQuery();
			rs.next();

			st.setFirstName(rs.getString(2));
			st.setLastName(rs.getString(3));
			st.setEmailId(rs.getString(4));
			st.setD_o_b(rs.getDate(5).toString());
			st.setGender(rs.getString(6));
			st.setFathersName(rs.getString(7));
			st.setMothersName(rs.getString(8));
			st.setAdmisionDate(rs.getDate(9).toString());
			st.setActiveStatus(rs.getBoolean(10));
			st.setPhNo(rs.getString(11));
			st.setRollNo(rs.getInt(12));
			st.setBatchNo(rs.getString(13));
			st.setCourseId(rs.getInt(14));
			st.setCurrentsen(rs.getInt(15));
			st.setClas(rs.getString(16));
			st.setYear(rs.getString(17));

			con.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public void addStudent(StudentData sd) {

		try {

			// Make it more Exception handled

			Connection con = db.getconnection();

			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO student (firstName, lastName, emailId, d_o_b, gender, fathersName, mothersName, "
							+ "admissionDate, activeStatus, phNo, rollNo, batchNo, courseId, currentSem, class, year) VALUES "
							+ "(?, ?, ?, ?, ?, ?, ?, CURRENT_DATE, ?, ?, ?, ?, ?, ?, ?, ?);");

			ps.setString(1, sd.getFirstName());
			ps.setString(2, sd.getLastName());
			ps.setString(3, sd.getEmailId());
			ps.setString(4, sd.getD_o_b());
			ps.setString(5, sd.getGender());
			ps.setString(6, sd.getFathersName());
			ps.setString(7, sd.getMothersName());
			ps.setBoolean(8, sd.getActiveStatus());
			ps.setString(9, sd.getPhNo());
			ps.setInt(10, sd.getRollNo());
			ps.setString(11, sd.getBatchNo());
			ps.setInt(12, sd.getCourseId());
			ps.setInt(13, sd.getCurrentsen());
			ps.setString(14, sd.getClas());
			ps.setString(15, sd.getYear());

			ps.executeUpdate();

			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void updateStudent(StudentData sd) {

		try {
			Connection con = db.getconnection();

			PreparedStatement ps = con.prepareStatement(
					"UPDATE student set firstName = ?, lastName = ?, emailId = ?, d_o_b = ?, gender = ?, fathersName = ?"
							+ ", mothersName = ?, admissionDate = ?, activeStatus = ?, phNo = ?, rollNo = ?, batchNo = ?, , courseId = ?, currentSem = ?, class = ? "
							+ ", year = ? where studentId = ?");

			ps.setString(1, sd.getFirstName());
			ps.setString(2, sd.getLastName());
			ps.setString(3, sd.getEmailId());
			ps.setDate(4, Date.valueOf(sd.getD_o_b()));
			ps.setString(5, sd.getGender());
			ps.setString(6, sd.getFathersName());
			ps.setString(7, sd.getMothersName());
			ps.setDate(8, Date.valueOf(sd.getAdmisionDate()));
			ps.setBoolean(9, sd.getActiveStatus());
			ps.setString(10, sd.getPhNo());
			ps.setInt(11, sd.getRollNo());
			ps.setString(12, sd.getBatchNo());
			ps.setInt(13, sd.getCourseId());
			ps.setInt(14, sd.getCurrentsen());
			ps.setString(15, sd.getClas());
			ps.setString(16, sd.getYear());
			ps.setInt(17, sd.getStudentId());

			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void inacative(int sid) {
		try {
			Connection con = db.getconnection();

			PreparedStatement ps = con.prepareStatement("UPDATE student set activeStatus = false where studentId = ?");

			ps.setInt(1, sid);

			ps.executeUpdate();

			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public int studentCount() {

		try {

			Connection con = db.getconnection();

			PreparedStatement ps = con.prepareStatement("select count(*) from student;");

			ResultSet rs = ps.executeQuery();
			rs.next();

			int i = rs.getInt(1);

			con.close();

			return i;
		} catch (SQLException e) {

			e.printStackTrace();
			return 0;
		}

	}

	public static ResultSet getstudents() {

		try {

			Connection con = new DataBase().getconnection();

			PreparedStatement ps = con.prepareStatement(" select class 'Class',rollNo as 'Role Number', firstName  "
					+ "as 'Student Name', (select cName from course where course.courseId = student.courseId) as 'Course Name'"
					+ ",year as 'Year' from student;");

			ResultSet rs = ps.executeQuery();

			return rs;

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}

	}
}
