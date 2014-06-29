package com.silverhillapps.boxsorter.loader;

import com.silverhillapps.boxsorter.conf.Constants;

/**
 * This is the factory of loaders that is called from the primary controller.
 * @author salva
 *
 */
public class LoaderFactory {
 
	public InitialConfLoader getLoader(int loaderCode){
		InitialConfLoader loader = null;
		
		switch(loaderCode){
		case Constants.RANDOM_LOADER_CODE:
			loader = new RandomInitialConfLoader();
			break;
		case Constants.JSON_LOADER_CODE:
			loader = new JsonAssetsInitialConfLoader();
			break;
		}
		
		return loader;
	}
	
}
