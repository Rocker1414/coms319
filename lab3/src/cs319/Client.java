package cs319;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JTextArea;

// This class handles all of the Client thread work.

public class Client {

	public final static int PORT = 1234;
	public static Scanner serverIn;
	public static PrintWriter serverOut;
	public static JTextArea chatLog;

	public static void main(String[] args){

		// login frame
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
}
