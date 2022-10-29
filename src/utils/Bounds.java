package utils;

import java.awt.Point;

public class Bounds {

	private Point location;
	private Size size;
	
	public Bounds(Point location, Size size) {
		this.location = location;
		this.size = size;
	}
	
	public Size getScaledSize(float widthPercent, float heightPercent) {
		return new Size((int)(widthPercent * size.height) / 100, (int)(heightPercent * size.height) / 100);
	}
	
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}

	public Point getLoctionClone() {
		return location.getLocation();
	}
	
	public Size getSizeClone() {
		return size.getSize();
	}
	
	public int getWidth() {
		return this.size.getWidth();
	}
	
	public int getHeight() {
		return this.size.getHeight();
	}
	
	public double getX() {
		return this.location.getX();
	}
	
	public double getY() {
		return this.location.getY();
	}
}