package gameobjects.button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import commoninterfaces.Drawable;
import enums.ImageId;
import gameobjects.GenericItem;
import utils.ImageManager;
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

	public ImageId getImageId() {
		return imageId;
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
			g.setColor(Color.PINK);
			g.fillRect((int)this.getPoint().getX(), 
					(int)this.getPoint().getY(),
					this.getBounds().getWidth(),
					this.getBounds().getHeight());
		}

	}
}
