package test;

import common.db.Courcedb;

public class Test01 {

	
	
	public Test01(){
		ma();
	}
	
	public void ma() {
		
		//CourseData cd  =  new CourseData(1);	
		System.out.println(Courcedb.getCourseNames());
		
		for(String i : Courcedb.getCourseNames()) {
			System.out.println(i);
		}
		
		
		
	}
}
