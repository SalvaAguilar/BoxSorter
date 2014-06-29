package com.silverhillapps.boxsorter.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores all the information needed for making the initial render over the canvas.
 * @author salva
 *
 */
public class InitialPositionConfig {

	private List<Element> elements;

	public InitialPositionConfig(){
		this.elements = new ArrayList<Element>();
	}
	
	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}
	
	public void addElement(Element e){
		this.elements.add(e);
	}
	
	public int getSize(){
		return this.elements.size();
	}
	
}
