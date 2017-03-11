/**
 * 
 */
package com.fastprac.sees.model;

import java.awt.Color;
import java.text.SimpleDateFormat;
import com.fastprac.utils.lib.StdDraw;

/**
 * @author Admin
 *
 */
public class Timer {
	private Location loc;
	private Dimension dimension;
	private long startTime;
	private long elapsedTime;
	public static final String pattern = "HH:mm:ss";
	public static final SimpleDateFormat format = new SimpleDateFormat(pattern);

	/**
	 * 
	 */
	public Timer() {
		this.loc = new Location();
		this.dimension = new Dimension(40, 30);
		this.startTime = System.currentTimeMillis();
	}

	/**
	 * @param loc
	 * @param dimension
	 */
	public Timer(Location loc, Dimension dimension) {
		super();
		this.loc = loc;
		this.dimension = dimension;
		this.startTime = System.currentTimeMillis();
	}

	public void clear() {
		synchronized (this.getClass()) {
			int halfWidth = dimension.getWidth() / 2;
			int halfHeight = dimension.getHeight() / 2;
			int x = loc.getX() + halfWidth;
			int y = loc.getY() + halfHeight;

			StdDraw.setPenColor(Color.WHITE);
			//StdDraw.text(x, y, Long.toString(elapsedTime));
			StdDraw.filledRectangle(x, y, halfWidth, halfHeight);
		}
	}

	/**
	 * @return the loc
	 */
	public Location getLoc() {
		return loc;
	}

	/**
	 * @param loc
	 *            the loc to set
	 */
	public void setLoc(Location loc) {
		this.loc = loc;
	}

	/**
	 * @return the dimension
	 */
	public Dimension getDimension() {
		return dimension;
	}

	/**
	 * @param dimension
	 *            the dimension to set
	 */
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	/**
	 * @return the startTime
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public void draw() {
		synchronized (this.getClass()) {
			int halfWidth = dimension.getWidth() / 2;
			int halfHeight = dimension.getHeight() / 2;
			int x = loc.getX() + halfWidth;
			int y = loc.getY() + halfHeight;

			elapsedTime = (System.currentTimeMillis() - startTime) / 1000;

			StdDraw.setPenColor(Color.RED);
			StdDraw.text(x, y, Long.toString(elapsedTime));

			//StdDraw.rectangle(x, y, halfWidth, halfHeight);
		}
	}

	public void reset() {
		startTime = System.currentTimeMillis();
		elapsedTime = 0;
	}

}
