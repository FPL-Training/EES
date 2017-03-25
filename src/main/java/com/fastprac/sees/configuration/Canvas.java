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
package com.fastprac.sees.configuration;

import com.fastprac.utils.lib.StdDraw;

public class Canvas {
	private final int canvasWidth;
	private final int canvasHeight;
	
	public Canvas() {
		this.canvasWidth = 800;
		this.canvasHeight = 550;
		setCanvasScale();
	}

	public Canvas(int width, int height) {
		this.canvasWidth = width;
		this.canvasHeight = height;
		setCanvasScale();
	}
	
	private void setCanvasScale() {
		StdDraw.setCanvasSize(canvasWidth, canvasHeight);

		// Change scale from the default of [0 - 1.0] to [0 - width] and [0 - height].
		StdDraw.setXscale(0, canvasWidth);
		StdDraw.setYscale(0, canvasHeight);
	}
}
