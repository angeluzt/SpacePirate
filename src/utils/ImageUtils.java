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

	public final Image readRegularImage(String path, int width, int height) {
		try {
			// Read and scale image
			Image outputImage = ImageIO.read(this.getClass().getResourceAsStream(path))
					.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			
			return outputImage;
		} catch (Exception e) {
			System.out.println("ERROR While reading regular image: " + path);
			System.out.println(e);
			return null;
		}
	}
	
	// Read and scale image
	public final Image readSmoothImage(String path, int width, int height) {
		try {
			Image outputImage = ImageIO.read(this.getClass().getResourceAsStream(path))
					.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			
			return outputImage;
		} catch (Exception e) {
			System.out.println("ERROR While reading smooth image: " + path);
			System.out.println(e);
			return null;
		}
	}
}
