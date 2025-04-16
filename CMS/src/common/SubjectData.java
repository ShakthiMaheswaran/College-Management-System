package common;

import common.db.Subjectdb;

public class SubjectData {
	
	private int subjectId,cid;
	private String subjectName,subtype;
	
	public SubjectData(int sid) {
		this.subjectId = sid;
		
		getdata();
	}
	
	private void getdata() {
		Subjectdb sud = new Subjectdb(); 
	}

	public int getSubjectId() {
		return subjectId;
	}

	public int getCid() {
		return cid;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	
	
}
