package forms;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import commoninterfaces.PropagateClick;
import gameobjects.gui.GenericGui;
import gameobjects.gui.MainMenuWindow;
import gameobjects.gui.LoadingPage;
import gameobjects.sprites.Sprite;
import utils.Constants;
import utils.ImageManager;
import utils.ImageUtils;
import utils.Size;
import utils.Trigonometry;
import utils.Utils;
import weapons.Bullet;

/**
 * This file contains the canvas, and the main function is to propagate the clicks, events, delta time,
 * to the main menu and consequently to the classes inside the menu.
 * 
 * @author angel
 *
 */
public class CanvasForm extends JComponent implements PropagateClick, Runnable {

	double vx = 10, vy = 10;
	double objX = 50, objY = 50;

	ImageUtils image = ImageUtils.getImageUtils();

	//List<Bullet> bullets = new ArrayList<>(); 
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
		
		/*for (int i = 0; i < 10; i++) {
			bullets.add(new Bullet(200, 200, Utils.getRandomNumber(0, 299), 100));
		}*/
		
		// start the loading bar animation once the main menu is loaded
		new Thread(this).start();
	}
	
	public void repaintGame() {
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		
		/*this.sprite.drawElement(g);
		this.spriteUfo.drawElement(g);*/
		
		this.menu.drawElement(g);

		if(this.loadingPage != null) {
			this.loadingPage.drawElement(g);
		}

		/*g.setColor(Color.RED);
		for(int i = 0; i < this.bullets.size(); i++) {
			this.bullets.get(i).paintBullet(g);
		}*/
		


		// create rectangle centered on the point we want to rotate it about

		/*AffineTransform transform = new AffineTransform();
		transform.rotate(theta);
		transform.translate(x, y); 
		// it's been while, you might have to perform the rotation and translate in the
		// opposite order

		Shape shape = transform.createTransformedShape(rect);

		 // get it from whatever you're drawing to
		g.setColor(Color.RED);
		g.fillRect(x, y, 10, 10);
		graphics.draw(shape);*/
		
		if(viewLaser) {
			int randomHeight = Utils.getRandomNumber(1, heightt);
			int randomWith = Utils.getRandomNumber(1, 10);
			Rectangle2D rect = new Rectangle2D.Double(xt, yt + randomHeight / 2, widtht - randomWith, heightt - randomHeight);
			Shape shape = rotate(theta, centerXt, centerYt).createTransformedShape(rect);
			Graphics2D graphics = (Graphics2D) g;
			
			g.setColor(Color.RED);
			g.fillRect(centerXt, centerYt, 3, 3);
			graphics.fill(shape);
		}

		//graphics.draw(shape);
	}
	
	public AffineTransform rotate(double theta, double anchorx, double anchory) {
		AffineTransform transform = new AffineTransform();
	    // REMIND: Simple for now - optimize later
		transform.translate(anchorx, anchory);
		transform.rotate(theta);
		transform.translate(-anchorx, -anchory);

		return transform;
	}

	/**
	 * This is the method that moves all the objects by updating their coordinates every tick call.
	 * A tick represent how many calls we make per second to this method, so this "dt" represents the
	 * UPS = updates per second
	 * 
	 * Example:
	 * 	1dt/seg  (1dt  means we are calling this method once per second)
	 *	2dt/seg  (2dt  means we are calling this method two times per second)
	 *	60dt/seg (60dt means we are calling this method sixty times per second)
	 *
	 * Example 1: 1dt and speed of object = 30
	 * by using d = s/t (distance = speed/ time)
	 * 
	 * Calculate new location
	 * 		1) d = 30 / 1dt
	 * 		   d = 30
	 * 		2) this means the object moves a distance of 30 per second(then in 60 calls we also have a distance of 30)
	 * 
	 * Example 2: 2dt and speed of object = 30
	 * by using d = s/t (distance = speed/ time)
	 * 
	 * Calculate new location
	 * 		1) d = 30 / 2
	 * 		   d = 15
	 * 		2) this means the object moves a distance of 15 in every call(then in 2 calls we also have a distance of 30)
	 *
	 * Example 3: 60dt and speed of object = 30
	 * by using d = s/t (distance = speed/ time)
	 * 
	 * Calculate new location
	 * 		1) d = 30 / 60
	 * 		   d = 0.5
	 * 		2) this means the object moves a distance of 0.5 in every call(then in 60 calls we also have a distance of 30)
	 * 
	 * @param dt - Delta Time
	 */
	public void tick(double dt) {

		/*for(int i = 0; i < this.bullets.size(); i++) {
			this.bullets.get(i).update(dt);
		}*/
		
		//this.menu.activateEvent();
		
		/*this.sprite.moveSprite();
		this.spriteUfo.moveSprite();
		this.loadingBar.increment(1);*/
	}

	public void propagateClick(Point point) {

		/*double xBullet = Constants.WINDOW_WIDTH / 2, yBullet = Constants.WINDOW_HEIGHT / 2;
		double angle = Trigonometry.getAngleBetweenTwoPoints( point.x, point.y, xBullet, yBullet);
		//System.out.println("Angle: " + angle);
		/*bullets.add(
				new Bullet(
						xBullet, yBullet, 
						angle, 
						400));*/
		this.menu.isElementClicked(point, null);
	}
	
	int xt = 500;
	int yt = 400;
	int widtht = 300;
	int heightt = 30;
	int centerXt = xt, centerYt = yt + heightt / 2;
	double theta = 0;
	boolean viewLaser = false;
	public void propagateDrag(Point point) {
		theta = Trigonometry.getFixedLaserAngleBetweenTwoPoints(point.x, point.y, centerXt, centerYt);
		theta = Math.toRadians(theta);
		viewLaser = true;
		//System.out.println(point.x + "_" + point.y);
		/*for (Bullet bullet: bullets) {
			bullet.setAngle(Trigonometry.getAngleBetweenTwoPoints(point.x, point.y, bullet.x, bullet.y));
		}*/

		this.menu.dragElement(point, null);
	}
	
	public void stopLaser() {
		viewLaser = false;
	}

	/**
	 * This run method handles the animation of the loading bar
	 * once the main menu is loaded we start the animation,
	 * this is only visual, considering that when this thread starts
	 * the main menu is already loaded.
	 */
	@Override
	public void run() {
		while(true) {
			
			// star bar animation when the main menu is already loaded
			if(this.menu != null && this.menu.isPageLoaded()) {
				while(!this.loadingPage.isCompleted()) {
					this.loadingPage.setIncrement(1);
					
					try {
						//Thread.sleep(30);
						Thread.sleep(10);
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
