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
		
		this.widthForLeftAndRightPortion = (int) (this.getSize().width * 0.1);
		this.widthForCenter = (int) (this.getSize().width * 0.8);
		
		this.xForCenterPortion = (int) this.getPoint().getX() + this.widthForLeftAndRightPortion;
		this.xForRightPortion = (int) this.getPoint().getX() + this.getSize().width - widthForLeftAndRightPortion;
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
			this.left = ImageId.LOADING2_L;
			this.center = ImageId.LOADING2_C;
			this.right = ImageId.LOADING2_R;
			
			break;
		case 3:
			this.left = ImageId.LOADING3_L;
			this.center = ImageId.LOADING3_C;
			this.right = ImageId.LOADING3_R;

			break;
		default:
			this.left = ImageId.LOADING1_L;
			this.center = ImageId.LOADING1_C;
			this.right = ImageId.LOADING1_R;
			break;
		}

		int heightForPortion = this.getSize().height;

		ImageManager.addImage(left, new Size(widthForLeftAndRightPortion, heightForPortion));
		ImageManager.addImage(center, new Size(widthForCenter, heightForPortion));
		ImageManager.addImage(right, new Size(widthForLeftAndRightPortion, heightForPortion));
		ImageManager.addImage(table, this.getSize());
	}

	@Override
	public void drawElement(Graphics g) {

		if(!this.isVisible()) {
			return;
		}

		g.drawImage(ImageManager.getImage(this.table), 
				(int)this.getPoint().getX(), 
				(int)this.getPoint().getY(), null);
		
		g.drawImage(ImageManager.getImage(this.left), 
				(int)this.getPoint().getX(), 
				(int)this.getPoint().getY(), null);

		g.drawImage(ImageManager.getImage(this.center), 
				this.xForCenterPortion, 
				(int)this.getPoint().getY(),
				(currentCenterPercent * widthForCenter) / 100, 
				this.getSize().height, null);

		if(this.isCompleted) {
			g.drawImage(ImageManager.getImage(this.right), 
					this.xForRightPortion, 
					(int)this.getPoint().getY(), null);
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
