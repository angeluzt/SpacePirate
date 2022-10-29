package gameobjects.matrix;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import utils.Bounds;
import utils.Size;

public class WindowSquare {

	private Bounds bounds;

	private ArrayList<WindowRow> sections = new ArrayList<>();
	
	public WindowSquare(Point point, Size size) {

		this.bounds = new Bounds(point,size);
	}
	
	public WindowSquare(int itemWidth, int itemHeight, int x, int y) {
		this.bounds = new Bounds(new Point(x, y),  new Size(itemWidth, itemHeight));
	}
	
	public WindowSquare addRow(float windowPercent, float elements) {
		int y = 0;

		// float windowPercent, int elements, Size windowSize, Point location
		if(this.sections.size() > 0) {
			WindowRow lastRow = this.sections.get(this.sections.size() - 1);
			y = lastRow.getCurrentY();
			this.sections.add(new WindowRow(windowPercent, elements, bounds.getSize(), new Point((int) bounds.getX(), y)));
		} else {
			this.sections.add(new WindowRow(windowPercent, elements, bounds.getSize(), bounds.getLocation()));
		}
		
		return this;
	}
	
	public WindowSquare addRowWithColumnPercent(float windowPercent, float elements, List<Double> columnsPercent) {
		int y = 0;

		if(this.sections.size() > 0) {
			WindowRow lastRow = this.sections.get(this.sections.size() - 1);
			y = lastRow.getCurrentY();
			this.sections.add(new WindowRow(windowPercent, elements, bounds.getSize(), new Point((int)bounds.getX(), y), columnsPercent));
		} else {
			this.sections.add(new WindowRow(windowPercent, elements, bounds.getSize(), bounds.getLocation(), columnsPercent));
		}
		
		return this;
	}
	
	public ArrayList<WindowRow> getRows() {
		return this.sections;
	}
	
	public WindowRow getRow(int index) {
		return this.sections.get(index);
	}

	public Point getPoint() {
		return bounds.getLocation();
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

	public Bounds getBounds() {
		return bounds;
	}

	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}
	
}
