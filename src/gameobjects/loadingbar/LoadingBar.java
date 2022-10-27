package gameobjects.loadingbar;

import java.awt.Graphics;
import java.awt.Point;

import commoninterfaces.Drawable;
import enums.ImageId;
import gameobjects.GenericItem;
import utils.ImageManager;
import utils.Size;
import utils.Utils;

public class LoadingBar extends GenericItem implements Drawable {

	private ImageId left, center, right, table = ImageId.LOADING_TABLE;
	private int widthForLeftAndRightPortion, widthForCenter;
	private int xForCenterPortion, xForRightPortion;
	
	private int currentCenterPercent = 0;
	private boolean isCompleted = false;

	public LoadingBar(Point point, Size size) {
		super(point, size);
		
		this.widthForLeftAndRightPortion = (int) (this.getSize().getWidth() * 0.1);
		this.widthForCenter = (int) (this.getSize().getWidth() * 0.8);
		
		this.xForCenterPortion = (int) this.getPoint().getX() + this.widthForLeftAndRightPortion;
		this.xForRightPortion = (int) this.getPoint().getX() + this.getSize().getWidth() - widthForLeftAndRightPortion;
		this.loadImages();
	}

	private void loadImages() {
		int loadingId = Utils.getRandomNumber(1, 3);

		switch(loadingId) {
		case 1:
			this.left = ImageId.LOADING1_L;
			this.center = ImageId.LOADING1_C;
			this.right = ImageId.LOADING1_R;

			break;
		case 2:
			this.left = ImageId.LOADING1_L;
			this.center = ImageId.LOADING1_C;
			this.right = ImageId.LOADING1_R;
			
			break;
		case 3:
			this.left = ImageId.LOADING1_L;
			this.center = ImageId.LOADING1_C;
			this.right = ImageId.LOADING1_R;

			break;
		default:
			this.left = ImageId.LOADING1_L;
			this.center = ImageId.LOADING1_C;
			this.right = ImageId.LOADING1_R;
			break;
		}

		int heightForPortion = this.getSize().getHeight();

		ImageManager.addImage(left, "/Images/Gui/Loading_Bar/LoadingBar" + loadingId + "_Left.png", new Size(widthForLeftAndRightPortion, heightForPortion));
		ImageManager.addImage(center, "/Images/Gui/Loading_Bar/LoadingBar" + loadingId + "_Center.png", new Size(widthForCenter, heightForPortion));
		ImageManager.addImage(right, "/Images/Gui/Loading_Bar/LoadingBar" + loadingId + "_Right.png", new Size(widthForLeftAndRightPortion, heightForPortion));
		ImageManager.addImage(table, "/Images/Gui/Loading_Bar/Table.png", this.getSize());
	}

	@Override
	public void drawElement(Graphics g) {
		g.drawImage(ImageManager.getImage(this.table), 
				(int)this.getPoint().getX(), 
				(int)this.getPoint().getY(), 
				this.getSize().getWidth(), 
				this.getSize().getHeight(), null);
		
		g.drawImage(ImageManager.getImage(this.left), 
				(int)this.getPoint().getX(), 
				(int)this.getPoint().getY(), 
				widthForLeftAndRightPortion, 
				this.getSize().getHeight(), null);

		g.drawImage(ImageManager.getImage(this.center), 
				this.xForCenterPortion, 
				(int)this.getPoint().getY(),
				(currentCenterPercent * widthForCenter) / 100, 
				this.getSize().getHeight(), null);

		if(this.isCompleted) {
			g.drawImage(ImageManager.getImage(this.right), 
					this.xForRightPortion, 
					(int)this.getPoint().getY(), 
					widthForLeftAndRightPortion, 
					this.getSize().getHeight(), null);
		}
	}
	
	public void increment(int increment) {
		int tempSum = increment + this.currentCenterPercent;

		if(tempSum <= 100) {
			this.currentCenterPercent = tempSum;
		} else {
			this.currentCenterPercent = 100;
			this.isCompleted = true;
		}
	}
}
