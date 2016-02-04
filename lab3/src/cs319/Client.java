package cs319;

import java.awt.EventQueue;

public class Client {
	
	public static int PORT = 1234;
	
	public static void main(String[] args){
		
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
