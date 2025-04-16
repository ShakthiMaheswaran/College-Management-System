package cource;

import common.db.Courcedb;

public class CourseData {
	private String name,code;
	private int id,duration,deptid;
	private int subjectCount;
	
	public CourseData(int id) {
		this.id = id;
		getdata();
	}
	public CourseData() {
		
	}
	
	
	public void getdata() {
		Courcedb cdb = new Courcedb();
		cdb.setdata(this);
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	public int getDuration() {
		return duration;
	}
	public int getDeptid() {
		return deptid;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}


	public int getSubjectCount() {
		return subjectCount;
	}


	public void setSubjectCount(int subjectCount) {
		this.subjectCount = subjectCount;
	}
	
}
