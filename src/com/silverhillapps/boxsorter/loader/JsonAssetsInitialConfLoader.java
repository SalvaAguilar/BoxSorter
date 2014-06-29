package com.silverhillapps.boxsorter.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.graphics.Color;
import com.silverhillapps.boxsorter.BSApplication;
import com.silverhillapps.boxsorter.conf.Conf;
import com.silverhillapps.boxsorter.conf.Constants;
import com.silverhillapps.boxsorter.entities.Element;
import com.silverhillapps.boxsorter.entities.InitialPositionConfig;
import com.silverhillapps.boxsorter.utils.Utils;


/**
 * This is the json loader from assets. This reads the json file and generates the InitialPositionConfig object which stores the information of the initial elements.
 * @author salva
 *
 */
public class JsonAssetsInitialConfLoader extends InitialConfLoader {

	// Variables for adding random element and for avoiding that these objects overlaps over the static elements in ui
	private int maxWidth;
	private int maxHeight;
	private int correctionFactor;

	public JsonAssetsInitialConfLoader(){
		maxWidth = BSApplication.getmScreenWidth()-Conf.INITIAL_DIMENSION_ELEMENTS;
		correctionFactor = BSApplication.getmScreenHeight()*Conf.INITIAL_FREE_BOTTOM_SPACE_PERCENTAGE_PER_RANDOM_CREATOR/100;
		maxHeight = BSApplication.getmScreenHeight()-Conf.INITIAL_DIMENSION_ELEMENTS-correctionFactor;
	}


	/**
	 * This method load the initial elements information
	 * @return returns the positions of all the initial elements to be rendered
	 */
	@Override
	public InitialPositionConfig loadElements() {

		return parse();
	}


/**
 * This class is called for creating new elements in the canvas.
 * @return Returns the new created element
 */
	@Override
	public Element addElement() {
		return getRandomElement();
	}
	
	
/**
 * Internal method for reading the json file located in assets.
 * @return the json contents
 */
	private String loadJSONFromAsset() {
		String json = null;
		try {

			InputStream is = BSApplication.getAppContext().getAssets().open("initial.json"); // this name should be taken from constants classes or from properties.

			int size = is.available();

			byte[] buffer = new byte[size];

			is.read(buffer);

			is.close();

			json = new String(buffer, "UTF-8");


		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;

	}

	/**
	 * This method parses the json contents and creates the structure of the initial elements
	 * @return The initial elements
	 */
	private InitialPositionConfig parse() {
		//This parsing usually can be made directly using libraries like Gson.
		InitialPositionConfig ipc = new InitialPositionConfig();
		Element e = null;

		JSONObject obj;
		try {
			String json = loadJSONFromAsset();
			obj = new JSONObject(json);

			JSONArray squaresArray = obj.getJSONArray("squares"); // all these tags from json attributes should be taken from constant classes or from properties. 

			for (int i = 0; i < squaresArray.length(); i++) 
			{
				e = new Element();
				JSONObject square = squaresArray.getJSONObject(i);


				int xpos = square.getInt("x");
				int ypos = square.getInt("y");
				String color = square.getString("colour");
				int colorInt = Color.parseColor(color);

				int size = square.getInt("size");

				e.setFigureCode(Conf.INITIAL_FIGURE_CODE);
				e.setColour(colorInt);

				e.setSize(size);
				e.setxPos(xpos);
				e.setyPos(ypos);

				ipc.addElement(e);

			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		return ipc;
	}

	/**
	 * Method for creating random element
	 * @return the random element
	 */
	private Element getRandomElement(){
		Element newElelement = new Element();
		Random r = new Random();

		int xPos = r.nextInt(maxWidth);
		int yPos = r.nextInt(maxHeight);

		newElelement.setColour(Utils.randomColor());
		newElelement.setSize(Conf.INITIAL_DIMENSION_ELEMENTS*2);
		newElelement.setxPos(xPos);
		newElelement.setyPos(yPos);
		newElelement.setFigureCode(Conf.INITIAL_FIGURE_CODE);

		return newElelement;

	}


}
