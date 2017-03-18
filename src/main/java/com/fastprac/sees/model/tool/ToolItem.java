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
package com.fastprac.sees.model.tool;

import java.awt.Color;
import java.util.concurrent.atomic.AtomicLong;

import com.fastprac.sees.model.Location;

/**
 * @author Admin
 *
 */
public abstract class ToolItem {
	protected AtomicLong itemId = new AtomicLong(0);

	protected Long id;
	protected String name;
	protected String text;
	protected Color color;
	protected Location loc;
	
	/**
	 * 
	 */
	public ToolItem() {
		
	}

	/**
	 * @param id
	 * @param name
	 */
	public ToolItem(String name, String text, Location loc) {
		super();		
		this.id = itemId.incrementAndGet();
		this.name = name;
		this.text = text;
		this.loc = loc;
	}

	/**
	 * @return the id
	 */public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the loc
	 */
	public Location getLoc() {
		return loc;
	}

	/**
	 * @param loc the loc to set
	 */
	public void setLoc(Location loc) {
		this.loc = loc;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	abstract public void draw();
}
