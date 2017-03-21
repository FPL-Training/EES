package com.fastprac.sees.model.tool;

import com.fastprac.sees.model.tool.Button;

public class Toolbar {

	private Button startBtn;
	private Button stopBtn;
	private Button resetBtn;

	public Toolbar(Button startBtn, Button stopBtn, Button resetBtn) {
		this.startBtn = startBtn;
		this.stopBtn = stopBtn;
		this.resetBtn = resetBtn;
	}

	public void pressStart() {
		startBtn.disable();
		stopBtn.enable();
	}

	public void pressStop() {
		startBtn.enable();
		stopBtn.disable();
	}

	public void reset() {
		startBtn.enable();
		stopBtn.disable();
	}

	public Button getStartBtn() {
		return startBtn;
	}

	public Button getStopBtn() {
		return stopBtn;
	}

	public Button getResetBtn() {
		return resetBtn;
	}

	public void enable() {
		startBtn.enable();
		stopBtn.disable();
		resetBtn.enable();
	}

	public void disable() {
		startBtn.disable();
		stopBtn.disable();
		resetBtn.disable();
	}

	public void toggle(int x, int y) {
		if (startBtn.pointOn(x, y) && startBtn.isReleased()) {
			pressStart();
		} else if (stopBtn.pointOn(x, y) && stopBtn.isReleased()) {
			pressStop();
		} else if (resetBtn.pointOn(x, y) && resetBtn.isReleased()) {
			pressStop();
		}
	}

	public void draw() {
		startBtn.draw();
		stopBtn.draw();
		resetBtn.draw();
	}
}
