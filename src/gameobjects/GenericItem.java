package gameobjects;

import java.awt.Point;

import utils.Bounds;
import utils.Constants;
import utils.Size;

public abstract class GenericItem {
	private int id = Constants.NEXT_ID.getAndIncrement();
	private Bounds bounds;
	private boolean isVisible = true;

	public GenericItem(Bounds bounds) {
		this.bounds = bounds;
	}

	public GenericItem(Point point, Size size) {
		this.bounds = new Bounds(point, size);
	}
	
	public GenericItem(Point point, int size) {
		this.bounds = new Bounds(point, new Size(size, size));
	}

	public Bounds getBounds() {
		return this.bounds;
	}
	
	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Point getPoint() {
		return this.bounds.getLocation();
	}

	public void setPoint(Point point) {
		this.bounds.setLocation(point);
	}

	public Size getSize() {
		return this.bounds.getSize();
	}

	public void setSize(Size size) {
		this.bounds.setSize(size);
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
}
