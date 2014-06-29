package com.silverhillapps.boxsorter.conf;

import com.silverhillapps.boxsorter.BSApplication;


/**
 * This class stores the configuration constants of the application
 * @author salva
 *
 */
public class Conf {
	
	public static final int INITIAL_COLOUR = 0x000000; //Initial color for initialization purposes
	public static final int INITIAL_CIRCLE_RADIUS = 100; //Initial radius of the suck in figure
	
	public static final int INITIAL_FREE_BOTTOM_SPACE_PERCENTAGE_PER_RANDOM_CREATOR = 30; // Margin for not overlapping generated figures over elements in the ui
	
	public static final int INITIAL_ELEMENTS = 3; //Initial elements for being displayed in random loader case
	public static final int INITIAL_DIMENSION_ELEMENTS = 50; // Initial dimension for the created figures.
	public static final int INITIAL_FIGURE_CODE = Constants.SQUARE_CODE; // Initial figure code for the created figures.
	
	// Constants for determining the circle suck in figure in screen
	public static final int CIRCLE_CENTER_X_POS = INITIAL_CIRCLE_RADIUS*3/2;
	public static final int CIRCLE_CENTER_Y_POS = BSApplication.getmScreenHeight()-(INITIAL_CIRCLE_RADIUS*2);
	public static final int CIRCLE_STARTING_X_POS = CIRCLE_CENTER_X_POS-INITIAL_CIRCLE_RADIUS;
	public static final int CIRCLE_STARTING_Y_POS = CIRCLE_CENTER_Y_POS-INITIAL_CIRCLE_RADIUS;
	
	// Constants for limiting the scaling factor
	public static final int MINIMUM_DIMENSION_ELEMENTS = 50;
	public static final int MAXIMUM_DIMENSION_ELEMENTS = 400;
	
	// Animation time constraints
	public static final long PRIMARY_ANIMATION_TIME = 500;
	public static final long SECONDARY_ANIMATION_TIME = 400;
}
