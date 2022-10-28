package gameobjects.button;

import java.awt.Graphics;
import java.awt.Point;

import commoninterfaces.Drawable;
import enums.ImageId;
import gameobjects.GenericItem;
import utils.ImageManager;
import utils.Size;

public class Icon extends GenericItem implements Drawable {

	protected ImageId imageId;

	public Icon(ImageId imageId, Point point, Size size) {
		super(point, size);
		ImageManager.addSmoothImage(imageId, size);
		this.imageId = imageId;
	}

	public ImageId getImageId() {
		return imageId;
	}

	public void setImageId(ImageId imageId) {
		this.imageId = imageId;
	}

	@Override
	public void drawElement(Graphics g) {
		g.drawImage(ImageManager.getImage(imageId), 
				(int)this.getPoint().getX(), 
				(int)this.getPoint().getY(), 
				this.getSize().width, 
				this.getSize().height, null);
	}
}
