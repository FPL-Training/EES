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
	private int size;
	private Cell cell;
	private Color color;

	public Tree() {
		super();
		this.id = 0;
		this.name = "";
		this.size = 0;
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
	public Tree(int id, String name, int size, Cell cell, Color color) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
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
	 * @return the size
	 */
	public int getSize() {
		return size;
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
			StdDraw.setPenColor(color);
			StdDraw.filledCircle(this.cell.getLoc().getX(), this.cell.getLoc().getY(), size / 2);

			StdDraw.setPenColor(Color.WHITE);
			StdDraw.circle(this.cell.getLoc().getX(), this.cell.getLoc().getY(), size / 2);

			// Take the cell
			this.cell.setOccupant(this);
		}
	}
}
