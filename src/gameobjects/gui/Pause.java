package gameobjects.gui;

import java.awt.Graphics;
import java.awt.Point;

import enums.ImageId;
import gameobjects.button.ButtonWithActive;
import gameobjects.button.Icon;
import utils.Bounds;
import utils.ImageManager;
import utils.Size;
import utils.Trigonometry;

public class Pause extends GenericGui {

	private ButtonWithActive settings;
	private ButtonWithActive menu;
	private ButtonWithActive hangar;
	private ButtonWithActive accept;
	private ButtonWithActive map;
	
	private Icon scoreTxt, title;

	public Pause(Point point, Size size) {
		super(ImageId.WINDOW_PAUSE_PAUSE_WINDOW, point, size);

		//this.setShell();
		this.pageLoaded = true;
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
	public void isElementClicked(Point point, GenericGui currentUi) {
		if(!this.isFocused()) {
			return;
		}
	
		settings.isElementClicked(point, this);
		menu.isElementClicked(point, this);
		hangar.isElementClicked(point, this);
		accept.isElementClicked(point, this);
		map.isElementClicked(point, this);
	}

	@Override
	public void setShell() {
		Bounds externalBounds;
		Size newSize;
		
		this.windowBounds
		.addRow(14, 1)
		.addRow(28.5f, 2)
		.addRow(28.5f, 1)
		.addRow(28.5f, 4);
		
		// window title: "pause"
		externalBounds = this.windowBounds.getRow(0).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(50, 60);
		title = new Icon(
				ImageId.WINDOW_PAUSE_HEADER_TXT,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize);
		this.setWindowHeader(title);

		// score text
		externalBounds = this.windowBounds.getRow(1).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(40f, 30f);
		scoreTxt = new Icon(
				ImageId.WINDOW_PAUSE_SCORE_TXT,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);

		// map
		externalBounds = this.windowBounds.getRow(2).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(40f, 50f);
		map = new ButtonWithActive(
				ImageId.WINDOW_PAUSE_MAP_BTN, 
				ImageId.WINDOW_PAUSE_MAP_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		
		// close
		externalBounds = this.windowBounds.getRow(3).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(60, 60);
		settings = new ButtonWithActive(
				ImageId.WINDOW_PAUSE_SETINGS_BTN, 
				ImageId.WINDOW_PAUSE_SETINGS_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		
		// menu
		externalBounds = this.windowBounds.getRow(3).getSquare(1).getBounds();
		newSize = externalBounds.getScaledSize(60, 60);
		menu = new ButtonWithActive(
				ImageId.WINDOW_PAUSE_MENU_BTN, 
				ImageId.WINDOW_PAUSE_MENU_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		
		// hangar
		externalBounds = this.windowBounds.getRow(3).getSquare(2).getBounds();
		newSize = externalBounds.getScaledSize(60, 60);
		hangar = new ButtonWithActive(
				ImageId.WINDOW_PAUSE_HANGAR_BTN, 
				ImageId.WINDOW_PAUSE_HANGAR_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		
		// accept
		externalBounds = this.windowBounds.getRow(3).getSquare(3).getBounds();
		newSize = externalBounds.getScaledSize(60, 60);
		accept = new ButtonWithActive(
				ImageId.WINDOW_PAUSE_PAUSE_BTN, 
				ImageId.WINDOW_PAUSE_PAUSE_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
	}
	
	@Override
	public void removeComponents() {
		ImageManager.removeImage(ImageId.WINDOW_PAUSE_PAUSE_WINDOW);
		ImageManager.removeImage(ImageId.WINDOW_PAUSE_HEADER_TXT);
		title.removeComponents();
		scoreTxt.removeComponents();
		map.removeComponents();
		settings.removeComponents();
		menu.removeComponents();
		hangar.removeComponents();
		accept.removeComponents();
	}

	@Override
	public void activateEvent(ImageId buttonId) {
		// TODO Auto-generated method stub
		
	}
}
