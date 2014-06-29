package com.silverhillapps.boxsorter.views;

import java.util.ArrayList;
import java.util.List;
import com.silverhillapps.boxsorter.R;
import com.silverhillapps.boxsorter.conf.Conf;
import com.silverhillapps.boxsorter.conf.Constants;
import com.silverhillapps.boxsorter.entities.Element;
import com.silverhillapps.boxsorter.entities.InitialPositionConfig;
import com.silverhillapps.boxsorter.subviews.Circle;
import com.silverhillapps.boxsorter.subviews.Figure;
import com.silverhillapps.boxsorter.subviews.FigureFactory;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

/**
 * This class is the canvas of the application
 * @author salva
 *
 */
public class BoxCanvasView extends ViewGroup {

	private int sourceElements; //This variable is taken from the layout and determines the source for the loader
	private Context mContext;

	private List<Figure> mFigures; //The figures displayed in the canvas
	private FigureFactory mFf; // This factory should be injected instead of manually created
	private Figure mCircle; //The suck in figure
	
	
	public BoxCanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.mContext = context;
		this.mFf = new FigureFactory(context);
		this.mFigures = new ArrayList<Figure>();
		init(attrs);

	}

	public BoxCanvasView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		this.mFf = new FigureFactory(context);
		this.mFigures = new ArrayList<>();
		init(attrs);
	}

	/**
	 * Method for adding the initial elements into the canvas
	 * @param positions these are the initial elements
	 */
	public void addInitialValues(InitialPositionConfig positions){

		//Create the suck in figure
		mCircle = mFf.createFigure(Constants.CIRCLE_CODE); 
		this.addView(mCircle.getView());
		this.invalidate();
		mCircle.getView().invalidate();
		
		//We create the rest of the initial elements
		placeInitialElements(positions);
	}

/*
 * This method is responsible of creating the initial elements from the loaded structure created with the loader
 */
	private void placeInitialElements(InitialPositionConfig positions) {
		Element l = null;
		for(int i = 0; i<positions.getSize();i++){
			l = positions.getElements().get(i);

			Figure f = mFf.createFigure(l.getFigureCode());
			f.setDimension(l.getSize());
			f.setColor(l.getColour());
			f.setPosition(l.getxPos(), l.getyPos());

			mFigures.add(f);
			this.addView(f.getView());
		}

	}	


	/**
	 * This method displays a new element
	 * @param l the new Element
	 */
	public void addNewElement(Element l){

		Figure f = mFf.createFigure(l.getFigureCode());
		f.setDimension(l.getSize());
		f.setPosition(l.getxPos(), l.getyPos());
		f.setColor(l.getColour());
		mFigures.add(f);
		
		this.addView(f.getView());
		invalidate();
	}

/*
 * This method extracts the parameter of the origin of the loader from the layout
 */
	private void init(AttributeSet attrs) { 
		TypedArray a=getContext().obtainStyledAttributes(
				attrs,
				R.styleable.BoxCanvasView);

		Log.i("test","Origin: " + a.getInt(R.styleable.BoxCanvasView_origin, 0));
		this.sourceElements = a.getInt(R.styleable.BoxCanvasView_origin, 0);
		a.recycle();
	}

	/**
	 * This method generates the layout for all the figures in the canvas
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		
		for(int i=0; i< this.getChildCount(); i++){
			Figure v  = (Figure)getChildAt(i);

			v.getView().layout(v.getLeftPosition(), v.getTopPosition(), v.getLeftPosition()+v.getSize(), v.getTopPosition()+v.getSize()); 
		}

	}

	/**
	 * This method removes an element from the canvas
	 * @param f the figure to be removed
	 */
	public void removeElement(final Figure f){

		long timeTranslate = Conf.PRIMARY_ANIMATION_TIME;
		long timeSuckIn = Conf.SECONDARY_ANIMATION_TIME;

		ObjectAnimator translateX = ObjectAnimator.ofFloat(f, "translationX",Conf.CIRCLE_CENTER_X_POS-(f.getLeftPosition()+(f.getSize()/2)));
		translateX.setDuration(timeTranslate);
		ObjectAnimator translateY = ObjectAnimator.ofFloat(f, "translationY",Conf.CIRCLE_CENTER_Y_POS-(f.getTopPosition()+(f.getSize()/2)));
		translateY.setDuration(timeTranslate);
		ObjectAnimator fadeOut = ObjectAnimator.ofFloat(f, "alpha", 0f);
		fadeOut.setDuration(timeSuckIn);
		fadeOut.setStartDelay(timeTranslate);

		ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(f, "scaleX", 0f);
		ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(f, "scaleY", 0f);

		scaleDownX.setStartDelay(timeTranslate);
		scaleDownY.setStartDelay(timeTranslate);
		scaleDownX.setDuration(timeSuckIn);
		scaleDownY.setDuration(timeSuckIn);

		ObjectAnimator rotate = ObjectAnimator.ofFloat(f, "rotation", 0f,180f);
		rotate.setDuration(timeSuckIn);
		rotate.setStartDelay(timeTranslate);

		AnimatorSet animatorSet = new AnimatorSet();

		animatorSet.addListener(new AnimatorListenerAdapter() {
			public void onAnimationEnd(Animator animation) {
				mFigures.remove(f);
				removeView(f.getView());
				invalidate();
			}
		});

		animatorSet.play(translateX).with(translateY).with(fadeOut).with(scaleDownY).with(scaleDownX).with(rotate);
		animatorSet.start();

	}

	/**
	 * This method removes all the elements displayed in the canvas
	 */
	public void removeAllElements(){	
		for(int i = 0; i < mFigures.size(); i++){

			mFigures.get(i).delete(true);
		}

	}
	
	/**
	 * Source elements getter
	 * @return This is the source of the getter
	 */
	public int getSourceElements(){
		return sourceElements;
	}
}
