package student;

import java.sql.Date;

import common.db.Studentdb;

public class StudentData {
	
	private int studentId,rollNo,courseId,currentsen;
	
	private boolean activeStatus;
	
	private String 	firstName,LastName,emailId,d_o_b,gender,
					fathersName,mothersName,admisionDate,phNo,batchNo,clas,year;

	public StudentData(int studentid) {
		this.studentId = studentid;
		
		getData();
		
		
	}
	public StudentData() {
		
	}
	
	public StudentData(String fn,String ln,String emid,Date dob,String gen,String fan,String mon,
						Date addate,Boolean as,String pno,int rno,String bno,int cId,int cursem,String clas,String yer) {
		
		this.firstName = fn;
		this.LastName = ln;
		this.emailId = emid;
		this.d_o_b = dob.toString();
		this.gender = gen;
		this.fathersName = fan;
		this.mothersName = mon;
		this.admisionDate = addate.toString();
		this.activeStatus = as;
		this.phNo = pno;
		this.rollNo = rno;
		this.batchNo = bno;
		this.courseId = cId;
		this.currentsen = cursem;
		this.clas = clas;
		this.year =yer;
		
	}
	
	public void getData() {
		Studentdb s = new Studentdb();
		s.setData(this);
		System.out.println(this);
	}
	
	public int getStudentId() {
		return studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getPhNo() {
		return phNo;
	}

	public String getD_o_b() {
		return d_o_b;
	}

	public String getGender() {
		return gender;
	}

	public String getFathersName() {
		return fathersName;
	}

	public String getMothersName() {
		return mothersName;
	}

	public int getRollNo() {
		return rollNo;
	}

	public String getAdmisionDate() {
		return admisionDate;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public boolean getActiveStatus() {
		return activeStatus;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}

	public void setD_o_b(String d_o_b) {
		this.d_o_b = d_o_b;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public void setAdmisionDate(String admisionDate) {
		this.admisionDate = admisionDate;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public int getCourseId() {
		return courseId;
	}

	public int getCurrentsen() {
		return currentsen;
	}

	public String getClas() {
		return clas;
	}

	public String getYear() {
		return year;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public void setCurrentsen(int currentsen) {
		this.currentsen = currentsen;
	}

	public void setClas(String clas) {
		this.clas = clas;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	
					
	
	
}
