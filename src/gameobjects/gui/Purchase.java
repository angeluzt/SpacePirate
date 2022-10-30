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

public class Purchase extends GenericGui implements Clickable {

	private ButtonWithActive close;
	private ButtonWithActive question;
	private ButtonWithActive accept;
	
	private Icon amount;
	
	public Purchase(Point point, Size size) {
		super(ImageId.WINDOW_PURCHASE_WINDOW, point, size);

		this.setShell();
	}

	@Override
	public void drawElement(Graphics g) {
		super.drawElement(g);
		
		if(!this.isVisible()) {
			return;
		}
		
		close.drawElement(g);
		question.drawElement(g);
		accept.drawElement(g);
		
		//this.getWindowHeader().drawElement(g);

		//amount.drawElement(g);
	}

	@Override
	public boolean isElementClicked(Point point) {

		close.isElementClicked(point);
		question.isElementClicked(point);
		accept.isElementClicked(point);

		return false;
	}

	@Override
	public void setShell() {
		Bounds externalBounds;
		Size newSize;

		//this.sections = new WindowSquare(this.getPointInWindow(), this.getSizeInWindow());
		
		this.windowBounds
		.addRow(19, 1)
		.addRow(40.5f, 1)
		.addRow(40.5f, 3);
		
		// window title: "purchase"
		externalBounds = this.windowBounds.getRow(0).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(50, 60);
		Icon title = new Icon(
				ImageId.WINDOW_PURCHASE_HEADER_TXT,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize);
		this.setWindowHeader(title);
		
		// close
		externalBounds = this.windowBounds.getRow(2).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(60, 60);
		close = new ButtonWithActive(
				ImageId.WINDOW_PURCHASE_CLOSE_BTN, 
				ImageId.WINDOW_PURCHASE_CLOSE_A_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
	
		// question
		externalBounds = this.windowBounds.getRow(2).getSquare(1).getBounds();
		newSize = externalBounds.getScaledSize(60, 60);
		question = new ButtonWithActive(
				ImageId.WINDOW_PURCHASE_FAQ_BTN, 
				ImageId.WINDOW_PURCHASE_FAQ_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize.getSize()
			);
	
		// accept
		externalBounds = this.windowBounds.getRow(2).getSquare(2).getBounds();
		newSize = externalBounds.getScaledSize(60, 60);
		accept = new ButtonWithActive(
				ImageId.WINDOW_PURCHASE_OK_BTN, 
				ImageId.WINDOW_PURCHASE_OK_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize.getSize()
			);
	}

}
