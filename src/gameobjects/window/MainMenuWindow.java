package gameobjects.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;
import java.util.List;

import gameobjects.matrix.WindowSquare;
import utils.Size;
import utils.Trigonometry;
import utils.Utils;

public class MainMenuWindow extends GenericWindow {

	WindowSquare sections;

	public MainMenuWindow(Point point, Size size) {
		super(point, size, null);
		
		// create a new Square to place te objects, this is a margin
		Size newSizeForMargin = new Size((int) (size.width * 0.95), (int) (size.height * 0.9));
		Point newMarginPoint = new Point(Trigonometry.centerSquareInsideanother(this.getBounds(), newSizeForMargin));

		sections = new WindowSquare(newMarginPoint, newSizeForMargin);
		List<Double> columnsPercent = Arrays.asList(10d, 80d, 10d);

		sections.addRowWithColumnPercent(100, 3, columnsPercent);
		sections.getRow(0).getSquare(0)
			.addRow(50, 1)
			.addRow(12.5f, 1)
			.addRow(12.5f, 1)
			.addRow(12.5f, 1)
			.addRow(12.5f, 1);
		sections.getRow(0).getSquare(1).addRow(60, 1).addRow(40, 1);
		sections.getRow(0).getSquare(2)
			.addRow(50, 1)
			.addRow(12.5f, 1)
			.addRow(12.5f, 1)
			.addRow(12.5f, 1)
			.addRow(12.5f, 1);

	}

	@Override
	public void drawElement(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(this.getPoint().x, this.getPoint().y, this.getSize().width, this.getSize().height);
		
		if(this.sections != null) {
			g.setColor(Color.WHITE);
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
