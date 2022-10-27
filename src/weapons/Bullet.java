package weapons;

import java.awt.Graphics;

/**
 * Code based on code from user: Fluidic Ice
 * Reference: https://stackoverflow.com/questions/45675018/shooting-inaccurate-bullets-due-to-x-y-stepping
 * @author angel
 *
 */
public class Bullet {
    private double x;
    private double y;
    private int speed;
    private int direction;
    private int length = 10;

    public Bullet(double x, double y, int direction, int speed)
    {
        this.x = x;
        this.y = y;
        this.direction = direction; //Set the direction.
        this.speed = speed;
    }

    public void update(double dt)
    {
    	// Divide the speed between the delta time(each dt represents a portion of game time, something like delta seconds)
        x += lengthdir_x(speed/dt, direction);
        y += lengthdir_y(speed/dt, direction);
        
        //System.out.println("X: " + x + ", Y: " + y);
    }
    
    public void paintBullet(Graphics g) {
    	g.fillOval((int)x, (int)y, length, length);
    }
    
    public double lengthdir_x(double len, int dir)
    {
        return len * Math.cos(Math.toRadians(dir - 90));
    }
    
    public double lengthdir_y(double len, int dir)
    {
    	return len * Math.sin(Math.toRadians(dir - 90));
    }
}
