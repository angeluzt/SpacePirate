package gameobjects.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import enums.ImageId;
import gameobjects.button.ButtonWithActive;
import gameobjects.button.Icon;
import gameobjects.button.SelectLevelBtn;
import utils.Bounds;
import utils.CommonEvents;
import utils.ImageManager;
import utils.Size;
import utils.Trigonometry;
import utils.Utils;

public class LevelSelector extends GenericGui {

	private ButtonWithActive left;
	private ButtonWithActive right;
	private ButtonWithActive close;
	
	public int selectedLevel;
	
	//private Icon levelSelector;

	private Icon background;
	private List<SelectLevelBtn> levels;
	public LevelSelector(Point point, Size size) {
		super(ImageId.WINDOW_MENU_BG, point, size);
		
		//this.setShell();
		this.pageLoaded = true;
	}

	public int getSelectedLevel() {
		return selectedLevel;
	}


	public void setSelectedLevel(int selectedLevel) {
		this.selectedLevel = selectedLevel;
	}


	@Override
	public void drawElement(Graphics g) {
		if(!this.isVisible()) {
			return;
		}
		background.drawElement(g);
		Utils.drawGridSystem(g, windowBounds);

		left.drawElement(g);
		right.drawElement(g);
		close.drawElement(g);
		//levelSelector.drawElement(g);
		
		SelectLevelBtn currentIcon;
		if(levels.size() > 0) {
			for (int i = 0; i < levels.size(); i++) {
				currentIcon = levels.get(i);
				currentIcon.drawElement(g);
			}
		}
	}
	
	public void setLevel(SelectLevelBtn clicketLevel) {
		System.out.println("Level: " + clicketLevel.getLevelId() + ", Stars: " + clicketLevel.getStars());
	}
	
	@Override
	public void activateEvent(ImageId buttonId) {
		switch (buttonId) {
		case WINDOW_LEVEL_SELECTOR_CLOSE_BTN:
			CommonEvents.closeWindowOpenedOnTop(this.getReferenceUI(), this);
			this.getReferenceUI().setVisible(true);
			break;

		default:
			
			break;
		}
		this.setReferenceUI(null);
	}

	@Override
	public void isElementClicked(Point point, GenericGui currentUi) {
		if(!this.isFocused()) {
			return;
		}
		this.setReferenceUI(currentUi);

		left.isElementClicked(point, this);
		right.isElementClicked(point, this);
		close.isElementClicked(point, this);
		
		SelectLevelBtn currentLevelBtn;
		for (int i = 0; i < levels.size(); i++) {
			currentLevelBtn = levels.get(i);
			currentLevelBtn.isElementClicked(point, this);
		}
	}

	@Override
	public void setShell() {
		Bounds externalBounds;
		Size newSize;

		this.windowBounds
			.addRowWithColumnPercent(100, 3, Arrays.asList(15d, 70d, 15d))
			.getRow(0).getSquare(1)
			.addRow(80, 1)
			.addRow(20, 1);
		
		// background: requires the size of the window
		externalBounds = this.windowBounds.getBounds();
		newSize = this.getBounds().getSize().getSize();
		background = new Icon(
				ImageId.WINDOW_MENU_BG,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);

		// left
		externalBounds = this.windowBounds.getRow(0).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSizeSameWidth(60);
		left = new ButtonWithActive(
				ImageId.WINDOW_LEVEL_SELECTOR_LEFT_ARROW_BTN, 
				ImageId.WINDOW_LEVEL_SELECTOR_LEFT_ARROW_ACT_BTN,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);

		// left
		externalBounds = this.windowBounds.getRow(0).getSquare(2).getBounds();
		newSize = externalBounds.getScaledSizeSameWidth(60);
		right = new ButtonWithActive(
				ImageId.WINDOW_LEVEL_SELECTOR_RIGHT_ARROW_BTN, 
				ImageId.WINDOW_LEVEL_SELECTOR_RIGHT_ARROW_ACT_BTN,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		
		// left
		externalBounds = this.windowBounds.getRow(0).getSquare(1).getRow(1).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSizeSameHeight(60);
		close = new ButtonWithActive(
				ImageId.WINDOW_LEVEL_SELECTOR_CLOSE_BTN, 
				ImageId.WINDOW_LEVEL_SELECTOR_CLOSE_ACT_BTN,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		
		externalBounds = this.windowBounds.getRow(0).getSquare(1).getRow(0).getSquare(0).getBounds();
		//newSize = externalBounds.getScaledSize(80, 80);

		Point levelLocation = externalBounds.getLoctionClone();
		Size levelSize = new Size(externalBounds.getSize().width / 4, externalBounds.getSize().height / 3);
		this.addLevels(new Bounds(levelLocation, levelSize));
	}
	
	@Override
	public void removeComponents() {
		ImageManager.removeImage(ImageId.WINDOW_MENU_BG);
		background.removeComponents();
		left.removeComponents();
		right.removeComponents();
		close.removeComponents();
	}
	
	private void addLevels(Bounds levelsBounds) {
		Bounds externalBounds = new Bounds(levelsBounds.getLoctionClone(), levelsBounds.getSizeClone());
		Size newSize = levelsBounds.getScaledSizeSameHeight(80);
		Point currentLocation;
		
		// add levels
		levels = new ArrayList<>();
		int currentLevel = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				currentLocation = new Point((int)(levelsBounds.getX() + i * levelsBounds.getWidth()), (int)(levelsBounds.getY() + j * levelsBounds.getHeight()));
				
				externalBounds.setLocation(currentLocation);
				int randomNumber = Utils.getRandomNumber(0, 3);
				
				switch (randomNumber) {
					case 0: 
						levels.add(new SelectLevelBtn(
								ImageId.WINDOW_LEVEL_SELECTOR_BTN_1,
								ImageId.WINDOW_LEVEL_SELECTOR_BTN_1,
								Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
								newSize, currentLevel,0));
						break;
					case 1: 
						levels.add(new SelectLevelBtn(
								ImageId.WINDOW_LEVEL_SELECTOR_BTN_2,
								ImageId.WINDOW_LEVEL_SELECTOR_BTN_2,
								Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
								newSize, currentLevel,0));
						break;
					case 2: 
						levels.add(new SelectLevelBtn(
								ImageId.WINDOW_LEVEL_SELECTOR_BTN_3,
								ImageId.WINDOW_LEVEL_SELECTOR_BTN_3,
								Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
								newSize, currentLevel,0));
						break;
					case 3: 
						levels.add(new SelectLevelBtn(
								ImageId.WINDOW_LEVEL_SELECTOR_BTN_4,
								ImageId.WINDOW_LEVEL_SELECTOR_BTN_4,
								Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
								newSize, currentLevel,0));
						break;
				}
				
				currentLevel += 1;
			}
		}
	}

}
