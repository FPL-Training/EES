/**
 * 
 */
package com.fastprac.sees.model.tool;

import java.awt.Color;
import java.awt.Font;

import com.fastprac.sees.model.Dimension;
import com.fastprac.sees.model.Location;
import com.fastprac.utils.lib.StdDraw;

/**
 * @author Admin
 *
 */
public class Button extends ToolItem {
	private Dimension dimension;
	private ButtonStatus status;
	
	/**
	 * 
	 */
	public Button() {
		super();
		this.dimension = new Dimension();
		this.status = ButtonStatus.ON;		
	}
	
	/**
	 * 
	 * @param name
	 * @param text
	 * @param loc
	 * @param dimension
	 */
	public Button(String name, String text, Location loc, Dimension dimension) {		
		super(name, text, loc);
		this.dimension = dimension;
		this.status = ButtonStatus.ON;
	}

	/**
	 * @return the status
	 */
	public ButtonStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ButtonStatus status) {
		this.status = status;
	}

	@Override
	public void draw() {
		int halfWidth = dimension.getWidth()/2;
		int halfHeight = dimension.getHeight()/2;
		int x = loc.getX() + halfWidth;
		int y = loc.getY() + halfHeight;

		StdDraw.setPenColor(status.getBorderColor());
		StdDraw.rectangle(x, y, halfWidth, halfHeight);
		
		StdDraw.setPenColor(status.getTextColor());
		StdDraw.text(x, y, text);
	}


}