package gameobjects.button;

import java.awt.Point;

import commoninterfaces.Clickable;
import commoninterfaces.Drawable;
import enums.ImageId;
import utils.Size;
import utils.Trigonometry;

public class GenericButton extends Icon implements Drawable, Clickable, Runnable {

	protected boolean isAnimationActive = false;

	public GenericButton(ImageId imageId, Point location, Size size) {
		super(imageId, location, size);
		
		this.imageId = imageId;
	}
	
	public GenericButton(ImageId imageId, Point location, int size) {
		super(imageId, location, size);
		
		this.imageId = imageId;
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
		Size tempSize = this.getSize().getSize();

		int tempX = (int)(this.getPoint().x), tempY = (int)(this.getPoint().y);
		int tempWidth = (int)(this.getSize().width * 0.8), tempHeight = (int)(this.getSize().height * 0.8);

		int marginX = this.getSize().width - tempWidth, marginY = this.getSize().height - tempHeight;

		this.setPoint(new Point(tempX + marginX / 2, tempY + marginY /2));
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
