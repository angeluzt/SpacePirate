package gameobjects.gui;

import java.awt.Graphics;
import java.awt.Point;

import commoninterfaces.Clickable;
import enums.ImageId;
import gameobjects.button.ButtonWithActive;
import gameobjects.button.Icon;
import utils.Bounds;
import utils.Size;
import utils.Trigonometry;

public class Pause extends GenericGui implements Clickable {

	private ButtonWithActive settings;
	private ButtonWithActive menu;
	private ButtonWithActive hangar;
	private ButtonWithActive accept;
	private ButtonWithActive map;
	
	private Icon scoreTxt;
	
	public Pause(Point point, Size size) {
		super(ImageId.WINDOW_PAUSE_PAUSE_WINDOW, point, size);

		this.setShell();
	}

	@Override
	public void drawElement(Graphics g) {
		super.drawElement(g);
		
		if(!this.isVisible()) {
			return;
		}

		settings.drawElement(g);
		menu.drawElement(g);
		hangar.drawElement(g);
		accept.drawElement(g);
		map.drawElement(g);
		scoreTxt.drawElement(g);
		
		//this.getWindowHeader().drawElement(g);
	}

	@Override
	public boolean isElementClicked(Point point) {

		settings.isElementClicked(point);
		menu.isElementClicked(point);
		hangar.isElementClicked(point);
		accept.isElementClicked(point);
		map.isElementClicked(point);

		return false;
	}

	@Override
	public void setShell() {
		Bounds externalBounds;
		Size newSize;
		
		this.sections
		.addRow(14, 1)
		.addRow(28.5f, 2)
		.addRow(28.5f, 1)
		.addRow(28.5f, 4);
		
		// window title: "pause"
		externalBounds = this.sections.getRow(0).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(50, 60);
		Icon title = new Icon(
				ImageId.WINDOW_PAUSE_HEADER_TXT,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize);
		this.setWindowHeader(title);

		// score text
		externalBounds = this.sections.getRow(1).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(40f, 30f);
		scoreTxt = new Icon(
				ImageId.WINDOW_PAUSE_SCORE_TXT,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);

		// map
		externalBounds = this.sections.getRow(2).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(40f, 50f);
		map = new ButtonWithActive(
				ImageId.WINDOW_PAUSE_MAP_BTN, 
				ImageId.WINDOW_PAUSE_MAP_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		
		// close
		externalBounds = this.sections.getRow(3).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(60, 60);
		settings = new ButtonWithActive(
				ImageId.WINDOW_PAUSE_SETINGS_BTN, 
				ImageId.WINDOW_PAUSE_SETINGS_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		
		// menu
		externalBounds = this.sections.getRow(3).getSquare(1).getBounds();
		newSize = externalBounds.getScaledSize(60, 60);
		menu = new ButtonWithActive(
				ImageId.WINDOW_PAUSE_MENU_BTN, 
				ImageId.WINDOW_PAUSE_MENU_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		
		// hangar
		externalBounds = this.sections.getRow(3).getSquare(2).getBounds();
		newSize = externalBounds.getScaledSize(60, 60);
		hangar = new ButtonWithActive(
				ImageId.WINDOW_PAUSE_HANGAR_BTN, 
				ImageId.WINDOW_PAUSE_HANGAR_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		
		// accept
		externalBounds = this.sections.getRow(3).getSquare(3).getBounds();
		newSize = externalBounds.getScaledSize(60, 60);
		accept = new ButtonWithActive(
				ImageId.WINDOW_PAUSE_PAUSE_BTN, 
				ImageId.WINDOW_PAUSE_PAUSE_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
	}
}
