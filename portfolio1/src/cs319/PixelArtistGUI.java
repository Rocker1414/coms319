package cs319;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class PixelArtistGUI extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}

				PixelArtistGUI frame = new PixelArtistGUI();
				frame.setVisible(true);
			}
		});
	}

	String colors[] = { "Red", "Orange", "Yellow", "Green", "Blue", "Magenta", "Pink", "Brown", "Black", "White" };
	JComboBox colorList = new JComboBox(colors);

	public PixelArtistGUI() {
		setTitle("PixelArtist");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		this.setPreferredSize(new Dimension(400, 450));

		// Content Pane
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 392 };
		gbl_contentPane.rowHeights = new int[] { 370, 30 };
		gbl_contentPane.columnWeights = new double[] { 0.0 };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0 };
		contentPane.setLayout(gbl_contentPane);

		String[] colHeadings = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
		int numRows = 16;
		PixelModel model = new PixelModel(numRows, colHeadings.length);
		model.setColumnIdentifiers(colHeadings);

		GridBagConstraints gbc_table_1 = new GridBagConstraints();
		gbc_table_1.gridwidth = 3;
		gbc_table_1.fill = GridBagConstraints.BOTH;
		gbc_table_1.gridx = 0;
		gbc_table_1.gridy = 0;

		JPanel topPanel = new JPanel();
		topPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		topPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		GridBagConstraints gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_topPanel.fill = GridBagConstraints.BOTH;
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
		contentPane.add(topPanel, gbc_topPanel);
		topPanel.setLayout(new GridLayout(0, 1, 0, 0));
		JTable table_1 = new JTable(model);
		topPanel.add(table_1);
		table_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				TableCellEditor editor = table_1.getCellEditor();
				if (editor != null) {
					if (editor.stopCellEditing()) {
						editor.cancelCellEditing();
					}
				}
			}
		});
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setRowSelectionAllowed(false);
		table_1.setDefaultRenderer(Object.class, new CustomRenderer());
		table_1.setDefaultEditor(Object.class, new CustomEditor());
		table_1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table_1.setCellSelectionEnabled(true);
		table_1.setRowHeight(23);

		JPanel menuPanel = new JPanel();
		GridBagConstraints gbc_menuPanel = new GridBagConstraints();
		gbc_menuPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_menuPanel.gridx = 0;
		gbc_menuPanel.gridy = 1;
		contentPane.add(menuPanel, gbc_menuPanel);
		GridBagLayout gbl_menuPanel = new GridBagLayout();
		gbl_menuPanel.columnWidths = new int[] { 131, 131, 131 };
		gbl_menuPanel.rowHeights = new int[] { 20, 0, 0 };
		gbl_menuPanel.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gbl_menuPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		menuPanel.setLayout(gbl_menuPanel);

		JLabel colorSelect = new JLabel("Select Color:");
		GridBagConstraints gbc_colorSelect = new GridBagConstraints();
		gbc_colorSelect.fill = GridBagConstraints.BOTH;
		gbc_colorSelect.gridx = 0;
		gbc_colorSelect.gridy = 0;
		menuPanel.add(colorSelect, gbc_colorSelect);
		colorSelect.setFont(new Font("Tahoma", Font.PLAIN, 14));
		colorSelect.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_colorList = new GridBagConstraints();
		gbc_colorList.anchor = GridBagConstraints.NORTH;
		gbc_colorList.fill = GridBagConstraints.BOTH;
		gbc_colorList.gridx = 1;
		gbc_colorList.gridy = 0;
		menuPanel.add(colorList, gbc_colorList);

		// Screenshot button

		JButton screenshotButton = new JButton("Save Screenshot");
		GridBagConstraints gbc_screenshotButton = new GridBagConstraints();
		gbc_screenshotButton.fill = GridBagConstraints.BOTH;
		gbc_screenshotButton.gridx = 2;
		gbc_screenshotButton.gridy = 0;
		menuPanel.add(screenshotButton, gbc_screenshotButton);

		JLabel screenshotLabel = new JLabel("");
		screenshotLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		GridBagConstraints gbc_screenshotLabel = new GridBagConstraints();
		gbc_screenshotLabel.anchor = GridBagConstraints.SOUTHWEST;
		gbc_screenshotLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_screenshotLabel.gridwidth = 3;
		gbc_screenshotLabel.gridx = 0;
		gbc_screenshotLabel.gridy = 1;
		menuPanel.add(screenshotLabel, gbc_screenshotLabel);
		screenshotButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
				Date today = Calendar.getInstance().getTime();
				String fileAppend = df.format(today);

				saveComponentAsJPEG(topPanel, fileAppend);
				screenshotLabel.setText("Screenshot saved to project directory as PixelArt" + fileAppend + ".jpeg");
			}
		});

		this.pack();
	}

	// Save screenshot of JPanel
	public void saveComponentAsJPEG(JPanel panel, String fileAppend) {
		Dimension size = panel.getSize();
		BufferedImage myImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = myImage.createGraphics();
		panel.paint(g2);

		try {
			OutputStream out = new FileOutputStream("PixelArt" + fileAppend + ".jpeg");
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(myImage);
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Paints the cells based on given value
	public class CustomRenderer extends DefaultTableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			super.getTableCellRendererComponent(table, null, false, hasFocus, row, column);
			if (value != null && value instanceof Color) {
				setBackground((Color) value);
			} else {
				setBackground(null);
			}
			return this;
		}
	}

	// Gets selected color
	public class CustomEditor extends AbstractCellEditor implements TableCellEditor {

		private JPanel panel;

		public CustomEditor() {
			this.panel = new JPanel();
		}

		@Override
		public Color getCellEditorValue() {
			Color c = null;
			try {
				String cString = colorList.getSelectedItem().toString().toLowerCase();
				if (cString.equals("brown")){
					c = new Color(102, 51, 0);}
				else {
					Field field = Class.forName("java.awt.Color").getField(cString);
					c = (Color) field.get(null);
				}
			} catch (Exception e) {
				c = null;
			}
			return c;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			panel.setBackground((Color) getCellEditorValue());
			return panel;
		}

		@Override
		public boolean isCellEditable(EventObject e) {
			return true;
		}

	}

	// Custom table model to make the cells selectable but not editable
	public class PixelModel extends DefaultTableModel {

		PixelModel(int numRows, int numColumns) {
			super(numRows, numColumns);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return true;
		}

	}
}