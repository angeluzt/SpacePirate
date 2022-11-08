package forms;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import utils.Constants;

/**
 * Main frame that handles Delta time and also detect every user interaction with the screen
 * example: clicks, listeners, events, etc.
 * @author angel
 *
 */
public class DeltaForm extends JFrame implements Runnable, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	CanvasForm gameManager;
	
	private final double FPS = 40D;
	private final double UPS = 30D;
	
	private final double timeFps = Constants.NANO_SECOND / FPS;
	private final double timeUps = Constants.NANO_SECOND / UPS;
	
	private double deltaUps = 0, deltaFps = 0;
	private long initialTime = System.nanoTime();
	
	boolean quit = false;
    double t = 0.0;
    double dt = 1;

    double currentTime = System.nanoTime();
    double accumulator = 0.0;

    public DeltaForm() {
    	// #1: required game instances
    	this.gameManager = new CanvasForm();

    	// #2: Define window
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.pack();
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.getContentPane().add(gameManager);

        // #3: Add listeners
        this.addMouseListener(this);
        //this.addKeyListener(this);
        this.addMouseMotionListener(this);
        
        new Thread(this).start();
    }

    private int speed = 40;
    private int distance = 10;
    private double tickTime = 0;
    private boolean enableTimeCount = false;
    
    /**
     * This run method handles the Delta Time, this is what brings the game to live since is the main loop.
     */
	@Override
	public void run() {
		long startTime = 0;
		long elapsedTime;

		while(!quit) {
			long currentTime = System.nanoTime();
	        this.deltaUps += (currentTime - this.initialTime) / this.timeUps;
	        this.deltaFps += (currentTime - this.initialTime) / this.timeFps;
	
	        this.initialTime = currentTime;
	
	        // UPS - Update Per Second
		    if(this.deltaUps >= 1){
		    	if(enableTimeCount) {
		    		startTime = System.nanoTime();
		    	}
		    	/** 
		    	 * the objects will move the same amount of distance in 1 UPS or 300 UPS
		    	 * d = s/t, considering UPS as time, then distance = speed/UPS will result 
		    	 * in a fraction of distance every second
		    	 */
		    	this.gameManager.tick(UPS);
		    	this.deltaUps = 0;
		    	
		    	if(enableTimeCount) {
			        elapsedTime = System.nanoTime() - startTime;
			        System.out.println("Total execution time to create 1000K objects in Java in millis: " + elapsedTime/1000000);
		    	}
		    }

	        // FPS - Frames Per Second
		    if(this.deltaFps >= 1) {
		    	this.gameManager.repaintGame();
		    	this.deltaFps = 0;
		    }
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		this.gameManager.propagateClick(e.getPoint());
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.gameManager.propagateDrag(e.getPoint());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
