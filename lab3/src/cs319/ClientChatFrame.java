package cs319;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientChatFrame extends JFrame {

	private JPanel contentPane;
	private JTextField messageInsert;
	public static JTextArea chatLog;


	/**
	 * Create the frame.
	 */
	public ClientChatFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		setTitle("FastChat Client");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 22, 241, 231);
		getContentPane().add(scrollPane);
		
		chatLog = new JTextArea();
		chatLog.setEditable(false);
		chatLog.setLineWrap(true);
		scrollPane.setViewportView(chatLog);
		
		Client.chatLog = chatLog;
		
		messageInsert = new JTextField();
		messageInsert.setBounds(100, 279, 151, 29);
		getContentPane().add(messageInsert);
		messageInsert.setColumns(10);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setBounds(22, 286, 68, 14);
		getContentPane().add(lblMessage);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//only send if field not blank
				if(!messageInsert.getText().equals("")){
					Thread send = new Thread(new SendMessageThread(messageInsert.getText()));
					send.start();
				}
			}
		});
		btnSend.setBounds(285, 279, 100, 29);
		getContentPane().add(btnSend);
	}

}
