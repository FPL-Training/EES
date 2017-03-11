/**
 * 
 */
package com.fastprac.sees.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.fastprac.sees.task.DrawCell;
import com.fastprac.utils.lib.StdDraw;

/**
 * @author Admin
 *
 */
public class Campus {
	private Location startLoc;
	private int cellSize;

	public static final int numInX = 51;
	public static final int numInY = 31;
	public static final Cell[][] cells = new Cell[numInX][numInY];
	public static List<Cell> doors;
	public static List<Tree> trees;
	public static Attacker attacker;

	// Classroom
	private static int classroomStartI;
	private static int classroomStartJ;
	private static int classroomEndI;
	private static int classroomEndJ;
	private static int classroomNumX;
	private static int classroomNumY;

	/**
	 * Construct a Campus object using default settings.
	 */
	public Campus() {
		this.startLoc = new Location(0, 0);
		this.cellSize = 10;
		initialize();
	}

	/**
	 * @param startLoc
	 * @param cellSize
	 */
	public Campus(Location startLoc, int cellSize) {
		super();
		this.startLoc = startLoc;
		this.cellSize = cellSize;
		initialize();
	}

	/**
	 * @param startX
	 * @param startY
	 */
	public Campus(int startX, int startY, int cellSize) {
		this.startLoc = new Location(startX, startY);
		this.cellSize = cellSize;
		initialize();
	}

	/**
	 * @return the startX
	 */
	public Location getStartLoc() {
		return startLoc;
	}

	/**
	 * @param startX
	 *            the startX to set
	 */
	public void setStartLoc(Location startLoc) {
		this.startLoc = startLoc;
	}

	/**
	 * @return the cellSize
	 */
	public int getCellSize() {
		return cellSize;
	}

	/**
	 * @param cellSize
	 *            the cellSize to set
	 */
	public void setCellSize(int cellSize) {
		this.cellSize = cellSize;
	}

	/**
	 * Initialize campus cells
	 */
	private void initialize() {
		// Initialize campus cells;
		Cell.setStartLoc(this.startLoc);
		for (int i = 0; i < numInX; i++) {
			for (int j = 0; j < numInY; j++) {
				Cell cell = new Cell(new Grid(i, j), new Dimension(cellSize, cellSize), CellType.OUTER_AREA);
				// Store the cell
				cells[i][j] = cell;
			}
		}

		// Initialize doors
		doors = new ArrayList<Cell>();
		trees = new ArrayList<Tree>();
	}

	public static int getClassroomStartI() {
		return classroomStartI;
	}

	public static int getClassroomStartJ() {
		return classroomStartJ;
	}

	public static int getClassroomNumX() {
		return classroomNumX;
	}

	public static int getClassroomNumY() {
		return classroomNumY;
	}

	/**
	 * Draw a campus
	 */
	public void draw() {

		// Draw campus cells.
		drawCampus();

		// Draw campus wall
		drawCampusWall();

		// Draw classroom
		drawClassroom();

		// Draw doors
		drawClassroomDoors();

		// Draw doors
		drawTrees();
	}

	/**
	 * Draw campus
	 */
	private void drawCampus() {

		try {
			// Set thread pool.
			ExecutorService executor = Executors.newFixedThreadPool(numInX * numInY);

			// Create runnable tasks
			for (int i = 0; i < numInX; i++) {
				for (int j = 0; j < numInY; j++) {
					Cell cell = cells[i][j];
					if (cell != null) {
						executor.execute(new DrawCell(cell));
					}
				}
			}

			// Turn off thread pool
			executor.shutdown();

			// Wait until all threads are finish
			System.out.println("Draw school campus...");
			while (!executor.awaitTermination(10, TimeUnit.SECONDS))
				;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void drawCampusWall() {
		int centerI = numInX / 2;
		int centerJ = numInY / 2;
		int centerX = startLoc.getX() + cellSize * centerI;
		int centerY = startLoc.getY() + cellSize * centerJ;
		int halfWidth = cellSize * numInX / 2;
		int halfHeight = cellSize * numInY / 2;
		System.out.println(
				"x0=" + startLoc.getX() + ", y0=" + startLoc.getY() + ", w2=" + halfWidth + ", h2=" + halfHeight);

		StdDraw.setPenColor(Color.GRAY);
		StdDraw.rectangle(centerX, centerY, halfWidth + 2, halfHeight + 2);
	}

	private void drawClassroom() {
		try {
			// Set classroom area
			classroomStartI = numInX / 4;
			classroomStartJ = numInY / 4;
			classroomNumX = numInX - classroomStartI * 2;
			classroomNumY = numInY - classroomStartJ * 2;

			// Set thread pool.
			ExecutorService executor = Executors.newFixedThreadPool(classroomNumX * classroomNumY);

			// Create and execute runnable thread tasks
			for (int i = 0; i < classroomNumX; i++) {
				classroomEndI = classroomStartI + i;
				for (int j = 0; j < classroomNumY; j++) {
					classroomEndJ = classroomStartJ + j;
					Cell cell = cells[classroomEndI][classroomEndJ];
					if (cell != null) {
						cell.setType(CellType.CLASSROOM);

						executor.execute(new DrawCell(cell));
					}
				}
			}

			// Turn off thread pool
			executor.shutdown();

			// Wait until all threads are finish
			System.out.println("Draw school classroom building...");
			while (!executor.awaitTermination(10, TimeUnit.SECONDS))
				;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void drawClassroomDoors() {
		int eastDoorI = classroomEndI;
		int eastDoorJ = classroomStartJ + classroomNumY / 2;
		drawDoor(eastDoorI, eastDoorJ);
		drawDoor(eastDoorI, eastDoorJ - 1);
		drawDoor(eastDoorI, eastDoorJ + 1);

		 int southDoorI = classroomStartI + classroomNumX / 2;
		 int southDoorJ = classroomStartJ;
		 drawDoor(southDoorI, southDoorJ);
		 drawDoor(southDoorI - 1, southDoorJ);
		 drawDoor(southDoorI + 1, southDoorJ);
		
//		 int westDoorI = classroomStartI;
//		 int westDoorJ = classroomStartJ + classroomNumY / 2;
//		 drawDoor(westDoorI, westDoorJ);
//		 drawDoor(westDoorI, westDoorJ - 1);
//		 drawDoor(westDoorI, westDoorJ + 1);
		
		 int northDoorI = classroomStartI + classroomNumX / 2;
		 int northDoorJ = classroomEndJ;
		 drawDoor(northDoorI, northDoorJ);
		 drawDoor(northDoorI - 1, northDoorJ);
		 drawDoor(northDoorI + 1, northDoorJ);
	}

	private void drawTrees() {
		int id = 1;
		int treeI = classroomStartI + classroomNumX / 3;
		int treeJ = classroomStartJ + classroomNumY / 3;
		drawTree(id++, treeI, treeJ);
		drawTree(id++, treeI, treeJ-1);
		
		treeI = classroomStartI + classroomNumX * 2 / 3;
		treeJ = classroomStartJ + classroomNumY / 3;
		drawTree(id++, treeI, treeJ);
		drawTree(id++, treeI, treeJ-1);
		
		treeI = classroomStartI + classroomNumX * 2 / 3;
		treeJ = classroomStartJ + classroomNumY * 2 / 3;
		drawTree(id++, treeI, treeJ);
		drawTree(id++, treeI, treeJ+1);
		
		treeI = classroomStartI + classroomNumX / 3;
		treeJ = classroomStartJ + classroomNumY * 2 / 3;
		drawTree(id++, treeI, treeJ);
		drawTree(id++, treeI, treeJ+1);

		treeI = classroomStartI + classroomNumX / 2;
		treeJ = classroomStartJ + classroomNumY / 2;
		drawTree(id++, treeI, treeJ);
		drawTree(id++, treeI, treeJ-1);
		drawTree(id++, treeI, treeJ+1);
	}

	/**
	 * 
	 * @param doorI
	 * @param doorJ
	 */
	private void drawDoor(int doorI, int doorJ) {
		Cell doorCell = cells[doorI][doorJ];
		if (doorCell != null) {
			doorCell.setType(CellType.DOOR);
			doors.add(doorCell);
			doorCell.draw();
		}
	}

	private void drawTree(int id, int treeI, int treeJ) {
		Cell cell = cells[treeI][treeJ];
		if (cell != null) {
			Tree tree = new Tree(id, "", cellSize, cell, Color.GREEN);
			trees.add(tree);
			tree.draw();
		}
	}
}
