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

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import com.fastprac.sees.driver.RunEES;
import com.fastprac.sees.model.Location;
import com.fastprac.sees.model.Person;
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
public class Escape extends TaskBase implements Callable<List<MomentStatus>> {

	private Person person;
	private int speed; // (1-1000);
	private int duration;

	/**
	 * 
	 */
	public Escape() {
		super();
		this.person = new Person();
		speed = 10;
		duration = 100;
	}

	/**
	 * 
	 * @param person
	 * @param speed
	 * @param duration
	 */
	public Escape(Controller ctrl, Person person, int speed, int duration) {
		super(ctrl);
		this.person = person;
		this.speed = speed;
		this.duration = duration;
	}

	@Override
	public List<MomentStatus> call() throws Exception {
		int pauseTime = 1000 / speed;

		List<MomentStatus> mStatusList = new ArrayList<MomentStatus>();
		Long seq = 0L;

		int count = 0;		
		while (person.isMovable() && (count < duration)) {
			ctrl.checkMouseEvent();
			if (ctrl.isStartActive()) {
				seq++;
				MomentStatus mStatus = person.move();
				mStatusList.add(mStatus);

				// Record the move
				count++;
				Result result = new Result(1, count, person.getId(), person.getCell(), person.getStatus());
				ctrl.addResult(1, result);
				
				//
				Thread.sleep(pauseTime);
			}
		}

		return mStatusList;
	}

}
