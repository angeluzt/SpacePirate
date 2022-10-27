package gameobjects.gui;

import java.awt.Point;

import utils.Size;

public class WindowSquare {
	private Point point;
	private Size size;

	private WindowMatrix internalMatrix;
	
	public WindowSquare(Point point, Size size) {
		this.size = size;
		this.point = point;
	}
	
	public WindowSquare(int itemWidth, int itemHeight, int x, int y) {
		this.size = new Size((int)itemWidth, (int)itemHeight);
		this.point = new Point(x, y);
	}
	
	public void addInternalMatrix(WindowMatrix matrix) {
		this.internalMatrix = matrix;
	}
	
	public WindowMatrix getMatrix() {
		return internalMatrix;
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
