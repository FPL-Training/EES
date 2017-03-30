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
import java.util.List;
import java.util.concurrent.Callable;

import com.fastprac.sees.driver.RunEES;
import com.fastprac.sees.model.Attacker;
import com.fastprac.sees.model.Result;
import com.fastprac.sees.model.status.MomentStatus;
import com.fastprac.sees.model.tool.Button;
import com.fastprac.sees.model.tool.ButtonType;
import com.fastprac.sees.model.tool.Toolbar;
import com.fastprac.utils.lib.StdDraw;

/**
 * @author Admin
 *
 */
public class Attacking extends TaskBase implements Callable<List<MomentStatus>> {
	private Attacker attacker;
	private long duration;
	private int speed;

	/**
	 * 
	 */
	public Attacking() {
		super();
		this.duration = 0;
		this.attacker = new Attacker();
	}

	/**
	 * @param attacker
	 * @param duration
	 */
	public Attacking(Controller ctrl, Attacker attacker, int speed, long duration) {
		super(ctrl);
		this.attacker = attacker;
		this.speed = speed;
		this.duration = duration;
	}

	@Override
	public List<MomentStatus> call() {
		int pauseTime = 1000 / speed;

		List<MomentStatus> mStatusList = new ArrayList<MomentStatus>();
		try {
			Toolbar toolbar = ctrl.getToolbar();
			int count = 0;
			while (count < duration) {
				if (StdDraw.mousePressed()) {
					int x = (int) StdDraw.mouseX();
					int y = (int) StdDraw.mouseY();
					toolbar.toggle(x, y);
				}	
				
				if (toolbar.getStartBtn().isPressed()) {
					attacker.killNeighbors();

					MomentStatus mStatus = attacker.move();
					mStatusList.add(mStatus);
					
					// Record the move
					count++;
					Result result = new Result(1, count, attacker.getId(), attacker.getCell(), attacker.getStatus());
					ctrl.addResult(1, result);
					
					//
					Thread.sleep(pauseTime);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return mStatusList;
	}

}
