package cs319;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

//this document is for all client related threads

class ClientConnectionThread implements Runnable {

	private Socket clientSocket;

	public ClientConnectionThread(int port) throws UnknownHostException, IOException, InterruptedException {
		try{
		clientSocket = new Socket("localhost", port);
		Thread.sleep(1000);
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
		out.print("Client socket Local Address: " + clientSocket.getLocalAddress() + ":" + clientSocket.getLocalPort() + "\n");
		out.println("  Client socket Remote Address: " + clientSocket.getRemoteSocketAddress() + "\n");
		out.flush(); // forces data from buffer to be sent to server
		}
		catch(IOException e){
			System.out.println("Could not connect to server on port " + port);
		}
	}

	@Override
	public void run() {
		System.out.println("client running");
		// start listening for connection
		/*
		 * while (true) { Socket clientSocket = null; /*try { //clientSocket =
		 * clientSocket.accept();
		 * 
		 * Thread handler = new Thread(new ClientHandleThread(clientSocket));
		 * handler.start();
		 * 
		 * } catch (IOException e) { System.out.println("Accept failed: " +
		 * port); }
		 */
	}
}
