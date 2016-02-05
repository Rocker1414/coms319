package cs319;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.UnknownHostException;

// This class handles all of the Client thread work.

public class Client {
	
	public final static int PORT = 1234;
	
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException{
		
		//thread to handle connections
		Thread connections = new Thread(new ClientConnectionThread(PORT));
		connections.start();

		//login frame
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientLoginFrame frame = new ClientLoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
		
	}
	
	public void LaunchChatApp(){
		
		//chat frame
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientChatFrame frame = new ClientChatFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
