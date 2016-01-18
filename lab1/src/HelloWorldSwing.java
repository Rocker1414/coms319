
// Ian Baer
// Chris Rogers
// Group 14
// Lab 1

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class HelloWorldSwing {
	
	// Global variable text display
	public static JLabel textDisplay = new JLabel("pls turn red");

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
		m.setLayout(new FlowLayout());
		return m;
	}

	// Create top panel for holding JLabel, JTextField, and JComboBox
	private static JPanel createTopPanel() {
		JPanel t = new JPanel();

		// TextField
		JTextField text = new JTextField("Hello World!");
		/*
		 * text.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * textDisplay.setText(text.getText()); } });
		 */

		// JComboBox
		String[] options = { "Tiny", "Small", "Medium", "Large" };
		JComboBox<String> combo = new JComboBox<String>(options);
		int[] sizes = { 8, 12, 20, 28 };
		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int fontSize = sizes[combo.getSelectedIndex()];
				// label1.setFontSize(12);

			}
		});

		// Add Components to Top Panel
		t.add(new JLabel("Text:"));
		t.add(text);
		t.add(combo);

		return t;
	}

	// Create middle panel for holding JRadioButtons and JLabel
	private static JPanel createMiddlePanel() {

		JPanel m = new JPanel();

		// Radio Button
		JRadioButton p = new JRadioButton("Plain");
		JRadioButton b = new JRadioButton("Bold");
		JRadioButton i = new JRadioButton("Italic");
		JRadioButton bi = new JRadioButton("Bold Italic");
		ButtonGroup bg = new ButtonGroup();
		p.setSelected(true);
		bg.add(p);
		bg.add(b);
		bg.add(i);
		bg.add(bi);
		
		// Add Components to middle Panel
		m.add(p);
		m.add(b);
		m.add(i);
		m.add(bi);
		m.add(textDisplay);
		
		return m;
	}

	// Create bottom panel for holding two JButtons
	private static JPanel createBottomPanel(){
		JPanel b = new JPanel();

		JButton showButton = new JButton("Show!");

		showButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Get text from input field
				// change the JLabel
				// change text to red
				textDisplay.setForeground(Color.red);
			}
		});

		JButton exitButton = new JButton("Exit");

		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// close window
				// f.dispatchEvent(new WindowEvent(frame,
				// WindowEvent.WINDOW_CLOSING));
			}
		});
		
		b.add(showButton);
		b.add(exitButton);

		return b;
		
	}
	public static void main(String[] args) {
		
		// Create JFrame
		JFrame f = createJFrame("HelloWorldSwing!", 370, 220);

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
