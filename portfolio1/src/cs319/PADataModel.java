package cs319;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.imageio.ImageIO;

/* This data structure keeps track of the color values in each of the pixel cells
 * It also can load and save itself
 */
public class PADataModel {
	
	public int[][] pixelVals;
	
	private boolean modelLoaded;
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
		modelLoaded = true;
	}
	
	public PADataModel(String filename){
		modelLoaded = false;
		loadPAImage(filename);
	}
	
	public void changePixelAt(int r, int c, int color){
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
	
	private void loadPAImage(String filename){
		String extension = getFileExtension(filename);
		if(!extension.equals("pa")){
			return;
		}
		try {
			FileInputStream fi = new FileInputStream(filename);
			
			byte[] x = new byte[4];
			byte[] y = new byte[4];
			
			
			//read x size
			
			fi.read(x, 0, 4);			
			//read y size
			
			fi.read(y, 0, 4);

			
			ByteBuffer bb = ByteBuffer.allocate(8);
			bb.order(ByteOrder.BIG_ENDIAN);
			
			bb.put(x);
			bb.put(y);
			//process sizes
			xSize = bb.getInt(0);
			ySize = bb.getInt(4);
			
			pixelVals = initPixels();
			//size of remaining
			int size = 4*xSize*ySize;
			
			byte[] data = new byte[size];
			fi.read(data);
			
			ByteBuffer dataBuffer = ByteBuffer.allocate(size);
			dataBuffer.order(ByteOrder.BIG_ENDIAN);
			
			dataBuffer.put(data);
			
			int index = 0;
			
			//process data
			for(int i = 0; i < xSize; i++){
				for(int j = 0; j < ySize; j++){
					
					pixelVals[i][j] = dataBuffer.getInt(index);
					
					index += 4;
				}
				
			}
			
			modelLoaded = true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			System.out.println("Error reading file");
		}
		
		
				
	}
	


	public static String getFileExtension(String filename) {
		
	    try {
	        return filename.substring(filename.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	        return "";
	    }
	}
	
	public static String getFileName(String filename) {
		if(!filename.contains(".")){
			return filename;
		}
	    try {
	        return filename.substring(0, filename.lastIndexOf("."));
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return "";
	    }
	}


	
	public void savePAImage(String fn){
		
		String filename = getFileName(fn);		
		
		try {

			//size = 4(x size) + 4(ysize) + 4*xsize*ysize (all of the pixels)
			int size = 4 + 4 + 4*xSize*ySize;
			FileOutputStream fo = new FileOutputStream(filename + ".pa", false);
			ByteBuffer bb = ByteBuffer.allocate(size);
			bb.order(ByteOrder.BIG_ENDIAN);
			
			//first output x and y size
			bb.putInt(xSize);
			bb.putInt(ySize);
			
			//now all pixels
			for(int i = 0; i < xSize; i++){
				for(int j = 0; j < ySize; j++){
					
					bb.putInt(pixelVals[i][j]);
				}
				
			}
			
			fo.write(bb.array());
			
			fo.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error saving file");
		}
		
		
	}
	
	public int getPixelAt(int r, int c){
		
		return pixelVals[r][c];
	}
	
	public void outputPixelsInConsole(){
		
		for(int i = 0; i < xSize; i++){
			for(int j = 0; j < ySize; j++){
				System.out.println(pixelVals[i][j]);
			}
			
		}
		
	}
	
	public boolean isLoaded(){
		return modelLoaded;
	}
	
	public void clear(){
		pixelVals = initPixels();
	}
	
}
