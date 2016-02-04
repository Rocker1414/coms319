package cs319;

public class Server {
	
	public static int PORT = 1234;
	
	public static void main(String[] args){
		
		//thread to handle connections
		Thread connections = new Thread(new ServerConnectionThread(PORT));
		connections.start();
		
	}
}
