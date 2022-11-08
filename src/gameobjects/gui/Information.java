package gameobjects.gui;

import java.awt.Graphics;
import java.awt.Point;

import enums.ImageId;
import gameobjects.button.ButtonWithActive;
import gameobjects.button.Icon;
import gameobjects.textarea.InfoTextArea;
import utils.Bounds;
import utils.CommonEvents;
import utils.ImageManager;
import utils.Size;
import utils.Trigonometry;

public class Information extends GenericGui {

	private ButtonWithActive accept;
	private Icon title;
	Size newSize = new Size(600, 300);
	Point newPoint = new Point(0, 50);
	private InfoTextArea infoText;

	public Information(Point point, Size size) {
		super(ImageId.WINDOW_INFO_WINDOW, point, size);

		this.pageLoaded = true;
	}
	
	public void setText(String currentInfoTXT) {
		infoText.splitContent(currentInfoTXT);
	}

	@Override
	public void drawElement(Graphics g) {
		super.drawElement(g);
		
		if(!this.isVisible()) {
			return;
		}

		accept.drawElement(g);
		this.infoText.drawElement(g);
		
	}

	@Override
	public void isElementClicked(Point point, GenericGui currentUi) {
		if(!this.isFocused()) {
			return;
		}
		
		// required to set the reference in every window, so we can show the previous window once we close this one
		this.setReferenceUI(currentUi);

		accept.isElementClicked(point, this);
		infoText.isElementClicked(point, null);
	}
	
	@Override
	public void activateEvent(ImageId buttonId) {
		
		switch (buttonId) {
		case WINDOW_INFO_OK_BTN:
			CommonEvents.closeWindowOpenedOnTop(getReferenceUI(), this);
			break;
		}
		this.setReferenceUI(null);
	}

	@Override
	public void setShell() {
		Bounds externalBounds;
		Size newSize;

		//this.sections = new WindowSquare(this.getPointInWindow(), this.getSizeInWindow());
		
		this.windowBounds
		.addRow(19, 1)
		.addRow(50f, 1)
		.addRow(31f, 3);
		
		// window title: "information"
		externalBounds = this.windowBounds.getRow(0).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(50, 60);
		title = new Icon(
				ImageId.WINDOW_INFO_HEADER_TXT,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize);
		this.setWindowHeader(title);
		
		String infoTxt = "1234567890";
		externalBounds = this.windowBounds.getRow(1).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(94, 94);

		this.infoText = new InfoTextArea(new Bounds(Trigonometry.centerSquareInsideanother(externalBounds, newSize), newSize));
	
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
	
	@Override
	public void removeComponents() {
		ImageManager.removeImage(ImageId.WINDOW_INFO_WINDOW);
		title.removeComponents();
		accept.removeComponents();
	}

}
