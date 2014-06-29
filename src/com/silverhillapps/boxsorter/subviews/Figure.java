package com.silverhillapps.boxsorter.subviews;

import android.view.View;
import android.widget.ViewSwitcher.ViewFactory;

import com.silverhillapps.boxsorter.conf.Conf;
import com.silverhillapps.boxsorter.conf.Constants;

public interface Figure {

	public int code = Constants.VOID_CODE;
	public int colour = Conf.INITIAL_COLOUR;
	
	public void delete(boolean animate);
	public void setPosition(int x, int y);
	public void setDimension(int dim);
	public void setColor(int color);
	public View getView();
	
	public int getLeftPosition();
	public int getTopPosition();
	public int getSize();
	
	public boolean isDeleted();
	public void setDeleted(boolean deleted);

}
