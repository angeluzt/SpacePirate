package gameobjects.gui;

import java.awt.Graphics;
import java.awt.Point;

import commoninterfaces.Clickable;
import enums.ImageId;
import gameobjects.button.ButtonWithActive;
import gameobjects.button.Icon;
import gameobjects.matrix.WindowMatrix;
import gameobjects.matrix.WindowSquare;
import utils.Bounds;
import utils.ImageManager;
import utils.Size;
import utils.Trigonometry;

public class Purchase extends GenericGui implements Clickable {

	private ButtonWithActive close;
	private ButtonWithActive question;
	private ButtonWithActive accept;
	
	private Icon amount;
	
	public Purchase(Point point, Size size, float xPercent, float yPercent, float downPercent) {
		super(ImageId.PAUSE_WINDOW, point, size, xPercent, yPercent, downPercent);

		this.setShell();
	}

	@Override
	public void drawElement(Graphics g) {
		super.drawElement(g);

		/*close.drawElement(g);
		question.drawElement(g);
		accept.drawElement(g);
		
		amount.drawElement(g);*/
	}

	@Override
	public boolean isElementClicked(Point point) {

		/*close.isElementClicked(point);
		question.isElementClicked(point);
		accept.isElementClicked(point);*/

		return false;
	}

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
