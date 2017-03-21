package com.fastprac.sees.model.tool;

public enum ButtonType {
	START("Start"),
	STOP("Stop"),
	RESET("Reset");
	
	private String label;
	
	private ButtonType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
