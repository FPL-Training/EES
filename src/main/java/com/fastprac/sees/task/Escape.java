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
import com.fastprac.sees.model.Person;
import com.fastprac.sees.model.status.MomentStatus;
import com.fastprac.sees.model.tool.Button;
import com.fastprac.sees.model.tool.ButtonType;
import com.fastprac.sees.model.tool.Toolbar;
import com.fastprac.utils.lib.StdDraw;

/**
 * @author Admin
 *
 */
public class Escape implements Callable<List<MomentStatus>> {

	private Person person;
	private int speed; // (1-1000);
	private int duration;

	/**
	 * 
	 */
	public Escape() {
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
	public Escape(Person person, int speed, int duration) {
		super();
		this.person = person;
		this.speed = speed;
		this.duration = duration;
	}

	@Override
	public List<MomentStatus> call() throws Exception {
		int pauseTime = 1000 / speed;

		List<MomentStatus> mStatusList = new ArrayList<MomentStatus>();
		Long seq = 0L;

		Toolbar toolbar = Controller.getInstance().getToolbar();
		while (person.isMovable() && (duration > 0)) {
			if (StdDraw.mousePressed()) {
				int x = (int) StdDraw.mouseX();
				int y = (int) StdDraw.mouseY();
				toolbar.toggle(x, y);
			}

			if (toolbar.getStartBtn().isPressed()) {
				seq++;
				MomentStatus mStatus = person.move();
				mStatusList.add(mStatus);
				Thread.sleep(pauseTime);
				// System.out.println("Person: "+person.getId() + ", status:
				// "+person.getStatus().toString()+", Duration left =
				// "+duration);
				duration--;
			}
		}

		return mStatusList;
	}

}
