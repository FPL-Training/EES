package com.fastprac.sees.task;

public abstract class TaskBase {

	protected Controller ctrl;
	
	public TaskBase() {
		ctrl = null;
	}
	
	public TaskBase(Controller ctrl) {
		this.ctrl = ctrl;
	}

}
