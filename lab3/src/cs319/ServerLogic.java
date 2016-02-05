package cs319;

import java.io.IOException;
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
			System.out.println("Could not listen on port " + port);
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

	public ClientHandleThread(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		printSocketInfo(s);
		Scanner in;

		try {
			// Read from client using socket
			in = new Scanner(s.getInputStream());
			String clientMessage = in.nextLine();
			// Print client
			System.out.println(clientMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Waiting for client to connect!");
	}

	void printSocketInfo(Socket s) {
		System.out.print("Socket on Server " + Thread.currentThread() + "\n");
		System.out.print("Server Socket Local Address: " + s.getLocalAddress() + ":" + s.getLocalPort() + "\n");
		System.out.println("Server Socket Remote Address: " + s.getRemoteSocketAddress());
	}
}
