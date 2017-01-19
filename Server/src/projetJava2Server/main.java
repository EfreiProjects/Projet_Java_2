package projetJava2Server;

import java.util.ArrayList;
import java.util.List;

import networking.Server;
import data.admin;
import data.course;
import data.teacher;

public class main {
	public static void main (String[] args){
		int port = 6789;
		Server server = new Server( port );
		try {
			server.startServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
