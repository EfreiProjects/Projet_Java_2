package data;

import java.io.Serializable;

public class exam implements Serializable{
	protected int weight;
	protected int grade;
	
	public exam(){ this.grade = 0; }
	
	public int getWeight(){ return this.weight; }
	public int getGrade(){ return this.grade; }
	
	public boolean setGrade( int grade ){
		if(grade >=0 && grade <=20){
			this.grade = grade;
			return true;
		}
		else
			return false;
	}
}
