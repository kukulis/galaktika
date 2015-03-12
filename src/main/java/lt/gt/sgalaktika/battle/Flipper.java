package lt.gt.sgalaktika.battle;

import java.util.Random;

public class Flipper {
	
	private static Random random = new Random ();

	public static boolean yes ( double probability ) {
		 return Math.random() <= probability;
	}
	
	public static int randomInt ( int max ) {
		return random.nextInt(max);
	}
}
