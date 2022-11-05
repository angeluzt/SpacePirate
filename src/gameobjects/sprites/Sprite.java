package gameobjects.sprites;

import java.awt.Graphics;
import java.awt.Point;

import commoninterfaces.Drawable;
import gameobjects.GenericItem;
import utils.ImageManager;
import utils.Size;

public class Sprite extends GenericItem implements Drawable {
	private String baseId;
	private short currentSprite;
	private short spriteLength;
	private SpriteDuration duration;
	private String currentImage;

	StringBuilder builder = new StringBuilder();

	public Sprite(String baseId, short spriteLength, Point location, Size size) {
		super(location, size);
		
		this.changeSpriteStatus(baseId, spriteLength);
	}
	
	public void changeSpriteStatus(String baseId, short spriteLength) {
		this.currentSprite = 1;
		this.baseId = baseId;
		this.duration = new SpriteDuration();
		this.spriteLength = spriteLength;
		this.currentImage = this.builder.append(baseId).append(this.currentSprite).toString();
	}

	public void moveSprite() {
		if(this.duration.isTimeCovered()) {
			short nextSprite = (short)(this.currentSprite + 1);
			
			this.currentSprite = nextSprite <= this.spriteLength? nextSprite : 1;
			
			this.builder.setLength(0);
			this.currentImage = this.builder.append(baseId).append(this.currentSprite).toString();
		}
	}

	@Override
	public void drawElement(Graphics g) {
		if(ImageManager.getSpriteImage(this.currentImage) != null) {
			g.drawImage(ImageManager.getSpriteImage(this.currentImage), 
					(int)this.getPoint().getX(), 
					(int)this.getPoint().getY(), null);
		}

	}
	
	@Override
	public void removeComponents() {
		for (int i = 0; i < spriteLength; i++) {
			ImageManager.removeSpriteImage(baseId + this.currentSprite);
		}
	}
}
