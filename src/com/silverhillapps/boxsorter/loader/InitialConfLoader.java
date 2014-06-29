package com.silverhillapps.boxsorter.loader;

import com.silverhillapps.boxsorter.entities.Element;
import com.silverhillapps.boxsorter.entities.InitialPositionConfig;

/**
 * This abstract class determines the structure of a loader
 * @author salva
 *
 */
public abstract class InitialConfLoader {

	public abstract InitialPositionConfig loadElements();
	
	public abstract Element addElement();
}
