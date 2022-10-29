package gameobjects.gui;

import java.awt.Graphics;
import java.awt.Point;

import commoninterfaces.Clickable;
import enums.ImageId;
import gameobjects.button.ButtonWithActive;
import gameobjects.button.Icon;
import gameobjects.matrix.WindowSquare;
import utils.Bounds;
import utils.ImageManager;
import utils.Size;
import utils.Trigonometry;

public class Pause extends GenericGui implements Clickable {

	private ButtonWithActive settings;
	private ButtonWithActive menu;
	private ButtonWithActive hangar;
	private ButtonWithActive accept;
	
	private Icon pauseTxt;
	private Icon scoreTxt, pauseTable;
	
	public Pause(Point point, Size size, float xPercent, float yPercent, float downPercent) {
		super(ImageId.PAUSE_WINDOW, point, size, xPercent, yPercent, downPercent);

		this.setShell();
	}

	@Override
	public void drawElement(Graphics g) {
		super.drawElement(g);

		/*settings.drawElement(g);
		menu.drawElement(g);
		hangar.drawElement(g);
		accept.drawElement(g);
		
		pauseTxt.drawElement(g);
		scoreTxt.drawElement(g);
		pauseTable.drawElement(g);*/
	}

	@Override
	public boolean isElementClicked(Point point) {

		/*settings.isElementClicked(point);
		menu.isElementClicked(point);
		hangar.isElementClicked(point);
		accept.isElementClicked(point);*/

		return false;
	}

	/*private ButtonWithActive settings;
	private ButtonWithActive menu;
	private ButtonWithActive hangar;
	private ButtonWithActive accept;
	
	private Icon pauseTxt;
	private Icon scoreTxt, pauseTable;*/
	@Override
	public void setShell() {
		this.sections = new WindowSquare(this.getPointInWindow(), this.getSizeInWindow());
		
		this.sections
		.addRow(50, 1)
		.addRow(50, 3);
		
		// Close
		/*Bounds externalBounds = this.sections.getRow(1).getSquare(0).getBounds();
		Size newSize = externalBounds.getScaledSize(60, 60);
		close = new ButtonWithActive(
				ImageId.WINDOW_ACCEPT_CLOSE_BTN, 
				ImageId.WINDOW_ACCEPT_CLOSE_A_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		
		// question
		externalBounds = this.sections.getRow(1).getSquare(1).getBounds();
		newSize = externalBounds.getScaledSize(60, 60);
		question = new ButtonWithActive(
				ImageId.WINDOW_ACCEPT_FAQ_BTN, 
				ImageId.WINDOW_ACCEPT_FAQ_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		
		// accept
		externalBounds = this.sections.getRow(1).getSquare(2).getBounds();
		newSize = externalBounds.getScaledSize(60, 60);
		accept = new ButtonWithActive(
				ImageId.WINDOW_ACCEPT_BTN, 
				ImageId.WINDOW_ACCEPT_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);*/
	}
}
