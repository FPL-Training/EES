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

import com.fastprac.sees.model.Cell;

/**
 * @author Admin
 *
 */
public class DrawCell implements Runnable {
	private Cell cell;
	
	/**
	 * 
	 */
	public DrawCell() {
		cell = new Cell();
	}

	public DrawCell(Cell cell) {
		this.cell = cell;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		if (cell != null) {
			cell.draw();
		}
	}

}
