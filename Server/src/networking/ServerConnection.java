package networking;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import data.admin;
import data.course;
import data.specialisation;
import data.student;
import data.teacher;
import data.human;

class ServerConnection implements Runnable{
    private BufferedReader is;
    private PrintStream os;
    private Socket clientSocket;
    private int id;
    private Server server;

    public ServerConnection(Socket clientSocket, int id, Server server) throws ClassNotFoundException {
    	this.clientSocket = clientSocket;
    	this.id = id;
    	this.server = server;
    	System.out.println( "Connection " + id + " established with: " + clientSocket );
    	try {
    		is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    		os = new PrintStream(clientSocket.getOutputStream());
			}
    	catch (IOException e) {
    		System.out.println(e);
    	}
    	
    	}

    public void run() {
        String line;
        try {
        	boolean serverStop = false;
	  
	  		ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
	  		out.flush();
	  		ObjectInputStream inner = new ObjectInputStream(clientSocket.getInputStream());
	  		System.out.println("Client a cree les flux");
	  		
	  		ArrayList objetRecu = null;
			try {
				objetRecu = (ArrayList) inner.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println(objetRecu);
			
			out.writeObject(whatToSend(objetRecu));
    		System.out.println("On emet vers le client : "+whatToSend(objetRecu));
    		out.flush();
			
	  		System.out.println( "Connection " + id + " closed." );
            is.close();
            os.close();
            clientSocket.close();

            if ( serverStop ) server.stopServer();
        } catch (IOException e) {
        	System.out.println(e);
        }
    }
    public ArrayList whatToSend(ArrayList recieved){
    	ArrayList result = new ArrayList();
    	
    	if(recieved.get(0).equals("get") == true){
    		if(recieved.get(1).equals("admin") == true)
    			result = (ArrayList) this.server.getData().getAdmin();
    		else if(recieved.get(1).equals("teacher") == true)
    			result = (ArrayList) this.server.getData().getTeacher();
    		else if(recieved.get(1).equals("student") == true)
    			result = (ArrayList) this.server.getData().getStudent();
    		else if(recieved.get(1).equals("course") == true)
    			result = (ArrayList) this.server.getData().getCourses();
    		else if(recieved.get(1).equals("specialisation") == true)
    			result = (ArrayList) this.server.getData().getSpecialisation();
    		else
    			result.add("Invalid request");
    	}
    	else if(recieved.get(0).equals("connect")){
    		ArrayList<human> users = new ArrayList<human>();
    		users.addAll(this.server.getData().getAdmin());
    		users.addAll(this.server.getData().getTeacher());
    		users.addAll(this.server.getData().getStudent());
    		for(human h : users){
    			if(recieved.get(1).equals(h.getSurname()+" "+h.getName()) == true){
    				if(recieved.get(2).equals(h.getPassword())){
    					result.add(true);
    					if(h instanceof admin)
    						result.add("admin");
    					else if(h instanceof teacher)
    						result.add("teacher");
    					else if(h instanceof student)
    						result.add("student");
    				}
    			}
    		}
    		if(result.size() == 0)
    			result.add(false);
    			
    	}
    	else if(recieved.get(0).equals("remove") == true){
    		if(recieved.get(1).equals("admin") == true){
    			this.server.getData().removeAdmin((String) recieved.get(2));
    			result.add("done");
    		}
    		else if(recieved.get(1).equals("teacher") == true){
    			this.server.getData().removeTeacher((String) recieved.get(2));
    			result.add("done");
    		}
    		else if(recieved.get(1).equals("student") == true){
    			this.server.getData().removeStudent((String) recieved.get(2));
    			result.add("done");
    		}
    		else if(recieved.get(1).equals("course") == true){
    			this.server.getData().removeCourse((String) recieved.get(2));
    			result.add("done");
    		}
    		else if(recieved.get(1).equals("specialisation") == true){
    			this.server.getData().removeSpecialisation((String) recieved.get(2));
    			result.add("done");
    		}
    		else
    			result.add("Invalid request");
    	}
    	else if(recieved.get(0) instanceof admin){
    		this.server.getData().addAdmin((admin) recieved.get(0));
			result.add("done");
    	}
    	else if(recieved.get(0) instanceof teacher){
    		this.server.getData().addTeacher((teacher) recieved.get(0));
			result.add("done");
    	}
    	else if(recieved.get(0) instanceof student){
    		this.server.getData().addStudent((student) recieved.get(0));
			result.add("done");
    	}
    	else if(recieved.get(0) instanceof course){
    		this.server.getData().addCourse((course) recieved.get(0));
			result.add("done");
    	}
    	else if(recieved.get(0) instanceof specialisation){
    		this.server.getData().addSpecialisation((specialisation) recieved.get(0));
			result.add("done");
    	}
    	else
    		result.add("Invalid request");
    	return result;
    }
}