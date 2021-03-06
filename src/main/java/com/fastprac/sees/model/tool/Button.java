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

import java.awt.Font;

import com.fastprac.sees.model.Dimension;
import com.fastprac.sees.model.Location;
import com.fastprac.utils.lib.StdDraw;

/**
 * @author Admin
 *
 */
public class Button extends ToolItem {
	private ButtonType type;
	private Dimension dimension;
	private ButtonStatus status;

	public Button() {
		super();
		this.dimension = new Dimension();
		this.status = ButtonStatus.ON;
	}

	public Button(ButtonType type, Location loc, Dimension dimension) {
		super(type.getLabel(), type.getLabel(), loc);
		this.type = type;
		this.dimension = dimension;
		this.status = ButtonStatus.ON;
	}

	public Button(String name, String text, Location loc, Dimension dimension) {
		super(name, text, loc);
		this.dimension = dimension;
		this.status = ButtonStatus.ON;
	}

	public ButtonType getType() {
		return type;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public ButtonStatus getStatus() {
		return status;
	}

	public void setStatus(ButtonStatus status) {
		this.status = status;
	}

	@Override
	public void draw() {
		int halfWidth = dimension.getWidth() / 2;
		int halfHeight = dimension.getHeight() / 2;
		int x = loc.getX() + halfWidth;
		int y = loc.getY() + halfHeight;

		StdDraw.setPenColor(status.getBorderColor());
		StdDraw.rectangle(x, y, halfWidth, halfHeight);

		StdDraw.setPenColor(status.getTextColor());
		Font font = new Font("Arial", Font.ITALIC, 12);
		StdDraw.setFont(font);
		StdDraw.text(x, y, text);
	}

	public Boolean pointOn(int x, int y) {
		int btnX = loc.getX();
		int btnY = loc.getY();
		return (x > btnX && x < (btnX + dimension.getWidth()) && y > btnY && y < (btnY + dimension.getHeight()));
	}

	@Override
	public void enable() {
		status = ButtonStatus.ON;
		draw();
	}

	@Override
	public void disable() {
		status = ButtonStatus.OFF;
		draw();
	}

	public boolean isReleased() {
		return (status == ButtonStatus.ON);
	}

	public boolean isPressed() {
		return (status == ButtonStatus.OFF);
	}
}
