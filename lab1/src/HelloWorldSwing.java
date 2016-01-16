
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
	public static void main(String[] args) {

		JLabel textDisplay = new JLabel("pls turn red");

		// Create JFrame
		JFrame f = new JFrame("HelloWorldSwing!");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(370, 220);

		// Create Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());
		f.setContentPane(mainPanel);

		// ---Create Top Panel---
		JPanel topPanel = new JPanel();

		// TextField
		JTextField text = new JTextField("Hello World!");
		/*text.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textDisplay.setText(text.getText());
			}
		});*/
		text.getDocument().addDocumentListener(new DocumentListener() {
			public void actionPerformed(ActionEvent e) {
				textDisplay.setText(text.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				textDisplay.setText(text.getText());
				
			}
		});
		/*// Remove Text on Mouse Click
		text.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        text.setText("");
		    }
		});*/
		
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
		topPanel.add(new JLabel("Text:"));
		topPanel.add(text);
		topPanel.add(combo);
		mainPanel.add(topPanel);

		// ---Create Middle Panel---
		JPanel midPanel = new JPanel();

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
		midPanel.add(p);
		midPanel.add(b);
		midPanel.add(i);
		midPanel.add(bi);
		midPanel.add(textDisplay);
		mainPanel.add(midPanel);

		// ---Create Bottom Panel---
		JPanel panel4 = new JPanel();

		JButton showButton = new JButton("Show!");

		showButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//Get text from input field
						//change the JLabel
						// change text to red
						textDisplay.setForeground(Color.red);					}
		});

		JButton exitButton = new JButton("Exit");

		exitButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//close window
						//f.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					}
		});


		panel4.add(showButton);
		panel4.add(exitButton);

		mainPanel.add(panel4);


		// Execute
		f.setVisible(true);
	}
}
