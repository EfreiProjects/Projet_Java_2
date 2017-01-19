package data;

import java.io.Serializable;
import java.util.List;

public class specialisation implements Serializable{
	private String name;
	private List<course> courses;
	
	public specialisation(String name, List<course> courses){
		this.name = name;
		this.courses = courses;
	}
	
	public String getName(){ return this.name; }
	public List<course> getCourses(){ return this.courses; }
	
	public void setName(String name){ this.name = name; }
	public void setCourses(List<course> courses){ this.courses = courses; }
	public boolean addCourse(course toAdd){
		if(this.courses.indexOf(toAdd) == -1){
			this.courses.add(toAdd);
			return true;
		}
		else
			return false;
			
	}
	public boolean removeCourse(course toDelete){
		if(this.courses.indexOf(toDelete) != -1){
			this.courses.remove(this.courses.indexOf(toDelete));
			return true;
		}
		else
			return false;
	}
	
	
	public String toString(){
		return this.name + " : " + this.courses.get(0).getName() + ", " + this.courses.get(1).getName() + ", " + this.courses.get(2).getName();
	}
	
}