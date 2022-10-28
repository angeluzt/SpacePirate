package weapons;

import java.awt.Graphics;
import java.awt.Point;

/**
 * Code based on code from user: Fluidic Ice
 * Reference: https://stackoverflow.com/questions/45675018/shooting-inaccurate-bullets-due-to-x-y-stepping
 * @author angel
 *
 */
public class Bullet {
    private double x, y;
    private int speed;
    private double directionAngle;
    private int length = 10;

    public Bullet(double x, double y, double directionAngle, int speed)
    {
    	this.x = x;
    	this.y = y;
        this.directionAngle = directionAngle; //Set the direction.
        this.speed = speed;
    }

    public void update(double dt)
    {
    	// Divide the speed between the delta time(each dt represents a portion of game time, something like delta seconds)
    	x += lengthdir_x(speed/dt, directionAngle);
        y += lengthdir_y(speed/dt, directionAngle);
    }
    
    public void paintBullet(Graphics g) {

    	g.fillOval((int) x, (int) y, length, length);
    }
    
    public double lengthdir_x(double len, double dir)
    {
        return len * Math.cos(Math.toRadians(dir - 90));
    }
    
    public double lengthdir_y(double len, double dir)
    {
    	return len * Math.sin(Math.toRadians(dir - 90));
    }
}
