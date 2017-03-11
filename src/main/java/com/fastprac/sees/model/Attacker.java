package com.fastprac.sees.model;

import java.util.ArrayList;
import java.util.List;

import com.fastprac.sees.model.status.PersonStatus;

public class Attacker extends Person {

	public Attacker() {
		super();
		this.type = PersonType.ATTACKER;
		this.status = PersonStatus.ATTACKING;
	}

	public Attacker(int id, String name, int size, Cell cell) {
		super(id, name, size, cell);
		this.type = PersonType.ATTACKER;
		this.status = PersonStatus.ATTACKING;
	}

	public void killNeighbors() {
		synchronized (Attacker.class) {
			List<Cell> reachableCells = getReachablePeople();
			for (Cell cell: reachableCells) {
				kill(cell.getOccupant());
			}
		}
	}

	private void kill(Object occupant) {
		if (occupant != null && occupant instanceof Person && !(occupant instanceof Attacker)) {
			synchronized (occupant.getClass()) {
				((Person) occupant).setStatus(PersonStatus.DEAD);
				((Person) occupant).draw();
			}
		}
	}
	
	private List<Cell> getReachablePeople() {
		List<Cell> reachableCells = new ArrayList<Cell>();

		int i = this.cell.getGrid().getI();
		int j = this.cell.getGrid().getJ();
		Cell cellTo;

		// East
		if (i < (Campus.numInX - 1)) {
			cellTo = Campus.cells[i + 1][j];
			if (cellTo.isReachableBy(this.cell)) {
				reachableCells.add(cellTo);
			}
		}

		// South
		if (j > 0) {
			cellTo = Campus.cells[i][j - 1];
			if (cellTo.isReachableBy(this.cell)) {
				reachableCells.add(cellTo);
			}
		}

		// West
		if (i > 0) {
			cellTo = Campus.cells[i - 1][j];
			if (cellTo.isReachableBy(this.cell)) {
				reachableCells.add(cellTo);
			}
		}

		// North
		if (j < (Campus.numInY - 1)) {
			cellTo = Campus.cells[i][j + 1];
			if (cellTo.isReachableBy(this.cell)) {
				reachableCells.add(cellTo);
			}
		}
		/*
		// North-East
		if (i < (Campus.numInX - 1) && j < (Campus.numInY - 1)) {
			cellTo = Campus.cells[i + 1][j + 1];
			if (cellTo.isReachableBy(this.cell)) {
				reachableCells.add(cellTo);
			}
		}

		// South-East
		if (i < (Campus.numInX - 1) && j > 0) {
			cellTo = Campus.cells[i + 1][j - 1];
			if (cellTo.isReachableBy(this.cell)) {
				reachableCells.add(cellTo);
			}
		}

		// South-West
		if (i > 0 && j > 0) {
			cellTo = Campus.cells[i - 1][j - 1];
			if (cellTo.isReachableBy(this.cell)) {
				reachableCells.add(cellTo);
			}
		}

		// North-West
		if (i > 0 && j < (Campus.numInY - 1)) {
			cellTo = Campus.cells[i - 1][j + 1];
			if (cellTo.isReachableBy(this.cell)) {
				reachableCells.add(cellTo);
			}
		}
		*/
		return reachableCells;

	}
}
