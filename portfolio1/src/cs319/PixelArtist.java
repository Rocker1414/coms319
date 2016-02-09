package cs319;

import java.awt.EventQueue;

public class PixelArtist {

	public static void main(String[] args) {
		try {
			PixelArtistGUI frame = new PixelArtistGUI();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
