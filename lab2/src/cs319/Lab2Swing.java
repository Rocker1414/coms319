package cs319;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Color;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.ListModel;
import java.awt.ScrollPane;
import java.awt.List;
import java.awt.FlowLayout;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Lab2Swing extends JFrame {

	private JPanel contentPane;

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

		JPanel tablePanel = new JPanel();
		tabbedPane.addTab("Table", null, tablePanel, null);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { tabbedPane }));
	}
}