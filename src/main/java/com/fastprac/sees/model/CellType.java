/**
 * 
 */
package com.fastprac.sees.model;

import java.awt.Color;

/**
 * @author Admin
 *
 */
public enum CellType {
	OUTER_AREA(Color.WHITE, true),
	DOOR(Color.YELLOW, true),
	CLASSROOM(Color.CYAN, true), 
	WALL(Color.BLUE, false),
	TREE(Color.GREEN, false);
	
	private Color color;
	private boolean accessible;
	
	private CellType(Color color, boolean accessible) {
		this.color = color;
		this.accessible = accessible;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public boolean isAccessible() {
		return this.accessible;
	}
}
