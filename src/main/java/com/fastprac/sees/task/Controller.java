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

import java.util.Date;

import com.fastprac.sees.model.Canvas;
import com.fastprac.sees.model.Result;
import com.fastprac.sees.model.MonitorPanel;
import com.fastprac.sees.model.Results;
import com.fastprac.sees.model.tool.Button;
import com.fastprac.sees.model.tool.Toolbar;
import com.fastprac.utils.lib.StdDraw;

public class Controller {

	private Canvas canvas;

	private long startTime;
	private long timeElapsed;

	private Results results;

	public Controller() {
		startTime = 0;
		timeElapsed = 0;

		results = new Results();
	}

	public Toolbar getToolbar() {
		return canvas.getToolPanel().getToolbar();
	}

	public MonitorPanel getResultPanel() {
		return canvas.getResultPanel();
	}

	public void addResult(int simulId, Result result) {
		this.results.addResult(1, result);
	}

	public Results getResults() {
		return this.results;
	}

	public void setResults(Results results) {
		this.results = results;
	}

	public Button getActiveButton() {
		Button activeBtn = null;
		Toolbar toolbar = getToolbar();
		if (StdDraw.mousePressed()) {
			int x = (int) StdDraw.mouseX();
			int y = (int) StdDraw.mouseY();
			toolbar.toggle(x, y);
		}

		if (toolbar.getStartBtn().isPressed()) {
			activeBtn = toolbar.getStartBtn();
		} else if (toolbar.getStopBtn().isPressed()) {
			activeBtn = toolbar.getStopBtn();
		} else if (toolbar.getResetBtn().isPressed()) {
			activeBtn = toolbar.getResetBtn();
		}
		return activeBtn;
	}

	public void startSimulation() {
		startTime = (new Date()).getTime();
		timeElapsed = 0L;
	}

	public void updateMonitor() {
		updateTimeElapsed();
		updateAttacked();
		updateUnattacked();
	}

	public void updateTimeElapsed() {
		long currentTime = (new Date()).getTime();
		if (getToolbar().getStartBtn().isPressed()) {
			timeElapsed = currentTime - startTime;
			canvas.getResultPanel().updateTime("Time", timeElapsed);
		} else {
			startTime = currentTime - timeElapsed;
		}
	}

	public void updateAttacked() {

	}

	public void updateUnattacked() {

	}

	public void addCanvas(int width, int height) {
		addCanvas(width, height, false);
	}

	public void addCanvas(int width, int height, Boolean reset) {
		if (canvas == null || reset) {
			canvas = new Canvas(width, height);
		}
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public long getStartTime() {
		return startTime;
	}

	public long getTimeElapsed() {
		return timeElapsed;
	}

}
