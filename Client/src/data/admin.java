package data;

import java.util.List;

public class admin extends human{
	public admin(String name, String firstName, String civilState, int[] birth, String password) {
		super(name, firstName, civilState, birth, password);
	}
	
	public student createStudent(String name, String firstName, int[] birth, String password, String CivilState, specialisation spe, List<course> mendatory, teacher referent, int year){
		return new student(name, firstName, birth, CivilState, spe, mendatory, password, referent, year);
	}
	
	public teacher createTeacher(String name, String firstName, String civilState, int[] birth, String password){
		return new teacher(name, firstName, civilState, birth, password);
	}
	
	public course createCourse(String name, teacher tenured, List assistants){
		return new course(name, tenured, assistants);
	}
	
	public specialisation createSpe(String name, List<course> courses){
		return new specialisation(name, courses);
	}
	
	public String toString(){
	    return this.civilState + " " + this.firstName +" "+ this.name + " né le " + this.birth[0] + "/" + this.birth[1] + "/" + this.birth[2] + " mot de passe : " + this.password;
	}
}
