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
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import com.fastprac.sees.model.tool.DisplayText;

public class ResultPanel {
	private Map<String, DisplayText> results;

	public ResultPanel() {
		super();
		results = new HashMap<String, DisplayText>();
	}

	public void addDisplayText(String label, DisplayText text) {
		results.put(label, text);
	}

	public Map<String, DisplayText> getResults() {
		return results;
	}

	public void draw() {
		for (Entry<String, DisplayText> text : results.entrySet()) {
			DisplayText displayText = text.getValue();
			displayText.draw();
		}
	}

	public void drawText() {
		for (Entry<String, DisplayText> text : results.entrySet()) {
			DisplayText displayText = text.getValue();
			displayText.drawText();
		}
	}

	public void clearText() {
		for (Entry<String, DisplayText> text : results.entrySet()) {
			DisplayText displayText = text.getValue();
			displayText.clearText();
		}
	}
	
	public void reset() {
		for (Entry<String, DisplayText> text : results.entrySet()) {
			DisplayText displayText = text.getValue();
			displayText.clearText();
		}
	}

	public void updateTime(String label, long timeElapsed) {
		DisplayText displayTime = results.get(label);
		displayTime.clearText();

		long seconds =  TimeUnit.MILLISECONDS.toSeconds(timeElapsed);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(timeElapsed);
		long hours = TimeUnit.MILLISECONDS.toHours(timeElapsed);
		String time = hours + ":"+minutes + ":"+seconds;
		displayTime.setText(time);
		displayTime.drawText();
		System.out.println("Time: " + time+"......");		
	}
}
