package networking;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

import data.teacher;

public class ClientSocket {
	public ClientSocket() {}
	
    public ArrayList ClientSocket(ArrayList aEmettre) throws Exception{
    	String hostname = "localhost";
    	int port = 6789;
    	
        Socket ClientSocket = null;  
        DataOutputStream os = null;
        BufferedReader is = null;
        
        ArrayList objetRecu = null;
    	
    	try {
            ClientSocket = new Socket(hostname, port);
            os = new DataOutputStream(ClientSocket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
         
            ObjectOutputStream out = new ObjectOutputStream(ClientSocket.getOutputStream());
            out.flush();
     
            ObjectInputStream inner = new ObjectInputStream(ClientSocket.getInputStream());
     
     
            System.out.println("Serveur a cree les flux");
            out.writeObject(aEmettre);
            out.flush();
            System.out.println("On émet au serveur"+aEmettre);
           
            objetRecu = (ArrayList) inner.readObject();
            System.out.println("Server envoie : "+objetRecu);

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + hostname);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + hostname);
            e.printStackTrace();
        }
	
        if (ClientSocket == null) {
        	System.err.println( "Something is wrong. One variable is null." );
        }
        
        int a = 1;
        while(a != 42){Thread.sleep(500);
        break;}
        	
        
        ClientSocket.close();
        
        return objetRecu;
    }            
}