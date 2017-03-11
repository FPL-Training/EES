/**
 * 
 */
package com.fastprac.sees.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fastprac.sees.model.status.CellStatus;
import com.fastprac.sees.model.status.MomentStatus;
import com.fastprac.sees.model.status.PersonStatus;
import com.fastprac.utils.lib.StdDraw;

/**
 * @author Admin
 *
 */
public class Person {
	protected int id;
	protected String name;
	protected PersonStatus status;
	protected int size;
	protected Cell cell;
	protected PersonType type;

	/**
	 * 
	 */
	public Person() {
		this.id = 0;
		this.name = "";
		this.status = PersonStatus.SAFE;
		this.size = 1;
		this.cell = new Cell();
	}

	/**
	 * @param id
	 * @param name
	 * @param status
	 */
	public Person(int id, String name, int size, Cell cell) {
		super();
		this.id = id;
		this.name = name;
		this.status = PersonStatus.SAFE;
		this.size = size;
		this.cell = cell;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	public Cell getCell() {
		return this.cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public void clear() {
		synchronized (Person.class) {
			if (this.cell != null) {
				this.cell.draw();
				this.cell.setOccupant(null);
			}
		}
	}

	/**
	 * Find a neighbor cell to move
	 * 
	 * @return
	 */
	public MomentStatus move() {
		synchronized (Person.class) {
			MomentStatus tmStatus = new MomentStatus();
			Cell cellTo = chooseCellToMove();
			if (cellTo != null) {
				cellTo.setStatus(CellStatus.CLAIMED); // Claim the new position
				this.clear(); // Clear current position
				this.cell = cellTo; // Take the new position
				this.draw(); // Show
				cellTo.setOccupant(this); // Occupy the position
			}

			return tmStatus;
		}
	}

	public PersonStatus getStatus() {
		return status;
	}

	public void setStatus(PersonStatus status) {
		this.status = status;
	}

	public void draw() {
		synchronized (this.getClass()) {
			if (this.cell != null) {
				StdDraw.setPenColor(status.getColor());
				StdDraw.filledCircle(this.cell.getLoc().getX(), this.cell.getLoc().getY(), size / 2);

				StdDraw.setPenColor(Color.WHITE);
				StdDraw.circle(this.cell.getLoc().getX(), this.cell.getLoc().getY(), size / 2);

				// Take the cell
				this.cell.setStatus(CellStatus.OCCUPIED);
			}
		}
	}

	private Cell chooseCellToRunAway(Cell badCell) {
		Cell cellTo = null;
		if (badCell != null) {
			int i = cell.getGrid().getI();
			int j = cell.getGrid().getJ();
			int spanX = badCell.getGrid().getI() - i;
			int spanY = badCell.getGrid().getJ() - j;

			if (Math.abs(badCell.getGrid().getI() - cell.getGrid().getI()) < 2
					|| Math.abs(badCell.getGrid().getJ() - cell.getGrid().getJ()) < 2) {

				int i2 = i;
				if (spanX > 0) {
					i2 = i - 1;
				} else if (spanX < 0) {
					i2 = i + 1;
				}
				int j2 = j;
				if (spanY > 0) {
					j2 = j - 1;
				} else if (spanY < 0) {
					j2 = j + 1;
				}

				cellTo = Campus.cells[i2][j2];
				boolean cellToFeasible = false;
				if (cellTo != null) {
					cellToFeasible = cellTo.isFeasibleTo(this.cell);
					if (!cellToFeasible) {
						cellTo = null;
					}
				}
			}
		}
		return cellTo;
	}

	private Cell chooseNearestDoor() {
		int m = 0;
		int shortestDist = 0;
		Cell shortestDoor = null;
		for (Cell door : Campus.doors) {
			int distI = door.getGrid().getI() - cell.getGrid().getI();
			int distJ = door.getGrid().getJ() - cell.getGrid().getJ();
			int dist = distI * distI * +distJ * distJ;
			if (m == 0 || dist < shortestDist) {
				shortestDist = dist;
				shortestDoor = door;
			}
			m++;
		}
		return shortestDoor;
	}

	private Cell chooseDoorRandomly() {
		Random r = new Random();
		int idx = r.nextInt(Campus.doors.size());
		Cell door = Campus.doors.get(idx);
		return door;
	}

	private Cell chooseCellToMove(Cell door) {
		Cell cellTo = null;
		if (door != null) {
			Grid grid = this.cell.getGrid();

			int i = grid.getI();
			int j = grid.getJ();

			int spanX = door.getGrid().getI() - i;
			int spanY = door.getGrid().getJ() - j;

			int i2 = i;
			if (spanX > 0) {
				i2 = i + 1;
			} else if (spanX < 0) {
				i2 = i - 1;
			}
			int j2 = j;
			if (spanY > 0) {
				j2 = j + 1;
			} else if (spanY < 0) {
				j2 = j - 1;
			}

			cellTo = Campus.cells[i2][j2];
			boolean cellToFeasible = false;
			if (cellTo != null) {
				cellToFeasible = cellTo.isFeasibleTo(this.cell);
				if (!cellToFeasible) {
					cellTo = null;
				}
			}
			// System.out.println("i=" + i + ", j=" + j + ", nextI=" + i2 + ",
			// nextJ=" + j2
			// + ", cellTo feasible: " + cellToFeasible);
		}

		return cellTo;
	}

	private List<Cell> getFeasibleCells() {
		List<Cell> feasibleCells = new ArrayList<Cell>();

		int i = this.cell.getGrid().getI();
		int j = this.cell.getGrid().getJ();
		Cell cellTo;

		// East
		if (i < (Campus.numInX - 1)) {
			cellTo = Campus.cells[i + 1][j];
			if (cellTo.isFeasibleTo(this.cell)) {
				feasibleCells.add(cellTo);
			}
		}

		// South
		if (j > 0) {
			cellTo = Campus.cells[i][j - 1];
			if (cellTo.isFeasibleTo(this.cell)) {
				feasibleCells.add(cellTo);
			}
		}

		// West
		if (i > 0) {
			cellTo = Campus.cells[i - 1][j];
			if (cellTo.isFeasibleTo(this.cell)) {
				feasibleCells.add(cellTo);
			}
		}

		// North
		if (j < (Campus.numInY - 1)) {
			cellTo = Campus.cells[i][j + 1];
			if (cellTo.isFeasibleTo(this.cell)) {
				feasibleCells.add(cellTo);
			}
		}

		// North-East
		if (i < (Campus.numInX - 1) && j < (Campus.numInY - 1)) {
			cellTo = Campus.cells[i + 1][j + 1];
			if (cellTo.isFeasibleTo(this.cell)) {
				feasibleCells.add(cellTo);
			}
		}

		// South-East
		if (i < (Campus.numInX - 1) && j > 0) {
			cellTo = Campus.cells[i + 1][j - 1];
			if (cellTo.isFeasibleTo(this.cell)) {
				feasibleCells.add(cellTo);
			}
		}

		// South-West
		if (i > 0 && j > 0) {
			cellTo = Campus.cells[i - 1][j - 1];
			if (cellTo.isFeasibleTo(this.cell)) {
				feasibleCells.add(cellTo);
			}
		}

		// North-West
		if (i > 0 && j < (Campus.numInY - 1)) {
			cellTo = Campus.cells[i - 1][j + 1];
			if (cellTo.isFeasibleTo(this.cell)) {
				feasibleCells.add(cellTo);
			}
		}

		return feasibleCells;

	}

	private Cell chooseFeasibleCell() {
		Cell feasibleCell = null;

		List<Cell> feasibleCells = getFeasibleCells();

		if (!feasibleCells.isEmpty()) {
			Random rand = new Random();
			int idx = rand.nextInt(feasibleCells.size());
			feasibleCell = feasibleCells.get(idx);
		}

		return feasibleCell;
	}

	private Cell chooseCellToMove() {
		Cell cellTo = null;

		if (this.type != PersonType.ATTACKER) {
			cellTo = chooseCellToRunAway(Campus.attacker.getCell());
		}

		if (cellTo == null) {
			if (this.cell.isClassroom()) {
				// Choose the door
				Cell door = chooseNearestDoor();

				// Choose move toward the door
				cellTo = chooseCellToMove(door);
			} else if (this.cell.isDoor()) {
				// Choose out cell
				cellTo = chooseCellOut();
			}
		}

		// Choose any feasible move
		double rand = Math.random();
		double randomMove = 0.25;
		if (this.type == PersonType.ATTACKER) {
			randomMove = 0.50;
		}

		if (cellTo == null || rand < randomMove) {
			cellTo = chooseFeasibleCell();
		}

		return cellTo;
	}

	private Cell chooseCellOut() {
		Cell cellOut = null;
		List<Cell> feasibleCells = getFeasibleCells();

		List<Cell> outCells = new ArrayList<Cell>();
		for (Cell cell : feasibleCells) {
			if (cell.isOpen() && cell.isOutArea()) {
				outCells.add(cell);
			}
		}

		if (!outCells.isEmpty()) {
			Random rand = new Random();
			int idx = rand.nextInt(outCells.size());
			cellOut = outCells.get(idx);
		}

		return cellOut;
	}

	public boolean isMovable() {
		return (status == PersonStatus.SAFE || status == PersonStatus.INJURED);
	}

}
