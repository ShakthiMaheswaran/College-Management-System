package common.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import faculty.FacultyData;

public class Facultydb {
	
	DataBase db = new DataBase();

	public boolean login(String username, String password) {
		
		try {
			Connection con = db.getconnection();
			
			PreparedStatement ps = con.prepareStatement("select password from auth where username = ?;");
			ps.setString(1, username);
		
			ResultSet rs = ps.executeQuery();
			
			if(!!rs.isBeforeFirst() ) {
				rs.next();
				
				if(password.equals(rs.getString(1))) {
					con.close();
					return true;
				}
				System.out.println(rs.getString(1)+password);
				con.close();
				
			}
			return false;
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
		
	}
	public static int getId(String username) {
		
		try {
			Connection con = new DataBase().getconnection();
			
			PreparedStatement ps = con.prepareStatement("select userID from auth where username = ?;");
			ps.setString(1, username);
		
			ResultSet rs = ps.executeQuery();
			
			if(!!rs.isBeforeFirst() ) {
				rs.next();
				int i = rs.getInt(1);
				con.close();
				return i;
				
				
			}
			
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
			return 0;
		}
		
		return 0;
	}

	public void setData(FacultyData fd) {
		
		try {
			Connection con = db.getconnection();
			
			PreparedStatement ps = con.prepareStatement("select * from faculty where fId = ?;");
			ps.setInt(1, fd.getfId());
		
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			fd.setFname(rs.getString(2));
			fd.setAddres(rs.getString(3));
			fd.setEmailID(rs.getString(4));
			fd.setContactNo(rs.getString(5));
			fd.setQualification(rs.getString(6));
			fd.setExperience(rs.getString(7));
			fd.setGender(rs.getString(8));
			fd.setDob(rs.getDate(9).toString());
			fd.setJoindate(rs.getDate(10).toString());
			
			
			
			con.close();
			
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		
	}
	
	public void addFaculty(FacultyData fd) {
		
		try {
			
			// Make it more Exception handled

			
			Connection con = db.getconnection();
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO faculty(fName, addres, emailId, contactNo, qualification, experience, gender, dob, joindate)"
					+ "values(?,?,?,?,?,?,?,?,?);");
			
			
			ps.setString(1, fd.getFname());
			ps.setString(2, fd.getAddres());
			ps.setString(3, fd.getEmailID());
			ps.setString(4, fd.getContactNo());
			ps.setString(5, fd.getQualification());
			ps.setString(6, fd.getExperience());
			ps.setString(7, fd.getGender());
			ps.setDate(8, Date.valueOf(fd.getDob()));
			ps.setDate(9, Date.valueOf(fd.getJoindate()));
			
			
			int i = ps.executeUpdate();
			
			if(i==1)
				System.out.println("succesfully updated");
			else
				System.out.println("failed");
			
			con.close();
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void upadateFaculity(FacultyData fd) {
		
		try {
			Connection con = db.getconnection();
			
			PreparedStatement ps = con.prepareStatement("UPDATE faculty set fname = ?, addres = ?, emailID = ?, contactNo = ?, qualification = ?, experience = ?, "
					+ "gender = ?, dob = ?, joindate = ? where fId = ?");
			
			ps.setInt(10, fd.getfId());
			ps.setString(1, fd.getFname());
			ps.setString(2, fd.getAddres());
			ps.setString(3, fd.getEmailID());
			ps.setString(4, fd.getContactNo());
			ps.setString(5, fd.getQualification());
			ps.setString(6, fd.getExperience());
			ps.setString(7, fd.getGender());
			ps.setDate(8, Date.valueOf(fd.getDob()));
			ps.setDate(9, Date.valueOf(fd.getJoindate()));
			
			
			
			int i = ps.executeUpdate();
			
			if(i==1)
				System.out.println("succesfully updated");
			else
				System.out.println("failed");
			
			con.close();
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public int facultyCount() {
		
		try {
			
			Connection con = db.getconnection();
			
			PreparedStatement ps = con.prepareStatement("select count(*) from faculty;");
					
			
		
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			int i =rs.getInt(1);
			
			con.close();
			
			return i;
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			return 0;
		}
	
	}
	
	public static ResultSet getfacultys() {
		
		try {
			
			Connection con = new DataBase().getconnection();
			
			PreparedStatement ps = con.prepareStatement("select fId as 'Faculty ID',fname as 'Faculty Name', "
					+ "emailID as 'Email ID', qualification as 'Qualification', experience as 'Experience' from faculty;");
					
			
		
			ResultSet rs = ps.executeQuery();
			
			
			
			
			
			return rs;
			
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
	}

}
