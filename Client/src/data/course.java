package data;

import java.io.Serializable;
import java.util.List;

public class course implements Serializable{
	private String name;
	private teacher tenured;
	private List assistants;
	private midtermExam CE;
	private finalExam DE;
	private project prj;
	
	public course(){};
	public course(String name, teacher tenured, List assistants){
		this.name = name;
		this.tenured = tenured;
		this.assistants = assistants;
		this.CE = new midtermExam();
		this.DE = new finalExam();
		this.prj = new project();
	}
	
	public String getName(){ return this.name; }
	public teacher getTenured(){ return this.tenured; }
	public List getAssistants(){ return this.assistants; }
	public exam getCE(){return this.CE; }
	public exam getDE(){return this.DE; }
	public exam getProject(){return this.prj; }
	
	public void setName(String name){ this.name = name; }
	public void setTenured(teacher tenured){ this.tenured = tenured; }
	public void addAssistant(human assistant){ this.assistants.add(assistant.getSurname() + ' ' + assistant.getName()); }
	public void setAssistants(List<student> assistants){ this.assistants = assistants; }
	
	public int computeAverage(){
		return (this.CE.getWeight() * this.CE.getGrade() + this.DE.getWeight() * this.DE.getGrade() + this.prj.getWeight() * this.prj.getGrade() ) / (this.CE.getWeight() + this.DE.getWeight() + this.prj.getWeight());
	}
	
	/*
	public String toString(){
		return this.name + " by " + this.tenured.getCivilState() + " " + this.tenured.getSurname() + " " + this.tenured.getName() + " helped by : " + this.assistants;
	}
	*/
}