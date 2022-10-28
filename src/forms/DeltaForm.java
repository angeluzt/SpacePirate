package forms;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import utils.Constants;

public class DeltaForm extends JFrame implements Runnable, MouseListener {

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
        //this.addMouseMotionListener(this);
        
        new Thread(this).start();
    }

    int speed = 40;
    int distance = 10;
    double tickTime = 0;

    int currentStep = 0;
    double deltaTime = 0;
	@Override
	public void run() {
		while(!quit) {
			long currentTime = System.nanoTime();
	        this.deltaUps += (currentTime - this.initialTime) / this.timeUps;
	        this.deltaFps += (currentTime - this.initialTime) / this.timeFps;
	
	        this.initialTime = currentTime;
	
	        // UPS
		    if(this.deltaUps >= 1){

		    	//long startTime = System.nanoTime();
		    	// Calculate the delta time (local time between each step)
		    	// the figures will move the same amount of distance in 1 UPS or 300 UPS
		    	currentStep += Constants.NANO_SECOND;
		    	deltaTime += currentStep/timeUps;

		    	this.gameManager.tick(deltaTime);

			    if(currentStep >= UPS) {
			    	deltaTime = 0;
			    	currentStep = 0;
			    }
		    	
		    	this.deltaUps = 0;
		        //long elapsedTime = System.nanoTime() - startTime;

		        //System.out.println("Total execution time to create 1000K objects in Java in millis: " + elapsedTime/1000000);
		    }

	        // FPS
		    if(this.deltaFps >= 1) {
		    	this.gameManager.repaintGame();
		    	this.deltaFps = 0;
		    }
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		this.gameManager.isElementClicked(e.getPoint());
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
}
