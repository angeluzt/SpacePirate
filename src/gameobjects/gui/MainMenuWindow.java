package gameobjects.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;

import commoninterfaces.DragDrop;
import enums.ImageId;
import enums.Sounds;
import gameobjects.button.ButtonWithActive;
import gameobjects.button.Icon;
import utils.Bounds;
import utils.CommonEvents;
import utils.Constants;
import utils.ImageManager;
import utils.Size;
import utils.Sound;
import utils.Trigonometry;
import utils.Utils;

public class MainMenuWindow extends GenericGui implements DragDrop {

	private Icon menuHeader, background;
	private ButtonWithActive start, map, exit;
	private ButtonWithActive info, settings, score;
	private ButtonWithActive moreGames, record, FAQ;

	private Settings settingsWindow;
	//private Purchase purchase;
	private Information informationWindow; 
	private LevelSelector levelSelector;
	
	private Sound backgroundSound;
	
	private final String infoTxt = "Wellcome to Space GUI, ";

	public MainMenuWindow(Point point, Size size) {
		super(ImageId.WINDOW_MENU_BG, point, size);
		
	}

	private void initSettings() {
		Size newSize = this.getBounds().getScaledSize(25, 45);
		settingsWindow = new Settings(
				Trigonometry.centerSquareInsideanother(this.getBounds(), newSize),  
				newSize
		);
	}

	private void initInformation() {
		Size newSize = this.getBounds().getScaledSize(25, 45);
		informationWindow = new Information(
				Trigonometry.centerSquareInsideanother(this.getBounds(), newSize),  
				newSize
		);
	}

	private void initLevelSelector() {
		levelSelector = new LevelSelector(this.getPoint(), this.getSize());
	}

	@Override
	public void drawElement(Graphics g) {

		if(this.isVisible()) {
			background.drawElement(g);
			Utils.drawGridSystem(g, windowBounds);

			info.drawElement(g);
			settings.drawElement(g);
			
			menuHeader.drawElement(g);
			start.drawElement(g);
			map.drawElement(g);
			exit.drawElement(g);
		}

		if(!this.isFocused()) {
			// Internal windows
			if(settingsWindow != null) 
				settingsWindow.drawElement(g);
			if(informationWindow != null) 
				informationWindow.drawElement(g);
			if(levelSelector != null) 
				levelSelector.drawElement(g);	
		}
	}

	@Override
	public void isElementClicked(Point point, GenericGui currentUi) {
		// do not detect clicks if not focused 
		if(this.isFocused()) {
			info.isElementClicked(point, this);
			settings.isElementClicked(point, this);
			start.isElementClicked(point, this);
			map.isElementClicked(point, this);
			exit.isElementClicked(point, this);
			
			return;
		} else {
			// detect clicks in internal windows
			if(settingsWindow != null) 
				settingsWindow.isElementClicked(point, this);
			if(informationWindow != null) 
				informationWindow.isElementClicked(point, this);
			if(levelSelector != null) 
				levelSelector.isElementClicked(point, this);
		}
	}
	
	@Override
	public void activateEvent(ImageId buttonId) {
		switch (buttonId) {
			case WINDOW_MENU_INFO_BTN: 
				//this.informationWindow.resizeImageIfExists();
				CommonEvents.openWindowOnTop(this, informationWindow);

				break;
			case WINDOW_MENU_SETTINGS_BTN:
				CommonEvents.openWindowOnTop(this, settingsWindow);

				break;
			case WINDOW_MENU_START_BTN:
				CommonEvents.openWindowOnTop(this, levelSelector);
				this.setVisible(false);

				break;
			case WINDOW_MENU_MAP_BTN: 
				
				break;

			case WINDOW_MENU_EXIT_BTN: 
				CommonEvents.closeGame();

				break;
			case WINDOW_SETTINGS_DRAG_BAR_MUSIC_SOUND, WINDOW_SETTINGS_MUSIC_BTN: 
				this.adjustMusicVolume(this.settingsWindow.getMusicSound());

				break;
			case WINDOW_SETTINGS_DRAG_BAR_EFECTS_SOUND, WINDOW_SETTINGS_SOUND_BTN: 
				this.adjustEffectsVolume(this.settingsWindow.getEffectsSound());
				new Sound().setFile(Sounds.BUTTON_CLICK, Constants.SOUND_EFFECTS_LEVEL).play();
				break;
		default:
			break;
		}
		this.setReferenceUI(null);
	}
	
	@Override
	public void dragElement(Point point, GenericGui currentUi) {
		this.settingsWindow.dragElement(point, this);
	}
	
	public void adjustMusicVolume(float newVolume) {
		this.backgroundSound.setVolume(newVolume);
		Constants.MUSIC_LEVEL = newVolume;
	}
	
	public void adjustEffectsVolume(float newVolume) {
		Constants.SOUND_EFFECTS_LEVEL = newVolume;
	}

	@Override
	public void setShell() {
		Bounds externalBounds;
		Size newSize;

		// get the size of the window and reduce the size, so we can define a margin once the we center it
		newSize = this.getBounds().getScaledSize(95, 95);
		Point newLocation = Trigonometry.centerSquareInsideanother(this.getBounds(), newSize);		
		this.windowBounds.setBounds(new Bounds(newLocation, newSize));

		windowBounds.addRowWithColumnPercent(100f, 3f, Arrays.asList(10d, 80d, 10d));
		windowBounds.getRow(0).getSquare(0) // information column
			.addRow(50, 1)
			.addRow(12.5f, 1)
			.addRow(12.5f, 1)
			.addRow(12.5f, 1)
			.addRow(12.5f, 1);
		windowBounds.getRow(0).getSquare(1)// header column
			.addRow(62.5f, 1)
			.addRow(12.5f, 1)
			.addRow(12.5f, 1)
			.addRow(12.5f, 1); 
		windowBounds.getRow(0).getSquare(2)// settings column
			.addRow(50, 1)
			.addRow(12.5f, 1)
			.addRow(12.5f, 1)
			.addRow(12.5f, 1)
			.addRow(12.5f, 1);

		// background: requires the size of the window
		externalBounds = this.windowBounds.getBounds();
		newSize = this.getBounds().getSize().getSize();
		background = new Icon(
				ImageId.WINDOW_MENU_BG,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);

		// Info button
		externalBounds = this.windowBounds.getRow(0).getSquare(0).getRow(0).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSizeSameWidth(50);
		info = new ButtonWithActive(
				ImageId.WINDOW_MENU_INFO_BTN,
				ImageId.WINDOW_MENU_INFO_ACT_BTN,
				Trigonometry.topCenterSquareInsideanother(externalBounds, newSize), 
				newSize.getWidth());
		
		// settings button
		externalBounds = this.windowBounds.getRow(0).getSquare(2).getBounds();
		//newSize = externalBounds.getScaledSize(50, 60);
		settings = new ButtonWithActive(
				ImageId.WINDOW_MENU_SETTINGS_BTN,
				ImageId.WINDOW_MENU_SETTINGS_ACT_BTN,
				Trigonometry.topCenterSquareInsideanother(externalBounds, newSize), 
				newSize.getWidth());
		
		// header icon
		externalBounds = this.windowBounds.getRow(0).getSquare(1).getRow(0).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(80, 80); // this can be 1, 1 so we can perform an animation in the thread
		menuHeader = new Icon(
				ImageId.WINDOW_MENU_HEADER_TXT,
				Trigonometry.downCenterSquareInsideanother(externalBounds, newSize), 
				newSize
			);

		// start icon
		externalBounds = this.windowBounds.getRow(0).getSquare(1).getRow(1).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(15, 60);
		start = new ButtonWithActive(
				ImageId.WINDOW_MENU_START_BTN,
				ImageId.WINDOW_MENU_START_BTN,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		// MAP button
		externalBounds = this.windowBounds.getRow(0).getSquare(1).getRow(2).getSquare(0).getBounds();
		//newSize = externalBounds.getScaledSize(15, 60);
		map = new ButtonWithActive(
				ImageId.WINDOW_MENU_MAP_BTN,
				ImageId.WINDOW_MENU_MAP_BTN,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);

		// EXIT button
		externalBounds = this.windowBounds.getRow(0).getSquare(1).getRow(3).getSquare(0).getBounds();
		//newSize = externalBounds.getScaledSize(15, 60);
		exit = new ButtonWithActive(
				ImageId.WINDOW_MENU_EXIT_BTN,
				ImageId.WINDOW_MENU_EXIT_BTN,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		
		// set focused in false because the first page is the loading page, thats the focused one
		this.setVisible(false);
		this.setFocused(false);
		
		initLevelSelector();
		initSettings();
		initInformation();
	}
	
	@Override
	public void removeComponents() {
		ImageManager.removeImage(ImageId.WINDOW_MENU_BG);
		info.removeComponents();
		settings.removeComponents();
		menuHeader.removeComponents();
		start.removeComponents();
		map.removeComponents();
		exit.removeComponents();
	}
	
	public void startSound() {
		backgroundSound = new Sound();
		backgroundSound.setFile(Sounds.MAIN_BG_MUSIC, Constants.MUSIC_LEVEL).play().loop();
	}
	
	public void pauseSound() {
		backgroundSound.stop();
	}
}
