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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.fastprac.sees.configuration.SimulationConfig;
import com.fastprac.sees.model.Attacker;
import com.fastprac.sees.model.Campus;
import com.fastprac.sees.model.Canvas;
import com.fastprac.sees.model.MonitorPanel;
import com.fastprac.sees.model.Person;
import com.fastprac.sees.model.Result;
import com.fastprac.sees.model.Results;
import com.fastprac.sees.model.status.MomentStatus;
import com.fastprac.sees.model.status.PersonStatus;
import com.fastprac.sees.model.tool.Button;
import com.fastprac.sees.model.tool.Toolbar;
import com.fastprac.utils.lib.StdDraw;

public class Controller {

	private Canvas canvas;

	private long startTime;
	private long timeElapsed;
	private final List<Integer> attacked;	
	private final Attackers attackers;
	private final People people;

	private Results results;

	public Controller() {
		startTime = 0;
		timeElapsed = 0;
		attackers = new Attackers();
		people = new People();
		attacked = new ArrayList<Integer>();
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

	public boolean isStartActive() {
		return getToolbar().getStartBtn().isPressed();
	}

	public boolean isTopActive() {
		return getToolbar().getStopBtn().isPressed();
	}
	
	public void startTimer() {
		startTime = (new Date()).getTime();
		timeElapsed = 0L;
	}

	public void updateMonitor() {
		updateTimeElapsed();
		
		int numOfAttacked = attacked.size();
		int numOfUnattacked = people.getPeople().size() - numOfAttacked;
		canvas.getResultPanel().updateDisplayText("Attacked", numOfAttacked);
		canvas.getResultPanel().updateDisplayText("Unattacked", numOfUnattacked);
	}

	private void updateTimeElapsed() {
		long currentTime = (new Date()).getTime();
		if (getToolbar().getStartBtn().isPressed()) {
			timeElapsed = currentTime - startTime;
			canvas.getResultPanel().updateTime("Time", timeElapsed);
		} else {
			startTime = currentTime - timeElapsed;
		}
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

	public Attackers getAttackers() {
		return attackers;
	}

	public People getPeople() {
		return people;
	}

	public List<Integer> getAttacked() {
		return attacked;
	}

	public void addAttacked(Person person) {
		if (person.getStatus() == PersonStatus.DEAD ||
				person.getStatus() == PersonStatus.INJURED) {
			attacked.add(person.getId());
		}
	}

	public void addAttackers(SimulationConfig config) {
		int startId = config.getNumOfPeople() + 1;
		int num = config.getNumOfAttackers();
		int duration = config.getDuration();
		int speed = 1;
		int size = 10;
		attackers.add(num, startId, size, speed, duration, this);
		Campus.attackers = attackers;
	}

	public void addPeople(SimulationConfig config) {
		int startId = 1;
		int num = config.getNumOfPeople();
		int duration = config.getDuration();
		int speed = 1;
		int size = 10;
		people.add(num, startId, size, speed, duration, this);
	}

	public void runSimulation(int poolSize) {
		Toolbar toolbar = getToolbar();
		toolbar.enable();

		// Run simulation
		Button startBtn = toolbar.getStartBtn();
		while (startBtn.isReleased()) {
			if (StdDraw.mousePressed()) {
				int x = (int) StdDraw.mouseX();
				int y = (int) StdDraw.mouseY();
				toolbar.toggle(x, y);

				if (startBtn.isPressed()) {
					startSimulation(poolSize);

					toolbar.disable();
					toolbar.getResetBtn().enable();
				}
			}
		}
	}

	private void startSimulation(int poolSize) {
		// Start simulation.
		try {			
			// Keep all threads in list
			ExecutorService executor = Executors.newFixedThreadPool(poolSize);

			// Start clock
			getResultPanel().reset();

			// Start escape
			Map<Person, Escape> personEscapes = getPeople().getPeople();
			List<Future<List<MomentStatus>>> futureList = new ArrayList<Future<List<MomentStatus>>>();
			for (Entry<Person, Escape> entry : personEscapes.entrySet()) {
				Future<List<MomentStatus>> future = executor.submit(entry.getValue());
				futureList.add(future);
			}
			
			Map<Attacker, Attacking> attackers = getAttackers().getAttackers();
			for (Entry<Attacker, Attacking> entry : attackers.entrySet()) {
				Future<List<MomentStatus>> future = executor.submit(entry.getValue());
				futureList.add(future);
			}

			executor.shutdown();

			// Wait until all threads are finish
			System.out.println("The attack starts and run for your life...");

			startTimer();
			while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
				checkMouseEvent();
				updateMonitor();
			}
			
			// Output results
			writeOutResults();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println();
			System.out.println("****************");
			System.out.println("    FINISH!     ");
			System.out.println("****************");
		}
	}
	
	public void writeOutResults() {
		Map<Long, Result> escapingResults = getResults().getEscapingResults();
		Map<Long, Result> timingResults = getResults().getEscapingResults();

		for (Entry<Long, Result> entry : timingResults.entrySet()) {
			long time = entry.getKey();
			Result result = entry.getValue();
			System.out.println("Time:" + time + "\t" + "Loc: (" + result.getCell().getLoc().getX() + ","
					+ result.getCell().getLoc().getY() + "), status: " + result.getStatus());
		}
	}
	
	public void checkMouseEvent() {
		if (StdDraw.mousePressed()) {
			int x = (int) StdDraw.mouseX();
			int y = (int) StdDraw.mouseY();
			getToolbar().toggle(x, y);
		}
	}
}
