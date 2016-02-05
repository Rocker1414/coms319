package cs319;

public class Server {

	public final static int PORT = 1234;

	public static void main(String[] args) {

		// Server thread
		Thread serverThread = new Thread(new ServerConnectionThread(PORT));
		serverThread.start();

	}
}
