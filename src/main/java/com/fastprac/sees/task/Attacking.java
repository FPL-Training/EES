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

import com.fastprac.sees.model.Attacker;
import com.fastprac.sees.model.status.MomentStatus;

/**
 * @author Admin
 *
 */
public class Attacking implements Callable<List<MomentStatus>> {
	private Attacker attacker;
	private long duration;
	private int speed;

	/**
	 * 
	 */
	public Attacking() {
		this.duration = 0;
		this.attacker = new Attacker();
	}

	/**
	 * @param attacker
	 * @param duration
	 */
	public Attacking(Attacker attacker, int speed, long duration) {
		super();
		this.attacker = attacker;
		this.speed = speed;
		this.duration = duration;
	}

	@Override
	public List<MomentStatus> call() {
		int pauseTime = 1000 / speed;

		List<MomentStatus> mStatusList = new ArrayList<MomentStatus>();
		try {

			while (duration > 0) {
				
				attacker.killNeighbors();
				
				MomentStatus mStatus = attacker.move();
				mStatusList.add(mStatus);
				Thread.sleep(pauseTime);
				duration--;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return mStatusList;		
	}

}
