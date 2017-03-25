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
package com.fastprac.sees.task;

import com.fastprac.sees.model.Dimension;
import com.fastprac.sees.model.Location;
import com.fastprac.sees.model.Result;
import com.fastprac.sees.model.Results;
import com.fastprac.sees.model.tool.Button;
import com.fastprac.sees.model.tool.ButtonType;
import com.fastprac.sees.model.tool.Panel;
import com.fastprac.sees.model.tool.Toolbar;

public class Controller {

	private Panel toolPanel;
	private Toolbar toolbar;
	private Panel resultPanel;
	private static Controller controller;
	private static Results results;

	private Controller() {
		results = new Results();
	}

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}
	
	public void addToolPanel(int x, int y, int width, int height) {
		toolPanel = new Panel(new Location(x, y), new Dimension(width, height));
	}

	public void addToolbar() {
		if (toolPanel != null) {
			int panelWidth = toolPanel.getDimension().getWidth();
			int panelHeight = toolPanel.getDimension().getHeight();
			int panelX = toolPanel.getLoc().getX();
			int panelY = toolPanel.getLoc().getY();

			Button startBtn = createStartButton(panelX, panelY, panelWidth, panelHeight);
			Button stopBtn = createStopButton(panelX, panelY, panelWidth, panelHeight);
			Button resetBtn = createResetButton(panelX, panelY, panelWidth, panelHeight);

			toolbar = new Toolbar(startBtn, stopBtn, resetBtn);
			toolbar.disable();
		}
	}

	public Toolbar getToolbar() {
		return toolbar;
	}

	public void addResult(int simulId, Result result) {
		results.addResult(1, result);
	}
	
	public Results getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(Results results) {
		this.results = results;
	}

	private Button createStartButton(int panelX, int panelY, int panelWidth, int panelHeight) {
		int btnW = panelWidth / 20;
		int btnH = panelHeight * 3 / 5;
		int btnX = panelX + panelWidth * 4 / 5;
		int btnY = panelY + panelHeight / 5;
		Button button = new Button(ButtonType.START, new Location(btnX, btnY), new Dimension(btnW, btnH));
		return button;
	}

	private Button createStopButton(int panelX, int panelY, int panelWidth, int panelHeight) {
		int btnW = panelWidth / 20;
		int btnH = panelHeight * 3 / 5;
		int btnX = panelX + panelWidth * 4 / 5 + (btnW + 2);
		int btnY = panelY + panelHeight / 5;
		Button button = new Button(ButtonType.STOP, new Location(btnX, btnY), new Dimension(btnW, btnH));
		return button;
	}

	private Button createResetButton(int panelX, int panelY, int panelWidth, int panelHeight) {
		int btnW = panelWidth / 20;
		int btnH = panelHeight * 3 / 5;
		int btnX = panelX + panelWidth * 4 / 5 + (btnW + 2) * 2;
		int btnY = panelY + panelHeight / 5;
		Button button = new Button(ButtonType.RESET, new Location(btnX, btnY), new Dimension(btnW, btnH));
		return button;
	}
	
	public void draw() {
		toolPanel.draw();
		toolbar.draw();
	}
}
