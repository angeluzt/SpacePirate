package gameobjects.gui;

import java.awt.Graphics;
import java.awt.Point;

import commoninterfaces.Drawable;
import enums.ImageId;
import gameobjects.GenericItem;
import gameobjects.button.Icon;
import gameobjects.matrix.WindowSquare;
import utils.ImageManager;
import utils.Size;
import utils.Utils;

public abstract class GenericGui extends GenericItem implements Drawable {

	private ImageId windowId;
	private Icon windowTitle;

	protected WindowSquare windowBounds;

	public GenericGui(ImageId windowId, Point point, Size size) {
		super(point, size);
		
		this.windowId = windowId;

		ImageManager.addImage(windowId, size);
		
		this.windowBounds = new WindowSquare(point, size);
	}

	public ImageId getWindowId() {
		return windowId;
	}

	public void setWindowId(ImageId windowId) {
		this.windowId = windowId;
	}
	
	public WindowSquare getWindowBounds() {
		return windowBounds;
	}

	public void setWindowBounds(WindowSquare windowBounds) {
		this.windowBounds = windowBounds;
	}

	public Icon getWindowHeader() {
		return windowTitle;
	}

	public void setWindowHeader(Icon windowTitle) {
		this.windowTitle = windowTitle;
	}
	
	/*public void setWindowHeaderIcon(ImageId windowIcon) {
		//this.setWindowHeaderIcon(ImageId.WINDOW_WINER_HEADER_WIN_TXT);
		ImageManager.addSmoothImage(windowIcon, windowTitle.getSize());
		ImageManager.removeImage(windowTitle.getImageId());
		this.windowTitle.setImageId(windowIcon);
	}*/

	@Override
	public void drawElement(Graphics g) {
		if(!this.isVisible()) {
			return;
		}
		
		g.drawImage(ImageManager.getImage(this.windowId), 
				(int)this.getPoint().getX(), 
				(int)this.getPoint().getY(), null);
		
		this.windowTitle.drawElement(g);
		
		Utils.drawGridSystem(g, windowBounds);
	}

	public abstract void setShell();
}
