package com.fastprac.sees.model.tool;

import com.fastprac.sees.model.tool.Button;

public class Toolbar {

	private Button startBtn;
	private Button stopBtn;
	private Button resetBtn;
	private Button activeBtn;

	public Toolbar(Button startBtn, Button stopBtn, Button resetBtn) {
		this.startBtn = startBtn;
		this.stopBtn = stopBtn;
		this.resetBtn = resetBtn;
		this.activeBtn = null;
	}

	private void pressStart() {
		startBtn.disable();
		stopBtn.enable();
		resetBtn.disable();
		this.activeBtn = this.startBtn;
	}

	private void pressStop() {
		startBtn.enable();
		stopBtn.disable();
		resetBtn.enable();
		this.activeBtn = this.stopBtn;
	}

	private void pressReset() {
		startBtn.enable();
		stopBtn.disable();
		resetBtn.disable();
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
		resetBtn.disable();
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
			pressReset();
		}
	}

	public void draw() {
		startBtn.draw();
		stopBtn.draw();
		resetBtn.draw();
	}
}
