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

public class YouWinLose extends GenericGui {

	private ButtonWithActive retry;
	private ButtonWithActive play;
	private ButtonWithActive close;
	
	private Icon starCero, star2, star3;
	private Icon score, record;

	public YouWinLose(Point point, Size size) {
		super(ImageId.WINDOW_WINER_WINDOW, point, size);

		//this.setShell();
	}

	@Override
	public void drawElement(Graphics g) {
		super.drawElement(g);

		if(!this.isVisible()) {
			return;
		}

		starCero.drawElement(g);
		star2.drawElement(g);
		star3.drawElement(g);
		
		score.drawElement(g);
		record.drawElement(g);
		
		retry.drawElement(g);
		play.drawElement(g);
		close.drawElement(g);
	}

	@Override
	public void isElementClicked(Point point, GenericGui currentUi) {
		if(!this.isFocused()) {
			return;
		}

		retry.isElementClicked(point, this);
		play.isElementClicked(point, this);
		close.isElementClicked(point, this);
	}

	@Override
	public void setShell() {
		Bounds externalBounds;
		Size newSize;

		this.windowBounds
			.addRow(14, 1)
			.addRow(29f, 3)
			.addRow(14f, 2)
			.addRow(14f, 2)
			.addRow(29f, 3);
		
		// window title: default "you win"
		externalBounds = this.windowBounds.getRow(0).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(50, 60);
		Icon title = new Icon(Trigonometry.centerSquareInsideanother(externalBounds, newSize), newSize);
		this.setWindowHeader(title);
		this.setTitleIfWiner(false);
		
		// star 1
		externalBounds = this.windowBounds.getRow(1).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(70, 70);
		starCero = new Icon(
				ImageId.WINDOW_WINER_START_0_POINTS,
				Trigonometry.downCenterSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		
		// star 2
		externalBounds = this.windowBounds.getRow(1).getSquare(1).getBounds();
		newSize = externalBounds.getScaledSize(70, 70);
		star2 = new Icon(
				ImageId.WINDOW_WINER_START_HALF_POINTS,
				Trigonometry.topCenterSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		
		// star 3
		externalBounds = this.windowBounds.getRow(1).getSquare(2).getBounds();
		newSize = externalBounds.getScaledSize(70, 70);
		star3 = new Icon(
				ImageId.WINDOW_WINER_START_1_POINTS,
				Trigonometry.downCenterSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		
		
		// TODO: numbers
		
		// score
		externalBounds = this.windowBounds.getRow(2).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(60, 40);
		score = new Icon(
				ImageId.WINDOW_WINER_SCORE_TXT,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		
		// record
		externalBounds = this.windowBounds.getRow(3).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(60, 40);
		record = new Icon(
				ImageId.WINDOW_WINER_RECORD_TXT,
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		
		// butons
		// retry
		externalBounds = this.windowBounds.getRow(4).getSquare(0).getBounds();
		newSize = externalBounds.getScaledSize(60, 60);
		retry = new ButtonWithActive(
				ImageId.WINDOW_WINER_REPLAY_BTN, 
				ImageId.WINDOW_WINER_REPLAY_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		// play
		externalBounds = this.windowBounds.getRow(4).getSquare(1).getBounds();
		newSize = externalBounds.getScaledSize(60, 60);
		play = new ButtonWithActive(
				ImageId.WINDOW_WINER_PLAY_BTN, 
				ImageId.WINDOW_WINER_PLAY_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
		// Close
		externalBounds = this.windowBounds.getRow(4).getSquare(2).getBounds();
		newSize = externalBounds.getScaledSize(60, 60);
		close = new ButtonWithActive(
				ImageId.WINDOW_WINER_CLOSE_BTN, 
				ImageId.WINDOW_WINER_CLOSE_ACT_BTN, 
				Trigonometry.centerSquareInsideanother(externalBounds, newSize), 
				newSize
			);
	}
	
	@Override
	public void removeComponents() {
		ImageManager.removeImage(ImageId.WINDOW_WINER_WINDOW);
		ImageManager.removeImage(ImageId.WINDOW_WINER_START_0_POINTS);
		ImageManager.removeImage(ImageId.WINDOW_WINER_START_HALF_POINTS);
		ImageManager.removeImage(ImageId.WINDOW_WINER_START_1_POINTS);
		ImageManager.removeImage(ImageId.WINDOW_WINER_SCORE_TXT);
		ImageManager.removeImage(ImageId.WINDOW_WINER_REPLAY_BTN);
		ImageManager.removeImage(ImageId.WINDOW_WINER_REPLAY_ACT_BTN);
		ImageManager.removeImage(ImageId.WINDOW_WINER_PLAY_BTN);
		ImageManager.removeImage(ImageId.WINDOW_WINER_PLAY_ACT_BTN);
		ImageManager.removeImage(ImageId.WINDOW_WINER_CLOSE_BTN);
		ImageManager.removeImage(ImageId.WINDOW_WINER_CLOSE_ACT_BTN);
	}
	
	// TODO
	public void setStars() {
		
	}
	
	public void setTitleIfWiner(boolean winner) {
		if(winner) {
			this.getWindowHeader().setImageId(ImageId.WINDOW_WINER_HEADER_WIN_TXT);
		} else {
			this.getWindowHeader().setImageId(ImageId.WINDOW_WINER_HEADER_LOSE_TXT);
		}
	}

	@Override
	public void activateEvent(ImageId buttonId) {
		// TODO Auto-generated method stub
		
	}

}
