package gameobjects.gui;

import java.awt.Graphics;
import java.awt.Point;

import commoninterfaces.Drawable;
import enums.ImageId;
import gameobjects.GenericItem;
import gameobjects.matrix.WindowSquare;
import utils.ImageManager;
import utils.Size;

public abstract class GenericGui extends GenericItem implements Drawable {

	private ImageId windowId;
	private ImageId windowTitle;
	private WindowSquare internalSquare;

	private Point pointInWindow;
	private Size sizeInWindow;
	protected WindowSquare sections;

	public GenericGui(ImageId imageId, Point point, Size size, float xPercent, float yPercent, float downPercent) {
		super(point, size);
		this.windowId = imageId;
		ImageManager.addImage(imageId, size);
		
		this.pointInWindow = new Point(
				(int)(this.getPoint().getX() + (this.getSize().width * xPercent)), 
				(int)(this.getPoint().getY() + (this.getSize().height * yPercent))
		);

		this.sizeInWindow = new Size(
				(int)(this.getSize().width - (this.getSize().width * xPercent) * 2), 
				(int)(this.getSize().height - (this.getSize().height * downPercent))
		);
		
		this.sections = new WindowSquare(this.pointInWindow, this.sizeInWindow);
	}
	
	public Point getPointInWindow() {
		return this.pointInWindow;
	}
	
	public Size getSizeInWindow() {
		return sizeInWindow;
	}

	public ImageId getWindowId() {
		return windowId;
	}

	public void setWindowId(ImageId windowId) {
		this.windowId = windowId;
	}

	public WindowSquare getInternalSquare() {
		return internalSquare;
	}

	public void setInternalSquare(WindowSquare internalSquare) {
		this.internalSquare = internalSquare;
	}

	public void setPointInWindow(Point pointInWindow) {
		this.pointInWindow = pointInWindow;
	}

	public void setSizeInWindow(Size sizeInWindow) {
		this.sizeInWindow = sizeInWindow;
	}
	
	public WindowSquare getSections() {
		return sections;
	}

	public void setSections(WindowSquare sections) {
		this.sections = sections;
	}

	@Override
	public void drawElement(Graphics g) {
		g.drawImage(ImageManager.getImage(this.windowId), 
				(int)this.getPoint().getX(), 
				(int)this.getPoint().getY(), 
				this.getSize().width, 
				this.getSize().height, null);
	}

	public abstract void setShell();
}
