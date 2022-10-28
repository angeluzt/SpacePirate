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
	
	public static Point centerSquareInsideanother(Point externalPoint, Size externalSize, Size internalSquareSize) {
		Point externalMiddlePoint = Trigonometry.getCentralPointOfSquare(externalPoint, externalSize);

		return new Point(externalMiddlePoint.x - internalSquareSize.width / 2, externalMiddlePoint.y - internalSquareSize.height / 2);
	}

	public static Point topCenterSquareInsideanother(Point externalPoint, Size externalSize, Size internalSquareSize) {
		Point externalMiddlePoint = Trigonometry.getCentralPointOfSquare(externalPoint, externalSize);

		return new Point(externalMiddlePoint.x - internalSquareSize.width / 2, externalPoint.y);
	}
	
	public static Point downCenterSquareInsideanother(Point externalPoint, Size externalSize, Size internalSquareSize) {
		Point externalMiddlePoint = Trigonometry.getCentralPointOfSquare(externalPoint, externalSize);

		return new Point(externalMiddlePoint.x - internalSquareSize.width / 2, (externalPoint.y + externalSize.height) - internalSquareSize.height);
	}
	
	public static Point rightCenterSquareInsideanother(Point externalPoint, Size externalSize, Size internalSquareSize) {
		Point externalMiddlePoint = Trigonometry.getCentralPointOfSquare(externalPoint, externalSize);

		return new Point((externalPoint.x + externalSize.getWidth() - internalSquareSize.width), externalMiddlePoint.y - internalSquareSize.height / 2);
	}

	public static Point leftCenterSquareInsideanother(Point externalPoint, Size externalSize, Size internalSquareSize) {
		Point externalMiddlePoint = Trigonometry.getCentralPointOfSquare(externalPoint, externalSize);

		return new Point(externalPoint.x, externalMiddlePoint.y - internalSquareSize.height / 2);
	}
}
