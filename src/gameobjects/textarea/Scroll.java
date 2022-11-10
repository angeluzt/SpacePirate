package gameobjects.textarea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import commoninterfaces.DragDrop;
import commoninterfaces.Drawable;
import enums.Sounds;
import gameobjects.GenericItem;
import gameobjects.gui.GenericGui;
import utils.Bounds;
import utils.Constants;
import utils.Size;
import utils.Sound;

public class Scroll extends GenericItem implements DragDrop, Drawable {
	private int scrollInternalHight;
	Polygon upTriangle = new Polygon();
	Polygon downTriangle = new Polygon();

	Point p1, p2;
	Size s1, s2;

	public Scroll(Bounds bounds) {
		super(bounds);
		
		p1 = new Point(this.getPoint().x, this.getPoint().y);
		p2 = new Point(this.getPoint().x, this.getPoint().y + this.getSize().height - this.getSize().width);
		
		s1 = new Size(this.getSize().width, this.getSize().width);
		s2 = new Size(this.getSize().width, this.getSize().width);

		upTriangle.addPoint(p1.x, p1.y + s1.width);
		upTriangle.addPoint(p1.x + s1.width, p1.y + s1.width);
		upTriangle.addPoint(p1.x + s1.width / 2, p1.y);
		
		downTriangle.addPoint(p2.x, p2.y);
		downTriangle.addPoint(p2.x + s2.width, p2.y);
		downTriangle.addPoint(p2.x + s2.width / 2, p2.y + s2.width);
	}

	@Override
	public void removeComponents() {
		
	}

	@Override
	public void dragElement(Point point, GenericGui currentUi) {
		
	}

	public int getScrollInternalHight() {
		return scrollInternalHight;
	}

	public void setScrollInternalHight(int scrollInternalHight) {
		this.scrollInternalHight = scrollInternalHight;
	}

	@Override
	public void drawElement(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(this.getPoint().x, this.getPoint().y, this.getSize().width, this.getSize().height);
		
		g.setColor(Color.GRAY);
		g.fillRect(p1.x, p1.y, s1.width, s1.width);
		g.fillRect(p2.x, p2.y, s2.width, s2.width);
		
		g.setColor(Color.RED);
		g.fillPolygon(upTriangle);
		g.fillPolygon(downTriangle);
	}
	
	public boolean upClicked(Point point) {
		if(upTriangle.contains(point)) {
			System.out.println("Up clicked");
			new Sound().setFile(Sounds.BUTTON_CLICK, Constants.SOUND_EFFECTS_LEVEL).play();
			return true;
		}
		return false;
	}
	
	public boolean downClicked(Point point) {
		if(downTriangle.contains(point)) {
			System.out.println("Down clicked");
			new Sound().setFile(Sounds.BUTTON_CLICK, Constants.SOUND_EFFECTS_LEVEL).play();
			return true;
		}
		return false;
	}
}
