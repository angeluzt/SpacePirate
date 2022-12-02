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
	
	public static double getAngleBetweenTwoPoints(double x1, double y1, double x2, double y2)
	{
		double deltaY = Math.abs(y2 - y1);
		double deltaX = Math.abs(x2 - x1);

		double angleInDegrees = Math.atan2(deltaY, deltaX) * 180 / Math.PI;
		System.out.println("Current Angle: " + angleInDegrees);
		if(y2 > y1) // Second point is lower than first, angle goes down (180-360)
		{
		  if(x2 < x1)//Second point is to the left of first (180-270)
		    angleInDegrees = 90 - angleInDegrees; // top_right
		  else //(270-360)
		    angleInDegrees += 270; // top_left
		}
		else if (x2 < x1) //Second point is top left of first (90-180)
			angleInDegrees += 90; // down_right
		else
			angleInDegrees = 270 - angleInDegrees; // down_left
		
		System.out.println("New Angle: " + angleInDegrees);
		return angleInDegrees;
	}
	
	public static double getFixedLaserAngleBetweenTwoPoints(double x1, double y1, double x2, double y2)
	{
	    /*double angle = Math.toDegrees(Math.atan2(x2 - x1, y2 - y1));
	    // Keep angle between 0 and 360
	    angle = angle + Math.ceil( -angle / 360 ) * 360;*/

		//double deltaY = y1 - y1;
		//double deltaX = x2 - x1;
		//return (float) (Math.atan2(deltaY, deltaX));
		
		double deltaY = Math.abs(y2 - y1);
		double deltaX = Math.abs(x2 - x1);

		double angleInDegrees = Math.atan2(deltaY, deltaX) * 180 / Math.PI;
		System.out.println("Current Angle: " + angleInDegrees);
		if(y2 > y1) // Second point is lower than first, angle goes down (180-360)
		{
		  if(x2 < x1)//Second point is to the left of first (180-270)
		    angleInDegrees = 90 - angleInDegrees - 90; // top_right
		  else //(270-360)
		    angleInDegrees += 270 -90; // top_left
		}
		else if (x2 < x1) //Second point is top left of first (90-180)
			angleInDegrees = angleInDegrees; // down_right
		else
			angleInDegrees = 270 - angleInDegrees - 90; // down_left
		
		System.out.println("New Angle: " + angleInDegrees);
		return angleInDegrees;
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
