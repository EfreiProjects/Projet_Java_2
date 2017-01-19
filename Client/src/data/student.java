package data;

import java.util.ArrayList;
import java.util.List;

public class student extends human{
	private int year;
	private specialisation spe;
	private List<course> mendatory;
	private teacher referent;
	
	public student(){ super(); }
	public student(String name, String firstName, int[] birth, String civilState,  specialisation spe, List<course> mendatory, String password, teacher referent, int year){
		super(name, firstName, civilState, birth, password);
		this.referent = referent;
		this.spe = spe;
		this.year = year;
		this.mendatory = mendatory;
		for(int i=0; i<=this.spe.getCourses().size(); i++){
			this.spe.getCourses().get(i).getCE().setGrade(0);
			this.spe.getCourses().get(i).getDE().setGrade(0);
			this.spe.getCourses().get(i).getProject().setGrade(0);
		}
		for(int i=0; i<=this.mendatory.size(); i++){
			this.mendatory.get(i).getCE().setGrade(0);
			this.mendatory.get(i).getDE().setGrade(0);
			this.mendatory.get(i).getProject().setGrade(0);
		}
		
	}
	public int getYear(){ return this.year; }
	public specialisation getSpe(){ return this.spe; }
	public List<course> getMendatory(){ return this.mendatory; }
	public teacher getReferent(){ return this.referent; }
	
	public void setYear(int year){ this.year = year; }

	public void setSpe(specialisation spe){ this.spe = spe; }
	public boolean addMendatory(course toAdd){
		if(spe.getCourses().indexOf(toAdd) == -1){
			mendatory.add(toAdd);
			return true;
		}
		else
			return false;
	}
	public void setMendatory(List<course> mendatory){ this.mendatory = mendatory; }
	public boolean removeMendatory(course toDelete){
		if(this.mendatory.indexOf(toDelete) != -1){
			this.mendatory.remove(this.mendatory.indexOf(toDelete));
			return true;
		}
		else
			return false;
	}
	public void setReferent(teacher referent){ this.referent = referent; }
	public boolean setGrade(int exam, course toGrade, int grade){
		if(this.spe.getCourses().indexOf(toGrade) != -1){
			if(exam == 1){ // si CE
				this.spe.getCourses().get(this.spe.getCourses().indexOf(toGrade)).getCE().setGrade(grade);
				return true;
			}
			else if(exam == 2){ // si DE
				this.spe.getCourses().get(this.spe.getCourses().indexOf(toGrade)).getDE().setGrade(grade);
				return true;
			}
			else if(exam == 3){ // si Projet
				this.spe.getCourses().get(this.spe.getCourses().indexOf(toGrade)).getProject().setGrade(grade);
			}
			return false;
		}
		else if(this.mendatory.indexOf(toGrade) != -1)
			if(exam == 1){ // si CE
				this.mendatory.get(this.mendatory.indexOf(toGrade)).getCE().setGrade(grade);
				return true;
			}
			else if(exam == 2){ // si DE
				this.mendatory.get(this.mendatory.indexOf(toGrade)).getDE().setGrade(grade);
				return true;
			}
			else if(exam == 3){ // si Projet
				this.mendatory.get(this.mendatory.indexOf(toGrade)).getProject().setGrade(grade);
			}
			return false;
	}
	
	/*
	public String toString(){
		return this.civilState + " " + this.firstName +" "+ this.name + " né le " + this.birth[0] + "/" + this.birth[1] + "/" + this.birth[2] + " mot de passe : " + this.password + " Référent : " + this.referent.getSurname() + " " + this.referent.getName() + " Spécialisation : " + this.spe.getName() ;
	}
	*/
}