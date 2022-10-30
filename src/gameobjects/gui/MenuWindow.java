package gameobjects.gui;

import java.awt.Point;

import commoninterfaces.Clickable;
import enums.ImageId;
import utils.Size;

public class MenuWindow extends GenericGui implements Clickable  {

	public MenuWindow(ImageId windowId, Point point, Size size) {
		super(windowId, point, size);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isElementClicked(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setShell() {
		// TODO Auto-generated method stub
		
	}

}
