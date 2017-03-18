/*
 * *********************************************************************************
 * Copyright (c) Fast & Practical Learning LLC (F&PL), 2017
 *
 * This unpublished material is proprietary to Fast & Practical Learning, LLC (F&PL).
 * All rights reserved. The methods and techniques described herein are considered 
 * trade secrets and/or confidential. Reproduction or distribution, in whole or in 
 * part, is forbidden except by express written permission of Fast & Practical 
 * Learning, LLC (F&PL).
 ***********************************************************************************
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
