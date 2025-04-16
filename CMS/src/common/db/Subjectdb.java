package common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.SubjectData;

public class Subjectdb {

	DataBase db  = new DataBase();
	
	public void adddata(SubjectData sd) {
	    try {
	        Connection con = db.getconnection();
	        
	       
	        PreparedStatement ps = con.prepareStatement("INSERT INTO subject (subjectName, subtype, cid) VALUES (?, ?, ?);");
	        ps.setString(1, sd.getSubjectName());
	        ps.setString(2, sd.getSubtype());
	        ps.setInt(3, sd.getCid());
	        
	        
	        ps.executeUpdate();
	        
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public void updatedata(SubjectData sd) {
	    try {
	        Connection con = db.getconnection();
	        
	        
	        PreparedStatement ps = con.prepareStatement("UPDATE subject SET subjectName = ?, subtype = ?, cid = ? WHERE subjectId = ?;");
	        ps.setString(1, sd.getSubjectName());
	        ps.setString(2, sd.getSubtype());
	        ps.setInt(3, sd.getCid());
	        ps.setInt(4, sd.getSubjectId());
	        
	        
	        ps.executeUpdate();
	        
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public void setdata(SubjectData sd) {
	    try {
	        Connection con = db.getconnection();
	        
	        
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM subject WHERE subjectId = ?;");
	        ps.setInt(1, sd.getSubjectId());
	        
	        
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) { 
	            sd.setSubjectName(rs.getString("subjectName"));
	            sd.setSubtype(rs.getString("subtype"));
	            sd.setCid(rs.getInt("cid"));
	        }
	        
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
