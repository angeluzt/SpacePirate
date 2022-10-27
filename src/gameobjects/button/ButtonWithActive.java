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
	}
	
	public void drawElement(Graphics g) {
		if(!this.isAnimationActive) {
			g.drawImage(ImageManager.getImage(this.imageId), 
					(int)this.getPoint().getX(), 
					(int)this.getPoint().getY(), 
					this.getSize().getWidth(), 
					this.getSize().getHeight(), null);
		} else {
			g.drawImage(ImageManager.getImage(this.imageActiveId), 
					(int)this.getPoint().getX(), 
					(int)this.getPoint().getY(), 
					this.getSize().getWidth(), 
					this.getSize().getHeight(), null);
		}
	}

}
