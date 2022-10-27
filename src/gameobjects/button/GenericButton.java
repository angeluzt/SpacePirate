package gameobjects.button;

import java.awt.Graphics;
import java.awt.Point;

import commoninterfaces.Clickable;
import commoninterfaces.Drawable;
import enums.ImageId;
import gameobjects.GenericItem;
import utils.ImageManager;
import utils.Size;
import utils.Trigonometry;

public class GenericButton extends GenericItem implements Drawable, Clickable, Runnable {

	protected ImageId imageId;
	protected boolean isAnimationActive = false;

	public GenericButton(ImageId imageId, Point location, Size size) {
		super(location, size);
		this.imageId = imageId;
	}

	@Override
	public void drawElement(Graphics g) {
		g.drawImage(ImageManager.getImage(imageId), 
				(int)this.getPoint().getX(), 
				(int)this.getPoint().getY(), 
				this.getSize().getWidth(), 
				this.getSize().getHeight(), null);
	}

	@Override
	public boolean isElementClicked(Point point) {
		if(isAnimationActive) {
			return false;
		}

		boolean isClicket = Trigonometry.isPointInsideRegion(point, this);
		
		if(isClicket) {
			this.isAnimationActive = true;
			new Thread(this).start();
		}

		return isClicket;
	}

	@Override
	public void run() {
		Point tempLocation = this.getPoint().getLocation();
		Size tempSize = this.getSize().getCloneSize();

		int tempX = (int)(this.getPoint().x), tempY = (int)(this.getPoint().y);
		int tempWidth = (int)(this.getSize().getWidth() * 0.8), tempHeight = (int)(this.getSize().getHeight() * 0.8);
		
		int marginX = this.getSize().getWidth() - tempWidth, marginY = this.getSize().getHeight() - tempHeight;
		
		this.setPoint(new Point(tempX + marginX / 2, tempY + marginY / 2));
		this.setSize(new Size(tempWidth, tempHeight));
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.setPoint(tempLocation);
		this.setSize(tempSize);
		
		this.isAnimationActive = false;
	}
}
