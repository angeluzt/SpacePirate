package gameobjects;

import java.awt.Point;

import utils.Constants;
import utils.Size;

public abstract class GenericItem {
	private int id = Constants.NEXT_ID.getAndIncrement();
	private Point point;
	private Size size;

	public GenericItem(Point point, Size size) {
		this.point = point;
		this.size = size;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
}
