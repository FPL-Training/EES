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

import com.fastprac.sees.configuration.SimulationConfig;
import com.fastprac.sees.task.Controller;

/**
 * @author Admin
 *
 */
public class RunEES {

	private static Controller ctrl;
	private static SimulationConfig config;

	public RunEES() {

	}

	public void loadConfiguration() {
		System.out.println("Loading configuration...");
		config = new SimulationConfig();
	}

	public void initialize() {
		System.out.println("Initializing...");
		ctrl = new Controller();
		
		System.out.println("Creating a cavas...");
		ctrl.addCanvas(config.getCanvasWidth(), config.getCanvasHeight());
		
		System.out.println("Creating a campus layout...");
		ctrl.getCanvas().addCampus(50, 50, 10);
		
		System.out.println("Adding attackers on campus...");
		ctrl.addAttackers(config);
		
		System.out.println("Adding people on campus...");
		ctrl.addPeople(config);

	}

	public void draw() {
		System.out.println("Drawing canvas...");
		ctrl.getCanvas().draw();
		ctrl.getResultPanel().updateTime("Time", 0);
		ctrl.getAttackers().draw();
		ctrl.getPeople().draw();
	}

	private void runAttackingSimulation() {
		System.out.println("Start attacking simulation...");
		ctrl.runSimulation(config.getPoolSize());
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
		runEes.runAttackingSimulation();
	}

}
