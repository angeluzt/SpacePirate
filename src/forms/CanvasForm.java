package forms;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;

import commoninterfaces.Clickable;
import enums.ImageId;
import gameobjects.button.ButtonWithActive;
import gameobjects.sprites.Sprite;
import utils.Constants;
import utils.ImageManager;
import utils.ImageUtils;
import utils.Size;
import weapons.Bullet;

public class CanvasForm extends JComponent implements Clickable  {

	double vx = 210, vy = 10;
	double objX = 50, objY = 50;

	double vx1 = 100, vy1 = 10;
	double objX1 = 50, objY1 = 100;

	double vx2 = 100, vy2 = 10;
	double objX2 = 50, objY2 = 150;

	double vx3 = 100, vy3 = 10;
	double objX3 = 50, objY3 = 200;
	
	double vx4 = 100, vy4 = 10;
	double objX4 = 50, objY4 = 250;
	
	double vx5 = 100, vy5 = 10;
	double objX5 = 50, objY5 = 300;
	
	ImageUtils image = ImageUtils.getImageUtils();
	
	int bulletX = 700, bulletY = 400;
	Bullet bullet = new Bullet(bulletX, bulletY, 0, 30);
	Bullet bullet1 = new Bullet(bulletX, bulletY, 90, 100);
	Bullet bullet2 = new Bullet(bulletX, bulletY, 180, 30);
	Bullet bullet3 = new Bullet(bulletX, bulletY, 270, 30);
	
	Bullet bullet4 = new Bullet(bulletX, bulletY, 45, 30);
	Bullet bullet5 = new Bullet(bulletX, bulletY, 135, 30);
	Bullet bullet6 = new Bullet(bulletX, bulletY, 225, 30);
	Bullet bullet7 = new Bullet(bulletX, bulletY, 315, 30);
	
	ButtonWithActive button;
	
	Sprite sprite;
	Sprite spriteUfo;
	
	private static final long serialVersionUID = 1L;

	public CanvasForm() {

		for (int i = 1; i <= 10; i++) {
			ImageManager.addSpriteImage("Missile1_Flying_" + i,
					"/Images/Items/Sprites/Missile/Missile1_Flying_" + i + ".png", new Size(200, 200));
		}
		sprite = new Sprite("Missile1_Flying_", (short)10, new Point(500, 200), new Size(100, 400));
		
		for (int i = 1; i <= 9; i++) {
			ImageManager.addSpriteImage("Ufo_Regular_A_Explosion_" + i,
					"/Images/Ships/Ufo/Ships/Regular/A/Explosion_" + i + ".png", new Size(200, 200));
			System.out.println("Ufo_Regular_A_Explosion_" + i);
		}
		spriteUfo = new Sprite("Ufo_Regular_A_Explosion_", (short)9, new Point(800, 200), new Size(400, 400));

		ImageManager.addImage(ImageId.CLOSE_BTN, "/Images/Gui/Buttons/Btns/Close_BTN.png", new Size(200, 200));
		ImageManager.addImage(ImageId.CLOSE_ACT_BTN, "/Images/Gui/Buttons/Btns_Active/Close_BTN.png", new Size(200, 200));
		this.button = new ButtonWithActive(ImageId.CLOSE_BTN, ImageId.CLOSE_ACT_BTN, new Point(100, 100), new Size(200, 200));
		

		this.setDoubleBuffered(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		
		g.setColor(Color.RED);
		bullet.paintBullet(g);
		bullet1.paintBullet(g);
		bullet2.paintBullet(g);
		bullet3.paintBullet(g);
		
		bullet4.paintBullet(g);
		bullet5.paintBullet(g);
		bullet6.paintBullet(g);
		bullet7.paintBullet(g);
		
		this.button.drawElement(g);
		this.sprite.drawElement(g);
		this.spriteUfo.drawElement(g);
	}

	public void tick(double dt) {
		objX += vx/dt;
		objY += vy/dt;
		objX1 += vx1/dt;
		objX2 += vx2/dt;

		objX3 += vx3/dt;
		objX4 += vx4/dt;
		objX5 += vx5/dt;
		
		bullet.update(dt);
		bullet1.update(dt);
		bullet2.update(dt);
		bullet3.update(dt);
		
		bullet4.update(dt);
		bullet5.update(dt);
		bullet6.update(dt);
		bullet7.update(dt);
		
		this.sprite.moveSprite();
		this.spriteUfo.moveSprite();
	}
	
	public void repaintGame() {
		this.repaint();
	}

	@Override
	public boolean isElementClicked(Point point) {
		this.button.isElementClicked(point);

		return false;
	}
}
