package gameobjects.gui;

import java.awt.Graphics;
import java.awt.Point;

import commoninterfaces.Clickable;
import commoninterfaces.Drawable;
import enums.ImageId;
import enums.TriggerEvent;
import gameobjects.GenericItem;
import gameobjects.button.Icon;
import gameobjects.matrix.WindowSquare;
import utils.ImageManager;
import utils.Size;
import utils.Utils;

public abstract class GenericGui extends GenericItem implements Drawable, TriggerEvent, Clickable {

	private ImageId windowId;
	private Icon windowTitle;
	private boolean focused;
	private boolean executeEvents;
	protected boolean pageLoaded;

	protected WindowSquare windowBounds;
	private GenericGui referenceUI;

	public GenericGui(ImageId windowId, Point point, Size size) {
		super(point, size);
		
		this.windowId = windowId;
		this.focused = false;
		this.executeEvents = false;
		this.pageLoaded = false;
		
		this.windowBounds = new WindowSquare(point, size);
		
		resizeImageIfExists();
		// the default behavior is when not visible
		this.setVisible(false);
		this.setShell();
		
		this.pageLoaded = true;
	}
	
	public void resizeImageIfExists() {
		if(ImageManager.containsImage(windowId)) {
			ImageManager.removeImage(windowId);
		}
		ImageManager.addImage(windowId, this.getSize());
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

	public boolean isFocused() {
		return focused;
	}

	public void setFocused(boolean focused) {
		this.focused = focused;
	}

	public boolean isPageLoaded() {
		return pageLoaded;
	}

	public boolean isExecuteEvents() {
		return executeEvents;
	}

	public void setExecuteEvents(boolean executeEvents) {
		this.executeEvents = executeEvents;
	}

	public GenericGui getReferenceUI() {
		return referenceUI;
	}

	public void setReferenceUI(GenericGui referenceUI) {
		this.referenceUI = referenceUI;
	}

	@Override
	public void drawElement(Graphics g) {
		if(!this.isVisible()) {
			return;
		}
		
		g.drawImage(ImageManager.getImage(this.windowId), 
				(int)this.getPoint().getX(), 
				(int)this.getPoint().getY(), null);
		
		if(this.windowTitle != null) {
			this.windowTitle.drawElement(g);
		}
		
		Utils.drawGridSystem(g, windowBounds);
	}

	public abstract void setShell();
	public abstract void removeComponents();
}
