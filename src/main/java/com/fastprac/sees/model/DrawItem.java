package com.fastprac.sees.model;

public abstract class DrawItem {

	public DrawItem() {
		
	}
	
	protected abstract void drawMe();

	protected void drawItem() {
		synchronized (DrawItem.class) {
			drawMe();
		}
	}
}
