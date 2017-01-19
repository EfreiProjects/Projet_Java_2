package data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class human implements Serializable{
	protected String name;
	protected String firstName;
	protected String civilState;
	protected int birth[] = new int[3];
	protected String password;
	
	human(){}
	
	human(String name, String firstName, String civilState, int[] birth, String password){
		this.name = name;
		this.firstName = firstName;
		this.civilState = civilState;
		this.setBirth(birth[0], birth[1], birth[2]);
		this.password = password;
	}
	
	public String getName(){ return this.name; }
	public String getSurname(){ return this.firstName; }
	public String getCivilState(){ return this.civilState; }
	public int[] getBirth(){ return this.birth; }
	public String getPassword(){ return this.password; }
	
	public void setName(String name){ this.name = name; }
	public void setFirstName(String firstName){ this.firstName = firstName; }
	public void setCivilState(String civilState){ this.civilState = civilState; }
	public boolean setBirth(int day, int month, int year){
		if((day > 0 && day <= 31) && (month > 0 && month <= 12) && (year > 0 && year <= Calendar.getInstance().get(Calendar.YEAR))){
			this.birth[0] = day;
			this.birth[1] = month;
			this.birth[2] = year;
			return true;
		}
		else
			return false;
	}
	public void setPassword(String password){ this.password = password; }
}