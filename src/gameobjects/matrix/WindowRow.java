package gameobjects.matrix;

import java.awt.Point;
import java.util.ArrayList;

import utils.Size;

public class WindowRow {

	private ArrayList<WindowSquare> squares = new ArrayList<>();
	
	public WindowRow(float windowPercent, int elements, Size windowSize, Point location) {
		if(elements == 0) {
			throw new ArithmeticException("[WindowRow][constructor] elements cannot be zero");
		}

		float itemWidth = (windowSize.width / elements);
		float itemHeight = (windowPercent * windowSize.height) / 100;

		for (int i = 0; i < elements; i++) {
			WindowSquare section = 
					new WindowSquare(
							(int) itemWidth, (int) itemHeight,
							(int) (location.getX() + (i * itemWidth)), (int)location.getY()
					);
			this.squares.add(section);
		}
	}
	
	public WindowSquare getSquare(int index) {
		return this.squares.get(index);
	}
	
	public ArrayList<WindowSquare> getSquares() {
		return this.squares;
	}

	public int getCurrentY() {
		WindowSquare current = this.squares.get(0);
		
		if(current != null) {
			return (int)current.getPoint().getY() + (int)current.getSize().height;
		} else {
			return 0;
		}
	}
}
