package utils;

import java.awt.Image;

import javax.imageio.ImageIO;

public final class ImageUtils {

	private static ImageUtils imageUtils;
	
	private ImageUtils() {
		
	}
	
	public static final ImageUtils getImageUtils() {
		if(imageUtils != null) {
			return imageUtils;
		} else {
			imageUtils = new ImageUtils();
			return imageUtils;
		}
	}

	public final Image readImageDefault(String path, int width, int height) {
		try {
			// Reads input image
			Image outputImage = ImageIO.read(this.getClass().getResourceAsStream(path))
					.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			
			return outputImage;
		} catch (Exception e) {
			System.out.println("ERROR While reading image: " + path + "\n " + e);
			return null;
		}
	}
	
	public final Image readImageSmooth(String path, int width, int height) {
		try {
			// Reads input image
			Image outputImage = ImageIO.read(this.getClass().getResourceAsStream(path))
					.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			
			return outputImage;
		} catch (Exception e) {
			System.out.println("ERROR While reading image: " + path + "\n " + e);
			return null;
		}
	}
}
