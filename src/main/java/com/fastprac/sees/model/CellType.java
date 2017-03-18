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
