package gameobjects.gui;

import java.awt.Graphics;
import java.awt.Point;

import enums.ImageId;
import gameobjects.button.ButtonWithActive;
import gameobjects.button.Icon;
import utils.Bounds;
import utils.CommonEvents;
import utils.Size;
import utils.Trigonometry;

public class Information extends GenericGui {

	private ButtonWithActive accept;

	public Information(Point point, Size size) {
		super(ImageId.WINDOW_PURCHASE_WINDOW, point, size);

		this.setShell();
	}

	@Override
	public void drawElement(Graphics g) {
		super.drawElement(g);
		
		if(!this.isVisible()) {
			return;
		}

		accept.drawElement(g);
		
	}

	@Override
	public void isElementClicked(Point point, GenericGui currentUi) {
		if(!this.isFocused()) {
			return;
		}
		
		// required to set the reference in every window, so we can show the previous window once we close this one
		this.setReferenceUI(currentUi);

		accept.isElementClicked(point, this);
	}
	
	@Override
	public void activateEvent(ImageId buttonId) {
		
		switch (buttonId) {
		case WINDOW_INFO_OK_BTN:
			CommonEvents.closeWindowOpenedOnTop(getReferenceUI(), this);
			break;
		}
		
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
		
		// window title: "information"
		externalBounds = this.windowBounds.getRow(0).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(50, 60);
		Icon title = new Icon(
				ImageId.WINDOW_INFO_HEADER_TXT,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize);
		this.setWindowHeader(title);
	
		// accept
		externalBounds = this.windowBounds.getRow(2).getSquare(1).getBounds();
		newSize = externalBounds.getScaledSizeSameHeight(50);
		accept = new ButtonWithActive(
				ImageId.WINDOW_INFO_OK_BTN, 
				ImageId.WINDOW_INFO_OK_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize.getSize()
			);
	}

}
