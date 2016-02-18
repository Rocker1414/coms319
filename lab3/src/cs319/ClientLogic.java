package cs319;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JTextArea;

//this document is for all client related threads

class ClientConnectionThread implements Runnable {

	private Socket clientSocket;
	private int port;

	public ClientConnectionThread(int port){
		this.port = port;
	}

	@Override
	public void run() {
		try{
			clientSocket = new Socket("localhost", port);
			Client.serverIn = new Scanner(clientSocket.getInputStream());
			Client.serverOut = new PrintWriter(clientSocket.getOutputStream(), true);
			
			//send name
			Thread sendName = new Thread(new SendMessageThread(Client.name));
			sendName.start();

			//start listening
			Thread listen = new Thread(new ClientListenThread(Client.chatLog));
			listen.start();
			
			
		}
		catch(Exception e){
			System.out.println("Could not find a FastChat server on port " + port + ". Exiting.");
			System.exit(-1);
		}
	}
}

//listens for messages from the server
class ClientListenThread implements Runnable{
	
	JTextArea chatArea;
	public ClientListenThread(JTextArea chatArea){
		this.chatArea = chatArea;
	}
	
	public void run(){
		
		//always listen
		while(true){
			
			//wait for message
			while(!Client.serverIn.hasNextLine()){}
			
			String msg = Client.serverIn.nextLine();
			
			chatArea.setText(chatArea.getText() + msg + "\n");
			
		}
		
	}
}

class SendMessageThread implements Runnable{
	
	String msg;
	
	public SendMessageThread(String msg){
		this.msg = msg;
	}
	
	public void run(){
		Client.serverOut.println(msg);
		
	}
}
