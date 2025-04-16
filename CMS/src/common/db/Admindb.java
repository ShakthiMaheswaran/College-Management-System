package common.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import admin.AdminData;

public class Admindb {

	DataBase db = new DataBase();

	public boolean login(String username, String password) {

		try {
			Connection con = db.getconnection();

			PreparedStatement ps = con.prepareStatement("select password from auth where username = ?;");
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			if (!!rs.isBeforeFirst()) {
				rs.next();

				if (password.equals(rs.getString(1))) {
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

	public static int getId(String username) {

		try {
			Connection con = new DataBase().getconnection();

			PreparedStatement ps = con.prepareStatement("select userID from auth where username = ?;");
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			if (!!rs.isBeforeFirst()) {
				rs.next();
				int i = rs.getInt(1);
				con.close();
				return i;

			}

		} catch (SQLException e) {

			e.printStackTrace();
			return 0;
		}

		return 0;
	}

	public void setData(AdminData ad) {

		try {

			Connection con = db.getconnection();

			PreparedStatement ps = con.prepareStatement(
					"select username,email,phone,role,department_id,status,acces,created_at from users where user_id = ?;");

			ps.setInt(1, ad.getAdminId());

			ResultSet rs = ps.executeQuery();
			rs.next();

			ad.setUsername(rs.getString(1));
			ad.setEmailId(rs.getString(2));
			ad.setPhno(rs.getString(3));
			ad.setRole(rs.getString(4));
			ad.setDeptId(rs.getInt(5));
			ad.setStatus(rs.getString(6));
			ad.setAcces(rs.getBoolean(7));

			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void addUser(AdminData ad) {

		try {

			Connection con = db.getconnection();

			PreparedStatement ps = con.prepareStatement(
					"insert into users(username,email,phone,role,department_id,status,acces,created_at)"
							+ "values(?,?,?,?,?,?,?,current_date);"); // change this to current time for the create_at
																		// while design UX/UI

			ps.setString(1, ad.getUsername());
			ps.setString(2, ad.getEmailId());
			ps.setString(3, ad.getPhno());
			ps.setString(4, ad.getRole());
			ps.setInt(5, ad.getDeptId());
			ps.setString(6, ad.getStatus());
			ps.setBoolean(7, ad.getAcces());

			ps.executeUpdate();

			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void removeAcces(AdminData ad) {

		try {

			Connection con = db.getconnection();

			PreparedStatement ps = con.prepareStatement("UPDATE users set acces = false where user_id = ?");

			ps.setInt(1, ad.getAdminId());

			ps.executeUpdate();


			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
