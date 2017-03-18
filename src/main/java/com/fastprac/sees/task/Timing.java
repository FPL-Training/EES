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

import com.fastprac.sees.model.Timer;

/**
 * @author Admin
 *
 */
public class Timing implements Runnable {
	private Timer clock;
	private long duration;

	/**
	 * 
	 */
	public Timing() {
		this.clock = new Timer();
		this.duration = 0;
	}

	/**
	 * @param clock
	 */
	public Timing(Timer clock, long duration) {
		this.clock = clock;
		this.duration = duration;
	}

	@Override
	public void run() {
		try {
			while (duration > 0) {
				clock.draw();
				Thread.sleep(1000);
				clock.clear();
				duration--;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
