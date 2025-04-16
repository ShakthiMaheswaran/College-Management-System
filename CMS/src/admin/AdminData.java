package admin;

import common.db.Admindb;

public class AdminData {

	private int adminId, deptId;

	private String username, emailId, phno, role, status;

	private boolean acces;

	public AdminData(int Id) {
		this.adminId = Id;

		getData();
	}

	public AdminData() {

	}

	public void getData() {
		Admindb adb = new Admindb();
		adb.setData(this);
		System.out.println(this.username);
	}

	public boolean getAcces() {
		return acces;
	}

	public int getAdminId() {
		return adminId;
	}

	public String getUsername() {
		return username;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getPhno() {
		return phno;
	}

	public String getRole() {
		return role;
	}

	public int getDeptId() {
		return deptId;
	}

	public String getStatus() {
		return status;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public void setRole(String roll) {
		this.role = roll;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAcces(boolean acces) {
		this.acces = acces;
	}

}
