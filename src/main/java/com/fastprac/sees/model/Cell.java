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

import com.fastprac.sees.model.status.CellStatus;
import com.fastprac.utils.lib.StdDraw;

/**
 * @author Admin
 *
 */
public class Cell {

	private static Location startLoc;

	private final Location loc;
	private final Grid grid;
	private final Dimension dimension;
	private Color color;
	private CellType type;
	private CellStatus status;
	private Object occupant;

	public static Location getStartLoc() {
		return startLoc;
	}

	public static void setStartLoc(Location loc) {
		startLoc = loc;
	}

	public static void setStartLoc(int x, int y) {
		startLoc = new Location(x, y);
	}

	public Grid getGrid() {
		return grid;
	}

	/**
	 * Construct a Cell object using default settings.
	 */
	public Cell() {
		this.grid = new Grid();
		this.loc = new Location();
		this.dimension = new Dimension();
		this.color = Color.CYAN;
		this.type = CellType.OUTER_AREA;
		this.status = CellStatus.OPEN;
		normalizeStatus();
	}

	public Cell(Grid grid, Dimension dimension, CellType type) {
		this.grid = grid;
		this.loc = new Location();
		this.dimension = dimension;
		this.type = type;
		if (type.isAccessible()) {
			this.status = CellStatus.OPEN;
		} else {
			this.status = CellStatus.OCCUPIED;
		}
		normalizeStatus();
	}

	/**
	 * @return the loc
	 */
	public Location getLoc() {
		return loc;
	}

	/**
	 * @return the dimension
	 */
	public Dimension getDimension() {
		return dimension;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the type
	 */
	public CellType getType() {
		return type;
	}

	public void setType(CellType type) {
		this.type = type;
	}

	public CellStatus getStatus() {
		return status;
	}

	public void setStatus(CellStatus status) {
		this.status = status;
		normalizeStatus();
	}

	public boolean isOpen() {
		return (type.isAccessible() && status == CellStatus.OPEN);
	}

	private void normalizeStatus() {
		if (type == CellType.WALL || type == CellType.TREE) {
			status = CellStatus.OCCUPIED;
		}

		updateLocation();
	}

	/**
	 * 
	 */
	private void updateLocation() {
		int x = startLoc.getX() + this.dimension.getWidth() * this.grid.getI();
		int y = startLoc.getY() + this.dimension.getHeight() * this.grid.getJ();
		this.loc.setX(x);
		this.loc.setY(y);
//		System.out.println("startLoc[x, y]="+startLoc.getX()+","+startLoc.getY());
//		System.out.println("[x, y]="+x+","+y);
	}

	public void draw() {
		synchronized (Cell.class) {
			// Draw the cell body
			StdDraw.setPenColor(type.getColor());
			StdDraw.filledRectangle(loc.getX(), loc.getY(), dimension.getWidth() / 2, dimension.getHeight() / 2);

			// Draw the cell border
			// StdDraw.setPenColor(Color.WHITE);
			StdDraw.setPenColor(225, 225, 225);
			StdDraw.rectangle(loc.getX(), loc.getY(), dimension.getWidth() / 2, dimension.getHeight() / 2);
		}
	}

	public boolean isDoor() {
		return (this.type == CellType.DOOR);
	}

	/**
	 * @return the occupant
	 */
	public Object getOccupant() {
		return occupant;
	}

	/**
	 * @param occupant
	 *            the occupant to set
	 */
	public void setOccupant(Object occupant) {
		synchronized (Cell.class) {
			this.occupant = occupant;
			if (occupant != null) {
				this.setStatus(CellStatus.OCCUPIED);
			} else {
				if (this.getType().isAccessible()) {
					this.setStatus(CellStatus.OPEN);
				}
			}
		}
	}

	public boolean isClassroom() {
		return (this.type == CellType.CLASSROOM);
	}

	public boolean isFeasibleTo(Cell cell) {
		boolean isFeasible = (this.isOpen() && (this.getType() == cell.getType() || this.isDoor() || cell.isDoor()));
		return isFeasible;
	}

	public boolean isReachableBy(Cell cell) {
		boolean isReachable = (this.getType() == cell.getType() || this.isDoor() || cell.isDoor());
		return isReachable;
	}
	
	public boolean isOutArea() {
		return (this.type == CellType.OUTER_AREA);
	}

}
