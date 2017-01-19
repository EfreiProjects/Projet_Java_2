package projetJava2Server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import data.admin;
import data.course;
import data.human;
import data.specialisation;
import data.student;
import data.teacher;

public class parser {
	private List<human> adminList = new ArrayList<human>();
	private List<teacher> teacherList = new ArrayList<teacher>();
	private List<student> studentList = new ArrayList<student>();
	private List<course> courseList = new ArrayList<course>();
	private List<specialisation> specialisationList = new ArrayList<specialisation>();
	
	public parser(){
		this.adminList = parseAdmin();
		this.teacherList = parseTeacher();
		this.courseList = parseCourse();
		this.specialisationList = parseSpecialisation();
		this.studentList = parseStudent();
		
		writeStudent();
	}
	
	public void setAdminList(List<human> adminList){ this.adminList = adminList; }
	public boolean addAdmin(admin toAdd){
		if(this.adminList.indexOf(toAdd) != -1)
			return false;
		else
			this.adminList.add(toAdd);
		return true;
	}
	public boolean removeAdmin(String admin){
		for(human a : this.adminList){
			if(admin.equals(a.getSurname()+" "+a.getName())){
				this.adminList.remove(this.adminList.indexOf(a));
				return true;
			}
		}
		return false;
	}
	
	public void setTeacherList(List<teacher> teacherList){ this.teacherList = teacherList; }
	public boolean addTeacher(teacher toAdd){
		if(this.teacherList.indexOf(toAdd) != -1)
			return false;
		else
			this.teacherList.add(toAdd);
		return true;
	}
	public boolean removeTeacher(String teacher){
		for(teacher t : this.teacherList){
			if(teacher.equals(t.getSurname()+" "+t.getName())){
				this.teacherList.remove(this.teacherList.indexOf(t));
				return true;
			}
		}
		return false;
	}
	
	public void setStudentList(List<student> studentList){ this.studentList = studentList; }
	public boolean addStudent(student toAdd){
		if(this.studentList.indexOf(toAdd) != -1)
			return false;
		else
			this.studentList.add(toAdd);
		return true;
	}
	public boolean removeStudent(String student){
		for(student s : this.studentList){
			if(student.equals(s.getSurname()+" "+s.getName())){
				this.studentList.remove(this.studentList.indexOf(s));
				return true;
			}
		}
		return false;
	}
	
	public void setCourseList(List<course> courseList){ this.courseList = courseList; }
	public boolean addCourse(course toAdd){
		if(this.courseList.indexOf(toAdd) != -1)
			return false;
		else
			this.courseList.add(toAdd);
		return true;
	}
	public boolean removeCourse(String course ){
		for(course c : this.courseList){
			if(course.equals(c.getName())){
				this.courseList.remove(this.courseList.indexOf(c));
				return true;
			}
		}
		return false;
	}
	
	public void setSpecialisationList(List<specialisation> specialisationList){ this.specialisationList = specialisationList; }
	public boolean addSpecialisation(specialisation toAdd){
		if(this.specialisationList.indexOf(toAdd) != -1)
			return false;
		else
			this.specialisationList.add(toAdd);
		return true;
	}
	public boolean removeSpecialisation(String specialisation){
		for(specialisation s : this.specialisationList){
			if(specialisation.equals(s.getName())){
				this.specialisationList.remove(this.specialisationList.indexOf(s));
				return true;
			}
		}
		return false;
	}
	
	public List<human> getAdmin(){ return this.adminList; }
	public List<teacher> getTeacher(){ return this.teacherList; }
	public List<student> getStudent(){ return this.studentList; }
	public List<course> getCourses(){ return this.courseList; }
	public List<specialisation> getSpecialisation(){ return this.specialisationList; }
	
	public List<human> parseAdmin(){
		List<human> result = new ArrayList<human>();
		admin temp;
		int[] tempBirth;
		try {	 
			File fXmlFile = new File("src/admin.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			 
			doc.getDocumentElement().normalize();
			 
			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			 
			NodeList nList = doc.getElementsByTagName("admin");
			
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				 
				//System.out.println("\nCurrent Element :" + nNode.getNodeName());
				 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					tempBirth = new int[]{Integer.parseInt(eElement.getElementsByTagName("birthDay").item(0).getTextContent()), Integer.parseInt(eElement.getElementsByTagName("birthMonth").item(0).getTextContent()), Integer.parseInt(eElement.getElementsByTagName("birthYear").item(0).getTextContent())};
					temp = new admin(eElement.getElementsByTagName("name").item(0).getTextContent(), eElement.getElementsByTagName("firstname").item(0).getTextContent(), eElement.getElementsByTagName("civilstate").item(0).getTextContent(), tempBirth, eElement.getElementsByTagName("password").item(0).getTextContent());
					//System.out.println(temp);
					result.add(temp);
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	return result;
	}
	
	public List<teacher> parseTeacher(){
		List<teacher> result = new ArrayList<teacher>();
		teacher temp;
		int[] tempBirth;
		try {	 
			File fXmlFile = new File("src/teacher.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			 
			doc.getDocumentElement().normalize();
			 
			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			 
			NodeList nList = doc.getElementsByTagName("teacher");
			
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				 
				//System.out.println("\nCurrent Element :" + nNode.getNodeName());
				 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					tempBirth = new int[]{Integer.parseInt(eElement.getElementsByTagName("birthDay").item(0).getTextContent()), Integer.parseInt(eElement.getElementsByTagName("birthMonth").item(0).getTextContent()), Integer.parseInt(eElement.getElementsByTagName("birthYear").item(0).getTextContent())};
					temp = new teacher(eElement.getElementsByTagName("name").item(0).getTextContent(), eElement.getElementsByTagName("firstname").item(0).getTextContent(), eElement.getElementsByTagName("civilstate").item(0).getTextContent(), tempBirth, eElement.getElementsByTagName("password").item(0).getTextContent());
					//System.out.println(temp);
					result.add(temp);
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	return result;
	}
	
	public List<course> parseCourse(){
		List<course> result = new ArrayList<course>();
		course temp;
		teacher tenured;
		List<String> assistants = new ArrayList<String>();
		try {	 
			File fXmlFile = new File("src/courses.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			 
			doc.getDocumentElement().normalize();
			 
			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			 
			NodeList nList = doc.getElementsByTagName("course");
			
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				 
				//System.out.println("\nCurrent Element :" + nNode.getNodeName());
				 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					tenured = this.teacherList.get(0);
					for(teacher t : this.teacherList){
						if(eElement.getElementsByTagName("tenured").item(0).getTextContent().equals(t.getSurname()+' '+t.getName())){
							tenured = t;
						}
					}
					temp = new course(eElement.getElementsByTagName("name").item(0).getTextContent(), tenured, new ArrayList<String>());
					for(int j=0; j<eElement.getElementsByTagName("AssistantName").getLength(); j++){
						temp.getAssistants().add(eElement.getElementsByTagName("AssistantName").item(j).getTextContent());
					}
					//System.out.println(temp);
					result.add(temp);
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public List<specialisation> parseSpecialisation(){
		List<specialisation> result = new ArrayList<specialisation>();
		specialisation temp;
		List<course> speCourses = new ArrayList<course>();
		try {	 
			File fXmlFile = new File("src/specialisations.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			 
			doc.getDocumentElement().normalize();
			 
			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			 
			NodeList nList = doc.getElementsByTagName("specialisation");
			
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				//System.out.println("lol");
				 
				//System.out.println("\nCurrent Element :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					temp = new specialisation(eElement.getElementsByTagName("name").item(0).getTextContent(), new ArrayList<course>());
					for(int j=0; j<eElement.getElementsByTagName("courseName").getLength(); j++){
						for(course c : this.courseList){
							if(eElement.getElementsByTagName("courseName").item(j).getTextContent().equals(c.getName())){
								temp.addCourse(c);
							}
						}
					}
					result.add(temp);
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	return result;
	}
	
	public List<student> parseStudent(){
		List<student> result = new ArrayList<student>();
		student temp;
		int[] tempBirth;
		specialisation spe;
		teacher referent;
		List<course> mendatoryList = new ArrayList<course>();
		try {	 
			File fXmlFile = new File("src/students.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			 
			doc.getDocumentElement().normalize();
			 
			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			 
			NodeList nList = doc.getElementsByTagName("student");
			
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				 
				//System.out.println("\nCurrent Element :" + nNode.getNodeName());
				 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					tempBirth = new int[]{Integer.parseInt(eElement.getElementsByTagName("birthDay").item(0).getTextContent()), Integer.parseInt(eElement.getElementsByTagName("birthMonth").item(0).getTextContent()), Integer.parseInt(eElement.getElementsByTagName("birthYear").item(0).getTextContent())};
					
					referent = this.teacherList.get(0);
					for(teacher t : this.teacherList){
						if(eElement.getElementsByTagName("referent").item(0).getTextContent().equals(t.getSurname()+' '+t.getName()))
							referent = t;
					}
					
					spe = this.specialisationList.get(0);
					for(specialisation s : this.specialisationList){
						if(eElement.getElementsByTagName("specialisation").item(0).getTextContent().equals(s.getName()))
							spe = s;
					}
					mendatoryList.clear();
					for(course c : this.courseList){
						for(int j=0; j < eElement.getElementsByTagName("mendatoryName").getLength(); j++){
							if(eElement.getElementsByTagName("mendatoryName").item(j).getTextContent().equals(c.getName())){
								mendatoryList.add(c);
							}
						}
					}
					System.out.println();
					temp = new student(eElement.getElementsByTagName("name").item(0).getTextContent(), eElement.getElementsByTagName("firstname").item(0).getTextContent(), tempBirth, eElement.getElementsByTagName("civilstate").item(0).getTextContent(), spe, mendatoryList, eElement.getElementsByTagName("password").item(0).getTextContent(), referent, Integer.parseInt(eElement.getElementsByTagName("year").item(0).getTextContent()));
					//System.out.println(temp);
					result.add(temp);
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	return result;
	}
	
	public void writeToXML(String location, StringBuffer content){
		PrintWriter ecri ;
		try
		{
			ecri = new PrintWriter(new FileWriter(location));
			ecri.print(content);
			ecri.flush();
			ecri.close();
		}//try
		catch (NullPointerException a)
		{
			System.out.println("Erreur : pointeur null");
		}
		catch (IOException a)
		{
			System.out.println("Problème d'IO");
		}
	}
	
	public void writeAll(){
		this.writeAdmin();
		this.writeTeacher();
		this.writeStudent();
		this.writeCourse();
		this.writeSpecialisation();
	}
	
	public void writeAdmin(){
		StringBuffer toWrite = new StringBuffer();
		toWrite.append("<?xml version=\"1.0\"?>").append("\r\n");
		toWrite.append("<admins>").append("\r\n");
		for(human a : this.adminList){
			toWrite.append("\t").append("<admin>").append("\r\n");
			toWrite.append("\t").append("\t").append("<firstname>"+a.getSurname()+"</firstname>").append("\r\n");
			toWrite.append("\t").append("\t").append("<name>"+a.getName()+"</name>").append("\r\n");
			toWrite.append("\t").append("\t").append("<password>"+a.getPassword()+"</password>").append("\r\n");
			toWrite.append("\t").append("\t").append("<civilstate>"+a.getCivilState()+"</civilstate>").append("\r\n");
			toWrite.append("\t").append("\t").append("<birthDay>"+a.getBirth()[0]+"</birthDay>").append("\r\n");
			toWrite.append("\t").append("\t").append("<birthMonth>"+a.getBirth()[1]+"</birthMonth>").append("\r\n");
			toWrite.append("\t").append("\t").append("<birthYear>"+a.getBirth()[2]+"</birthYear>").append("\r\n");
			toWrite.append("\t").append("</admin>").append("\r\n");
		}
		toWrite.append("</admins>");
		writeToXML("./bin/admin.xml", toWrite);
	}
	public void writeTeacher(){
		StringBuffer toWrite = new StringBuffer();
		
		toWrite.append("<?xml version=\"1.0\"?>").append("\r\n");
		toWrite.append("<teachers>").append("\r\n");
		for(teacher a : this.teacherList){
			toWrite.append("<teacher>").append("\r\n");
			toWrite.append("\t").append("\t").append("<firstname>"+a.getSurname()+"</firstname>").append("\r\n");
			toWrite.append("\t").append("\t").append("<name>"+a.getName()+"</name>").append("\r\n");
			toWrite.append("\t").append("\t").append("<password>"+a.getPassword()+"</password>").append("\r\n");
			toWrite.append("\t").append("\t").append("<civilstate>"+a.getCivilState()+"</civilstate>").append("\r\n");
			toWrite.append("\t").append("\t").append("<birthDay>"+a.getBirth()[0]+"</birthDay>").append("\r\n");
			toWrite.append("\t").append("\t").append("<birthMonth>"+a.getBirth()[1]+"</birthMonth>").append("\r\n");
			toWrite.append("\t").append("\t").append("<birthYear>"+a.getBirth()[2]+"</birthYear>").append("\r\n");
			toWrite.append("</teacher>").append("\r\n");
		}
		toWrite.append("</teachers>");
		writeToXML("./bin/teacher.xml", toWrite);
	}
	
	public void writeCourse(){
		StringBuffer toWrite = new StringBuffer();
		
		toWrite.append("<?xml version=\"1.0\"?>").append("\r\n");
		toWrite.append("<courses>").append("\r\n");
		for(course c : this.courseList){
			toWrite.append("\t").append("<course>").append("\r\n");
			toWrite.append("\t").append("\t").append("<name>"+c.getName()+"</name>").append("\r\n");
			toWrite.append("\t").append("\t").append("<tenured>"+c.getName()+"</tenured>").append("\r\n");
			for(Object s : c.getAssistants()){
				toWrite.append("\t").append("\t").append("<AssistantName>"+s+"</AssistantName>").append("\r\n");
			}
			toWrite.append("\t").append("</course>").append("\r\n");
		}
		toWrite.append("</teachers>");
		writeToXML("./bin/courses.xml", toWrite);
		
	}
	
	public void writeSpecialisation(){
		StringBuffer toWrite = new StringBuffer();
		
		toWrite.append("<?xml version=\"1.0\"?>").append("\r\n");
		toWrite.append("<specialisations>").append("\r\n");
		for(specialisation s : this.specialisationList){
			toWrite.append("\t").append("<specialisation>").append("\r\n");
			toWrite.append("\t").append("\t").append("<name>"+s.getName()+"</name>").append("\r\n");
			for(int i=0; i<s.getCourses().size(); i++){
				toWrite.append("\t").append("\t").append("<courseName>"+s.getCourses().get(i).getName()+"</courseName>").append("\r\n");
			}
			toWrite.append("\t").append("</specialisation>").append("\r\n");
		}
		toWrite.append("</specialisations>");
		writeToXML("./bin/specialisations.xml", toWrite);
	}
	
	public void writeStudent(){
		StringBuffer toWrite = new StringBuffer();
		
		toWrite.append("<?xml version=\"1.0\"?>").append("\r\n");
		toWrite.append("<students>").append("\r\n");
		for(specialisation s : this.specialisationList){
			for(student st : this.studentList){
				toWrite.append("\t").append("<student>").append("\r\n");
				toWrite.append("\t").append("\t").append("<firstname>"+st.getSurname()+"</firstname>").append("\r\n");
				toWrite.append("\t").append("\t").append("<name>"+st.getName()+"</name>").append("\r\n");
				toWrite.append("\t").append("\t").append("<password>"+st.getPassword()+"</password>").append("\r\n");
				toWrite.append("\t").append("\t").append("<civilstate>"+st.getCivilState()+"</civilstate>").append("\r\n");
				toWrite.append("\t").append("\t").append("<birthDay>"+st.getBirth()[0]+"</birthDay>").append("\r\n");
				toWrite.append("\t").append("\t").append("<birthMonth>"+st.getBirth()[1]+"</birthMonth>").append("\r\n");
				toWrite.append("\t").append("\t").append("<birthYear>"+st.getBirth()[2]+"</birthYear>").append("\r\n");
				toWrite.append("\t").append("\t").append("<year>"+st.getYear()+"</year>").append("\r\n");
				toWrite.append("\t").append("\t").append("<referent>"+st.getReferent().getSurname()+" "+st.getReferent().getName()+"</referent>").append("\r\n");
				toWrite.append("\t").append("\t").append("<specialisation>"+st.getSpe().getName()+"</specialisation>").append("\r\n");
				for(course c :st.getMendatory()){
					toWrite.append("\t").append("\t").append("<mendatoryName>"+c.getName()+"</mendatoryName>").append("\r\n");
				}
				toWrite.append("\t").append("</student>").append("\r\n");
			}
		}
		toWrite.append("</students>");
		writeToXML("./bin/students.xml", toWrite);
	}
}
