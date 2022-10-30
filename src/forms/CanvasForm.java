package forms;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import commoninterfaces.Clickable;
import gameobjects.gui.Accept;
import gameobjects.gui.GenericGui;
import gameobjects.gui.Information;
import gameobjects.gui.MainMenuWindow;
import gameobjects.gui.Pause;
import gameobjects.gui.Purchase;
import gameobjects.gui.YouWinLose;
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
	
	LoadingBar loadingBar = new LoadingBar(new Point(400, 700), new Size(700, 80));
	GenericGui gui;
	
	Sprite sprite;
	Sprite spriteUfo;
	
	Pause pause;
	Information information;
	Accept accept;
	Purchase purchase;
	YouWinLose youWin;
	
	MainMenuWindow menu = new MainMenuWindow(new Point(0, 0), new Size(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
	
	private static final long serialVersionUID = 1L;

	public CanvasForm() {
		accept = new Accept(new Point(0, 0), new Size(300, 300));
		purchase = new Purchase(new Point(300, 0), new Size(300, 300));
		information = new Information(new Point(600, 0), new Size(300, 300));
		pause = new Pause(new Point(900, 0), new Size(400, 350));
		youWin = new YouWinLose(new Point(0, 350), new Size(400, 400));
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

		this.setDoubleBuffered(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		
		//g.setColor(Color.RED);
		/*for(int i = 0; i < this.bullets.size(); i++) {
			this.bullets.get(i).paintBullet(g);
		}*/
		
		this.sprite.drawElement(g);
		this.spriteUfo.drawElement(g);
		this.loadingBar.drawElement(g);

		this.accept.drawElement(g);
		this.purchase.drawElement(g);
		this.information.drawElement(g);
		this.pause.drawElement(g);
		this.youWin.drawElement(g);
		
		this.menu.drawElement(g);
	}

	public void tick(double dt) {
		double dtStep = vx/dt;
	
		/*for(int i = 0; i < this.bullets.size(); i++) {
			this.bullets.get(i).update(dtStep);
		}*/
		
		this.sprite.moveSprite();
		this.spriteUfo.moveSprite();
		this.loadingBar.increment(1);
	}
	
	public void repaintGame() {
		this.repaint();
	}

	@Override
	public boolean isElementClicked(Point point) {
		this.accept.isElementClicked(point);
		this.purchase.isElementClicked(point);
		this.information.isElementClicked(point);
		this.pause.isElementClicked(point);
		this.youWin.isElementClicked(point);
		
		this.menu.isElementClicked(point);

		return false;
	}
}
