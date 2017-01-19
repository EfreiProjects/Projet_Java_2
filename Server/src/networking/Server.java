package networking;

import java.awt.List;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import data.teacher;
import projetJava2Server.parser;

public class Server {
	private parser data;
    // declare a server socket and a client socket for the server;
    // declare the number of connections

     ServerSocket echoServer = null;
    Socket clientSocket = null;
    int numConnections = 0;
    int port;
	
    public Server( int port ) {
    	this.port = port;
    	this.data = new parser();
    }

    public void stopServer() {
    	System.out.println( "Server cleaning up." );
    	System.exit(0);
    }
    
    public parser getData(){ return this.data; }

    public void startServer() throws Exception{
	// Try to open a server socket on the given port
	// Note that we can't choose a port less than 1024 if we are not
	// privileged users (root)
	
        try {
	    echoServer = new ServerSocket(port);
        }
        catch (IOException e) {
	    System.out.println(e);
        }   
	
	System.out.println( "Server is started and is waiting for connections." );
	System.out.println( "With multi-threading, multiple connections are allowed." );

	// Whenever a connection is received, start a new thread to process the connection
	// and wait for the next connection.
	
		while ( true ) {
		    try {
				clientSocket = echoServer.accept();
				numConnections ++;
				ServerConnection oneconnection = new ServerConnection(clientSocket, numConnections, this);
				new Thread(oneconnection).start();
		    } catch (IOException e) {
		    	System.out.println(e);
		    }
		    Date today = new Date();
		    Date nextSave = new Date();
		    nextSave.setHours(0);
		    nextSave.setMinutes(0);
		    nextSave.setSeconds(0);
		    if(Math.abs(today.getTime() - nextSave.getTime()) < 180000)
		    	this.data.writeAll();
		}
    }
}