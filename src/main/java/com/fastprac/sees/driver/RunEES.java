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
package com.fastprac.sees.driver;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.fastprac.sees.model.Person;
import com.fastprac.sees.model.Result;
import com.fastprac.sees.model.status.MomentStatus;
import com.fastprac.sees.model.tool.Button;
import com.fastprac.sees.model.tool.Toolbar;
import com.fastprac.sees.task.Attacking;
import com.fastprac.sees.task.Controller;
import com.fastprac.sees.task.Escape;
import com.fastprac.utils.lib.StdDraw;

/**
 * @author Admin
 *
 */
public class RunEES {

	private static Controller ctrl;
	private static SimulationConfig config;
	Attacker attacker;

	public RunEES() {

	}

	public void loadConfiguration() {
		System.out.println("Loading configuration...");
		config = new SimulationConfig();
	}
	
	public void initialize() {
		ctrl = new Controller();
		ctrl.addCanvas(config.getCanvasWidth(), config.getCanvasHeight());
		ctrl.getCanvas().addCampus(50, 50, 10);
		
	}

	public void draw() {
		ctrl.getCanvas().draw();
		ctrl.getResultPanel().updateTime("Time", 0);
		//attacker.draw();
		//draw people();
	}
	
	private Attacker addAttacker() {
		System.out.println("Adding an attacker in campus...");
		int attackStartI = (int) (Campus.getClassroomStartI() + Math.random() * Campus.getClassroomNumX());
		int attackStartJ = (int) (Campus.getClassroomStartJ() + Campus.getClassroomNumY() / 2);

		int numOfPeople = config.getNumOfPeople();
		int attackerId = numOfPeople + 1;
		attacker = new Attacker(attackerId, "Attacker", 10, Campus.cells[attackStartI][attackStartJ]);
		Campus.attacker = attacker;
		attacker.draw();
		System.out.println("An attacker created at (" + attackStartI + ", " + attackStartJ + ")");
		return attacker;
	}

	private Map<Person, Escape> addPeople() {
		// Create students/faculty
		int numOfPeople = config.getNumOfPeople();
		int duration = config.getDuration();
		Map<Person, Escape> personEscapes = new HashMap<Person, Escape>();
		for (int n = 0; n < numOfPeople; n++) {
			int i = Campus.getClassroomStartI() + (int) (Math.random() * Campus.getClassroomNumX());
			int j = Campus.getClassroomStartJ() + (int) (Math.random() * Campus.getClassroomNumY());
			if (Campus.cells[i][j].isOpen()) {
				Person person = new Person(n + 1, "Student", 10, Campus.cells[i][j]);
				Escape escape = new Escape(ctrl, person, 1, duration);
				personEscapes.put(person, escape);

				person.draw();
			}
		}
		System.out.println("Total people created: " + personEscapes.size());

		return personEscapes;
	}

	private void runAttackingSimulation(Attacker attacker, Map<Person, Escape> personEscapes) {

		Toolbar toolbar = ctrl.getToolbar();
		toolbar.enable();

		// Define attacking action
		int duration = config.getDuration();
		Attacking attacking = new Attacking(ctrl, attacker, 1, duration);

		// Run simulation
		Button startBtn = toolbar.getStartBtn();
		while (startBtn.isReleased()) {
			if (StdDraw.mousePressed()) {
				int x = (int) StdDraw.mouseX();
				int y = (int) StdDraw.mouseY();
				toolbar.toggle(x, y);

				if (startBtn.isPressed()) {
					startSimulation(personEscapes, attacking);

					toolbar.disable();
					toolbar.getResetBtn().enable();
				}
			}
		}
	}

	private static void startSimulation(Map<Person, Escape> personEscapes, Attacking attacking) {
		// Start simulation.
		try {
			// Keep all threads in list
			// ExecutorService executor = Executors.newSingleThreadExecutor();
			int poolSize = config.getPoolSize();
			ExecutorService executor = Executors.newFixedThreadPool(poolSize);

			// Start clock
			ctrl.getResultPanel().reset();
			// executor.execute(timing);

			// Start attack
			// executor.execute(attacking);

			// Start escape
			List<Future<List<MomentStatus>>> futureList = new ArrayList<Future<List<MomentStatus>>>();
			for (Entry<Person, Escape> entry : personEscapes.entrySet()) {
				Future<List<MomentStatus>> future = executor.submit(entry.getValue());
				futureList.add(future);
			}
			futureList.add(executor.submit(attacking));

			executor.shutdown();

			// Wait until all threads are finish
			System.out.println("The attack starts and run for your life...");

			ctrl.startSimulation();
			while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
				ctrl.updateMonitor();
			}
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
		Map<Long, Result> escapingResults = ctrl.getResults().getEscapingResults();
		Map<Long, Result> timingResults = ctrl.getResults().getEscapingResults();

		for (Entry<Long, Result> entry : timingResults.entrySet()) {
			long time = entry.getKey();
			Result result = entry.getValue();
			System.out.println("Time:" + time + "\t" + "Loc: (" + result.getCell().getLoc().getX() + ","
					+ result.getCell().getLoc().getY() + "), status: " + result.getStatus());
			;
		}

	}

	/**
	 * Run EES
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		RunEES runEes = new RunEES();
		runEes.loadConfiguration();
		runEes.initialize();
		runEes.draw();

		Attacker attacker = runEes.addAttacker();

		Map<Person, Escape> people = runEes.addPeople();

		// Run simulation
		runEes.runAttackingSimulation(attacker, people);

		// Write out results
		runEes.writeOutResults();
	}

}
