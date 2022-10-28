package gameobjects.matrix;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import utils.Size;

public class WindowMatrix {

	private ArrayList<WindowRow> sections = new ArrayList<>();
	
	public WindowMatrix() {
		
	}
	
	public void addRow(float windowPercent, int elements, Point location, Size windowSize) {
		int y = 0;

		if(this.sections.size() > 0) {
			WindowRow lastRow = this.sections.get(this.sections.size() - 1);
			y = lastRow.getCurrentY();
			this.sections.add(new WindowRow(windowPercent, elements, windowSize, new Point((int)location.getX(), y)));
		} else {
			this.sections.add(new WindowRow(windowPercent, elements, windowSize, location));
		}
	}
	
	public void addRowWithColumnPercent(float windowPercent, int elements, Point location, Size windowSize, List<Double> columnsPercent) {
		int y = 0;

		if(this.sections.size() > 0) {
			WindowRow lastRow = this.sections.get(this.sections.size() - 1);
			y = lastRow.getCurrentY();
			this.sections.add(new WindowRow(windowPercent, elements, windowSize, new Point((int)location.getX(), y), columnsPercent));
		} else {
			this.sections.add(new WindowRow(windowPercent, elements, windowSize, location, columnsPercent));
		}
	}
	
	public WindowRow getRowByIndex(int index) {
		return this.sections.get(index);
	}
	
	public ArrayList<WindowRow> getMatrix() {
		return this.sections;
	}
}
