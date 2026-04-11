package common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Departdb {
	
	private DataBase db = new DataBase();
	
	public String[] getdepts() {
		
		try {
			String[] str;
			
			Connection con = new DataBase().getconnection();
			
			PreparedStatement ps = con.prepareStatement("select count(*) from departments;");
		
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			str = new String[rs.getInt(1)];
			
			ps = con.prepareStatement("select Name from departments;");
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
	
	public int getdeptId(String name) {
		
		try {	
			
			Connection con = new DataBase().getconnection();
			
			PreparedStatement ps = con.prepareStatement("select department_id from departments where name = ?;");
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
