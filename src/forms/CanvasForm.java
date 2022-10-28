package forms;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import commoninterfaces.Clickable;
import enums.ImageId;
import gameobjects.button.ButtonWithActive;
import gameobjects.gui.Accept;
import gameobjects.gui.GenericGui;
import gameobjects.gui.Pause;
import gameobjects.gui.Purchase;
import gameobjects.loadingbar.LoadingBar;
import gameobjects.sprites.Sprite;
import utils.Constants;
import utils.ImageManager;
import utils.ImageUtils;
import utils.Size;
import weapons.Bullet;

public class CanvasForm extends JComponent implements Clickable  {

	double vx = 210, vy = 10;
	double objX = 50, objY = 50;
	
	ImageUtils image = ImageUtils.getImageUtils();
	
	List<Bullet> bullets = new ArrayList<>(); 
	//Bullet bullet;
	
	ButtonWithActive button;
	ButtonWithActive button1;
	ButtonWithActive button2;
	LoadingBar loadingBar = new LoadingBar(new Point(400, 700), new Size(700, 80));
	GenericGui gui;
	
	Sprite sprite;
	Sprite spriteUfo;
	
	Pause pause;
	Accept accept;
	Purchase purchase;
	
	private static final long serialVersionUID = 1L;

	public CanvasForm() {
		pause = new Pause(new Point(0, 0), new Size(500, 500), 0.01f, 0.14f, 0.17f);
		accept = new Accept(new Point(500, 0), new Size(500, 500), 0.01f, 0.14f, 0.17f);
		purchase = new Purchase(new Point(1000, 0), new Size(500, 500), 0.01f, 0.14f, 0.17f);
		// TODO: Remove when testing is completed
		for (int i = 1; i <= 10; i++) {
			ImageManager.addSpriteImage("Missile1_Flying_" + i,
					"/Images/Items/Sprites/Missile/Missile1_Flying_" + i + ".png", new Size(100, 400));
		}
		sprite = new Sprite("Missile1_Flying_", (short)10, new Point(500, 200), new Size(100, 400));
		
		for (int i = 1; i <= 9; i++) {
			ImageManager.addSpriteImage("Ufo_Regular_A_Explosion_" + i,
					"/Images/Ships/Ufo/Ships/Regular/A/Explosion_" + i + ".png", new Size(100, 400));
		}
		spriteUfo = new Sprite("Ufo_Regular_A_Explosion_", (short)9, new Point(800, 200), new Size(100, 400));
		
		for(int i = 0; i < 360; i++) {
			this.bullets.add(new Bullet(800, 500, i, 5));
		}

		ImageManager.addImage(ImageId.CLOSE_BTN, new Size(200, 200));
		ImageManager.addImage(ImageId.CLOSE_ACT_BTN, new Size(200, 200));
		this.button = new ButtonWithActive(ImageId.CLOSE_BTN, ImageId.CLOSE_ACT_BTN, new Point(100, 100), new Size(200, 200));
		this.button1 = new ButtonWithActive(ImageId.CLOSE_BTN, ImageId.CLOSE_ACT_BTN, new Point(100, 300), new Size(200, 200));
		this.button2 = new ButtonWithActive(ImageId.CLOSE_BTN, ImageId.CLOSE_ACT_BTN, new Point(100, 500), new Size(200, 200));

		this.setDoubleBuffered(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		
		g.setColor(Color.RED);
		for(int i = 0; i < this.bullets.size(); i++) {
			this.bullets.get(i).paintBullet(g);
		}
		
		this.button.drawElement(g);
		this.button1.drawElement(g);
		this.button2.drawElement(g);
		this.sprite.drawElement(g);
		this.spriteUfo.drawElement(g);
		this.loadingBar.drawElement(g);
		this.pause.drawElement(g);
		this.accept.drawElement(g);
		this.purchase.drawElement(g);
	}

	public void tick(double dt) {
		double dtStep = vx/dt;
	
		for(int i = 0; i < this.bullets.size(); i++) {
			this.bullets.get(i).update(dtStep);
		}
		
		this.sprite.moveSprite();
		this.spriteUfo.moveSprite();
		this.loadingBar.increment(1);
	}
	
	public void repaintGame() {
		this.repaint();
	}

	@Override
	public boolean isElementClicked(Point point) {
		this.button.isElementClicked(point);
		this.button1.isElementClicked(point);
		this.button2.isElementClicked(point);

		this.pause.isElementClicked(point);
		this.accept.isElementClicked(point);
		purchase.isElementClicked(point);

		return false;
	}
}
