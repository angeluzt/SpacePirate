package utils;

import java.awt.Point;

import gameobjects.GenericItem;

public class Trigonometry {

	public static boolean isPointInsideRegion(Point point, GenericItem item) {
		
		if(point.getX() > item.getPoint().getX() 
				&& point.getX() < item.getPoint().getX() + item.getSize().width
				&& point.getY() > item.getPoint().getY() 
				&& point.getY() < item.getPoint().getY() + item.getSize().height) {
			return true;
		}
		
		return false;
	}
	
	public static Point getCentralPointOfSquare(Point point, Size size) {

		return new Point((int)(point.x + size.width / 2) , (int)(point.y + size.height / 2));
	}
	
	public static Point centerSquareInsideanother(Bounds bounds, Size internalSquareSize) {
		Point externalMiddlePoint = Trigonometry.getCentralPointOfSquare(bounds.getLocation(), bounds.getSize());

		return new Point(externalMiddlePoint.x - internalSquareSize.width / 2, externalMiddlePoint.y - internalSquareSize.height / 2);
	}

	public static Point topCenterSquareInsideanother(Bounds bounds, Size internalSquareSize) {
		Point externalMiddlePoint = Trigonometry.getCentralPointOfSquare(bounds.getLocation(), bounds.getSize());

		return new Point(externalMiddlePoint.x - internalSquareSize.width / 2, (int)bounds.getY());
	}
	
	public static Point downCenterSquareInsideanother(Bounds bounds, Size internalSquareSize) {
		Point externalMiddlePoint = Trigonometry.getCentralPointOfSquare(bounds.getLocation(), bounds.getSize());

		return new Point(externalMiddlePoint.x - internalSquareSize.width / 2, (int)(bounds.getY() + bounds.getHeight()) - internalSquareSize.height);
	}
	
	public static Point rightCenterSquareInsideanother(Bounds bounds, Size internalSquareSize) {
		Point externalMiddlePoint = Trigonometry.getCentralPointOfSquare(bounds.getLocation(), bounds.getSize());

		return new Point((int)(bounds.getX() + bounds.getWidth() - internalSquareSize.width), externalMiddlePoint.y - internalSquareSize.height / 2);
	}

	public static Point leftCenterSquareInsideanother(Bounds bounds, Size internalSquareSize) {
		Point externalMiddlePoint = Trigonometry.getCentralPointOfSquare(bounds.getLocation(), bounds.getSize());

		return new Point((int)bounds.getX(), externalMiddlePoint.y - internalSquareSize.height / 2);
	}
	
	public void scaleSize() {
		
	}
}
