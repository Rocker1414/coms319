package cs319;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/* This data structure keeps track of the color values in each of the pixel cells
 * It also can load and save itself
 */
public class PADataModel {
	
	public int[][] pixelVals;
	
	private int xSize;
	private int ySize;
	
	//RGB value for white
	public static int RGB_WHITE = 16777215;
	
	private String palette[][] = {{"black", "red", "orange", "yellow", "green"},
			  {"white", "brown", "blue", "pink", "magenta"}
			 };
	
	public PADataModel(int r, int c){
		xSize = r;
		ySize = c;
		pixelVals = initPixels();
	}
	
	public void changePixel(int r, int c, int color){
		pixelVals[r][c] = color;
	}
	
	private int[][] initPixels()
	{
		int[][] p = new int[xSize][ySize];
		for (int i = 0; i < xSize; i++) {
		       for (int j = 0; j < ySize; j++) {
		    	   p[i][j] = RGB_WHITE;
		    	   
		       }
		    }
		
		return p;
	}
	
	public int getHeight(){
		
		return ySize;
	}
	
	public int getWidth(){
		return xSize;
		
	}
	
	public void loadPAImage(){
		//File file= new File(filename);
				File file= new File("PixelArt201602140218.png");

				try {
					BufferedImage image = ImageIO.read(file);
					int width = image.getWidth();
				    int height = image.getHeight();
				    int[][] pixel = new int[height][width];

				    for (int i = 0; i < height; i++) {
				       for (int j = 0; j < width; j++) {
				    	   pixel[i][j] = image.getRGB(i, j);
				    	   
				       }
				    }

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}
	
	public void savePAImage(){
		
	}
	
	public int getPixelAt(int r, int c){
		
		return pixelVals[r][c];
	}
	
	public void outputPixels(){
		
		for(int i = 0; i < xSize; i++){
			for(int j = 0; j < ySize; j++){
				System.out.println(pixelVals[i][j]);
			}
			
		}
		
	}
	
}
