import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HelloWorldSwing2 {

	public static JLabel textDisplay;
	public static int fontSize = 12;
	private JFrame frame;
	private JTextField textInput;
	public final static int[] fontSizes = { 8, 12, 20, 28 };
	public static JComboBox<String> textSizes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelloWorldSwing2 window = new HelloWorldSwing2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HelloWorldSwing2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 370, 220);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textInput = new JTextField();
		textInput.setText("Hello World!");
		textInput.setBounds(45, 4, 100, 20);
		frame.getContentPane().add(textInput);
		textInput.setColumns(10);
		
		textDisplay = new JLabel("Hello World!");
		textDisplay.setFont(new Font("Arial", Font.PLAIN, 12));
		textDisplay.setBounds(116, 35, 211, 88);
		frame.getContentPane().add(textDisplay);
		
		DocumentListener documentListener = new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent documentEvent) {
				textDisplay.setText(textInput.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent documentEvent) {
				textDisplay.setText(textInput.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent documentEvent) {
				textDisplay.setText(textInput.getText());
			}
		};

		textInput.getDocument().addDocumentListener(documentListener);
		
		JLabel labelText = new JLabel("Text:");
		labelText.setBounds(0, 7, 46, 14);
		frame.getContentPane().add(labelText);
		
		textSizes = new JComboBox<String>();
		textSizes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fontSize = fontSizes[textSizes.getSelectedIndex()];
				textDisplay.setFont(new Font(textDisplay.getFont().getName(), Font.PLAIN, fontSize));
			}
		});
		textSizes.setModel(new DefaultComboBoxModel<String>(new String[] {"Tiny", "Small", "Medium", "Large"}));
		textSizes.setSelectedIndex(1);
		textSizes.setBounds(155, 4, 89, 20);
		frame.getContentPane().add(textSizes);
		
		ButtonGroup rg = new ButtonGroup();
		
		JRadioButton radioPlain = new JRadioButton("Plain");
		radioPlain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDisplay.setFont(new Font(textDisplay.getFont().getName(), Font.PLAIN, fontSize));
			}
		});
		radioPlain.setSelected(true);
		radioPlain.setBounds(0, 31, 109, 23);
		frame.getContentPane().add(radioPlain);
		
		JRadioButton radioBold = new JRadioButton("Bold");
		radioBold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDisplay.setFont(new Font(textDisplay.getFont().getName(), Font.BOLD, fontSize));
			}
		});
		radioBold.setBounds(0, 59, 109, 23);
		frame.getContentPane().add(radioBold);
		
		JRadioButton radioItalic = new JRadioButton("Italic");
		radioItalic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDisplay.setFont(new Font(textDisplay.getFont().getName(), Font.ITALIC, fontSize));
			}
		});
		radioItalic.setBounds(0, 88, 109, 23);
		frame.getContentPane().add(radioItalic);
		
		JRadioButton radioBoldItalic = new JRadioButton("Bold Italic");
		radioBoldItalic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDisplay.setFont(new Font(textDisplay.getFont().getName(), Font.BOLD + Font.ITALIC, fontSize));
			}
		});
		radioBoldItalic.setBounds(0, 117, 109, 23);
		frame.getContentPane().add(radioBoldItalic);
		
		rg.add(radioPlain);
		rg.add(radioBold);
		rg.add(radioItalic);
		rg.add(radioBoldItalic);
		
		JButton buttonShow = new JButton("Show!");
		buttonShow.addActionListener(new ActionListener() {
			boolean changeToRed = true;
			
			public void actionPerformed(ActionEvent e) {
				if (changeToRed) {
					textDisplay.setForeground(Color.red);
					changeToRed = false;
				} else {
					textDisplay.setForeground(Color.black);
					changeToRed = true;
				}
			}
		});
		buttonShow.setBounds(64, 147, 89, 23);
		frame.getContentPane().add(buttonShow);
		
		JButton buttonExit = new JButton("Exit");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		buttonExit.setBounds(213, 147, 89, 23);
		frame.getContentPane().add(buttonExit);
	}
}
