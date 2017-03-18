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
package com.fastprac.sees.model.tool;

import java.awt.Color;

/**
 * @author Admin
 *
 */
public interface ToolItemStatus {
	public Color getTextColor();
	public Color getBorderColor();
	public void setTextColor(Color color);
	public void setBorderColor(Color color);
}
