package gameobjects.button;

import java.awt.Point;

import commoninterfaces.Clickable;
import commoninterfaces.Drawable;
import enums.ImageId;
import enums.Sounds;
import gameobjects.gui.GenericGui;
import utils.Constants;
import utils.Size;
import utils.Sound;
import utils.Trigonometry;

public class GenericButton extends Icon implements Drawable, Clickable, Runnable {

	protected boolean isAnimationActive = false;
	private GenericGui referenceUI;

	public GenericButton(ImageId imageId, Point location, Size size) {
		super(imageId, location, size);
		
		this.imageId = imageId;	}
	
	public GenericButton(ImageId imageId, Point location, int size) {
		super(imageId, location, size);
		
		this.imageId = imageId;
	}

	@Override
	public void isElementClicked(Point point, GenericGui currentUi) {
		if(isAnimationActive) {
			return;
		}

		boolean isClicket = Trigonometry.isPointInsideRegion(point, this);
		
		if(isClicket) {
			new Sound().setFile(Sounds.BUTTON_CLICK, Constants.SOUND_EFFECTS_LEVEL).play();

			this.referenceUI = currentUi;
			this.isAnimationActive = true;
			new Thread(this).start();
		}
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
		
		// reset animation
		this.isAnimationActive = false;
		
		// Trigger event for specific UI
		this.referenceUI.activateEvent(imageId);
		this.referenceUI = null;
	}
}
