package cs319;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ClientLoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterUsername;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ClientLoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("ComboBox.disabledBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrFastchat = new JTextArea();
		txtrFastchat.setBackground(UIManager.getColor("ComboBox.disabledBackground"));
		txtrFastchat.setForeground(Color.RED);
		txtrFastchat.setFont(new Font("Kristen ITC", Font.BOLD | Font.ITALIC, 27));
		txtrFastchat.setText("FastChat");
		txtrFastchat.setBounds(150, 61, 150, 40);
		contentPane.add(txtrFastchat);
		
		txtEnterUsername = new JTextField();
		txtEnterUsername.setText("Enter Username");
		txtEnterUsername.setBounds(63, 112, 150, 28);
		contentPane.add(txtEnterUsername);
		txtEnterUsername.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(240, 112, 125, 30);
		contentPane.add(btnNewButton);
	}
}
