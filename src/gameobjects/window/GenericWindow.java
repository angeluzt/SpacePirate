package gameobjects.window;

import java.awt.Point;

import commoninterfaces.Clickable;
import commoninterfaces.Drawable;
import commoninterfaces.Event;
import gameobjects.GenericItem;
import gameobjects.matrix.WindowSquare;
import utils.Size;

public abstract class GenericWindow extends GenericItem implements Drawable, Clickable, Event {

	public GenericWindow(Point point, Size size, WindowSquare internalSquare) {
		super(point, size);
	}

	public abstract void loadWindow();
	public abstract void removeWindow();
}
