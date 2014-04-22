package client;

import java.io.*;
import java.net.*;

public class ClientSocket {
    private Socket socket;
    
    public ClientSocket() {
    	socket = new Socket();
    }
    
    
    public void connectToServer() throws Exception {
        // Make connection and initialize streams
    	String host = "localhost"; //InetAddress.getLocalHost().getHostName();    	
    	
    	socket = new Socket(host, 8080);
                
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        pw.println("GET / HTTP/1.1");
        pw.println("Host: http://localhost:8080/Priject1_Server/displayModels");
        pw.println("");
    	pw.flush();
    }
    
    
    public static void main(String[] args) throws Exception {
        ClientSocket client = new ClientSocket();
        client.connectToServer();
    }
    
}