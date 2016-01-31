package cs319;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ClientGUI {

	private JFrame frmClientSide;
	private JTextField messageInsert;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI window = new ClientGUI();
					window.frmClientSide.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClientSide = new JFrame();
		frmClientSide.setResizable(false);
		frmClientSide.setTitle("Client Side");
		frmClientSide.setBounds(100, 100, 450, 500);
		frmClientSide.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClientSide.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 22, 241, 231);
		frmClientSide.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		messageInsert = new JTextField();
		messageInsert.setBounds(100, 279, 151, 29);
		frmClientSide.getContentPane().add(messageInsert);
		messageInsert.setColumns(10);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setBounds(22, 286, 68, 14);
		frmClientSide.getContentPane().add(lblMessage);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(279, 279, 95, 29);
		frmClientSide.getContentPane().add(btnSend);
	}
}
