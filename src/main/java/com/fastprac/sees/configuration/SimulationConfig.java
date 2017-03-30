package com.fastprac.sees.configuration;

public class SimulationConfig {
	private int canvasWidth;
	private int canvasHeight;
	private int poolSize;
	private int duration;
	private int numOfPeople;
	private int numOfAttackers;

	public SimulationConfig() {
		canvasWidth = SimulationDefault.CANVAS_WIDTH;
		canvasHeight = SimulationDefault.CANVAS_HEIGHT;
		poolSize = SimulationDefault.THREAD_POOL_SIZE;
		duration = SimulationDefault.SIMULATION_DURATION;
		numOfPeople = SimulationDefault.NUMBER_OF_PEOPLE;
		numOfAttackers = SimulationDefault.NUMBER_OF_ATTACKERS;
	}

	public int getCanvasWidth() {
		return canvasWidth;
	}

	public int getCanvasHeight() {
		return canvasHeight;
	}

	public int getPoolSize() {
		return poolSize;
	}

	public int getDuration() {
		return duration;
	}

	public int getNumOfPeople() {
		return numOfPeople;
	}

	public int getNumOfAttackers() {
		return numOfAttackers;
	}

}
