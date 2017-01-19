package networking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.course;
import data.finalExam;
import data.midtermExam;
import data.project;
import data.specialisation;
import data.student;
import data.teacher;

public class SaisieTexte extends JFrame implements ActionListener{
	public void AjoutProfesseur(){
		JPanel container = new JPanel();
		this.setTitle("Ajout d'un professeur");
		this.setSize(350, 400);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		
		final JFormattedTextField civiliteSaisie = new JFormattedTextField();
		final JFormattedTextField prenomSaisi = new JFormattedTextField();
		final JFormattedTextField nomSaisi = new JFormattedTextField();
		final JFormattedTextField dateSaisie = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
		final JFormattedTextField passSaisi = new JFormattedTextField();
		
		civiliteSaisie.setPreferredSize(new Dimension(50, 30));
		prenomSaisi.setPreferredSize(new Dimension(150, 30));
		nomSaisi.setPreferredSize(new Dimension(150, 30));
		dateSaisie.setPreferredSize(new Dimension(150, 30));
		passSaisi.setPreferredSize(new Dimension(150, 30));
		
		JLabel civilite = new JLabel("Civilité");
		JLabel prenom = new JLabel("Prénom du professeur");
		JLabel nom = new JLabel("Nom du professeur");
		JLabel birth = new JLabel("Date de naissance");
		JLabel pass = new JLabel("Mot de passe");
		JButton envoi = new JButton ("Envoi !");
				
		envoi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				teacher toReturn = new teacher();
				toReturn.setName(nomSaisi.getText());
				toReturn.setFirstName(prenomSaisi.getText());
				toReturn.setCivilState(civiliteSaisie.getText());
				toReturn.setBirth(Integer.parseInt(dateSaisie.getText().split("/")[0]), Integer.parseInt(dateSaisie.getText().split("/")[1]), Integer.parseInt(dateSaisie.getText().split("/")[2]));
				toReturn.setPassword(passSaisi.getText());
				ArrayList toSend = new ArrayList();
				toSend.add(toReturn);
				try {
					ClientSocket client = new ClientSocket();
					client.ClientSocket(toSend);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		top.add(civilite);
		top.add(civiliteSaisie);
		top.add(prenom);
		top.add(prenomSaisi);
		top.add(nom);
		top.add(nomSaisi);
		top.add(birth);
		top.add(dateSaisie);
		top.add(pass);
		top.add(passSaisi);
		top.add(envoi);
		
		this.setContentPane(top);
		this.setVisible(true);
	}
	public void SupprTeacher(){
		JPanel container = new JPanel();
		this.setTitle("Suppression d'un professeur");
		this.setSize(350, 100);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		
		ArrayList toSend = new ArrayList();
		toSend.add("get");
		toSend.add("teacher");
		ArrayList<teacher> teachers = new ArrayList();
		ArrayList teacherNames = new ArrayList();
		try {
			ClientSocket client = new ClientSocket();
			teachers = (ArrayList) client.ClientSocket(toSend);
			for(teacher t : teachers)
				teacherNames.add(t.getSurname()+" "+t.getName());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		final JComboBox teacherList = new JComboBox(teacherNames.toArray());
		JButton envoi = new JButton ("Envoi !");
				
		envoi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ArrayList toSend = new ArrayList();
				toSend.add("remove");
				toSend.add("teacher");
				toSend.add(teacherList.getSelectedItem());
				try {
					ClientSocket client = new ClientSocket();
					client.ClientSocket(toSend);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		top.add(teacherList);
		top.add(envoi);
		
		this.setContentPane(top);
		this.setVisible(true);
	}
	  
	public void AjoutEtudiant(){
		JPanel container = new JPanel();
		this.setTitle("Ajout d'un étudiant");
		this.setSize(350, 400);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		
		final JFormattedTextField civiliteSaisie = new JFormattedTextField();
		final JFormattedTextField prenomSaisi = new JFormattedTextField();
		final JFormattedTextField nomSaisi = new JFormattedTextField();
		final JFormattedTextField dateSaisie = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
		final JFormattedTextField passSaisi = new JFormattedTextField();
		final JFormattedTextField anneeSaisie = new JFormattedTextField(NumberFormat.getIntegerInstance());
		
		ArrayList getSpe = new ArrayList();
		getSpe.add("get");
		getSpe.add("specialisation");
		ArrayList<specialisation> specialisations = new ArrayList();
		ArrayList specialisationNames = new ArrayList();
		try {
			ClientSocket client = new ClientSocket();
			specialisations = (ArrayList) client.ClientSocket(getSpe);
			for(specialisation s : specialisations)
				specialisationNames.add(s.getName());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		final ArrayList<specialisation> specialisationsPrime = specialisations;
		final JComboBox spe = new JComboBox(specialisationNames.toArray());
		
		ArrayList<String> getCourse = new ArrayList();
		getCourse.add("get");
		getCourse.add("course");
		ArrayList<course> courses = new ArrayList<course>();
		ArrayList courseNames = new ArrayList<String>();
		try {
			ClientSocket client = new ClientSocket();
			courses = (ArrayList) client.ClientSocket(getCourse);
			for(course c : courses)
				courseNames.add(c.getName());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		final ArrayList<course> coursesPrime = courses;
		final JComboBox listeDeCourses = new JComboBox(courseNames.toArray());
		final ArrayList<course> mendatory = new ArrayList<course>();
		
		ArrayList<String> getTeachers = new ArrayList();
		getTeachers.add("get");
		getTeachers.add("teacher");
		ArrayList<teacher> referents = new ArrayList<teacher>();
		ArrayList referentNames = new ArrayList<String>();
		try {
			ClientSocket client = new ClientSocket();
			referents = (ArrayList) client.ClientSocket(getTeachers);
			for(teacher t : referents)
				referentNames.add(t.getSurname()+" "+t.getName());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		final ArrayList<teacher> referentsPrime = referents;
		final JComboBox listeDeReferents = new JComboBox(referentNames.toArray());
		
		civiliteSaisie.setPreferredSize(new Dimension(50, 30));
		prenomSaisi.setPreferredSize(new Dimension(150, 30));
		nomSaisi.setPreferredSize(new Dimension(150, 30));
		dateSaisie.setPreferredSize(new Dimension(150, 30));
		passSaisi.setPreferredSize(new Dimension(150, 30));
		anneeSaisie.setPreferredSize(new Dimension(50, 30));
		
		JLabel civilite = new JLabel("Civilité");
		JLabel prenom = new JLabel("Prénom");
		JLabel nom = new JLabel("Nom");
		JLabel birth = new JLabel("Date de naissance");
		JLabel pass = new JLabel("Mot de passe");
		JLabel annee = new JLabel("Année");
		JLabel referentAdd = new JLabel("Professeur référent");
		JButton ajouter = new JButton ("Ajouter un cours");
		JButton envoi = new JButton ("Envoi !");
				
		envoi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				student toReturn = new student();
				toReturn.setName(nomSaisi.getText());
				toReturn.setFirstName(prenomSaisi.getText());
				toReturn.setCivilState(civiliteSaisie.getText());
				toReturn.setBirth(Integer.parseInt(dateSaisie.getText().split("/")[0]), Integer.parseInt(dateSaisie.getText().split("/")[1]), Integer.parseInt(dateSaisie.getText().split("/")[2]));
				toReturn.setPassword(passSaisi.getText());
				toReturn.setYear(Integer.parseInt(anneeSaisie.getText()));
				specialisation chosenSpe = null;
				for(specialisation s : specialisationsPrime){
					if(spe.getSelectedItem().equals(s.getName()))
						toReturn.setSpe(specialisationsPrime.get(specialisationsPrime.indexOf(s)));
				}
				toReturn.setMendatory(mendatory);
				for(teacher t : referentsPrime){
					if(listeDeReferents.getSelectedItem().equals(t.getSurname()+" "+t.getName()))
						toReturn.setReferent(t);
				}
				ArrayList toSend = new ArrayList();
				toSend.add(toReturn);
				try {
					ClientSocket client = new ClientSocket();
					client.ClientSocket(toSend);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		ajouter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				for(course c : coursesPrime){
					if(listeDeCourses.getSelectedItem().equals(c.getName()))
						mendatory.add(c);
				}
			}
		});
		
		top.add(civilite);
		top.add(civiliteSaisie);
		top.add(prenom);
		top.add(prenomSaisi);
		top.add(nom);
		top.add(nomSaisi);
		top.add(birth);
		top.add(dateSaisie);
		top.add(pass);
		top.add(passSaisi);
		top.add(annee);
		top.add(anneeSaisie);
		top.add(spe);
		top.add(listeDeCourses);
		top.add(ajouter);
		top.add(referentAdd);
		top.add(listeDeReferents);
		top.add(envoi);
		
		this.setContentPane(top);
		this.setVisible(true);
	}
	public void SupprEtudiant(){
		JPanel container = new JPanel();
		this.setTitle("Suppression d'un étudiant");
		this.setSize(350, 100);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		
		ArrayList toSend = new ArrayList();
		toSend.add("get");
		toSend.add("student");
		ArrayList<student> students = new ArrayList();
		ArrayList studentNames = new ArrayList();
		try {
			ClientSocket client = new ClientSocket();
			students = (ArrayList) client.ClientSocket(toSend);
			for(student s : students)
				studentNames.add(s.getSurname()+" "+s.getName());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		final JComboBox studentList = new JComboBox(studentNames.toArray());
		JButton envoi = new JButton ("Envoi !");
				
		envoi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ArrayList toSend = new ArrayList();
				toSend.add("remove");
				toSend.add("student");
				toSend.add(studentList.getSelectedItem());
				try {
					ClientSocket client = new ClientSocket();
					client.ClientSocket(toSend);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		top.add(studentList);
		top.add(envoi);
		
		this.setContentPane(top);
		this.setVisible(true);
	}
	public void ConsulterNotes(String type){
		JPanel container = new JPanel();
		this.setTitle("Consultation de notes");
		this.setSize(350, 150);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		
		ArrayList<String> getStudents = new ArrayList();
		getStudents.add("get");
		getStudents.add("student");
		ArrayList<student> students = new ArrayList<student>();
		ArrayList studentNames = new ArrayList<String>();
		try {
			ClientSocket client = new ClientSocket();
			students = (ArrayList) client.ClientSocket(getStudents);
			if(type.equals("all")){
				for(student s : students)
					studentNames.add(s.getSurname()+" "+s.getName());
			}
			else{
				for(student s : students){
					if((s.getSurname()+" "+s.getName()).equals(type))
						studentNames.add(s.getSurname()+" "+s.getName());
				}
				System.out.println(type);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		final ArrayList<student> studentsPrime = students;
		final JComboBox listeStudents = new JComboBox(studentNames.toArray());
		
		JButton envoi = new JButton ("Envoi !");
				
		envoi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				student chosenStudent = null;
				for(student s : studentsPrime){
					if(listeStudents.getSelectedItem().equals(s.getSurname()+" "+s.getName()))
						chosenStudent = s;
				}
				AffichageListe affichage = new AffichageListe();
				affichage.displayGrades(chosenStudent);
			}
		});
		
		top.add(listeStudents);
		top.add(envoi);
		
		this.setContentPane(top);
		this.setVisible(true);
	}
	public void ChoisirEtudiant(){
		JPanel container = new JPanel();
		this.setTitle("Modifier les notes");
		this.setSize(350, 100);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		
		ArrayList<String> getStudents = new ArrayList();
		getStudents.add("get");
		getStudents.add("student");
		ArrayList<student> students = new ArrayList<student>();
		ArrayList studentNames = new ArrayList<String>();
		try {
			ClientSocket client = new ClientSocket();
			students = (ArrayList) client.ClientSocket(getStudents);
			for(student s : students)
				studentNames.add(s.getSurname()+" "+s.getName());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		final ArrayList<student> studentsPrime = students;
		final JComboBox listeStudents = new JComboBox(studentNames.toArray());
		
		JButton envoi = new JButton ("Choisir");
				
		envoi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				student chosenStudent = null;
				for(student s : studentsPrime){
					if(listeStudents.getSelectedItem().equals(s.getSurname()+" "+s.getName()))
						chosenStudent = s;
				}
				ChoisirMatiere(chosenStudent);
			}
		});
		
		top.add(listeStudents);
		top.add(envoi);
		
		this.setContentPane(top);
		this.setVisible(true);
	}
	public void ChoisirMatiere(final student etudiant){
		JPanel container = new JPanel();
		this.setTitle("Modifier les notes");
		this.setSize(350, 100);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		
		ArrayList<course> courses = new ArrayList<course>();
		for(int i=0; i<etudiant.getSpe().getCourses().size(); i++)
			courses.add(etudiant.getSpe().getCourses().get(i));
		for(int i=0; i<etudiant.getMendatory().size(); i++)
			courses.add(etudiant.getMendatory().get(i));
		final ArrayList<course> coursesPrime = courses;
		ArrayList<String> coursesName = new ArrayList<String>();
		for(course c : courses)
			coursesName.add(c.getName());
		final JComboBox listeCourses = new JComboBox(coursesName.toArray());
		
		JButton envoi = new JButton ("Choisir");
				
		envoi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				course selected = null;
				for(course c : coursesPrime){
					if(listeCourses.getSelectedItem().equals(c.getName()))
						selected = c;
				}
				modifierNotes(etudiant, selected);
			}
		});
		
		top.add(listeCourses);
		top.add(envoi);
		
		this.setContentPane(top);
		this.setVisible(true);
	}
	public void modifierNotes(final student etudiant, final course selected){
		System.out.println(selected.getName());
		JPanel containerP = new JPanel();
		setTitle(selected.getName());
		setSize(350, 100);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		containerP.setBackground(Color.white);
		containerP.setLayout(new BorderLayout());
		JPanel topP = new JPanel();
		
		
		final JFormattedTextField CeSaisi = new JFormattedTextField(selected.getCE().getGrade());
		final JFormattedTextField DeSaisi = new JFormattedTextField(selected.getDE().getGrade());
		final JFormattedTextField PrjSaisi = new JFormattedTextField(selected.getProject().getGrade());
		
		CeSaisi.setPreferredSize(new Dimension(50, 30));
		DeSaisi.setPreferredSize(new Dimension(50, 30));
		PrjSaisi.setPreferredSize(new Dimension(50, 30));
		
		JLabel Ce = new JLabel("CE");
		JLabel De = new JLabel("DE");
		JLabel Prj = new JLabel("Projet");
		JButton execute = new JButton ("Valider");
		execute.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ArrayList toSend = new ArrayList();
				toSend.add("remove");
				toSend.add("student");
				toSend.add(etudiant.getSurname()+" "+etudiant.getName());
				try {
					ClientSocket client = new ClientSocket();
					client.ClientSocket(toSend);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				selected.getCE().setGrade(Integer.parseInt(CeSaisi.getText()));
				selected.getDE().setGrade(Integer.parseInt(DeSaisi.getText()));
				selected.getProject().setGrade(Integer.parseInt(PrjSaisi.getText()));
				for(int i=0; i<etudiant.getSpe().getCourses().size(); i++){
					if(etudiant.getSpe().getCourses().get(i).getName().equals(selected.getName())){
						etudiant.getSpe().getCourses().set(i, selected);
						System.out.println("trouvé dans spe");
					}
				}
				for(int i=0; i<etudiant.getMendatory().size(); i++){
					if(etudiant.getMendatory().get(i).getName().equals(selected.getName())){
						etudiant.getMendatory().set(i, selected);
						System.out.println("trouvé dans mendatory");
					}
				}
				toSend = new ArrayList();
				toSend.add(etudiant);
				System.out.println(etudiant.getSpe().getCourses().get(0).getCE().getGrade());
				try {
					ClientSocket client = new ClientSocket();
					client.ClientSocket(toSend);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		topP.add(Ce);
		topP.add(CeSaisi);
		topP.add(De);
		topP.add(DeSaisi);
		topP.add(Prj);
		topP.add(PrjSaisi);
		topP.add(execute);
		
		this.setContentPane(topP);
		this.setVisible(true);
	}
	
	public void AjoutCours(){
		JPanel container = new JPanel();
		this.setTitle("Ajout d'un module");
		this.setSize(350, 400);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		final JFormattedTextField nomSaisi = new JFormattedTextField();
		
		ArrayList getTeachers = new ArrayList();
		getTeachers.add("get");
		getTeachers.add("teacher");
		ArrayList<teacher> teachers = new ArrayList();
		ArrayList teacherNames = new ArrayList();
		try {
			ClientSocket client = new ClientSocket();
			teachers = (ArrayList) client.ClientSocket(getTeachers);
			for(teacher t : teachers)
				teacherNames.add(t.getSurname()+" "+t.getName());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		final ArrayList<teacher> teachersPrime = teachers;
		final JComboBox tenured = new JComboBox(teacherNames.toArray());
		
		ArrayList<String> getStudents = new ArrayList();
		getStudents.add("get");
		getStudents.add("student");
		ArrayList<student> students = new ArrayList<student>();
		ArrayList studentNames = new ArrayList<String>();
		try {
			ClientSocket client = new ClientSocket();
			students = (ArrayList) client.ClientSocket(getStudents);
			for(student s : students)
				studentNames.add(s.getSurname()+" "+s.getName());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		final ArrayList<student> studentsPrime = students;
		final JComboBox listeStudents = new JComboBox(studentNames.toArray());
		final ArrayList<student> assistants = new ArrayList<student>();
		
		
		nomSaisi.setPreferredSize(new Dimension(150, 30));
		
		JLabel nom = new JLabel("Nom");
		JLabel tenuredSet = new JLabel("Professeur titulaire");
		JButton ajouter = new JButton ("Ajouter un assistant");
		JButton envoi = new JButton ("Envoi !");
				
		envoi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				course toReturn = new course();
				toReturn.setName(nomSaisi.getText());
				for(teacher t : teachersPrime){
					if(tenured.getSelectedItem().equals(t.getSurname()+" "+t.getName()))
						toReturn.setTenured(t);
				}
				toReturn.setAssistants(assistants);
				ArrayList toSend = new ArrayList();
				toSend.add(toReturn);
				try {
					ClientSocket client = new ClientSocket();
					client.ClientSocket(toSend);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		ajouter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				for(student s : studentsPrime){
					if(listeStudents.getSelectedItem().equals(s.getSurname()+" "+s.getName())){
						assistants.add(s);
					}
				}
			}
		});
		
		top.add(nom);
		top.add(nomSaisi);
		top.add(tenuredSet);
		top.add(tenured);
		top.add(listeStudents);
		top.add(ajouter);
		top.add(envoi);
		
		this.setContentPane(top);
		this.setVisible(true);
	}
	public void SupprCourse(){
		JPanel container = new JPanel();
		this.setTitle("Suppression d'un module");
		this.setSize(350, 100);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		
		ArrayList toSend = new ArrayList();
		toSend.add("get");
		toSend.add("course");
		ArrayList<course> courses = new ArrayList();
		ArrayList courseNames = new ArrayList();
		try {
			ClientSocket client = new ClientSocket();
			courses = (ArrayList) client.ClientSocket(toSend);
			for(course c : courses)
				courseNames.add(c.getName());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		final JComboBox courseList = new JComboBox(courseNames.toArray());
		JButton envoi = new JButton ("Envoi !");
				
		envoi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ArrayList toSend = new ArrayList();
				toSend.add("remove");
				toSend.add("course");
				toSend.add(courseList.getSelectedItem());
				try {
					ClientSocket client = new ClientSocket();
					client.ClientSocket(toSend);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		top.add(courseList);
		top.add(envoi);
		
		this.setContentPane(top);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {}
}
