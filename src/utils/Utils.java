package utils;

import java.util.Random;

public class Utils {

	public static int getRandomNumber(int min, int max) {
		Random rand = new Random();

		// nextInt as provided by Random is exclusive of the top value so you need to add 1 
		int randomNumber = rand.nextInt((max - min) + 1) + min;
		//System.out.println(randomNumber);

		return randomNumber;
	}
}
