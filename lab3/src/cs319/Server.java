package cs319;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	public final static int PORT = 1234;
	public static String chatLog = "";
	public static ArrayList<PrintWriter> clientOutput = new ArrayList<PrintWriter>();
	public static boolean fileLock = false;

	public static void main(String[] args) {

		// Server thread
		Thread serverThread = new Thread(new ServerConnectionThread(PORT));
		serverThread.start();

	}
}
