package gameobjects.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;

import commoninterfaces.Clickable;
import enums.ImageId;
import gameobjects.button.ButtonWithActive;
import gameobjects.button.Icon;
import gameobjects.matrix.WindowSquare;
import utils.Bounds;
import utils.Size;
import utils.Trigonometry;
import utils.Utils;

public class MainMenuWindow extends GenericGui implements Clickable, Runnable {

	private Icon menuHeader, background;
	private ButtonWithActive start, map, exit;
	private ButtonWithActive info, settings, score;
	private ButtonWithActive moreGames, record, FAQ;

	public MainMenuWindow(Point point, Size size) {
		super(ImageId.WINDOW_MENU_BG, point, size);
		
		this.setShell();
		new Thread(this).start();
	}

	@Override
	public void drawElement(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(this.getPoint().x, this.getPoint().y, this.getSize().width, this.getSize().height);
		
		background.drawElement(g);
		info.drawElement(g);
		settings.drawElement(g);
		
		menuHeader.drawElement(g);
		start.drawElement(g);
		map.drawElement(g);
		exit.drawElement(g);

		g.setColor(Color.WHITE);
		Utils.drawGridSystem(g, windowBounds);
	}

	@Override
	public boolean isElementClicked(Point point) {

		info.isElementClicked(point);
		settings.isElementClicked(point);

		start.isElementClicked(point);
		map.isElementClicked(point);
		exit.isElementClicked(point);

		return false;
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
		windowBounds.getRow(0).getSquare(2) // settings column
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
		newSize = externalBounds.getScaledSize(50, 60);
		info = new ButtonWithActive(
				ImageId.WINDOW_MENU_INFO_BTN,
				ImageId.WINDOW_MENU_INFO_ACT_BTN,
				Trigonometry.topCenterSquareInsideanother(externalBounds, newSize), 
				newSize.getWidth());
		
		// settings button
		externalBounds = this.windowBounds.getRow(0).getSquare(2).getBounds();
		newSize = externalBounds.getScaledSize(50, 60);
		settings = new ButtonWithActive(
				ImageId.WINDOW_MENU_SETTINGS_BTN,
				ImageId.WINDOW_MENU_SETTINGS_ACT_BTN,
				Trigonometry.topCenterSquareInsideanother(externalBounds, newSize), 
				newSize.getWidth());
		
		// header icon
		externalBounds = this.windowBounds.getRow(0).getSquare(1).getRow(0).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(1, 1);
		menuHeader = new Icon(
				ImageId.WINDOW_MENU_HEADER,
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
		// MAP icon
		externalBounds = this.windowBounds.getRow(0).getSquare(1).getRow(2).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(15, 60);
		map = new ButtonWithActive(
				ImageId.WINDOW_MENU_MAP_BTN,
				ImageId.WINDOW_MENU_MAP_BTN,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		// EXIT icon
		externalBounds = this.windowBounds.getRow(0).getSquare(1).getRow(3).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(15, 60);
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
