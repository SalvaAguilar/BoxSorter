package com.silverhillapps.boxsorter.gesture;

import com.silverhillapps.boxsorter.conf.Conf;
import com.silverhillapps.boxsorter.subviews.Figure;
import com.silverhillapps.boxsorter.utils.Utils;

import android.util.Log;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class BSGestureDetector extends GestureDetector.SimpleOnGestureListener{
	
	private Figure mfigure;
	private float tempXpos;
	private float tempYpos;
	
	public BSGestureDetector(Figure f){
		this.mfigure = f;
	}
	

@Override
public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
		float velocityY) {
	// TODO Auto-generated method stub
	Log.i("sa", "Fly away");
	return super.onFling(e1, e2, velocityX, velocityY);
}

	// event when double tap occurs
	@Override
	public boolean onDoubleTap(MotionEvent e) {

		
		mfigure.setColor(Utils.randomColor());

		return true;
	}
	


	
}
