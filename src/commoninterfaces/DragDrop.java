package commoninterfaces;

import java.awt.Point;

import gameobjects.gui.GenericGui;

public interface DragDrop {

	public void dragElement(Point point, GenericGui currentUi);
}
