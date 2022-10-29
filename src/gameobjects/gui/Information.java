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

public class Information extends GenericGui implements Clickable {

	private ButtonWithActive close;
	private ButtonWithActive question;
	private ButtonWithActive accept;
	
	private Icon cristal;
	
	public Information(Point point, Size size) {
		super(ImageId.WINDOW_INFO_WINDOW, point, size);

		this.setShell();
	}

	@Override
	public void drawElement(Graphics g) {
		if(!this.isVisible()) {
			return;
		}

		super.drawElement(g);

		close.drawElement(g);
		question.drawElement(g);
		accept.drawElement(g);
		cristal.drawElement(g);
		
		//this.getWindowHeader().drawElement(g);
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
		
		this.sections
		.addRow(19, 1)
		.addRow(40.5f, 2)
		.addRow(40.5f, 3);
		
		// window title: "information"
		externalBounds = this.sections.getRow(0).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(50, 60);
		Icon title = new Icon(
				ImageId.WINDOW_INFO_HEADER_TXT,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize);
		this.setWindowHeader(title);

		// cristal
		externalBounds = this.sections.getRow(1).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(50,50);
		cristal = new Icon(
				ImageId.WINDOW_INFO_CRISTAL_ICON,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);

		// close
		externalBounds = this.sections.getRow(2).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(60, 60);
		close = new ButtonWithActive(
				ImageId.WINDOW_INFO_CLOSE_BTN, 
				ImageId.WINDOW_INFO_CLOSE_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
	
		// question
		externalBounds = this.sections.getRow(2).getSquare(1).getBounds();
		newSize = externalBounds.getScaledSize(60, 60);
		question = new ButtonWithActive(
				ImageId.WINDOW_INFO_FAQ_BTN, 
				ImageId.WINDOW_INFO_FAQ_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize.getSize()
			);
	
		// accept
		externalBounds = this.sections.getRow(2).getSquare(2).getBounds();
		newSize = externalBounds.getScaledSize(60, 60);
		accept = new ButtonWithActive(
				ImageId.WINDOW_INFO_OK_BTN, 
				ImageId.WINDOW_INFO_OK_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize.getSize()
			);
	}

}
