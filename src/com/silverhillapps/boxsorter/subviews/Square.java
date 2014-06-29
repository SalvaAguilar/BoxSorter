package com.silverhillapps.boxsorter.subviews;

import com.silverhillapps.boxsorter.conf.Conf;
import com.silverhillapps.boxsorter.utils.Utils;
import com.silverhillapps.boxsorter.views.BoxCanvasView;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * This is the class which represents the square figure
 * @author salva
 *
 */
public class Square extends View implements Figure {

	private Paint mFigurePaint;
	private int mXPos ;
	private int mYPos;
	private int mDimension;
	private Rect mRect;
	private Context mContext;
	private int mColor;

	private float tempXpos;
	private float tempYpos;

	private boolean deleted = false;
	private boolean scaling = false;

	private GestureDetector gestureDetector; // Gesture detector for fling and double tap
	private ScaleGestureDetector scaleDetector; // Gesture detector for scaling


	public Square(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		init();
		recalculateRect();
	}

	/**
	 * This method initializes the square with the paint object as well as the gesture detectors
	 */
	private void init() {
		mFigurePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mFigurePaint.setColor(Color.BLUE);
		mFigurePaint.setAntiAlias(true);

		gestureDetector = new GestureDetector(mContext, new BSDetector());
		scaleDetector = new ScaleGestureDetector(mContext, new ScaleListener());

	}

	/**
	 * The method used for drawing the figure in the canvas
	 */
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawRect(mRect , mFigurePaint);

	}

	/**
	 * Method for deleting the figure from canvas	
	 */
	@Override
	public void delete(boolean animate) {
		((BoxCanvasView)getParent()).removeElement(this);

	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		setMeasuredDimension(mDimension, mDimension);
	}

	/**
	 * This method calculates the rect of the figure  
	 */
	private void recalculateRect() {
		mRect = new Rect(0, 0, mDimension, mDimension);
	}

	/**
	 * Position setter
	 */
	@Override
	public void setPosition(int x, int y) {
		this.mXPos = x;
		this.mYPos = y;

		recalculateRect();
		invalidate();
	}

	/**
	 * color setter
	 */
	@Override
	public void setColor(int color) {
		this.mColor = color;
		mFigurePaint.setColor(color);
		invalidate();

	}

	/**
	 * Dimension setter	
	 */
	@Override
	public void setDimension(int dim) {
		this.mDimension = dim;

		this.invalidate();

	}

	/**
	 * Method for unwrapping the view from the figure structure
	 */
	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return this;
	}

	/*
	 * Position and dimension getters
	 */
	@Override
	public int getLeftPosition() {
		// TODO Auto-generated method stub
		return mXPos;
	}

	@Override
	public int getTopPosition() {
		// TODO Auto-generated method stub
		return mYPos;
	}

	@Override
	public int getSize() {
		return mDimension;
	}


	/**
	 * Method responsible of managing the touch events
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if(!deleted){
			manageMove(event); // manages the move 
			gestureDetector.onTouchEvent(event); // manages the double tap and fling
			scaleDetector.onTouchEvent(event); // manages the scale
			// The rotate was not developed, but it would be included as a new detector here 
		}

		return true;
	}

	/**
	 * Method responsible of managing the move interaction in ui
	 * @param event
	 */
	private void manageMove(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			tempXpos = (int)event.getRawX();
			tempYpos = (int)event.getRawY();
			scaling = false;
			break;
		case MotionEvent.ACTION_MOVE:
			if(!scaling){
				float deltax = event.getRawX()-tempXpos;
				float deltay = (event.getRawY()-tempYpos); 

				mXPos+=deltax;
				mYPos+= deltay;
				tempXpos = event.getRawX();
				tempYpos = event.getRawY();

				isInsideActionArea();
				
				this.invalidate();
				((ViewGroup)this.getParent()).requestLayout();
			}
			break;
		case MotionEvent.ACTION_UP:
			break;

		}
	}


	/**
	 * This method determines if the figure is located in the action area for being deleted
	 */
	private void isInsideActionArea() {

		int squareXCenter = mXPos+(mDimension/2);
		int squareYCenter = mYPos+(mDimension/2);
		int actionArea = mDimension;

		if((Math.pow(squareXCenter-Conf.CIRCLE_CENTER_X_POS,2) + Math.pow(squareYCenter-Conf.CIRCLE_CENTER_Y_POS,2))<(actionArea*actionArea)){
			deleted = true;
			delete(false);
		}


	}

	/*
	 * Delete getter and setters
	 * @see com.silverhillapps.boxsorter.subviews.Figure#isDeleted()
	 */
	@Override
	public boolean isDeleted() {
		return deleted;
	}

	@Override
	public void setDeleted(boolean deleted) {
		// TODO Auto-generated method stub
		this.deleted = deleted;
	}


	/**
	 * Private class for managing the scaling over the figure
	 * @author salva
	 *
	 */
	private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			float scale = detector.getScaleFactor();
			int newDimension = (int)(mDimension*scale);
			// Scaling restrictions
			newDimension = newDimension>Conf.MINIMUM_DIMENSION_ELEMENTS?newDimension:Conf.MINIMUM_DIMENSION_ELEMENTS;
			newDimension = newDimension<Conf.MAXIMUM_DIMENSION_ELEMENTS?newDimension:Conf.MAXIMUM_DIMENSION_ELEMENTS;

			mDimension = newDimension;
			
			//Update ui
			recalculateRect();
			scaling = true;
			invalidate();
			((ViewGroup)getParent()).requestLayout();

			return true;
		}

	}

	/**
	 * Class for managing the fling and double tap
	 * @author salva
	 *
	 */
	private class BSDetector extends GestureDetector.SimpleOnGestureListener{
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			
			//The fling is not developed, but it takes the velocity and direction vector here and send it to parent canvas for creating the animation
			return true;

		}


		/**
		 * Double tap 
		 */
		@Override
		public boolean onDoubleTap(MotionEvent e) {

			setColor(Utils.randomColor());

			return true;
		}

	}


}
