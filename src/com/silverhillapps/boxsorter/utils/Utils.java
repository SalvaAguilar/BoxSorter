package com.silverhillapps.boxsorter.utils;

import java.util.Random;
import android.graphics.Color;

/**
 * Class with utils for the application
 * @author salva
 *
 */
public class Utils {

	/**
	 * THis method generates a random color
	 * @return
	 */
	public static int randomColor(){
		Random rnd = new Random();
		int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
	
		return color;
	}


	
}
