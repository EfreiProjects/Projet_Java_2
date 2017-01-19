package data;

import java.io.Serializable;
import java.util.List;

public class teacher extends human implements Serializable{
	public teacher(){}
	
	public teacher(String name, String firstName, String civilState, int[] birth, String password){
		super(name, firstName, civilState, birth, password);
	}
	/*
	public String toString(){
		return this.civilState + " " + this.firstName +" "+ this.name + " né le " + this.birth[0] + "/" + this.birth[1] + "/" + this.birth[2] + " mot de passe : " + this.password;
	}
	*/
}