/**
 * 
 */
package com.fastprac.sees.model.status;

import java.awt.Color;

/**
 * @author Admin
 *
 */
public enum PersonStatus {
	SAFE(Color.BLUE),
	INJURED(Color.PINK),
	DEAD(Color.GRAY),
	ATTACKING(Color.RED);
	
	private Color color;
	
	private PersonStatus(Color color) {
		this.color = color;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	
}
