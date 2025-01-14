package gameobjects.button;

import java.awt.Graphics;
import java.awt.Point;

import enums.ImageId;
import utils.ImageManager;
import utils.Size;

public class ButtonWithActive extends GenericButton {

	private ImageId imageActiveId;
	
	public ButtonWithActive(ImageId imageId, ImageId imageActiveId, Point location, Size size) {
		super(imageId, location, size);
		
		this.imageActiveId = imageActiveId;

		ImageManager.addImage(imageActiveId, size);
	}

	public ButtonWithActive(ImageId imageId, ImageId imageActiveId, Point location, int size) {
		super(imageId, location, size);
		
		this.imageActiveId = imageActiveId;

		ImageManager.addImage(imageActiveId, this.getSize());
	}
	
	public void drawElement(Graphics g) {
		if(!this.isVisible()) {
			return;
		}

		if(!this.isAnimationActive) {
			g.drawImage(ImageManager.getImage(this.imageId), 
					(int)this.getPoint().getX(), 
					(int)this.getPoint().getY(), null);
		} else {
			// Scaling is required here
			g.drawImage(ImageManager.getImage(this.imageActiveId), 
					(int)this.getPoint().getX(), 
					(int)this.getPoint().getY(),
					this.getSize().getWidth(),
					this.getSize().height,
					null);
		}
	}
	
	@Override
	public void removeComponents() {
		ImageManager.removeImage(imageId);
		ImageManager.removeImage(imageActiveId);
	}
	
	public ImageId getImageId() {
		return imageActiveId;
	}
}
