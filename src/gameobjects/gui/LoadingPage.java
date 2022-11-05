package gameobjects.gui;

import java.awt.Graphics;
import java.awt.Point;

import enums.ImageId;
import gameobjects.button.Icon;
import gameobjects.loadingbar.LoadingBar;
import utils.Bounds;
import utils.ImageManager;
import utils.Size;
import utils.Trigonometry;

public class LoadingPage extends GenericGui {

	LoadingBar bar;
	Icon title;

	public LoadingPage(Point point, Size size) {
		super(ImageId.LOADING1_BG, point, size);
		this.setVisible(true);
	}
	
	@Override
	public void drawElement(Graphics g) {
		super.drawElement(g);

		if(!this.isVisible()) {
			return;
		}

		bar.drawElement(g);
	}

	@Override
	public void activateEvent(ImageId buttonId) {
	}

	@Override
	public void isElementClicked(Point point, GenericGui currentUi) {
		// TODO Auto-generated method stub
		
	}
	
	public int getCurrentCenterPercent() {
		return this.bar.getCurrentCenterPercent();
	}
	
	public void setIncrement(int currentCenterPercent) {
		this.bar.increment(currentCenterPercent);
	}
	
	public boolean isCompleted() {
		return this.bar.isCompleted();
	}

	@Override
	public void setShell() {
		Bounds externalBounds;
		Size newSize;

		this.windowBounds
			.addRow(80, 1)
			.addRow(20, 1);
		
		// window title: "accept"
		externalBounds = this.windowBounds.getRow(0).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(80, 80);
		title = new Icon(
				ImageId.LOADING_SETTINGS_HEADER_TXT,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize);
		this.setWindowHeader(title);

		externalBounds = this.windowBounds.getRow(1).getSquare(0).getBounds();
		newSize = this.getBounds().getScaledSize(60, 9);
		//Point newLocation = Trigonometry.centerSquareInsideanother(getBounds(), newSize);
		bar = new LoadingBar(Trigonometry.centerSquareInsideanother(externalBounds, newSize), newSize);
	}
	@Override
	public void removeComponents() {
		ImageManager.removeImage(ImageId.LOADING1_BG);
		title.removeComponents();
		bar.removeComponents();
	}
}
