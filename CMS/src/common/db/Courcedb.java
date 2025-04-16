package common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cource.CourseData;

public class Courcedb {
	
	DataBase db  = new DataBase();
	
	public void setdata(CourseData cd) {
		try {
			Connection con = db.getconnection();
			
			PreparedStatement ps = con.prepareStatement("select * from course where courseId = ?;");
			ps.setInt(1, cd.getId());
		
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			cd.setName(rs.getString(2));
			cd.setDuration(rs.getInt(3));
			cd.setDeptid(rs.getInt(4));
			cd.setCode(rs.getString(5));
			
			ps = con.prepareStatement("select count(*) from subject where cid = ?;");
			ps.setInt(1, cd.getId());
			
			rs = ps.executeQuery();
			rs.next();
			
			cd.setSubjectCount(rs.getInt(1));
			
			
			con.close();
			
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
			
		}
	}
	
	public void adddata(CourseData cd) {
	    try {
	        Connection con = db.getconnection();
	        
	        
	        PreparedStatement ps = con.prepareStatement("INSERT INTO course (cName, cDuration, deptId, coursecode) VALUES (?, ?, ?, ?);");
	        ps.setString(1, cd.getName());
	        ps.setInt(2, cd.getDuration());
	        ps.setInt(3, cd.getDeptid());
	        ps.setString(4, cd.getCode());
	        
	        
	        ps.executeUpdate();
	        
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void editdata(CourseData cd) {
	    try {
	        Connection con = db.getconnection();
	        
	       
	        PreparedStatement ps = con.prepareStatement("UPDATE course SET cName = ?, cDuration = ?, deptId = ?, coursecode = ? WHERE courseId = ?;");
	        ps.setString(1, cd.getName());
	        ps.setInt(2, cd.getDuration());
	        ps.setInt(3, cd.getDeptid());
	        ps.setString(4, cd.getCode());
	        ps.setInt(5, cd.getId());
	        
	        
	        ps.executeUpdate();
	        
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static ResultSet getcourcse() {
		
		try {
			
			Connection con = new DataBase().getconnection();
			
			PreparedStatement ps = con.prepareStatement("select coursecode as 'Course Code', cName as 'Course Name'"
					+ ", (select count(*) from subject where cid = course.courseId) as 'Subjects', (select count(*) from student where "
					+ "courseId = course.courseId) as 'Students', cDuration as 'Total years' from course");
		
			ResultSet rs = ps.executeQuery();
			
			return rs;
			
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static String[] getCourseNames() {
		
		try {
			String[] str;
			
			Connection con = new DataBase().getconnection();
			
			PreparedStatement ps = con.prepareStatement("select count(*) from course;");
		
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			str = new String[rs.getInt(1)];
			
			ps = con.prepareStatement("select cName from course;");
			rs = ps.executeQuery();
			int i = 0;
			while(rs.next()) {
				str[i++] = rs.getString(1);
			}
			con.close();
			return str;
			
			
			
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
	}
	public static int getcid(String name) {
		try {
			
			
			Connection con = new DataBase().getconnection();
			
			PreparedStatement ps = con.prepareStatement("select courseId from course where cName = ?;");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			int i = rs.getInt(1);
			con.close();
			return i;
			
			
			
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			return 0;
		}
	}
	
	
}
