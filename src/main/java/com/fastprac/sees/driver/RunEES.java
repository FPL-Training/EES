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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.fastprac.sees.configuration.Canvas;
import com.fastprac.sees.model.Attacker;
import com.fastprac.sees.model.Campus;
import com.fastprac.sees.model.Dimension;
import com.fastprac.sees.model.Location;
import com.fastprac.sees.model.Person;
import com.fastprac.sees.model.Result;
import com.fastprac.sees.model.status.MomentStatus;
import com.fastprac.sees.model.tool.Button;
import com.fastprac.sees.model.tool.DisplayText;
import com.fastprac.sees.task.Attacking;
import com.fastprac.sees.task.Controller;
import com.fastprac.sees.task.Escape;
import com.fastprac.sees.model.tool.Toolbar;
import com.fastprac.utils.lib.StdDraw;

/**
 * @author Admin
 *
 */
public class RunEES {
	private static final Canvas cavas;
	private static final int canvasWidth = 800;
	private static final int canvasHeight = 550;
	private static final int poolSize = 100;
	private static final int duration = 60;
	private static final int numOfPeople = 100;
	private static Toolbar toolbar;
	private static Controller ctrl;

	static {
		System.out.println("Creating canvas...");
		cavas = new Canvas(canvasWidth, canvasHeight);
		ctrl = Controller.getInstance();
	}

	public RunEES() {

	}

	private static void createToolPanel() {
		ctrl.addToolPanel(0, (canvasHeight - 60), canvasWidth, 40);
		ctrl.addToolbar();
	}

	private static void createResultPanel() {
		System.out.println("Creating result panel...");
		
		// Time
		Location timeLoc = new Location(700, canvasHeight - 100);
		Dimension timeDimension = new Dimension(100, 32);
		DisplayText time = new DisplayText(timeLoc, timeDimension, "Time", "");
		ctrl.getResultPanel().addDisplayText("Time", time);
		
		// Total people
		
		// Dead
		
		// Uninjured
		

	}

	private static Campus createCampus() {
		System.out.println("Creating school campus...");
		Campus campus = new Campus(50, 50, 10);
		campus.draw();
		return campus;
	}

	private static Attacker addAttacker() {
		System.out.println("Adding an attacker in campus...");
		int attackStartI = (int) (Campus.getClassroomStartI() + Math.random() * Campus.getClassroomNumX());
		int attackStartJ = (int) (Campus.getClassroomStartJ() + Campus.getClassroomNumY() / 2);

		int attackerId = numOfPeople + 1;
		Attacker attacker = new Attacker(attackerId, "Attacker", 10, Campus.cells[attackStartI][attackStartJ]);
		Campus.attacker = attacker;
		attacker.draw();
		System.out.println("An attacker created at (" + attackStartI + ", " + attackStartJ + ")");
		return attacker;
	}

	private static Map<Person, Escape> addPeople() {
		// Create students/faculty
		Map<Person, Escape> personEscapes = new HashMap<Person, Escape>();
		for (int n = 0; n < numOfPeople; n++) {
			int i = Campus.getClassroomStartI() + (int) (Math.random() * Campus.getClassroomNumX());
			int j = Campus.getClassroomStartJ() + (int) (Math.random() * Campus.getClassroomNumY());
			if (Campus.cells[i][j].isOpen()) {
				Person person = new Person(n + 1, "Student", 10, Campus.cells[i][j]);
				Escape escape = new Escape(person, 1, duration);
				personEscapes.put(person, escape);

				person.draw();
			}
		}
		System.out.println("Total people created: " + personEscapes.size());

		return personEscapes;
	}

	private static void runAttackingSimulation(Attacker attacker, Map<Person, Escape> personEscapes) {

		Toolbar toolbar = Controller.getInstance().getToolbar();
		toolbar.enable();

		// Define attacking action
		Attacking attacking = new Attacking(attacker, 1, duration);

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

			Date startTime = new Date();
			long startTimeVal = startTime.getTime();
			long timeElapsed = 0L;
			while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
				Date currentTime = new Date();
				if (ctrl.getToolbar().getStartBtn().isPressed()) {
					timeElapsed = currentTime.getTime() - startTimeVal;
					ctrl.getResultPanel().updateTime("Time", timeElapsed);
				} else {
					startTimeVal = currentTime.getTime() - timeElapsed;
				}
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

	public static void writeOutResults() {
		Map<Long, Result> escapingResults = ctrl.getResults().getEscapingResults();
		Map<Long, Result> timingResults = ctrl.getResults().getEscapingResults();
		
		for (Entry<Long, Result> entry : timingResults.entrySet()) {
			long time = entry.getKey();
			Result result = entry.getValue();
			System.out.println("Time:"+time+"\t"+ "Loc: ("+result.getCell().getLoc().getX()+","+result.getCell().getLoc().getY()+"), status: "+result.getStatus());;
		}
		
	}
	
	public Controller getController() {
		return Controller.getInstance();
	}
	
	/**
	 * Run EES
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Create tool panel and toolbar
		createToolPanel();

		createResultPanel();

		ctrl.draw();
		ctrl.getResultPanel().updateTime("Time", 0);

		// Crate campus
		Campus campus = createCampus();

		Attacker attacker = addAttacker();

		Map<Person, Escape> people = addPeople();
		
		// Run simulation
		runAttackingSimulation(attacker, people);
		
		// Write out results
		writeOutResults();
	}

}
