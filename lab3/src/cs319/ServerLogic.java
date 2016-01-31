package cs319;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerLogic {
	
	public static int PORT = 1234;
		
	public static void main(String[] args){
		Thread connections = new Thread(new ConnectionThread(PORT));
		connections.start();
		
	}
	
	
}

//thread to handle connections
class ConnectionThread implements Runnable{
	
	private ServerSocket serverSocket;
	private int port;
	
	public ConnectionThread(int port){
		try {
			this.port = port;
			serverSocket = new ServerSocket(port);	
			
		} catch (IOException e) {
			System.out.println("Could not listen on port " + port);
			System.exit(-1);
		}
	}
	
	@Override
	public void run(){
		//start listening for connection
		while(true){
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();

				Thread handler = new Thread(new ClientHandleThread(clientSocket));
				handler.start();

			} catch (IOException e) {
				System.out.println("Accept failed: " + port);
			}
		}
	}
}

class ClientHandleThread implements Runnable{

	private Socket client;
	
	public ClientHandleThread(Socket s){
		client = s;
	}
	
	@Override
	public void run() {
		
	}
	
	
}


