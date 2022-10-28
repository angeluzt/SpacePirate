package utils;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import gameobjects.matrix.WindowMatrix;
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
	
	public static void drawGridSystem(Graphics g, WindowMatrix matrix) {
		if(matrix != null && matrix.getMatrix().size() > 0) {
			g.setColor(Color.GREEN);
			for (WindowRow row: matrix.getMatrix()) {
				for (WindowSquare square : row.getSquares()) {
					g.drawRect((int) square.getPoint().getX(), (int)square.getPoint().getY(), square.getSize().width, square.getSize().height);
					
					if(square.getMatrix() != null) {
						drawGridSystem(g, square.getMatrix());
					}
				}
			}
		}
	}
}
