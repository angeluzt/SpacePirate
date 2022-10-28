package gameobjects.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;
import java.util.List;

import gameobjects.matrix.WindowMatrix;
import gameobjects.matrix.WindowRow;
import gameobjects.matrix.WindowSquare;
import utils.Size;
import utils.Trigonometry;
import utils.Utils;

public class MainMenuWindow extends GenericWindow {

	WindowMatrix sections;

	public MainMenuWindow(Point point, Size size) {
		super(point, size, null);
		
		// create a new Square to place te objects, this is a margin
		Size newSizeForMargin = new Size((int) (size.width * 0.95), (int) (size.height * 0.9));
		Point newMarginPoint = new Point(Trigonometry.centerSquareInsideanother(point, size, newSizeForMargin));

		sections = new WindowMatrix();
		List<Double> columnsPercent = Arrays.asList(10d, 80d, 10d);
		//Point p = sections.getRowByIndex(0).getSquares().get(0).getPoint();
		//Size s = sections.getRowByIndex(0).getSquares().get(0).getSize();
		sections.addRowWithColumnPercent(100, 3, newMarginPoint, newSizeForMargin, columnsPercent);
		
	}

	@Override
	public void drawElement(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect(this.getPoint().x, this.getPoint().y, this.getSize().width, this.getSize().height);
		
		if(this.sections != null) {
			g.setColor(Color.BLACK);
			Utils.drawGridSystem(g, sections);
		}
	}

	@Override
	public boolean isElementClicked(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void itemEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadWindow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeWindow() {
		// TODO Auto-generated method stub
		
	}

}
