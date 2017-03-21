package com.fastprac.sees.model.tool;

import com.fastprac.sees.model.tool.Button;
import com.fastprac.sees.model.tool.ButtonStatus;

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
}
