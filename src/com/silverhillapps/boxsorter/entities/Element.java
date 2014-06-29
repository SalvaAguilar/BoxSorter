package com.silverhillapps.boxsorter.entities;

/**
 * Entity class for determining a initial element to be rendered in the canvas
 * @author salva
 *
 */
public class Element {

	private int xPos;
	private int yPos;
	private int colour;
	private int size;
	private int figureCode;
	
	
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public int getColour() {
		return colour;
	}
	public void setColour(int colour) {
		this.colour = colour;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getFigureCode() {
		return figureCode;
	}
	public void setFigureCode(int figureCode) {
		this.figureCode = figureCode;
	}
	
	
	
}
