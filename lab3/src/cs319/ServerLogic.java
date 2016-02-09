package cs319;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//This class handles all of the Server thread work

class ServerConnectionThread implements Runnable {

	private ServerSocket serverSocket;
	private int port;
	

	public ServerConnectionThread(int port) {
		try {
			this.port = port;
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("Could not listen on port " + port + ". Exiting.");
			System.exit(-1);
		}
	}

	@Override
	public void run() {
		System.out.println("FastChat server started. Waiting for client to connect.");
		// start listening for connection
		while (true) {
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();
				Server.clientOutput.add(new PrintWriter(clientSocket.getOutputStream(), true));
				Thread handler = new Thread(new ClientHandleThread(clientSocket));
				handler.start();
			} catch (IOException e) {
				System.out.println("Accept failed: " + port);
			}
		}
	}
}

class ClientHandleThread implements Runnable {

	private Socket s; // Serverside socket that connects to client
	private String clientName;

	public ClientHandleThread(Socket s) {
		this.s = s;
		
		//when this is created, get name from client
		
		Server.chatLog += clientName + " connected."+ "\n";
	}

	@Override
	public void run() {
		//printSocketInfo(s);
		Scanner in;
		
		
		try {
			in = new Scanner(s.getInputStream());
			// Always be listening
			while(true){
				

				//while no message, wait for one
				while(!in.hasNextLine()){}
				
				String clientMessage = in.nextLine();
				clientMessage = clientName + ": " + clientMessage; 
				
				Server.chatLog += clientMessage + "\n";
				
				//start new thread to update client chat logs
				Thread output = new Thread(new ClientBroadcastThread(clientMessage));
				output.start();
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("Waiting for client to connect!");
	}

//	void printSocketInfo(Socket s) {
//		System.out.print("Socket on Server " + Thread.currentThread() + "\n");
//		System.out.print("Server Socket Local Address: " + s.getLocalAddress() + ":" + s.getLocalPort() + "\n");
//		System.out.println("Server Socket Remote Address: " + s.getRemoteSocketAddress());
//	}
}

class ClientBroadcastThread implements Runnable {
	
	private String msg;

	public ClientBroadcastThread(String msg) {
		
		this.msg = msg;
	
	}

	@Override
	public void run() {
		
		for(PrintWriter out : Server.clientOutput){
			
			out.println(msg);
			
		}
		
		//update chat log
		//lock file so no one else uses
		while(Server.fileLock){}
		Server.fileLock = true;
		
		FileWriter fw;
		try {
			fw = new FileWriter("chat.txt", true);
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println(msg);
			
			pw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		Server.fileLock = false;
		
	}
	
}


