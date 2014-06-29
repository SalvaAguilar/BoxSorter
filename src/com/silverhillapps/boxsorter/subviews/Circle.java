package com.silverhillapps.boxsorter.subviews;


import com.silverhillapps.boxsorter.conf.Conf;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * This class represents the view of the suck in figure. 
 * @author salva
 *
 */
public class Circle extends View implements Figure {

	private Paint mFigurePaint; // The paint object created for painting over the canvas
	//Initial position and dimension of the figure
	private int mXPos=Conf.CIRCLE_STARTING_X_POS;
	private int mYPos= Conf.CIRCLE_STARTING_Y_POS;
	private int mDimension=Conf.INITIAL_CIRCLE_RADIUS;

	private Context mContext;

	public Circle(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		this.mContext = context;
	}

	/**
	 * Method oriented for setting the displaying parameters
	 */
	private void init() {
		mFigurePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mFigurePaint.setColor(Color.BLACK);
		mFigurePaint.setAntiAlias(true);

	}

	/**
	 * Method used for deleting the figure
	 */
	@Override
	public void delete(boolean animate) {
		// Not used over this figure

	}

	/**
	 * Method which draws the figure over the canvas
	 */
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawCircle(mDimension, mDimension, mDimension, mFigurePaint);
	}

	/**
	 * Position setter
	 */
	@Override
	public void setPosition(int x, int y) {
		this.mXPos = x;
		this.mYPos = y;

		this.invalidate();
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
	 * Color setter
	 */
	@Override
	public void setColor(int color) {
		//Not needed for this figure

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		setMeasuredDimension(mDimension*2, mDimension*2);
	}


	/**
	 * Method responsible of unwrapping the view from Figure structure
	 */
	@Override
	public View getView() {
		return this;
	}

	/**
	 * x position getter
	 */
	@Override
	public int getLeftPosition() {
		return mXPos;
	}

	/**
	 * y position getter
	 */
	@Override
	public int getTopPosition() {
		return mYPos;
	}

/**
 * Dimension getter
 */
	@Override
	public int getSize() {
		return mDimension*2;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// The touch behaviour is not detailed over this figure
		return true;
	}

	/**
	 * Delete getter and setter
	 */
	@Override
	public boolean isDeleted() {
		return false; // This figure cannot be deleted
	}
	@Override
	public void setDeleted(boolean deleted) {

		// This method is not needed in this figure
	}

}
