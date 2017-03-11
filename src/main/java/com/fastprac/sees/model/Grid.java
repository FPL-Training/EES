/**
 * 
 */
package com.fastprac.sees.model;

/**
 * @author Admin
 *
 */
public class Grid {
	private int i;
	private int j;

	/**
	 * 
	 */
	public Grid() {
		this.i = 0;
		this.j = 0;
	}

	/**
	 * @param i
	 * @param j
	 */
	public Grid(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}

	/**
	 * @return the i
	 */
	public int getI() {
		return i;
	}

	/**
	 * @param i the i to set
	 */
	public void setI(int i) {
		this.i = i;
	}

	/**
	 * @return the j
	 */
	public int getJ() {
		return j;
	}

	/**
	 * @param j the j to set
	 */
	public void setJ(int j) {
		this.j = j;
	}

}
