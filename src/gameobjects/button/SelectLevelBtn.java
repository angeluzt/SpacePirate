package gameobjects.button;

import java.awt.Point;

import enums.ImageId;
import gameobjects.gui.GenericGui;
import gameobjects.gui.LevelSelector;
import utils.Size;
import utils.Trigonometry;

public class SelectLevelBtn extends ButtonWithActive {
	private int levelId, stars;

	public SelectLevelBtn(ImageId imageId, ImageId imageActiveId, Point location, Size size, int levelId, int stars) {
		super(imageId, imageActiveId, location, size);
		
		this.levelId = levelId;
		this.stars = stars;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}
	
	@Override
	public void isElementClicked(Point point, GenericGui currentUi) {
		super.isElementClicked(point, currentUi);

		boolean isClicket = Trigonometry.isPointInsideRegion(point, this);
		
		if(isClicket) {
			if(currentUi instanceof LevelSelector) {
				((LevelSelector) currentUi).setLevel(this);
			}
		}
	}
}
