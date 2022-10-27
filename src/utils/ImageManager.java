package utils;

import java.awt.Image;
import java.util.HashMap;

import enums.ImageId;

public final class ImageManager {

	public final static HashMap<ImageId, Image> GUI_IMAGES = new HashMap<>();
	public final static HashMap<String, Image> SPRITES = new HashMap<>();
	
	private ImageManager() {
		
	}
	
	public static void addImage(ImageId imageId, String path, Size size) {
		if(!GUI_IMAGES.containsKey(imageId)) 
			GUI_IMAGES.put(imageId, ImageUtils.getImageUtils().readImageDefault(path, size.getWidth(), size.getHeight()));
	}
	
	public static void removeImage(ImageId imageId) {
		GUI_IMAGES.remove(imageId);
	}
	
	public static Image getImage(ImageId imageId) {
		return GUI_IMAGES.get(imageId);
	}

	public static void addSpriteImage(String imageId, String path, Size size) {
		if(!SPRITES.containsKey(imageId)) 
			SPRITES.put(imageId, ImageUtils.getImageUtils().readImageSmooth(path, size.getWidth(), size.getHeight()));
	}
	
	public static void removeSpriteImage(String imageId) {
		SPRITES.remove(imageId);
	}
	
	public static Image getSpriteImage(String imageId) {
		return SPRITES.get(imageId);
	}
}
