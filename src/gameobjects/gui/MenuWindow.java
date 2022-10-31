package gameobjects.gui;

import java.awt.Point;

import enums.ImageId;
import utils.Size;

public class MenuWindow extends GenericGui  {

	public MenuWindow(ImageId windowId, Point point, Size size) {
		super(windowId, point, size);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void isElementClicked(Point point, GenericGui currentUi) {
		if(!this.isFocused()) {
			return;
		}
	}

	@Override
	public void setShell() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activateEvent(ImageId buttonId) {
		// TODO Auto-generated method stub
		
	}

}
