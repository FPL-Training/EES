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

import com.fastprac.sees.configuration.SimulationDefault;
import com.fastprac.sees.model.tool.DisplayText;
import com.fastprac.sees.model.tool.ToolPanel;
import com.fastprac.utils.lib.StdDraw;

public class Canvas {

	private int canvasWidth;
	private int canvasHeight;
	
	private ToolPanel toolPanel;
	private MonitorPanel resultPanel;
	private Campus campus;

	public Canvas() {
		this.canvasWidth = SimulationDefault.CANVAS_WIDTH;
		this.canvasHeight = SimulationDefault.CANVAS_HEIGHT;
		initialize();
	}

	public Canvas(int width, int height) {
		this.canvasWidth = width;
		this.canvasHeight = height;
		initialize();
	}

	private void initialize() {
		setCanvasScale();
		createToolPanel();
		createResultPanel();
	}

	private void setCanvasScale() {
		StdDraw.setCanvasSize(canvasWidth, canvasHeight);

		// Change scale from default [0 - 1.0] to [0 - width] and [0 - height].
		StdDraw.setXscale(0, canvasWidth);
		StdDraw.setYscale(0, canvasHeight);
	}

	private void createToolPanel() {
		toolPanel = new ToolPanel(new Location(0, (canvasHeight - 60)), new Dimension(canvasWidth, 40));
	}

	private void createResultPanel() {
		resultPanel = new MonitorPanel();
		
		// Time
		Location timeLoc = new Location(700, (canvasHeight - 100));
		Dimension timeDimension = new Dimension(100, 32);
		DisplayText time = new DisplayText(timeLoc, timeDimension, "Time", "");
		resultPanel.addItem("Time", time);

		// Total people

		// Dead

		// Uninjured

	}
	
	public void addCampus(int startX, int startY, int cellSize) {
		campus = new Campus(startX, startY, cellSize);
	}
	
	public int getCanvasWidth() {
		return canvasWidth;
	}

	public int getCanvasHeight() {
		return canvasHeight;
	}

	public ToolPanel getToolPanel() {
		return toolPanel;
	}

	public MonitorPanel getResultPanel() {
		return resultPanel;
	}

	public void draw() {
		toolPanel.draw();
		resultPanel.draw();
		campus.draw();
	}
}
