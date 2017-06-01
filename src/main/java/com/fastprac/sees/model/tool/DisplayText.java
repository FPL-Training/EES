package com.fastprac.sees.model.tool;

import java.awt.Color;

import com.fastprac.sees.model.Dimension;
import com.fastprac.sees.model.Location;
import com.fastprac.utils.lib.StdDraw;

public class DisplayText {
	private Location loc;
	private Dimension dimension;
	private String label;
	private String text;
	private int textX;
	
	public DisplayText() {
		
	}

	/**
	 * @param loc
	 * @param dimension
	 * @param label
	 * @param text
	 */
	public DisplayText(Location loc, Dimension dimension, String label, String text) {
		super();
		this.loc = loc;
		this.dimension = dimension;
		this.label = label;
		this.text = text;
		this.textX = loc.getX() + dimension.getWidth()/2;
	}

	/**
	 * @return the loc
	 */
	public Location getLoc() {
		return loc;
	}

	/**
	 * @return the dimention
	 */
	public Dimension getDimention() {
		return dimension;
	}

	
	/**
	 * @return the dimension
	 */
	public Dimension getDimension() {
		return dimension;
	}

	/**
	 * @param dimension the dimension to set
	 */
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		if (text!= null && !text.equalsIgnoreCase(this.text)) {
			clearText();
			this.text = text;
		}
	}

	/**
	 * @param loc the loc to set
	 */
	public void setLoc(Location loc) {
		this.loc = loc;
	}

	public void draw() {
		drawLabel();
		drawText();
	}
	
	public void clear() {
		clearLabel();
		clearText();
	}
	
	public void drawLabel() {
		StdDraw.setPenColor(Color.GRAY);
		StdDraw.text(loc.getX(), loc.getY(), label + ": ");
	}
	
	public void drawText() {
		StdDraw.setPenColor(Color.GRAY);
		StdDraw.text(textX, loc.getY(), text);
	}
	
	public void clearLabel() {
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.text(loc.getX(), loc.getY(), label + ": ");	
	}
	
	public void clearText() {	
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.text(textX, loc.getY(), text);
	}
}
