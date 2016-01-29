package cs319;

// Ian Baer
// Chris Rogers
// Group 14
// Lab 1

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class HelloWorldSwing3 {

	// Global variables
	//using text area instead of JLabel so the text can overflow
	private static JFrame f;
	
	private static JTextArea textDisplay = new JTextArea("Hello World!");
	private static int fontSize = 12;
	private static JComboBox<String> combo;
	private static JTextField text;
	private final static int[] fontSizes = { 8, 12, 20, 28 };
	private static int DEFAULT_FONT_SIZE = 12;
	private static int DEFAULT_FONT = Font.PLAIN;
	
	private static JTextField timerLength;
	private static JCheckBox clearOption;
	private static boolean timerOn = false;
	//private static JLabel errorMsg;
	private static JTextArea errorMsg;
	private static int time;
	// Create JFrame with exit on close parameters
	private static JFrame createJFrame(String n, int x, int y) {
		JFrame f = new JFrame(n);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(x, y);
		return f;
	}

	// Create main panel for holding and layout of components
	private static JPanel createMainPanel() {
		JPanel m = new JPanel();
		m.setLayout(new BoxLayout(m, BoxLayout.Y_AXIS));
		return m;
	}

	// Create top panel for holding JLabel, JTextField, and JComboBox
	private static JPanel createTopPanel() {
		JPanel t = new JPanel();
		t.setLayout(new BoxLayout(t, BoxLayout.X_AXIS));
	    
		// TextField
		text = new JTextField("Hello World!");

		// TextField document listener
		DocumentListener documentListener = new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent documentEvent) {
				textDisplay.setText(text.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent documentEvent) {
				textDisplay.setText(text.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent documentEvent) {
				textDisplay.setText(text.getText());
			}
		};

		text.getDocument().addDocumentListener(documentListener);

		// JComboBox
		String[] options = { "Tiny", "Small", "Medium", "Large" };
		combo = new JComboBox<String>(options);
		combo.setSelectedItem("Small");		
		combo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fontSize = fontSizes[combo.getSelectedIndex()];
				textDisplay.setFont(new Font(textDisplay.getFont().getName(), Font.PLAIN, fontSize));
			}
		});

		// Add Components to Top Panel
		t.add(new JLabel("Text:      "));
		t.add(text);
		t.add(combo);
		
	    clearOption = new JCheckBox();
	    clearOption.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//when checked, display
				if(clearOption.isSelected()){
					timerOn = true;
					//display error message if the input is wrong
					
					if(time >= 1 && time <= 10){
						errorMsg.setVisible(false);
					}
					else{
						errorMsg.setVisible(true);
					}
					
				}
				else{
					timerOn = false;
					errorMsg.setVisible(false);
				}
				
			}
	    	
	    	
	    });
		t.add(clearOption);
		t.add(new JLabel("clear after:  "));
		timerLength = new JTextField();	
		
		DocumentListener timerLengthListener = new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent documentEvent) {
				try{
					time = Integer.parseInt(timerLength.getText());
					if(time >= 1 && time <= 10){
						errorMsg.setVisible(false);
					}
					else if(timerOn){
						errorMsg.setVisible(true);	
					}
				}
				catch(Exception e){
					if(timerOn){
						errorMsg.setVisible(true);	
					}					
				}
			}

			@Override
			public void insertUpdate(DocumentEvent documentEvent) {
				changedUpdate(documentEvent);
				
			}

			@Override
			public void removeUpdate(DocumentEvent documentEvent) {
				changedUpdate(documentEvent);
				
			}

		};
		
		timerLength.getDocument().addDocumentListener(timerLengthListener);

		
		t.add(timerLength);
		t.setVisible(true);

		return t;
	}
	

	// Create middle panel for holding JRadioButtons and JLabel
	private static JPanel createMiddlePanel() {

		JPanel m = new JPanel(new GridLayout(1, 2));

		// Radio Button
		JPanel ml = new JPanel(new GridLayout(4, 1));
		JRadioButton p = new JRadioButton("Plain");
		JRadioButton b = new JRadioButton("Bold");
		JRadioButton i = new JRadioButton("Italic");
		JRadioButton bi = new JRadioButton("Bold Italic");
		ButtonGroup bg = new ButtonGroup();
		p.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textDisplay.setFont(new Font(textDisplay.getFont().getName(), Font.PLAIN, fontSize));
			}
		});

		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textDisplay.setFont(new Font(textDisplay.getFont().getName(), Font.BOLD, fontSize));
			}
		});

		i.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textDisplay.setFont(new Font(textDisplay.getFont().getName(), Font.ITALIC, fontSize));
			}
		});

		bi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textDisplay.setFont(new Font(textDisplay.getFont().getName(), Font.BOLD + Font.ITALIC, fontSize));
			}
		});

		bg.add(p);
		bg.add(b);
		bg.add(i);
		bg.add(bi);
		p.setSelected(true);

		// Add Components to middle Panel
		ml.add(p);
		ml.add(b);
		ml.add(i);
		ml.add(bi);
		
		m.add(ml);
		
		//JPanel textDisPanel = new JPanel();
		//textDisPanel.setLayout(new BoxLayout(textDisPanel, BoxLayout.X_AXIS));
		textDisplay.setFont(new Font(textDisplay.getFont().getName(), DEFAULT_FONT, DEFAULT_FONT_SIZE));
		textDisplay.setWrapStyleWord(true);
		textDisplay.setLineWrap(true);
		textDisplay.setOpaque(false);
		m.add(textDisplay);
		//m.add(textDisPanel);
		
		
		m.setVisible(true);
		return m;
	}

	// Create bottom panel for holding two JButtons
	private static JPanel createBottomPanel() {
		//JPanel b = new JPanel(new FlowLayout(FlowLayout.CENTER, 110, 10));
		JPanel b = new JPanel(new GridLayout(1,3));
		JButton showButton = new JButton("Show!");

		showButton.addActionListener(new ActionListener() {
			boolean changeToRed = true;
			
			//wait amount of time
			//should this be in a different thread?

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(timerOn){
					
					textDisplay.setForeground(Color.red);
					
					//make a new anonymous thread so the red color updates
					//this thread will wait the amount of time and then clear					
					new Thread() {
					    @Override public void run () {
				        	try {
								Thread.sleep(time*1000);
								textDisplay.setForeground(Color.black);
								textDisplay.setText("");
								text.setText("");
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					    }
					  }.start();
				}
				
				else{
					if (changeToRed) {
						textDisplay.setForeground(Color.red);
						changeToRed = false;
					} else {
						textDisplay.setForeground(Color.black);
						changeToRed = true;
					}
				}
				
				
			}
		});
		
		//errorMsg = new JLabel("Time must be int between 1 and 10");
		errorMsg = new JTextArea("Time must be int between 1 and 10");
		errorMsg.setWrapStyleWord(true);
		errorMsg.setLineWrap(true);
		errorMsg.setOpaque(false);
		errorMsg.setVisible(false);
		

		JButton exitButton = new JButton("Exit");

		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		b.add(showButton);		
		b.add(errorMsg);
		b.add(exitButton);
		b.setVisible(true);
		return b;
	}

	public static void main(String[] args) {

		// Create JFrame
	    f = createJFrame("HelloWorldSwing!", 370, 220);

		// Create Main Panel
		JPanel mainPanel = createMainPanel();
		f.setContentPane(mainPanel);

		// ---Create Top Panel---
		JPanel topPanel = createTopPanel();
		mainPanel.add(topPanel);

		// ---Create Middle Panel---
		JPanel midPanel = createMiddlePanel();
		mainPanel.add(midPanel);

		// ---Create Bottom Panel---
		JPanel bottomPanel = createBottomPanel();
		mainPanel.add(bottomPanel);

		// Execute
		f.setVisible(true);
	}
}
