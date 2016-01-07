package hcmiu.edu.vn.nodeManager.server;

import java.net.*;
import java.io.*;
public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;
        if(args.length != 1){
			System.out.println("Give me the port number!");
		}else{
			int port = Integer.valueOf(args[0]);
	        try {
	            serverSocket = new ServerSocket(port);
	            System.out.println("Server is listening on port " + port);
	        } catch (IOException e) {
	            System.err.println("Could not listen on port " + port);
	            System.exit(-1);
	        }
	
	        while (listening)
	        new MultiServerThread(serverSocket.accept()).start();
	
	        serverSocket.close();
		}
    }
}
