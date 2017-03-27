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
package com.fastprac.sees.model;

import java.util.HashMap;
import java.util.Map;

import com.fastprac.sees.model.Result;

public class Results {
	private int simulId;
	private Map<Long, Result> escapingResults;
	private Map<Long, Result> timingResults;

	public Results() {
		escapingResults = new HashMap<Long, Result>();
		timingResults = new HashMap<Long, Result>();
	}

	public void addResult(int simulId, Result result) {
		this.simulId = simulId;
		int personId = result.getPersonId();
		int time = result.getDurCount();
		escapingResults.put((long) personId, result);
		timingResults.put((long) time, result);
	}

	/**
	 * @return the simulId
	 */
	public int getSimulId() {
		return simulId;
	}

	/**
	 * @return the escapingResults
	 */
	public Map<Long, Result> getEscapingResults() {
		return escapingResults;
	}

	/**
	 * @return the timingResults
	 */
	public Map<Long, Result> getTimingResults() {
		return timingResults;
	}
	
	
}
