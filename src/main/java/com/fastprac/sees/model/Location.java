/**
 * 
 */
package com.fastprac.sees.model;

/**
 * @author Admin
 *
 */
public class Location {
	private int x;
	private int y;
	
	/**
	 * 
	 */
	public Location() {
		this.x = 0;
		this.y = 0;
	}

	/**
	 * @param x
	 * @param y
	 */
	public Location(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	

}
