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

/**
 * @author Admin
 *
 */
public enum ButtonStatus implements ToolItemStatus {
	ON(Color.BLUE, Color.CYAN),
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
