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
		out.print("Client socket Local Address: " + clientSocket.getLocalAddress() + ":" + clientSocket.getLocalPort());
		out.print("Client socket Remote Address: " + clientSocket.getRemoteSocketAddress());
		out.flush();
		}
		catch(IOException e){
			System.out.println("Could not find a FastChat server on port " + port + ". Exiting.");
			System.exit(-1);
		}
	}

	@Override
	public void run() {
		System.out.println("client running");
		/**TODO**/
	}
}
