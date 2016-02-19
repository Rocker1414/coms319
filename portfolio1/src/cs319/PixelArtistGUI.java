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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;

import javax.imageio.ImageIO;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Insets;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	
	public Color leftColor;
	public Color rightColor;
	
	public static int DEFAULT_X = 16;
	public static int DEFAULT_Y = 16;
	
	public PADataModel paModel;
	
	private String palette[][] = {{"black", "red", "orange", "yellow", "green"},
						  {"white", "brown", "blue", "pink", "magenta"}
						 };
	
	public JTable table_1;
	public JTable selectedColor;
	public JTable colorOptions;

	public PixelArtistGUI() {
		setTitle("PixelArtist");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		this.setPreferredSize(new Dimension(400, 500));

		//DataModel
		paModel = new PADataModel(DEFAULT_X, DEFAULT_Y);
		
		//default colors
		leftColor = parseColor("black");
		rightColor = parseColor("white");
		
		// Content Pane
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 392 };
		gbl_contentPane.rowHeights = new int[] { 370, 30, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0 };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0 };
		contentPane.setLayout(gbl_contentPane);

		GridBagConstraints gbc_table_1 = new GridBagConstraints();
		gbc_table_1.gridwidth = 3;
		gbc_table_1.fill = GridBagConstraints.BOTH;
		gbc_table_1.gridx = 0;
		gbc_table_1.gridy = 0;

		JPanel topPanel = new JPanel();
		topPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		topPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		GridBagConstraints gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.insets = new Insets(0, 0, 5, 0);
		gbc_topPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_topPanel.fill = GridBagConstraints.BOTH;
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
		contentPane.add(topPanel, gbc_topPanel);
		topPanel.setLayout(new GridLayout(0, 1, 0, 0));
	
		
		
		String[] colHeadings = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
		int numRows = 16;
		PixelModel model = new PixelModel(numRows, colHeadings.length);
		model.setColumnIdentifiers(colHeadings);
		
		table_1 = new JTable(model);
		topPanel.add(table_1);
		
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setRowSelectionAllowed(false);
		table_1.setDefaultRenderer(Object.class, new CustomRenderer());
		//table_1.setDefaultEditor(Object.class, new CustomEditor());
		table_1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table_1.setCellSelectionEnabled(true);
		table_1.setRowHeight(23);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
		
				boolean leftClick = SwingUtilities.isLeftMouseButton(e); 
				boolean rightClick = SwingUtilities.isRightMouseButton(e);
				
				JTable table = (JTable)e.getSource();
				int row = table.rowAtPoint( e.getPoint() );
	            int column = table.columnAtPoint( e.getPoint() );
	            
	            //default s val
	            Color c = leftColor;
	            if(rightClick){
	            	c = rightColor;
	            }	         
	            	
            	paModel.changePixelAt(row, column, c.getRGB());
            	table.setValueAt(c, row, column);
					


				
			}
		});

		JPanel menuPanel = new JPanel();
		GridBagConstraints gbc_menuPanel = new GridBagConstraints();
		gbc_menuPanel.insets = new Insets(0, 0, 5, 0);
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
		
		JButton paSave = new JButton("Save PA file");
		paSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
					     "PixelArtist file (*.pxla)", "pxla");
				fc.setFileFilter(filter);
				int returnVal = fc.showSaveDialog(PixelArtistGUI.this);
				
				if(returnVal == JFileChooser.APPROVE_OPTION){
					String filename = PADataModel.getFileName(fc.getSelectedFile().getAbsolutePath());
					
					paModel.savePAImage(filename);
				}
			}
		});
		GridBagConstraints gbc_paSave = new GridBagConstraints();
		gbc_paSave.insets = new Insets(0, 0, 5, 5);
		gbc_paSave.gridx = 0;
		gbc_paSave.gridy = 0;
		menuPanel.add(paSave, gbc_paSave);
		
		JButton paLoad = new JButton("Load PA File");
		GridBagConstraints gbc_paLoad = new GridBagConstraints();
		gbc_paLoad.insets = new Insets(0, 0, 5, 5);
		gbc_paLoad.gridx = 1;
		gbc_paLoad.gridy = 0;
		menuPanel.add(paLoad, gbc_paLoad);
		paLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
					     "PixelArtist files (*.pxla)", "pxla");
				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(PixelArtistGUI.this);
				
				
				if(returnVal == JFileChooser.APPROVE_OPTION){
					
					PADataModel newModel = new PADataModel(fc.getSelectedFile().getAbsolutePath());
					if(newModel.isLoaded()){
						paModel = newModel;
					}
					paintTable();
				}
				
			}
		});

		// Screenshot button

		JButton screenshotButton = new JButton("Save Screenshot");
		GridBagConstraints gbc_screenshotButton = new GridBagConstraints();
		gbc_screenshotButton.insets = new Insets(0, 0, 5, 0);
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
		
		JPanel colorPalette = new JPanel();
		colorPalette.setLayout(null);
		GridBagConstraints gbc_colorPalette = new GridBagConstraints();
		gbc_colorPalette.fill = GridBagConstraints.BOTH;
		gbc_colorPalette.gridx = 0;
		gbc_colorPalette.gridy = 2;
		contentPane.add(colorPalette, gbc_colorPalette);
		
		
		
		String[] colHeadings2 = { "" };
		int numRows2 = 2;
		PixelModel selectedModel = new PixelModel(numRows2, colHeadings2.length);
		selectedModel.setColumnIdentifiers(colHeadings2);
		
		selectedColor = new JTable();
		selectedColor.setRowSelectionAllowed(false);
		selectedColor.setBorder(new LineBorder(new Color(0, 0, 0)));
		selectedColor.setBounds(28, 11, 23, 46);
		selectedColor.setModel(selectedModel);
		selectedColor.setDefaultRenderer(Object.class, new CustomRenderer());
		selectedColor.setRowHeight(23);

		//color the selected
		colorPalette.add(selectedColor);
		
		
		String[] colHeadings3 = new String[palette[0].length];
		int numRows3 = palette.length;
		PixelModel paletteModel = new PixelModel(numRows3, colHeadings3.length);
		paletteModel.setColumnIdentifiers(colHeadings3);
		
		colorOptions = new JTable();
		colorOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				boolean leftClick = SwingUtilities.isLeftMouseButton(e); 
				boolean rightClick = SwingUtilities.isRightMouseButton(e);
				
				JTable table = (JTable)e.getSource();
				int row = table.rowAtPoint( e.getPoint() );
	            int column = table.columnAtPoint( e.getPoint() );
	            
	            Color c = null;
	            if(leftClick){
	            	leftColor = parseColor(palette[row][column]);
	            	c = leftColor;
	            }
	            else if(rightClick){
	            	
	            	rightColor = parseColor(palette[row][column]);
	            	c = rightColor;
	            }
	            	          
            	int pos = leftClick ? 0 : 1;
            	//set the correct color for the selected color table
	            selectedColor.setValueAt(c, pos, 0); 
	            
	          
			}
		});
		colorOptions.setRowSelectionAllowed(false);
		colorOptions.setBorder(new LineBorder(new Color(0, 0, 0)));
		colorOptions.setBounds(87, 11, 115, 46);
		colorOptions.setModel(paletteModel);
		colorOptions.setRowHeight(23);
		colorOptions.setDefaultRenderer(Object.class, new CustomRenderer());
		//color the palette
		
		paintSelected();
		paintPalette();
		
		colorPalette.add(colorOptions);
		
		JButton btnClearCanvas = new JButton("Clear Canvas");
		btnClearCanvas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PADataModel newModel = new PADataModel(DEFAULT_X, DEFAULT_Y);
				paModel = newModel;
				paintTable();
			}
		});
		btnClearCanvas.setBounds(287, 11, 97, 46);
		colorPalette.add(btnClearCanvas);
		
		screenshotButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				saveImageAsJPEG(topPanel);
				screenshotLabel.setText("Screenshot saved");
			}
		});

		this.pack();
	}

	// Save screenshot of JPanel
	public void saveImageAsJPEG(JPanel panel) {
		Dimension size = panel.getSize();
		BufferedImage img = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = img.createGraphics();
		panel.paint(g2);

		try {
			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
				     "PNG file (*.png)", "png");
			fc.setFileFilter(filter);
			int returnVal = fc.showSaveDialog(PixelArtistGUI.this);
			
			if(returnVal == JFileChooser.APPROVE_OPTION){
				String filename = PADataModel.getFileName(fc.getSelectedFile().getAbsolutePath());
				String extension = PADataModel.getFileExtension(fc.getSelectedFile().getAbsolutePath());
				if(!extension.equals("png")){
				
					ImageIO.write(img, "PNG", new File(filename + ".png"));
				    
				}
				else{
					ImageIO.write(img, "PNG", fc.getSelectedFile());
				}
			
				//OutputStream out = new FileOutputStream(fc.getSelectedFile().getAbsolutePath());
//				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//				encoder.encode(myImage);
				//out.close();
			}
		
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
	
	public void paintTable(){
		for(int i = 0; i < paModel.getWidth(); i++){
			for(int j = 0; j < paModel.getHeight(); j++){
				table_1.setValueAt(new Color(paModel.getPixelAt(i, j)), i, j);
			}
		}
	}
	
	public void paintPalette(){
		for(int i = 0; i < palette.length; i++){
			for(int j = 0; j < palette[0].length; j++){
				colorOptions.setValueAt(parseColor(palette[i][j]), i, j);
			}
		}
	}
	
	public void paintSelected(){
		selectedColor.setValueAt(leftColor, 0, 0);
		selectedColor.setValueAt(rightColor, 1, 0);
	}
	
	public Color parseColor(String cString){
		try {
        	Color c;

        	if (cString.equals("brown")){
				c = new Color(102, 51, 0);
			}
			else {
				Field field;
				
				field = Class.forName("java.awt.Color").getField(cString);
				
				c = (Color) field.get(null);
			}
        	
        	return c;
		}
		catch(Exception e){
			
		}
		
		return null;
		
	}
	

	// Custom table model to make the cells selectable but not editable
	public class PixelModel extends DefaultTableModel {

		PixelModel(int numRows, int numColumns) {
			super(numRows, numColumns);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

	}
}