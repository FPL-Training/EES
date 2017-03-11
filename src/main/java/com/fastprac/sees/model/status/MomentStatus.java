/**
 * 
 */
package com.fastprac.sees.model.status;

import java.util.Date;

import com.fastprac.sees.model.Grid;

/**
 * @author Admin
 *
 */
public class MomentStatus {
	private Date moment;
	private Grid grid;
	private PersonStatus status;
	
	/**
	 * 
	 */
	public MomentStatus() {
		this.moment = new Date();
		this.grid = new Grid();
		this.status = PersonStatus.SAFE;
	}

}
