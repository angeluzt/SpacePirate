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
import gameobjects.gui.GenericGui;
import gameobjects.loadingbar.LoadingBar;
import gameobjects.sprites.Sprite;
import utils.Constants;
import utils.ImageManager;
import utils.ImageUtils;
import utils.Size;
import utils.Utils;
import weapons.Bullet;

public class CanvasForm extends JComponent implements Clickable  {

	double vx = 210, vy = 10;
	double objX = 50, objY = 50;
	
	ImageUtils image = ImageUtils.getImageUtils();
	
	List<Bullet> bullets = new ArrayList<>(); 
	
	ButtonWithActive button;
	LoadingBar loadingBar = new LoadingBar(new Point(400, 700), new Size(700, 80));
	GenericGui gui;
	
	Sprite sprite;
	Sprite spriteUfo;
	
	private static final long serialVersionUID = 1L;

	public CanvasForm() {
		// TODO: Remove when testing is completed
		for (int i = 1; i <= 10; i++) {
			ImageManager.addSpriteImage("Missile1_Flying_" + i,
					"/Images/Items/Sprites/Missile/Missile1_Flying_" + i + ".png", new Size(200, 200));
		}
		sprite = new Sprite("Missile1_Flying_", (short)10, new Point(500, 200), new Size(100, 400));
		
		for (int i = 1; i <= 9; i++) {
			ImageManager.addSpriteImage("Ufo_Regular_A_Explosion_" + i,
					"/Images/Ships/Ufo/Ships/Regular/A/Explosion_" + i + ".png", new Size(200, 200));
		}
		spriteUfo = new Sprite("Ufo_Regular_A_Explosion_", (short)9, new Point(800, 200), new Size(400, 400));
		
		for(int i = 0; i < 10000; i++) {
			this.bullets.add(new Bullet(400, 400, Utils.getRandomNumber(0, 360), 1));
		}

		// Pause
		/*ImageManager.addImage(ImageId.PAUSE_WINDOW, "/Images/Gui/Pause/Window.png", new Size(400, 400));
		gui = new GenericGui(ImageId.PAUSE_WINDOW, new Point(400, 0), new Size(400, 400), 0.01f, 0.14f, 0.17f);

		WindowMatrix sections = new WindowMatrix();
		sections.addColumn(100f / 3, 2, gui.getPointInWindow(), gui.getSizeInWindow());
		sections.addColumn(100f / 3, 1, gui.getPointInWindow(), gui.getSizeInWindow());
		sections.addColumn(100f / 3, 4, gui.getPointInWindow(), gui.getSizeInWindow());*/
		
		// Upgrade
		gui = new GenericGui(ImageId.PAUSE_WINDOW, new Point(400, 0), new Size(400, 600), 0.01f, 0.14f, 0.17f);
		// Hangar
		//gui = new GenericGui(ImageId.PAUSE_WINDOW, new Point(400, 0), new Size(400, 600), 0.01f, 0.14f, 0.17f);
		// Pause
		//gui = new GenericGui(ImageId.PAUSE_WINDOW, new Point(400, 0), new Size(400, 400), 0.01f, 0.14f, 0.17f);
		// Settings
		//gui = new GenericGui(ImageId.PAUSE_WINDOW, new Point(400, 0), new Size(400, 600), 0.01f, 0.14f, 0.17f);
		// You Win
		//gui = new GenericGui(ImageId.PAUSE_WINDOW, new Point(400, 0), new Size(400, 400), 0.01f, 0.14f, 0.17f);
		// Accept
		//gui = new GenericGui(ImageId.PAUSE_WINDOW, new Point(400, 0), new Size(400, 300), 0.01f, 0.14f, 0.17f);
		

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
		for(int i = 0; i < 10000; i++) {
			this.bullets.get(i).paintBullet(g);
		}
		
		this.button.drawElement(g);
		this.sprite.drawElement(g);
		this.spriteUfo.drawElement(g);
		this.loadingBar.drawElement(g);
		this.gui.drawElement(g);
	}

	public void tick(double dt) {
		double dtStep = vx/dt;
		for(int i = 0; i < 10000; i++) {
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

		return false;
	}
}
