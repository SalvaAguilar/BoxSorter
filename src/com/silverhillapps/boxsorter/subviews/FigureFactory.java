package com.silverhillapps.boxsorter.subviews;

import android.content.Context;

import com.silverhillapps.boxsorter.conf.Constants;

/**
 * This is the factory creator for the Figures
 * @author salva
 *
 */
public class FigureFactory {

	private Context mContext;
	
	public FigureFactory(Context context){
		this.mContext = context;
	}
	
	public Figure createFigure(int code){
		Figure out = null;
		
		switch(code){
		case Constants.CIRCLE_CODE:
			out = new Circle(mContext, null);
			break;
		case Constants.SQUARE_CODE:
			out = new Square(mContext, null);
			break;
		}
		
		return out; 
	}
}
