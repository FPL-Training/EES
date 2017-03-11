/**
 * 
 */
package com.fastprac.sees.model;

import java.awt.Color;
import java.util.Random;

import com.fastprac.sees.model.status.CellStatus;
import com.fastprac.sees.model.status.MomentStatus;
import com.fastprac.sees.model.status.PersonStatus;
import com.fastprac.utils.lib.StdDraw;

/**
 * @author Admin
 *
 */
public class Attack extends DrawItem {
	private int id;
	private String name;
	private AttackType type;
	private int size;
	private Grid grid;
	Color color;
	Color deadZoneColor;
	int deadZoneRadius;
	Color injureZoneColor;
	int injureZoneRadius;

	/**
	 * 
	 */
	public Attack() {
		this.id = 0;
		this.name = "";
		this.type = AttackType.FIRE;
		this.size = 1;
		this.grid = new Grid();
		this.color = Color.RED;
		this.deadZoneColor = Color.RED;
		this.injureZoneColor = Color.ORANGE;
		this.deadZoneRadius = size * 3 / 2;
		this.injureZoneRadius = size * 5 / 2;
	}

	/**
	 * @param id
	 * @param name
	 * @param type
	 * @param size
	 * @param grid
	 * @param color
	 * @param deadZoneColor
	 * @param deadZoneRadius
	 * @param injureZoneColor
	 * @param injureRadius
	 */
	public Attack(int id, String name, AttackType type, int size, Grid grid) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.size = size;
		this.grid = grid;
		this.color = Color.RED;
		this.deadZoneColor = Color.RED;
		this.injureZoneColor = Color.ORANGE;
		this.deadZoneRadius = size * 3 / 2;
		this.injureZoneRadius = size * 5 / 2;
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
	 * @return the type
	 */
	public AttackType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(AttackType type) {
		this.type = type;
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

	/**
	 * @return the grid
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * @param grid
	 *            the grid to set
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
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
	 * @return the deadZoneColor
	 */
	public Color getDeadZoneColor() {
		return deadZoneColor;
	}

	/**
	 * @param deadZoneColor
	 *            the deadZoneColor to set
	 */
	public void setDeadZoneColor(Color deadZoneColor) {
		this.deadZoneColor = deadZoneColor;
	}

	/**
	 * @return the deadZoneRadius
	 */
	public int getDeadZoneRadius() {
		return deadZoneRadius;
	}

	/**
	 * @param deadZoneRadius
	 *            the deadZoneRadius to set
	 */
	public void setDeadZoneRadius(int deadZoneRadius) {
		this.deadZoneRadius = deadZoneRadius;
	}

	/**
	 * @return the injureZoneColor
	 */
	public Color getInjureZoneColor() {
		return injureZoneColor;
	}

	/**
	 * @param injureZoneColor
	 *            the injureZoneColor to set
	 */
	public void setInjureZoneColor(Color injureZoneColor) {
		this.injureZoneColor = injureZoneColor;
	}

	/**
	 * @return the injureRadius
	 */
	public int getInjureZoneRadius() {
		return injureZoneRadius;
	}

	/**
	 * @param injureRadius
	 *            the injureRadius to set
	 */
	public void setInjureZoneRadius(int injureZoneRadius) {
		this.injureZoneRadius = injureZoneRadius;
	}

	public void draw() {
		synchronized (Attack.class) {
			Cell cell = Campus.cells[grid.getI()][grid.getJ()];

			if (cell != null) {
				// Draw attack source
				StdDraw.setPenColor(color);
				StdDraw.filledCircle(cell.getLoc().getX(), cell.getLoc().getY(), size / 2);

				StdDraw.setPenColor(Color.WHITE);
				StdDraw.circle(cell.getLoc().getX(), cell.getLoc().getY(), size / 2);

				// Draw dead zone.
				// StdDraw.setPenColor(deadZoneColor);
				// StdDraw.circle(cell.getLoc().getX(), cell.getLoc().getY(),
				// this.deadZoneRadius);

				// Draw injured zone.
				// StdDraw.setPenColor(this.injureZoneColor);
				// StdDraw.circle(cell.getLoc().getX(), cell.getLoc().getY(),
				// this.injureZoneRadius);
			}
		}
	}

	public MomentStatus move() {
		synchronized (Attack.class) {
			MomentStatus tmStatus = new MomentStatus();

			Grid gridTo = chooseCellToMove();
			if (gridTo != null) {
				Cell cellTo = Campus.cells[gridTo.getI()][gridTo.getJ()];
				Cell cell = Campus.cells[grid.getI()][grid.getJ()];

				if (cellTo != null && cellTo != cell && cellTo.getType() == CellType.CLASSROOM && cellTo.isOpen()) {
					cellTo.setStatus(CellStatus.CLAIMED);
					this.clear();
					this.grid = gridTo;
					this.draw();
					cellTo.setOccupant(this);
				} else {
					// this.draw();
					// cell.setOccupant(this);
				}
			}
			return tmStatus;
		}
	}

	private Grid chooseCellToMove() {
		int nextI = grid.getI();
		int nextJ = grid.getJ();
		Random r = new Random();
		int idx = r.nextInt(5);
		switch (idx) {
		case 0:
			nextJ++;
			break;
		case 1:
			nextI++;
			nextJ++;
			break;
		case 2:
			nextI++;
			break;
		case 3:
			nextI++;
			nextJ--;
			break;
		case 4:
			nextJ--;
			break;
		default:
		}

		return new Grid(nextI, nextJ);
	}

	public void killNeighbors() {
		synchronized (Attack.class) {
			int i = grid.getI();
			int j = grid.getJ();
			Cell cell = Campus.cells[i + 1][j];
			kill(cell.getOccupant());

			cell = Campus.cells[i][j - 1];
			kill(cell.getOccupant());

			cell = Campus.cells[i - 1][j];
			kill(cell.getOccupant());

			cell = Campus.cells[i][j++];
			kill(cell.getOccupant());
		}
	}

	private void kill(Object occupant) {
		synchronized (Attack.class) {
			if (occupant != null && occupant instanceof Person) {
				((Person) occupant).setStatus(PersonStatus.DEAD);
				((Person) occupant).draw();
			}
		}
	}

	public void clear() {
		synchronized (Attack.class) {
			Cell cell = Campus.cells[grid.getI()][grid.getJ()];
			if (cell != null) {
				cell.draw();
				cell.setOccupant(null);
			}
		}
	}

	@Override
	protected void drawMe() {

	}
}
