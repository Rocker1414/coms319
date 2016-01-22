package cs319;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Color;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JList;

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
	 */
	public Lab2Swing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setBounds(0, 239, 434, 22);
		contentPane.add(tabbedPane);
		
		JPanel listPanel = new JPanel();
		tabbedPane.addTab("List", null, listPanel, null);
		
		JList list = new JList();
		listPanel.add(list);
		
		JPanel treePanel = new JPanel();
		tabbedPane.addTab("Tree", null, treePanel, null);
		
		JPanel tablePanel = new JPanel();
		tabbedPane.addTab("Table", null, tablePanel, null);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tabbedPane}));
	}
}
