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
import com.fastprac.sees.model.tool.Panel;

public class MonitorPanel extends Panel {
	private Map<String, DisplayText> items;

	public MonitorPanel() {
		super();
		initialize();
	}

	public MonitorPanel(Location loc, Dimension dimension) {
		super(loc, dimension);
		initialize();
	}

	private void initialize() {
		items = new HashMap<String, DisplayText>();
	}

	public void addItem(String label, DisplayText text) {
		items.put(label, text);
	}

	public Map<String, DisplayText> getItems() {
		return items;
	}

	public void draw() {
		for (Entry<String, DisplayText> text : items.entrySet()) {
			DisplayText displayText = text.getValue();
			displayText.draw();
		}
	}

	public void drawText() {
		for (Entry<String, DisplayText> text : items.entrySet()) {
			DisplayText displayText = text.getValue();
			displayText.drawText();
		}
	}

	public void clearText() {
		for (Entry<String, DisplayText> text : items.entrySet()) {
			DisplayText displayText = text.getValue();
			displayText.clearText();
		}
	}

	public void reset() {
		for (Entry<String, DisplayText> text : items.entrySet()) {
			DisplayText displayText = text.getValue();
			displayText.clearText();
		}
	}

	public void updateTime(String label, long timeElapsed) {
		DisplayText displayTime = items.get(label);
		if (displayTime != null) {
			displayTime.clearText();

			long seconds = TimeUnit.MILLISECONDS.toSeconds(timeElapsed);
			long minutes = TimeUnit.MILLISECONDS.toMinutes(timeElapsed);
			long hours = TimeUnit.MILLISECONDS.toHours(timeElapsed);
			String time = hours + ":" + minutes + ":" + seconds;
			displayTime.setText(time);
			displayTime.drawText();
		}
	}
}
