package commoninterfaces;

import java.awt.Point;

import gameobjects.gui.GenericGui;

public interface Clickable {
	public abstract void isElementClicked(Point point, GenericGui currentUi);
}
