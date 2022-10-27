package utils;

import java.awt.Point;

import gameobjects.GenericItem;

public class Trigonometry {

	public static boolean isPointInsideRegion(Point point, GenericItem item) {
		
		if(point.getX() > item.getPoint().getX() 
				&& point.getX() < item.getPoint().getX() + item.getSize().getWidth()
				&& point.getY() > item.getPoint().getY() 
				&& point.getY() < item.getPoint().getY() + item.getSize().getHeight()) {
			return true;
		}
		
		return false;
	}
}
