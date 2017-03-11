/**
 * 
 */
package com.fastprac.sees.model.tool;

import java.awt.Color;

/**
 * @author Admin
 *
 */
enum ButtonStatus implements ToolItemStatus {
	ON(Color.GREEN, Color.GREEN),
	OFF(Color.LIGHT_GRAY, Color.LIGHT_GRAY);
	
	private Color textColor;
	private Color borderColor;
	
	private ButtonStatus(Color fillColor, Color borderColor) {
		this.textColor = fillColor;
		this.borderColor = borderColor;
	}

	@Override
	public Color getTextColor() {
		return textColor;
	}

	@Override
	public Color getBorderColor() {
		return borderColor;
	}

	@Override
	public void setTextColor(Color color) {
		this.textColor = color;
	}

	@Override
	public void setBorderColor(Color color) {
		this.borderColor = color;
	}

	
}
