package cs319;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.ComponentOrientation;

public class Lab2Swing extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lab2Swing frame = new Lab2Swing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws FileNotFoundException
	 */
	public Lab2Swing() throws FileNotFoundException {
		setTitle("Tabbed Swing Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(0, 0, 434, 260);
		contentPane.add(tabbedPane);

		// List

		JPanel listPanel = new JPanel();
		listPanel.setBorder(null);
		tabbedPane.addTab("List", null, listPanel, null);

		DataModel data = new DataModel();
		listPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 418, 199);
		listPanel.add(scrollPane);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JList list = new JList(data);
		list.setSelectionBackground(Color.YELLOW);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);

		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					data.addElement(JOptionPane.showInputDialog("What is the new company?"));
				} catch (IOException ioe) {
					System.err.println("IOException: " + ioe.getMessage());
				}
			}
		});
		addButton.setBounds(100, 208, 89, 23);
		listPanel.add(addButton);

		JButton remButton = new JButton("Remove");
		remButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					data.removeElement(list.getSelectedValue());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		remButton.setBounds(234, 208, 89, 23);
		listPanel.add(remButton);

		// Tree

		JPanel treePanel = new JPanel();
		tabbedPane.addTab("Tree", null, treePanel, null);

		// Table

		JPanel tablePanel = new JPanel();
		tabbedPane.addTab("Table", null, tablePanel, null);
		tablePanel.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(2, 5, 426, 227);
		scrollPane_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tablePanel.add(scrollPane_1);

		String[] columnNames = { "First Name", "Last Name", "Age", "Gender", "Vegetarian" };

		String[][] dataValues = { { "Kathy", "Smith", "25", "F", "false" }, { "John", "Doe", "43", "M", "false" },
				{ "Sue", "Black", "61", "F", "true" }, { "Jane", "White", "17", "F", "true" },
				{ "Joe", "Brown", "32", "M", "false" }, { "Abby", "Dawn", "41", "F", "false" },
				{ "Mila", "Manson", "26", "F", "true" }, { "Jack", "Schmitt", "32", "M", "true" } };

		DefaultTableModel tableModel = new DefaultTableModel(dataValues, columnNames);

		table = new JTable(tableModel);
		scrollPane_1.setViewportView(table);

		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { tabbedPane }));
	}
}