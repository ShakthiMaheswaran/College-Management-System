package faculty;

import common.db.Facultydb;

public class FacultyData {
	
	private int fId;
	
	private String fname, addres, emailID, contactNo, qualification, experience, gender, dob, joindate;
	
	public FacultyData(String fn,String ad,String emid,String conno,String quli,String exp,String gen,String dob,String jodate) {
		
		this.fname = fn;
		this.addres = ad;
		this.emailID = emid;
		this.contactNo = conno;
		this.qualification = quli;
		this.experience = exp;
		this.gender = gen;
		this.dob = dob;
		this.joindate = jodate;
		
	}
	
	public FacultyData(int fid) {
		
		this.fId = fid;
		
		getData();
	}
	public FacultyData() {
		
		
		
		
	}
	

	private void getData() {
		
		Facultydb fdb = new Facultydb();
		fdb.setData(this);
		
		System.out.println(this);
		System.out.println(this.fname);
	}

	public int getfId() {
		return fId;
	}

	public String getFname() {
		return fname;
	}

	public String getAddres() {
		return addres;
	}

	public String getEmailID() {
		return emailID;
	}

	public String getContactNo() {
		return contactNo;
	}

	public String getQualification() {
		return qualification;
	}

	public String getExperience() {
		return experience;
	}

	public String getGender() {
		return gender;
	}

	public String getDob() {
		return dob;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setfId(int fId) {
		this.fId = fId;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
	
	
}
