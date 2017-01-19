package networking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import data.human;
import data.teacher;

public class MenuClient extends JFrame implements ActionListener {


public static void main(String[] args){
	
	MenuClient menu = new MenuClient();
}

public void LoginBox(){
	
	JPanel container = new JPanel();
	this.setTitle("Connection");
	this.setSize(250, 100);
	this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	
	container.setBackground(Color.white);
	container.setLayout(new BorderLayout());
	JPanel top = new JPanel();
	top.setLayout(new GridLayout(3, 2));
	
	final JFormattedTextField nomSaisi = new JFormattedTextField();
	final JFormattedTextField passSaisi = new JFormattedTextField();
	
	nomSaisi.setPreferredSize(new Dimension(150, 30));
	passSaisi.setPreferredSize(new Dimension(150, 30));
	

	JLabel nom = new JLabel("Login");
	JLabel pass = new JLabel("password");
	JButton envoi = new JButton ("Connection");
	
	Font police = new Font("Arial", Font.BOLD, 14);
	final JTextArea message = new JTextArea();
	message.setFont(police);
	message.setEditable(false);
	
	envoi.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			ArrayList toSend = new ArrayList();
			toSend.add("connect");
			toSend.add(nomSaisi.getText());
			toSend.add(passSaisi.getText());
			System.out.println(toSend);
			try {
				ClientSocket client = new ClientSocket();
				ArrayList answer = client.ClientSocket(toSend);
				passSaisi.setText("");
				if((Boolean) answer.get(0) == true){
					message.setBackground(Color.green);
					message.setText("Access granted");
					UserInterface((String) answer.get(1), nomSaisi.getText());
				}
				else{
					message.setBackground(Color.red);
					message.setText("Access denied");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	});
	
	top.add(nom);
	top.add(nomSaisi);
	top.add(pass);
	top.add(passSaisi);
	top.add(envoi);
	top.add(message);
	
	this.setContentPane(top);
	this.setVisible(true);
}

	public MenuClient(){
		LoginBox();
	}

	public void UserInterface(final String type, final String nomType){
		 JMenuBar menuBar = new JMenuBar();
		
		
		 JMenu module = new JMenu("Modules");
		 JMenu professeur = new JMenu("Professeurs");
		 JMenu etudiant = new JMenu("Etudiants");
		
		 JMenuItem listeModules = new JMenuItem("Liste des modules");
		
		 JMenu moduleModifier = new JMenu("Modifier");
		 JMenuItem menuItemModuleAjouter = new JMenuItem("Ajouter");
		 JMenuItem menuItemModuleSupprimer = new JMenuItem("Supprimer");
		
		 JMenuItem listeProfesseurs = new JMenuItem("Liste des professeurs");
		
		 JMenu ProfesseurModifier = new JMenu("Modifier");
		 JMenuItem menuItemProfesseurAjouter = new JMenuItem("Ajouter");
		 JMenuItem menuItemProfesseurSupprimer = new JMenuItem("Supprimer");
		
		 JMenuItem listeEtudiants = new JMenuItem("Liste des étudiants");
		 JMenu EtudiantModifier = new JMenu("Modifier");
		 JMenuItem menuItemEtudiantAjouter = new JMenuItem("Ajouter");
		 JMenuItem menuItemEtudiantSupprimer = new JMenuItem("Supprimer");
		 JMenuItem menuItemEtudiantConsulterNotes = new JMenuItem("Consulter Notes");
		 JMenuItem menuItemEtudiantAjouterNote = new JMenuItem("Ajouter Note");
		this.setTitle("Inscription aux modules");
		this.setSize(400, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		//Initialisation du menu
		module.add(listeModules);
		listeModules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    System.out.println("affichage des modules");
					 
				 try {
					ArrayList toSend = new ArrayList();
					toSend.add("get");
					toSend.add("course");
					ClientSocket client = new ClientSocket();
					AffichageListe menu = new AffichageListe();
					menu.displayCourses(client.ClientSocket(toSend));
					//System.out.println(client.ClientSocket(toSend));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			});

		professeur.add(listeProfesseurs);
			listeProfesseurs.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e) {
		    System.out.println("liste des profs");
			 	try {
					ArrayList toSend = new ArrayList();
					toSend.add("get");
					toSend.add("teacher");
					ClientSocket client = new ClientSocket();
					AffichageListe menu = new AffichageListe();
					menu.displayTeachers(client.ClientSocket(toSend));
				} 
			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
		   
		}

			});

		etudiant.add(listeEtudiants);
		listeEtudiants.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e) {
			    System.out.println("affichage etudiants");
				 
				 try {
					 	ArrayList toSend = new ArrayList();
						toSend.add("get");
						toSend.add("student");
						ClientSocket client = new ClientSocket();
						AffichageListe menu = new AffichageListe();
						menu.displayStudents(client.ClientSocket(toSend));
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			   
			}

			});
		//}

		//Ajour éléments pour modifier un module
		if(type.equals("admin")){
			moduleModifier.add(menuItemModuleAjouter);
			menuItemModuleAjouter.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
				    System.out.println("on ajoute un module");
					 
					 try {
						 SaisieTexte addCourse = new SaisieTexte();
						 addCourse.AjoutCours();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}


				   
				}

			});

			moduleModifier.add(menuItemModuleSupprimer);
			menuItemModuleSupprimer.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
				    System.out.println("on supprime un module");
					 
					 try {
						 SaisieTexte delCourse = new SaisieTexte();
						 delCourse.SupprCourse();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			});
			
			module.add(moduleModifier);
			
			ProfesseurModifier.add(menuItemProfesseurAjouter);
			menuItemProfesseurAjouter.addActionListener(new ActionListener (){
				public void actionPerformed(ActionEvent e) {
				    System.out.println("on ajoute un professeur");
					 
					 try {
						 SaisieTexte addTeacher = new SaisieTexte();
						 addTeacher.AjoutProfesseur();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}


				   
				}

			});

			ProfesseurModifier.add(menuItemProfesseurSupprimer);
			menuItemProfesseurSupprimer.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
				    System.out.println("on supprime un professeur");
					 
					 try {
						 SaisieTexte delTeacher = new SaisieTexte();
						 delTeacher.SupprTeacher();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}


				   
				}

			});

			professeur.add(ProfesseurModifier);
			
			EtudiantModifier.add(menuItemEtudiantAjouter);
			menuItemEtudiantAjouter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			    System.out.println("ajout etudiant");
				 
				 try {
						SaisieTexte addStudent = new SaisieTexte();
						addStudent.AjoutEtudiant();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			   
			}
			});

			EtudiantModifier.add(menuItemEtudiantSupprimer);
			 menuItemEtudiantSupprimer.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e) {
					    System.out.println(" on suppr un etudiant");
						 
						 try {
							 SaisieTexte delStudent = new SaisieTexte();
							 delStudent.SupprEtudiant();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
			});
		}
		if(type.equals("teacher") || type.equals("admin")){
			EtudiantModifier.add(menuItemEtudiantAjouterNote);
			 menuItemEtudiantAjouterNote.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e) {
			     System.out.println("Modifier les notes");
			 	 
			 	 try {
			 			SaisieTexte editGrades = new SaisieTexte();
			 			editGrades.ChoisirEtudiant();
			 	} catch (Exception e1) {
			 		// TODO Auto-generated catch block
			 		e1.printStackTrace();
			 	}
			    
			 }
			 });
		}
		 
		 EtudiantModifier.add(menuItemEtudiantConsulterNotes);
		 menuItemEtudiantConsulterNotes.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent e) {
		     System.out.println("Consulter notes");
		 	 
		 	 try {
		 		 	SaisieTexte seeGrades = new SaisieTexte();
		 		 	if(type.equals("teacher") || type.equals("admin"))
		 		 		seeGrades.ConsulterNotes("all");
		 		 	else if(type.equals("student")){
		 		 		seeGrades.ConsulterNotes(nomType);
		 		 	System.out.println(nomType);
		 		 	}
		 	} catch (Exception e1) {
		 		// TODO Auto-generated catch block
		 		e1.printStackTrace();
		 	}
		    
		 }
		 });

		etudiant.add(EtudiantModifier);

		//Barre séparatrice entre modifier et fermer

		professeur.add(listeProfesseurs);
		etudiant.add(listeEtudiants);
		module.add(listeModules);
		//L'ordre d'ajout va déterminer l'ordre d'apparition dans le menu de gauche à droite
		//Le premier ajouté sera tout à gauche de la barre de menu et inversement pour le dernier

		menuBar.add(module);
		menuBar.add(professeur);
		menuBar.add(etudiant);
		setJMenuBar(menuBar);
		setVisible(true);
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}


	}