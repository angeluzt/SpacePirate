package utils;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import gameobjects.matrix.WindowRow;
import gameobjects.matrix.WindowSquare;

public class Utils {

	public static int getRandomNumber(int min, int max) {
		Random rand = new Random();

		// nextInt as provided by Random is exclusive of the top value so you need to add 1 
		int randomNumber = rand.nextInt((max - min) + 1) + min;
		//System.out.println(randomNumber);

		return randomNumber;
	}
	
	public static void drawGridSystem(Graphics g, WindowSquare currentSquare) {
		g.setColor(Color.YELLOW);
		if(currentSquare != null && currentSquare.getRows().size() > 0) {
			for (WindowRow row: currentSquare.getRows()) {
				for (WindowSquare square : row.getSquares()) {
					g.drawRect((int) square.getPoint().getX(), (int)square.getPoint().getY(), square.getSize().width, square.getSize().height);
					
					if(square != null && square.getRows().size() > 0) {
						drawGridSystem(g, square);
					}
				}
			}
		}
	}
}
