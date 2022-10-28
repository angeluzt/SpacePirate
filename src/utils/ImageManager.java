package utils;

import java.awt.Image;
import java.util.HashMap;

import enums.ImageId;

public final class ImageManager {

	public final static HashMap<ImageId, Image> GUI_IMAGES = new HashMap<>();
	public final static HashMap<String, Image> SPRITES = new HashMap<>();
	
	// Handle regular images and icons
	public static void addImage(ImageId imageId, Size size) {
		if(!GUI_IMAGES.containsKey(imageId)) 
			GUI_IMAGES.put(imageId, ImageUtils.getImageUtils().readRegularImage(imageId.getPath(), size.width, size.height));
	}
	
	// Handle regular images and icons
	public static void addSmoothImage(ImageId imageId, Size size) {
		if(!GUI_IMAGES.containsKey(imageId)) 
			GUI_IMAGES.put(imageId, ImageUtils.getImageUtils().readSmoothImage(imageId.getPath(), size.width, size.height));
	}
	
	public static void removeImage(ImageId imageId) {
		GUI_IMAGES.remove(imageId);
	}
	
	public static Image getImage(ImageId imageId) {
		return GUI_IMAGES.get(imageId);
	}

	// Handle sprite images
	public static void addSpriteImage(String imageId, String path, Size size) {
		if(!SPRITES.containsKey(imageId)) 
			SPRITES.put(imageId, ImageUtils.getImageUtils().readSmoothImage(path, size.width, size.height));
	}
	
	public static void removeSpriteImage(String imageId) {
		SPRITES.remove(imageId);
	}
	
	public static Image getSpriteImage(String imageId) {
		return SPRITES.get(imageId);
	}
}
