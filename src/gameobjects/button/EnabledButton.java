package gameobjects.button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import enums.ImageId;
import gameobjects.gui.GenericGui;
import utils.Constants;
import utils.ImageManager;
import utils.Size;
import utils.Trigonometry;

public class EnabledButton extends GenericButton {

	private boolean isButtonActive = true;

	public EnabledButton(ImageId imageId, Point location, Size size) {
		super(imageId, location, size);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void isElementClicked(Point point, GenericGui currentUi) {
		super.isElementClicked(point, currentUi);

		boolean isClicket = Trigonometry.isPointInsideRegion(point, this);
		
		if(isClicket) {
			isButtonActive = !isButtonActive;
		}
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
			g.drawImage(ImageManager.getImage(this.imageId), 
					(int)this.getPoint().getX(), 
					(int)this.getPoint().getY(),
					this.getSize().getWidth(),
					this.getSize().height,
					null);
		}

		if(!isButtonActive){
			g.setColor(Constants.disabledBtnColor);
			g.fillRect(
					(int)this.getPoint().getX(), 
					(int)this.getPoint().getY(),
					this.getSize().width,
					this.getSize().height);
			g.setColor(Color.BLACK);
			g.drawLine(
					this.getPoint().x + this.getSize().width, 
					this.getPoint().y, 
					this.getPoint().x, 
					this.getPoint().y + this.getSize().height);
		}
	}

	public boolean isButtonActive() {
		return isButtonActive;
	}

	public void setButtonActive(boolean isButtonActive) {
		this.isButtonActive = isButtonActive;
	}
}
