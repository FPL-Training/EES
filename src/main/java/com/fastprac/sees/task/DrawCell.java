/**
 * 
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
