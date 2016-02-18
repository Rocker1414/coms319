package cs319;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ClientLoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField UsernameField;

	/**
	 * Create the frame.
	 */
	public ClientLoginFrame() {
		setTitle("FastChat Client");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("ComboBox.disabledBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea FastChat = new JTextArea();
		FastChat.setEditable(false);
		FastChat.setBackground(UIManager.getColor("ComboBox.disabledBackground"));
		FastChat.setForeground(Color.RED);
		FastChat.setFont(new Font("Kristen ITC", Font.BOLD | Font.ITALIC, 27));
		FastChat.setText("FastChat");
		FastChat.setBounds(150, 61, 150, 40);
		contentPane.add(FastChat);

		UsernameField = new JTextField();
		UsernameField.setBounds(63, 112, 150, 28);
		contentPane.add(UsernameField);
		UsernameField.setColumns(10);
		
		DocumentListener nameUpdate = new DocumentListener(){

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				Client.name = UsernameField.getText();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changedUpdate(e);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
				
			}
				
				
		};
		UsernameField.getDocument().addDocumentListener(nameUpdate);

		JButton LoginButton = new JButton("Login");
		LoginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonClicked();
			}
		});
		LoginButton.setBounds(240, 112, 125, 30);
		contentPane.add(LoginButton);
		
		JLabel lblNameCannotBe = new JLabel("Name cannot be blank");
		lblNameCannotBe.setEnabled(false);
		lblNameCannotBe.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameCannotBe.setBounds(130, 160, 185, 14);
		contentPane.add(lblNameCannotBe);
	}

	private void buttonClicked() {
		this.dispose();

		// Launch chat app
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientChatFrame frame = new ClientChatFrame();
					frame.setVisible(true);
					
					Thread connections = new Thread(new ClientConnectionThread(Client.PORT));
					connections.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}

