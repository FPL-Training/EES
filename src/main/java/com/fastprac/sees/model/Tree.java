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

import java.awt.Color;

import com.fastprac.utils.lib.StdDraw;

public class Tree {
	private int id;
	private String name;
	private Cell cell;
	private Color color;

	public Tree() {
		super();
		this.id = 0;
		this.name = "";
		this.cell = null;
		this.color = Color.GREEN;
	}

	/**
	 * @param id
	 * @param name
	 * @param size
	 * @param cell
	 * @param color
	 */
	public Tree(int id, String name, Cell cell, Color color) {
		super();
		this.id = id;
		this.name = name;
		this.cell = cell;
		this.color = color;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the cell
	 */
	public Cell getCell() {
		return cell;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	public void draw() {
		if (this.cell != null) {
			int radius = this.cell.getDimension().getWidth() / 2;

			StdDraw.setPenColor(color);
			StdDraw.filledCircle(this.cell.getLoc().getX(), this.cell.getLoc().getY(), radius);

			StdDraw.setPenColor(Color.WHITE);

			StdDraw.circle(this.cell.getLoc().getX(), this.cell.getLoc().getY(), radius);

			// Take the cell
			this.cell.setOccupant(this);
		}
	}
}
