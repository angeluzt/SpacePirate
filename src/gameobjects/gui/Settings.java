package gameobjects.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;
import java.util.List;

import commoninterfaces.DragDrop;
import enums.ImageId;
import gameobjects.button.ButtonWithActive;
import gameobjects.button.Icon;
import gameobjects.dragbar.DragBar;
import utils.Bounds;
import utils.CommonEvents;
import utils.ImageManager;
import utils.Size;
import utils.Trigonometry;

public class Settings extends GenericGui implements DragDrop {

	private ButtonWithActive sound;
	private ButtonWithActive music;
	private ButtonWithActive notifications;
	private ButtonWithActive moreSettings;
	private ButtonWithActive ok;
	private ButtonWithActive menu;

	private Icon settingsTxt;
	private Icon soundTxt;
	private Icon musicTxt;
	private Icon notificationsTxt;
	private Icon title;

	private DragBar musicVolume;
	private DragBar effectsVolume;

	public Settings(Point point, Size size) {
		super(ImageId.WINDOW_SETTINGS_WINDOW, point, size);

		this.pageLoaded = true;
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
		moreSettings.drawElement(g);
		effectsVolume.drawElement(g);
		musicVolume.drawElement(g);
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
		moreSettings.isElementClicked(point, this);
	}
	
	@Override
	public void dragElement(Point point, GenericGui currentUi) {
		if(!this.isFocused()) {
			return;
		}
		effectsVolume.dragElement(point, currentUi);
		musicVolume.dragElement(point, currentUi);
	}
	
	public float getMusicSound() {
		return this.musicVolume.getActualValue();
	}
	
	public float getEffectsSound() {
		return this.effectsVolume.getActualValue();
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

				CommonEvents.closeWindowOpenedOnTop(getReferenceUI(), this);
				break;
			case WINDOW_SETTINGS_CLOSE_BTN: 
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
		.addRowWithColumnPercent(15f, 2, margin)
		.addRowWithColumnPercent(15f, 2, margin)
		.addRowWithColumnPercent(15f, 2, margin)
		.addRowWithColumnPercent(15f, 2, margin)
		.addRow(29f, 1);
		
		// window title: "accept"
		externalBounds = this.windowBounds.getRow(0).getSquare(0).getBounds();
		buttonSize = externalBounds.getScaledSize(50, 60);
		title = new Icon(
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
		
		Point volumeLocation = this.windowBounds.getRow(1).getSquare(1).getPoint();
		Size volumeSize = this.windowBounds.getRow(1).getSquare(1).getBounds().getScaledSize(97, 60);
		effectsVolume = new DragBar(ImageId.WINDOW_SETTINGS_DRAG_BAR_EFECTS_SOUND,
				Trigonometry.leftCenterSquareInsideanother(this.windowBounds.getRow(1).getSquare(1).getBounds(), volumeSize), 
				volumeSize);
		
		// music
		externalBounds = this.windowBounds.getRow(2).getSquare(0).getBounds();
		music = new ButtonWithActive(
				ImageId.WINDOW_SETTINGS_MUSIC_BTN, 
				ImageId.WINDOW_SETTINGS_MUSIC_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, buttonSize), 
				buttonSize
			);
		
		volumeLocation = this.windowBounds.getRow(2).getSquare(1).getPoint();
		//Size volumeSize = this.windowBounds.getRow(1).getSquare(1).getBounds().getScaledSize(98, 100);
		musicVolume = new DragBar(ImageId.WINDOW_SETTINGS_DRAG_BAR_MUSIC_SOUND,
				Trigonometry.leftCenterSquareInsideanother(this.windowBounds.getRow(2).getSquare(1).getBounds(), volumeSize),
				volumeSize);

		// notifications
		externalBounds = this.windowBounds.getRow(3).getSquare(0).getBounds();
		notifications = new ButtonWithActive(
				ImageId.WINDOW_SETTINGS_NOTIFICATIONS_BTN, 
				ImageId.WINDOW_SETTINGS_NOTIFICATIONS_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, buttonSize), 
				buttonSize
			);
		// show more settings
		externalBounds = this.windowBounds.getRow(4).getSquare(0).getBounds();
		moreSettings = new ButtonWithActive(
				ImageId.WINDOW_SETTINGS_MORESETTINGS_BTN, 
				ImageId.WINDOW_SETTINGS_MORESETTINGS_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, buttonSize), 
				buttonSize
			);
		
		// ok
		externalBounds = this.windowBounds.getRow(5).getSquare(0).getBounds();
		ok = new ButtonWithActive(
				ImageId.WINDOW_SETTINGS_OK_BTN, 
				ImageId.WINDOW_SETTINGS_OK_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, buttonSize), 
				buttonSize
			);
	}
	
	@Override
	public void removeComponents() {
		ImageManager.removeImage(ImageId.WINDOW_SETTINGS_WINDOW);
		title.removeComponents();
		sound.removeComponents();
		effectsVolume.removeComponents();
		music.removeComponents();
		musicVolume.removeComponents();
		notifications.removeComponents();
		moreSettings.removeComponents();
		ok.removeComponents();
	}
}
