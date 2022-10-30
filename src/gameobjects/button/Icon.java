package gameobjects.button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import commoninterfaces.Drawable;
import enums.ImageId;
import gameobjects.GenericItem;
import utils.ImageManager;
import utils.ImageUtils;
import utils.Size;

public class Icon extends GenericItem implements Drawable {

	protected ImageId imageId;

	public Icon(Point point, Size size) {
		super(point, size);
	}

	public Icon(ImageId imageId, Point point, Size size) {
		super(point, size);
		ImageManager.addSmoothImage(imageId, size);
		this.imageId = imageId;
	}
	
	public Icon(ImageId imageId, Point point, int size) {
		super(point, size);
		ImageManager.addSmoothImage(imageId, this.getSize());
		this.imageId = imageId;
	}

	public ImageId getImageId() {
		return imageId;
	}
	
	public void resizeIcon(Size newSize) {
		this.setSize(newSize);
		
		Image resizedImage = ImageUtils.getImageUtils().readSmoothImage(imageId.getPath(), newSize.width, newSize.height);
		ImageManager.replaceImage(imageId, resizedImage);
		//ImageManager.removeImage(imageId);
		//ImageManager.addSmoothImage(imageId, newSize);
	}

	public void setImageId(ImageId imageId) {
		ImageManager.addSmoothImage(imageId, this.getSize());
		ImageManager.removeImage(this.imageId);

		this.imageId = imageId;
	}

	@Override
	public void drawElement(Graphics g) {
		if(!this.isVisible()) {
			return;
		}

		if(ImageManager.getImage(imageId) != null) {
			g.drawImage(ImageManager.getImage(imageId), 
					(int)this.getPoint().getX(), 
					(int)this.getPoint().getY(), null);
		} else {
			/*g.setColor(Color.PINK);
			g.fillRect((int)this.getPoint().getX(), 
					(int)this.getPoint().getY(),
					this.getBounds().getWidth(),
					this.getBounds().getHeight());*/
		}

	}
}
