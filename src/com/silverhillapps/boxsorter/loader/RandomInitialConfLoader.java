package com.silverhillapps.boxsorter.loader;

import java.util.Random;
import com.silverhillapps.boxsorter.BSApplication;
import com.silverhillapps.boxsorter.conf.Conf;
import com.silverhillapps.boxsorter.entities.Element;
import com.silverhillapps.boxsorter.entities.InitialPositionConfig;
import com.silverhillapps.boxsorter.utils.Utils;

public class RandomInitialConfLoader extends InitialConfLoader {

	// Constants for the correct display of the initial random elements over the canvas
	private int maxWidth;
	private int maxHeight;
	private int correctionFactor;
	
	public RandomInitialConfLoader(){
		maxWidth = BSApplication.getmScreenWidth()-Conf.INITIAL_DIMENSION_ELEMENTS;
		correctionFactor = BSApplication.getmScreenHeight()*Conf.INITIAL_FREE_BOTTOM_SPACE_PERCENTAGE_PER_RANDOM_CREATOR/100;
		maxHeight = BSApplication.getmScreenHeight()-Conf.INITIAL_DIMENSION_ELEMENTS-correctionFactor;
	}
	
	/**
	 * Method for creating the initial elements
	 */
	@Override
	public InitialPositionConfig loadElements() {
		
		InitialPositionConfig positions = new InitialPositionConfig();
		Element newElement;
		
		int numElements = 0;
		do{
			newElement = getRandomElement();
			
			positions.addElement(newElement);
			numElements++;
		}while(numElements<Conf.INITIAL_ELEMENTS);
		
		return positions;
	}

	
	/**
	 * Method used for creating a new random element
	 * @return the random element
	 */
	private Element getRandomElement(){
		Element newElelement = new Element();
		Random r = new Random();
		
		int xPos = r.nextInt(maxWidth);
		int yPos = r.nextInt(maxHeight);
		
		newElelement.setColour(Utils.randomColor());
		newElelement.setSize(Conf.INITIAL_DIMENSION_ELEMENTS*2);
		newElelement.setxPos(xPos);
		newElelement.setyPos(yPos);
		newElelement.setFigureCode(Conf.INITIAL_FIGURE_CODE);
		
		return newElelement;
		
	}

	/**
	 * public method for adding new elements
	 */
	@Override
	public Element addElement() {
		return getRandomElement();
	}
}
