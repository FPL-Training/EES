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

import com.fastprac.sees.configuration.Canvas;
import com.fastprac.sees.model.Attacker;
import com.fastprac.sees.model.Campus;
import com.fastprac.sees.model.Dimension;
import com.fastprac.sees.model.Location;
import com.fastprac.sees.model.Person;
import com.fastprac.sees.model.Timer;
import com.fastprac.sees.model.status.MomentStatus;
import com.fastprac.sees.model.tool.Button;
import com.fastprac.sees.model.tool.ButtonStatus;
import com.fastprac.sees.model.tool.ButtonType;
import com.fastprac.sees.model.tool.Panel;
import com.fastprac.sees.model.tool.ToolItem;
import com.fastprac.sees.task.Attacking;
import com.fastprac.sees.task.Escape;
import com.fastprac.sees.task.Timing;
import com.fastprac.sees.task.Toolbar;
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

	static {
		System.out.println("Creating canvas...");
		cavas = new Canvas(canvasWidth, canvasHeight);
	}
	
	public RunEES() {
	
	}

	private static Panel createToolPanel() {
		System.out.println("Creating tool panel...");
		Panel toolPanel = new Panel(new Location(0, (canvasHeight - 70)), new Dimension(canvasWidth, 50));
		
		// Add start button
		toolPanel.addButton(600, (canvasHeight - 60), 49, 28, ButtonType.START);
		
		// Add pause button
		toolPanel.addButton(650, (canvasHeight - 60), 49, 28, ButtonType.STOP);
				
		// Add stop button
		toolPanel.addButton(700, (canvasHeight - 60), 49, 28, ButtonType.RESET);
		
		toolPanel.draw();
		return toolPanel;
	}
	
	private static Toolbar createToolbar(Panel toolPanel) {
		Button startBtn = (Button)toolPanel.getButton(ButtonType.START.getLabel());
		Button stopBtn = (Button)toolPanel.getButton(ButtonType.STOP.getLabel());
		Button resetBtn = (Button)toolPanel.getButton(ButtonType.RESET.getLabel());
		
		toolbar = new Toolbar(startBtn, stopBtn, resetBtn);
		return toolbar;
	}
	
	private static Timer createResultPanel() {
		System.out.println("Creating result panel...");
		Timer timer = new Timer(new Location(700, canvasHeight - 100), new Dimension(60, 32));
		Timing timing = new Timing(timer, 100L);
		timer.draw();
		
		return timer;
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
		int attackStartJ = (int) (Campus.getClassroomStartJ() + Campus.getClassroomNumY()/2);
		Attacker attacker = new Attacker(1, "Attacker", 10, Campus.cells[attackStartI][attackStartJ]);
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
	
	private static void runAttackingSimulation(Panel toolPanel, Timer timer, Attacker attacker, Map<Person, Escape> personEscapes) {
		// Define attacking action
		Attacking attacking = new Attacking(attacker, 1, duration);

		// Run simulation
		Button startBtn = toolbar.getStartBtn();
		while(startBtn.getStatus() == ButtonStatus.ON) {
			if (StdDraw.mousePressed()) {
			int x = (int) StdDraw.mouseX();
			int y = (int) StdDraw.mouseY();
			if (startBtn.pointOn(x, y)) {
				toolbar.pressStart();				
				startSimulation(timer, personEscapes, attacking);
				toolbar.pressStop();
			}}
		}
	}
	
	private static void startSimulation(Timer timer, Map<Person, Escape> personEscapes, Attacking attacking) {
		// Start simulation.
		try {
			// Keep all threads in list
			// ExecutorService executor = Executors.newSingleThreadExecutor();
			ExecutorService executor = Executors.newFixedThreadPool(poolSize);

			// Start clock
			timer.reset();
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
			System.out.println("Running for life...");
			while (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
				// System.out.println("Still running...");
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
	
	public static Toolbar getToolbar() {
		return toolbar;
	}

	/**
	 * Run EES
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Panel toolPanel = createToolPanel();
		Toolbar toolbar = createToolbar(toolPanel);

		Timer timer = createResultPanel();

		Campus campus = createCampus();

		Attacker attacker = addAttacker();

		Map<Person, Escape> personEscapes = addPeople();

		runAttackingSimulation(toolPanel, timer, attacker, personEscapes);
	}

}
