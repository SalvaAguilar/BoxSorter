package com.silverhillapps.boxsorter.gesture;

import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.View.OnTouchListener;

public class BSScaleDetector extends ScaleGestureDetector.SimpleOnScaleGestureListener {
	@Override
	public boolean onScale(ScaleGestureDetector detector) {
//		mScaleSpan = detector.getCurrentSpan(); // average distance between fingers
		Log.i("scale", "scale pinch");
		return true;
	}
}
