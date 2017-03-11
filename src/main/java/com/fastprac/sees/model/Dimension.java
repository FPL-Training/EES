/**
 * 
 */
package com.fastprac.sees.model;

/**
 * @author Admin
 *
 */
public class Dimension {
	private int width;
	private int height;

	/**
	 * 
	 */
	public Dimension() {
		this.width = 1;
		this.height = 1;
	}

	/**
	 * @param width
	 * @param height
	 */
	public Dimension(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

}
