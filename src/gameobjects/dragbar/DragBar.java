package gameobjects.dragbar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import commoninterfaces.DragDrop;
import enums.ImageId;
import gameobjects.button.Icon;
import gameobjects.gui.GenericGui;
import utils.ImageManager;
import utils.Size;
import utils.Trigonometry;

public class DragBar extends Icon implements DragDrop {

	
	private int currentWidth, currentHeight;
	private float actualValue;
	private Color barBackground = new Color(0, 0, 153, 80);

	public DragBar(ImageId imageId, Point point, Size size) {
		super(imageId, point, size);

		this.currentWidth = size.getWidth();
		this.currentHeight = size.getHeight();
		this.actualValue = 100;
	}
	
	@Override
	public void drawElement(Graphics g) {
		if(!this.isVisible()) {
			return;
		}

		g.setColor(barBackground);
		g.fillRect(this.getPoint().x, this.getPoint().y, this.getSize().width, this.getSize().height);

		g.drawImage(ImageManager.getImage(imageId), 
				(int)this.getPoint().getX(), 
				(int)this.getPoint().getY(),
				this.currentWidth,
				this.currentHeight, null);
	}

	@Override
	public void dragElement(Point point, GenericGui currentUi) {
		if(Trigonometry.isPointInsideRegion(point, this)) {

			int currentX = (this.getPoint().x + this.getSize().width) - point.x;
			
			// calculate the new size for the dragbar
			this.currentWidth = this.getSize().width - currentX;
			// calculate the corresponding size
			this.actualValue = (float)currentWidth / (float)this.getSize().width;
			System.out.println(this.actualValue);
			
			currentUi.activateEvent(imageId);
		}
	}

	public float getActualValue() {
		return actualValue;
	}

	public void setActualValue(float actualValue) {
		this.actualValue = actualValue;
	}
}
