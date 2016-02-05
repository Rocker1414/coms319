package cs319;

public class Server {
	
	public final static int PORT = 4444;
	
	public static void main(String[] args){
		
		ServerConnectionThread t = new ServerConnectionThread(PORT);
		t.run();
		
	}
}
