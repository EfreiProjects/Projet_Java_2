package networking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import data.course;
import data.student;
import data.teacher;

public class AffichageListe extends JFrame {
  private JPanel container = new JPanel();

  public void displayTeachers(ArrayList<teacher> toDisplay){
	    this.setTitle("Liste des professeurs");
	    this.setSize(600, 600);
	    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    this.setContentPane(container);
	    this.setVisible(true);     

	    setTitle("Liste des enseignants");
	    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    
	    JTable resultatTable = new JTable();

	    List<String> tableHeaders = new ArrayList<String>();
	    tableHeaders.add("Nom");
	    tableHeaders.add("Prenom");
	    tableHeaders.add("Date de naissance");
	    tableHeaders.add("Civilité");
	  //  tableHeaders.add("Enseigne");


	    List<List<String>> tableData = new ArrayList();
	    List<String> line = null;
	    for(teacher s : toDisplay){
	    	line = new ArrayList();
	    	line.add(s.getName());
	    	line.add(s.getSurname());
	    	line.add(s.getBirth()[0]+"/"+s.getBirth()[1]+"/"+s.getBirth()[2]);
	    	line.add(s.getCivilState());
	 //   	line.add(s.g);
	   // 	line.add(s.getReferent().getSurname()+" "+s.getReferent().getName());
	  //  	line.add(""+s.getYear());
	    	tableData.add(line);
	    }
	    
	    System.out.println(tableData);
	    resultatTable.setModel(new TableModelList(tableData, tableHeaders));

	    getContentPane().add(resultatTable.getTableHeader(), BorderLayout.NORTH);
	    getContentPane().add(resultatTable, BorderLayout.CENTER); 
	  }
  
  
  public void displayCourses(ArrayList<course> toDisplay){
	    this.setTitle("Liste des modules");
	    this.setSize(600, 600);
	    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    this.setContentPane(container);
	    this.setVisible(true);     

	    setTitle("Liste des modules");
	    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    
	    JTable resultatTable = new JTable();
	  
	    List<String> tableHeaders = new ArrayList<String>();
	    tableHeaders.add("Nom du cours");
	    tableHeaders.add("Nom du professeur");

	    List<List<String>> tableData = new ArrayList();
	    List<String> line = null;
	    for(course s : toDisplay){
	    	line = new ArrayList();
	    	line.add(s.getName());
	    	line.add(s.getTenured().getSurname()+" "+s.getTenured().getName());
	 
	    	tableData.add(line);
	    }
	    
	    System.out.println(tableData);
	    resultatTable.setModel(new TableModelList(tableData, tableHeaders));

	    getContentPane().add(resultatTable.getTableHeader(), BorderLayout.NORTH);
	    getContentPane().add(resultatTable, BorderLayout.CENTER);
	  
  }
  
  
  public void displayStudents(ArrayList<student> toDisplay){
    this.setTitle("Liste des étudiants");
    this.setSize(600, 600);
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
    container.setLayout(new BorderLayout());
    this.setContentPane(container);
    this.setVisible(true);     

    setTitle("Liste des eleves");
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    
    JTable resultatTable = new JTable();

    List<String> tableHeaders = new ArrayList<String>();
    tableHeaders.add("Nom");
    tableHeaders.add("Prenom");
    tableHeaders.add("Date de naissance");
    tableHeaders.add("Civilité");
    tableHeaders.add("Spécialisation");
    tableHeaders.add("Professeur référent");
    tableHeaders.add("Année");

    List<List<String>> tableData = new ArrayList();
    List<String> line = null;
    for(student s : toDisplay){
    	line = new ArrayList();
    	line.add(s.getName());
    	line.add(s.getSurname());
    	line.add(s.getBirth()[0]+"/"+s.getBirth()[1]+"/"+s.getBirth()[2]);
    	line.add(s.getCivilState());
    	line.add(s.getSpe().getName());
    	line.add(s.getReferent().getSurname()+" "+s.getReferent().getName());
    	line.add(""+s.getYear());
    	tableData.add(line);
    }
    
    System.out.println(tableData);
    resultatTable.setModel(new TableModelList(tableData, tableHeaders));

    getContentPane().add(resultatTable.getTableHeader(), BorderLayout.NORTH);
    getContentPane().add(resultatTable, BorderLayout.CENTER); 
  }
  public void displayGrades(student displayStudent){
	    this.setTitle("Liste des modules");
	    this.setSize(600, 600);
	    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    this.setContentPane(container);
	    this.setVisible(true);     

	    setTitle("Liste des notes");
	    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    
	    JTable resultatTable = new JTable();
	  
	    List<String> tableHeaders = new ArrayList<String>();
	    tableHeaders.add("Nom de la matière");
	    tableHeaders.add("CE");
	    tableHeaders.add("DE");
	    tableHeaders.add("Projet");

	    List<List<String>> tableData = new ArrayList();
	    List<String> line = null;
	    for(int i=0; i<displayStudent.getSpe().getCourses().size(); i++){
	    	line = new ArrayList();
	    	line.add(displayStudent.getSpe().getCourses().get(i).getName());
	    	line.add(Integer.toString(displayStudent.getSpe().getCourses().get(i).getCE().getGrade()));
	    	line.add(Integer.toString(displayStudent.getSpe().getCourses().get(i).getDE().getGrade()));
	    	line.add(Integer.toString(displayStudent.getSpe().getCourses().get(i).getProject().getGrade()));
	    	tableData.add(line);
	    }
	    for(int i=0; i<displayStudent.getMendatory().size(); i++){
	    	line = new ArrayList();
	    	line.add(displayStudent.getMendatory().get(i).getName());
	    	line.add(Integer.toString(displayStudent.getMendatory().get(i).getCE().getGrade()));
	    	line.add(Integer.toString(displayStudent.getMendatory().get(i).getDE().getGrade()));
	    	line.add(Integer.toString(displayStudent.getMendatory().get(i).getProject().getGrade()));
	    	tableData.add(line);
	    }
	    resultatTable.setModel(new TableModelList(tableData, tableHeaders));

	    getContentPane().add(resultatTable.getTableHeader(), BorderLayout.NORTH);
	    getContentPane().add(resultatTable, BorderLayout.CENTER);
	  
}
}