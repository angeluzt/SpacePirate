package forms;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import commoninterfaces.PropagateClick;
import enums.ImageId;
import gameobjects.gui.GenericGui;
import gameobjects.gui.MainMenuWindow;
import gameobjects.gui.LoadingPage;
import gameobjects.loadingbar.LoadingBar;
import gameobjects.sprites.Sprite;
import utils.Constants;
import utils.ImageManager;
import utils.ImageUtils;
import utils.Size;
import weapons.Bullet;

public class CanvasForm extends JComponent implements PropagateClick, Runnable {

	double vx = 210, vy = 10;
	double objX = 50, objY = 50;

	ImageUtils image = ImageUtils.getImageUtils();

	List<Bullet> bullets = new ArrayList<>(); 

	//LoadingBar loadingBar = new LoadingBar(new Point(400, 700), new Size(700, 80));
	LoadingPage loadingPage;
	GenericGui gui;

	Sprite sprite;
	Sprite spriteUfo;
	
	MainMenuWindow menu;
	private static final long serialVersionUID = 1L;

	public CanvasForm() {
		Size fullScreen = new Size(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		
		loadingPage = new LoadingPage(new Point(0, 0), fullScreen);
		menu = new MainMenuWindow(new Point(0, 0), fullScreen);

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
		
		// start the loading bar animation, once completed show the menu
		new Thread(this).start();
	}
	
	public void repaintGame() {
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		
		//g.setColor(Color.RED);
		/*for(int i = 0; i < this.bullets.size(); i++) {
			this.bullets.get(i).paintBullet(g);
		}*/
		
		/*this.sprite.drawElement(g);
		this.spriteUfo.drawElement(g);*/
		
		this.menu.drawElement(g);

		if(this.loadingPage != null) {
			this.loadingPage.drawElement(g);
		}
	}

	public void tick(double dt) {
		double dtStep = vx/dt;
	
		/*for(int i = 0; i < this.bullets.size(); i++) {
			this.bullets.get(i).update(dtStep);
		}*/
		
		//this.menu.activateEvent();
		
		/*this.sprite.moveSprite();
		this.spriteUfo.moveSprite();
		this.loadingBar.increment(1);*/
	}

	public void propagateClick(Point point) {		
		this.menu.isElementClicked(point, null);
	}
	
	public void propagateDrag(Point point) {
		this.menu.dragElement(point, null);
	}

	@Override
	public void run() {
		while(true) {
			if(this.menu != null && this.menu.isPageLoaded()) {
				// star bar animation
				int currentBarPercent = this.loadingPage.getCurrentCenterPercent();

				while(!this.loadingPage.isCompleted()) {
					this.loadingPage.setIncrement(1);
					
					currentBarPercent++;
					
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// show the menu once the loading animation is completed
				this.menu.setFocused(true);
				this.menu.setVisible(true);
				this.menu.startSound();
				
				
				this.loadingPage.removeComponents();
				this.loadingPage = null;
				return;
			}
		}
	}
}
