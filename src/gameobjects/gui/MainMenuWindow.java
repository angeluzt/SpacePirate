package gameobjects.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;

import enums.ImageId;
import gameobjects.button.ButtonWithActive;
import gameobjects.button.Icon;
import utils.Bounds;
import utils.CommonEvents;
import utils.Size;
import utils.Trigonometry;
import utils.Utils;

public class MainMenuWindow extends GenericGui implements Runnable {

	private Icon menuHeader, background;
	private ButtonWithActive start, map, exit;
	private ButtonWithActive info, settings, score;
	private ButtonWithActive moreGames, record, FAQ;
	
	private Settings settingsWindow;
	private Purchase purchase;
	private Information informationWindow; 

	public MainMenuWindow(Point point, Size size) {
		super(ImageId.WINDOW_MENU_BG, point, size);
		
		// The first window most be focused
		this.setFocused(true);
		this.setShell();
		
		Size newSize = this.getBounds().getScaledSize(25, 45);
		settingsWindow = new Settings(
				Trigonometry.centerSquareInsideanother(this.getBounds(), newSize),  
				newSize
		);

		informationWindow = new Information(
				Trigonometry.centerSquareInsideanother(this.getBounds(), newSize),  
				newSize
		);
		
		purchase = new Purchase(
				Trigonometry.centerSquareInsideanother(this.getBounds(), newSize),  
				newSize
		);


		this.setVisible(true);
		new Thread(this).start();
	}

	@Override
	public void drawElement(Graphics g) {
		//g.setColor(Color.BLACK);
		//g.fillRect(this.getPoint().x, this.getPoint().y, this.getSize().width, this.getSize().height);
		
		background.drawElement(g);
		
		//Utils.drawGridSystem(g, windowBounds);
		
		info.drawElement(g);
		settings.drawElement(g);
		
		menuHeader.drawElement(g);
		start.drawElement(g);
		map.drawElement(g);
		exit.drawElement(g);

		// Internal windows
		settingsWindow.drawElement(g);
		informationWindow.drawElement(g);
		purchase.drawElement(g);

	}

	@Override
	public void isElementClicked(Point point, GenericGui currentUi) {
		// detect clicks in internal windows
		settingsWindow.isElementClicked(point, this);
		informationWindow.isElementClicked(point, this);
		purchase.isElementClicked(point, this);
	
		// do not detect clciks if not focused 
		if(!this.isFocused()) {
			return;
		}

		info.isElementClicked(point, this);
		settings.isElementClicked(point, this);
		start.isElementClicked(point, this);
		map.isElementClicked(point, this);
		exit.isElementClicked(point, this);
	}
	
	@Override
	public void activateEvent(ImageId buttonId) {
		switch (buttonId) {
			case WINDOW_MENU_INFO_BTN: 
				CommonEvents.openWindowOnTop(this, informationWindow);
				//CommonEvents.openWindowOnTop(this, purchase);
				break;
			case WINDOW_MENU_SETTINGS_BTN: 
				CommonEvents.openWindowOnTop(this, settingsWindow);
				/*this.setFocused(false);
				this.settingsWindow.setVisible(true);
				this.settingsWindow.setFocused(true);*/

				break;
			case WINDOW_MENU_START_BTN: 
				
				break;
			case WINDOW_MENU_MAP_BTN: 
				
				break;

			case WINDOW_MENU_EXIT_BTN: 
				CommonEvents.closeGame();
				break;
		}
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
		newSize = externalBounds.getScaledSize(1, 1); // 1, 1 so we can perform an animation in the thread
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

		
	}

	@Override
	public void run() {
		Bounds externalBounds;
		Size newSize;
		float requiredPercent = 1;
	
		while(requiredPercent < 80) {
			externalBounds = this.windowBounds.getRow(0).getSquare(1).getRow(0).getSquare(0).getBounds();
			newSize = externalBounds.getScaledSize(requiredPercent, requiredPercent);
			
			menuHeader.resizeIcon(newSize);
			menuHeader.setPoint(Trigonometry.downCenterSquareInsideanother(externalBounds, newSize));
			
			try {
				Thread.sleep(9);
			} catch (InterruptedException e) {
			}
			requiredPercent++;
		}
		
	}
}
