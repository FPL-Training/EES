/*
 * *********************************************************************************
 * Copyright (c) Fast & Practical Learning LLC (F&PL), 2017
 *
 * This unpublished material is proprietary to Fast & Practical Learning, LLC (F&PL).
 * All rights reserved. The methods and techniques described herein are considered 
 * trade secrets and/or confidential. Reproduction or distribution, in whole or in 
 * part, is forbidden except by express written permission of Fast & Practical 
 * Learning, LLC (F&PL).
 ***********************************************************************************
 */
package com.fastprac.sees.model.tool;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fastprac.sees.model.Dimension;
import com.fastprac.sees.model.Location;
import com.fastprac.utils.lib.StdDraw;

/**
 * @author Admin
 *
 */
public class Panel {
	private final Location loc;
	private final Dimension dimension;
	private final Color fillColor;
	private final Color borderColor;
	
	private final Map<String, ToolItem> toolItems;
	
	/**
	 * 
	 */
	public Panel() {
		super();
		this.loc = new Location();
		this.dimension = new Dimension();
		this.toolItems = new HashMap<String, ToolItem>();
		this.fillColor = Color.WHITE;
		this.borderColor = Color.LIGHT_GRAY;
	}

	/**
	 * @param loc
	 * @param dimention
	 */
	public Panel(Location loc, Dimension dimension) {
		super();
		this.loc = loc;
		this.dimension = dimension;
		this.toolItems = new HashMap<String, ToolItem>();
		this.fillColor = Color.WHITE;
		this.borderColor = Color.LIGHT_GRAY;
	}

	public void addToolItem(ToolItem item) {
		toolItems.put(item.getName(), item);
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
	public Dimension getDimension() {
		return dimension;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return fillColor;
	}

	/**
	 * @return the toolItems
	 */
	public Map<String, ToolItem> getToolItems() {
		return toolItems;
	}
	
	public void draw() {
		
		int halfWidth = dimension.getWidth()/2;
		int halfHeight = dimension.getHeight()/2;
		int x = loc.getX() + halfWidth;
		int y = loc.getY() + halfHeight;
		
		StdDraw.setPenColor(fillColor);
		StdDraw.filledRectangle(x, y, halfWidth, halfHeight);

		StdDraw.setPenColor(borderColor);
		StdDraw.rectangle(x, y, halfWidth, halfHeight);
		
		for(Entry<String, ToolItem> entry : toolItems.entrySet()) {
			ToolItem item = entry.getValue();
			item.draw();
		}
	}

	public void addButton(int x, int y, int width, int height, ButtonType btnType) {
		String btnName = btnType.getLabel();
		ToolItem btn = new Button(btnType, new Location(x, y), new Dimension(width, height));
		toolItems.put(btnName, btn);
	}
	
	public ToolItem getButton(String btnName) {
		return toolItems.get(btnName);
	}
}
