package gameobjects.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;
import java.util.List;

import enums.ImageId;
import gameobjects.button.ButtonWithActive;
import gameobjects.button.Icon;
import utils.Bounds;
import utils.CommonEvents;
import utils.Size;
import utils.Trigonometry;

public class Settings extends GenericGui {
	private ButtonWithActive sound;
	private ButtonWithActive music;
	private ButtonWithActive notifications;
	private ButtonWithActive ok;
	private ButtonWithActive menu;
	private ButtonWithActive close;
	
	private Icon settingsTxt;
	private Icon soundTxt;
	private Icon musicTxt;
	private Icon notificationsTxt;

	public Settings(Point point, Size size) {
		super(ImageId.WINDOW_SETTINGS_WINDOW, point, size);
		//this.setVisible(false);
		
		this.setShell();
	}
	
	@Override
	public void drawElement(Graphics g) {
		super.drawElement(g);
		
		if(!this.isVisible()) {
			return;
		}

		music.drawElement(g);
		sound.drawElement(g);
		notifications.drawElement(g);
		ok.drawElement(g);
		close.drawElement(g);
		//.drawElement(g);
	}
	
	@Override
	public void isElementClicked(Point point, GenericGui currentUi) {
		if(!this.isFocused()) {
			return;
		}
		
		// required to set the reference in every window, so we can show the previous window once we close this one
		this.setReferenceUI(currentUi);
		
		music.isElementClicked(point, this);
		sound.isElementClicked(point, this);
		notifications.isElementClicked(point, this);
		ok.isElementClicked(point, this);
		close.isElementClicked(point, this);
	}
	
	@Override
	public void activateEvent(ImageId buttonId) {
		switch (buttonId) {
			case WINDOW_SETTINGS_SOUND_BTN: 
				
				break;
			case WINDOW_SETTINGS_MUSIC_BTN: 
				
				break;
			case WINDOW_SETTINGS_NOTIFICATIONS_BTN: 
				
				break;
			case WINDOW_SETTINGS_OK_BTN: 
				//this.setVisible(false);
				//this.setFocused(false);
				
				// Logic when settings saved
				
				// reset reference ui
				//this.getReferenceUI().setFocused(true);
				CommonEvents.closeWindowOpenedOnTop(getReferenceUI(), this);
				break;
			case WINDOW_SETTINGS_CLOSE_BTN: 
				/*this.setVisible(false);
				this.setFocused(false);
				
				this.getReferenceUI().setFocused(true);*/
				CommonEvents.closeWindowOpenedOnTop(getReferenceUI(), this);
				break;
		}
		this.setReferenceUI(null);
	}

	@Override
	public void setShell() {
		Bounds externalBounds;
		Size buttonSize;

		List<Double> margin =  Arrays.asList(20d, 80d);
		this.windowBounds
		.addRow(11, 1)
		.addRowWithColumnPercent(20f, 2, margin)
		.addRowWithColumnPercent(20f, 2, margin)
		.addRowWithColumnPercent(20f, 2, margin)
		.addRow(29f, 2);
		
		// window title: "accept"
		externalBounds = this.windowBounds.getRow(0).getSquare(0).getBounds();
		buttonSize = externalBounds.getScaledSize(50, 60);
		Icon title = new Icon(
				ImageId.WINDOW_SETTINGS_HEADER_TXT,
				Trigonometry.centerSquareInsideanother(externalBounds, buttonSize), 
				buttonSize);
		this.setWindowHeader(title);
		
		// sound
		externalBounds = this.windowBounds.getRow(1).getSquare(0).getBounds();
		buttonSize = externalBounds.getScaledSizeSameWidth(70);
		sound = new ButtonWithActive(
				ImageId.WINDOW_SETTINGS_SOUND_BTN, 
				ImageId.WINDOW_SETTINGS_SOUND_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, buttonSize), 
				buttonSize
			);
		
		// music
		externalBounds = this.windowBounds.getRow(2).getSquare(0).getBounds();
		music = new ButtonWithActive(
				ImageId.WINDOW_SETTINGS_MUSIC_BTN, 
				ImageId.WINDOW_SETTINGS_MUSIC_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, buttonSize), 
				buttonSize
			);
		
		// notifications
		externalBounds = this.windowBounds.getRow(3).getSquare(0).getBounds();
		notifications = new ButtonWithActive(
				ImageId.WINDOW_SETTINGS_NOTIFICATIONS_BTN, 
				ImageId.WINDOW_SETTINGS_NOTIFICATIONS_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, buttonSize), 
				buttonSize
			);
		
		// ok
		externalBounds = this.windowBounds.getRow(4).getSquare(0).getBounds();
		ok = new ButtonWithActive(
				ImageId.WINDOW_SETTINGS_OK_BTN, 
				ImageId.WINDOW_SETTINGS_OK_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, buttonSize), 
				buttonSize
			);
		
		// ok
		externalBounds = this.windowBounds.getRow(4).getSquare(1).getBounds();
		close = new ButtonWithActive(
				ImageId.WINDOW_SETTINGS_CLOSE_BTN, 
				ImageId.WINDOW_SETTINGS_CLOSE_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, buttonSize), 
				buttonSize
			);
	}

}
