package com.silverhillapps.boxsorter;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

/**
 * Application class for wrapping global runtime variables
 * @author salva
 *
 */
public class BSApplication extends Application {
	
	private static Context mContext; // Application context for assets loading 
	private static int mScreenWidth; // Screen dimensions 
	private static int mScreenHeight;

	public void onCreate(){
		super.onCreate();
		BSApplication.mContext = getApplicationContext();
		getScreenDimensions();

	}

	/**
	 * Method which returns the application context
	 * @return the application context
	 */
	public static Context getAppContext() {
		return BSApplication.mContext;
	}
	
	
	/**
	 * This method return the effective screen width of the window
	 * @return The calculated effective screen width in pixels
	 */
	public static int getmScreenWidth() {
		return mScreenWidth;
	}

	/**
	 * This method return the effective screen height of the window
	 * @return The calculated effective screen height in pixels
	 */
	public static int getmScreenHeight() {
		return mScreenHeight;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void getScreenDimensions(){
		WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2){
			Point size = new Point();
			display.getSize(size);
			mScreenWidth = size.x;
			mScreenHeight = size.y;
		} else{
			mScreenWidth = display.getWidth();  
			mScreenHeight = display.getHeight();

		}
		mScreenHeight = mScreenHeight-actionBarHeight(); //we need to substract the action bar height to the screen dimensions

	}
	
	private int actionBarHeight(){
		int height = 0;
		TypedValue typeValue = new TypedValue();
		getTheme().resolveAttribute(android.R.attr.actionBarSize, typeValue, true);
		height = TypedValue.complexToDimensionPixelSize(typeValue.data,getResources().getDisplayMetrics());
		return height;
	}

}
