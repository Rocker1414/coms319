
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class HelloWorldSwing {

	// Global variables
	public static JLabel textDisplay = new JLabel("Hello World!");
	public static int fontSize = 12;

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
		t.setLayout(new FlowLayout(FlowLayout.LEFT));

		// TextField
		JTextField text = new JTextField("Hello World!");

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
		JComboBox<String> combo = new JComboBox<String>(options);
		combo.setSelectedItem("Small");
		int[] sizes = { 8, 12, 20, 28 };
		combo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fontSize = sizes[combo.getSelectedIndex()];
				textDisplay.setFont(new Font(textDisplay.getFont().getName(), Font.PLAIN, fontSize));
			}
		});

		// Add Components to Top Panel
		t.add(new JLabel("Text:      "));
		t.add(text);
		t.add(combo);
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
		bi.setSelected(true);

		// Add Components to middle Panel
		ml.add(p);
		ml.add(b);
		ml.add(i);
		ml.add(bi);
		m.add(ml);
		m.add(textDisplay);
		m.setVisible(true);
		return m;
	}

	// Create bottom panel for holding two JButtons
	private static JPanel createBottomPanel() {
		JPanel b = new JPanel(new FlowLayout(FlowLayout.CENTER, 110, 10));
		JButton showButton = new JButton("Show!");

		showButton.addActionListener(new ActionListener() {
			boolean changeToRed = true;

			@Override
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

		JButton exitButton = new JButton("Exit");

		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		b.add(showButton);
		b.add(exitButton);
		b.setVisible(true);
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
